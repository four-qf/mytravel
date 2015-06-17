package com.tour.suse.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.tour.suse.base.BaseAction;
import com.tour.suse.entity.Scenic;
import com.tour.suse.entity.ScenicClass;
import com.tour.suse.entity.SubScenic;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class SubScenicAction extends BaseAction<SubScenic>{
	private File[] scenicImage;
	private String[] scenicImageFileName;
	//类别字段
	private String SubScenicClasses;
	//在更新期 修改类别
	private String isUpdateByUpdate;
	//在更新期是否增加 
	private String isUpdateAdd;
	//对应景区id
	private Long scenicID;
	
	public String getIsUpdateByUpdate() {
		return isUpdateByUpdate;
	}
	public void setIsUpdateByUpdate(String isUpdateByUpdate) {
		this.isUpdateByUpdate = isUpdateByUpdate;
	}
	public String getIsUpdateAdd() {
		return isUpdateAdd;
	}
	public void setIsUpdateAdd(String isUpdateAdd) {
		this.isUpdateAdd = isUpdateAdd;
	}
	public Long getScenicID() {
		return scenicID;
	}
	public void setScenicID(Long scenicID) {
		this.scenicID = scenicID;
	}
	public String getSubScenicClasses() {
		return SubScenicClasses;
	}
	public void setSubScenicClasses(String subScenicClasses) {
		SubScenicClasses = subScenicClasses;
	}
	public File[] getScenicImage() {
		return scenicImage;
	}
	public void setScenicImage(File[] scenicImage) {
		this.scenicImage = scenicImage;
	}
	public String[] getScenicImageFileName() {
		return scenicImageFileName;
	}

	public void setScenicImageFileName(String[] scenicImageFileName) {
		this.scenicImageFileName = scenicImageFileName;
	}

	public String addUI(){
		return "success";
	}
	
	//类别上传
	public String ClassUp(){
		try{
		PrintWriter out = getOut();
		if (SubScenicClasses!=null)
		{
			if(!SubScenicClasses.trim().equals(""))
			{
				//如果在更新 期间  直接操作主类
				if(isUpdateByUpdate!=null && isUpdateByUpdate.trim().equals("update"))
				{
					//解析id类别字段
						//景区类别
					String[] scenicclass = SubScenicClasses.split("\\,");
					Long[] scenicclassLong = new Long[scenicclass.length];
					for(int i=0;i<scenicclass.length;i++)
					{	
						scenicclassLong[i] = Long.parseLong(scenicclass[i]);
					}
					//获取新增类别对像集合
					List<ScenicClass> scenicClassList =  scenicClassService.getByIds(scenicclassLong);//获取类别 集合
					//获取 主类对象
					Scenic tempScenic = scenicService.getById(scenicID);
						//获取原来的scenicclass
//					List<ScenicClass> SubscenicClassList = (List<ScenicClass>) tempScenic.getScenicClasses();
					//合并新旧
					System.out.println("合并新旧");
					scenicClassList.addAll(tempScenic.getScenicClasses());
					//在更新
					tempScenic.setScenicClasses(new HashSet<ScenicClass>(scenicClassList));
					//更新主类
					scenicService.update(tempScenic);//scenicClasses
					out.print("success");
					return null;
				}
				//把类别与图片对应存入数据库
				SubScenic model= subScenicService.getByLastDateAndUser(getUser());
				model.setSubScenicClass(SubScenicClasses);
				subScenicService.update(model);
				out.print("success");
				return null;
			}else{
				return null;
			}
		}
		else
		{
			return null;
		}}catch(Exception e){e.printStackTrace(); }
		return null;
	}
	//图片上传
	public String ImgUp(){
		//是否更新增加
		System.out.println("isUpdateAdd"+isUpdateAdd+",scenicID"+scenicID);
		//景区名 自动封装
		System.out.println(model.getName());
		//作者
		model.setAuthor(getUser());
		System.out.println("景区题写人："+model.getAuthor().getUsername());
		//日期
		model.setPostTime(new Date());
		//图片路径
		PrintWriter  out = null;
		String realpath = ServletActionContext.getServletContext().getRealPath("/images");
		System.out.println(realpath);
		//图片新名字
		try{
		System.out.println("namelength:"+scenicImageFileName.length);
		String []newName=new String[scenicImageFileName.length];
		
		if(scenicImage!=null){
			File savedir = new File(realpath);
			if(!savedir.exists()) savedir.mkdirs();
			for(int i = 0 ; i<scenicImage.length ; i++){	
				//文件名称 随机五个数
				newName[i] =  scenicImageFileName[i].charAt(0)+getRandomFileName()+"."+
				scenicImageFileName[i].split("\\.")[scenicImageFileName[i].split("\\.").length-1];
				System.out.println("newName:"+newName[i]);
				File savefile = new File(savedir,newName[i]);
				try {
					FileUtils.copyFile(scenicImage[i], savefile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			String Urls="";
			for(String ImgUrl:newName)
			{
				System.out.println("图片所有路径"+ImgUrl);
				Urls+="images/"+ImgUrl+";";
			}
			//如果是更新页面 添加 则不存数据库
			if (isUpdateAdd!=null && isUpdateAdd.trim().equals("add")){
				//先取出
				Scenic temScenic = scenicService.getById(scenicID);
				String updateAfterUrl = temScenic.getImageUrl()+Urls;
				scenicService.updateImgUrlById(scenicID, updateAfterUrl);
				out = getOut();
				System.out.println("更新页面success");
				out.print("success");
				return null;
			}else{
			model.setImageUrl(Urls);
			System.out.println(model.getImageUrl());
			//保存 数据库
			subScenicService.save(model);
			out = getOut();
			System.out.println("添加页面success");
			out.print("success");}
			return null;
		}else
		{
			System.out.println("scenicImage"+scenicImage);
			out.print("fail");
			return null;
		}
		}catch(Exception e){e.printStackTrace();}
		return null;
	}
	/**
	 *  生成随机文件名：当前年月日时分秒+五位随机数
	 * @return
	 */
	private String getRandomFileName() {
		Random r = new Random();
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
		return rannum+""; // 当前时间
	}
}
