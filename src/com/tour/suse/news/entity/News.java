package com.tour.suse.news.entity;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.tour.suse.entity.User;

@Entity
public class News implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer newsid;
	
	private String newstitle;
	
	@Column(length=500)
	private String newscontent;
	
	private File attachment;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userid",referencedColumnName="id")
	private User userid;
	
	private Timestamp createtime;

	public Integer getNewsid() {
		return newsid;
	}

	public void setNewsid(Integer newsid) {
		this.newsid = newsid;
	}

	public String getNewstitle() {
		return newstitle;
	}

	public void setNewstitle(String newstitle) {
		this.newstitle = newstitle;
	}

	public String getNewscontent() {
		return newscontent;
	}

	public void setNewscontent(String newscontent) {
		this.newscontent = newscontent;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public User getUserid() {
		return userid;
	}

	public void setUserid(User userid) {
		this.userid = userid;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "News [newsid=" + newsid + ", newstitle=" + newstitle
				+ ", newscontent=" + newscontent + ", attachment=" + attachment
				+ ", userid=" + userid + ", createtime=" + createtime + "]";
	}
	
	
}
