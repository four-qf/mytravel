var finalSecTopicName = ""; 
$(document).ready(function() {
	var erJiCate = $("#erJiCate").css("font-family", "Arial").css("font-size","16pt");
	
	//初始化
	var dataIniti = $("#erJiCate li").map(function() { return $(this).children().html(); }).get();
	finalSecTopicName = dataIniti.join("-");
	
	//添加Jquery的插件
	erJiCate.dragsort( { 
					dragSelector: "li", 
					dragBetween: true, 
					dragEnd: saveSecOrder, 
					placeHolderTemplate: "<li class='placeHolder'><div></div></li>" 
					});//end dragsort
	$("#saveSecBtn").click(function() {
		if (finalSecTopicName != "") {
			$.ajax({
				type: "POST",
				url: "updateSecTopicsOrder.action",
				dataType: "text",
				data: {
					 	"secOrderNameStr":finalSecTopicName
					   },
				success: function(returnData) {
					if (returnData == 1) {
						alert("序号更新成功");
					} else {
						alert("序号更新失败2");
					}
				}
			});//end $.ajax()
		} else {
			alert("对不起，没有要排序的栏目");
		}
	});// end click
});
var saveSecOrder = function() {
	//拼接name字符串
	var data = $("#erJiCate li").map(function() { return $(this).children().html(); }).get();
	finalSecTopicName = data.join("-");
}
