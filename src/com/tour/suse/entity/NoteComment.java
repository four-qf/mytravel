package com.tour.suse.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class NoteComment implements Serializable{
	private Long id;
	private String content;
	private Date postTime;
	private User author;
	private Note note;
	private Set<NoteReply> noteReplies = new HashSet<NoteReply>();
	private String username;
	private String userhead;
	private Integer replnum;
	private Long authorId;
	public NoteComment(){}
	
	public NoteComment( String content, Date postTime, User author) {
		this.content = content;
		this.postTime = postTime;
		this.author = author;
	}
	public NoteComment(Long id, String content, Date postTime, User author) {
		this.id = id;
		this.content = content;
		this.postTime = postTime;
		this.author = author;
	}

	public NoteComment(Long id, String content, Date postTime, String username,
			String userhead,Integer num,Long authorId) {
		this.id = id;
		this.content = content;
		this.postTime = postTime;
		this.username = username;
		this.userhead = userhead;
		this.replnum = num;
		this.authorId = authorId;
	}
	public NoteComment(Long id, String content, Date postTime, String username,
			String userhead,Integer num) {
		this.id = id;
		this.content = content;
		this.postTime = postTime;
		this.username = username;
		this.userhead = userhead;
		this.replnum = num;
	}
	public NoteComment(Long id, String content, Date postTime, String username,
			String userhead) {
		super();
		this.id = id;
		this.content = content;
		this.postTime = postTime;
		this.username = username;
		this.userhead = userhead;
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
	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public Set<NoteReply> getNoteReplies() {
		return noteReplies;
	}
	public void setNoteReplies(Set<NoteReply> noteReplies) {
		this.noteReplies = noteReplies;
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
	public Integer getReplnum() {
		return replnum;
	}
	public void setReplnum(Integer replnum) {
		this.replnum = replnum;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
}
