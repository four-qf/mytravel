<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>权限角色列表</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js"> </script>
</head>
<body>
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
           	  权限角色管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="200px">角色名称</td>
                <td width="300px">角色说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        	<s:iterator value="#roleList">
				<tr class="TableDetail1 template">
					<td>${name}&nbsp;</td>
					<td>${description}&nbsp;</td>
					<td><s:a action="role_delete?model.id=%{id}" escapeAmp="role_delete"  onclick="return confirm('确认删除')">删除</s:a>
   						&nbsp;&nbsp;<s:a action="role_editUI?model.id=%{id}" escapeAmp="role_editUI"  >修改</s:a>
						&nbsp;&nbsp;<s:a action="role_setPrivilegeUI?model.id=%{id}" escapeAmp="role_setPrivilegeUI" >设置权限</s:a>
					</td>
				</tr>
			</s:iterator>
        </tbody>
    </table>
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="role_addUI" escapeAmp="role_addUI">新建</s:a>
	  </div>
    </div>
</div>
</body>
</html>


