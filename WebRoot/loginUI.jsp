<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!doctype html>
<html>
	<head>
		<title>登录</title>
		<link href="css2/login.css" rel="stylesheet" type="text/css"/>
		<script src="js/jquery-1.7.min.js"></script>
	</head>
	<body>
		<div class="top">
			<div class="content">
				<img src="img/bg4.jpg" />
				<div class="text">智慧旅游研究基地</div>
			</div>
		</div>
		<center><span style="color:blue;"><s:fielderror></s:fielderror></span></center>
		<div class="main">
			<form action="frontUser_login.action" method="post">
			<div class="mainTop">
				<div class="topText">用户登录</div>
			</div>
			<div class="texCon">请输入信息：</div>
			<input type="text" class="name" name="model.username" value="请输入用户名"/>
			<input type="password" class="pass" name="model.password" value="请输入密码"/>
			<div class="yanZM">
				<input type="text" class="yzm" name="sccode" value="请输入验证码"/>
				<img id="num" src="manager/user_code.action" onclick="document.getElementById('num').src = 'manager/user_code.action?'+(new Date()).getTime()"/>
			</div>
			<input type="submit" value="登录"/>
			<div class="regist">
				<a href="frontUser_registerUI.action">没有账号?进行注册</a>
			</div>
			</form>
		</div>
		<script src="js/newlogin.js"></script>
	</body>
</html>