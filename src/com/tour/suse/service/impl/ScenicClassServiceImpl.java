package com.tour.suse.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.suse.base.DaoSupportImpl;
import com.tour.suse.entity.ScenicClass;
import com.tour.suse.service.ScenicClassService;
@Transactional
@Service
public class ScenicClassServiceImpl extends DaoSupportImpl<ScenicClass> implements ScenicClassService{

	public List<ScenicClass> findTopList() {
		return getSession().createQuery(
				"FROM ScenicClass sc WHERE sc.parent IS  NULL ORDER BY id")
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<ScenicClass> findNoChildrenList() {
		return  getSession().createQuery(
		"FROM ScenicClass sc WHERE sc.parent IS NULL")
		.list();
	}

}
