<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<title>个人游记</title>
<link rel="stylesheet" type="text/css" href="css/other.css">
<link rel="stylesheet" type="text/css" href="css/details.css">

<script src="js/jquery-1.7.min.js"></script>
<script type="text/javascript" src="formerJs/login.js"></script>
</head>

<body>
	<div class="topNav">
		<div class="content">
			<div class="mould" style="margin-left: 25px;">
				<a href="javascript: ;">登录</a>
			</div>
			<div class="mould">
				<a href="javascript: ;">注册</a>
			</div>
			<div class="mould">
				<a href="javascript: ;" onmouseover="searchMouseover(event)"
					onmouseout="hide(event)">搜索</a>
			</div>
			<div class="search" id="search" onclick="searchMouseover(event)"
				onmouseover="searchMouseover(event)" onmouseout="hide(event)">
				<div class="cancelDiv">
					<a href="javascript: ;" onclick="hide(event)"></a>
				</div>
				<div class="inputDiv">
					<input type="text">
				</div>
				<div class="imgDiv">
					<a href="javascript: ;"></a>
				</div>
			</div>
		</div>
	</div>
	<div class="header">
		<div class="top">
			<div class="theSeal">
				<a href=""><img src="formerImage/theSealImage1.png"></a>
			</div>
			<div class="title1">
				<img src="formerImage/homePage.png">四川省旅游局旅游科研重点基地
			</div>
			<div class="title2">
				<img src="formerImage/homePage.png">四川理工学院重点科研平台
			</div>
		</div>
		<div class="buttom">
			<div class="navMenu">
				<ul>
					<li><a href="">首页</a></li>
					<li><a href="downloadlists">资源下载</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="login" id="loginView" onclick="loginMouseover(event)">
		<div class="accountNumberp">
			<input type="text" value="请输入账号" id="accountNumber"
				onfocus="loginInputFocus('true','accountNumber')"
				onblur="loginInputFocus('false','accountNumber')">
		</div>
		<div class="password">
			<input type="text" value="请输入密码" id="password"
				onfocus="loginInputFocus('true','password')"
				onblur="loginInputFocus('false','password')">
		</div>
		<div class="submit">
			<a href="">登&nbsp;录</a>
		</div>
	</div>
	<div class="topline"></div>
	<div id="main">
		<div class="dealCon">
			<ul class="left" id="selLeft">
				<li><a href="空间首页.html">空间首页</a></li>
				<li><a href="完善资料.html">完善资料</a></li>
				<li class="select"><a href="个人游记.html">个人游记</a></li>
				<li><a href="已评价景区.html">已评价景区</a></li>
			</ul>
			<div class="rightList">
				<div class="mould mould1">游记列表</div>
				<ul id="listMou">
					<li>
						<div class="listLine"></div>
						<a href="javascript: ;"><img src="formerImage/find1.jpg"/></a>
						<div class="listTop">
							<a href="javascript: ;">全方位搜罗巴西旅游不可错过的畅快旅游</a>
						</div>
						<div class="content">生活，不管起伏还是平静，爱都会像浪一样，涌动如潮水；站在生活的彼岸，带一份勇敢与执着，跨越岁月的鸿沟，与快乐倾情相约；爱情这片淡蓝的大海，不会永远风平浪静，无风无雨，它，时而潮起，时而潮落，褪去你的稚气，带走你的不羁；时而激越，时而安静，教会你去珍惜，懂得爱的可贵与来之不易。
						</div>
						<div class="listBtm">
							<img src="formerImage/time.jpg"/>2014-01-14
							<span>湖北大悟--双桥花山</span>
						</div>
					</li>
					<li>
						<div class="listLine"></div>
						<a href="javascript: ;"><img src="formerImage/find2.jpg"/></a>
						<div class="listTop">
							<a href="javascript: ;">全方位搜罗巴西旅游不可错过的畅快旅游</a>
						</div>
						<div class="content">生活，不管起伏还是平静，爱都会像浪一样，涌动如潮水；站在生活的彼岸，带一份勇敢与执着，跨越岁月的鸿沟，与快乐倾情相约；爱情这片淡蓝的大海，不会永远风平浪静，无风无雨，它，时而潮起，时而潮落，褪去你的稚气，带走你的不羁；时而激越，时而安静，教会你去珍惜，懂得爱的可贵与来之不易。
						</div>
						<div class="listBtm">
							<img src="formerImage/time.jpg"/>2014-01-14
							<span>湖北大悟--双桥花山</span>
						</div>
					</li>
					<li>
						<div class="listLine"></div>
						<a href="javascript: ;"><img src="formerImage/find3.jpg"/></a>
						<div class="listTop">
							<a href="javascript: ;">全方位搜罗巴西旅游不可错过的畅快旅游</a>
						</div>
						<div class="content">生活，不管起伏还是平静，爱都会像浪一样，涌动如潮水；站在生活的彼岸，带一份勇敢与执着，跨越岁月的鸿沟，与快乐倾情相约；爱情这片淡蓝的大海，不会永远风平浪静，无风无雨，它，时而潮起，时而潮落，褪去你的稚气，带走你的不羁；时而激越，时而安静，教会你去珍惜，懂得爱的可贵与来之不易。
						</div>
						<div class="listBtm">
							<img src="formerImage/time.jpg"/>2014-01-14
							<span>湖北大悟--双桥花山</span>
						</div>
					</li>
					<li>
						<div class="listLine"></div>
						<a href="javascript: ;"><img src="formerImage/find4.jpg"/></a>
						<div class="listTop">
							<a href="javascript: ;">全方位搜罗巴西旅游不可错过的畅快旅游</a>
						</div>
						<div class="content">生活，不管起伏还是平静，爱都会像浪一样，涌动如潮水；站在生活的彼岸，带一份勇敢与执着，跨越岁月的鸿沟，与快乐倾情相约；爱情这片淡蓝的大海，不会永远风平浪静，无风无雨，它，时而潮起，时而潮落，褪去你的稚气，带走你的不羁；时而激越，时而安静，教会你去珍惜，懂得爱的可贵与来之不易。
						</div>
						<div class="listBtm">
							<img src="formerImage/time.jpg"/>2014-01-14
							<span>湖北大悟--双桥花山</span>
						</div>
					</li>
					<li>
						<div class="listLine"></div>
						<a href="javascript: ;"><img src="formerImage/find5.jpg"/></a>
						<div class="listTop">
							<a href="javascript: ;">全方位搜罗巴西旅游不可错过的畅快旅游</a>
						</div>
						<div class="content">生活，不管起伏还是平静，爱都会像浪一样，涌动如潮水；站在生活的彼岸，带一份勇敢与执着，跨越岁月的鸿沟，与快乐倾情相约；爱情这片淡蓝的大海，不会永远风平浪静，无风无雨，它，时而潮起，时而潮落，褪去你的稚气，带走你的不羁；时而激越，时而安静，教会你去珍惜，懂得爱的可贵与来之不易。
						</div>
						<div class="listBtm">
							<img src="formerImage/time.jpg"/>2014-01-14
							<span>湖北大悟--双桥花山</span>
						</div>
					</li>
					<li>
						<div class="listLine"></div>
						<a href="javascript: ;"><img src="formerImage/find6.jpg"/></a>
						<div class="listTop">
							<a href="javascript: ;">全方位搜罗巴西旅游不可错过的畅快旅游</a>
						</div>
						<div class="content">生活，不管起伏还是平静，爱都会像浪一样，涌动如潮水；站在生活的彼岸，带一份勇敢与执着，跨越岁月的鸿沟，与快乐倾情相约；爱情这片淡蓝的大海，不会永远风平浪静，无风无雨，它，时而潮起，时而潮落，褪去你的稚气，带走你的不羁；时而激越，时而安静，教会你去珍惜，懂得爱的可贵与来之不易。
						</div>
						<div class="listBtm">
							<img src="formerImage/time.jpg"/>2014-01-14
							<span>湖北大悟--双桥花山</span>
						</div>
					</li>
					<li>
						<div class="listLine"></div>
						<a href="javascript: ;"><img src="formerImage/find7.jpg"/></a>
						<div class="listTop">
							<a href="javascript: ;">全方位搜罗巴西旅游不可错过的畅快旅游</a>
						</div>
						<div class="content">生活，不管起伏还是平静，爱都会像浪一样，涌动如潮水；站在生活的彼岸，带一份勇敢与执着，跨越岁月的鸿沟，与快乐倾情相约；爱情这片淡蓝的大海，不会永远风平浪静，无风无雨，它，时而潮起，时而潮落，褪去你的稚气，带走你的不羁；时而激越，时而安静，教会你去珍惜，懂得爱的可贵与来之不易。
						</div>
						<div class="listBtm">
							<img src="formerImage/time.jpg"/>2014-01-14
							<span>湖北大悟--双桥花山</span>
						</div>
					</li>
					<li>
						<div class="listLine"></div>
						<a href="javascript: ;"><img src="formerImage/find8.jpg"/></a>
						<div class="listTop">
							<a href="javascript: ;">全方位搜罗巴西旅游不可错过的畅快旅游</a>
						</div>
						<div class="content">生活，不管起伏还是平静，爱都会像浪一样，涌动如潮水；站在生活的彼岸，带一份勇敢与执着，跨越岁月的鸿沟，与快乐倾情相约；爱情这片淡蓝的大海，不会永远风平浪静，无风无雨，它，时而潮起，时而潮落，褪去你的稚气，带走你的不羁；时而激越，时而安静，教会你去珍惜，懂得爱的可贵与来之不易。
						</div>
						<div class="listBtm">
							<img src="formerImage/time.jpg"/>2014-01-14
							<span>湖北大悟--双桥花山</span>
						</div>
					</li>
					<li>
						<div class="listLine"></div>
						<a href="javascript: ;"><img src="formerImage/find9.jpg"/></a>
						<div class="listTop">
							<a href="javascript: ;">全方位搜罗巴西旅游不可错过的畅快旅游</a>
						</div>
						<div class="content">生活，不管起伏还是平静，爱都会像浪一样，涌动如潮水；站在生活的彼岸，带一份勇敢与执着，跨越岁月的鸿沟，与快乐倾情相约；爱情这片淡蓝的大海，不会永远风平浪静，无风无雨，它，时而潮起，时而潮落，褪去你的稚气，带走你的不羁；时而激越，时而安静，教会你去珍惜，懂得爱的可贵与来之不易。
						</div>
						<div class="listBtm">
							<img src="formerImage/time.jpg"/>2014-01-14
							<span>湖北大悟--双桥花山</span>
						</div>
					</li>
					<li>
						<div class="listLine"></div>
						<a href="javascript: ;"><img src="formerImage/find10.jpg"/></a>
						<div class="listTop">
							<a href="javascript: ;">全方位搜罗巴西旅游不可错过的畅快旅游</a>
						</div>
						<div class="content">生活，不管起伏还是平静，爱都会像浪一样，涌动如潮水；站在生活的彼岸，带一份勇敢与执着，跨越岁月的鸿沟，与快乐倾情相约；爱情这片淡蓝的大海，不会永远风平浪静，无风无雨，它，时而潮起，时而潮落，褪去你的稚气，带走你的不羁；时而激越，时而安静，教会你去珍惜，懂得爱的可贵与来之不易。
						</div>
						<div class="listBtm">
							<img src="formerImage/time.jpg"/>2014-01-14
							<span>湖北大悟--双桥花山</span>
						</div>
					</li>
				</ul>
				<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" style="font-size: 14px;margin-top: 25px;">
					<tr>
						<td width="40%" style="text-align: right;">有<strong class="right-text09"><s:property value="pageModel.totalRecords" /></strong>条记录|共<strong><span class="right-text09"><s:property value="totalPages" /></span></strong>页|第<strong><span class="right-text09"><s:property value="pageModel.pageNo" /></span></strong>页</td>
						<td width="50%" align="right">[ <input type="button"
							onclick="topPage()" value="首页" style="background: none;border: none;cursor: pointer;"> | <input type="button"
							onclick="previousPage()" value="上一页" style="background: none;border: none;cursor: pointer;"> | <input
							type="button" onclick="nextPage()" value="下一页" style="background: none;border: none;cursor: pointer;">| <input
							type="button" onclick="bottomPage()" value="末页" style="background: none;border: none;cursor: pointer;"> ] 转至：
						</td>
						<td width="15%"><table width="20" border="0" cellspacing="0"
								cellpadding="0">
								<tr>
									<td width="1%"><input type="text" size="4" id="page"></td>
									<td width="87%"><input type="button" value="GO"
										onclick="toPage()" ></td>
								</tr>
							</table></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<div class="footer0"></div>
	<div class="footer2">
		<div class="content">
			<div class="substance">
				Copyright 2012-2013 ABCD.com,Inc. All Rights Reserved. 京ICP证09028442号 中国高尔夫球童网Copyright 2012-2013 ABCD.com,Inc. All Rights Reserved. 京ICP证09028442号 中国高尔夫球童网Copyright 2012-2013 ABCD.com,Inc. All Rights Reserved. 京ICP证09028442号 中国高尔夫球童网
			</div>
		</div>
	</div>
</body>
</html>
