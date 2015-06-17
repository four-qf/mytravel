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
	
	$(".passAgian").click(function(){
		var index = $(this).val();
		if(index == "请再一次输入密码"){
			$(this).val("");
		}
	}).blur(function(){
		if($(this).val() == ""){
			$(this).val("请再一次输入密码");
		}
	});
	
	$(".email").click(function(){
		var index = $(this).val();
		if(index == "请输入邮箱"){
			$(this).val("");
		}
	}).blur(function(){
		if($(this).val() == ""){
			$(this).val("请输入邮箱");
		}
	});
	
	$("#submit").click(function(){
		var name = $(".name").val();//姓名
		var pass = $(".pass").val();//密码
		var passAgian = $(".passAgian").val();//第二次密码
		var email = $(".email").val();//邮箱
		var yzm = $(".yzm").val();//验证码
		if(name == "" || name == "请输入用户名"){
			alert("用户名为空,请输入用户名!");
			return false;
		}
		if(pass == "" || pass == "请输入密码"){
			alert("密码为空,请输入密码!");
			return false;
		}
		if(passAgian == "" || passAgian == "请再一次输入密码"){
			alert("请再一次输入密码!");
			return false;
		}
		if(email == "" || email == "请输入邮箱"){
			alert("邮箱为空,请输入邮箱!");
			return false;
		}
		if(yzm == "" || yzm == "请输入验证码"){
			alert("验证码为空,请输入验证码!");
			return false;
		}
		if(pass != passAgian){
			alert("两次密码不一致,请重新输入!");
		}
	});
});