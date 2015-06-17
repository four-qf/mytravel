<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>智慧旅游研究基地</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<s:if test="#session.user==null">
	<% response.sendRedirect("user_htLoginUI.action"); %>
</s:if>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="59" background="../images/top.gif"><table width="99%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="1%"><img src="../images/logo.gif" width="557" height="59" border="0" /></td>
        <td width="64%" align="right" style="font-size:12px;vertical-align:bottom;"><a target="_blank"  href="<%=basePath%>index.action">访问首页</a></td>
        <td width="24%">
        	<table width="90%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="25%" rowspan="2"><img src="../images/ico02.gif"
										width="35" height="35" /></td>
									<td width="75%" height="22" class="left-font01">您好，${session.user.username}<span
										class="left-font02"><a href="managerInfo.jsp"
											target="mainFrame" style="text-decoration: none;color:#08f;">${sessionScope.admin.username
												}</a></span></td>
								</tr>
								<tr>
									<td height="22" class="left-font01">[&nbsp;<a
										href="<%=basePath%>manager/user_htLoginOut.action" target="_top" class="left-font01">退出</a>&nbsp;]
									</td>
								</tr>
							</table>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
