package com.tour.suse.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.suse.base.DaoSupportImpl;
import com.tour.suse.entity.City;
import com.tour.suse.service.CityService;
@Service
@Transactional
public class CityServiceImpl extends DaoSupportImpl<City> implements CityService{

}
