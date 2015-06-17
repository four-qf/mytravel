<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>相关游记列表界面</title>
		<link href="css/tourpal.css" rel="stylesheet" type="text/css"/>
		<link href="css/other.css" rel="stylesheet" type="text/css"/>
		<jsp:include flush="true" page="header.jsp"></jsp:include>
		<script type="text/javascript" src="formerJs/tourpalFind.js"></script>
		<style id="newStyle">
			#showMore{
				width: 220px;
				height: 40px;
				margin-top: 20px;
				line-height: 40px;
				font-size: 14px;
				text-align: center;
				color: red;
				cursor: pointer;
				letter-spacing: 3px;
				border: 1px solid black;
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
		</style>
		
	<style>
	.details p{
		position: relative;
		margin-top: 20px;
	}
	#demo-5 h3 a{
			position: relative;
			margin:20px 0;text-align:center;color:#fff;
			margin-top: 20px;
		}
	
	#container{
		margin:0 auto;
		text-align:left;
		width:760px;
	}
	p.aligncenter{text-align:center;}
	.demobox{
		float:left;
		margin:20px;
		width: 345px;
		height: 210px;
		border:2px solid #555;
		overflow:hidden;
		margin-left: -6px;
		margin-top: -10px;
	}
	.details{
		width: 345px;
		height: 210px;
		background:#000;
		color:#fff;
		text-align:center;
	}

	#demo-5{position:relative;}
	#demo-5 .details{
		opacity: 0.8;
		position:absolute;
		top:0;
		left:0;
		margin-left:-350px;		
		-webkit-transition: margin-left;
		-webkit-transition-timing-function: ease-in;
		-webkit-transition-duration: 150ms;
	}
	#demo-5:hover .details{
		margin-left:0;
	}
	</style>
	</head>
	<body>
		<input type="hidden" id="pagenum" value="2" />
		<div class="topline"></div>
		<div id="findMain" style="height: 500px;">
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
				<div class="findMou" style="height:300px">
					<div class="name"><span ><font color="blue">相关景点:</font>${aboutScenic.name}</span><span class="addMore">-</span></div>
					<ul style="height:200px">
						<li class=LiStyle style="height: auto"><font color="blue">内容简介:</font><br/>${aboutScenic.description}</li>
						<li class=LiStyle style="height: auto"><font color="blue">级别:</font>${aboutScenic.level}</li>
						<li class=LiStyle style="height: auto"><font color="blue">所在城市:</font>${aboutScenic.city.name}</li>
						<li class=LiStyle style="height: auto"><font color="blue">点击数/评论数:</font>${aboutScenic.commentNum}/${aboutScenic.clickNum}</li>
						<li class=LiStyle style="height: auto"><font color="blue">上传作者:</font>${aboutScenic.author.username}</li>
						<li class=LiStyle style="height: auto"><font color="blue">上传日期:</font>${aboutScenic.postTime}</li>
					</ul>
				</div>
				<div class="findMou">
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
								<div id="container">
									<div id="demo-5" class="demobox">
										<img src="${pageContext.request.contextPath}/${imageUrl}" />
										<div class="details">
											<h3><a href="frontnote_detail.action?model.id=${id}">相关游记名:${title}</a></h3>
											<p>撰写时间:<s:date name="postTime" format="yyyy-MM-dd HH:mm" /></p>			
										</div>
									</div>
								</div>
							</dd>
						</dl>
					</li>
				</s:iterator>
					<div style="clear:both;"></div>
				</ul>
				<script type="text/javascript">
					//正则获取url参数
				function getQueryString(name) {
				    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
				    var r = window.location.search.substr(1).match(reg);
				    if (r != null) return unescape(r[2]); return null;
			    }
				//显示更多ajax
				$(function(){
					$("#showMore").click(function(){
						//首先获取页数pagenum scenicId
					var pagenum = $("#pagenum").val();
					var scenicId = getQueryString("scenicId");
						if(scenicId!=null && pagenum!=null)
						{
							$.ajax({
								url: "frontnote_getNoteByScenicId.action",
								type: "get",
								data:{"notePageNum":pagenum,"scenicId":scenicId},
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
										 for(var i=0;i<data2.length;i++)
										 {
											 $("#spotImg").append("<li><dl class=spotCss3><dd></dd><dd><div id=container><div id=demo-5 class=demobox><img src=${pageContext.request.contextPath}/"+data2[i].imageUrl+" />"
											 +"<div class=details><h3><a href=frontnote_detail.action?model.id="+data2[i].id+">相关游记名:"+data2[i].title+"</a></h3><p>撰写时间:"+new Date(data2[i].postTime).toLocaleString()+"</p>	"
											 +"	</div></div></div></dd></dl></li>");
										 }
									}
								}
							});
						}
						
					});
					});
				</script>
			</div>
			<div id="showMore"  >显示更多</div>
			<div style="clear:both;"></div>
		</div>
		<div class="footer0"></div>
  		<jsp:include flush="true" page="footer2.jsp"></jsp:include>
		<div class="substnace" id="loading" style="display: none;">
			<div class="circle1"></div>
			<div class="circle2"></div>
			<div class="circle3"></div>
			<div class="circle4"></div>
		</div>
	</body>
</html>