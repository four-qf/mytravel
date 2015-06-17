<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻中心</title>
</head>
<body>
    <div style="margin-left: 40px;">
   	<s:if test="#session.user==null" >
		<a href="frontUser_loginUI.action" id="userlogin">登录</a>
	</s:if>
	<s:else>
		&nbsp;&nbsp;
		
		<a href="user_personArea.action">${session.user.username}</a>
		中心
	</s:else>
	</div>
    <hr/>

   <div style="margin-left: 130px;" >
    <h1>新闻中心</h1>
   </div> 
   <hr size="15" color="#808080" width="1100px"/>
   
   <center>
   	  <h2>${model.newstitle }</h2>
   	  <hr width="900px"/>
   	   <div style="width:500px">
   	    <div style="float: left">
   	     <font color="blue" size="1">文章来源：${model.userid.username }</font>
   	    </div> 
   	     <div style="float: right">
   	      <font color="blue" size="1" >发布时间：
   	       
   	       <s:date name="model.createtime" format="yyyy-MM-dd hh:mm:ss"/>
   	       
   	      
   	      </font>
   	     </div>
   	    </div> 
   	    <hr width="900px"/>
   	 </center> 
   	 <br/>  
   	    <div style="width: 900px;margin-left:255px">
   	   	  ${model.newscontent }
   	    </div>
   	    <br/>
   	    <br/>
        <div style="margin-left:925px">
                             发表时间：
           <s:date name="model.createtime" format="yyyy-MM-dd"/>
        </div>
        <hr width="900px"/>
        <if test=" model.attachment != null ">
        <div style="width: 900px;margin-left:240px">
        	附件： <a href="frontnews_getDownloadAttachment.action?fileName=${session.filename }"> ${session.filename } </a>
        </div>
</body>
</html>