<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新闻管理</title>
</head>
<body>
     <center> <h2>新闻发布</h2></center>
     <hr/>
     <table width="99%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
	     <s:form action="news_save" method="post" enctype="multipart/form-data">
	     <br/>
	     
	      <input type="hidden" name="newsid" value="${model.newsid}"/>
	        <tr>
	         <td nowrap align="right" width="10%">
			       新闻标题:
			 </td>
			 
	         <td width="41%">
	     	    <s:textfield name="model.newstitle" label="newstitle" size="40" >
	     	    </s:textfield>
	     	</td>
	     	
	       </tr>
	       <tr>
	         <td><br/></td><td>
	       </tr>
	       <tr>
				<td nowrap align="right" height="120px">
					新闻正文：
				</td>
				<td colspan="5">
					<s:textarea tabindex="container" cssClass="text" id="container0" name="model.newscontent" rows="5" cols="80" cssStyle="width: 795px; height: 320px;">
					</s:textarea>
					
				</td>
				
			</tr>
	        
	        <tr>
	         <td><br/></td><td>
	       </tr>
	       
	        <s:if test="model.attachment != null">
	         <tr>
	             <td nowrap align="right" width="10%"> 新闻附件：</td>
	             <td width="41%"> ${model.attachment.name }</td>
	         </tr>
	         <tr>
	         <td><br/></td><td>
	         </tr>
	        </s:if>
	       
	       <tr>
	      
	       	<td nowrap align="right" width="10%">新闻附件：</td>
	       	<td width="41%">
	       		<s:file name="attachment" label="attachment" ></s:file>
	       	</td>
	       </tr>
	    </table> 
	    <br/>  
	       <hr/>
	       <br/>
	       <center>
	          <s:submit value="发布" ></s:submit>&nbsp;&nbsp;
	          <s:reset value="取消"></s:reset>
	       </center>
	     </s:form>
     
</body>
</html>