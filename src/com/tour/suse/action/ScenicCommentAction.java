package com.tour.suse.action;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import zhu.jsonBean.MinMaxDate;
import zhu.jsonBean.ScenicCommentJson;
import zhu.jsonBean.TJJsON;
import zhu.jsonBean.TJJsON2;
import zhu.utils.CompareSort;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.tour.suse.base.BaseAction;
import com.tour.suse.entity.Scenic;
import com.tour.suse.entity.ScenicComment;
import com.tour.suse.util.CreateImage;
import com.tour.suse.util.QueryHelper;
@Controller
@Scope("prototype")
public class ScenicCommentAction extends BaseAction<ScenicComment>{
	private Long scenicId;
	private Long startYear;
	private Long endYear;
	//验证码
	private InputStream imageStream;
	private String sccode;
	private String time;//验证码
	/*
	 * 评论验证码
	 * */
	public String code() {
		// 产生验证码图片流对象
		 imageStream = CreateImage.getImageAsInputStream();
		// 获取随机字符串
		String securityCode = CreateImage.actionUsedStr;
		ActionContext.getContext().getSession().put("sccode", securityCode);
		return "code";
	}
	/**
	 * 评论删除 通过id
	 */
	public String delete()
	{
		try{
		System.out.println("sc.id"+model.getId());
		scenicCommentService.delete(model.getId());
		getOut().print("true");
		return null;
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
//	/**
//	 * 景区评论列表 通过景区id
//	 */
	public String getListBySceniId()
	{
		new QueryHelper(ScenicComment.class, "sc")
		.addCondition(true, "sc.scenic.id=?", scenicId)
		.addOrderProperty("postTime", true)
		.preparePageBean(scenicCommentService, pageNum, pageSize);
		return "list";
	}
	/**
	 * 景区评论列表 通过景区id 第二次获取
	 */
	@SuppressWarnings("unchecked")
	public void getLsBysId()
	{
		try{
			List<ScenicCommentJson> query=new QueryHelper("SELECT new zhu.jsonBean.ScenicCommentJson(sc.id,sc.author.head,sc.author.username, sc.content,sc.postTime,sc.serviceNum, sc.peopNum, sc.totalNum," +
				"sc.environmentNum, sc.transportNum, sc.status,sc.author.id)"
		,ScenicComment.class, "sc")
		.addCondition(true, "sc.scenic.id=?", scenicId)
		.addOrderProperty("postTime", true)
		.preparePageBean2(scenicCommentService, pageNum, pageSize);
			if(query.size()==0)
			{
				getOut().print("0");//没有数据
				System.out.println("0条评论");
				return;
			}else{
				String JsonQuery = JSON.toJSONString(query);
				System.out.println("评论查询第几"+pageNum+"页："+JsonQuery);
				getJsonOut().print(JsonQuery);
				return;
			}
		}catch(Exception e){
			e.printStackTrace();
			getOut().print("-1");
			return;
		}
	}

	/**
	 * 
	 * 用户评论景区
	 * @return
	 */
	public void add(){
	  try{
		 String codeV =  (String) ActionContext.getContext().getSession().get("sccode");
		 if(codeV==null)
		 {
			 getOut().print("nocode");
			 return ;
		 }else if(!codeV.equalsIgnoreCase(sccode))
		 {
			 getOut().print("nocode");
			 return ;
		 }
		if (getUser()==null)
		{getOut().print("noLogin");return ;}
		//设置关联用户
		model.setAuthor(getUser());
		if (scenicId!=null)
		{
			
			//设置关联景区
			model.setScenic(scenicService.getById(scenicId));
			//设置评论时间
			model.setPostTime(new Date());
			//设置评论状态
			//更新景区评论
			Scenic updateScenic = model.getScenic();
			//景区有评论分数
			if(2==model.getStatus() || 3==model.getStatus())
			{
				//景区有评分的评论数
				 updateScenic.setCommentNum( updateScenic.getCommentNum()+1);
				//景区真实的评论数(含不评分的)
				updateScenic.setRealCommentNum(updateScenic.getRealCommentNum()+1);
				System.out.println("景区评分："+model.getEnvironmentNum()+model.getPeopNum()+model.getServiceNum()+model.getTransportNum()+model.getTotalNum());
				if(updateScenic.getCommentNum()==1)//第一次
				{
					 updateScenic.setTotalScore(model.getTotalNum());
				 updateScenic.setEnvironmentScore(model.getEnvironmentNum());
				 updateScenic.setTransportScore(model.getTransportNum());
				 updateScenic.setServiceScore(model.getServiceNum());
				 updateScenic.setPeopleScore(model.getPeopNum());
				}else{
				 updateScenic.setTotalScore(
						 ( updateScenic.getTotalScore() *( updateScenic.getCommentNum()-1) +model.getTotalNum())
						/ updateScenic.getCommentNum());
				
				 updateScenic.setEnvironmentScore(
						 ( updateScenic.getEnvironmentScore()*( updateScenic.getCommentNum()-1) +model.getEnvironmentNum())
						/ updateScenic.getCommentNum());
				 
				 updateScenic.setTransportScore(
						 ( updateScenic.getTransportScore() *( updateScenic.getCommentNum()-1) +model.getTransportNum())
						/ updateScenic.getCommentNum());
				 
				 updateScenic.setServiceScore(
						 (updateScenic.getServiceScore() *(updateScenic.getCommentNum()-1)+model.getTotalNum())
							/ updateScenic.getCommentNum());
				
				 updateScenic.setPeopleScore(
						 	( updateScenic.getPeopleScore()*(updateScenic.getCommentNum()-1) + model.getTotalNum())
						 	/ updateScenic.getCommentNum());
				}
			}else
			{//景区真实的评论数
				System.out.println("real:"+updateScenic.getRealCommentNum());
				updateScenic.setRealCommentNum(updateScenic.getRealCommentNum()+1);
			}
			System.out.println("save:");
			//保存入数据库
			scenicCommentService.save(model);
			System.out.println("-------------------------");
			scenicService.update(updateScenic);
			getOut().print(1);
			return ;
		}else{getOut().print(-1); return ;}
	  }catch(Exception e){
		  e.printStackTrace();
		  getOut().print(-1); return;
	  }
	}
	
	//通过applicatiopn域获取城市
	public String tjGetCity()
	{
		
		return "tjIndex";
	}
	//统计 通过tkTime 和scenicId
	@SuppressWarnings("deprecation")
	public String tjData1()
	{
		System.out.println("查询景区id为："+scenicId+"查询起始日期"+startYear);
		if(scenicId==null)
		{return null;}
		try{
			Date statrdate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(startYear)+"-00-00");//startYear
			Date enddate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(startYear+1)+"-00-00");
			System.out.println("s:"+statrdate.getYear()+","+statrdate.toGMTString()+"end:"+enddate.getYear()+","+enddate);
		List<TJJsON> TJJList= scenicCommentService.getListByTimeAndScenicId(statrdate,enddate,scenicId);
		//按status分类 0 1 2 3 
		List<Date> list0 = new ArrayList<Date>();
		List<Date> list1 = new ArrayList<Date>();
		List<Date> list2 = new ArrayList<Date>();
		List<Date> list3 = new ArrayList<Date>();
		for (TJJsON tJJsON:TJJList)
		{
			if(tJJsON.getStatus()==0)
			{list0.add(tJJsON.getPostTime());}
			else if(tJJsON.getStatus()==1)
			{
				list1.add(tJJsON.getPostTime());
			}else if(tJJsON.getStatus()==2)
			{
				list2.add(tJJsON.getPostTime());
			}else if(tJJsON.getStatus()==3)
			{
				list3.add(tJJsON.getPostTime());
			}
		}
		//解析没个stauts为 一月：20,二月：20
//十二个月 对象 status 0开始
		TJJsON2 tJJsON20= new TJJsON2("一月",0);   
		TJJsON2 tJJsON21=  new TJJsON2("二月",0);  
		TJJsON2 tJJsON22=  new TJJsON2("三月",0);  
		TJJsON2 tJJsON23=  new TJJsON2("四月",0);  
		TJJsON2 tJJsON24=  new TJJsON2("五月",0);  
		TJJsON2 tJJsON25=  new TJJsON2("六月",0);  
		TJJsON2 tJJsON26=  new TJJsON2("七月",0);  
		TJJsON2 tJJsON27=  new TJJsON2("八月",0);  
		TJJsON2 tJJsON28=  new TJJsON2("九月",0);  
		TJJsON2 tJJsON29=  new TJJsON2("十月",0);  
		TJJsON2 tJJsON210=new TJJsON2("十一月",0);
		TJJsON2 tJJsON211= new TJJsON2("十二月",0);
		//再依次遍历出 不同月份 并且统计一个月的次数
		List<TJJsON2> jsonobj0 = new ArrayList<TJJsON2>();
		for(Date date:list0)
		{	
			switch(date.getMonth()){
			case 0:tJJsON20.setNum(tJJsON20.getNum()+1);break;
			case 1:tJJsON21.setNum(tJJsON21.getNum()+1);break;
			case 2:tJJsON22.setNum(tJJsON22.getNum()+1);break;
			case 3:tJJsON23.setNum(tJJsON23.getNum()+1);break;
			case 4:tJJsON24.setNum(tJJsON24.getNum()+1);break;
			case 5:tJJsON25.setNum(tJJsON25.getNum()+1);break;
			case 6:tJJsON26.setNum(tJJsON26.getNum()+1);break;
			case 7:tJJsON27.setNum(tJJsON27.getNum()+1);break;
			case 8:tJJsON28.setNum(tJJsON28.getNum()+1);break;
			case 9:tJJsON29.setNum(tJJsON29.getNum()+1);break;
			case 10:tJJsON210.setNum(tJJsON210.getNum()+1);break;
			case 11:tJJsON211.setNum(tJJsON211.getNum()+1);break;
			}
		}
		//装入数据
		jsonobj0.add(tJJsON20);
		jsonobj0.add(tJJsON21);
		jsonobj0.add(tJJsON22);
		jsonobj0.add(tJJsON23);
		jsonobj0.add(tJJsON24);
		jsonobj0.add(tJJsON25);
		jsonobj0.add(tJJsON26);
		jsonobj0.add(tJJsON27);
		jsonobj0.add(tJJsON28);
		jsonobj0.add(tJJsON29);
		jsonobj0.add(tJJsON210);
		jsonobj0.add(tJJsON211);
		
//重置数据 遍历 status 1开始
		tJJsON20= new TJJsON2("一月",0);  
		 tJJsON21= new TJJsON2("二月",0); 
		 tJJsON22= new TJJsON2("三月",0); 
		 tJJsON23= new TJJsON2("四月",0); 
		 tJJsON24= new TJJsON2("五月",0); 
		 tJJsON25= new TJJsON2("六月",0); 
		 tJJsON26= new TJJsON2("七月",0); 
		 tJJsON27= new TJJsON2("八月",0); 
		 tJJsON28= new TJJsON2("九月",0); 
		 tJJsON29= new TJJsON2("十月",0); 
		 tJJsON210= new TJJsON2("十一月",0);
		 tJJsON211= new TJJsON2("十二月",0);
		List<TJJsON2> jsonobj1 = new ArrayList<TJJsON2>();
		for(Date date:list1)
		{	
			switch(date.getMonth()){
			case 0:tJJsON20.setNum(tJJsON20.getNum()+1);break;
			case 1:tJJsON21.setNum(tJJsON21.getNum()+1);break;
			case 2:tJJsON22.setNum(tJJsON22.getNum()+1);break;
			case 3:tJJsON23.setNum(tJJsON23.getNum()+1);break;
			case 4:tJJsON24.setNum(tJJsON24.getNum()+1);break;
			case 5:tJJsON25.setNum(tJJsON25.getNum()+1);break;
			case 6:tJJsON26.setNum(tJJsON26.getNum()+1);break;
			case 7:tJJsON27.setNum(tJJsON27.getNum()+1);break;
			case 8:tJJsON28.setNum(tJJsON28.getNum()+1);break;
			case 9:tJJsON29.setNum(tJJsON29.getNum()+1);break;
			case 10:tJJsON210.setNum(tJJsON210.getNum()+1);break;
			case 11:tJJsON211.setNum(tJJsON211.getNum()+1);break;
			}
		}
		//循环结束 装入数据
		jsonobj1.add(tJJsON20);
		jsonobj1.add(tJJsON21);
		jsonobj1.add(tJJsON22);
		jsonobj1.add(tJJsON23);
		jsonobj1.add(tJJsON24);
		jsonobj1.add(tJJsON25);
		jsonobj1.add(tJJsON26);
		jsonobj1.add(tJJsON27);
		jsonobj1.add(tJJsON28);
		jsonobj1.add(tJJsON29);
		jsonobj1.add(tJJsON210);
		jsonobj1.add(tJJsON211);
		
//重置数据 遍历 status 2开始
		tJJsON20= new TJJsON2("一月",0);  
		 tJJsON21= new TJJsON2("二月",0); 
		 tJJsON22= new TJJsON2("三月",0); 
		 tJJsON23= new TJJsON2("四月",0); 
		 tJJsON24= new TJJsON2("五月",0); 
		 tJJsON25= new TJJsON2("六月",0); 
		 tJJsON26= new TJJsON2("七月",0); 
		 tJJsON27= new TJJsON2("八月",0); 
		 tJJsON28= new TJJsON2("九月",0); 
		 tJJsON29= new TJJsON2("十月",0); 
		 tJJsON210= new TJJsON2("十一月",0);
		 tJJsON211= new TJJsON2("十二月",0);
		List<TJJsON2> jsonobj2 = new ArrayList<TJJsON2>();
		for(Date date:list2)
		{	
			switch(date.getMonth()){
			case 0:tJJsON20.setNum(tJJsON20.getNum()+1);break;
			case 1:tJJsON21.setNum(tJJsON21.getNum()+1);break;
			case 2:tJJsON22.setNum(tJJsON22.getNum()+1);break;
			case 3:tJJsON23.setNum(tJJsON23.getNum()+1);break;
			case 4:tJJsON24.setNum(tJJsON24.getNum()+1);break;
			case 5:tJJsON25.setNum(tJJsON25.getNum()+1);break;
			case 6:tJJsON26.setNum(tJJsON26.getNum()+1);break;
			case 7:tJJsON27.setNum(tJJsON27.getNum()+1);break;
			case 8:tJJsON28.setNum(tJJsON28.getNum()+1);break;
			case 9:tJJsON29.setNum(tJJsON29.getNum()+1);break;
			case 10:tJJsON210.setNum(tJJsON210.getNum()+1);break;
			case 11:tJJsON211.setNum(tJJsON211.getNum()+1);break;
			}
		}
		//循环结束 装入数据
		jsonobj2.add(tJJsON20);
		jsonobj2.add(tJJsON21);
		jsonobj2.add(tJJsON22);
		jsonobj2.add(tJJsON23);
		jsonobj2.add(tJJsON24);
		jsonobj2.add(tJJsON25);
		jsonobj2.add(tJJsON26);
		jsonobj2.add(tJJsON27);
		jsonobj2.add(tJJsON28);
		jsonobj2.add(tJJsON29);
		jsonobj2.add(tJJsON210);
		jsonobj2.add(tJJsON211);
		
		
//重置数据 遍历 status 3开始
		 tJJsON20= new TJJsON2("一月",0);  
		 tJJsON21= new TJJsON2("二月",0); 
		 tJJsON22= new TJJsON2("三月",0); 
		 tJJsON23= new TJJsON2("四月",0); 
		 tJJsON24= new TJJsON2("五月",0); 
		 tJJsON25= new TJJsON2("六月",0); 
		 tJJsON26= new TJJsON2("七月",0); 
		 tJJsON27= new TJJsON2("八月",0); 
		 tJJsON28= new TJJsON2("九月",0); 
		 tJJsON29= new TJJsON2("十月",0); 
		 tJJsON210= new TJJsON2("十一月",0);
		 tJJsON211= new TJJsON2("十二月",0);
		List<TJJsON2> jsonobj3 = new ArrayList<TJJsON2>();
		for(Date date:list3)
		{	
			switch(date.getMonth()){
			case 0:tJJsON20.setNum(tJJsON20.getNum()+1);break;
			case 1:tJJsON21.setNum(tJJsON21.getNum()+1);break;
			case 2:tJJsON22.setNum(tJJsON22.getNum()+1);break;
			case 3:tJJsON23.setNum(tJJsON23.getNum()+1);break;
			case 4:tJJsON24.setNum(tJJsON24.getNum()+1);break;
			case 5:tJJsON25.setNum(tJJsON25.getNum()+1);break;
			case 6:tJJsON26.setNum(tJJsON26.getNum()+1);break;
			case 7:tJJsON27.setNum(tJJsON27.getNum()+1);break;
			case 8:tJJsON28.setNum(tJJsON28.getNum()+1);break;
			case 9:tJJsON29.setNum(tJJsON29.getNum()+1);break;
			case 10:tJJsON210.setNum(tJJsON210.getNum()+1);break;
			case 11:tJJsON211.setNum(tJJsON211.getNum()+1);break;
			}
		}
		//循环结束 装入数据
		jsonobj3.add(tJJsON20);
		jsonobj3.add(tJJsON21);
		jsonobj3.add(tJJsON22);
		jsonobj3.add(tJJsON23);
		jsonobj3.add(tJJsON24);
		jsonobj3.add(tJJsON25);
		jsonobj3.add(tJJsON26);
		jsonobj3.add(tJJsON27);
		jsonobj3.add(tJJsON28);
		jsonobj3.add(tJJsON29);
		jsonobj3.add(tJJsON210);
		jsonobj3.add(tJJsON211);
//每个status的月份 数量封装完毕  开始装到json		
JSONObject node = new JSONObject();
	node.put("st0", jsonobj0);
	node.put("st1", jsonobj1);
	node.put("st2", jsonobj2);
	node.put("st3", jsonobj3);
	System.out.println(node);
		System.out.println("评论数"+TJJList.size());
		//发送到前端
		
		getJsonOut().print(node.toJSONString());
		return null;
		}catch(Exception e){e.printStackTrace();getOut().print("false");return null;}
	}

	public Long getStartYear() {
		return startYear;
	}

	public void setStartYear(Long startYear) {
		this.startYear = startYear;
	}
	
	//统计 通过startYear 和cityId
	@SuppressWarnings("deprecation")
	public String tjData2()
	{
		System.out.println("查询城市id为："+cityId+"查询起始日期"+startYear+"截止日期:"+endYear);
		try{
		if(cityId==null)
		{
			cityId=0l;
		}else{
			ActionContext.getContext().put("cityId", cityId);
		}
		if(startYear==null)
		{
			Calendar  cal= Calendar.getInstance();
			startYear=Long.parseLong(String.valueOf(cal.get(Calendar.YEAR)));
		}if(endYear==null)
		{
			endYear=startYear+1;
		}else{
			ActionContext.getContext().put("startYear", startYear);
		}
		System.out.println("查询城市id为："+cityId+"查询起始日期"+startYear);
			Date statrdate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(startYear)+"-00-00");//startYear
			Date enddate = new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(endYear)+"-00-00");
			System.out.println("s:"+statrdate.getYear()+","+statrdate.toGMTString()+"end:"+enddate.getYear()+","+enddate);
			//单个城市统计
			List<TJJsON> TJJList= scenicCommentService.getListByTimeAndCityId(statrdate,enddate,cityId);
			System.out.println("list:"+TJJList.toString());
			//先按景区分类
			Map<String,Integer> map1= new HashMap<String,Integer>();
			Map<String,Map<String,Integer>> map2= new HashMap<String,Map<String,Integer>>();
		for (TJJsON tJJsON:TJJList)//计算四太值
		{
			if(!map2.containsKey(tJJsON.getsName()))
			{
				map1 = new HashMap<String,Integer>();
				map1.put("class"+String.valueOf(tJJsON.getStatus()), 1);
				map2.put(tJJsON.getsName(), map1);
			}else
			{
				map1 = map2.get(tJJsON.getsName());//存在 先取出
				if(!map1.containsKey("class"+String.valueOf(tJJsON.getStatus())))//不存在status 则创建
				{
					map1.put("class"+String.valueOf(tJJsON.getStatus()), 1);
				}else 
				{
					String objstr= "class"+String.valueOf(tJJsON.getStatus());
					Integer objnum =map1.get("class"+String.valueOf(tJJsON.getStatus()))+1;
					map1.put(objstr, objnum);
				}
				map2.put(tJJsON.getsName(), map1);//修改后 再放入 只放一份
			}
		}
		//设置浏览数
		map1 = new HashMap<String,Integer>();
		for (TJJsON tJJsON:TJJList)
		{
				map1.put(tJJsON.getsName(), tJJsON.getClickNum());
		}
		//算法：没去过的x0.095，打算去的x0.3，已去过x0.2，打算再去x0.4， 浏览量x0.05
		//输出格式 {["景区":"景区1","百分比":"20%"]}
		if(NO_GO_PER==null)
		{
			NO_GO_PER=9.5f;
		}
		if(IN_GO_PER==null)
			IN_GO_PER=30f;
		if(AL_GO_PER==null)
			AL_GO_PER=20f;
		if(ALIN_GO_PER==null)
			ALIN_GO_PER=40f;
		if(CLICK_PER==null)
			CLICK_PER=0.5f;
		Float totalNum=0f;//计算总数
		Map<String,Float> map11= new HashMap<String,Float>();
		Set<Entry<String, Map<String, Integer>>> entry= map2.entrySet();
		for(Entry<String, Map<String, Integer>> e:entry)
		{
				Float teTOTAL=0f;//临时用于计算一个景区的四个状态基点
				Map<String, Integer> map00= e.getValue();
				System.out.println("x:"+e.getKey()+"v:"+e.getValue());
				if(map00.containsKey("class0"))//没去过
				{
					teTOTAL+=
						map00.get("class0")
						*NO_GO_PER;
				}
				if(map00.containsKey("class1"))//打算去
				{
					teTOTAL+=map00.get("class1")*IN_GO_PER;
				}
				if(map00.containsKey("class2"))//已去过
				{
					teTOTAL+=map00.get("class2")*AL_GO_PER;
				}
				if(map00.containsKey("class3"))//去过还想去
				{
					teTOTAL+=map00.get("class3")*ALIN_GO_PER;
				}
				map11.put(e.getKey(), teTOTAL);//计算好的四种状态 值 放入对应的景区
//			}
		}
		//从map1集合中 取出点击数计算 map11是存四太均值
		Set<Entry<String, Integer>>  entry2= map1.entrySet();
		for(Entry<String, Integer> e2: entry2)
		{
			if(map11.containsKey(e2.getKey()))
			{
				map11.put(e2.getKey(), map11.get(e2.getKey())+e2.getValue()*CLICK_PER);
				totalNum+=map11.get(e2.getKey());
			}
		}
		//遍历map11集合  计算百分值 用对象封装 再放入List集合
		
		Set<Entry<String, Float>> entry3=  map11.entrySet();
		//先计算
		for(Entry<String, Float> e3: entry3)
		{
			map11.put(e3.getKey(), e3.getValue()/totalNum);
		}
		//排序 
		List list3 = new ArrayList(map11.entrySet());
		System.out.println("前:"+list3);
		Collections.sort(list3, new CompareSort());
		System.out.println("hou:"+list3);
		ActionContext.getContext().put("list", list3);
		String json = JSON.toJSONString(list3);
		ActionContext.getContext().put("json",json);
		System.out.println("json:"+json);
		System.out.println("查到的数目："+TJJList.size());
		//获取景区评论最大、小日期 放入actionContext域里 格式精确年数
		MinMaxDate dates= scenicCommentService.getminMaxDate();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		ActionContext.getContext().put("mindate", sdf.format(dates.getMin()));
		ActionContext.getContext().put("maxdate",sdf.format(dates.getMax()));
		return "tjData2";
		}catch(Exception e){e.printStackTrace();
			return null;
		}
	}
	private Float NO_GO_PER;//没去过 0.095
	private Float IN_GO_PER;//打算去 0.3
	private Float AL_GO_PER;//已去过0.2
	private Float ALIN_GO_PER;//打算再去0.4
	private Float CLICK_PER;//点击数0.005
	
	public Float getNO_GO_PER() {
		return NO_GO_PER;
	}

	public void setNO_GO_PER(Float nOGOPER) {
		NO_GO_PER = nOGOPER;
	}

	public Float getIN_GO_PER() {
		return IN_GO_PER;
	}

	public void setIN_GO_PER(Float iNGOPER) {
		IN_GO_PER = iNGOPER;
	}

	public Float getAL_GO_PER() {
		return AL_GO_PER;
	}

	public void setAL_GO_PER(Float aLGOPER) {
		AL_GO_PER = aLGOPER;
	}

	public Float getALIN_GO_PER() {
		return ALIN_GO_PER;
	}

	public void setALIN_GO_PER(Float aLINGOPER) {
		ALIN_GO_PER = aLINGOPER;
	}

	public Float getCLICK_PER() {
		return CLICK_PER;
	}

	public void setCLICK_PER(Float cLICKPER) {
		CLICK_PER = cLICKPER;
	}
	private Long cityId;//
	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getEndYear() {
		return endYear;
	}

	public void setEndYear(Long endYear) {
		this.endYear = endYear;
	}
	public Long getScenicId() {
		return scenicId;
	}

	public void setScenicId(Long scenicId) {
		this.scenicId = scenicId;
	}
	public InputStream getImageStream() {
		return imageStream;
	}
	public void setImageStream(InputStream imageStream) {
		this.imageStream = imageStream;
	}
	public String getSccode() {
		return sccode;
	}
	public void setSccode(String sccode) {
		this.sccode = sccode;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
