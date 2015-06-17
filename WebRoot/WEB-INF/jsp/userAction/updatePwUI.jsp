<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>修改密码</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.tabfont01 {	
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}
.font051 {font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}
.font201 {font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>
</head>
<body >
<center>
	<span style="color:red;"><s:fielderror></s:fielderror></span>
 <form action="user_updatePw.action" method="post" id="updForm" >
 	<input type="hidden" name="model.username"  value="${session.user.username}"  />
 	<fieldset  style="width:400px;">
 		<legend>修改密码</legend>
 		<table>
 			<tr><td>请输入原始密码:</td><td><input type="text" name="model.password" id="pw" /></td></tr>
 			<tr><td>请输入新密码:</td><td><input type="password" name="rePassword"  id="repw" /></td></tr>
 			<tr><td>确认新密码:</td><td><input type="password" name="repassword2"  id="repw2" /></td></tr>
 			<tr><td><input type="button" value="提交"  id="sub"/></td></tr>
 		</table>
 	</fieldset>
 </form>
</center>
 <script language=JavaScript>
 	window.onload=function(){
 		document.getElementById("sub").onclick=function(){
 			var pw = document.getElementById("pw");
 			var repw = document.getElementById("repw");
 			var repw2 = document.getElementById("repw2");
 			if(pw.length>3 && pw.length<12)
 			{alert("原始密码长度应该大于3小于12");return;}
 			if(repw.length>3 && repw.length<12)
 			{alert("新密码长度应该大于3小于12");return;}
 			if(repw.value!=repw2.value)
 			{alert("输入的两次新密码不相同");return;}
 			document.getElementById("updForm").submit();
 		}
 	}
 </script>
</body>
</html>
