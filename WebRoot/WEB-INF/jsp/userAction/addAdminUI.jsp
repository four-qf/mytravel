<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加管理员页面</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js"> </script>
	<SCRIPT type="text/javascript">
		$(function(){
			
		});
	</SCRIPT>
  </head>
  <body>
  	<s:fielderror></s:fielderror>
  	<s:form action="user_%{id!=null?'editAdmin':'addAdmin'}.action" method="post">
  		<s:hidden name="model.id"></s:hidden>
   		<table>
   				<tr>
   					<td>用户名2</td><td><s:if test="model.id!=null"><s:textfield disabled="true"  name="model.username"  /></s:if><s:else><s:textfield   name="model.username"  /></s:else></td>
   					<td>真实姓名</td><td><s:textfield name="model.realName"  /></td>
   					<td>权限角色</td>
   						<td><s:select name="roleIds" multiple="true" size="10" cssClass="SelectStyle"
                        		list="#roles" listKey="id" listValue="name"/>
                        </td>
   				</tr>
		</table>
			<s:submit></s:submit><s:a href="javascript:history.go(-1);">返回上一层</s:a>
		</s:form>
  </body>
</html>
