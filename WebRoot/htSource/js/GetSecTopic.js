$(document).ready(function() {
	var selectSelected = $("#selectSelected");  //隐藏input标签
	var selectSelectedVal = selectSelected.val();
	var secSelct = $("#secSelct");				//select标签
	if (selectSelectedVal.length != 0) {
		secSelct.val(selectSelectedVal);
	}
	var secSelctVal = secSelct.val();
	secSelct.change(function() {				//select标签的change事件
		secSelctVal = secSelct.val();
	}); // end change
	
	$("#displaySecTopicBtn").click(function() {
		if (secSelctVal == "父级栏目") {
			alert("请选择一级栏目");
			return ;
		}
		$("#topicNameHideInput").attr("value", secSelctVal);
		var hideSecTopicForm = $("#hideSecTopicForm");
		hideSecTopicForm.attr("action", "getSecTopicByFirName.action");
		hideSecTopicForm.submit();
	});//end click
});
