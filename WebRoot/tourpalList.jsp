<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>游记搜索</title>
<link href="css/tourpal.css" rel="stylesheet" type="text/css" />
<link href="css/other.css" rel="stylesheet" type="text/css" />
<jsp:include flush="true" page="header.jsp"></jsp:include>
<script type="text/javascript" src="formerJs/tourpalList.js"></script>
<style type="text/css">
	#showMore{
				background-color: rgb(235,235,235);
				width: 220px;
				height: 40px;
				margin-left: 510px;
				margin-top: 10px;
				line-height: 40px;
				font-size: 14px;
				text-align: center;
				color: rgb(153,153,153);
				cursor: pointer;
				letter-spacing: 3px;
			}
			#showMore:hover{
				background-color: #0070D1;
				color: white;
			}
</style>
</head>
<body>
<input type="hidden" id="pageNum"  value="1" />
<input type="hidden" id="root"  value="${pageContext.request.contextPath}" />
	<div class="topline"></div>
	<div id="findMain">
		<div class="top">
			<div class="name"></div>
			<div class="rollHot">
				<ul>
					<s:iterator value="recomScenics">
						<li><a href="frontscenic_detailScenic.action?model.id=${id}">
						<img title="${name}" src="${pageContext.request.contextPath}/${imageUrl}" /></a></li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="left">
			<div class="findMou" id="notelevel">
				<div class="name">
					<span>景区级别</span><span class="addMore">-</span>
				</div>
				<ul>
					<li>世界级</li>
					<li>国家级</li>
					<li>省级</li>
					<li>市级</li>
					<li>其他</li>
				</ul>
			</div>
			<div class="findMou" id="noteloaction">
				<div class="name">
					<span>地理位置</span><span class="addMore">-</span>
				</div>
				<ul>
					<s:iterator value="#application.cityList">
					<li>${name}</li>
					</s:iterator>
				</ul>
			</div>
			<div class="findMou" id="noteclass">
				<div class="name">
					<span>景区类别</span><span class="addMore">-</span>
				</div>
				<ul>
					<s:iterator value="#application.scenicClassList"><!-- scenicClassList若在栈值中则可以使用size() 放在application中则不能 -->
						<s:if test="children.size()>0">
							<s:iterator value="children">
								<li z="${id}">${name}</li>
							</s:iterator>
							</s:if>
						<s:else>	
							<li z="${id}" >${name}</li>
						</s:else>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="right">
			<div id="findSea">
				<div class="title">您的选择：</div>
				<dl></dl>
				<a href="javascript: ;">清空</a>
			</div>
			<ul id="listSort">
				<li class="current" title="postTime" id="postTime">时间排序</li>
				<li title="clickNum" id="clickNum">点击量</li>
				<li title="commentNum" id="commentNum">评论量</li>
			</ul>
			<ul id="listMou">
				<s:iterator value="recordList">
					<li>
						<div class="listLine"></div> <a href="frontnote_detail.action?model.id=${id}"><img
							src="${pageContext.request.contextPath}/${imageUrl }" /></a>
						<div class="listTop">
							<a href="frontnote_detail.action?model.id=${id}">${title}</a>
							<div class="author"><a href="frontUser_findPersonInfo.action?model.id=${authorId}">${username}</a></div>
							<img class="autImg" src="${pageContext.request.contextPath}/head/${username}/${authorimg}" />
						</div>
						<div class="content">${content}</div>
						<div class="listBtm">
							<img src="formerImage/time.jpg" />
							<s:date name="postTime" format="yyyy-MM-dd HH:mm" />
							<span>${city.name}--${title}(点击量：${clickNum })(评论数：${commentNum })</span>
						</div>
					</li>
				</s:iterator>
			</ul>
		</div>
		<div style="clear:both;"></div>
		<div id="showMore">显示更多</div>
	</div>
	<div class="footer0"></div>
	<jsp:include flush="true" page="footer2.jsp"></jsp:include>
	<jsp:include flush="true" page="returnTop.jsp"></jsp:include>
</body>
</html>