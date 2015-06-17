var Wmove=null;
function creatLi(move,name,obj,type){
	var newLi = document.createElement("li");
	if(type == 0){
		$(newLi).html("<span>"+name+"</span><div></div>")
				.find("div").click(function(){
					$(this).parent().remove();
					$(obj).removeClass("schLi")
						  .find("div").removeClass("schDiv");
					$("#pageNum").val("1");
					getDate_Ajax(move,1);
				});
	}else if(type == 1){
		var newName = $(obj).closest("li").find("span").html()+":  "+name;
		$(newLi).html("<span>"+newName+"</span><div></div>")
				.find("div").click(function(){
					$(this).parent().remove();
					$(obj).removeClass("smallA");
					if($(obj).closest("li").find("a.smallA").length == 0){
						$(obj).closest("li").removeClass("schLi")
							  .find("div").removeClass("schDiv");
						
					}
					$("#pageNum").val("1");
					getDate_Ajax(move,1);
				});
	}else if(type == 2){
		$(newLi).html("<span>"+name+"</span><div></div>")
				.find("div").click(function(){
					$(this).parent().remove();
					$(obj).removeClass("schLi")
						  .find("div").removeClass("schDiv");
					$("#pageNum").val("1");
					getDate_Ajax(move,1);
				});
	}else if(type == 3){
		var newName = $(obj).closest("li").find("span").html()+":  "+name;
		$(newLi).html("<span>"+newName+"</span><div></div>")
				.find("div").click(function(){
					$(this).parent().remove();
					$(obj).removeClass("smallA");
					if($(obj).closest("li").find("a.smallA").length == 0){
						$(obj).closest("li").removeClass("schLi")
							  .find("div").removeClass("schDiv");
						
					}
					$("#pageNum").val("1");
					getDate_Ajax(move,1);
				});
	}
	return newLi;
}
function move1(obj){
	for(var i=0; i<obj.length; i++){
		$(obj[i]).css("display","none");
		$("#queBkgrd").append(obj[i]);
	}
	for(var i=0; i<obj.length; i++){
		$(obj[i]).delay(100*i).fadeIn(1000);
	}
}
/*
function move5(obj){
	for(var i=0; i<obj.length; i++){
		$(obj[i]).css("opacity","0");
		$("#queBkgrd").append(obj[i]);
	}
	var ulLeft = $("#queBkgrd").offset().left;
	var oldTop = $(obj[0]).prev().offset().top+340;
	for(var i=0; i<obj.length; i++){
		$(obj[i]).css({"left": -$(obj[i]).offset().left+ulLeft, "top": -$(obj[i]).offset().top+oldTop})
				 .delay(30*i).animate({"left": "0px","top": "0px","opacity": "1"},800);
	}
}
*/
function move2(obj){
	for(var i=0; i<obj.length; i++){
		$(obj[i]).css("display","none");
		$("#queBkgrd").append(obj[i]);
	}
	for(var i=0; i<obj.length; i++){
		$(obj[i]).css({"left": "0px", "top": "0px"});
		$(obj[i]).delay(100*i).slideDown(1000);
	}
}
function move3(obj){
	for(var i=0; i<obj.length; i++){
		$("#queBkgrd").append(obj[i]);
	}
	for(var i=0; i<obj.length; i++){
		$(obj[i]).addClass("reduce");
	}
}
function move4(obj){
	for(var i=0; i<obj.length; i++){
		$("#queBkgrd").append(obj[i]);
	}
	for(var i=0; i<obj.length; i++){
		$(obj[i]).addClass("add");
	}
}
$(function(){
	//运动函数
	var move;
	//用于判断浏览器版本
	var IEversion = $.browser.version;
	var isWebkit = /webkit/.test(navigator.userAgent.toLowerCase());
	var isOpera = /opera/.test(navigator.userAgent.toLowerCase());
	var isMozilla = /firefox/.test(navigator.userAgent.toLowerCase());
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
	//上方选择框的点击事件
	$(".queSch li").click(function(){
		var text = $(this).find("span").html();
		//阻止包含小类的点击事件
		if($(this).find("dl.smallSch").length != 0){
			return ;
		}
		if($(this).hasClass("schLi")){
		}else{
			$(this).addClass("schLi")
				   .find("div").addClass("schDiv");
			$(".queSel ul").append(creatLi(move,text,this,0));
			$("#pageNum").val("1");
			getDate_Ajax(move,1);
		}
	});
	$(".queSch:eq(1) li")
		.children("dl.smallSch")
		.parent().mouseover(function(){
			var nowLeft = 0-140+parseInt($(this).width()/2)+"px";
			$(this).find("dl.smallSch").css("left",nowLeft);
		});
	$(".queSch:eq(1) li")
		.children("dl.smallSch")
		.parent()
		.find("a").click(function(){
			var text = $(this).next().html();
			if( !$(this).hasClass("smallA") ){
				$(this).addClass("smallA")
					   .closest("li").addClass("schLi")
					   .find("div").addClass("schDiv");
				$(".queSel ul").append(creatLi(move,text,this,1));
				$("#pageNum").val("1");
				getDate_Ajax(move,1);
			}
		});
	
	
	
	//清空按钮的点击事件
	$(".clear").click(function(){
		$(".queSel li").remove();
		$(".queSch li").removeClass("schLi")
					   .find("div").removeClass("schDiv");
		$(".queSch:eq(1) li").children("dl.smallSch").parent().find("a").removeClass("smallA");
		$("#pageNum").val("1");
		getDate_Ajax(move,1);
	});
	//消除下方li的右边距
	$("#queBkgrd li:nth-child(3n)").css("margin-right","0px");
	//鼠标移动显示图片和介绍
	$(".queHot dd img").mousemove(function(event){
		var topY = event.pageY+10;
		var leftX = event.pageX+10+"px";
		var totleTop = $(document).scrollTop()+$(window).height();
		
		if($(".curHot").css("display") == "none"){
			var imgSrc = $(this).attr("src");
			var name = $(this).parent().next().find("a").html();
			var text = $(this).parent().next().next().html();
			$(".curHot").find("img").attr("src",imgSrc).end()
						.find(".curName").html(name).end()
						.find(".curText").html(text);
		}
		var hotHei= $(".curHot").height();
		if(hotHei+topY >= totleTop){
			topY = totleTop-hotHei-20;
		}
		$(".curHot").css({"left": leftX,"top": topY+"px","display": "block"});
	}).mouseout(function(){
		$(".curHot").css("display","none");
	});
	//点击显示更多
	if($("#queBkgrd li").length < 12){
		$(".queMore").css("display","none");
	}
	$(".queMore a").click(function(){
		$(".queMore").css("marginTop","0px");
		var pageNum = parseInt($("#pageNum").val());
		$("#pageNum").val(pageNum+1);
		getDate_Ajax(move,2);
	});
	
	//判断浏览器加载css和Js
	if(isWebkit || isOpera || isMozilla){
		$("<link>").attr({rel: "stylesheet",type: "text/css",href: "css/scenicCSS3.css"})
				   .appendTo("head");
		move = [move1,move2,move3,move3];
		Wmove = move;
	}else{
		if(IEversion == 11.0){
			$("<link>").attr({rel: "stylesheet",type: "text/css",href: "css/scenicCSS3.css"})
				   .appendTo("head");
			move = [move1,move2,move3,move4];
			Wmove = move;
		}else{
			move = [move1,move2];
			Wmove = move;
		}
	}
});
function getDate_Ajax(move,type){
	//级别的SQL
	var levelSQl = "";
	var levelLi = $("#levelSql li.schLi");
	for(var i=0; i<levelLi.length; i++){
		levelSQl = levelSQl + $(levelLi[i]).find("span").attr("index")+",";
	}
	if(levelSQl != ""){
		levelSQl = levelSQl.substring(0,levelSQl.length-1);
	}	
	//地理位置的SQL
	var positionSQl = "";
	var positionLi = $("#positionSQl li.schLi");
	for(var i=0; i<positionLi.length; i++){
		positionSQl = positionSQl + $(positionLi[i]).find("span").attr("index")+",";
	}
	if(positionSQl != ""){
		positionSQl = positionSQl.substring(0,positionSQl.length-1);
	}
	
	
	//类别的SQL
	var categorySQL = "";
	var categoryLi = $("#categorySQL li.schLi");
	for(var i=0; i<categoryLi.length; i++){
		if($(categoryLi[i]).find("dd").length == 0){			
			categorySQL = categorySQL + $(categoryLi[i]).find("span").attr("index")+",";
		}else{
			var tempDD = $(categoryLi[i]).find("dd a.smallA");
			for(var j=0; j<tempDD.length; j++){
				categorySQL = categorySQL + $(tempDD[j]).next().attr("index")+",";
			}
		}
	}
	if(categorySQL != ""){
		categorySQL = categorySQL.substring(0,categorySQL.length-1);
	}
	
	var dataSql = levelSQl+";"+positionSQl+";"+categorySQL+";";
	//获取页数
	var pageNum = $("#pageNum").val();
	//通过AJAX获取数据
	$.ajax({
		type: "get",
        url: "frontscenic_queryScenicByOther.action", 
        data : {
			"queryString" : ""+dataSql,
			"pageNum" : ""+pageNum,
			"keywords" : $("#keyW2").val()
		},
       dataType: "text",   
       	success:function(returnData){        
            var returnData1 = returnData.substr(0,4);
			var returnData2 = returnData.substr(4,returnData.length)
			if(returnData1=="true"){
				var obj2 = eval(returnData2);
				//$("#queBkgrd").html("");
				
				if(type=="1"){
					$("#queBkgrd li").remove();
				}
				if(obj2.length < 12){
					$(".queMore").css("display","none");
				}else{
					$(".queMore").css("display","block");
				}
				//3.显示查询景区列表
				creImgLi2(move,obj2);
			}  
        }  	
	});
}
//显示更多修改版
function creImgLi2(move,obj2){
	var imgLi = [];
	var movLen = move.length;
	if(obj2.length == 0){
		alert("抱歉,没有相关数据!");
	}else{
		for(var i=0; i<obj2.length; i++){                                                                   //${fn:split(imageUrl,';')[0]}
			imgLi[i]=$("<li>");
			$(imgLi[i]).append('<img src='+$('#getRootUrl').html()+obj2[i].imageUrl.split(';')[0]+'>' +
			'<div  class=queCon><div class=queLiBk></div><a href=frontscenic_detailScenic.action?model.id='+obj2[i].id+' class=spotName>'+obj2[i].name+'</a><span class="spotText"><p>'+del_html_tags(obj2[i].description)+'</p></span>' +
			'<a href="frontscenic_detailScenic.action?model.id='+obj2[i].id+'" class="spotPal">进入该景点</a> ' +
			'<a href="frontnote_getAboutNoteByScenicId.action?scenicId='+obj2[i].id+'" class="spotPal">查看相关游记</a></div>');
			if((i+1)%3 == 0){
				$(imgLi[i]).css("marginRight","0px");
			}
		}
		move[parseInt(Math.random()*movLen)](imgLi);
	}
		
}
//去标签
function del_html_tags(str)
{
    var words = '';
    words = str.replace(/<[^>]+>/g,"");
    words = words.replace("<","");
    return words;
}

