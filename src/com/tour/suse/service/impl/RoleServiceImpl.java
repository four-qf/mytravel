package com.tour.suse.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.suse.base.DaoSupportImpl;
import com.tour.suse.entity.Role;
import com.tour.suse.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends DaoSupportImpl<Role> implements RoleService {

	
}
