package com.tour.suse.service;

import java.util.List;

import com.tour.suse.base.DaoSupport;
import com.tour.suse.entity.User;


public interface UserService extends DaoSupport<User> {
	/*登录验证*/
	boolean YZUser(User model);

	List<User> getAdminList();

	boolean YZUserName(String username);

	void initPWById(Long id);
/*修改密码*/
	boolean YZUser2(User model, String rePassword);
	/*注册验证*/
	boolean YZregister(User model);
	
	/*获取个人游记*/	/*获取个人相关评论*/
	List<Object> getpersonalEntity(String obj,User user, int i);
	
	/**
	 * 获取个人评论的景区
	 * @param user
	 * @param i
	 * @return
	 */
	 List<Object> getpersonalCommentedScenic( User user, int i);
}
