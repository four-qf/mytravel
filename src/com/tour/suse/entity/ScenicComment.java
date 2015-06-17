package com.tour.suse.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : ScenicComment.java
//  @ Date : 2014/5/22
//  @ Author : @\zhuxiao
public class ScenicComment implements Serializable{
	private Long id;
	private String title;
	private String content;
	private Date postTime;
	private Float serviceNum=0.0f;
	private Float peopNum=0.0f;
	private Float totalNum=0.0f;
	private Float environmentNum=0.0f;
	private Float transportNum=0.0f;
	private Integer status;
	private User author;
	private Scenic scenic;
	public ScenicComment(){}
	public ScenicComment(String title, String content, Date postTime,
			Float serviceNum, Float peopNum, Float totalNum,
			Float environmentNum, Float transportNum, Integer status,
			User author) {
		super();
		this.title = title;
		this.content = content;
		this.postTime = postTime;
		this.serviceNum = serviceNum;
		this.peopNum = peopNum;
		this.totalNum = totalNum;
		this.environmentNum = environmentNum;
		this.transportNum = transportNum;
		this.status = status;
		this.author = author;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Scenic getScenic() {
		return scenic;
	}
	public void setScenic(Scenic scenic) {
		this.scenic = scenic;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Float getServiceNum() {
		return serviceNum;
	}
	public void setServiceNum(Float serviceNum) {
		this.serviceNum = serviceNum;
	}
	public Float getPeopNum() {
		return peopNum;
	}
	public void setPeopNum(Float peopNum) {
		this.peopNum = peopNum;
	}
	public Float getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Float totalNum) {
		this.totalNum = totalNum;
	}
	public Float getEnvironmentNum() {
		return environmentNum;
	}
	public void setEnvironmentNum(Float environmentNum) {
		this.environmentNum = environmentNum;
	}
	public Float getTransportNum() {
		return transportNum;
	}
	public void setTransportNum(Float transportNum) {
		this.transportNum = transportNum;
	}
}