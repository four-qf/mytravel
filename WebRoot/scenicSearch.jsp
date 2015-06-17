<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>景点景区查询页面</title><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<link rel="stylesheet" type="text/css" href="css/other.css"></link>
		<link rel="stylesheet" type="text/css" href="css/scenic.css"></link>
		<script src="formerJs/jquery-1.7.min.js" type="text/javascript"></script>
		<script src="formerJs/scenicSearch.js" type="text/javascript"></script>
		<script type="text/javascript">
		//正则获取url参数
			function getQueryString(name) {
			    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
			    var r = window.location.search.substr(1).match(reg);
			    if (r != null) return unescape(r[2]); return null;
			    }
			window.onload=function(move)
			{	
				var passV= $("#frontName").attr("value");
				var arr = null;
				var createType=2;
				if(getQueryString("frontType")=="level")
				{arr = $("#levelSql li span");}
				else if(getQueryString("frontType")=="city")
				{arr = $("#positionSQl li span");}
				else if(getQueryString("frontType")=="scenicClass")
				{arr = $("#categorySQL li span");createType = 3;}
				if(arr!=null)
				for(var i=0;i<arr.length;i++ )
				{
					if($(arr[i]).attr("index")==(passV))//schLi
						{
							$(arr[i]).parent().addClass("schLi")
								.find("div").addClass("schDiv");
							$(".queSel ul").append(creatLi(Wmove,$(arr[i]).html(),$(arr[i]).parent(),createType));
						}
				}
			}
		</script>
	</head>
	<body>
	<div id="getRootUrl" style="display: none">${pageContext.request.contextPath}/</div>
	<input type="hidden" value="${pageNum}" id="pageNum"/>
		<jsp:include flush="true" page="header.jsp"></jsp:include>
		<div class="topline"></div>
		<div id="queMain">
			<div class="queTop"></div>
			<div class="queSch" style="border-top: 2px solid #4dafdc">
			<input type="hidden" id="frontName" name="frontName" value="${frontName}" />
				<div class="schLeft">级别：</div>
				<ul id="levelSql">
					<li><span index="世界级">世界级</span><div></div></li>
					<li><span index="国家级">国家级</span><div></div></li>
					<li><span index="省级">省级</span><div></div></li>
					<li><span index="市级">市级</span><div></div></li>
					<li><span index="其他">其他</span><div></div></li>
				</ul>
				<div style="clear: both;"></div>
			</div>
			<div class="queSch">
				<div class="schLeft">类别：</div>
				<ul id="categorySQL">
					<s:iterator value="#application.scenicClassList"><!-- scenicClassList若在栈值中则可以使用size() 放在application中则不能 -->
						<li ><span index=${id} >${name}</span><div></div>
							<s:if test="children.size()>0">
								<dl class="smallSch">
									<dd class="schPoi"></dd>
									<dd class="line"></dd>
									<s:iterator value="children">
										<dd><a href="javascript:void(0) ;" index=${id} ></a><span class="smaSpan" index=${id}>${name}</span></dd>
									</s:iterator>
								</dl>
							</s:if>	
						</li>
					</s:iterator>
				</ul>
				<div style="clear: both;"></div>
			</div>
			<div class="queSch" style="border-bottom: none;">
				<div class="schLeft">地理位置：</div>
				<ul id="positionSQl">
					<s:iterator value="#application.cityList">
					<li><span index=${name}>${name}</span><div></div></li>
					</s:iterator>
				</ul>
				<div style="clear: both;"></div>
			</div>
			<div class="queSel">
				<div class="name">您的选择：</div>
				<div class="clear"><a>清空</a></div>
				<ul></ul>
				<div class="line"></div>
			</div>	
			<div class="queHot">
				<div class="hotName">热门景点</div>
				<dl>
					<s:iterator value="#hotScenicList" status="st">
					<dd>
						<a href="frontscenic_detailScenic.action?model.id=${id }"><img src="${pageContext.request.contextPath}/${fn:split(imageUrl,';')[0]} "/></a>
						<div class="imgName"><a href="frontscenic_detailScenic.action?model.id=${id }">${name }</a></div>
						<span>${description}</span>
					</dd>
					</s:iterator>
				</dl>
			</div>
			<!-- 热门景区结束 -->
			<!-- 查询景区开始 -->
			<ul id="queBkgrd">
				<s:iterator value="recordList">
				<li>
					<img src="${pageContext.request.contextPath}/${fn:split(imageUrl,';')[0]} "/>
					<div class="queCon">
						<div class="queLiBk"></div>
						<a href="frontscenic_detailScenic.action?model.id=${id }" class="spotName">${name }</a>
						<span class="spotText"><p>${fn:trim(description)}</p></span>
						<a href="frontscenic_detailScenic.action?model.id=${id }" class="spotPal">进入景点 </a>
						<a href="frontnote_getAboutNoteByScenicId.action?scenicId=${id}" class="spotPal">查看相关游记</a> 
					</div>
				</li>
				</s:iterator>
			</ul>
			<div style="clear:both;"></div>
			<div class="queMore">
				<div class="queGet">
  					<img src="formerImage/getMoreImg.png"/>
  					<a href="javascript: ;">点击显示更多...</a>
  				</div>
			</div>
		</div>
		<div class="footer0"></div>
  		<jsp:include flush="true" page="footer2.jsp"></jsp:include>
		<jsp:include flush="true" page="returnTop.jsp"></jsp:include>
		<div class="curHot">
			<img />
			<div class="curName"></div>
			<div class="curText"></div>
		</div>
	</body>
</html>
