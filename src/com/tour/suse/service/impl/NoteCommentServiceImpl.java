package com.tour.suse.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.suse.base.DaoSupportImpl;
import com.tour.suse.entity.NoteComment;
import com.tour.suse.service.NoteCommentService;
@Service
@Transactional
public class NoteCommentServiceImpl extends DaoSupportImpl<NoteComment> implements NoteCommentService{

	public void deleteList(String[] array) {
		for(String id: array)
		{
			this.delete(Long.parseLong(id));
		}
	}

	
	public NoteComment getByDate(Date date) {
		return (NoteComment) this.getSession().createQuery(
				"SELECT new com.tour.suse.entity." +
	"NoteComment(nc.id,nc.content, nc.postTime,nc.author.username,nc.author.head) From NoteComment nc WHERE nc.postTime=?")
				.setParameter(0, date)
				.uniqueResult();
	}
}
