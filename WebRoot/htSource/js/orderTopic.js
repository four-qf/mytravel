var finalResult = "";
var finalId = "";
$(document).ready(function() {
	//加样式
	var yiJiCate = $("#yiJiCate").css("font-family", "Arial").css("font-size","16pt");
	
	//初始化ID与name的字符串				
	var dataIniti = $("#yiJiCate li").map(function() { return $(this).children().html(); }).get();
	finalResult = dataIniti.join("-");
	var dataIdIniti = $("#yiJiCate li").map(function() { return $(this).find("div[class=getIdsClass]").attr("title"); }).get();
	finalId = dataIdIniti.join("-");	
	
	//添加Jquery的插件
	yiJiCate.dragsort( { 
					dragSelector: "li", 
					dragBetween: true, 
					dragEnd: saveOrder, 
					placeHolderTemplate: "<li class='placeHolder'><div></div></li>" 
					});//end dragsort
	$("#saveOrder").click(function() {
		if (finalResult != "") {
			$.ajax({
				type: "POST",
				url: "manager/m_updateTypeOrder.action",
				dataType: "text",
				data: {
					 	"orderIdString":finalId,
					 	"orderString":finalResult
					   },
				success: function(returnData) {
					if (returnData == 1) {
						alert("序号更新成功2");
					} else {
						alert(finalId+"++"+dataIdIniti);
						alert("序号更新失败");
					}
				}
			});//end $.ajax()
		} else {
			alert("对不起，没有要排序的栏目");
		}
	});			
});
var saveOrder = function() {
	//拼接Id字符串
	var dataId = $("#yiJiCate li").map(function() { return $(this).find("div[class=getIdsClass]").attr("title"); }).get();
	finalId = dataId.join("-"); 
	//拼接name字符串
	var data = $("#yiJiCate li").map(function() { return $(this).children().html(); }).get();
	finalResult = data.join("-");
}
