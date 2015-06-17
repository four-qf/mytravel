package com.tour.suse.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhu.jsonBean.MinMaxDate;
import zhu.jsonBean.TJJsON;

import com.tour.suse.base.DaoSupportImpl;
import com.tour.suse.entity.ScenicComment;
import com.tour.suse.service.ScenicCommentService;
@Transactional
@Service
public class ScenicCommentServiceImpl extends DaoSupportImpl<ScenicComment> implements ScenicCommentService{
	
	public List<TJJsON> getListByTimeAndScenicId(Date strDate,Date endDate, Long scenicId) {
		String sql = "SELECT new zhu.jsonBean.TJJsON(id,status,postTime)" +
		" FROM ScenicComment sc" +
		" WHERE sc.scenic.status=1 AND sc.scenic.id=? AND sc.postTime BETWEEN ? AND ?" +
		" ORDER BY sc.postTime ASC";
		System.out.println("sql"+sql+"str"+strDate+",end"+endDate);
		return this.getSession().createQuery//zhu.jsonBean
			(sql)
				.setParameter(0, scenicId)
				.setParameter(1, strDate)
				.setParameter(2, endDate)
				.list();
	}

	@SuppressWarnings("unchecked")
	
	public List<TJJsON> getListByTimeAndCityId(Date statrdate, Date enddate, Long cityId) {
		if(cityId==0)//全省
		{
			return getSession().createQuery//含有评论的景区  才有统计数据
			("SELECT new zhu.jsonBean.TJJsON(sc.id,sc.status,sc.scenic.id,sc.scenic.name,sc.scenic.clickNum)" +
					" FROM ScenicComment sc " +
					" WHERE sc.scenic.status=1 AND sc.postTime BETWEEN ? AND ?" +
					" order by sc.postTime ASC")
					.setParameter(0, statrdate)
					.setParameter(1, enddate)
					.list();
		}else
		return getSession().createQuery
		("SELECT new zhu.jsonBean.TJJsON(id,status,scenic.id,scenic.name,scenic.clickNum)" +
				" FROM ScenicComment sc" +
				" WHERE sc.scenic.city.id=? AND sc.scenic.status=1 AND sc.postTime BETWEEN ? AND ?" +
				" order by sc.postTime ASC")
				.setParameter(0, cityId)
				.setParameter(1, statrdate)
				.setParameter(2, enddate)
				.list();
	}

	
	public MinMaxDate getminMaxDate() {
		return (MinMaxDate) this.getSession().createQuery
		("SELECT new zhu.jsonBean.MinMaxDate(MIN(sc.postTime),MAX(sc.postTime)) " +
		"FROM ScenicComment sc WHERE sc.scenic.status=1")
				.uniqueResult();
	}

}
