<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>travel</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.tabfont01 {
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}

.font051 {
	font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}

.font201 {
	font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}

html {
	overflow-x: auto;
	overflow-y: auto;
	border: 0;
}
-->
</style>
<link href="${pageContext.request.contextPath}/zhjd_htglxt/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js"> </script>
<script type="text/javascript">
function is(num) {
	if (num == 1) {
		$("#type1").show();
		$("#type2").hide();
	} else {
		$("#type2").show();
		$("#type1").hide();
	}
}
</script>
</head>
<script language="javascript" >
var Arr=null ;
function dodelete(arr)
{	
	if (confirm("确认删除放入景区回收站？")==true)
		{
			$.ajax({
				url:"scenic_delete2.action",
				type:"get" ,
				data:{"model.id":arr},
				success: function(data){
					alert(data);
					if(data==1)
					{
						if($("#scenicList"+arr).attr("title")==arr)
						{$("#scenicList"+arr).remove();}
					}else if(data==-1){alert("操作失败！");}
					
				}
			});
		}
}
function callback()//这里不能传递参数
{	var t = 'scenicList'+Arr;
	var node = document.getElementById(t); 
	if(xmlHttp.readyState==4)
	{	
		if(xmlHttp.status==200)
		{	var s = xmlHttp.responseText;
			if (s.trim()=='true')
			{
				if (node.title.value==Arr);//out.print(jg);
					{
						$(node).remove();
					}
			}
		}
	}
}

	function selectAll() {
		var obj = document.fom.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "delid") {
				obj[i].checked = true;
			}
		}
	}

	function unselectAll() {
		var obj = document.fom.elements;
		for ( var i = 0; i < obj.length; i++) {
			if (obj[i].name == "delid") {
				if (obj[i].checked == true)
					obj[i].checked = false;
				else
					obj[i].checked = true;
			}
		}
	}
function plDelete(){
	if (confirm("确认批量删除放入景区回收站？")==true)
	{
		var flag=false;
		var obj = document.getElementsByName('delid');
			var j=0;
			for (var i=0;i<obj.length;i++)
				{
					if(obj[i].checked==true)
						{	
						flag = true;
						}
				}
		if (flag==true)
		{
			var obj = document.getElementsByName('delid');
			var arrIds = new Array;
			var j=0;
			for (var i=0;i<obj.length;i++)
				{
					if(obj[i].checked==true)
						{	
							arrIds[j++] = obj[i].id;
						}
				}
			$.ajax({
				type : "post",
				url : "scenic_deleteList2.action",
				dataType : "text",
				data : {
					"arrayId" : arrIds.toString()
				},
				success : function(data) {
					if(data==1){
						for (var i=0;i<arrIds.length;i++)
							{
							$('#scenicList'+arrIds[i]).remove();
							}
						alert("把"+arrIds.length+"条记录放入了景区回收站");
					}else if(data==-1){
						alert("操作失败");
					}
				}
				});
			}else{alert("请选择一条数据");}
		}
	}
</SCRIPT>
	<body>
		<form name="fom" id="fom" method="post" action="">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="50">
					</td>
				</tr>
				<tr>
					<td>
						<table id="subtree1" style="DISPLAY: " width="100%" border="0"
							cellspacing="0" cellpadding="0">
							<tr>
								<td>
									<table width="95%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="20">
												<span class="newfont07">选择：<a href="#"
													class="right-font08" onclick="selectAll();">全选</a>-<a
													href="#" class="right-font08" onclick="unselectAll();">反选</a>
												</span>
							<s:a href="javascript: plDelete();" escapeAmp="scenic_deleteList2">批量删除文章
							</s:a>
							<a href="javascript: location.reload();" >刷新</a>
											</td>
										</tr>
										<tr>
											<td height="40" class="font42">
												<table width="100%" border="0" cellpadding="4"
													cellspacing="1" bgcolor="#464646" class="newfont03">
													<tr class="CTitle">
														<td height="22" colspan="12" align="center"
															style="font-size: 16px">
															景区详细列表
														</td>
													</tr>
													<tr bgcolor="#EEEEEE">
														<td width="3%" align="center" height="30">	选择
														</td>
														<td width="5%">创建时间
														</td>
														<td width="5%">景区标题
														</td>
														<td width="5%">	地理位置
														</td>
														<td width="5%">	级别
														</td>
														<td width="10%">	类别
														</td>
														<td width="3%">	点击数
														</td>
														<td width="3%">	评论数
														</td>
														<td width="5%">	支持/反对
														</td>
														<td width="5%">	景区首张图片
														</td>
														<td width="5%">	创建人
														</td>
														<td width="16%">操作
														</td>
													</tr>
													<s:iterator value="recordList" status="st">
														<tr bgcolor="#FFFFFF" id="scenicList${id}" name="scenicListName" title="${id}">
															<td height="20">
																<label for="${id}">序列${st.index+1}</label>
																<input type="checkbox" name="delid" id="${id}" />
															</td>
															<td>${postTime }
															</td>
															<td>${name}
															</td>
															<td>${city.name}
															</td>
															<td>${level }
															</td>
															<td>
																<s:iterator value="scenicClasses">
																	${name }&nbsp;
																</s:iterator>
															</td>
															<td>${clickNum }
															</td>
															<td>${commentNum }
															</td>
															<td>${goodCount }/${badCount}</td>
															<td>
																<img src="${pageContext.request.contextPath}/${fn:split(imageUrl,';')[0]} "
																		style="width: 100%; height: 50px;" />
																		
															</td>
															<td>${author.username}
															</td>
															<td>
																<s:a action="scenic_editUI.action" escapeAmp="scenic_editUI">更新
																	<s:param name="model.id"><s:property value="id"/></s:param>
																</s:a>
																<s:a action="scenicComment_getListBySceniId.action" escapeAmp="scenicComment_getListBySceniId">管理评论
																	<s:param name="scenicId"><s:property value="id"/></s:param>
																</s:a>
																<a href="${pageContext.request.contextPath}/frontscenic_detailScenic.action?model.id=${id}" target="_blank">查看详情 </a>
																<s:a href="javascript: dodelete(%{id});" escapeAmp="scenic_delete2">删除
																</s:a>
															</td>
														</tr>
													</s:iterator>
												</table>
											</td>
										</tr>
									</table>
<script LANGUAGE="JavaScript">
//评论管理 showMangeCommopenwin
function showMangeCommopenwin(id) {
	window.open(
					"scenicComment_getListBySceniId.action?scenicId=" + id,
					"newwindow",
					"height=600, width=800, top=10,left=250,toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no");
}
</script>
									<table width="95%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="6"><img src="<%=basePath%>zhjd_htglxt/images/spacer.gif"  width="1" height="1"  /></td>
										</tr>
										<tr>
											<td height="33" >
													<!--分页信息-->
													<%@ include  file="/pageView.jspf"%>
													<s:form action="scenic_list.action" id="pageForm"></s:form>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
						<input type="hidden"
							value="<s:property value="pageModel.pageNo"/>" name="pageNo" />
						</form>
						
	</body>
</html>
