<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>管理员列表</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js"> </script>
	<SCRIPT type="text/javascript">
		function del(id,username){
			var id = id;
			if(!confirm("确认删除:“"+username+"”?"))	
			{return false;}
			$.ajax({
				url:"user_deleteAjax.action",
				type:"get",
				data:{"model.id": id},
				success: function(data){
					if(data==1)
					{$("#tr_"+id).remove();return false;}
					else{alert("操作失败"+data);return false;}
				}
			});
		}
		//初始化密码
		function initpw(id,username)
		{
			if(!confirm("确认初始化:“"+username+"”密码为123456?"))	
			{return false;}
			var id = id;	
			$.ajax({
				url:"user_initpwAjax.action",
				type:"get",
				data:{"model.id": id},
				success: function(data){
					if(data==1)
					{alert("初始化密码成功");return false;}
					else{alert("操作失败"+data);return false;}
				}
			});
		}
	</SCRIPT>
  </head>
  
  <body>
  	<s:a action="user_addAdminUI.action" escapeAmp="user_addAdminUI">添加</s:a>
   		<table>
   			<thead>
   				<tr>
   					<td>用户ID</td>
   					<td>用户名</td>
   					<td>昵称</td>
   					<td>邮箱</td>
   					<td>真实姓名</td>
   					<td>手机号</td>
   					<td>所在城市</td>
   					<td>权限角色</td>
   					<td>最近登陆时间</td>
   					<td>操作</td>
   				</tr>
   			</thead>
   			<tbody>
   				<s:iterator value="users">
   				<tr id="tr_${id}">
   					<td>${id }</td>
   					<td>${username }</td>
   					<td>${nickName }</td>
   					<td>${email }</td>
   					<td>${realName }</td>
   					<td>${phoneNumber }</td>
   					<td>${cityName }</td>
   					<td><s:iterator value="roles">${name}、</s:iterator> </td>
   					<td>${lastLoginTime }</td>
   					<td>
   						<s:a href="javascript: del(%{id},%{username});" escapeAmp="user_deleteAjax">删除</s:a>
   						<s:a href="user_editAdminUI.action?model.id=%{id}" escapeAmp="user_editAdminUI" >更新</s:a>
   						<s:a href="javascript: initpw(%{id},%{username});" escapeAmp="user_initpwAjax">初始化密码</s:a>
   					</td>
   				</tr>
   				</s:iterator>
   			</tbody>
		</table>
  </body>
</html>
