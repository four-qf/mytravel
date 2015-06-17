package com.tour.suse.service;

import java.util.Date;
import java.util.List;

import zhu.jsonBean.MinMaxDate;
import zhu.jsonBean.TJJsON;

import com.tour.suse.base.DaoSupport;
import com.tour.suse.entity.ScenicComment;

public interface ScenicCommentService extends DaoSupport<ScenicComment>{

	List<TJJsON> getListByTimeAndScenicId (Date date, Date enddate, Long scenicId);

	List<TJJsON> getListByTimeAndCityId(Date statrdate, Date enddate,
			Long cityId);

	MinMaxDate getminMaxDate();

}
