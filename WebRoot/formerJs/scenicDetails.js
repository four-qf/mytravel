var session;
var commupkey;
function dealNum(num){
	var newNum1 = parseInt(num);
	var newNum2 = num*10%10;
	if(newNum2 == 0){
		return newNum1;
	}else if(newNum2 > 0 && newNum2 < 5){
		return newNum1+"-3";
	}else if(newNum2 == 5){
		return newNum1+"-5";
	}else if(newNum2 > 5 && newNum2 <= 9){
		return newNum1+"-7";
	}
}
var FCC=true;//firstclickcomment
var noteclick=true;//游记ajax
var notePageNUm = 1;
$(function(){
	var Timer=null;
	var returnTop = document.getElementById("returnTop");
	//介绍，交通，点评，游记选择
	$(".topSel li div").click(function(){
		var parLi = $(this).parent();
		$(parLi).siblings().removeClass("curSel");
		$(parLi).addClass("curSel");
		$(parLi).parent().siblings().css("display","none");
		if($(parLi).index() == 0){
			$(".detText").css("display","block");
		}else if($(parLi).index() == 1){
			$(".detTra").css("display","block");
		}else if($(parLi).index() == 2){//评论
			$(".detCom").css("display","block");
			//点击评论栏目 conGet
			if(FCC){
				$("#conGet").click();
				FCC = false;
			}
		}else if($(parLi).index() == 3){//相关游记
			$(".detRela").css("display","block");
			//ajax
			if($("#scenicId").val()!=null && noteclick)
			{addNoteLi();noteclick = false;}
		}
	});
	//驴友点评、我要点评选择
	$(".comSel li").click(function (){
		if($(this).attr("id")=="comm2")//我要评论项  判断是否登录 切换评论
		{
			//更新评论验证码
			$("#codeImg").click();
			if($("#Ausername").val()==null)
			{
				$("#userlogin").click();
				//切换到未登录界面
				$("#cliLogin").css("display","block");
				$("#logined").css("display","none");
			} else if($("#Ausername").val()=="")
			{
				$("#userlogin").click();
				//切换到未登录界面
				$("#cliLogin").css("display","block");
				$("#logined").css("display","none");
			}else{
				$("#cliLogin").css("display","none");
				$("#logined").css("display","block");
			}
		}
		$(this).siblings().removeClass("selCur");
		$(this).addClass("selCur");
		$(this).parent().siblings().css("display","none");
		if($(this).index() == 0){
			$(".comCon").css("display","block");
		}else if($(this).index() == 1){
			$(".myCom").css("display","block");
		}
	});
	//设置驴友点评的li状态
	$(".comCon li").last().css("border","none");
	if($(".comCon li").length == 10){
		$(".conGet").css("display","block");
	}
	//设置相关游记的li状态
	$(".detRela li").last().css("border","none");
	if($(".detRela li").length == 10){
		$(".relaGet").css("display","block");
	}
	//评分选择事件
	$(".mySoce dd").click(function(){
		$(this).parent().attr("class","");
		$(this).parent().addClass("star"+($(this).index()+1));
		if($(this).parent().parent().html().indexOf("人气：")>-1){
			$("#peopNum").attr("value",$(this).index()+1);
			}
		if($(this).parent().parent().html().indexOf("服务：")>-1){
			$("#serviceNum").attr("value",$(this).index()+1);
			}
		if($(this).parent().prev().html() == "总体评价；"){
			$(this).parent().prev().prev().html(($(this).index()+1)+"分");
			$("#totalNum").attr("value",$(this).index()+1);
		}
		
		if($(this).parent().parent().html().indexOf("环境：")>-1){
			$("#environmentNum").attr("value",$(this).index()+1);
			}
		if($(this).parent().parent().html().indexOf("交通：")>-1){
			$("#transportNum").attr("value",$(this).index()+1);
			}
	});
	//评价字数限制
	$(".rightTxet").keyup(commupkey =function(){
		var textLen = $(this).attr("value").length;
		if(textLen < 20){
			$(this).next().html("您尚需20个字才能提交！")
				   .next().next().removeClass("myQueCk")
						  .attr("disabled");
		}else if(textLen >= 20 && textLen <= 300){
			$(this).next().html("亲爱的用户,您可以提交了。")
				   .next().next().addClass("myQueCk")
						  .removeAttr("disabled");
		}else{
			$(this).next().html("字数请控制在300个字以内！")
				   .next().next().removeClass("myQueCk")
						  .attr("disabled");
		}
	});
	//设置上方缩略图的li状态	
	var liLen = parseInt($(".ulDiv li").length / 6)+1;
	$(".ulDiv ul").width(330*liLen);
	var smaImgLi = $(".ulDiv li");
	for(var i=0; i<smaImgLi.length; i++){
		var left = 5+i%2*165+parseInt(i/6)*330;
		var top = parseInt((i%6)/2)*117;
		$(smaImgLi[i]).css({"left": left+"px","top": top+"px"});
	}
	//向右移动事件
	$(".rightRet").click(function(){
		var left = parseInt((0-parseInt($(".ulDiv ul").css("left")))/330)+1;
		if(left < liLen){
			if($(".ulDiv ul").is(":animated")){
				left++;
				if(left >= liLen){
					left = liLen-1;
				}
				$(".ulDiv ul").stop(true);
			}
			$(".ulDiv ul").animate({"left": -(left*330)},1000);
		}
	});
	//向左移动事件
	$(".leftRet").click(function(){
		var left = parseInt((0-parseInt($(".ulDiv ul").css("left")))/330)-1;
		if(left > -1){
			if($(".ulDiv ul").is(":animated")){
				if(left <= 0){
					left = 0;
				}
				$(".ulDiv ul").stop(true);
			}
			$(".ulDiv ul").animate({"left": -(left*330)},1000);
		}
	});
	$(".largeImg img:eq(1)").css({"opacity":"0"});
	//上方缩略图点击事件
	$(".ulDiv li").click(function(){
		var imgSrc = $(this).find("img").attr("src");
		var oldSrc = $(".largeImg img:eq(1)").attr("src");
		if($(".largeImg img:eq(1)").is(":animated")){
			$(".largeImg img:eq(1)").stop(true,true)
									.css({"opacity":"0"})
									.prev().attr("src",oldSrc);
		}
		$(".largeImg img:eq(1)").attr("src",imgSrc)
								.animate({"opacity": "1"},1000,function(){
									$(this).prev().attr("src",imgSrc).end()
										   .css("opacity","0");
								});
	});
	//没去过、打算去、已去过、打算再去的选择
	$(".selGo a").click(function(){
		$(".selGo a").removeClass("spaSel");
		$(this).addClass("spaSel");
		var selGoIn = $(this).parent().index();
		if(selGoIn == 0 || selGoIn == 1){
			$(".mySoce").css("display","none");
			$("#status").attr("value",selGoIn);
		}else if(selGoIn == 2 || selGoIn == 3){
			$(".mySoce").css("display","block");
			$("#status").attr("value",selGoIn);
		}
	});
	//底部设置，点击事件
	var bgDivLi = $(".bgDiv li");
	var bgLen = bgDivLi.length/2;
	$(".bgDiv li").css({"top":"30px","opacity": "0"});
	$(".bgDiv dl").click(function(){
		if($(this).css("paddingTop") == "0px"){
			$(this).find("dd").animate({"height":"4px"},500);
			$(this).animate({"paddingTop":"5px"},500);
			$(".bgDiv li").animate({"top":"30px","opacity": "0"},500);
		}else{
			$(this).find("dd").animate({"height":"6px"},500);
			$(this).animate({"paddingTop":"0px"},500);
			for(var i=0; i<bgLen; i++){
				$(bgDivLi[i]).delay(100*(bgLen-i)).animate({"top":"0px","opacity": "1"},500);
				$(bgDivLi[bgLen+i]).delay(100*(i)).animate({"top":"0px","opacity": "1"},500);
			}
		}
	});
//返回点击事件
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
	//右下角缩略图状态设置
	$(".rightMou").last().css("marginBottom","0px");
	//驴友点评中分数状态设置
	$(".score dd:nth-child(5n-4)").addClass("totSco").find("span").css("display","none");
	var scoreDd = $(".score dd:nth-child(5n-4)");
	for(var i=0; i<scoreDd.length; i++){
		var scoreNum = $(scoreDd[i]).find("span").html();
		var scoreImg = document.createElement("img");
		if(scoreNum != -1){
			$(scoreImg).attr("src","formerImage/star"+scoreNum+".png");
			$(scoreDd[i]).append(scoreImg);
		}
		
	}
	//景点评分中状态设置
	$(".rightCom span").css("display","none");
	var str = dealNum(parseFloat($(".rightSco span").html()));
	$(".rightSco img").attr("src","formerImage/star"+str+".png");
	var rigComLi = $(".rightCom li");
	for(var i=0; i<rigComLi.length; i++){
		str = dealNum(parseFloat($(rigComLi[i]).find("span").html()));
		$(rigComLi[i]).find("img").attr("src","formerImage/star"+str+".png");
	}
});

//游记显示更多、
$(function (){
	$("#relaGet").click(function (){
	addNoteLi();
	});
});

$(function(){
var CommentData0=null;
var CommentStatus = null;
//评论 发表
	$("#ScenicSub").click(function(){
		//封装用户评论数据 peopNum serviceNum environmentNum transportNum
		CommentStatus = $("#CommentStatus").find("input").val();
		if(CommentStatus>1){
			 CommentData0 = $("#totalNum").val();
		}else{
			//没有评分
		}
		//先发送ajax 再添加节点 comCon
		$.ajax({
			type: "post",
			url: "frontscenicComment_add.action",
			data : {
				"scenicId" : $("#scenicId").val(),
				"model.status" : CommentStatus,
				"model.totalNum" : $("#totalNum").val(),
				"model.peopNum" : $("#peopNum").val(),
				"model.transportNum" : $("#transportNum").val(),
				"model.serviceNum" : $("#serviceNum").val(),
				"model.environmentNum" : $("#environmentNum").val(),
				"model.content" : $("#rightTxet").val(),
				"sccode" : $("#sccode").val()
			},
			success: function(data)
			{ 
			if(data=="nocode"){
				alert("验证码错误，评论失败!");
				//刷新验证码
				$("#codeImg").click();
			}
			else if(data!="false")
			{
				//增加节点
addCommentLI("head/"+$("#Ausername").val()+"/"+$("#userhead").val(),//authorPhoto "head/"+data[i].authorName+"/"+data[i].head
	$("#Ausername").val(),CommentData0,$("#peopNum").val(),$("#serviceNum").val(),$("#environmentNum").val(),$("#transportNum").val(),CommentStatus,$("#rightTxet").val());
				 $("#rightTxet").val("");//commupkey
				 $("#rightTxet").unbind("keyup");
				 $("#rightTxet").keyup(commupkey);
				 $(".retext").html("你尚需输入20个字方可提交");
				 $("#ScenicSub").attr("disabled","true");
				 $("#ScenicSub").removeClass("myQueCk");
				 //景点总的评分改变 节点
//				var totalScore = new String(obj.totalScore).substring(0,4);
//				var peopleScore = new String(obj.peopleScore).substring(0,4);
//				var serviceScore = new String(obj.serviceScore).substring(0,4);
//				var environmentScore = new String(obj.environmentScore).substring(0,4);
//				var transportScore = new String(obj.transportScore).substring(0,4);
//				$(".rightSco").html("");
//				$(".rightSco").append('<span>'+totalScore+'分</span><img src="formerImage/star5.png"/>');
//				$(".rightCom").html("");
//				$(".rightCom").append('<li>人气：<img src="formerImage/star5.png"/> <span style="display: none" >'+peopleScore+'</span></li>' +
//				'<li>服务：<img src="formerImage/star5.png"/><span style="display: none" >'+serviceScore+'</span></li>' +
//				'<li>环境：<img src="formerImage/star5.png"/><span style="display: none" >'+environmentScore+'</span></li>' +
//				'<li>交通：<img src="formerImage/star5.png"/><span style="display: none">'+transportScore+'</span></li>');
				//评论数改变 commentNum
				$('#updateComm').html("驴友点评("+(parseInt($('#updateComm').attr("index"))+1)+")");
				//刷新验证码
				$("#codeImg").click();
			}//if结束
			else{
				alert("评论失败!");
			}
						}});
				});//发表评论结束
	//更多评论
$("#conGet").click(function(){
	var pagenum = $("#pagenumComm").val();
		//发送ajax
		$.ajax({
			type: "get",
			url: "frontscenicComment_getLsBysId.action",
			data:{
				"pageNum" : pagenum,
				"scenicId" : $("#scenicId").val(),
			}
			,success: function(data)
			{ 
				if(data=="0"){$("#conGet").css("display","none");return;}
				else if(data=="-1")
				{
					alert("获取评论列表失败！");
					$("#conGet").css("display","none");
				}else 
//					if(data.substring(0,1)=="1")
				{
//					data = data.substring(1,data.length);
					//修改 评论页数
					$("#pagenumComm").val(parseInt($("#pagenumComm").val())+1);
					data = eval(data);
					if (data.length<12){$("#conGet").css("display","none");}
					for (var i=0;i<data.length;i++)
						addCommentLI("head/"+data[i].authorName+"/"+data[i].head,
								"<a href=frontUser_findPersonInfo.action?model.id="+data[i].authorId+">"+data[i].authorName+"</a>"
								,
								data[i].totalNum,data[i].peopNum,data[i].serviceNum,
							data[i].environmentNum,data[i].transportNum,data[i].status,data[i].content);
				}
				//else{alert("服务器出错");}
			}
		})
	});//更多评论结束
		});
								
//创建评论节点
	function addCommentLI(authorPhoto,authorName,totalNum,peopNum,serviceNum,enviormentNum,transportNum,CommentStatus,content){
	var comUl = $("#comCon").find("ul");
	var comLI = comUl.append("<li></li>");
	var commDiv1 = $('<div class=conLeft></div>');
	commDiv1.append('<img src='+$("#root").val()+'/'+authorPhoto+' />');
	var n = "<div class=leftName>"
	n = n+authorName;
	n = n+"</div>";
	commDiv1.append(n);
	var commDiv2 = $('<div class=conRight></div>');
	if(CommentStatus>1)
	{	//
		commDiv2.append('<dl class=score><dd>总体评价：<span style=display:none>'+totalNum+'</span><img src=formerImage/star'+totalNum+'.png /></dd>' +
		'<dd>人气：<span>'+peopNum+'</span></dd>' +
		'<dd>服务：<span>'+serviceNum+'</span></dd>' +
		'<dd>环境：<span>'+enviormentNum+'</span></dd>' +
		'<dd>交通：<span>'+transportNum+'</span></dd></dl>');
	}else
	{
		if(CommentStatus==0)
			commDiv2.append('<dl class=score><dd style="">旅客状态：<span style=display:none>-1</span>没去过</dd></dl>');
		else if(CommentStatus==1)
			commDiv2.append('<dl class=score><dd style="">旅客状态：<span style=display:none>-1</span>打算去</dd></dl>');
	}
	commDiv2.append('<div class=textCon style="word-break:break-all;  overflow:auto;">'+content+'</div>');
	var  commDiv3 = $('<div style=clear:both;></div>');
		comLI.append(commDiv1);
		comLI.append(commDiv2);
		comLI.append(commDiv3);
		 $("#comm1").click();
	}

//创建notte节点
function addNoteLi(){
	$.ajax({
	type : "get",
	url: "frontnote_getNoteByScenicId.action",
	data:{
		"scenicId" : $("#scenicId").val(),
		"notePageNum" : notePageNUm
	}
	,success: function(returnData)
	{
		var returnData1 = returnData.substr(0,4);
		var returnData2 = returnData.substr(4,returnData.length)
		if(returnData1=="true")
		{	
				var obj2 = eval(returnData2);
				//创建节点
				//隐藏more
					if(obj2.length<10)
					{$("#relaGet").attr("display","none");}
					else{$("#relaGet").attr("display","block");}
					for(var i =0 ;i<obj2.length;i++)
					{
					var notLi = $("#detRela ul").append('<li><a href=frontnote_detail.action?model.id='+obj2[i].id+'><img src='+$("#root").val()+"/"+obj2[i].imageUrl+' /></a>' +
					'<a class="relaTop" href=frontnote_detail.action?model.id='+obj2[i].id+'>'+obj2[i].title+'</a>' +
					'<div class="releCon">'+obj2[i].content+'</div>' +
					'<div class="releTxet"><img src="formerImage/time.jpg"/>'+new Date(obj2[i].postTime).toLocaleString()+'<span>作者:<a href="frontUser_findPersonInfo.action?model.id='+obj2[i].authorId+'">'+obj2[i].username+'</a></span></div>' +
					'</li>');
					}
					notePageNUm = parseInt(notePageNUm)+1;
		}
	}
	});
}
