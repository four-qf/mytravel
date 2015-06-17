package zhu.jsonBean;

import java.util.Date;

public class ScenicCommentJson {
	private Long id;
	private String authorName;
	private String content;
	private Date postTime;
	private Float serviceNum=0.0f;
	private Float peopNum=0.0f;
	private Float totalNum=0.0f;
	private Float environmentNum=0.0f;
	private Float transportNum=0.0f;
	private Integer status;
	private String head;
	private Long authorId;
	public ScenicCommentJson(){}
	public ScenicCommentJson(Long id,String head, String authorName, String content,
			Date postTime, Float serviceNum, Float peopNum, Float totalNum,
			Float environmentNum, Float transportNum, Integer status,Long authorId) {
		super();
		this.head = head;
		this.id = id;
		this.authorName = authorName;
		this.content = content;
		this.postTime = postTime;
		this.serviceNum = serviceNum;
		this.peopNum = peopNum;
		this.totalNum = totalNum;
		this.environmentNum = environmentNum;
		this.transportNum = transportNum;
		this.status = status;
		this.authorId =authorId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
}
