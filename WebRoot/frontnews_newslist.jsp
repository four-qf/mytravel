<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
   <div style="margin-left: 40px;">
   	<s:if test="#session.user==null" >
		<a href="frontUser_loginUI.action" id="userlogin">登录</a>
	</s:if>
	<s:else>
		&nbsp;&nbsp;&nbsp;<a href="user_personArea.action"> ${session.user.username}</a>
	</s:else>
	</div>
    <hr/>
    <center><h2>新闻中心</h2></center>
    <hr size="10" color="#808080" width="1100px"/>
    <center>
    <s:iterator value="#session.newslist" begin="#session.pageNow" end="#session.pageMax">
       <a href="frontnews_shownews.action?newsid=${newsid }">
	       <div style="height: 6px; width: 1000px" >
	        <div style="float:left">
	          ${newstitle }
	        </div>
	        <div style="float:right">
	          ${createtime }
	        </div>
	       </div>
       </a>
        <br/>
        <hr width="1010px"/>
       
    </s:iterator>
   <s:if test="#session.pageNum > 1">
	    [ <a href="frontnews_page.action?pageNum=${session.pageNum-1 }">上一页</a> ]
	</s:if>
	<s:iterator var="i" begin="1" end="#session.pageCount">
	 [<a href="frontnews_page.action?pageNum=${i }"> ${i} </a>]
	</s:iterator>
	
	<s:if test="#session.pageNum < #session.pageCount">
		[ <a href="frontnews_page.action?pageNum=${session.pageNum+1 }" >下一页</a>]
	</s:if>
	
	<div align="right">${session.pageNum } / ${pageCount }</div>
	
    </center>
  
  
  
</body>
</html>