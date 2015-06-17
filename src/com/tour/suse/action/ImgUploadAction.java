package com.tour.suse.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.tuckey.web.filters.urlrewrite.Run;

import zhu.utils.TestImgType;
import zhu.utils.TimerDelPic;

import com.opensymphony.xwork2.ActionContext;
import com.tour.suse.base.BaseAction;
import com.tour.suse.entity.NoteFileUp;
@SuppressWarnings({ "serial", "unchecked" })
@Controller
@Scope("prototype")
public class ImgUploadAction extends BaseAction<NoteFileUp>{
	private String pictime;
	public String imgUpload() {
		HttpServletRequest  request= ServletActionContext.getRequest();
		PrintWriter out = getOut();
		//检查用户是否存在
		if(getUser()==null)
		{
			out.print("该用户未登录");
			return null;
		}
		//路径模式：NoteUploads+用户名
		String picurl = "/NoteUploads/"+getUser().getUsername();
		String saveRealFilePath = ServletActionContext.getServletContext().getRealPath(picurl);
		File fileDir = new File(saveRealFilePath);
		if (!fileDir.exists()) { //如果不存在 则创建 
			fileDir.mkdirs();
		}
		File savefile;
		//文件名字  原文件第一个字+随机5位数
		String imgname= model.getFiledataFileName().charAt(0)+getRandomFileName()+"."+model.getFiledataFileName().split("\\.")[model.getFiledataFileName().split("\\.").length-1];
		savefile = new File(saveRealFilePath + "/" +imgname);
		System.out.println("fileName:"+savefile.getName());
		//同时倒计时30分钟删除图片 若没有提交
			Timer timer= new Timer();
			timer.schedule(new TimerDelPic(savefile), 1000*60*30);//半个小时后自动删除
			//记录用户上传的图片路径
			Map<String, Object> imgsession = ActionContext.getContext().getSession();
			//设置时间标志
			//key=time标志+用户名 value=List<Timer>
			String key = pictime+getUser().getUsername();
			Object obj=imgsession.get(key);
			System.out.println("setkey:"+key+","+obj);
			if(obj!=null)
			{
				//取出List集合   加入当前的timer
				List<Timer> liTimer = (List<Timer>) obj;
					//限制上传数为3
					if(liTimer.size()>2)
					{out.print("添加图片数最多为3");
						return null;
					}
				liTimer.add(timer);
				imgsession.put(key, liTimer);
				System.out.println("有了");
			}else{//没有图片记录 新建List集合 加入当前的timer
				List<Timer> liTimer=new ArrayList<Timer>();
				liTimer.add(timer);
				imgsession.put(key, liTimer);
				System.out.println("放进去了");
			}
		//同时倒计时30分钟删除图片 结束	
		
		//检查图片扩展名1
		String reg=".+(.JPG|.jpg|.GIF|.gif|.PNG|.png)$";
		Pattern pattern= Pattern.compile(reg);
		Matcher matcher=pattern.matcher(model.getFiledataFileName().toLowerCase());
		if(!matcher.find()){out.print("上传图片非法格式,请上传jpg,gif,png类型");return null;};
		//检查图片大小
		if(model.getFiledata().exists() && model.getFiledata().isFile())
		{
			Long len = model.getFiledata().length();
			if(len>2*1024*1024)
			{
				out.print("上传图片不能超过2M");
				return null;
			}
		}else{out.print("上传图片不存在或者不是文件");return null;}
		
		//检查是否为真正图片
		try {
			if("-1".equals(TestImgType.getImageType(null,model.getFiledata())))
			{
				out.print("请不要攻击服务器1！");
				return null;
			}
		} catch (IOException e1) {
			out.print("请不要攻击服务器2！");
			e1.printStackTrace();
			return null;
		}
		
		try {
			FileUtils.copyFile(model.getFiledata(), savefile);
		} catch (IOException e) {
			model.setErr("错误"+e.getMessage());
			out.print("发生未知错误");
			e.printStackTrace();
			return null;
		}
		final String fileName = request.getContextPath() + picurl +"/"+ imgname;
		model.setMsg("{\"err\":\"" + model.getErr() + "\",\"msg\":\"" + fileName + "\"}");
		out.print(model.getMsg()); //返回msg信息
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
	public String getPictime() {
		return pictime;
	}
	public void setPictime(String pictime) {
		this.pictime = pictime;
	}
}
