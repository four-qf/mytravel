package com.tour.suse.service;

import java.util.List;

import com.tour.suse.base.DaoSupport;
import com.tour.suse.entity.ScenicClass;

public interface ScenicClassService extends DaoSupport<ScenicClass>{

	List<ScenicClass> findTopList();

	List<ScenicClass> findNoChildrenList();

}
