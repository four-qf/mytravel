window.onload=function(){
	var textFirst = document.getElementById("textFirst");
	var textFirstA = textFirst.getElementsByTagName("a");
	textFirstA[0].style.display="inline";
	var Timer=null;
	var nowIndex=0;
	var scenicSpots = document.getElementById("scenicSpots");//取得景点景区的DIV
	var scenicA = scenicSpots.getElementsByTagName("a");
	for(var i=1; i<scenicA.length; i++){
		if(scenicA[i].innerHTML.length>4){
			scenicA[i].innerHTML=scenicA[i].innerHTML.substring(0,4);
		}
	}
	var Bodytimer=setInterval(moveNext, 5000);//自动播放的载体
	var adImageView = document.getElementById("adImageView");//取得adImageView的DIV
	var adBackground = getByClass(adImageView, "backgroundImage")[0];//取得adImageView下的背景图片
	var buttomImage = getByClass(adImageView, "buttom")[0];//取得adImageView下的buttom的DIV
	var circleImage = buttomImage.getElementsByTagName("img");//取得buttom里面的img(圆点图片)
	var buttomText = getByClass(buttomImage, "text")[0].getElementsByTagName("a");//取得buttom下的text的DIV下的a标签(图片的名字)
	var left = getByClass(adImageView, "left")[0];//向左的DIV
	var right = getByClass(adImageView, "right")[0];//向右的DIV
	var returnTop = document.getElementById("returnTop");//取得返回顶部的DIV
	//中间滚动图片
	var allContent = document.getElementById("allContent");
	var allContentBackground = getByClass(allContent, "background")[0];//取得中间滚动的图片的DIV
	var rollTimer = setInterval(starRollMove,30);//自动滚动的载体
	allContentBackground.innerHTML+=allContentBackground.innerHTML;//扩充两倍
	
	if(document.getElementById("footer1") != null){
		//取得底部的各种DIV
		var footer1 = document.getElementById("footer1");
		var footer1Content = getByClass(footer1, "content")[0];
		var footer1Link = getByClass(footer1Content, "link")[0];
		var count = footer1Link.getElementsByTagName("li").length;

		//调节底部的友情链接的高度(有问题，当小于90的时候)
		footer1Link.style.height = count*20+"px";
		footer1Content.style.height = count*20+30+"px";
		footer1.style.height = count*20+55+"px";
	}
	
	//调用中间图片滚动函数
	function starRollMove(){
		rollMove(allContentBackground);
	}
	
	//圆点图片点击后的变化函数
	function change(index){
		for(var j=0; j<circleImage.length; j++){
			circleImage[j].src = "formerImage/circle.png";
			buttomText[j].style.display = "none";
		}
		circleImage[index].src = "formerImage/circle2.png";
		buttomText[index].style.display = "inline";
		startMove(adBackground, {left: -660*nowIndex});
	}
	
	//底部圆点图片的处理
	for(var i=0; i<circleImage.length; i++){
		circleImage[i].index=i;
		circleImage[i].onclick=function(){
			nowIndex=this.index;
			change(nowIndex);
		};
	}
	//向左查看图片的DIV的点击事件
	left.onclick=function(){
		nowIndex--;
		if(nowIndex == -1){
			nowIndex = 4;
		}
		change(nowIndex);
	};
	
	//向右查看图片的点击事件
	right.onclick=function(){
		nowIndex++;
		if(nowIndex == 5){
			nowIndex = 0;
		}
		change(nowIndex);
	};
	
	//自动播放显示下一个
	function moveNext(){
		nowIndex++;
		if(nowIndex==circleImage.length){
			nowIndex=0;
		}
		change(nowIndex);
	}
	
	//广告位图片自动播放
	adBackground.onmouseover=function (){
		clearInterval(Bodytimer);
	};
	adBackground.onmouseout=function (){
		Bodytimer=setInterval(moveNext, 5000);
	};
	
	//点击返回事件
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
	//中间滚动图片的自动滚动
	allContent.onmouseover=function (){
		clearInterval(rollTimer);
	};
	allContent.onmouseout=function (){
		rollTimer=setInterval(starRollMove,30);
	};
};
