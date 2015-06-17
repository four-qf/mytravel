<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人信息</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js"> </script>
</head>
<body>
<script language=JavaScript >
	$(function(){
		
		$("#sub").click(function(){
			var _this = this;
			var _id = $(_this).attr("index");
			if($(_this).attr("title")=="0")//准备修改页面
			{
				$("#nickName").html('<input type=text name=model.nickName value='+$("#nickName").html()+' />');
				$("#email").html('<input type=text name=model.email value='+$("#email").html()+' />');
				$("#realName").html('<input type=text name=model.realName value='+$("#realName").html()+' />');
				$("#phone").html('<input type=text name=model.phoneNumber value='+$("#phone").html()+' />');
				$("#cityName").html('<input type=text name=model.cityName value='+$("#cityName").html()+' />');
				$("#profile").html('<textarea name=model.profile >'+$("#profile").html()+'</textarea>');
					$(_this).attr("title","1");		
			}else{
				//提交form
				$("#updForm").submit();
			}
		});
		
	});
</script>
<center>
	<span style="color:red;"><s:fielderror></s:fielderror></span>
 <form action="user_updatePersonInfo.action" method="post" id="updForm" >
 	<input type="hidden" name="model.id"  value="${session.user.id}"  />
 	<fieldset  style="width:400px;">
 		<legend>个人信息</legend>
 		<table>
 			<tr><td>用户名:</td><td>${session.user.username}</td></tr>
 			<tr><td>昵称:</td><td id="nickName">${session.user.nickName}</td></tr>
 			<tr><td>邮箱:</td><td id="email">${session.user.email}</td></tr>
 			<tr><td>真实姓名:</td><td id="realName">${session.user.realName}</td></tr>
 			<tr><td>手机号:</td><td id="phone">${session.user.phoneNumber}</td></tr>
 			<tr><td>所在城市:</td><td id="cityName">${session.user.cityName}</td></tr>
 			<tr><td>权限角色:</td><td><s:iterator value="#session.user.roles">${name}</s:iterator> </td></tr>
 			<tr><td>个人简介:</td><td id="profile">${session.user.profile} </td></tr>
 			<tr><td><input type="button" value="点击修改"  id="sub" title="0"  index="${session.user.id}" /></td></tr>
 		</table>
 	</fieldset>
 </form>
</center>
</body>
</html>
