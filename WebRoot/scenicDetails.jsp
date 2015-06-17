<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
		<title>景点景区详情页面</title>
		<jsp:include  page="header.jsp"></jsp:include><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/other.css"></link>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/scenic.css"></link>
		<script src="formerJs/scenicDetails.js" type="text/javascript"></script>
		<style>
			.sccodeDiv{
				width: 200px;
				float: left;
				height: 30px;
				color: #999;
				text-align: center;
				line-height: 28px;
				font-size: 14px;
				margin-top: 10px;
				cursor: pointer;
				outline: none;
				background: #FFF;
				margin-left: 100px;
			}
			.sccodeDiv img{float: left;}
			.sccode
			{
				position: relative;
				height: 25px;
				width: 65px;
				top: 0px;
				float: left;
				margin-right: 10px;
			}
		</style>
	</head>
		<script type="text/javascript">
			$(function(){
				var imgs =$(".rightMou").find("img");
				for(var i=0;i<imgs.length;i++)
					{
						$(imgs[i]).attr("src",$("#root").val()+"/"+$(imgs[i]).attr("src"));
					}
			});
		</script>
	<body>
		<div id="needData">
			<input type="hidden" value="${session.user.username }" id="Ausername" />
			<input type="hidden" value="${session.user.head }" id="userhead" />
		</div>
		<input type="hidden" value="${pageContext.request.contextPath}" id="root" />
		<input type="hidden" value="${pageNum }" id="pageNum1" />
		<input type="hidden" value="1" id="pagenumComm" autocomplete="off" />
		<input type="hidden" value="${model.id }" id="scenicId" />
		<div class="topline"></div>
		<div id="detMain">
			<div class="detImg">
				<div class="detName">${model.name}
					<div class="rightName">所在城市:${model.city.name }</div>
				</div>
				<div class="largeImg">
					<img src="${pageContext.request.contextPath}/${fn:split(model.imageUrl,';')[0]}" />
					<img src=""/>
				</div>
				<div class="smallImg">
					<div class="leftRet"></div>
					<div class="ulDiv">
						<ul>
							<s:iterator value="#imgUrlList" id="idT" >
								<li><img src="${pageContext.request.contextPath}/${idT}"/></li>
							</s:iterator>
						</ul>
					</div>
					<div class="rightRet"></div>
				</div>
			</div>
			<div class="detCon">
				<ul class="topSel">
					<li class="curSel"><div>景区介绍</div></li>
					<li><div>交通线路</div></li>
					<li><div id="updateComm" index="${model.realCommentNum }">驴友点评(${model.realCommentNum })</div></li>
					<li><div>景点游记(${model.notenum})</div></li>
				</ul>
				<div class="detText" style="word-break:break-all;  overflow:auto;">
					${model.description }
				</div>
				<div class="detTra" style="word-break:break-all;  overflow:auto;"> 
					${model.scenicLine }
				</div>
				<div class="detCom">
					<ul class="comSel">
						<li class="selCur" id="comm1">驴友点评</li>
						<li id="comm2">我要评价</li>
					</ul>
					<div class="comCon" id="comCon">
						<ul>
						</ul>
						<div class="conGet" style='display: block;z-index:100' id="conGet">更多评价</div>
					</div>
					<div class="myCom">
<!-- 向前缩进了 -->
<s:form action="frontscenicComment_add.action" method="post" id="logined" style="display: none">
	<s:hidden name="scenicId" id="scenicId" value="%{model.id}"></s:hidden>
	<ul class="selGo" id="CommentStatus">
		<li><a href="javascript: ;"></a><span>没去过</span></li>
		<li><a href="javascript: ;" ></a><span>打算去</span></li>
		<li><a href="javascript: ;" class="spaSel"></a><span>已去过</span></li>
		<li><a href="javascript: ;"></a><span>打算再去</span></li>
		<input type="hidden" name="model.status" value=2 id="status" />
	</ul>
	<div class="mySoce">
		<div class="myLeft">
			<span>5分</span>
			<div>总体评价；</div>
			<dl class="star5"><dd></dd><dd></dd><dd></dd><dd></dd><dd></dd></dl>
			<input type="hidden" id="totalNum" name="model.totalNum" value="5" />
		</div>
		<ul class="myRight" id="RightComment">
			<li>人气：<dl class="star5"><dd></dd><dd></dd><dd></dd><dd></dd><dd></dd></dl></li>
			<input type="hidden" id="peopNum" name="model.peopNum" value=5 />
			<li>服务：<dl class="star5"><dd></dd><dd></dd><dd></dd><dd></dd><dd></dd></dl></li>
			<input type="hidden" id="serviceNum" name="model.serviceNum" value=5 />
			<li>环境：<dl class="star5"><dd></dd><dd></dd><dd></dd><dd></dd><dd></dd></dl></li>
			<input type="hidden" id="environmentNum" name="model.environmentNum" value=5 />
			<li>交通：<dl class="star5"><dd></dd><dd></dd><dd></dd><dd></dd><dd></dd></dl></li>
			<input type="hidden" id="transportNum" name="model.transportNum" value=5 />
		</ul>
	</div>
	<div class="leftName" >点评内容：</div>
	<textarea class="rightTxet" name="model.content" id="rightTxet"></textarea>
	<div class="retext">您尚需20个字才能提交!</div>
	<div class="sccodeDiv">
		<input class="sccode" type="text" placeholder="请输入验证码" id="sccode">
		<img id="codeImg" onclick="document.getElementById('codeImg').src = 'scencomenCode.action?time='+(new Date()).getTime()" src="${pageContext.request.contextPath }/scencomenCode.action"/>
	</div>
	<input type="button" id="ScenicSub" class="myQue" disabled="true" value="发表评论"/>
	<div style="clear:both;"></div>
</s:form>
	<div id="cliLogin" style="display: none" >
		你还没有登录，请先<a href="frontUser_loginUI.action">登录</a>
	</div>	
<!-- 向前缩进了 -->
					</div>
				</div>
				<div class="detRela" id="detRela">
					<ul>
					</ul>
					<div class="relaGet"  id="relaGet">更多游记</div>
				</div>
			</div>
			<div class="detRight">
				<div class="rightMou">
					<div class="rightName">景点评分</div>
					<div style="clear: both;"></div>
					<div class="rightSco">
						<span>${fn:substring(model.totalScore,0,4) }分</span>
						<img src="formerImage/star5.png"/>
					</div>
					<ul class="rightCom"> 
						<li>人气：<img src="formerImage/star5.png"/> <span>${fn:substring(model.peopleScore,0,4)}</span></li>
						<li>服务：<img src="formerImage/star5.png"/><span>${fn:substring(model.serviceScore,0,4)}</span></li>
						<li>环境：<img src="formerImage/star5.png"/><span>${fn:substring(model.environmentScore,0,4)}</span></li>
						<li>交通：<img src="formerImage/star5.png"/><span>${fn:substring(model.transportScore,0,4)}</span></li>
					</ul>
				</div>
				<div class="rightMou">
					<div class="rightName">相关景点</div>
					<div style="clear: both;"></div>
					<ul class="rightSuo">
							   
						<s:iterator value="aboutScenicList" id="aboutScenicByCity" status="status" > 
							<li>
								<a href="frontscenic_detailScenic.action?model.id=${aboutScenicByCity.id }"><img src="${fn:split(imageUrl,';')[0]}"/></a>
								<a href="frontscenic_detailScenic.action?model.id=${aboutScenicByCity.id }">${aboutScenicByCity.name }</a>
								<div class="suoNei" style="word-break:break-all;  overflow:auto;">
									${aboutScenicByCity.description}...
								</div>
							</li>
						 </s:iterator>
					</ul>
				</div>
			</div>
			<div style="clear:both;"></div>
		</div>
		<div class="footer0"></div>
  		<jsp:include flush="true" page="footer2.jsp"></jsp:include>
		<jsp:include flush="true" page="returnTop.jsp"></jsp:include>
	</body>
</html>