<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<script type="text/javascript" src="<%=basePath%>jslib/jquery-1.8.3.js" >
</script>
<script type="text/javascript" src="<%=basePath%>jslib/user_login.js"></script>
<script type="text/javascript" src="formerJs/login.js"></script><!-- 搜索和返回顶部和弹出层 -->
<style>
<!--
	#KeyWords{
		width: auto;
		height: 22px;
		margin-left: 20px;
	}
	#KeyWords .keyTitle{
		font-size: 14px;
		color: blue;
	}
	#keyW{
		border-bottom: 1px dashed black;
		padding: 2px 2px;
	}
	#keyW:hover{
	 	cursor:	pointer;
	}
-->
</style>
<input type="hidden" value="${session.user.username}" id="usernameCur"/>
<div class="topNav">
	<div class="content">
		<div  id="a" class="mould" style="margin-left: 25px;">
		<s:if test="#session.user==null">
			<a href="frontUser_loginUI.action" id="userlogin">登录</a>
		</s:if><s:else>
			<a href="<%=basePath%>user_personArea.action">${session.user.username}</a>
		</s:else>
			
		</div>
		<div class="mould" id="b">
		<s:if test="#session.user==null">
			<a href="frontUser_registerUI.action">注册</a>
		</s:if><s:else>
			<a href="javascript:void(0)" onclick="userExit()">退出</a>
		</s:else>
		</div>
		
		<div id="search" style="width: 295px;height: 29px;line-height: 29px; display: inline; position: relative">
			<select id="searchSel">
				<s:if test="keywords==null">
					<option title="2" selected>文章搜索</option>
					<option title="3"  >资源搜索</option>
					<option title="4"  >景区搜索</option>
					<option title="5"  >游记搜索</option>
				</s:if><s:else>
				<option title="2" <s:if test="keywords.substring(keywords.length()-1,keywords.length())==2">selected</s:if> >文章搜索</option>
				<option title="3" <s:if test="keywords.substring(keywords.length()-1,keywords.length())==3">selected</s:if> >资源搜索</option>
				<option title="4" <s:if test="keywords.substring(keywords.length()-1,keywords.length())==4">selected</s:if> >景区搜索</option>
				<option title="5" <s:if test="keywords.substring(keywords.length()-1,keywords.length())==5">selected</s:if> >游记搜索</option>
				</s:else>
			</select>
			<form action="" method="post" id="searchForm" style="display: none"> 
			</form>
			<input type="text" name="keywords" id="searchCon" value="<s:property value="keywords!=null?keywords.substring(0,keywords.length()-1):''" />"/>
			<input type="button" value="搜索" id="searchSub"/>
		</div>
		<div id="KeyWords" style="display: inline;">
			<input type="hidden" value="${keywords}" id="keyW2"/>
			<span class="keyTitle">关键词:</span>&nbsp; 
				<span id="keyW" title="点击删除" index="<s:property value="keywords!=null?keywords.substring(keywords.length()-1,keywords.length()):''" />">
						<s:property value="keywords!=null?keywords.substring(0,keywords.length()-1):''" /></span>
		</div>
		<!-- 搜索类型 -->
		<input type="hidden" id="seaClass" value="" />
	</div>
</div>
<div>ssssssssss</div>
