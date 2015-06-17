<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>修改资料</title>
<link rel="stylesheet" type="text/css" href="css2/other.css">
<link rel="stylesheet" type="text/css" href="css2/details.css">
<script src="js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="js/replenish.js"></script>
<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">  
$(function(){
	$("#cityName").val(ILData[2]+" "+ILData[3]);
}); 
</script>  
</head>
<body>
	<jsp:include flush="true" page="header.jsp"></jsp:include>
	<div class="topline"></div>
	<div id="main">
		<div class="dealCon">
			<ul class="left" id="selLeft">
				<li ><a href="user_personArea.action">空间首页</a></li>
				<li class="select"><a href="user_pI2UUI.action">修改资料</a></li>
				<li><a href="frontnote_userlist.action">更多个人游记</a></li>
				<li><a href="frontnote_search.action">更多游记</a></li>
				<li><a href="frontscenic_listByfront.action">更多景区</a></li>
			</ul>
			<form action="user_personInfo2Update.action" method="post" enctype="multipart/form-data">
			<div class="rightList">
				<div class="mould mould1"> 修改资料<s:fielderror ></s:fielderror></div>
				<div class="mould">
					<div class="title">个人头像：</div>
						<input type="file" name="headImg" style="display: none;"/>
					<div class="fileSel">
						<input type="text" disabled="disabled"/>
						<div class="selBtn">选择文件</div>
					</div>
					<div class="impBg"></div>(*若之前有头像则覆盖原来的)
				</div>
				<div class="mould">
					<div class="title">真实姓名：</div>
					<input type="text" name="model.realName" id="name" value="${model.realName}"/>
					<div class="impBg"></div>
				</div>
				<div class="mould">
					<div class="title">性别：</div>
					<input type="radio" name="model.gender" value="true" ${model.gender?'checked':'' } " />
					<span>男</span> 
					<input type="radio" name="model.gender" value="false" ${model.gender?'':'checked' } />
					<span>女</span>
				</div>
				<div class="mould">
					<div class="title">出生日期：</div>
					<input id="Wdate" name="model.birthDay" class="Wdate" type="text" onClick="WdatePicker()" value="${model.birthDay }">
					<div class="impBg"></div>
				</div>
				<div class="mould">
					<div class="title">所在城市：</div>
					<input type="text" name="provinceName" id="cityName" value="${model.cityName}"  />
					<div class="impBg"></div>
				</div>
				<div class="mould">
					<div class="title">手机：</div>
					<input type="text" name="model.phoneNumber" id="tel" value="${model.phoneNumber }"/>
					<div class="impBg"></div>
				</div>
				<div class="mould">
					<div class="title">个人简介：</div>
					<div class="addText">
						<textarea id="textArea" name="model.profile">${model.profile }</textarea>
					</div>
				</div>
				<input type="submit" value="确定提交" id="submit"/>
			</div>
			</form>
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
