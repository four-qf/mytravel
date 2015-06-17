$(function(){
	$(".name").click(function(){
		var index = $(this).val();
		if(index == "请输入用户名"){
			$(this).val("");
		}
	}).blur(function(){
		if($(this).val() == ""){
			$(this).val("请输入用户名");
		}
	});
	
	$(".pass").click(function(){
		var index = $(this).val();
		if(index == "请输入密码"){
			$(this).val("").prop("type","password");
		}
	}).blur(function(){
		if($(this).val() == ""){
			$(this).prop("type","text").val("请输入密码");
		}
	});
	
	$(".yzm").click(function(){
		var index = $(this).val();
		if(index == "请输入验证码"){
			$(this).val("");
		}
	}).blur(function(){
		if($(this).val() == ""){
			$(this).val("请输入验证码");
		}
	});
});