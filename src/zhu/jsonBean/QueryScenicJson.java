/*
 * 用于查询景区数据的封装
 * */
package zhu.jsonBean;

import com.tour.suse.util.HtmlUtil;

public class QueryScenicJson {
	private Long id;
	private String name;
	private String description;
	private Integer clickNum;
	private String imageUrl;
	public QueryScenicJson(Long id, String name){
		this.id = id;
		this.name = name;
	}
	
	public QueryScenicJson(Long id, String name, String description,
			Integer clickNum, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		String DESC=HtmlUtil.ConvertToText(description);
		System.out.println("yuan :"+description);
		if(DESC.length()<40)
		{DESC = DESC.substring(0, DESC.length());}
		else{DESC = DESC.substring(0, 40);}
		System.out.println("hou"+DESC);
		this.description = DESC;
		this.clickNum = clickNum;
		this.imageUrl = imageUrl;
	}

	public QueryScenicJson(Long id, String name, String description, Integer clickNum) {
		super();
		this.id = id;
		this.name = name;
		String DESC=HtmlUtil.ConvertToText(description);
		System.out.println("yuan :"+description);
		if(DESC.length()<40)
		{DESC = DESC.substring(0, DESC.length());}
		else{DESC = DESC.substring(0, 40);}
		System.out.println("hou"+DESC);
		this.description = DESC;
		this.clickNum = clickNum;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public QueryScenicJson(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
