package com.tour.suse.service;

import java.util.Collection;
import java.util.List;

import com.tour.suse.base.DaoSupport;
import com.tour.suse.entity.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege> {

	List<Privilege> findTopList();

	Collection<Privilege> getAllPrivilegeUrls();

}
