<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
<title>驴友游记详情页面</title>
<meta http-equiv="pragma" content="no-cache" />    
<meta http-equiv="cache-control" content="no-cache" />    
<meta http-equiv="expires" content="0" />   
<link href="${pageContext.request.contextPath}/css/other.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/tourpal.css" rel="stylesheet" type="text/css" />
<jsp:include flush="true" page="header.jsp"></jsp:include>
<script type="text/javascript" src="formerJs/tourpalDetails.js"></script>
<script>
$(function(){
	$("#commPageNum").val(1);
	//设置更多
	var s = $(".MainMou").children().size();
	if(s<10)
	{
		$(".moreComm").css("display","none");
	}
});
	
</script>
<style>
	.moreComm{
		float: right;
		index-right: 20px;
		width: 90px;
		height: 40px;
		background: #95E1CD;
		color: #FFFFFF;
		font-size: 14px;
		cursor: pointer;
		line-height: 40px;
		text-align: center;
		margin-top: 5px
	}
	.moreComm:hover{
		background-color: #0070D1;
		color: white;
	}
</style>
</head>
<body>
<form autocomplete="off">    
<input type="hidden" id="modelId" value="${model.id}" />
<input type="hidden" id="root" value="${pageContext.request.contextPath}" />
<input type="hidden" id="commPageNum" value="${pageNum}" />
<input type="hidden" id="authorHead" value="${session.user.head}" />
<input type="hidden" id="authorUsername" value="${session.user.username}" />
</form>
	<div class="topline"></div>
	<div class="main">
		<div class="left">
			<div class="hotSpot">
				<div class="top">热门景点</div>
				<ul>
					<s:iterator value="#hotScenicList">
					<li>
						<img src="${pageContext.request.contextPath}/${fn:split(imageUrl,';')[0]}"></img>
						 <a href="frontscenic_detailScenic.action?model.id=${id }">${name }</a>
					 </li>
					</s:iterator>
				</ul>
			</div>
			<div class="tradeInfor">
				<div class="top">行业资讯</div>
				<div class="content">
					<ul>
						<li><a href="lists.action?type=7">智慧公告</a></li>
						<li><a href="lists.action?type=8">旅游动态</a></li>
						<li><a href="lists.action?type=9">专题活动</a></li>
						<li><a href="lists.action?type=10">旅游业百科</a></li>
						<li><a href="lists.action?type=11">政策法规</a></li>
					</ul>
				</div>
			</div>
			<div id="strategy">
				<div class="top">驴友游记攻略(热门游记)</div>
				<ul>
					<s:iterator value="hotNotes">
						<li>
							<img src="formerImage/liImage1.png">
							<a href="frontnote_detail.action?model.id=${id}">[${formatTime}] ${title}</a>
						</li>
					</s:iterator>
				</ul>
			</div>
		</div>
		<div class="right">
			<div id="position">
				<a>当前位置：</a>&gt; <a href="index.action">首页</a>&nbsp;&gt;&nbsp;&nbsp; <a
					href="frontnote_search.action">驴友游记搜索</a>&nbsp;&nbsp;&gt; <a>${model.title}</a>
			</div>
			<div id="substance">
				<div class="subtop">${model.title }</div>
				<div class="authorTime">发布者:<a href="frontUser_findPersonInfo.action?model.id=${model.author.id }">${model.author.username }</a>&nbsp;&nbsp;点击数:
					${model.clickNum } &nbsp;&nbsp; <s:date name="model.postTime" format="yyyy-MM-dd HH:mm" />  <s:if test="#session.user.id==model.author.id"><a href="frontnote_editUI.action?model.id=${model.id}">编辑</a></s:if></div>
				<div class="content">${model.content }</div>
			</div>
			<div id="posNeg">
				<div id="negativeDiv">${model.goodNum }票</div>
				<a class="negative"  href="javascript:voteNote(true,'${id }');"   title="差评"></a>
				<div id="positiveDiv">${model.badNum }票</div>
				<a class="positive"   href="javascript:voteNote(false,'${id }');" title="好评"></a>
			</div>
			<ul id="choose">
				<li class="current"  index="${model.commentNum}">评论(${model.commentNum})</li>
				<li>发表评论</li>
			</ul>
			<form action=""  method="post" >
				<div id="comment">
					<textarea id="commContent" name="model.content" ></textarea>
					<div class="comSta">请最少填写21个字，才能回复哦。</div>
					<input type="button"  id="commSub" disabled value="评论"  />
					<input type="hidden" name="NoteId" value="${model.id}" />
				</div>
			</form>
			<div id="lookCom">
			<div class="MainMou">
			<s:iterator value="recordList" >
				<div class="comMou">
					<form autocomplete="off">    
						<input type="hidden" class="FClick" value="true" />
						<input type="hidden" class="authorHead" value="${session.user.head}" />
						<input type="hidden" class="authorName" value="${session.user.username}" />
						<input type="hidden" class="notecommId" value="${id}" />
						<input type="hidden" class="firstClick" value="true" />
						<input type="hidden" class="replyPageNum" value="1" />
					</form>
						<img class="headImg" src="head/${username}/${userhead}"></img>
						<div class="content">
							<div class="top">
								<div class="name"><a href="frontUser_findPersonInfo.action?model.id=${authorId }">${username }</a></div>
								<div class="date">发表于${postTime }</div>
							</div>
							<div class="substance">${content}</div>
							<div class="replyMou">
								<ul>
									<li id="checkrep" index="${replnum}">查看回复(${replnum})</li>
									<li class="nowLi">发表回复</li>
								</ul>
								<div class="replyCon">
									<div class="repMore" style="display: none">更多回复</div>
								</div>
								<div class="replyPub" style="display: block">
										<textarea class="repcontent"></textarea>
										<div class="repHint">请最少填写20个字，才能回复哦。</div>
										<input type="button" class="repbtn" value="回复" onfocus="this.blur()" disabled="disabled" />
								</div>
							</div>
						</div>
						</div>
				</s:iterator>
					</div>
					<div class="moreComm" >更多评论</div>
			</div>
		</div>
	</div>
	<div class="footer0"></div>
	<jsp:include flush="true" page="footer2.jsp"></jsp:include>
	<jsp:include flush="true" page="returnTop.jsp"></jsp:include>
</body>
</html>
