<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
	<head>
		<title>导航菜单</title>
			<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js"> </script><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<script type="text/javascript">
			function menuClick( menu ){
				$(menu).next().toggle();
			}
		</script>
		<style type="text/css">
			body{
				margin-left: -20px;border-right: 1px solid black;
				background-color: #eeeeee;
			}
			#MenuUl li{list-style: none;}
			#MenuUl .level1{
				font-size: 14px;
				margin: 5px;
				padding: 2px;
				line-height: 15px;
				
			}
			#MenuUl .level1:hover{
				cursor: pointer;
			}
			#MenuUl a{
				text-decoration: none;
				
			}
			#MenuUl .level2 a:hover{
				color: red;
			}
			#MenuUl .level2{
				margin: 2px;
				margin-left: -20px;
				padding: 2px;
				line-height: 15px;
				font-size: 12px;
			}
		</style>
	</head>
	<body >
		<div id="Menu">
			<ul id="MenuUl">
				<%-- 显示一级菜单 --%>
				<s:iterator value="#application.topPrivilegeList">
					<s:if test="#session.user.hasPrivilegeByName(name)">
					<li class="level1">
						<div onClick="menuClick(this);" class="level1Style">
							${name}
						</div>
						<ul style="display:none" class="MenuLevel2" id="aa">
							<%-- 显示二级菜单 --%>
							<s:iterator value="children">
								<s:if test="#session.user.hasPrivilegeByName(name)">
								<li class="level2">
									<div class="level2Style">
										<a target="mainFrame" href="${pageContext.request.contextPath}${url}.action"> ${name}</a>
									</div>
								</li>
								</s:if>
							</s:iterator>
						</ul> 
					</li>
					</s:if>
				</s:iterator>
			</ul>
		</div>
	</body>
</html>