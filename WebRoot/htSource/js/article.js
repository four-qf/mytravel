
function sub(){
		var type = $("#type").val();
		var title = $("#title").val();
		var origin = $("#origin").val();
		var container = $("#container").val();
		var ishot = $("#ishot").val();
		alert(ishot);
		if(type==""||title==""||origin==""){
			alert("带 s 的内容为必填内容");
			return false;
		}else{
			$.ajax({
				type : "post",
				url : "m_addArticle.action",
				dataType : "text",
				data : {
					"type" : type,
					"article.title":title,
					"article.origin":origin,
					"article.content":container,
					"article.ishot":ishot
				},
				success : function(returnData) {
					if(returnData==true.valueOf().toString()){
						alert("发布成功");
						window.self.location = "m_findArticle?type="+type+"&pageNo=1";
					}else if(returnData==false.valueOf().toString()){
						alert("发布失败");
					}
				}
			});
		}
	};