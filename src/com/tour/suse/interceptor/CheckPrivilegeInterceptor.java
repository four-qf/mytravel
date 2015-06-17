package com.tour.suse.interceptor;

/*
 * 对后台的拦截
 * */
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tour.suse.entity.User;

public class CheckPrivilegeInterceptor extends AbstractInterceptor{

	public String intercept(ActionInvocation invocation) throws Exception {
		//获取信息
		User user = (User) ActionContext.getContext().getSession().get("user");
		String nameSpaceName = invocation.getProxy().getNamespace();//获取action中对应的包名
		String actionName = invocation.getProxy().getActionName();//获取action的名字
		String privUrl = nameSpaceName+"/"+actionName;
		//zhengyan ma
		System.out.println(privUrl);
		if(privUrl.startsWith("/manager/user_code")|| privUrl.startsWith("/manager/user_htLogin")
				|| privUrl.startsWith("/manager/user_loginAjax"))///
		{
			return invocation.invoke();
		}
		//未登录
		if(user==null)
			if(privUrl.startsWith("/user_htLogin") || privUrl.startsWith("/user_code")|| privUrl.startsWith("/frontUser_"))
			{
				return invocation.invoke();
			}
			else 	//如果不是区登录，转到登录页面
			{
				System.out.println("未登录、超权");
				return "htLoginUI";
			}
		//已登录，就判断权限
		else
		{
			if (user.hasPrivilegeByUrl(privUrl)){
				System.out.println("privUrl："+privUrl+",通过");
				//如果有权限，就放行
				return invocation.invoke();
			}
			else{
				//如果没有权限就转到提示页面
				System.out.println("privUrl："+privUrl+",没通过");
				return "noPrivilegeError";
			}
		}
	}
	
}
