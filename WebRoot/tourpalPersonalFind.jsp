<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>${session.user.username }个人游记列表界面</title>
		<link href="css/tourpal.css" rel="stylesheet" type="text/css"/>
		<link href="css/other.css" rel="stylesheet" type="text/css"/>
		<jsp:include flush="true" page="header.jsp"></jsp:include>
		<script type="text/javascript" src="formerJs/tourpalFind.js"></script>
		<style type="text/css">
			#location li .LiStyle{
				background-color: rgb(77, 174, 221);
				 color: rgb(255, 255, 255);
				  background-position: initial initial; 
				  background-repeat: initial initial;
				}
			#showMore{
				background-color: rgb(235,235,235);
				width: 220px;
				height: 40px;
				margin-top: 20px;
				line-height: 40px;
				font-size: 14px;
				text-align: center;
				color: rgb(153,153,153);
				cursor: pointer;
				letter-spacing: 3px;
				margin-left: 510px;
			}
			#showMore:hover{
				background-color: #0070D1;
				color: white;
			}
			#noteContent{
				top: 0px;
				height:auto;
				z-index: 1000;
				background-color: white;
				filter:alpha(Opacity=50);-moz-opacity:0.5;opacity: 0.5;
			}
			#noteContent .name,#noteContent .date{ color: #000088;
			 }
			 .name{
			 	cursor: pointer;
			 	z-index: 99;
			 }
			 .name:hover{
			 	background: yellow;
			 }
		</style>
	</head>
	<body>
		<input type="hidden" id="pagenum" value="2" />
		<div class="topline"></div>
		<div id="findMain">
			<div class="top">
				<div class="name"></div>
				<div class="rollHot">
				<ul>
					<s:iterator value="recomScenics">
						<li><a href="frontscenic_detailScenic.action?model.id=${id}">
						<img title="${name}" src="${pageContext.request.contextPath}/${imageUrl}" /></a></li>
					</s:iterator>
				</ul>
				</div>
			</div>
			<div class="left">
			<div class="findMou">
					<div class="name" style="cursor: default;background-color: #0070D1;color:white;"><span >${session.user.username }个人游记列表界面</span></div>
				</div>
				<div class="findMou" id="location">
					<div class="name"><span>地理位置</span><span class="addMore">-</span></div>
					<ul >
						<s:iterator value="#application.cityList">
							<li z="${id}" class=LiStyle>${name}</li>
						</s:iterator>
					</ul>
				</div>
				<div class="findMou">
				</div>
			</div>
			<div class="right">
				<ul id="spotImgBg"></ul>
				<ul id="spotImg">
				<s:iterator value="recordList">
					<li>
						<dl class="spotCss3">
							<dd></dd>
							<dd>
								<img src="${pageContext.request.contextPath}/${imageUrl}" />
								<span id="noteContent">
									<div class="name" onclick="window.location.href='frontnote_detail.action?model.id=${id}'">${title}</div>
									<div class="date"><s:date name="postTime" format="yyyy-MM-dd HH:mm" /></div>
								</span>
							</dd>
						</dl>
					</li>
				</s:iterator>
					
				</ul>
				<script type="text/javascript">
				//选择城市 location
				$(function(){
					$("#location ul li").click(function(){
						if($(this).attr("index")=="0")
						{
							$(this).attr("index","1");
							$(this).css({"background-color": "rgb(77, 174, 221)", "color": "rgb(255, 255, 255)","background-position": "initial initial", "background-repeat": "initial initial"});
							//ajax 先清空页数为1 和显示更多 清空数据
							$("#spotImg").html("");
							$("#pagenum").attr("value","1");
							$("#showMore").css("display","block");
							$("#showMore").click();
						}
						else{
							$(this).attr("index","0");
							$(this).removeAttr('style');
							//ajax 先清空页数为1
							$("#spotImg").html("");
							$("#pagenum").attr("value","1");
							$("#showMore").css("display","block");
							$("#showMore").click();
						}
					});
				});
				//显示更多ajax
				$(function(){
					$("#showMore").click(function(){
						//首先获取页数pagenum cityId
					var pagenum = $("#pagenum").val();
					var ids =$("#location ul").find("li[index=1]");
					var cityArray="";
					for(var i=0;i<ids.length;i++)
						{
							if(i!=ids.length-1)
								{
									cityArray=cityArray+$(ids[i]).attr("z")+",";
								}else
									{
										cityArray=cityArray+$(ids[i]).attr("z");
									}
						}
						if( pagenum!=null)
						{
							$.ajax({
								url: "frontnote_userlistajax.action",
								type: "get",
								data:{"pageNum":pagenum,
									"cityArray": cityArray
								},
								success:function(data){
									var data1 =data.substring(0,4);
									if(data1=="true")
									{
										//页数加一
										$("#pagenum").attr("value",parseInt($("#pagenum").val())+1);
										data2 = eval(data.substring(4,data.length))
										//返回数据条数小于10则隐藏 更多
										if(data2.length<10)
										{
											$("#showMore").css("display","none");
										}
										$("#spotImg li").last().css("marginBottom","0px")
														.prev().css("marginBottom","0px");
										
										 for(var i=0;i<data2.length;i++)
										 $("#spotImg").append('<li><dl class="spotCss3"><dd></dd><dd>' +
										 '<a href=frontnote_detail.action?model.id='+data2[i].id+'>' +
										 '<img src=${pageContext.request.contextPath}/'+data2[i].imageUrl+' /></a><span id="noteContent">' +
										 '<div class="name">'+data2[i].title+'</div>' +
										 '<div class="date">'+new Date(data2[i].postTime).toLocaleString()+'</div></span></dd></dl></li>');
										$("#spotImg li").last().css("marginBottom","20px")
											.prev().css("marginBottom","20px");
										 //增加背景 清除
										$("#spotImgBg li").remove();
										//设置li背景
											var spotLi = $("#spotImg li");
											for(var i=0; i<spotLi.length; i++){
												$("#spotImgBg").append("<li>");
											}
											$("#spotImgBg li").last().css("paddingBottom","20px")
															.prev().css("paddingBottom","20px");
																			}
																		}
							});
						}
						
					});
	//点击返回事件
	var Timer=null;
	var returnTop = document.getElementById("returnTop");
	returnTop.onclick=function(){
		if(document.body.scrollTop == 0){
			Timer=setInterval(function(){
				if(document.documentElement.scrollTop<70){
					document.documentElement.scrollTop=0;
					clearInterval(Timer);
				}else{
					document.documentElement.scrollTop=document.documentElement.scrollTop-70;
				}
			},30);
		}else{
			Timer=setInterval(function(){
				if(document.body.scrollTop<70){
					document.body.scrollTop=0;
					clearInterval(Timer);
				}else{
					document.body.scrollTop=document.body.scrollTop-70;
				}
			},30);
		}
	};
					});
	
				</script>
			</div>
			<div style="clear:both;"></div>
			<div id="showMore">显示更多</div>
		</div>
		<div class="footer0"></div>
  		<jsp:include flush="true" page="footer2.jsp"></jsp:include>
  		<jsp:include flush="true" page="returnTop.jsp"></jsp:include>
		<div class="substnace" id="loading" style="display: none;">
			<div class="circle1"></div>
			<div class="circle2"></div>
			<div class="circle3"></div>
			<div class="circle4"></div>
		</div>
	</body>
</html>