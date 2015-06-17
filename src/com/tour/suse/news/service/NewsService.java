package com.tour.suse.news.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.suse.news.dao.NewsDao;
import com.tour.suse.news.entity.News;

@Service
@Transactional(readOnly = true, noRollbackFor = {Exception.class})
public class NewsService {
	
	@Resource
	private NewsDao newsDao;
	
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	@Transactional
	public void save(News model) {
		newsDao.save(model);
	}

	public List<News> getAll() {
		return newsDao.getAll();
	}

	@Transactional
	public void delete(Integer newsid) {
		newsDao.delete(newsid);
	}

	public News getNewsById(Integer newsid) {
		return newsDao.getNewsById(newsid);
	}

	
}
