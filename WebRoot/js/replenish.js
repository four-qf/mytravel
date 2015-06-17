$(function(){
	$(".rightList input[type=file]").change(function(){
		var newVal = $(this).attr("value");
		if(newVal != ""){
			$(".fileSel input").attr("value",newVal);
			$(this).next().next().addClass("impBg2");
		}else{
			$(".fileSel input").attr("value","");
			$(this).next().next().removeClass("impBg2");
		}
	});
	$(".rightList .selBtn").click(function(){
		$(".rightList input[type=file]").click();
	});
	$("#submit").click(function(){
		var time = $("#Wdate").val();
		if(time == ""){
			alert("请选择日期!");
			return false;
		}
		var name = $("#name").val();
		if(name == ""){
			alert("请填写真实姓名!");
			return false;
		}
		var cityName = $("#cityName").val();
		if(cityName == ""){
			alert("请填写所在居住地!");
			return false;
		}
		var tel = $("#tel").val();
		if(tel == ""){
			alert("请填写联系电话!");
			return false;
		}
	});
});