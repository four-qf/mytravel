<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"  %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻管理</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(".delete").click(function(){
			var flag = confirm("确定要删除");
			if(flag){
				var $tr = $(this).parent().parent();
				var url = this.href;
				var args = {"time":new Date()};
				
				//向后台发出请求
				$.post(url, args, function(data){
					if(data == "1"){
						alert("删除成功");
						$tr.remove();
					}else{
						alert("删除失败");
					}
				});
			}
				
			return false;
		});
	})
</script>
</head>
<body>

	<center><h2>新闻列表</h2></center>
	<hr size="15" color="#464646"/>
	<center>
	<s:if test="#session.newslist == null || #session.newslist.size() == 0">
		<font color="red" size="20">亲 没有可看信息哦！！！</font>
	</s:if>
	<s:else>
	
	<table border="1" width="95%" align="center">
		<tr height="40px">
			<th align="center" width="55%">新闻标题</th>
			<th width="20%">发布时间</th>
			<th width="10%">修改内容</th>
			<th width="10%">删除</th>
		</tr>
	     	<s:iterator value="#session.newslist" begin="#session.pageNow" end="#session.pageMax">
			    <tr height="40px">
			    	<td>${newstitle}</td>
			    	<td>${createtime }</td>
			    	<td><a href="news_input.action?newsid=${newsid }">修改内容</a></td>
			    	<td><a href="news_delete.action?newsid=${newsid}" class="delete">删除</a></td>
			    </tr>
			</s:iterator>
		
	</table>
	
	</s:else>
	<hr/>
	<s:if test="#session.pageNum > 1">
	    [ <a href="news_page.action?pageNum=${session.pageNum-1 }">上一页</a> ]
	</s:if>
	<s:iterator var="i" begin="1" end="#session.pageCount">
	 [<a href="news_page.action?pageNum=${i }"> ${i} </a>]
	</s:iterator>
	
	<s:if test="#session.pageNum < #session.pageCount">
		[ <a href="news_page.action?pageNum=${session.pageNum+1 }" >下一页</a>]
	</s:if>
	
	</center>
	<div align="right">${session.pageNum } / ${pageCount }</div>
	
	
</body>
</html>