function isFull(obj){
	for(var i=0; i<obj.length; i++){
		if(obj[i] == 0){
			$("#checkSta").html("抱歉，还有未完成的内容");
			return ;
		}
	}
	$("#checkSta").html("可以提交了，请提交。");
}
$(function(){
	var check;//
	if($("#editUI").val()!="true")
	 	check = [0,0,0,0,0];
	else
		check = [1,1,1,1,1];
	$("#pubMain input[class=name]").change(function(){
		if($(this).attr("value") == ""){
			check[0]=0;
			$(this).attr("placeholder","请输入游记标题");
			$("#checkSta").html("抱歉，标题不能为空");
			return;
		}else if($(this).attr("value").length<3){
			check[0]=0;
			$("#checkSta").html("抱歉，标题至少3个字");
			return;
		}else{
			check[0]=1;
			isFull(check);	
		}
		
	});
	//城市选择事件
	$("#city").change(function(){
		if($(this).attr("index") != "-1"){
			check[1]=1;
			isFull(check);
		}else{
			check[1]=0;
			$("#checkSta").html("没有选择城市，请填写完整！");
			return;
		}
	});
	//景点名字选择
	$("#spot").change(function(){
		if($(this).attr("index") != "-1"){
			check[2]=1;
			isFull(check);
			//设置对应的景区id
			$("#sId").val($(this).find("option:selected").attr("value"));
		}else{
			check[2]=0;
			$("#checkSta").html("抱歉，不能提交，请填写完整");
			return;  
		}
	});
	
		//内容失去焦点验证 text 
	$(".text").find("iframe").contents().find("body").keyup(function(){
		if($(this).html().length>20){
			check[3]=1;
			isFull(check);
		}else{
			check[3]=0;
			$("#checkSta").html("抱歉，游记内容未满20字，不能提交，请填写完整");
		}
	});
	//图片
	$("#imageInput").change(function(){
		if($(this).val()==null)
		{
			check[4]=0;
			$("#checkSta").html("抱歉，不能提交，请插入游记首页图片");
			return; 
		}else
		{
			check[4]=1;
			isFull(check);
		}
	});
	//提交按钮
	$(".status #sub0").click(function(){
		var _this =this;
		//提交之前检查 除内容的其他属性是否完成
		var flag=true;
			for(var i=0;i<check.length;i++)
			{if(check[i]==0){flag=false;}}
		if(flag)
		{
			if($.trim($(".text").find("iframe").contents().find("body"))==""){
				$(".checkSta").html("抱歉2，不能提交，请填写内容。");
				return false;
			}else{
				//设置发送数据
				//设置time内容图片标志
				$("#pubMain").append('<input type="hidden" name="pictime" value='+pictime+' />');
				var sid = $("#spot").find("option:selected").attr("value");
				$("#sId").val(sid);
				var cityId=$("#city").find("option:selected").attr("value");
				$("#cityId").val(cityId);
				//content
				$("#xhecontent").val();
				document.getElementById("addform").submit();
			}
		}else{
			$(".checkSta").html("抱歉3，不能提交，请填写完整。");
			return false;
		}
	});
});

//选择景区ajax
	function findScenicByCity()
	{
		$.ajax(
			{	type: "get",
				url: "frontnote_cityToScenic.action",
				dataType: "text",
				data : {
					"cityId" : $("#city").val()//发送城市id  返回第一个匹配元素的 value 属性的值。
				},
				success: function(returnData)
				{
					var returndata = returnData.substr(0,4);
					var returndata2 = returnData.substr(4,returnData.length)
					if (returndata=="true")
						{	
						var obj = eval(returndata2);
						var newSelect = "<option value=-1>请选择景点名称</option>" ;
						//拼接字符串	
							if(obj.length<1)
							{
								//没有城市
							}else
								for(var i=0;i<obj.length;i++)
								{
									newSelect +="<option value='"+obj[i].id+"'>"+obj[i].name+"</option>";
								}
							if($("#developed").find("#spot"))
							{
									$("#developed").find("#spot").html("");
									$("#developed").find("#spot").append(newSelect);
							}
						}
					else
						alert("returnData："+returnData);
				}
			}
		);
	}