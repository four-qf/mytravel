<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>智慧旅游研究基地后台管理</title>
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
<link href="<%=basePath%>htSource/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>htSource/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=basePath%>htSource/js/login.js"></script>
</head>
<body onkeydown="if (event.keyCode==13) {document.getElementById('enter').click();}">
<input type="hidden" id="root" value="<%=basePath%>" />

<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="147" background="<%=basePath%>htSource/images/top02.gif">
    <img src="<%=basePath%>htSource/images/top03.gif" width="776" height="147" /></td>
  </tr>
</table>
<table width="562" border="0" align="center" cellpadding="0" cellspacing="0" class="right-table03">
  <tr>
    <td width="221">
    <table width="95%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
          <tr>
            <td align="center"><img src="<%=basePath%>htSource/images/ico13.gif" width="107" height="97" /></td>
          </tr>
          <tr>
            <td height="40" align="center">&nbsp;</td>
          </tr>
        </table>
       </td>
        <td><img src="<%=basePath%>htSource/images/line01.gif" width="5" height="292" /></td>
      </tr>
    </table></td>
    <td>
    <form action="manager/user_htLogin.action" method="post" id="loginForm">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    	<tr><td><span style="color: red;"><s:fielderror></s:fielderror> </span></td></tr>
      <tr>
        <td width="31%" height="35" class="login-text02">用户名称：<br /></td>
        <td width="69%"><input name="model.username" type="text" size="30" id="user"/></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">密　码：<br /></td>
        <td><input name="model.password" type="password" size="30" id="pwd"/></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">验证图片：<br /></td>
        <td><img id="num" src="manager/user_code.action"  width="109px"  height="40px" /> 
            <a class="login-text03" onclick="document.getElementById('num').src = 'manager/user_code.action?'+(new Date()).getTime()">看不清楚，换张图片</a></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">请输入验证码：</td>
        <td><input name="sccode" type="text" size="30" id="chknumber"/></td>
      </tr>
      <tr>
        <td height="35">&nbsp;</td>
        <td><input type="button" class="right-button01" value="确认登陆" onclick="sub()"  id="enter"/>
          <input type="reset" class="right-button02" value="重 置"  /></td>
      </tr>
    </table>
    </form>
    </td>
  </tr>
</table>
</body>
</html>