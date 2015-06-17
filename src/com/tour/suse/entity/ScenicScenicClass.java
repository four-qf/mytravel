package com.tour.suse.entity;

import java.io.Serializable;

public class ScenicScenicClass implements Serializable{
	private Long id;
	private Scenic scenic;
	private ScenicClass scenciClass;
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
	public ScenicClass getScenciClass() {
		return scenciClass;
	}
	public void setScenciClass(ScenicClass scenciClass) {
		this.scenciClass = scenciClass;
	}

}
