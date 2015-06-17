package com.tour.suse.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
/**
 * 用户
 * @author Lazy_
 *
 */
public class User implements Serializable{
	//用户ID、用户名、密码、邮箱、昵称、头像、真实姓名、性别、出生日期、所在城市、手机、个人简介……
	private Long id;
	private String username;
	private String password;
	private String email;
	private String nickName;
	private String head;
	private String realName;
	private boolean gender;
	private Date birthDay;
	private String cityName;
	private String phoneNumber;
	private String profile;
	private Date createdate;
	private Date lastLoginTime;
	private long logincount;
//	private Long type=0l;//一般申请的用户都是0类型 表示非管理员
	//
	private Set<Role> roles  = new HashSet<Role>();
	private Set<Note> notes  = new HashSet<Note>();
	private Set<ScenicComment> scenicComments  = new HashSet<ScenicComment>();
	/*权限判断*/
	public boolean hasPrivilegeByName(String name)
	{
		if (isAdmin())
		{return true;}
		
		//普通用户 判断是否含有这个权限
		for (Role role: roles)
		{
			for (Privilege priv: role.getPrivileges())
			{
				if (priv.getName().equals(name))
				{return true;}
			}
		}
		//如果普通用户 的所有role角色中没有与权限名相同，则进行返回false
		return false;
	}
	
	/*权限判断 通过url*/
	public boolean hasPrivilegeByUrl(String privUrl)
	{
		if (isAdmin())
		{return true;}
		//去掉后面的参数
				int pos = privUrl.indexOf("?");
				if (pos>-1)
				{privUrl = privUrl.substring(0, pos);}
				
				//去掉UI后缀
				if (privUrl.endsWith("UI"))
				{privUrl = privUrl.substring(0, privUrl.length()-2);}
				
				//从服务启动加载的数据 列中取出， 其实就是application
				Collection<Privilege> allPrivileges = (Collection<Privilege>) ActionContext.getContext().
						getApplication().get("allPrivileges");
		//判断是否为控制权限
				//不是控制权限 给予通行
				if (!allPrivileges.contains(privUrl))
				{
					System.out.println("f:"+allPrivileges.contains(privUrl)+"privUrl:"+privUrl);
					return true;}
				else{
						//普通用户 判断是否含有这个权限
						for (Role role: roles)
						{
							for (Privilege priv: role.getPrivileges())
							{
								if (privUrl.equals(priv.getUrl()))
								{return true;}
							}
						}
				}
		//如果普通用户 的所有role角色中没有与权限名相同，则进行返回false
		return false;
	}
	/*
	 * 判断本用户是否为超级管理员
	 * */
	private boolean isAdmin() {
		return "admin".equals(username);
	}
	
	public User(){}
	public User(long id) {
		super();
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public long getLogincount() {
		return logincount;
	}
	public void setLogincount(long logincount) {
		this.logincount = logincount;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}

//	public Long getType() {
//		return type;
//	}
//
//	public void setType(Long type) {
//		this.type = type;
//	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public Set<ScenicComment> getScenicComments() {
		return scenicComments;
	}

	public void setScenicComments(Set<ScenicComment> scenicComments) {
		this.scenicComments = scenicComments;
	}
}
