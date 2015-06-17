package com.tour.suse.entity;

import java.io.File;

public class NoteFileUp {
	private String err = "";
	private String msg;              //返回信息
	private File filedata;           //上传文件
	private String filedataFileName; //文件名
	public String getErr() {
		return err;
	}
	public void setErr(String err) {
		this.err = err;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public File getFiledata() {
		return filedata;
	}
	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}
	public String getFiledataFileName() {
		return filedataFileName;
	}
	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}
}
