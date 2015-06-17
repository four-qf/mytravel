<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>驴友发表游记</title>
<style type="text/css">
#imagePreview { 
	width: 300px; 
	height: 120px; 
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale); 
	margin-bottom: 15px;
} 
</style>
<link href="css/other.css" rel="stylesheet" type="text/css" />
<link href="css/tourpal.css" rel="stylesheet" type="text/css" />
<jsp:include flush="true" page="header.jsp" ></jsp:include>
<jsp:include flush="true" page="commjsf/picup.jspf"></jsp:include>
<script src="formerJs/tourpalPublic.js"  type="text/javascript"></script>
<script src="noteScript/zhu_picUp/picviewcore.js"  type="text/javascript"></script>
</head>
<body>
	<div class="topline"></div>
	<span style="color:red"><s:fielderror></s:fielderror></span>
	<form action="frontnote_add.action" id="addform" method="post" enctype="multipart/form-data" >
		<div id="pubMain">
			<div class="pubTop"></div>
			<div id="pubCon">
				<div class="right">
						<input type="hidden" name="model.city.id" id="cityId" value="-1"/>
						<input type="hidden" name="scenicId" id="sId" value="-1"/>
						<input type="text" class="name" name="model.title" placeholder="请输入标题"/>
					<div style="border: 1px solid gray; margin-bottom: 5px;width:500px">
							<div style="line-height: 30px; width: 200px; height: 30px;top: 5px;"> <span>首页图片：</span></div>
						 	 <input id="imageInput" type="file" name="noteImage" onchange="loadImageFile();" /><br /> 
						 	 <div id="imagePreview"> 
							</div>
					</div>
					<div id="developed">
						<select id="city"  onchange="findScenicByCity()">
								<option value="-1">请选择城市</option>
							<s:iterator value="citys">
								<option value="${id}">${name}</option>
							</s:iterator>
						</select> 
						<select id="spot">
							<option value="-1">请选择景点名</option>
						</select>
					</div>
					<div class="text">
						<textarea id="xhecontent"  class="textNote" name="model.content"  placeholder="请至少输入20字" style="height: 350px;"></textarea>
					</div>
				</div>
				<div class="left">
					<div id="state">
						<div class="staTop">游记公约</div>
						<ul>
							<li>转载他人的游记时请注明原文作者和出处链接，相信原作者一定会因此感谢您的。</li>
							<li>请用游记来分享您旅途中的快乐体验，勿用不雅词句或人身攻击毁了您的游记。</li>
							<li>请尊重他人的风俗习惯和喜好，不要在游记中发表任何针对种族、国家、宗教、性别、年龄、地缘、性取向、生理特征的歧视和仇恨言论。
							</li>
						</ul>
					</div>
					<div id="state">
						<div class="staTop">版权使用说明</div>
						<div class="copr">同意四川省智慧旅游研究基地将本人的原创内容在支付相关稿酬的情况下用于商业用途，并在不改变原意的情况下可进行适度改编用于书籍出版等用途。具体稿酬参看法律声明中的相关条款。
						</div>
					</div>
				</div>
			</div>
			<div class="status">
				<input type="submit"  id="sub0" value="发表游记" />
				<div class="checkSta" id="checkSta">抱歉，不能提交，请填写完整。</div>
				<div style="float: right;margin-right: 0px;"> <s:if test="#session.user.id==model.author.id"><input type="button" value="返回" onclick="window.history.go(-1)" /></s:if></div>
			</div>
		</div>
	</form>
	<div class="footer0"></div>
	<jsp:include flush="true" page="footer2.jsp"></jsp:include>
</body>
</html>