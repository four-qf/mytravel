package com.tour.suse.service.impl;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhu.jsonBean.HotScenicJson;
import zhu.jsonBean.NameIdByScenic;

import com.tour.suse.base.DaoSupportImpl;
import com.tour.suse.entity.Scenic;
import com.tour.suse.entity.ScenicClass;
import com.tour.suse.service.ScenicService;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class ScenicServiceImpl extends DaoSupportImpl<Scenic> implements ScenicService{

	//真正删除
	
	public void deleteList(String[] arrayId) {
		for (String id: arrayId){//删除没一个景区前先取出图片地址 删除图片
			Long LId = Long.parseLong(id);
			String url = (String) this.getSession().createQuery("SELECT s.imageUrl FROM Scenic s WHERE s.id=?")
			.setParameter(0, LId).uniqueResult();
			if(url!=null){
				String [] arrs = url.split(";");
				for(String a:arrs)
				{
					File file = new File(getRoot()+a);
					if(file!=null && file.exists())
					{file.delete();System.out.println("删除了图片");}
				}
			}
			this.delete(LId);
		}
	}

	
	public String findByName(String name) {
		return (String) this.getSession().createQuery("SELECT s.name " +
				"FROM Scenic s  WHERE name=? AND status=1")
				.setParameter(0, name)
				.uniqueResult();
	}

	
	public List<HotScenicJson> findHotScenic() {
		return this.getSession().createQuery(
				"SELECT new zhu.jsonBean.HotScenicJson(s.id,s.name,s.description,substring(s.imageUrl,1,20)) FROM Scenic s " +
		"WHERE s.status=1 ORDER BY s.clickNum/10+s.commentNum DESC ")
		.setFirstResult(0)
		.setMaxResults(9)
		.list();
	}
	
	
	public List<Scenic> findAllByPostTime() {
		return this.getSession().createQuery(
				"FROM Scenic s WHERE status=1 ORDER BY s.postTime DESC")
				.list();
	}

	
	public void delete2(Long id) {
		try{
		Scenic model= this.getById(id);
		model.setStatus(0);
		this.getSession().update(model);
		}catch(Exception e)
		{
			 e.printStackTrace();
			new RuntimeException();}
	}

	
	public void deleteList2(String[] array) {
		try{
			for(String id: array)
			{
				//setStatus
				//修改status
				Scenic temScenic = this.getById(Long.parseLong(id));
				temScenic.setStatus(0);
				this.getSession().update(temScenic);
			}
		}catch(Exception e)
		{e.printStackTrace();new RuntimeException();}
	}

	
	public void rebackById(Long id) {
		try{
		Scenic model = this.getById(id);
		model.setStatus(1);
		this.getSession().update(model);
		}catch(Exception e)
		{
			new RuntimeException();
		}
	}

	
	public List<NameIdByScenic> findAllAboutCity(Long cityId) {
		return this.getSession().createQuery(
				"select new zhu.jsonBean.NameIdByScenic(id,name) FROM Scenic s WHERE s.city.id=? AND status=1 ORDER BY postTime ASC")
		.setParameter(0, cityId)
		.list();
	}

	
	public void updateImgUrlById(Long id,String afterUpdateUrl) {
		try{
		System.out.println(getSession().createSQLQuery("UPDATE Scenic s SET s.imageUrl=? WHERE s.id=?")
		.setParameter(0, afterUpdateUrl)
		.setParameter(1, id).executeUpdate());
		System.out.println("update部分 正常");
		}catch(Exception e){e.printStackTrace();}
	}

	
	public void updateClassById(Set<ScenicClass> scenicClasses, Long scenicID) {
		try{
			System.out.println(getSession().createSQLQuery("UPDATE Scenic s SET s.scenicClasses=? WHERE s.id=?")
			.setParameter(0, scenicClasses)
			.setParameter(1, scenicID).executeUpdate());
			System.out.println("update部分 正常");
			}catch(Exception e){e.printStackTrace();}
	}

	
	public List<HotScenicJson> findAllAboutCity0(Long id) {
		return this.getSession().createQuery(
	"select new zhu.jsonBean.HotScenicJson(s.id,s.name,s.description,substring(s.imageUrl,1,20)) FROM Scenic s WHERE s.city.id=? AND s.status=1 ORDER BY s.postTime DESC")
			.setParameter(0, id)
			.setFirstResult(0)
			.setMaxResults(9)
			.list();
	}

	
	public Scenic getnotescenicById(Long scenicId) {
		return (Scenic)this.getSession().createQuery
		("SELECT new com.tour.suse.entity." +
				"Scenic(s.name,s.level,s.description,s.postTime,s.clickNum,s.commentNum,s.author.username,s.city.name)" +
				" FROM Scenic s WHERE s.id=?")
		.setParameter(0, scenicId).uniqueResult();
	}

	
	
	public List<Scenic> getRecommScenic() {
		return this.getSession()//最新到最旧
		.createQuery("SELECT new com.tour.suse.entity." +
				"Scenic(s.id,s.name,substring(s.imageUrl,1,20))" +
				"FROM Scenic s WHERE s.status=1 ORDER BY s.postTime DESC")
		.setFirstResult(0)
		.setMaxResults(10)
		.list();
	}

	
	public Scenic getScoreById(Long scenicId) {
		return (Scenic) this.getSession().createQuery
		("SELECT new com.tour.suse.entity.Scenic" +
				"(s.id,s.environmentScore,s.transportScore,s.realCommentNum,s.serviceScore,s.peopleScore,s.totalScore)" +
				"FROM Scenic s WHERE s.id=?")
		.setParameter(0, scenicId)
		.uniqueResult();
	}
	private String getRoot()
	{
		return ServletActionContext.getServletContext().getRealPath("/");
	}
}
