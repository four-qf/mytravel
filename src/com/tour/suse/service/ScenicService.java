package com.tour.suse.service;

import java.util.List;
import java.util.Set;

import zhu.jsonBean.HotScenicJson;
import zhu.jsonBean.NameIdByScenic;

import com.tour.suse.base.DaoSupport;
import com.tour.suse.entity.Scenic;
import com.tour.suse.entity.ScenicClass;

public interface ScenicService extends DaoSupport<Scenic>{

	void deleteList(String[] arrayId);

	String findByName(String name);

	List<HotScenicJson> findHotScenic();

	List<Scenic> findAllByPostTime();

	void delete2(Long id);

	void deleteList2(String[] array);

	void rebackById(Long id);

	List<NameIdByScenic> findAllAboutCity(Long cityId);

	void updateImgUrlById(Long id, String updateAfter);

	void updateClassById(Set<ScenicClass> scenicClasses, Long scenicID);

	List<HotScenicJson> findAllAboutCity0(Long id);

	Scenic getnotescenicById(Long scenicId);

	List<Scenic> getRecommScenic();

	Scenic getScoreById(Long scenicId);

}
