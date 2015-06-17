package com.tour.suse.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tour.suse.base.DaoSupportImpl;
import com.tour.suse.entity.NoteReply;
import com.tour.suse.service.NoteReplyService;
@Service
@Transactional
public class NoteReplyServiceImpl extends DaoSupportImpl<NoteReply> implements NoteReplyService{
	
}
