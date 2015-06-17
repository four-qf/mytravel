package com.tour.suse.service;

import java.util.Date;

import com.tour.suse.base.DaoSupport;
import com.tour.suse.entity.NoteComment;

public interface NoteCommentService extends DaoSupport<NoteComment>{
	void deleteList(String[] array);

	NoteComment getByDate(Date date);
}
