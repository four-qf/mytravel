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
function ajax(url, fnSucc, fnFaild)
{
	var oAjax=null;
	if(window.XMLHttpRequest)
	{
		oAjax=new XMLHttpRequest();
	}
	else
	{
		oAjax=new ActiveXObject("Microsoft.XMLHTTP");
	}
	oAjax.open("GET", url, true);
	oAjax.send();
	oAjax.onreadystatechange=function ()
	{
		if(oAjax.readyState==4)	//读取完成
		{
			if(oAjax.status==200)	//成功
			{
				fnSucc(oAjax.responseText);
			}
			else
			{
				if(fnFaild)
				{
					fnFaild(oAjax.status);
				}
			}
		}
	};
}
function searchMouseover(event){ 
	var oEvent=event||window.event;
	oEvent.stopPropagation?oEvent.stopPropagation():oEvent.cancelBubble=true;
	var searchView = document.getElementById("search");
	searchView.style.display = "block";
} 
function hide(event){
	var oEvent=event||window.event;
	oEvent.stopPropagation?oEvent.stopPropagation():oEvent.cancelBubble=false;
	var searchView = document.getElementById("search"); 
//	var searchInput = searchView.getElementsByTagName("input")[0];
	searchView.style.display = "none";
//	searchInput.value = "";
}
function loginInputFocus(choice,id){
	var loginInput = document.getElementById(id);
	if(choice == "true"){
		if(id == "password"){
			loginInput.type = "password";
		}
		if(loginInput.value == "请输入账号" || loginInput.value == "请输入密码"){
			loginInput.value = "";
		}
	}else{
		if(id == "accountNumber"){
			if(loginInput.value == ""){
				loginInput.value = "请输入账号";
			}
		}else{
			loginInput.type = "text";
			if(loginInput.value == ""){
				loginInput.value = "请输入密码";
			}else{
				loginInput.type = "password";
			}
		}
	}
}
//function myAddEvent(obj, ev, fn)
//{
//	if(obj.attachEvent)
//	{
//		obj.attachEvent('on'+ev, fn);
//	}
//	else
//	{
//		obj.addEventListener(ev, fn, false);
//	}
//}
//function alertaa(){
//	alert("进入了");
//}
document.onclick=function(event){
	hide(event);
//	var loginInputAccount = document.getElementById("accountNumber");
//	var loginInputPassword = document.getElementById("password");
//	loginInputAccount.value = "请输入账号";
//	loginInputPassword.value = "请输入密码";
//	loginInputPassword.type = "text";
};
window.onscroll=function(){
	var returnTop = document.getElementById("returnTop");
//	var loginView = document.getElementById("loginView");
	var searchView = document.getElementById("search");
	searchView.style.display = "none";
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
//			loginView.style.display = "none";
		}
	}
	//alert(document.body.scrollTop);
};