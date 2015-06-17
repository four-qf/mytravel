<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>用户${model.username}的个人资料</title>
<link rel="stylesheet" type="text/css" href="css2/other.css">
<link rel="stylesheet" type="text/css" href="css2/details.css">

<script src="js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="js/city.js"></script>
<script type="text/javascript" src="js/replenish.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<jsp:include flush="true" page="header.jsp"></jsp:include>
	<div class="topline"></div>
	<div id="main">
		<div class="dealCon">
			<ul class="left" id="selLeft">
				<li ><a href="user_personArea.action">空间首页</a></li>
				<li><a href="frontnote_search.action">更多游记</a></li>
			</ul>
			<div class="rightList">
				<div class="mould mould1">${model.realName }资料</div>
				<div class="mould">
					<div class="title">个人头像：</div>
					<img src="head/${model.username}/${model.head}" width="200px" height="100px" />
					<div class="impBg"></div>
				</div>
				<div class="mould">
					<div class="title">真实姓名：</div>
					<input type="text" name="model.realName" id="name" value="${model.realName}"  disabled="disabled"/>
					<div class="impBg"></div>
				</div>
				<div class="mould">
					<div class="title">性别：</div>
					<input type="radio" name="model.gender" value="true" checked="checked" />
					<span>${model.gender?'男':'女' }</span> 
				</div>
				<div class="mould">
					<div class="title">出生日期：</div>
					<input id="Wdate" name="model.birthDay" value="${model.birthDay}" class="Wdate" type="text" disabled="disabled" onClick="WdatePicker()">
				</div>
				<div class="mould">
					<div class="title">所在城市：</div>
					<select id="provincial" disabled="disabled">
						<option>${model.cityName}</option>
					</select>
					<div class="impBg"></div>
				</div>
				<div class="mould">
					<div class="title">手机：</div>
					<input type="text" name="model.phoneNumber" value="${model.phoneNumber}" id="tel" disabled="disabled"/>
					<div class="impBg"></div>
				</div>
				<div class="mould">
					<div class="title">个人简介：</div>
					<div class="addText">
						<textarea id="textArea" name="model.profile" disabled="disabled">${model.profile}</textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer0"></div>
	<div class="footer2">
		<div class="content">
			<div class="substance">
				Copyright 2012-2013 ABCD.com,Inc. All Rights Reserved. 京ICP证09028442号 中国高尔夫球童网Copyright 2012-2013 ABCD.com,Inc. All Rights Reserved. 京ICP证09028442号 中国高尔夫球童网Copyright 2012-2013 ABCD.com,Inc. All Rights Reserved. 京ICP证09028442号 中国高尔夫球童网
			</div>
		</div>
	</div>
</body>
</html>
