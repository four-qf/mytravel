package com.tour.suse.news.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tour.suse.news.base.NewDaoBase;
import com.tour.suse.news.entity.News;

@Repository
public class NewsDao extends NewDaoBase{ 

	public void save(News model) {
		getSession().saveOrUpdate(model);
	}

	public List<News> getAll() {
		String hql = " From News order by createtime desc";
		@SuppressWarnings("unchecked")
		List<News> list = getSession().createQuery(hql).list();
		return list;
	}

	public void delete(Integer newsid) {
		String hql = "DELETE From News n where n.newsid = ? ";
		getSession().createQuery(hql).setInteger(0, newsid).executeUpdate();
	}

	public News getNewsById(Integer newsid) {
		String hql = "From News n where n.newsid = ?";
		return (News) getSession().createQuery(hql).setInteger(0, newsid).uniqueResult();
	}

}
