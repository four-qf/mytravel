window.onload=function(){
	var Timer=null;
	var returnTop = document.getElementById("returnTop");
	var mainDiv = document.getElementById("main1");
	var rightDiv = getByClass(mainDiv, "right")[0];
	if(document.getElementById("footer1") != null){
		var footer1 = document.getElementById("footer1");
		var footer1Content = getByClass(footer1, "content")[0];
		var footer1Link = getByClass(footer1Content, "link")[0];
		var count = footer1Link.getElementsByTagName("li").length;

		footer1Link.style.height = count*20+"px";
		footer1Content.style.height = count*20+30+"px";
		footer1.style.height = count*20+55+"px";
	}
	if(getStyle(rightDiv, "height") != "auto"){
		mainDiv.style.height = parseInt(getStyle(rightDiv, "height"))+20+"px";
	}else{
		mainDiv.style.height = parseInt(rightDiv.offsetHeight)+20+"px";
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