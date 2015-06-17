package com.tour.suse.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.tour.suse.base.DaoSupportImpl;
import com.tour.suse.entity.User;
import com.tour.suse.service.UserService;
import com.tour.suse.util.MD5Util;

@Service
@Transactional
public class UserServiceImpl extends DaoSupportImpl<User> implements UserService {

	public boolean YZUser(User model) {
		//首先对密码加密
		model.setPassword(MD5Util.md5code(model.getPassword()));
		//在验证在数据库中是否存在
		User obj = (User) this.getSession().createQuery("" +
				"FROM User u WHERE u.password=? AND u.username=? ")
		.setParameter(0, model.getPassword())
		.setParameter(1, model.getUsername())
		.uniqueResult();
		//如果不为空则放入session
		System.out.println("pw:"+model.getPassword()+"obj:"+obj);
		if(obj!=null)
		{
			obj.setLogincount(obj.getLogincount()+1);
			obj.setLastLoginTime(new Date());
			this.update(obj);
			ActionContext.getContext().getSession().remove("user");
			ActionContext.getContext().getSession().put("user", obj);
			return true;
		}else
		{return false;}
	}

	
	public List<User> getAdminList() {
		return this.getSession().createQuery("" +
				"SELECT u FROM User u WHERE u.roles.size>0 AND u.username<>?")
				.setParameter(0, "admin")
				.list();
	}

	
	public boolean YZUserName(String username) {
		Object obj = this.getSession().createQuery("FROM User u WHERE u.username=?").setParameter(0, username).uniqueResult();
		if(obj!=null)
		return true;
		else
			return false;
	}

	
	public void initPWById(Long id) {
		User u = this.getById(id);
		u.setPassword(MD5Util.md5code("123456"));
		this.update(u);
	}
	
	//修改密码验证
	public boolean YZUser2(User model,String rePassword) {
		//首先对密码加密
		model.setPassword(MD5Util.md5code(model.getPassword()));
		//在验证在数据库中是否存在
		User obj = (User) this.getSession().createQuery("" +
				"FROM User u WHERE u.password=? AND u.username=? ")
		.setParameter(0, model.getPassword())
		.setParameter(1, model.getUsername())
		.uniqueResult();
		//如果不为空则修改密码保存放入session
		if(obj!=null)
		{
			obj.setPassword(MD5Util.md5code(rePassword));
			this.update(obj);
			ActionContext.getContext().getSession().remove("user");
			ActionContext.getContext().getSession().put("user", obj);
			return true;
		}else
		{return false;}
	}

	//验证用户注册、并且存入数据库
	public boolean YZregister(User model) {
		if(YZUserName(model.getUsername()))//不等于null表示已存在返回true
		{return false;}
		else{
			model.setPassword(MD5Util.md5code(model.getPassword()));
			this.getSession().save(model);
			return true;
		}
	}
	public List<Object> getpersonalEntity(String entity, User user, int i) {
		return this.getSession().createQuery("FROM "+entity+" n WHERE n.author=? ORDER  BY n.postTime DESC")
				.setParameter(0, user)
				.setFirstResult(0).setMaxResults(i).list();
	}

	/**
	 * 个人已评价的景区
	 */
	public List<Object> getpersonalCommentedScenic( User user, int i) {
		return this.getSession().createQuery("SELECT DISTINCT sc.scenic  FROM ScenicComment sc WHERE sc.author=? ORDER  BY sc.postTime DESC")
				.setParameter(0, user)
				.setFirstResult(0).setMaxResults(i).list();
	}
}
