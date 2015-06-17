package com.tour.suse.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;
import com.tour.suse.base.BaseAction;
import com.tour.suse.entity.Note;
import com.tour.suse.entity.NoteComment;
import com.tour.suse.entity.User;
import com.tour.suse.service.NoteCommentService;
import com.tour.suse.util.HtmlUtil;
import com.tour.suse.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class NoteCommentAction extends BaseAction<NoteComment>{
	private Long NoteId;
	public Long getNoteId() {
		return NoteId;
	}

	public void setNoteId(Long noteId) {
		NoteId = noteId;
	}
	//游记评论查看ajax
	@SuppressWarnings("unchecked")
	public String getCommByNoteId()
	{
		if(NoteId!=null)
		{
			List<NoteComment> queryList= new QueryHelper("SELECT new com.tour.suse.entity." +
					"NoteComment(nc.id,nc.content,nc.postTime,nc.author.username,nc.author.head,nc.noteReplies.size)",NoteComment.class,"nc")
			.addCondition(true, "nc.note.id=?", NoteId)
			.addOrderProperty("postTime", true)
			.preparePageBean2(noteCommentService, pageNum, 10);
			System.out.println("id:"+NoteId+",con:"+queryList);
			String JsonQuery = JSONObject.toJSONString(queryList);
			System.out.println("评论查询第"+pageNum+"页："+JsonQuery);
			getOut().print("true"+JsonQuery);
			return null;
		}
		return null;
	}
	//游记评论添加ajax
	public String add(){
		try{
			if(NoteId!=null && getUser()!=null){
				if(filter.isContentKeyWords(model.getContent())){//判断是否有敏感词汇
					model.setContent(HtmlUtil.ConvertToText(filter.getReplaceKeyWords(model.getContent())));//敏感词和谐
				}
					Date date = new Date();
					model.setPostTime(date);
					model.setAuthor(getUser());
					Note note = noteService.getById(NoteId);
					//关联 Note
					model.setNote(note);
					note.setCommentNum(note.getCommentNum()+1);
				noteCommentService.save(model);
				noteService.update(note);
				model = noteCommentService.getByDate(date);
				List<NoteComment> listone =new ArrayList<NoteComment>();
				listone.add(model);
				String JsonQuery = JSONObject.toJSONString(listone);
				System.out.println("comm:"+JsonQuery);
				getJsonOut().print(JsonQuery);
				return null;
			}else
			{
				getOut().print(0);
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
			getOut().print(-1);
			return null;
		}
	}
}
