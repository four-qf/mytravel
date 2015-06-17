package com.tour.suse.service;

import com.tour.suse.base.DaoSupport;
import com.tour.suse.entity.SubScenic;
import com.tour.suse.entity.User;

public interface SubScenicService extends DaoSupport<SubScenic>{

	SubScenic getByLastDateAndUser(User user);

}
