function createDD(obj,name){
	var newdd = document.createElement("dd");
	$(newdd).html("<span>"+name+"</span><div class='cancle'></div>");
	$(newdd).find("div").click(function(){
		$(newdd).remove();
		$(obj).css({"background" : "#FFF","color" : "#999"})
			  .attr("index",0);
		//清空页数 然后ajax
			$("#pageNum").val(1);
			getData(true);
	});
	return newdd;
}
function hotMove(obj){
	var left = parseInt($(obj).css("left"), 10);
	$(obj).css("left",left-2+"px");
	if(left <= -$(obj).width()/2){
		$(obj).css("left","0px");
	}
}
$(function(){
	if($("#spotImg li").length %2 == 1){
		var newLi = document.createElement("li");
		$("#spotImg>div").before(newLi);
	}
	$("#spotImg li:odd").css("marginRight","20px");
	$("#spotImg li").last().css("marginBottom","20px")
					.prev().css("marginBottom","20px");
	//设置左边li背景
	var spotLi = $("#spotImg li");
	for(var i=0; i<spotLi.length; i++){
		$("#spotImgBg").append("<li>");
	}
	$("#spotImgBg li").last().css("paddingBottom","20px")
					.prev().css("paddingBottom","20px");
	//设置左边li的长度
	var findLi = $(".findMou").find("li");
	for(var i=0; i<findLi.length; i++){
		$(findLi[i]).attr("index",0);
		if($(findLi[i]).width() > 90){
			$(findLi[i]).width(190);
		}
	}
	//设置+状态和ul显示状态
	var findUl = $(".findMou ul");
	for(var i=0; i<findUl.length; i++){
		$(findUl[i]).attr("index",$(findUl[i]).height());
	}
	$(".findMou span:eq(3)").html("+");
	$(".findMou ul:eq(1)").css({"height":"0px","paddingBottom": "0px","paddingTop": "0px"});
	//name点击展开收缩事件
	$(".findMou .name").click(function(){
		if($(this).next().is(":animated")){
			$(this).next().stop();
		}
		if($(this).find("span:eq(1)").html() == "+"){
			$(this).next()
				   .animate({"height": $(this).next().attr("index")+"px","paddingBottom": "10px","paddingTop": "10px"},800)
				   .end()
				   .find("span:eq(1)").html("-");
		}else{
			$(this).next()
				   .animate({"height":"0px","paddingBottom": "0px","paddingTop": "0px"},800)
				   .end()
				   .find("span:eq(1)").html("+");
		}
	});
	//li点击右边显示事件
	for(var i=0; i<findLi.length; i++){
		$(findLi[i]).click(function(){
			$(this).attr("name",$(this).html());
			if($(this).attr("index") == "0"){//添加右边的选项
				$("#findSea dl").append(createDD(this,$(this).attr("name")));
				$(this).css({"background" : "#4DAEDD","color" : "#FFF"})
					   .attr("index",1);
			}else{//消除右边的选项
				$(this).css({"background" : "#FFF","color" : "#999"})
					   .attr("index",0);
				var ddObj = $("dd");
				for(var j=0; j<ddObj.length; j++){
					var nowSpan = $(ddObj[j]).find("span").first();
					if($(nowSpan).html() == $(this).attr("name")){
						$(nowSpan).parent().remove();
						//清空页数 然后ajax
						$("#pageNum").val(1);
						getData(true);
						return ;
					}
				}
			}
		//清空页数 然后ajax
		$("#pageNum").val(1);
		getData(true);
		});
	}
	//清空按钮
	$("#findSea a").click(function(){
		$(this).prev().children().remove();
		$(".findMou li").css({"background" : "#FFF","color" : "#999"})
						.attr("index",0);
		//清空页数 然后ajax
		$("#pageNum").val(1);
		getData(true);
	});
	//排序点击事件
	$("#listSort li").click(function(){
		$(this).siblings().removeClass("current");
		$(this).addClass("current");
		//清空页数 然后ajax
		$("#pageNum").val(1);
//		$(".queMore").css("display","block");
		getData(true);
	});
	//设置右边缩略图的底边
	$("#listMou li:last").css("border","none").find(".listLine").remove();
	//顶部的热门景点滚动条
	$(".rollHot ul").width($(".rollHot ul").width()*2).html($(".rollHot ul").html()+$(".rollHot ul").html());
	$(".rollHot ul").mouseout(function(){
		$time = setInterval(function(){
			hotMove($(".rollHot ul"));
		}, 30);
	}).mouseover(function(){
		clearInterval($time);
	}).mouseout();
});
//获取景区级别 地理位置 景区类别 集合
function getData(isdel)
{
	//var dataSql = levelSQl+";"+positionSQl+";"+categorySQL+";";
	//获取级别 
	var level = $("#notelevel").find("ul li[index=1]");
	var levelSQL="";
	for(var i=0;i<level.length;i++)
	{
		if(i!=level.length-1)
			levelSQL=levelSQL+$(level[i]).html()+",";
		else
			levelSQL=levelSQL+$(level[i]).html();
	}
	//获取地理位置
	var location = $("#noteloaction").find("ul li[index=1]");
	var locationSQL="";
	for(var i=0;i<location.length;i++)
	{
		if(i!=location.length-1)
			locationSQL=locationSQL+$(location[i]).html()+",";
		else
			locationSQL=locationSQL+$(location[i]).html();
	}
	
	//获取类别
	var noteclass = $("#noteclass").find("ul li[index=1]");
	var noteclassSQL="";
	for(var i=0;i<noteclass.length;i++)
	{
		if(i!=noteclass.length-1)
			noteclassSQL=noteclassSQL+$(noteclass[i]).attr("z")+",";
		else
			noteclassSQL=noteclassSQL+$(noteclass[i]).attr("z");
	}
	
	var DataSQL="";
		DataSQL+=levelSQL+";"+locationSQL+";"+noteclassSQL+";"
	//获取排序 类型
	var notesort = $("#listSort").find("li[class=current]").attr("title");
	//获取页数
	var pageNum = $("#pageNum").val();
	var ISDEL =isdel;
	//发送ajax
	$.ajax({
		url: "frontnote_searchData.action",
		type: "post",
		data:{
			"queryString" : DataSQL,
			"pageNum" : pageNum,
			"notesort" :notesort,
			"keywords" : $("#keyW2").val()
		},
		success:function(data)
		{
			var data1 = data.substring(0,4);
			if(data1=="true")
			{
				//增加页数
				$("#pageNum").val(parseInt($("#pageNum").val())+1);
				data2 = eval(data.substring(4,data.length));
				
				if(data2.length<10)
					$("#showMore").css("display","none");
				else {
					$("#showMore").css("display","block");
					}
				addnote(ISDEL,data2);
			}else{alert("程序出错");}
		}
	})
}

//showMore
$(function(){
	$("#showMore").click(function(){
		getData(false);
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
//添加游记节点
function addnote(ISDEL,data2){
	var root =$("#root").val();
	if(ISDEL)
		$("#listMou").html("");
	else if(data2.length<10)
		$("#showMore").css("display","none");
	else if(data2.length==10){
		$("#showMore").css("display","block");
		alert("s");
	}
	for(var i=0;i<data2.length;i++)
	{
		$("#listMou").append('<li><div class="listLine"></div> ' +
		'<a href="frontnote_detail.action?model.id='+data2[i].id+'">' +
		'<img src='+root+'/'+data2[i].imageUrl+' /></a>' +
		'<div class="listTop">' +
			'<a href="frontnote_detail.action?model.id='+data2[i].id+'">'+data2[i].title+'</a>' +
			'<div class="author"><a href="frontUser_findPersonInfo.action?model.id='+data2[i].authorId+'">'+data2[i].username+'</a></div>' +
			'<img class="autImg" src='+root+'/head/'+data2[i].username+'/'+data2[i].authorimg+' >' +
		'</div><div class="content">'+data2[i].content+'</div>' +
		'<div class="listBtm"><img src="formerImage/time.jpg" />'+new Date(data2[i].postTime).toLocaleString()+
		'<span>'+data2[i].cityname+'--'+data2[i].title+'(点击量：'+data2[i].clickNum+')(评论数：'+data2[i].commentNum+')</span>' +
		'	</div></li>');
	}
}
//
$(function(){
	if($("#listMou").children().size()<10){$("#showMore").css("display","none");}
});

