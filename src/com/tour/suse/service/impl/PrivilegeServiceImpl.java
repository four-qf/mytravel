package com.tour.suse.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.suse.base.DaoSupportImpl;
import com.tour.suse.entity.Privilege;
import com.tour.suse.service.PrivilegeService;
@Service
@Transactional
public class PrivilegeServiceImpl extends DaoSupportImpl<Privilege> implements PrivilegeService{
	
	@SuppressWarnings("unchecked")
	public List<Privilege> findTopList() {
		
		return getSession().createQuery(
				"FROM Privilege p WHERE p.parent IS NULL")
				.list();
	}

	
	public Collection<Privilege> getAllPrivilegeUrls() {
		return getSession().createQuery(
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")
				.list();//DISTINCT ���ظ�
	}

	
}
