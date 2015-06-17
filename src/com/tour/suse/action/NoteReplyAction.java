package com.tour.suse.action;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.tour.suse.base.BaseAction;
import com.tour.suse.entity.Note;
import com.tour.suse.entity.NoteComment;
import com.tour.suse.entity.NoteReply;
import com.tour.suse.entity.User;
import com.tour.suse.util.QueryHelper;

/**
 * @author nn
 *
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class NoteReplyAction extends BaseAction<NoteReply>{
	NoteComment comment;
	Note note;
	Long commentId; 
	//回复添加ajax
	public String add(){
//		commentId = model.getNoteComment().getId();
		System.out.println(getUser().getUsername()+"回复了id为"+commentId+"的评论");
		if(commentId!=null && getUser()!=null)
		{
			if(filter.isContentKeyWords(model.getContent())){//判断是否有敏感词汇
				model.setContent(filter.getReplaceKeyWords(model.getContent()));//敏感词和谐
			}
			System.out.println("回复添加");
			comment = noteCommentService.getById(commentId);
			note = comment.getNote();
			model.setPostTime(new Date());
			model.setAuthor(getUser());
			note.setCommentNum(comment.getNote().getCommentNum());
			model.setNoteComment(comment);
			noteReplyService.save(model);
			noteService.update(note);
			getOut().print("true");
			return null;
		}else{return "noLogin";}
	}
	//回复查看ajax
	public String getReplyByCommentId(){
//		commentId = model.getNoteComment().getId();
		System.out.println("回复查看页数:"+pageNum);
		if(commentId!=null)
		{
			System.out.println("游记评论回复"+commentId);
			List<NoteReply> queryList= new QueryHelper("SELECT new com.tour.suse.entity." +
					"NoteReply(nr.id,nr.content,nr.postTime,nr.author.username,nr.author.head,nr.author.id) ",NoteReply.class,"nr")
			.addCondition(true, "nr.noteComment.id=?", commentId)
			.addOrderProperty("postTime", true)
			.preparePageBean2(noteReplyService, pageNum, 10);
			System.out.println("id:"+commentId+",con:"+queryList);
			String JsonQuery = JSONObject.toJSONString(queryList);
			System.out.println("回复查询第"+pageNum+"页："+JsonQuery);
			getOut().print("true"+JsonQuery);
			return null;
		}
		return null;
	}

	public NoteComment getComment() {
		return comment;
	}
	public void setComment(NoteComment comment) {
		this.comment = comment;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
}
