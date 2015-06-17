<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>空间首页</title>
<link rel="stylesheet" type="text/css" href="css2/other.css">
<link rel="stylesheet" type="text/css" href="css2/details.css">

<script src="js/jquery-1.7.min.js"></script>
</head>

<body>
	<jsp:include flush="true" page="header.jsp"></jsp:include>
	</div>
	<div class="topline"></div>
	<div id="main">
		<div class="dealCon">
			<ul class="left" id="selLeft">
				<li class="select"><a href="user_personArea.action">空间首页</a></li>
				<li>
					<s:if test="#session.user.head!=null">
						<a href="user_personInfoUI2.action">个人资料</a>				
					</s:if>
					<s:else>
						<a href="user_pI2UUI.action">修改资料</a>
					</s:else>
				</li>
				<li><a href="frontnote_userlist.action">更多个人游记</a></li>
				<li><a href="frontnote_search.action">更多游记</a></li>
				<li><a href="frontscenic_listByfront.action">更多景区</a></li>
				<li><a href="frontnews_list.action">新闻中心</a></li>
			</ul>
			<div class="rightList">
				<div class="mould mould1">个人游记||<a href="frontnote_addUI.action">发表游记</a>
				</div>
				<ul class="processed">
				<s:iterator value="note2s">
					<li>
						<div class="imgMou">
							<a href="frontnote_detail.action?model.id=${id}">
								<div class="liBgDiv"></div>
								<img src="${imageUrl}" />
							</a>
						</div>
						<div class="textCon">${title}</div>
						<div class="timeCam" >
							<div>${postTime}</div>
						</div>
					</li>
					</s:iterator>
				</ul>
				<div class="mould mould1 mould2">已评价景区</div>
				<ul class="processed">
					<s:iterator value="scs">
					<li>
						<div class="imgMou">
							<a  href="frontscenic_detailScenic.action?model.id=${id}">
							<div class="liBgDiv"></div>
							<img src="${imageUrl}" /></a>
						</div>
						<div class="textCon">${name}</div>
					</li>
					</s:iterator>
				</ul>
				<div  style="clear: both;"></div>
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
	<script type="text/javascript" src="formerJs/login.js"></script>
	<script type="text/javascript" src="js/roomHome.js"></script>
</body>
</html>
