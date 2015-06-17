
package com.tour.suse.entity;
import java.io.Serializable;
import java.util.Date;


public class NoteReply implements Serializable{
	private Long id;
	private String content;
	private Date postTime;
	private NoteComment noteComment;
	private User author;
	private String username;
	private String userhead;
	private Long authorId;
	public NoteReply(){}
	public NoteReply(Long id, String content, Date postTime,
			 String name,String head,Long authorId) {
		this.id = id;
		this.content = content;
		this.postTime = postTime;
		this.userhead = head;
		this.username = name;
		this.authorId =authorId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public NoteComment getNoteComment() {
		return noteComment;
	}
	public void setNoteComment(NoteComment noteComment) {
		this.noteComment = noteComment;
	}
	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserhead() {
		return userhead;
	}
	public void setUserhead(String userhead) {
		this.userhead = userhead;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
}
