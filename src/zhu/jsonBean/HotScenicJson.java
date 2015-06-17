/*
 * 用于热门景区封装数据
 * */
package zhu.jsonBean;

import com.tour.suse.util.HtmlUtil;

public class HotScenicJson {
	private Long id;
	private String imageUrl;
	private String name;
	private String description;
	
	public HotScenicJson(){}
	public HotScenicJson(Long id,String name, String description,String imageUrl) {
		this.id = id;
		this.name = name.length()>8?name.substring(0,6)+"...":name;
		String DESC=HtmlUtil.ConvertToText(description);
		if(DESC.length()<20)
		{DESC = DESC.substring(0, DESC.length());}
		else{DESC = DESC.substring(0, 20);}
		this.description = DESC;
		this.imageUrl = imageUrl;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
