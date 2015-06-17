package com.tour.suse.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import zhu.jsonBean.NameIdByScenic;
import zhu.jsonBean.NoteJson;
import zhu.utils.TestImgType;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.tour.suse.base.BaseAction;
import com.tour.suse.entity.City;
import com.tour.suse.entity.Note;
import com.tour.suse.entity.NoteComment;
import com.tour.suse.entity.NoteReply;
import com.tour.suse.entity.Scenic;
import com.tour.suse.entity.ScenicClass;
import com.tour.suse.util.QueryHelper;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class NoteAction extends BaseAction<Note> {
	private String arrayId;
	private String noteImageContentType;
	private File noteImage;
	private String noteImageFileName;
	private List<City> citys;
	private String action;
	private String notesort="postTime";
	//搜索城市的条件
	private String cityArray;
	private Long cityId; 
	private boolean flag2;//投票
	private String delDescription;
	//
	private String keywords;
	private Long scenicId;
	private Integer notePageNum;
	private String queryString;
//	游记内容图片检验字段
	private String pictime;
	//根据scenicId获取note集合
	public String getAboutNoteByScenicId()
	{
		if(scenicId!=null)
		{
			Scenic aboutScenic = scenicService.getById(scenicId);
			ActionContext.getContext().put("aboutScenic",aboutScenic);
			System.out.println("aboutScenic:"+aboutScenic);
			new QueryHelper("SELECT new com.tour.suse.entity." +
					"Note(n.id, n.title,n.imageUrl,n.postTime) ",Note.class,"n")
			.addCondition(true,"n.status=1 AND n.scenic.id=?", scenicId)
			.addOrderProperty("postTime", false)
			.preparePageBean(noteService, 1, 10);
		}
		return "listall";
	}
	
	//ajax获取 景区相关游记
	public String getNoteByScenicId()
	{
		if(scenicId!=null)
		{
		System.out.println("收到条件:pagenum"+notePageNum+",scenicId"+scenicId);
		List<NoteJson> querList= new QueryHelper("SELECT new zhu.jsonBean." +
				"NoteJson(id, title,  imageUrl, content, postTime, author.username,author.id)", Note.class, "n")
		.addCondition(true, "n.status=1 AND  n.scenic.id=?", scenicId)
		.addOrderProperty("postTime", true)
		.preparePageBean2(noteService, notePageNum, 10);
		String queryJson = JSONObject.toJSONString(querList);
		System.out.println("相关游记2:"+queryJson);
		getOut().print("true"+queryJson);
		return null;
		}
		return null;
	}
	public String voteNote()
	{
		//创建cookie标志
		boolean ccookie=true;
		//首先用户未登录
		if(getUser()==null)
		{
			getOut().print("noLogin");
			return null;
		}
		//通过response获取cookie
		Cookie[] cookies= getRequest().getCookies();
		//遍历
		for(Cookie c: cookies)
		{
			if(c.getName().equals(getUser().getUsername()))
			{
				if(c.getValue().equals("1")){
					ccookie = false;
					getOut().print("noRight");
					return null;
				}
			}
		}
		if(ccookie){//创建cookie
			Cookie  cookie= new Cookie(getUser().getUsername(),"1");//默认是会话级别
			cookie.setMaxAge(30*60);//设置30分钟后过期 存入磁盘 先设置
			getResponse().addCookie(cookie);
		}
		
		model = noteService.getById(model.getId());
		if(flag2==true)
		{
			model.setGoodNum(model.getGoodNum()+1);
			noteService.update(model);
			getOut().print("true"+model.getGoodNum());
			return  null;
		}
		else
		{
			model.setBadNum(model.getBadNum()+1);
			noteService.update(model);
			getOut().print("true"+model.getBadNum());
			return  null;
		}
	}
	public String list() {
		new QueryHelper(Note.class, "n").addOrderProperty("n.postTime", false)
				.addCondition(true, "n.status=?", 1)
				.preparePageBean(noteService, pageNum, pageSize);
		System.out.println("游记列表！");
		return "list";
	}
	public String list2() {
		new QueryHelper(Note.class, "n").addOrderProperty("n.postTime", false)
				.addCondition(true, "n.status=?", 0)
				.preparePageBean(noteService, pageNum, pageSize);
		System.out.println("游记列表2！");
		return "list2";
	}
	//用户的游记列表
	public String userlist(){
		if(getUser()!=null)
		{
			
			new QueryHelper(Note.class, "n").addOrderProperty("postTime", false)
			.addCondition(true,"n.status=1 AND n.author=?", getUser())
			.preparePageBean(noteService, pageNum, 10);
			//推荐景区 默认先时间顺序
		}else{return "errorToLogin";}
		return "userlist";
	}
	//用户的游记列表ajax
	@SuppressWarnings("unchecked")
	public String userlistajax(){
		System.out.println("userlistajax:"+getUser().getUsername()+","+pageNum+","+cityArray);
		if(getUser()!=null && pageNum>0)
		{
			
			Long[] cityIds = null;
			//解析cityid
			if(cityArray!=null && !cityArray.trim().equals(""))
			{
				String[] strIds = cityArray.split(",");
				cityIds =new Long[strIds.length];
				for(int i=0;i<cityIds.length;i++)
				{
					cityIds[i] = Long.parseLong(strIds[i]);
				}
			}
			String SelectSQL ="SELECT new com.tour.suse.entity.Note(n.id,n.title,n.imageUrl,n.postTime)";
			List<Note> queryList =  new QueryHelper(SelectSQL,Note.class, "n")
			.addCondition(true,"n.status=1 AND n.author.id=?", getUser().getId())
			.addCondition(true, "n.scenic.city.id=?", cityIds)
			.addOrderProperty("postTime", false)
			.preparePageBean2(noteService, pageNum, 10);
			//queryList
			String JsonQuery = JSONObject.toJSONString(queryList);
			System.out.println("个人游记查询："+JsonQuery);
			getOut().print("true"+JsonQuery);
			return null;
		}else{return "errorToLogin";}
	}
	//游记详细
	public String detail() {
		//2.准备热门游记：格式 评价数+点击数大到小
		List<Note> hotNotes = noteService.getHotList();
		ActionContext.getContext().put("hotNotes", hotNotes);
		//1.准备热门景区:条件是按评价数+点击数从大到小获取
		
		System.out.println("modelid:"+model.getId());
		model = noteService.getById(model.getId());
		if(model.getTitle().length()>30)
		{model.setTitle(model.getTitle().substring(0,30)+"...");};
		model.setClickNum(model.getClickNum() + 1);
		noteService.update(model);
		//游记评论
		String selectSQL="SELECT new com.tour.suse.entity.NoteComment(nc.id,nc.content,nc.postTime" +
				",nc.author.username,nc.author.head,nc.noteReplies.size,nc.author.id) ";
		new QueryHelper(selectSQL,NoteComment.class, "nc")
		.addCondition(true,"nc.note.id=?", model.getId())
		.addOrderProperty("nc.postTime", true)
		.preparePageBean(noteCommentService, 1, 10);
		return "detail";
	}
	//游记搜索
	public String search() {
		
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
		
		
		System.out.println(notesort);
		String queSQL ="SELECT new com.tour.suse.entity.Note(n.id,n.title,n.imageUrl,substring(n.content,1,20)" +
		",n.postTime,n.clickNum,n.commentNum,n.author.username,n.scenic.city.name,n.author.head,n.author.id)";
		new QueryHelper(queSQL,Note.class, "n")
				.addCondition(true, "n.status=?",1)
				.addCondition(true, "n.title like ?", arr)
				.addOrderProperty("n."+notesort, false)
				.preparePageBean(noteService, 1, 10);
		return "search";
	}
	//三大条件搜索
	@SuppressWarnings("unchecked")
	public String searchData()
	{
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
		
		String queSQL ="SELECT new com.tour.suse.entity.Note(n.id,n.title,n.imageUrl,substring(n.content,1,20)" +
		",n.postTime,n.clickNum,n.commentNum,n.author.username,n.scenic.city.name,n.author.head,n.author.id)";
		try {
			System.out.println("pagenum:"+pageNum+"，景区查询传入字段："+queryString);
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
		//解析结束
	//按默认方式继续查询 ->更新时间由最新到最旧排列
	if (LevelName==null && LocationName==null && ClassId==null)
	{
		List<Note> query= new QueryHelper(queSQL, Note.class, "n")
		.addCondition(true, "n.status=?", new Integer(1))
		.addCondition(true, "n.title like ?", arr)
		.addOrderProperty("n."+notesort, false)//大到小
		.preparePageBean2(noteService, pageNum, 10);
		String JsonQuery = JSONObject.toJSONString(query);
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
				List<Note> query= new QueryHelper(true,queSQL,Note.class,ScenicClass.class,"n","sc")
			.addCondition(true, "(sc.id=? AND sc in elements(n.scenic.scenicClasses))", ClassId)
			.addCondition(true, "n.status=?", 1)
			.addCondition(true,"(n.scenic.level=?)", LevelName)
			.addCondition(true,"n.scenic.city.name=?",LocationName)
			.addCondition(true, "n.title like ?", arr)
			.addOrderProperty("n."+notesort, false)
			.preparePageBean2(noteService, pageNum, 10);
			String queryjson = JSONObject.toJSONString(query);
			System.out.println("条件查询1：ClassId:"+ClassId+",LevelName:"+LevelName+",LocationName:"+LocationName+queryjson);
			getOut().print("true"+queryjson);
			System.out.println("pageSize"+pageSize);
			return null;
			}catch(Exception e){
				e.printStackTrace();
			}
		//含景点级别，景点地理位置条件查询
		}else{
			List<Note> query= new QueryHelper(queSQL,Note.class,"n")
			.addCondition(true, "n.status=?", 1)
			.addCondition(true,"n.scenic.level=?",LevelName)
			.addCondition(true,"n.scenic.city.name=?", LocationName)
			.addCondition(true, "n.title like ?", arr)
			.addOrderProperty("n."+notesort, false)
			.preparePageBean2(noteService, pageNum, 10);
			//变为json格式
			String queryjson = JSONObject.toJSONString(query);
			System.out.println("条件查询："+",LevelName:"+LevelName+",LocationName:"+LocationName+queryjson);
			getOut().print("true"+queryjson);
			System.out.println("pageSize"+10);
			return null;
		}
	}//任何异常
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("查询字段querySring为null");
		//按默认方式继续查询 ->更新时间由最新到最旧排列
		List<Note> query= new QueryHelper(queSQL,Note.class,"n")
			.addCondition(true, "n.status=?", 1)
			.addCondition(true, "n.title like ?", arr)
			.addOrderProperty("n."+notesort, false)
			.preparePageBean2(noteService, pageNum, 10);
			String JsonQuery = JSONObject.toJSONString(query);
			System.out.println("发生异常，默认方式查询："+JsonQuery);
			getOut().print("true"+JsonQuery);
			return null;
	}
	return null;
	}
	
	//添加游记的界面
	public String addUI() {
		if(getUser()==null)
		{return "Login";}
		
		citys = cityService.findAll();
		return "addUI";
	}
	
	//游记城市对应景区  添加游记时对应的景区 需要做缓冲？ 数据多了
	public String cityToScenic()
	{
		try{
		List<NameIdByScenic> HotScenicJson = noteService.findAllAboutCity(cityId);
			System.out.println("cityId"+cityId);
			String json= JSONObject.toJSONString(HotScenicJson);
			System.out.println("json:"+json);
			getOut().print("true"+json);
		}catch(Exception e){e.printStackTrace();}
			return null;
	}
	//添加游记
public String add() {
	try{
		if(getUser()==null)
		{return "Login";}
		if(pictime==null)
		{return "errorToIndex";}
		if (noteImage != null) {
			if(filter.isContentKeyWords(model.getContent())){//判断是否有敏感词汇
				model.setContent(filter.getReplaceKeyWords(model.getContent()));//敏感词和谐
			}
			model.setAuthor(getUser());
			model.setPostTime(new Date());
			if(scenicId==null)
			{
				this.addFieldError("scenicId", "没有设置对应的景区");
				return "addUI";
			} else if(model.getTitle()==null)
			{
				this.addFieldError("title", "游记名不能为空");
				return "addUI";
			}
			Scenic scenic = scenicService.getById(scenicId);
			model.setScenic(scenic);//设置对应的景区 	
			//图片路径
			String realpath = ServletActionContext.getServletContext().getRealPath("/noteImages");
			System.out.println(realpath);
			File savedir = new File(realpath);
			//检验图片
				String reg=".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.PNG|.png)$";
				Pattern pattern= Pattern.compile(reg);
				Matcher matcher=pattern.matcher(noteImageFileName.toLowerCase());
				if(!matcher.find()){getOut().print("上传图片非法格式,请上传jpg,jpeg,gif,png类型");return null;};
				//检查图片大小
				if(noteImage.exists() && noteImage.isFile())
				{
					Long len = noteImage.length();
					if(len>2*1024*1024)
					{
						getOut().print("上传图片不能超过2M");
						return null;
					}
				}else{getOut().print("上传图片不存在或者不是文件");return null;}
				//检查是否为真正图片
				try {
					if("-1".equals(TestImgType.getImageType(null,noteImage)))
					{
						getOut().print("请不要攻击服务器1！");
						return null;
					}
				} catch (IOException e1) {
					getOut().print("请不要攻击服务器2！");
					e1.printStackTrace();
					return null;
				}
			//检验结束
			if(!savedir.exists()){savedir.mkdirs();}
			//设置图片根名字 公式 = 单个字符(原文件名)+随机四位数字
			int len = noteImageFileName.split("\\.").length;
			String newName = noteImageFileName.charAt(0)+getRandomFileName()+"."
			+noteImageFileName.split("\\.")[len-1];
			System.out.println("newName"+newName);
			File savefile= new File(savedir, newName);
			//存入磁盘
				FileUtils.copyFile(noteImage, savefile);
			//存入数据库
			newName= "noteImages/"+newName;
			model.setImageUrl(newName);
			//检查游记内容插入图片
			String key =pictime+getUser().getUsername();
			List<Timer> lisTimer= (List<Timer>) ActionContext.getContext().getSession().get(key);
			if(lisTimer!=null)
			if(lisTimer!=null && lisTimer.size()>0)
			{
				//遍历 取消图片删除定时器
				 len =lisTimer.size();
				for(int i=0;i<len;i++)
				{
					lisTimer.get(i).cancel();
					System.out.println("保存了游记取消定时器："+i);
				}
			}
			//设置对应的  游记
			noteService.save(model);
			return "add";
		}else{
			this.addFieldError("noteImage", "没有添加游记图片");
			return "addUI";
		}
	}catch(Exception e){
		e.printStackTrace();
		return "error";
	}
}
	
	public String editUI() {
		if(getUser()==null)
		{return "Login";}
		
		citys = cityService.findAll();
		model = noteService.getById(model.getId());
		scenic = model.getScenic();
		return "editUI";
	}

	//
	public String edit() throws Exception {
	try{
		if(getUser()==null)
		{return "Login";}
		//检查传入数据
		if(cityId==null || scenicId==null || model.getTitle()==null || model.getContent()==null)
		{
			this.addFieldError("error", "数据填写不完整");
			return "toeEditUI";
		}
		if(pictime==null)
		{return "error";}
		System.out.println("cityId:"+cityId+"scenicId:"+scenicId+",title"+model.getTitle()
				+",imag:"+model.getImageUrl()+",content"+model.getContent());
		//检查游记内容插入图片
		String key =pictime+getUser().getUsername();
		List<Timer> lisTimer= (List<Timer>) ActionContext.getContext().getSession().get(key);
		if(lisTimer!=null && lisTimer.size()>0)
		{
			//遍历 取消图片删除定时器
			int len =lisTimer.size();
			for(int i=0;i<len;i++)
			{
				lisTimer.get(i).cancel();
				System.out.println("保存了游记取消定时器："+i);
			}
		}
		if(filter.isContentKeyWords(model.getContent())){//判断是否有敏感词汇
			model.setContent(filter.getReplaceKeyWords(model.getContent()));//敏感词和谐
		}
		Note note = noteService.getById(model.getId());
		//标题 图片  城市 （放在景区） 景区  内容
		note.setTitle(model.getTitle());
		City city = cityService.getById(cityId);
		Scenic scenic = scenicService.getById(scenicId);
		scenic.setCity(city);
		note.setScenic(scenic);
		//跟新图片
		String regEx_img = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
		 Pattern p_image = Pattern.compile 
                 (regEx_img,Pattern.CASE_INSENSITIVE);   
		 Matcher  m_image = p_image.matcher(note.getContent()); 
		 String v1="";//全标签
		 String v2="";//路径
		 if(m_image.find()) 
		 {
			 System.out.println("匹配到："); 
			 v1 =  m_image.group();
			 v2 =  m_image.group(1);
		 }
		 //判断是否包含
		 if(!model.getContent().contains(v1))
		 {
			String[] a=  v2.substring(1,v2.length()).split("\\/");
			v2 = getRoot()+a[1]+"/"+a[2]+"/"+a[3];
			File file = new File(v2);
			if(file.exists())
			{
				System.out.println("删除了:"+file.delete());
			}
		 }
		note.setContent(model.getContent());
		note.setImageUrl(model.getImageUrl());
		noteService.update(note);
		//获取返回到详细页面 的数据
		String selectSQL="SELECT new com.tour.suse.entity.NoteComment(nc.id,nc.content,nc.postTime" +
		",nc.author.username,nc.author.head,nc.noteReplies.size) ";
		new QueryHelper(selectSQL,NoteComment.class, "nc")
		.addCondition(true,"nc.note.id=?", model.getId())
		.addOrderProperty("nc.postTime", true)
		.preparePageBean(noteCommentService, 1, 10);
		return "edit";
	}catch(Exception e){
		e.printStackTrace();
		return "error";
	}
	}
	//恢复
	public void doRecover()
	{
		try{
		if(model.getId()==null)
		{getOut().print(-1);}else{
			model = noteService.getById(model.getId());
			model.setStatus(1);
			noteService.update(model);
			getOut().print(1);
		}}
		catch(Exception e){e.printStackTrace();getOut().print(-1);}
	}
	//放入回收站 
	public void delete() {
		if(model.getId()!=null)
		try{
			model = noteService.getById(model.getId());
		 	//加一些参数
			 model.setStatus(0);
			 model.setDelUsername(getUser().getUsername());
			 noteService.update(model);
			 getOut().print(1);
		}catch(Exception e){e.printStackTrace();getOut().print(-1);}
		else{getOut().print(-1);}
	}
	//真正删除
	public void delete2() {
		try{
			model = noteService.getById(model.getId());
			if(model==null)
			{getOut().print("-1");}
			else if(model.getId()==null || model.getStatus()==null )
			{getOut().print("-1");}
			else if(model.getStatus()!=0)
			{getOut().print("-1");}
			else{
		//		删除 评论和回复
				 Set<NoteComment>  notecomms = model.getNoteComments();
					for(NoteComment nc: notecomms){
						Set<NoteReply> notereplies= nc.getNoteReplies();
							for(NoteReply nreply:notereplies)
							{
								noteReplyService.delete(nreply.getId());
							}
						noteCommentService.delete(nc.getId());
					}
					//删除对应的图片
					File indexfile = new File(getRoot()+model.getImageUrl());
					if(indexfile.exists())
					{
						System.out.println("删除index了:"+indexfile.delete());
					}
					//获取内容图片del
						String regEx_img = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
						 Pattern p_image = Pattern.compile 
				                 (regEx_img,Pattern.CASE_INSENSITIVE);   
						 Matcher  m_image = p_image.matcher(model.getContent()); 
						 String v1="";//全标签
						 String v2="";//路径
						 if(m_image.find()) 
						 {
							 System.out.println("匹配到："); 
							 v1 =  m_image.group();
							 v2 =  m_image.group(1);
						 }
							String[] a=  v2.substring(1,v2.length()).split("\\/");
							v2 = getRoot()+a[1]+"/"+a[2]+"/"+a[3];
							File file = new File(v2);
							if(file.exists())
							{
								System.out.println("删除了:"+file.delete());
							}
						 //
					noteService.delete(model.getId());
					getOut().print(1);
			}
		}catch(Exception e){e.printStackTrace();getOut().print(-1);}
	}
	//真正
	public void deleteList2() throws Exception {
		try{
			if(arrayId==null)
			{getOut().print(-1);}
			else{
				String[] array = arrayId.split(",");
				noteService.deleteList2(array);
				if (getOut() != null)
					getOut().print(1);
			}
		}catch(Exception e){e.printStackTrace();getOut().print(-1);}
	}
	
	public void deleteList() throws Exception {
		try{System.out.println("ids:"+arrayId);
			if(arrayId==null)
			{getOut().print(-1);}
			else{
				String[] array = arrayId.split(",");
				noteService.deleteList(array);
				if (getOut() != null)
					getOut().print(1);
			}
		}catch(Exception e){e.printStackTrace();getOut().print(-1);}
	}

	public File getNoteImage() {
		return noteImage;
	}

	public void setNoteImage(File noteImage) {
		this.noteImage = noteImage;
	}

	public String getNoteImageContentType() {
		return noteImageContentType;
	}

	public void setNoteImageContentType(String noteImageContentType) {
		this.noteImageContentType = noteImageContentType;
	}

	public String getNoteImageFileName() {
		return noteImageFileName;
	}

	public void setNoteImageFileName(String noteImageFileName) {
		this.noteImageFileName = noteImageFileName;
	}
	public List<City> getCitys() {
		return citys;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getNotesort() {
		return notesort;
	}

	public void setNotesort(String notesort) {
		this.notesort = notesort;
	}

	public String getCityArray() {
		return cityArray;
	}
	public void setCityArray(String cityArray) {
		this.cityArray = cityArray;
	}
	private String getRandomFileName() {
		//SimpleDateFormat sDateFormat;
		Random r = new Random();
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
		//sDateFormat = new SimpleDateFormat("yyyyMMdd"); // 时间格式化的格式
		return rannum+""; // 当前时间
	}
	public boolean isFlag2() {
		return flag2;
	}
	
	public String getDelDescription() {
		return delDescription;
	}

	public void setDelDescription(String delDescription) {
		this.delDescription = delDescription;
	}

	public void setCitys(List<City> citys) {
		this.citys = citys;
	}

	public void setFlag2(boolean flag2) {
		this.flag2 = flag2;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getArrayId() {
		return arrayId;
	}

	public void setArrayId(String arrayId) {
		this.arrayId = arrayId;
	}
	public Long getScenicId() {
		return scenicId;
	}
	public void setScenicId(Long scenicId) {
		this.scenicId = scenicId;
	}
	public Integer getNotePageNum() {
		return notePageNum;
	}
	public void setNotePageNum(Integer notePageNum) {
		this.notePageNum = notePageNum;
	}
	private Scenic scenic;

	public Scenic getScenic() {
		return scenic;
	}
	public void setScenic(Scenic scenic) {
		this.scenic = scenic;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}


	public String getPictime() {
		return pictime;
	}

	public void setPictime(String pictime) {
		this.pictime = pictime;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
}
