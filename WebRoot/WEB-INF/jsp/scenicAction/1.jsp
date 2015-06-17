<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=basePath%>scenicScript/tj/css/style.css" media="screen" type="text/css" />
</head>
<div id="subFrame" class="subFrame">
	<s:iterator value="list">
		<div class="skillbar clearfix "  data-percent="${percent*100 }+%">
			<div class="skillbar-title" style="background: #d35400;"><span>${name}</span></div>
			<div class="skillbar-bar" style="background: #e67e22;"></div>
			<div class="skill-bar-percent">${percent*100 }+%</div>
		</div> <!-- End Skill Bar -->
	</s:iterator>
</div>
  <script src='<%=basePath%>scenicScript/tj/js/jquery.js'></script>
  <script src="<%=basePath%>scenicScript/tj/js/index.js"></script>
