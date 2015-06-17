package com.tour.suse.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import zhu.jsonBean.HotScenicJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.tour.suse.base.BaseAction;
import com.tour.suse.entity.City;
import com.tour.suse.entity.Scenic;
import com.tour.suse.entity.ScenicClass;
import com.tour.suse.entity.SubScenic;
import com.tour.suse.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class ScenicAction extends BaseAction<Scenic>{
	private Long[] scenicClassIds=null;//风景类别集合id
	private File scenicImage;
	private String scenicImageFileName;
	private String arrayId;//批量删除表示id
	private String keywords;
	//首页，查询关键字
	private String frontName;
	private String frontType;
	//景区查询条件标识
	private String queryString;
	private List<Scenic> qwe;
	int j=0;
	/**
	 * 景点详情页面
	 * @return
	 */
	public String detailScenic(){
		if(model.getId()==null)
		{return "noPage";}
		try{
			Long start = System.currentTimeMillis();
			model = scenicService.getById(model.getId());
			//图片地址字符串 分割放入List
			String imagUrls = model.getImageUrl();
			List<String> imgUrlList = java.util.Arrays.asList(imagUrls.split("\\;"));
			ActionContext.getContext().put("imgUrlList", imgUrlList);
			//通过城市 获取相关景区  按日期
			List<HotScenicJson> aboutScenicList = scenicService.findAllAboutCity0(model.getCity().getId());
			ActionContext.getContext().put("aboutScenicList", aboutScenicList);
			//设置点击数
			model.setClickNum(model.getClickNum()+1);
			model.setNotenum(model.getNotes().size());
			//更新
			scenicService.update(model);
			System.out.println("用时:"+String.valueOf((System.currentTimeMillis()-start)));
			return "detailScenic";
		}catch(Exception e){e.printStackTrace(); return "noPage";}
	}
	
	public String addUI(){
		//准备回显数据1
		prepareData2();
		return "saveUI";
	}
	

	public void json() throws Exception{
		System.out.println(JSON.parseObject(JSON.toJSONString(scenicService.getById(12l)), Scenic.class));
		getOut().print(JSON.toJSONString(scenicService.findAll()));
	}
	
	
	//
	public String add(){
		//验证数据的可靠性
		if(!v2()){
			//准备回显数据1
			prepareData2();
			return "saveUI";
		}else{
		//图片上传
			SubScenic subScenic= subScenicService.getByLastDateAndUser(getUser());
			System.out.println("img:"+subScenic.getImageUrl());
			model.setImageUrl(subScenic.getImageUrl());
		//3.设置用户
		if(getUser()!=null)
		{
			model.setAuthor(getUser());
		}
		//景区类别
		String[] scenicclass = subScenic.getSubScenicClass().split("\\,");
		Long[] scenicclassLong = new Long[scenicclass.length];
		for(int i=0;i<scenicclass.length;i++)
		{	
			scenicclassLong[i] = Long.parseLong(scenicclass[i]);
		}
		List<ScenicClass> scenicClassList =  scenicClassService.getByIds(scenicclassLong);
		model.setScenicClasses(new HashSet<ScenicClass>(scenicClassList));
		//设置创建时间
		model.setPostTime(new Date());
		scenicService.save(model);
				return "toList";
		}
	}
	//放入回收站
	public void delete2() {
		if(model.getId()!=null)
			try{
			scenicService.delete2(model.getId());
			getOut().print(1);
			}catch(Exception e)
			{ e.printStackTrace();getOut().print(-1);}
		else{getOut().print(-1);}
	}
	//放入回收站
	public void deleteList2() {
		if(arrayId!=null)
			try{
			String[] array = arrayId.split(",");
			System.out.println("放入回收站");
			scenicService.deleteList2(array);
			getOut().print(1);
			}catch(Exception e)
			{ e.printStackTrace();getOut().print(-1);}
		else{getOut().print(-1);}
	}
	//彻底删除
	public void delete() {
		if(model.getId()!=null)
			try{
			scenicService.delete(model.getId());
				if(model.getImageUrl()!=null){
					String [] arrs = model.getImageUrl().split(";");
					for(String a:arrs)
					{
						File file = new File(this.getRoot()+a);
						if(file!=null && file.exists())
						{file.delete();System.out.println("删除了图片");}
					}
				}
			getOut().print(1);
			}catch(Exception e)
			{ e.printStackTrace();getOut().print(-1);}
		else{getOut().print(-1);}
	}
	//彻底删除
	public void deleteList()  {
		if(arrayId!=null)
			try{
			String[] array = arrayId.split(",");
			scenicService.deleteList(array);
			getOut().print(1);
			}catch(Exception e){e.printStackTrace();getOut().print(-1);}
		else{getOut().print(-1);}	
	}
	//从回收站恢复
	public void reback()
	{
		if(model.getId()!=null)
			try{
				scenicService.rebackById(model.getId());
				getOut().print(1);
			}catch(Exception e){
				e.printStackTrace();getOut().print(-1);
			}
		else{getOut().print(-1);}
	}
	//在回收站的 status=0
	public String list2(){
		new QueryHelper(Scenic.class, "s")
		.addCondition(true, "s.status=?", 0)
		.addOrderProperty("postTime", false).
		preparePageBean(scenicService, pageNum, pageSize);
		return "list2";
	}
	//
	public String list(){
		new QueryHelper(Scenic.class, "s")
		.addCondition(true, "s.status=?", 1)
		.addOrderProperty("postTime", false).
		preparePageBean(scenicService, pageNum, pageSize);
		return "list";
	}
	/*
	 * 访问前端查询页面 准备数据
	 * */
	public String listByfront(){
		String[] arr=null;
		if(keywords!=null)
		{
			if(!keywords.trim().equals("")){
				System.out.println("key:"+keywords);
				String keywords1 = keywords.substring(0,keywords.length()-1);
				//拆分为单词
				arr = keywords1.split("\\s{1,}");
				for(int i=0;i<arr.length;i++)
				{arr[i]="%"+arr[i]+"%";}
				}else{keywords=null;}
		}
		
		String selectSQL = "SELECT new zhu.jsonBean.QueryScenicJson(s.id,s.name,s.description,s.clickNum,substring(s.imageUrl,1,20)) ";
		//准备栏目
		
		//1.准备热门景区:条件是按评价数+点击数从大到小获取
		List<HotScenicJson> hotScenicList = scenicService.findHotScenic();//clickNum/10
		if(hotScenicList.size()>0)
		System.out.println("hots:"+hotScenicList.size());
		ActionContext.getContext().put("hotScenicList", hotScenicList);
	//2.准备选择景点信息列表，按发布日期从最新到最老
		try {
			if(frontName!=null)
			frontName =new String (frontName.getBytes("iso-8859-1"),"utf-8");
			if(frontType!=null)
			frontType = new String(frontType.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
 
		if (!"scenicClass".equals(frontType))
		{
			if("level".equals(frontType))
				new QueryHelper(selectSQL,Scenic.class, "s") 
				.addCondition(true, "s.status=?", new Integer(1))
				.addCondition(true, "s.level=?", frontName)
				.addCondition(true, "s.name like ?", arr)
				.addOrderProperty("s.commentNum", false)
				.preparePageBean(scenicService, pageNum, pageSize);
			else if("city".equals(frontType))
				new QueryHelper(selectSQL,Scenic.class, "s") 
				.addCondition(true, "s.status=?", new Integer(1))
				.addCondition(true, "s.city.name=?", frontName)
				.addCondition(true, "s.name like ?", arr)
				.addOrderProperty("s.commentNum", false)
				.preparePageBean(scenicService, pageNum, pageSize);
			else
				new QueryHelper(selectSQL,Scenic.class, "s") 
				.addCondition(true, " s.status=? ", 1)
				.addCondition(true, "s.name like ?", arr)
				.addOrderProperty("s.commentNum", false)
				.preparePageBean(scenicService, pageNum, pageSize);
			System.out.println("不查类别");
		}
		else
		{
			Long[] scIds = null;
			ScenicClass sc=scenicClassService.getById(Long.parseLong(frontName));
			if (sc.getChildren()==null || sc.getChildren().size()==0)
			{
				scIds = new Long[]{Long.parseLong(frontName)};
			}else
			{//含有主类别
				Set<ScenicClass> scList= sc.getChildren();
				scIds = new Long[scList.size()];
				int i=0;
				for(ScenicClass sc2: scList)
				{
					scIds[i++] = new Long(sc2.getId());
				}
			}
			System.out.println("只查类别"+scIds+","+frontName);
			 new QueryHelper(selectSQL,Scenic.class, ScenicClass.class, "s", "sc")
			 .addCondition(true, "s.status=?", new Integer(1))
			.addCondition(true, "(sc.id=? AND  sc in elements(s.scenicClasses))", scIds)
			.addCondition(true, "s.name like ?", arr)
			.addOrderProperty("s.commentNum", false)
			.preparePageBean2(scenicService, pageNum, pageSize);
		}
			ActionContext.getContext().put("frontName",frontName);
		return "list";
	}
	/*
	 * 查询页面准备数据
	 * */
	@SuppressWarnings("unchecked")
	public String queryScenicByOther()throws Exception{
		
		String[] arr=null;
		if(keywords!=null)
		{
			if(!keywords.trim().equals("")){
			System.out.println("key:"+keywords);
			String keywords1 = keywords.substring(0,keywords.length()-1);
			//拆分为单词
			arr = keywords1.split("\\s{1,}");
			for(int i=0;i<arr.length;i++)
			{arr[i]="%"+arr[i]+"%";}
			}else{keywords=null;}
		}
		
		String SelectSQL = "SELECT DISTINCT new zhu.jsonBean.QueryScenicJson(s.id,s.name,s.description,s.clickNum,substring(s.imageUrl,1,20))";
		try {
			System.out.println("pagenum:"+pageNum);
			queryString = new String(queryString.getBytes("iso-8859-1"),"utf-8");
		System.out.println("景区查询传入字段："+queryString);
		String[] LevelName=null;//级别：世界级..
		String[] LocationName=null;//地理位置： 成都。。。
		Long[] ClassId=null;//类别id
		//解析查询传入字段  各数组以;为分隔符   例如："世界级,国家级;自贡,成都;1,2,3;"
		String[] tempStr= queryString.split("\\;");
		String [] ClassIdStr = null;//景区类别辅助字段
			for(String s:tempStr)
				System.out.println("每个条件输出:"+s);
			//把每个条件赋予各字段
			for(int j=0;j<tempStr.length;j++)
			{
				if(j==0)
				{
					if(tempStr[0]!=null )
						if(!"".equals(tempStr[0].trim()))
							LevelName = tempStr[0].split("\\,");
				}
				else if(j==1)
				{
					if(tempStr[1]!=null)
						if(!"".equals(tempStr[1].trim()))
						LocationName = tempStr[1].split("\\,");
				}else if(j==2)
				{
					if(tempStr[2]!=null)
						if(!"".equals(tempStr[2].trim()))
						ClassIdStr = tempStr[2].split("\\,");
							ClassId = new Long[ClassIdStr.length];
							for(int i=0;i<ClassId.length;i++)
							{
								ClassId[i]=Long.parseLong(ClassIdStr[i]);
							}
				}
			}
			//按默认方式继续查询 ->更新时间由最新到最旧排列
			if (LevelName==null && LocationName==null && ClassId==null)
			{
				List query= new QueryHelper(SelectSQL, Scenic.class, "s")
				.addCondition(true, "s.status=?", new Integer(1))
				.addCondition(true, "s.name like ?", arr)
				.addOrderProperty("s.commentNum", false)
				.preparePageBean2(scenicService, pageNum, pageSize);
				String JsonQuery = JSON.toJSONString(query);
				System.out.println("默认查询："+JsonQuery);
				getOut().print("true"+JsonQuery);
				return null;
			}
		//1.有景区级别条件
		else if(LevelName!=null || LocationName!=null || ClassId!=null)
		{  
			//含景点类别,景点级别，景点地理位置条件查询
			if(ClassId!=null )
			{
				try{
				List query = new QueryHelper(SelectSQL,Scenic.class, ScenicClass.class, "s", "sc")
				.addCondition(true, "s.status=?", new Integer(1))
				.addCondition(true, "(sc.id=? AND sc in elements(s.scenicClasses))", ClassId)
				.addCondition(true,"(s.level=?)", LevelName)
				.addCondition(true,"s.city.name=?",LocationName)
				.addCondition(true, "s.name like ?", arr)
				.addOrderProperty("s.commentNum", false)
				.preparePageBean2(scenicService, pageNum, pageSize);
				String queryjson = JSON.toJSONString(query);
				System.out.println("条件查询1：ClassId:"+ClassId+",LevelName:"+LevelName+",LocationName:"+LocationName+queryjson);
				getOut().print("true"+queryjson);
				System.out.println("pageSize"+pageSize);
				}catch(Exception e){
					e.printStackTrace();
				}
				return null;
			//含景点级别，景点地理位置条件查询
			}else{
				List query= new QueryHelper(SelectSQL,Scenic.class, "s")
				.addCondition(true, "s.status=?", new Integer(1))
				.addCondition(true,"s.level=?",LevelName)
				.addCondition(true,"s.city.name=?", LocationName)
				.addCondition(true, "s.name like ?", arr)
				.addOrderProperty("s.commentNum", false)
				.preparePageBean2(scenicService, pageNum, pageSize);
				//变为json格式
				String queryjson = JSON.toJSONString(query);
				System.out.println("条件查询2："+",LevelName:"+LevelName+",LocationName:"+LocationName+queryjson);
				getOut().print("true"+queryjson);
				System.out.println("pageSize"+pageSize);
				return null;
			}
		}//任何异常
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("查询字段querySring为null");
			//按默认方式继续查询 ->更新时间由最新到最旧排列
				List query= new QueryHelper(SelectSQL, Scenic.class, "s")
				.addCondition(true, "s.status=?", 1)
				.addCondition(true, "s.name like ?", arr)
				.addOrderProperty("s.commentNum", false)
				.preparePageBean2(scenicService, pageNum, pageSize);
				String JsonQuery = JSON.toJSONString(query);
				System.out.println("发生异常，默认方式查询："+JsonQuery);
				getOut().print("true"+JsonQuery);
				return null;
		}
		return null;
	}
	/*修改*/
	public String editUI(){
		//准备数据
		prepareData2();
		model = scenicService.getById(model.getId());
		//图片的url
		if(model.getImageUrl()!=null){
			List<String> imageUrlList =Arrays.asList(model.getImageUrl().split("\\;"));
			ActionContext.getContext().put("imageUrlList", imageUrlList);
		}
		//为scenicClassIds赋值
		if( model.getScenicClasses()!=null){
			scenicClassIds = new Long[model.getScenicClasses().size()];
			int index=0;
			for (ScenicClass sc: model.getScenicClasses())
			{
				scenicClassIds[index++] = sc.getId();
			}
		}
		return "saveUI";
	}
	
	public String edit()
	{
		//数据库获取对象
		Scenic temScneic =scenicService.getById(model.getId());
		//标题
		temScneic.setName(model.getName());
		//图片路径
		//图片类别
		//图片级别
		temScneic.setLevel(model.getLevel());
		//所属城市
		temScneic.setCity(model.getCity());
		temScneic.setPostTime(new Date());
		if(getUser()!=null)
		{
			temScneic.setAuthor(getUser());
		}
		//其余的自动封装
		//修改编辑器的p标签
		if(model.getDescription().startsWith("<p>"))
			temScneic.setDescription(model.getDescription().substring(3, model.getDescription().length()-4));
		if(model.getScenicLine().startsWith("<p>"))
			temScneic.setScenicLine(model.getScenicLine().substring(3, model.getScenicLine().length()-4));
		//更新数据
		scenicService.update(temScneic);
		return "toList";
	}
	
	public Long[] getScenicClassIds() {
		return scenicClassIds;
	}

	public void setScenicClassIds(Long[] scenicClassIds) {
		this.scenicClassIds = scenicClassIds;
	}
	


	

	
	private void prepareData1() {
		List<ScenicClass> scenicClassList = scenicClassService.findTopList();
		ActionContext.getContext().put("scenicClassList",scenicClassList);
		List<City> cityList = cityService.findAll();
		ActionContext.getContext().put("cityList",cityList);
	}
	private void prepareData2() {
		List<ScenicClass> scenicClassList = scenicClassService.findAll();
		List<ScenicClass> scenicClassNoChilList = new ArrayList<ScenicClass>();
		for (ScenicClass sc: scenicClassList)
		{
			if(sc.getChildren().size()==0)
				scenicClassNoChilList.add(sc);
		}
		ActionContext.getContext().put("scenicClassNoChilList",scenicClassNoChilList);
		List<City> cityList = cityService.findAll();
		ActionContext.getContext().put("cityList",cityList);
	}

	public String getArrayId() {
		return arrayId;
	}

	public void setArrayId(String arrayId) {
		this.arrayId = arrayId;
	}
	
	//action中方法的验证  自动调用
	public boolean v2() {//
		boolean isGo=true;
		//用户名
		if(this.model.getCity().getId()==null || "".equals(this.model.getCity().getId())){
			isGo=false;
			addFieldError("city", "景区所在城市不能为空");
		}
		
		if(this.model.getName()==null || "".equals(this.model.getName())){
			isGo=false;
			addFieldError("name", "景区主题不能为空");
		}else if(scenicService.findByName(this.model.getName())!=null)//需要从数据库中查找注册名
		{
			System.out.println("数据库的："+scenicService.findByName(this.model.getName())+"传过来的"+model.getName());
			isGo=false;
			addFieldError("name", "景区名名已被注册");
		}
		
		if(this.model.getCity().getId()==null || "".equals(this.model.getCity().getId())){
			isGo=false;
			addFieldError("city", "景区所选城市不能为空");
		}
		
		if(this.model.getLevel()==null || "".equals(this.model.getLevel().trim())){
			isGo=false;
			addFieldError("level", "景区级别不能为空");
		}
		
//		if(scenicClassIds==null || "".equals(scenicClassIds)){
//			isGo=false;
//			addFieldError("scenicClass", "景区类别不能为空");
//		}
		
		if(this.model.getDescription()==null || "".equals(this.model.getDescription())){
			isGo=false;
			addFieldError("description", "景区内容不能为空");
		}
		return isGo;
	}

	
	
	public String getFrontName() {
		return frontName;
	}
	public void setFrontName(String frontName) {
		this.frontName = frontName;
	}
	public String getFrontType() {
		return frontType;
	}
	public void setFrontType(String frontType) {
		this.frontType = frontType;
	}

	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	//图片删除
	private String cancelImgUrl;
	public String cancelImg()
	{
		System.out.println("图片删除来了");
		PrintWriter out= getOut();
		if(cancelImgUrl!=null){
			if(!cancelImgUrl.trim().equals(""))
			{
				try{
				//有地址
				//1.先清除数据库记录
					
						Scenic temScenic = scenicService.getById(model.getId());
							//1).解析url 并清除
						cancelImgUrl = "images"+cancelImgUrl+";";
						if(temScenic.getImageUrl().contains(cancelImgUrl))
						{
							temScenic.setImageUrl(temScenic.getImageUrl().replace(cancelImgUrl,""));
							//如果有清除记录则 更新数据库
							scenicService.update(temScenic);
							//继续清除文件
							String realCancelImgUrl = ServletActionContext.getRequest().getRealPath("/")+cancelImgUrl.replace("/", "\\").replace(";", "");
							System.out.println(realCancelImgUrl);
							File cancelFile = new File(realCancelImgUrl);
							if(cancelFile.exists())
							{
								System.out.println("删除："+cancelFile.delete());
							}
							out.print("success");
							//放入 actionContext
							ActionContext.getContext().getSession().put("UpdateImgUrl", temScenic.getImageUrl());
							System.out.println("图片删除成功！");
							return null;
						}else
							return null;
						}
					catch(Exception e){e.printStackTrace();}
			}
			else{
				return null;
			}
		}
		else{
			return null;
		}
		return null;
	}
	//图片更新
	public String UpdateSingleImg()
	{
		try {
		System.out.println(cancelImgUrl+":id"+model.getId()+":scenicImageFileName"+scenicImageFileName);
		//先删除原图
		String realCancelImgUrl = ServletActionContext.getRequest().getRealPath("/")+"images\\"+cancelImgUrl;
		System.out.println("最终删除路径"+realCancelImgUrl);
		File cancelFile = new File(realCancelImgUrl);
		if(cancelFile.exists())
		{
			System.out.println("删除："+cancelFile.delete());
		}
		//再上传
		String realpath = ServletActionContext.getServletContext().getRealPath("/images");
		System.out.println(realpath);
		String NewNameImg=null;
		if(scenicImage!=null){
			NewNameImg = getRandomFileName()+"."+scenicImageFileName.split("\\.")[scenicImageFileName.split("\\.").length -1];
			System.out.println("扩展名是："+scenicImageFileName.split("\\.")[scenicImageFileName.split("\\.").length -1]);
			File savefile = new File(new File(realpath), NewNameImg);
			if(!savefile.getParentFile().exists()) savefile.getParentFile().mkdirs();
			try {
				FileUtils.copyFile(scenicImage, savefile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("上传suc");
		}
		//再替换数据库数据
			Scenic temScenic = scenicService.getById(model.getId());
			System.out.println("改前地址："+temScenic.getImageUrl());
			temScenic.setImageUrl
			(temScenic.getImageUrl().
					replace(cancelImgUrl, NewNameImg));
			System.out.println("改后地址："+temScenic.getImageUrl());
			scenicService.update(temScenic);
			ActionContext.getContext().getSession().put("UpdateImgUrl", temScenic.getImageUrl());
			getOut().print("success;"+NewNameImg);
			return null;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	private String replaceNum;
	
	public String getReplaceNum() {
		return replaceNum;
	}
	public void setReplaceNum(String replaceNum) {
		this.replaceNum = replaceNum;
	}
	public String getCancelImgUrl() {
		return cancelImgUrl;
	}
	public void setCancelImgUrl(String cancelImgUrl) {
		this.cancelImgUrl = cancelImgUrl;
	}
	/**
	 *  生成随机文件名：当前年月日时分秒+五位随机数
	 * @return
	 */
	private String getRandomFileName() {
		//SimpleDateFormat sDateFormat;
		Random r = new Random();
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
		//sDateFormat = new SimpleDateFormat("yyyyMMdd"); // 时间格式化的格式
		return rannum+""; // 当前时间
	}
	public File getScenicImage() {
		return scenicImage;
	}
	public void setScenicImage(File scenicImage) {
		this.scenicImage = scenicImage;
	}
	public String getScenicImageFileName() {
		return scenicImageFileName;
	}
	public void setScenicImageFileName(String scenicImageFileName) {
		this.scenicImageFileName = scenicImageFileName;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
