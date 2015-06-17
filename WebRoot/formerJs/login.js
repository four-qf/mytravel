function getByClass(objParent, Name){
	var result=[];
	var element = objParent.getElementsByTagName("*");
	for(var i=0; i<element.length; i++){
		if(element[i].className == Name){
			result.push(element[i]);
		}	
	}
	return result;
}
function getStyle(obj, name)
{
	if(obj.currentStyle){
		return obj.currentStyle[name];
	}else{
		return window.getComputedStyle(obj, false)[name];
	}
}
function rollMove(oUl){
	var left=parseInt(getStyle(oUl, "left"));
	if(left <= -1015){
		oUl.style.left=0+"px";
	}else{
		oUl.style.left=left-2+"px";
	}
}
function startMove(obj, json, fnEnd)
{
	clearInterval(obj.timer);
	obj.timer=setInterval(function (){
		var bStop=true;
		for(var attr in json){
			var cur=0;
			
			if(attr=='opacity'){
				cur=Math.round(parseFloat(getStyle(obj, attr))*100);
			}else{
				cur=parseInt(getStyle(obj, attr));
			}
			
			var speed=(json[attr]-cur)/6;
			speed=speed>0?Math.ceil(speed):Math.floor(speed);
			
			if(cur!=json[attr])
				bStop=false;
			
			if(attr=='opacity'){
				obj.style.filter='alpha(opacity:'+(cur+speed)+')';
				obj.style.opacity=(cur+speed)/100;
			}else{
				obj.style[attr]=cur+speed+'px';
			}
		}
		if(bStop){
			clearInterval(obj.timer);
						
			if(fnEnd)fnEnd();
		}
	}, 30);
}


window.onscroll=function(){
	var returnTop = document.getElementById("returnTop");
	if(returnTop != null){
		if(document.body.scrollTop < 400){
			returnTop.style.display="none";
			if(document.documentElement.scrollTop < 400){
				returnTop.style.display="none";
			}else{
				returnTop.style.display="inline";
			}
		}else{
			returnTop.style.display="inline";
		}
	}
};

//用户推出登陆
function userExit(){
$.ajax(
{
	type: "get",
	url: "user_loginOut.action",
	dataType: "text",
	success: function(data)
	{
		if (data==1)
		{
			location.href="index.action";
		}
		else
			alert("操作失败！");
		}
	}
		);
}
//搜索
$(function(){
	$("#searchSub").click(function(){
		var type =  parseInt($("#searchSel").find("option:selected").attr("title"));
		 if(type==2)
		{
			//文章搜索
			$("#searchForm").attr("action","article_search.action");
			$("#searchCon").val($("#searchCon").val()+type);
			$("#searchForm").append($("#searchCon"));
			$("#searchForm").submit();
		}else if(type==3)
		{
			//文章搜索
			$("#searchForm").attr("action","down_search.action");
			$("#searchCon").val($("#searchCon").val()+type);
			$("#searchForm").append($("#searchCon"));
			$("#searchForm").submit();
		}else if(type==4)
		{
			//景区搜索
			$("#searchForm").attr("action","frontscenic_listByfront.action");//
			$("#searchCon").val($("#searchCon").val()+type);
			$("#searchForm").append($("#searchCon"));
			$("#searchForm").submit();
		}else if(type==5)
		{
			//游记搜索
			$("#searchForm").attr("action","frontnote_search.action");//
			$("#searchCon").val($("#searchCon").val()+type);
			$("#searchForm").append($("#searchCon"));
			$("#searchForm").submit();
		}
	});
});