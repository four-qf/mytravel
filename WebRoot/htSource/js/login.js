$(document).ready(function(){
	$("#type").blur(function() {
		var type = $("#type").val();
		if (type == "") {
			$("#type_msg").html("类型不能为空");
		} else {
			$("#type_msg").html("");
		}
	});

	$("#title").blur(function() {
		var title = $("#title").val();
		if (title == "") {
			$("#title_msg").html("标题不能为空");
		} else {
			$("#title_msg").html("");
		}
	});

	$("#origin").blur(function() {
		var origin = $("#origin").val();
		if (origin == "") {
			$("#origin_msg").html("来源不能为空");
		} else {
			$("#origin_msg").html("");
		}
	});
});

function sub() {
	var name = $("#user").val();
	var pwd = $("#pwd").val();
	var code = $("#chknumber").val();
	if (name == "" || pwd == "" || code == "") {
		alert("请正确填写登录信息");
		return false;
	} else {
		$("#loginForm").submit();		
	}
};