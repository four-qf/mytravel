package zhu.jsonBean;

import java.util.Date;

public class TJJsON {
	private Long id;
	private Integer status;
	private Date postTime;
	private Long sId;
	private String sName;
	private Integer clickNum=0;
	
	public Integer getClickNum() {
		return clickNum;
	}
	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public TJJsON(Long id, Integer status, Long sId,String Sname,Integer clickNum) {
		this.id = id;
		this.status = status;
		this.sId = sId;
		this.sName = Sname;
		this.clickNum = clickNum;
	}
	public Long getsId() {
		return sId;
	}
	public void setsId(Long sId) {
		this.sId = sId;
	}
	public TJJsON() {
	}
	public TJJsON(Long id, Integer status, Date postTime) {
		this.id = id;
		this.status = status;
		this.postTime = postTime;
	}
	public TJJsON(Integer status,Date postTime)
	{
		this.status = status;
		this.postTime = postTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	
}
