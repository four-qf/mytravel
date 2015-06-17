window.onload=function(){
	if(document.getElementById("footer1") != null){
		var footer1 = document.getElementById("footer1");
		var footer1Content = getByClass(footer1, "content")[0];
		var footer1Link = getByClass(footer1Content, "link")[0];
		var count = footer1Link.getElementsByTagName("li").length;

		footer1Link.style.height = count*20+"px";
		footer1Content.style.height = count*20+30+"px";
		footer1.style.height = count*20+55+"px";
	}
	var left = document.getElementById("left");
	var right = document.getElementById("right");
	var list = getByClass(right, "list")[0];
	var main = document.getElementById("main1");
	if(getStyle(left, "height") != "auto"){
		right.style.height = parseInt(getStyle(left, "height"))+25+"px";
		list.style.height = getStyle(left, "height");
		main.style.height = parseInt(getStyle(left, "height"))+60+"px";
	}else{
		right.style.height = parseInt(left.offsetHeight)+25+"px";
		list.style.height = parseInt(left.offsetHeight)+"px";
		main.style.height = parseInt(left.offsetHeight)+60+"px";
	}
};
