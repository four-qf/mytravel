<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!doctype html>
<html>
	<head>
		<title>注册</title>
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
		<center><span style="color:red;"><s:fielderror></s:fielderror></span></center>
		<div class="main main2">
			<div class="mainTop">
				<div class="topText">注册用户</div>
			</div>
			<form action="frontUser_register.action" method="post">
			<div class="texCon">请输入信息：</div>
			<input type="text" class="name" name="model.username" placeholder="请输入用户名，长度为3-10位"/>
			<input type="password" class="pass" name="model.password" placeholder="请输入密码，长度为4-12位"/>
			<input type="password" class="passAgian" name="rePassword" placeholder="请再一次输入密码"/>
			<input type="text" class="email" name="model.email" placeholder="请输入邮箱"/>
			<div class="yanZM">
				<input type="text" class="yzm" name="sccode" placeholder="请输入验证码"/>
				<img id="num" src="manager/user_code.action" onclick="document.getElementById('num').src = 'manager/user_code.action?'+(new Date()).getTime()"/>
			</div>
			<input type="submit" value="注册" id="submit"/> <a href="frontUser_loginUI.action">登录</a>
			</form>
		</div>
		<script src="js/regist.js"></script>
	</body>
</html>