window.onload=function(){
	var returnTop = document.getElementById("returnTop");
	var mainDiv = document.getElementById("main2");
	var rightDiv = getByClass(mainDiv, "right")[0];
	
	var left = document.getElementById("left");
	var list = getByClass(rightDiv, "list")[0];
	var leftHeight = null;
	var rightHeight = null;
	if(getStyle(left, "height") != "auto"){
		leftHeight = parseInt(getStyle(left, "height"));
	}else{
		leftHeight = parseInt(left.offsetHeight);
	}
	if(getStyle(rightDiv, "height") != "auto"){
		rightHeight = parseInt(getStyle(rightDiv, "height"));
	}else{
		rightHeight = parseInt(rightDiv.offsetHeight);
	}
	if(leftHeight > rightHeight){
		if(getStyle(left, "height") != "auto"){
			rightDiv.style.height = parseInt(getStyle(left, "height"))+"px";
			list.style.height = parseInt(getStyle(left, "height"))-35+"px";
			mainDiv.style.height = parseInt(getStyle(left, "height"))+40+"px";
		}else{
			rightDiv.style.height = parseInt(left.offsetHeight)+25+"px";
			list.style.height = parseInt(left.offsetHeight)+"px";
			mainDiv.style.height = parseInt(left.offsetHeight)+90+"px";
		}
	}else{
		if(getStyle(rightDiv, "height") != "auto"){
			mainDiv.style.height = parseInt(getStyle(rightDiv, "height"))+30+"px";
		}else{
			mainDiv.style.height = parseInt(rightDiv.offsetHeight)+30+"px";
		}
	}
	
	if(document.getElementById("footer1") != null){
		var footer1 = document.getElementById("footer1");
		var footer1Content = getByClass(footer1, "content")[0];
		var footer1Link = getByClass(footer1Content, "link")[0];
		var count = footer1Link.getElementsByTagName("li").length;

		footer1Link.style.height = count*20+"px";
		footer1Content.style.height = count*20+30+"px";
		footer1.style.height = count*20+55+"px";
	}

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
};
