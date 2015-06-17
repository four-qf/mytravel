package com.tour.suse.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import zhu.utils.CheckEmail;
import zhu.utils.TestImgType;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.tour.suse.base.BaseAction;
import com.tour.suse.entity.Role;
import com.tour.suse.entity.User;
import com.tour.suse.util.CreateImage;
import com.tour.suse.util.MD5Util;
import com.tour.suse.util.QueryHelper;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User>{
	private File headImg;
	private String headImgFileName;
	//验证码
	private InputStream imageStream;
	private String sccode;
	//设置角色
	private Long[] roleIds;
	//修改密码
	private String rePassword;
	private String provinceName;
	public Long[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	//查看他人信息
	public String findPersonInfo()
	{
		if(model.getId()!=null)
		{model = userService.getById(model.getId());}
		return "findPersonInfo";
	}
	//用户个人资料修改
	public String pI2UUI()
	{
		System.out.println("update");
		model = userService.getById(getUser().getId());
		System.out.println("-------");
		return "pI2UUI";
	}
	//用户个人资料修改2
		public String personInfo2Update()
		{
			System.out.println("po"+provinceName);
			User u = userService.getById(getUser().getId());
			if(u.getHead()==null && headImg==null)
			{
				this.addFieldError("error", "头像、还未上传");
				return "pI2UUI";
			}
			if( model.getRealName()==null || model.getBirthDay()==null || model.getPhoneNumber()==null)
			{
				this.addFieldError("error", "真实姓名、生日、联系电话不能为空");
				return "pI2UUI";
			}
			//2、上传
			//图片路径
			if(headImg!=null){
				//1、上传图片、先删除原来的图片
				if(u.getHead()!=null)
				{
					String root = getRoot();
					File f= new File(root+"head/"+getUser().getUsername()+"/"+u.getHead());
					System.out.println("head:"+f);
					if(f.exists()){f.delete();}
				}
				
				String realpath = ServletActionContext.getServletContext().getRealPath("/head/"+getUser().getUsername());
				System.out.println(realpath);
				File savedir = new File(realpath);
			//检验图片
				String reg=".+(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.PNG|.png)$";
				Pattern pattern= Pattern.compile(reg);
				Matcher matcher=pattern.matcher(headImgFileName.toLowerCase());
				if(!matcher.find()){getOut().print("上传图片非法格式,请上传jpg,jpeg,gif,png类型");return null;};
				//检查图片大小
				if(headImg.exists() && headImg.isFile())
				{
					Long len = headImg.length();
					if(len>2*1024*1024)
					{
						getOut().print("上传图片不能超过2M");
						return null;
					}
				}else{getOut().print("上传图片不存在或者不是文件");return null;}
				//检查是否为真正图片
				try {
					if("-1".equals(TestImgType.getImageType(null,headImg)))
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
			int len = headImgFileName.split("\\.").length;
			String newName = headImgFileName.charAt(0)+getRandomFileName()+"."
			+headImgFileName.split("\\.")[len-1];
			System.out.println("newName"+newName);
			File savefile= new File(savedir, newName);
			//存入磁盘
				try {
					FileUtils.copyFile(headImg, savefile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				//存入数据库
				u.setHead(newName);
			}
			/////////////---------------------图片 上传
			u.setBirthDay(model.getBirthDay());
			u.setCityName(provinceName);
			u.setEmail(model.getEmail());
			u.setRealName(model.getRealName());
			u.setNickName(model.getNickName());
			u.setProfile(model.getProfile());
			u.setPhoneNumber(model.getPhoneNumber());
			userService.update(u);
			getSen().put("user", u);
			this.addFieldError("right", "修改成功");
			return "personArea";
		}
	//用户个人资料查看
	public String personInfoUI2()
	{
		model = userService.getById(getUser().getId());
		return "personInfoUI2";
	}
	//用户个人空间
	public String personArea()
	{
		//个人游记、已评价的景区6
		List<Object> note2s= userService.getpersonalEntity("Note",getUser(),6);
		ActionContext.getContext().put("note2s", note2s);
		List<Object> scs = userService.getpersonalCommentedScenic(getUser(),6);
		ActionContext.getContext().put("scs", scs);
		return "personArea";
	}
	//用户登录
	public String loginUI()
	{
		return "loginUI";
	}
	//用户登录2
	public String login()
	{
		
		if(model.getUsername()==null || model.getPassword()==null || sccode==null)
		{
			this.addFieldError("error", "用户名、密码、验证码不能为空");return "loginUI";
		}
		String code = (String) getSen().get("loginCode");
		if(!code.equalsIgnoreCase(sccode))
		{
			this.addFieldError("error", "验证码错误");return "loginUI";
		}
		if(!userService.YZUser(model))
		{this.addFieldError("error", "用户、密码出错");return "loginUI";}
		return "toPersonArea";
	}
	//用户注册
	public String registerUI()
	{
		return "registerUI";
	}
	//用户注册2
	public String register()
	{
		System.out.println(model.getUsername()+model.getPassword()+model.getEmail()+rePassword+sccode);
		if(model.getUsername()==null || model.getPassword()==null || model.getEmail()==null || rePassword==null || sccode==null)
		{this.addFieldError("error1", "用户名、密码、电子邮件、验证码不能为空");return "registerUI";}
		//验证码
		String code = (String) (getSen().get("loginCode"));
		if(!code.equalsIgnoreCase(sccode))
		{
			this.addFieldError("error2", "验证码错误");return "registerUI";
		}
		//2密码
		if(!rePassword.equals(model.getPassword()))
		{
			this.addFieldError("error3", "输入的两次密码不正确");return "registerUI";
		}
		if(model.getUsername().trim().length()<3 || model.getUsername().trim().length()>9 
				|| model.getPassword().trim().length()<4 ||model.getPassword().trim().length()>12
				|| !CheckEmail.isEmail(model.getEmail()))//用户名在3-9、密码在4-12、邮箱格式不正确
		{this.addFieldError("error4", "用户名在3-9、密码在4-12、邮箱格式不正确");return "registerUI";}
		if(userService.YZregister(model))
		{
			this.addFieldError("right", "注册成功！请登录");
			return "loginUI";}else{
			this.addFieldError("error5", "此用户名被注册、请从新选择");
			return "registerUI";
		}
	}
	//后台首页
	public String htIndexUI()
	{
		return "htIndexUI";
	}
	
	//修改密码
	public String updatePwUI()
	{
		return "updatePwUI";
	}
	public String updatePw()
	{
		try{
			System.out.println(""+model.getPassword()+":"+rePassword);
			if(model.getPassword()==null || rePassword==null)
			{
				this.addFieldError("error", "输入框不能为空");
				return "updatePwUI";
			}else if(rePassword.equals(model.getPassword())){
				this.addFieldError("error", "新密码和旧密码相同");
				return "updatePwUI";
			}else 
			{
				if(userService.YZUser2(model,rePassword)){
					return "topersonInfoUI";
				}
				else{
					this.addFieldError("error", "原始密码错误");
					return "updatePwUI";
				}
			}
		}catch(Exception e){e.printStackTrace();return "error";}
	}
	
	public String updatePersonInfo()
	{
		System.out.println("来了:"+model.getId());
		if(model.getId()!=null && model.getProfile().length()<225)
		{
			User u = userService.getById(model.getId());
			u.setNickName(model.getNickName());
			u.setEmail(model.getEmail());
			u.setRealName(model.getRealName());
			u.setPhoneNumber(model.getPhoneNumber());
			u.setCityName(model.getCityName());
			u.setProfile(model.getProfile());
			userService.update(u);
			getSen().remove("user");
			getSen().put("user", u);
		}else
		{
			this.addFieldError("error", "个人简介字数限制在225以内");
		}
		return "personInfoUI";
	}
	//个人信息
	public String personInfoUI()
	{
		return "personInfoUI";
	}
	
	//初始化密码
	public void initpwAjax()
	{
		try{
			userService.initPWById(model.getId());
			getOut().print(1);
		}catch(Exception e){e.printStackTrace();getOut().print(-1);}
	}
	
	//用户列表
	public String userList()
	{
		String query = "SELECT u ";
		new QueryHelper(query,User.class, "u")
		.addCondition(true, " u.roles.size=? ", 0)
		.addCondition(true, " u.username<>? ", "admin")
		.addOrderProperty(" u .lastLoginTime ", false)
		.preparePageBean(userService, pageNum, pageSize);
		return "userList";
	}
	
	//更新admin
	public String editAdmin(){
		System.out.println("roleIds"+roleIds);
		//先保存roles
		if(roleIds!= null){
			Set<Role> roles = new HashSet<Role>(roleService.getByIds(roleIds));
			model.setRoles(roles);
		}
		//获取其他属性进行添加
		User user = userService.getById(model.getId());
		user.setRoles(model.getRoles());
		user.setRealName(model.getRealName());
		userService.update(user);
		return "toAdminList";
	}
	//添加admin
	public String addAdmin()
	{
		//先判断用户名是否存在
		if(userService.YZUserName(model.getUsername()))
		{
			this.addFieldError("error", "该用户名已存在");
			//准备roles
			List<Role> roles= roleService.findAll();
			ActionContext.getContext().put("roles", roles);
			return "addAdminUI";
		}
		//先保存roles
		if(roleIds!= null){
			Set<Role> roles = new HashSet<Role>(roleService.getByIds(roleIds));
			model.setRoles(roles);
		}
		//设置初始密码
		model.setPassword(MD5Util.md5code("123456"));
		model.setCreatedate(new Date());
		model.setLastLoginTime(new Date());
		model.setLogincount(1l);
		userService.save(model);
		return "toAdminList";
	}
	
	//更新adminUI
	public String editAdminUI()
	{
		//准备roles
		List<Role> roles= roleService.findAll();
		ActionContext.getContext().put("roles", roles);
		
		model = userService.getById(model.getId());
		Set<Role> roles2= model.getRoles();
		int index = 0;
		roleIds = new Long[roles2.size()];
		for(Role r: roles2)
		{
			roleIds[index++]= r.getId();
		}
		return "addAdminUI";
	}
	
	//添加adminUI
	public String addAdminUI()
	{
		//准备roles
		List<Role> roles= roleService.findAll();
		ActionContext.getContext().put("roles", roles);
		return "addAdminUI";
	}
	//删除ajax
	public void deleteAjax()
	{
			try{
				if(model.getId()!=null){
					userService.delete(model.getId());
					getOut().print(1);
				}else{
					getOut().print(0);
				}
			}catch(Exception e){e.printStackTrace();getOut().print(-1);}
	}
	
	//获取管理员列表
	public String adminList()
	{
		try{
			List<User> users = userService.getAdminList();
			ActionContext.getContext().put("users", users);
			return "adminList";
		}catch(Exception e){e.printStackTrace();return "error";}
	}
	
	
	//注销
	public String htLoginOut()
	{
		if(getSen().get("user")!=null)
		{
			getSen().remove("user");
			return "htLoginUI";
		}else
		{
			return "error";
		}
	}
	
	//注销2
	public void loginOut()
	{
		if(getSen().get("user")!=null)
		{
			getSen().remove("user");
			getOut().print(1);
		}else
		{
			getOut().print(0);
		}
	}
	public String htLoginUI()
	{
		return "htLoginUI";
	}
	
	public String code() {
		System.out.println("lai l ");
		// 产生验证码图片流对象
		 imageStream = CreateImage.getImageAsInputStream();
		// 获取随机字符串
		String securityCode = CreateImage.actionUsedStr;
		getSen().put("loginCode", securityCode);
		System.out.println(securityCode);
		return "code";
	}
	
	//登录ajax
	public void loginAjax()
	{
		try{
			if(model.getUsername()==null || model.getPassword()==null || sccode==null)
			{
				getOut().print(0);
			}else
			{
				String code = (String) (getSen().get("loginCode"));
				if(!sccode.equalsIgnoreCase(code))
				{
					getOut().print(-1);
				}
				else 
				{
					if(userService.YZUser(model))
					{
						User u = (User) (getSen().get("user"));
						String json = JSON.toJSONString(u);
						System.out.println("json"+json);
						getOut().print(1+json);
					}else
					{getOut().print(-2);}
				}
			}
		}catch(Exception e){e.printStackTrace();getOut().print(-3);}
	}
	
	//后台登录
	public String htLogin()
	{
		try{
			if(model.getUsername()==null || model.getPassword()==null || sccode==null)
			{
				this.addFieldError("error", "用户名、密码、验证码其一为空");
				return "htLoginUI";
			}else
			{
				String code = (String) (getSen().get("loginCode"));
				if(!sccode.equalsIgnoreCase(code))
				{	this.addFieldError("error", "用户名、密码、验证码其一为空");
					return "htLoginUI";}
				else 
				{
					if(userService.YZUser(model))
					{
						System.out.println("haol");
						return "toHtIndexUI";
					}
					else
					{this.addFieldError("error", "用户名或密码有误");
						return "htLoginUI";}
				}
			}
		}catch(Exception e){e.printStackTrace();return "error";}
	}
	//注册1
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
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	private String getRandomFileName() {
		//SimpleDateFormat sDateFormat;
		Random r = new Random();
		int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
		//sDateFormat = new SimpleDateFormat("yyyyMMdd"); // 时间格式化的格式
		return rannum+""; // 当前时间
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
}
