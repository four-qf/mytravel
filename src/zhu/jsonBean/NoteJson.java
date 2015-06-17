package zhu.jsonBean;

import java.util.Date;

import com.tour.suse.util.HtmlUtil;


public class NoteJson {
	private Long id;
	private String title;
	private String imageUrl;
	private String content;
	private Date postTime;
	private String username;
	private Long authorId;
	public NoteJson(){}
	public NoteJson(Long id, String title, String imageUrl, String content,
			Date postTime, String username,Long authorId) {
		this.id = id;
		this.title = title;
		this.imageUrl = imageUrl;
		String con = HtmlUtil.ConvertToText(content);
		if(con.length()<20)
		{con = con.substring(0, con.length());}
		else{con = con.substring(0, 20);}
		this.content = con;
		this.postTime = postTime;
		this.username = username;
		this.authorId =authorId; 
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
}
