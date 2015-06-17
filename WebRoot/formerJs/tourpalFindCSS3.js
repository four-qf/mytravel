function setCss3(obj,attrObj){//设置CSS3样式
	for(var i in attrObj){
		var newObj = i;
		if(newObj.indexOf("-")>0){
			var num=newObj.indexOf("-");
			newObj = newObj.replace(newObj.substr(num,2),newObj.substr(num+1,1).toUpperCase());
		}
		obj.style[newObj]=attrObj[i];
		newObj = newObj.replace(newObj.charAt(0),newObj.charAt(0).toUpperCase());
		obj.style["webkit"+newObj]=attrObj[i];
		obj.style["moz"+newObj]=attrObj[i];
		obj.style["o"+newObj]=attrObj[i];
		obj.style["ms"+newObj]=attrObj[i];
	}
}
function setCss3Evt(obj,name,fn){//为CSS3添加动画监听器
	if(name == "animationend"){
		$(obj).bind("animationend",fn);
		$(obj).bind("webkitAnimationEnd",fn);
	}else if(name == "animationstart"){
		$(obj).bind("animationstart",fn);
		$(obj).bind("webkitAnimationStart",fn);
	}	
}
//动态书写开始的动画CSS3
function startStyle(newStyle,left,top,width,height){
	//根据浏览器大小设置新的旋转动画
	var ruleStyle = 
		"@-webkit-keyframes roXimg{"
		+"0%{left: 0px;top: 0px;width: 345px;height: 210px;-webkit-transform: rotateX(0deg);}"
		+"50%{-webkit-transform: rotateX(-180deg);}"
		+"100%{left: "+left+"px;top: "+top+"px;width: "+width+"px;height: "+height+"px;-webkit-transform: rotateX(-180deg);}}"

		+"@keyframes roXimg{"
		+"0%{left: 0px;top: 0px;width: 345px;height: 210px;transform: rotateX(0deg);}"
		+"50%{transform: rotateX(-180deg);}"
		+"100%{left: "+left+"px;top: "+top+"px;width: "+width+"px;height: "+height+"px;transform: rotateX(-180deg);}}";
	$(newStyle).html(ruleStyle);
}
$(function(){
	//用于判断浏览器版本
	var IEversion = $.browser.version;
	var isWebkit = /webkit/.test(navigator.userAgent.toLowerCase());
	var isOpera = /opera/.test(navigator.userAgent.toLowerCase());
	var isMozilla = /firefox/.test(navigator.userAgent.toLowerCase());
	//获取元素
	var spotImg = document.getElementById("spotImg");
	var newStyle = document.getElementById("newStyle");
	var loading = document.getElementById("loading");
	var nowWidth;//获取浏览器的宽度
	var nowHeight;//获取浏览器的高度
	var nowLeft;//获取当前点击的元素的Left
	var nowTop;//获取当前点击的元素的Top;
	var index = 0;
	var nowHref;

	//右边图片点击事件
	$("#spotImg dl").click(function(){
		
		//添加缩小的class
		$("#spotImg li").addClass("reduce");
		//设置zIndex的值
		$("#spotImg li").css("zIndex",1);
		$(this).parent().css("zIndex",10);
		index = $(this).parent().index();
		//取得left和top
		nowLeft = -$(this).offset().left;
		nowTop = $(document).scrollTop()-($(this).offset().top-32);
		//取得浏览器宽度和高度
		nowWidth = $(window).width();//获取浏览器的宽度
		nowHeight = $(window).height()-32;//获取浏览器的高度
		startStyle(newStyle,nowLeft,nowTop,nowWidth,nowHeight);
	});
	//为Li运动添加animationend事件
	setCss3Evt($("#spotImg li"),"animationend",function(){
		setCss3(this,{"transform" : "translateZ(-200px)"});
		$(this).removeClass("reduce");
		$("#spotImg li:eq("+index+")").addClass("add");
		$("#spotImg dl:eq("+index+")").addClass("roXimg");
		//兼容IE的写法
		if(!isWebkit && !isMozilla){
			$("#spotImg dl:eq("+index+")").find("dd:eq(1)").css("display","none");
		}
	});
	//为dl运动添加animationend事件
	setCss3Evt($("#spotImg dl"),"animationend",function(){
		if($(this).hasClass("roXimg")){
			$("body").css("background","#FFF");
			$(this).css({"left" : nowLeft,"top" : nowTop,"width" : nowWidth,"height" : nowHeight}).removeClass("roXimg");
			
			$(".header,.topline,#findMain,.footer0,.footer2").css("display","none");
			loading.style.display="block";
			$(loading).addClass("move");
			$(".circle1").addClass("move1");
			$(".circle2").addClass("move2");
			$(".circle3").addClass("move3");
			$(".circle4").addClass("move4");
			nowHref = $(this).find("a").attr("href");
		}
	});
	
	setCss3Evt(loading,"animationend",function(){
		$(this).css("display","none");
		//FF下的兼容
		if(isMozilla){
			location.reload();
		}
		location.href=nowHref;
	});

	$(".spotCss3 a").click(function(event){
		var evt = event || window.event;
		evt.preventDefault();
	});
	
});










