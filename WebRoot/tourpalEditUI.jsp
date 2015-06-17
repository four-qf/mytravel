<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>驴友修改游记</title>
<link href="css/other.css" rel="stylesheet" type="text/css" />
<link href="css/tourpal.css" rel="stylesheet" type="text/css" />
<jsp:include flush="true" page="header.jsp"></jsp:include>
<jsp:include flush="true" page="commjsf/picup.jspf"></jsp:include>
<script src="formerJs/tourpalPublic.js"  type="text/javascript"></script>
<script>
	function PreviewImage(imgFile) {
		var pattern = /(\.*.jpg$)|(\.*.png$)|(\.*.jpeg$)|(\.*.gif$)|(\.*.bmp$)/;
		if (!pattern.test(imgFile.value)) {
			alert("系统仅支持jpg/jpeg/png/gif/bmp格式的照片！");
			imgFile.focus();
		} else {
			var path;
			if (document.all)//IE
			{
				imgFile.select();
				path = document.selection.createRange().text;
				document.getElementById("imgPreview").innerHTML = "";
				document.getElementById("imgPreview").style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\""
						+ path + "\")";//使用滤镜效果
			} else//FF
			{
				path = URL.createObjectURL(imgFile.files[0]);
				document.getElementById("imgPreview").innerHTML = "<img src='"+path+"'/>";
			}
		}
	}
</script>
</head>
<body>
	<div class="topline"></div>
	<s:fielderror></s:fielderror>
	<s:form action="frontnote_edit.action" id="addform" method="post" enctype="multipart/form-data" >
		<div id="pubMain">
			<div class="pubTop"></div>
			<div id="pubCon">
				<div class="right">
						<input type="hidden"  value="true" id="editUI" />
						<input type="hidden" name="cityId" id="cityId" value="${model.scenic.city.id}"/>
						<input type="hidden" name="model.id" value="${model.id}"/>
						<input type="hidden" id="getTitle" value="${model.title}"/>
						<input type="hidden" name="scenicId" id="sId" value="${model.scenic.id}"/>
						<input type="hidden" name="model.imageUrl" value="${model.imageUrl}"/>
						<input type="text" class="name" id="title" name="model.title" value="${model.title}"/>
					 <span>首页图片：</span>
					<input type="file" style="display: none;" onchange='PreviewImage(this)' name="noteImage"/>
					<div class="fileSel">
					</div>
					<div id="imgPreview" style='width:250px; height:150px;'>
						<img src="${model.imageUrl}" />
					</div>
					<div id="developed">
						<select id="city"  onchange="findScenicByCity()">
								<option id="editCityId" value="${model.scenic.city.id}" style="color:red">${model.scenic.city.name}</option>
							<s:iterator value="citys" >
								<option value="${id}" >${name}</option>
							</s:iterator>
						</select> 
						<select id="spot">
							<option value="${model.scenic.id}" style="color:red">${model.scenic.name}</option>
						</select>
					</div>
					<div class="text">
						<textarea id="xhecontent" name="model.content" style="height: 350px;">${model.content}</textarea>
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
				<div class="checkSta"></div>
				<div style="float: right;margin-right: 0px;"> 
				<s:if test="#session.user.id==model.author.id"><input type="button" value="返回" style="cursor:pointer;margin-top: 0px;background: rgb(79,176,226);color: white;width: 100px ;height: 30px;" onclick="window.history.go(-1)" /></s:if></div>
			</div>
		</div>
	</s:form>
	<div class="footer0"></div>
	<jsp:include flush="true" page="footer2.jsp"></jsp:include>
</body>
</html>