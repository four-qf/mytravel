package com.tour.suse.service;

import java.util.List;

import zhu.jsonBean.NameIdByScenic;

import com.tour.suse.base.DaoSupport;
import com.tour.suse.entity.Note;

public interface NoteService extends DaoSupport<Note>{

	void deleteList(String[] array);

	List<Note> getByScenicId(Long sId);

	List<Note> getHotList();
	void deleteList2(String[] array);
	List<NameIdByScenic> findAllAboutCity(Long cityId);
}
