package com.tour.suse.interceptor;

/*
 * 对前端的拦截
 * */
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tour.suse.entity.User;

public class CheckPrivilegeInterceptor2 extends AbstractInterceptor{

	public String intercept(ActionInvocation invocation) throws Exception {
		//获取信息
		User user = (User) ActionContext.getContext().getSession().get("user");
		String nameSpaceName = invocation.getProxy().getNamespace();//获取action中对应的包名
		String actionName = invocation.getProxy().getActionName();//获取action的名字
		String privUrl = nameSpaceName+"/"+actionName;
		System.out.println(privUrl);
		//未登录
		if(user==null){
			if(privUrl.startsWith("//lists") || privUrl.startsWith("//front") 
					|| privUrl.startsWith("//onelist") || privUrl.startsWith("//index")
					|| privUrl.startsWith("/manager/user_code")
					|| privUrl.startsWith("//findArticleById")
//					|| privUrl.startsWith("//user_loginOut")
					)//lists onelist frontnote_detail //findArticleById  //frontUser_registerUI //user_loginOut
			{
				return invocation.invoke();
			}
			else 	//如果不是区登录，转到登录页面
			{
				System.out.println("未登录、超权");
				return "loginUI";
			}
		//已登录，就判断权限
		}
		else
		{
			return invocation.invoke();
		}
	}
	
}
