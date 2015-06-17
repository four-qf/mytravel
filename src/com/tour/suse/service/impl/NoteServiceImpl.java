package com.tour.suse.service.impl;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zhu.jsonBean.NameIdByScenic;

import com.opensymphony.xwork2.ActionContext;
import com.tour.suse.base.DaoSupportImpl;
import com.tour.suse.entity.Note;
import com.tour.suse.entity.NoteComment;
import com.tour.suse.entity.NoteReply;
import com.tour.suse.entity.User;
import com.tour.suse.service.NoteCommentService;
import com.tour.suse.service.NoteReplyService;
import com.tour.suse.service.NoteService;
@Service
@Transactional
public class NoteServiceImpl extends DaoSupportImpl<Note> implements NoteService{
	@Resource
	protected NoteCommentService noteCommentService;
	@Resource
	protected NoteReplyService noteReplyService;//
	
	public void deleteList(String[] array) {
		for(String id: array)
		{
			//首先取出NOTE表 在放入subNOTE表 最后删除
			Note model = this.getById(Long.parseLong(id));
			model.setStatus(0);
			model.setDelUsername(getUser().getUsername());
			this.getSession().update(model);
		}
	}
	//真正的删除
	public void deleteList2(String[] array) {
		for(String id: array)
		{
			//首先取出NOTE表 在放入subNOTE表 最后删除
			Note model = this.getById(Long.parseLong(id));
			//删除对应的图片
				File indexfile = new File(ServletActionContext.getServletContext().getRealPath("/")+model.getImageUrl());
				if(indexfile.exists())
				{
					System.out.println("删除index了:"+indexfile.delete());
				}
				//获取内容图片del
					String regEx_img = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
					 Pattern p_image = Pattern.compile 
			                 (regEx_img,Pattern.CASE_INSENSITIVE);   
					 Matcher  m_image = p_image.matcher(model.getContent()); 
					 String v1="";//全标签
					 String v2="";//路径
					 if(m_image.find()) 
					 {
						 System.out.println("匹配到："); 
						 v1 =  m_image.group();
						 v2 =  m_image.group(1);
					 }
						String[] a=  v2.substring(1,v2.length()).split("\\/");
						v2 = ServletActionContext.getServletContext().getRealPath("/")+a[1]+"/"+a[2]+"/"+a[3];
						File file = new File(v2);
						if(file.exists())
						{
							System.out.println("删除了:"+file.delete());
					}
				//删除
						
			Set<NoteComment>  notecomms = model.getNoteComments();
			for(NoteComment nc: notecomms){
				Set<NoteReply> notereplies= nc.getNoteReplies();
					for(NoteReply nreply:notereplies)
					{
						noteReplyService.delete(nreply.getId());
					}
				noteCommentService.delete(nc.getId());
			}
			this.delete(Long.parseLong(id));
		}
	}
	private User getUser() {
		 if((User)ActionContext.getContext().getSession().get("admin")!=null)
			 return (User)ActionContext.getContext().getSession().get("admin");
		 else
			 return (User)ActionContext.getContext().getSession().get("user");
	}

	
	@Deprecated
	public List<Note> getByScenicId(Long sId) {
		return this.getSession().createQuery(
		"SELECT new com.tour.suse.entity." +
		"Note(n.id, n.title,n.imageUrl,n.postTime)" +
		"FROM Note n WHERE n.sId=? ORDER BY n.postTime DESC")
		.setFirstResult(0)
		.setMaxResults(10)
		.setParameter(0, sId)
		.list();
	}

	
	public List<Note> getHotList() {
		return this.getSession().createQuery
		("SELECT new  com.tour.suse.entity.Note(n.id,n.title,n.postTime)" +
				"FROM Note n ORDER BY (n.clickNum/100+n.noteComments.size) DESC")
		.setFirstResult(0)
		.setMaxResults(10)
		.list();
	}
	public List<NameIdByScenic> findAllAboutCity(Long cityId) {
		return (List<NameIdByScenic>) this.getSession().createQuery(
				"select new zhu.jsonBean.NameIdByScenic(id,name) FROM Scenic s WHERE s.city.id=? AND status=1 ORDER BY postTime ASC")
		.setParameter(0, cityId)
		.list();
	}

}
