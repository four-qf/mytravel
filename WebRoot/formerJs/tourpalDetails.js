var FClick = true;//是否发送第一次回复ajax查询
var Oarray;
var Oarray2;
var Oarray3;
var Oarray4;
$(document).ready(function(){
});
$(function(){
	var Timer=null;
	var chooseLi = document.getElementById("choose").children;
	var comment = document.getElementById("comment");
	var lookCom = document.getElementById("lookCom");
	var comMou = lookCom.children;
	var returnTop = document.getElementById("returnTop");
	comMou[comMou.length-1].style.border="none";
	comMou[comMou.length-1].style.paddingBottom="0px";
	chooseLi[0].onclick=function(){
		chooseLi[1].className = "";
		this.className = "current";
		comment.style.display="none";
		lookCom.style.display="block";
	};
	chooseLi[1].onclick=function(){
		chooseLi[0].className = "";
		this.className = "current";
		comment.style.display="block";
		lookCom.style.display="none";
	};
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
	
	for(var i=0; i<comMou.length; i++){
		$(comMou[i]).find(".lookRep").last().css("borderBottom","none");
		if($(comMou[i]).find(".lookRep").length == 5){
			$(comMou[i]).find(".repMore").css("display","block");
		}
	}
	
	//设置textarea为空
	$("textarea").val("");
	$(".replyPub textarea,#comment textarea").keyup(Oarray3 = function(){
		var textStr = $(this).val();
		if(textStr.length < 20){
			$(this).next().html("请最少填写20个字，才能回复哦。")
				   .next().attr("disabled","disabled").css("cursor","default");
		}else if(textStr.length > 20 && textStr.length <= 300){
			$(this).next().html("请不要超过300个字哦。")
				   .next().removeAttr("disabled").css("cursor","pointer");
		}else{
			$(this).next().html("超过了300个字哦，要少于300个字才能回复哦。")
				   .next().attr("disabled","disabled").css("cursor","default");
		}
	});
	$("#lookCom .replyMou li").click(Oarray =function repl(){
		if($(this).html().indexOf("查看回复")>-1){
			$(this).attr("class","nowLi")
				   .next().attr("class","")
				   .end().parent().next().css("display","block")
				   .next().css("display","none");
			var firstClick = $(this).parent().parent().parent().parent().find(".firstClick");
			var FClick = $(this).parent().parent().parent().parent().find(".FClick");
			if($(firstClick).val()=="true" && $(FClick).val()=="true")//第一次点击 ajax请求 replyPageNum
			{	 
				var repMore = $(this).parent().parent().find(".repMore");
				var noteCommId= $(this).parent().parent().parent().parent().find(".notecommId").val();
				var replyPageNum = $(this).parent().parent().parent().parent().find(".replyPageNum");
				$(repMore).click(Oarray4 = function(){
					var noteCommId= $(this).parent().parent().parent().parent().find(".notecommId").val();
					var replyPageNum = $(this).parent().parent().parent().parent().find(".replyPageNum");
						getrepList(noteCommId,replyPageNum,null,$(this));				
									});
				getrepList(noteCommId,replyPageNum,firstClick,repMore);
			}
			
		}else{
			$(this).attr("class","nowLi")
				   .prev().attr("class","")
				   .end().parent().next().css("display","none")
				   .next().css("display","block");
		}
	});
	
});

//投票
	 function voteNote(flag2,id){
		$.ajax({type: "get",
				url: "frontnote_voteNote.action",
				dataType: "text",
				data : {
					"flag2" : flag2,
					"model.id" : id
				},
				success: function(returnData){
					if(returnData.substring(0,4)=='true')
					{
						//为false2 则为好评
						if(!flag2)
						{	
							$("#positiveDiv").empty();
							$("#positiveDiv").html(returnData.substring(4,returnData.length)+"票");
						}else
						{
							$("#negativeDiv").empty();
							$("#negativeDiv").html(returnData.substring(4,returnData.length)+"票");
						}
					}else if(returnData=="noLogin")
					{
						alert("你没有登陆");
					}else if(returnData=="noRight")
					{
						alert("你近期已透过一次，请过会儿再投！");
					}else{
						alert("未知错误");
					}
				}
			});
			
		}
//添加回复节点
	 function addReplyRoot(obj,objParent){//<a href="frontUser_findPersonInfo.action?model.id=14">zhuxiao11</a>
									objParent.append('<div class="lookRep" ><img src=head/'+obj.username+'/'+obj.userhead+' /> <div class="repCon">' +
									'	<span class="repname"><a href=frontUser_findPersonInfo.action?model.id='+obj.authorId+'>'+obj.username+'</a></span>:<span>'+obj.content+'</span>' +
									'	<div class="repDate" style="bottom: 1px;">回复于'+new Date(obj.postTime).toLocaleString()+'</div>' +
									'	</div></div>');
									}
 $(function(){
	 //回复ajax
 $(".MainMou .repbtn").click(Oarray2 = function (){
	 var addParent = $(this).parent().parent().find(".replyCon");//回复的主节点
	 var noteCommId= $(this).parent().parent().parent().parent().find(".notecommId").val();
	 var repcontent = $(this).parent().find(".repcontent");
	  var head= $(this).parent().parent().parent().parent().find(".authorHead").val();
	  var name= $(this).parent().parent().parent().parent().find(".authorName").val();
		var time = new Date().toLocaleString();
		var checkrep = $(this).parent().parent().find("#checkrep");
		var repMore = $(this).parent().parent().find(".repMore");
		var firstClick = $(this).parent().parent().parent().parent().find(".firstClick");
	 $.ajax(
		{type: "post",
		url: "notereply_add.action",
		data : {
			"commentId" : noteCommId,
			"model.content" : repcontent.val()
		},
		success: function(returnData){
			if(returnData=='true')
			{
				//先显示添加的 后显示加载的   有条件判断 
				//没有显示更多 而且不是第一次加载回复 
				if($(repMore).css("display")=="none" && firstClick.val()=="false")
				{
					addParent.append('<div class="lookRep" style="border:1px solid red">' +
										'<img src='+$("#root").val()+"/head/"+name+"/"+head+' /> ' +
										'<div class="repCon">' +
											'<span class="repname">'+name+'</span>:<span>'+repcontent.val()+'</span>' +
											'<div class="repDate" style="bottom: 1px;">回复于'+time+'</div>' +
										'</div>' +
									'</div>');
					$("#FClick").val("false");
				}
				checkrep.click();
				repcontent.val("");
				//显示条数加一
				$(checkrep).attr("index",parseInt($(checkrep).attr("index"))+1);
				$(checkrep).html("查看回复("+$(checkrep).attr("index")+")");
				}else{alert("不能回复，未登录");}
			}
			});
	 });
 });
//获取一组回复
function getrepList(noteCommId,reppageNum,firstClick,repMore){
$.ajax({
	url: "notereply_getReplyByCommentId.action",
	type: "get",
	data:{"commentId":noteCommId
		,"pageNum" : $(reppageNum).val()
	},
	success: function(returnData)
	{	
		var returnData1 = returnData.substring(0,4);
		var obj = eval(returnData.substring(4,returnData.length));
		var isDisplay;
		if(returnData1=="true"){
			if(obj.length<10)//返回数据不够10条则不显示更多
			{repMore.css("display","none");isDisplay="none";}
			else
				{
					repMore.css("display","block");isDisplay="block";
				}
			//添加回复节点
			var rootParent = repMore.parent();
			//清空更多再
			rootParent.find(".repMore").remove();
			for(var i=0;i<obj.length;i++)
			{
				addReplyRoot(obj[i],rootParent);//<div class="repMore" style="display: none">更多回复</div>
			}
			rootParent.append('<div class="repMore" style=display:'+isDisplay+'>更多回复</div>');
			//先删除事件  再付 Oarray4
			$(rootParent).find(".repMore").unbind("click");
			$(rootParent).find(".repMore").click(Oarray4);
			
			$(firstClick).attr("value","false");
			$(reppageNum).val(parseInt($(reppageNum).val())+1);
		}
	}
});
}
	
function addComm(obj){
	 
for (var i=0;i<obj.length;i++)
{
	var replnum = 0;
	if($(obj[i].replnum).length>0)
	{replnum = obj[i].replnum}
$(".MainMou").append('<div class=comMou><form autocomplete="off"><input type="hidden" class="FClick" value="true" />' +
'<input type="hidden" class="authorHead" value='+$("#authorhead").val()+'/>' +
'<input type="hidden" class="authorName" value='+$("#authorUsername").val()+' />' +
'<input type="hidden" class="notecommId" value='+obj[i].id+' />' +
'<input type="hidden" class="firstClick" value="true"/>' +
'<input type="hidden" class="replyPageNum" value="1" /></form>' +
'<img class="headImg" src='+$("#root").val()+"/head/"+obj[i].username+"/"+obj[i].userhead+'></img>' +
'<div class="content">	<div class="top">' +
'<div class="name">'+obj[i].username+'</div>' +
'	<div class="date">发表于'+new Date(obj[i].postTime).toLocaleString()+'</div> </div>' +
'<div class="substance">'+obj[i].content+'</div>' +
'<div class="replyMou">' +
'<ul>' +
'	<li id="checkrep" index='+replnum+'>查看回复('+replnum+')</li>' +
'<li class="nowLi">发表回复</li>' +
'</ul>' +
'<div class="replyCon">' +
'	<div class="repMore" style="display: none">更多回复</div>' +
'</div>' +
'<div class="replyPub" style="display: block">' +
'<textarea class="repcontent"></textarea><div class="repHint">请最少填写20个字，才能回复哦。</div>' +
'<input type="button" class="repbtn" value="回复" onfocus="this.blur()" disabled=disabled />' +
'</div></div></div></div>');
}
}
	$(function(){
	var modeId = $("#modelId").val();
		//评论显示更多 commentPageNum
		$(".moreComm").click(function(){
			var pageNum = parseInt($("#commPageNum").val())+1;
			$("#commPageNum").val(pageNum);
			$.ajax({
				type: "post",
				url: "notecomment_getCommByNoteId.action",
				data:{
					"NoteId": modeId,
					"pageNum": pageNum
				},
				success: function(returnData)
				{	
					var returnData1 = returnData.substr(0,4);
					var obj = eval(returnData.substr(4,returnData.length));
					if(returnData1=="true")
					{
						if(obj.length<10)
						{$(".moreComm").css("display","none");}
						addComm(obj);
					}
				//重新绑定事件
				$("#lookCom .replyMou li").unbind("click");
				$("#lookCom .replyMou li").click(Oarray);
				$(".MainMou .repbtn").unbind("click");
				$(".MainMou .repbtn").click(Oarray2);
				$(".replyPub textarea,#comment textarea").unbind("keyup");
				$(".replyPub textarea,#comment textarea").keyup(Oarray3);
				//
				}
				});
		});//评论一次
		$("#commSub").click(function(){
			$.ajax({
				type: "post",
				url: "notecomment_add.action",
				data:{
					"NoteId": modeId,
					"model.content": $("#commContent").val()
				},
				success: function(data)
				{	
					if(data==-1)
					{alert("程序异常");}
					else if(data==0){alert("未登录");}
					else
					{
						var obj = eval(data);
						//先创建评论节点 查看评论更多是否none 
							//先初始化moreComm  根据MainMou
							var len = $(".MainMou").children().length;
							 addComm(obj);
						if(len<10)
						{
							$(".moreComm").css("display","none");
						}else
						{
							$(".moreComm").css("display","block");
						}
						 $("#choose").children(":first").click();
						 //修改评论数
						 var num = parseInt($("#choose").children(":first").attr("index"))+1;
						 $("#choose").children(":first").attr("index",num);
						  $("#choose").children(":first").html("评论("+num+")");
						 $("#commContent").val("");
						 
						 //重新绑定事件
						$("#lookCom .replyMou li").unbind("click");
						$("#lookCom .replyMou li").click(Oarray);
						$(".MainMou .repbtn").unbind("click");
						$(".MainMou .repbtn").click(Oarray2);
						$(".replyPub textarea,#comment textarea").unbind("keyup");
						$(".replyPub textarea,#comment textarea").keyup(Oarray3);
						//
					}
				}
			});
		});
	});