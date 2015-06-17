package com.tour.suse.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.suse.base.DaoSupportImpl;
import com.tour.suse.entity.SubScenic;
import com.tour.suse.entity.User;
import com.tour.suse.service.SubScenicService;
@Service
@Transactional
public class SubScenicServiceImpl extends DaoSupportImpl<SubScenic> implements SubScenicService  {

	public SubScenic getByLastDateAndUser(User user) {
		
		return (SubScenic) getSession().createQuery(
				"FROM SubScenic WHERE authorId=? ORDER BY postTime DESC")
				.setParameter(0, user.getId())
				.setFirstResult(0)
				.setMaxResults(1)
				.uniqueResult();
	}

	

}
