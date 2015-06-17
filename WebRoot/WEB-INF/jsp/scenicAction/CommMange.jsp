<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'CommMange.jsp' starting page</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-1.8.3.js"> </script>
<!-- 评论管理 -->
 <style type="text/css">
	body div{
		
	}
	.commFrame{ 
		position: absolute; 
		width: 800px;
		height: auto;
		
		float: left;
		background: white;
	}
	
	.commFrame .commContent{
		margin-left: 5px;
		position: relative;
		width: 790px;
		height: 90px;
		border-bottom: 1px dashed red;
	}
	.commContent input{
		position: absolute;
		float: right;
		right: -100px;
		bottom: 0px;
		
		line-height: 10px;
	}
  </style>
  <script type="text/javascript">
  //删除茨评论
	function delComm(_this,_id)
	{
		if(confirm("真的要删除？")){
			//先ajax通信
			$.ajax({
				type : "get",
				url : "scenicComment_delete.action",
				dataType : "text",
				data : {
					"model.id" : _id,
					"scenicId" : ""+$("#scenicId")
				},
				success:function(returnData) {
					if(returnData=="true"){
						_this.parentNode.parentNode.removeChild(_this.parentNode);
						alert("删除了一条评论");
					}else if(returnData==false.valueOf().toString()){
						alert("操作失败");
					}
				}
				});		
		}
	}
  </script>
  </head>
  <body>
  <input type="hidden" id="scenicId" value="${scenicId}" />
  <center> 	<!-- 管理评论 -->
 <div id="commFrame" class="commFrame">
		<s:iterator value="recordList">
			<div id="commContent" class="commContent" >
				${content}
			<input type="button" value="删除此评论"  onclick="delComm(this,${id})" /> 																		
		</div>
		</s:iterator>
		<div> 	<!--分页信息-->
			<%@ include  file="/pageView.jspf"%>
			<form action="scenicComment_getListBySceniId.action?ScenicId=${scenicId}" id="pageForm" method="post">
			</form>
	</div>	
  </div>
 </center> 
  </body>
</html>
