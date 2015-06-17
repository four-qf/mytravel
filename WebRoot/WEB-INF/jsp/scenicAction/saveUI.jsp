<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>智慧旅游</title>
	<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/zhjd_htglxt/css/style.css" type="text/css" />
		<script type="text/javascript">
		function is(num) {
			if (num == 1) {
				$("#type1").show();
				$("#type2").hide();
			} else {
				$("#type2").show();
				$("#type1").hide();
			}
		}
		</script>
	
		<style type="text/css">
		ul li{
			list-style-type:  none;
		}
		#ScenicClass input, #ScenicClass label{
			margin-top: 10px;
		}
		#ScenicClass label{
			margin-right: 5px;
		}
		</style>
<!-- 多图上传 -->	
<script type="text/javascript" src="${pageContext.request.contextPath}/scenicScript/jquery-1.8.3.js"></script>	
<link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath}/scenicScript/zhu_Pic_Up.css" type="text/css"  />
<script type="text/javascript" src="${pageContext.request.contextPath}/scenicScript/zhu_Pic_Up.js"></script> 

<!-- 类别选择 树-->
	<script language="javascript" src="${pageContext.request.contextPath}/scenicScript/jquery_treeview/jquery.treeview.js"></script>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/scenicScript/file.css" />
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/scenicScript/jquery_treeview/jquery.treeview.css" />
	<script language="javascript">
			$(function(){
			//指定事件处理函数
			$("[name=scenicClassIds]").click(function(){
				$(this).siblings("ul").find("input").attr("checked", this.checked);
				if (this.checked==true)
				{
					$(this).parents("li").children("input").attr("checked", true);
				}
				});
		});
		$("#tree").treeview();
		//
		function subClass(){
				var arr= $('#tree').find("input[checked='checked']");		
				var str="";
				var isUpdateByUpdate="";
				var scenicID="";
				for(var i=0;i<arr.length;i++)
				{
					str+=arr[i].value+",";	
				}
				//更新所需数据
				if($("#hiddenImgUrl").html()!="fail"){
					isUpdateByUpdate = "update";
					scenicID = document.getElementById("hiddenImgUrl").title;
				}
				//发送数据
				$.ajax(
				{
				 url: "subscenic_ClassUp.action",
				 type: "post",
				 dataType: "text",
				 data: {
						"SubScenicClasses": str,	
						"scenicID": scenicID,
						"isUpdateByUpdate": isUpdateByUpdate
					},
					success: function(returnData)
					{	
							if(returnData=="success")
							{
								document.getElementById("class_tanchu").value="类别已提交";
								$("#class_tanchu").attr("disabled","disabled");
							}else
							{
								document.getElementById("class_tanchu").value="程序出错请联系开发人员";
							}
							$("#close_Class").click();
					}
					})
			}
	</script>
<!-- 弹出层-->
<script type="text/javascript">
var FrameisACCSEE=false;
function showFrame(idname,closeId) {
	//调用弹出层前先判断是否是修改页面 alert($("#hiddenImgUrl").html()+",,"+FrameisACCSEE);
if($("#hiddenImgUrl").html()!="fail" && !FrameisACCSEE)
{
	FrameisACCSEE=true;
	//首先解析图片地址数组
	var headUrl = $("#hiddenRootUrl").html()+"/";
	var allUrl = $("#hiddenImgUrl").html();
	var arr = allUrl.split(";");
	//对修改图片进行初始化
		//2.pics_div下 img初始化
		for(var i=0;i<arr.length-1;i++)
		{
			var PicDiv = document.createElement('div');
			var PicsDiv = document.getElementById('pics_div');
			//创建img标签
			var UNImg = document.createElement('img');
			UNImg.title=i;
			//创建file标签
			var fileInput = document.createElement('input');
			fileInput.title = i;
			fileInput.type='file';
			fileInput.name='scenicImage';
			fileInput.className='fileInput';
			//1.pic_butn下file标签初始化
			$("#pic_butn").append(fileInput);
			UNImg.src=headUrl+arr[i];
			UNImg.width = 160;
			UNImg.height = 90;
			//取消按钮
			var btnCancel = document.createElement('input');
			btnCancel.className='btnCancel';
			btnCancel.type='button';
			btnCancel.value='实时删除';
			btnCancel.bottom = '0';
			btnCancel.style.width='70px';
			btnCancel.style.height= '25px';
			btnCancel.onclick=function()
			{
				var _this=this;
				//向数据库发送
			$.ajax(
			{
				 url: "scenic_cancelImg.action",
				 type: "post",
				  dataType: "text",
				 data: {"cancelImgUrl":this.parentNode.children[2].src.split("images")[1],"model.id":document.getElementById("hiddenImgUrl").title},
				success: function(returnData)
				{
					if (returnData=="success")
						{
							//移去file标签和img
							var inputs = document.getElementById("pic_butn").children;
							var img2 = _this.parentNode.children[2];
							for(var i=2;i<inputs.length;i++){
								if(inputs[i].title==img2.title)
								{
									TFile = inputs[i];
								}
							}
							TFile.parentNode.removeChild(TFile);
							_this.parentNode.parentNode.removeChild(_this.parentNode);
						}
					else{
							document.getElementById('pic_instruct').value="上传图片失败请联系开发人员update";
							alert("操作失败！"+returnData);
						}
				}
			}
				);//ajax
			}
	PicDiv.appendChild(btnCancel);
	//修改按钮
	var btnUpdate = document.createElement('input');
	btnUpdate.className='btnUpdate';
	btnUpdate.type='button';
	btnUpdate.value='实时修改';
	btnUpdate.bottom = '0';
	btnUpdate.style.width='70px';
	btnUpdate.style.height= '25px';
	
	PicDiv.appendChild(btnUpdate);
	PicDiv.appendChild(UNImg);//img放入div 最后
	PicsDiv.appendChild(PicDiv);
	//按钮改变事件置后
	btnUpdate.onclick=function()
	{
		//0.获取img对应父类的div 用于装入新的img标签
		var TDiv = this.parentNode;
		//1获取兄弟img节点
		var TImg  = TDiv.children[2];
		//2.找到对应file标签
		var TFile;
		var inputs = document.getElementById("pic_butn").children;
		for(var i=2;i<inputs.length;i++){
			if(inputs[i].title==TImg.title)
			{
				TFile = inputs[i];
			}
		}
		//3.打开file
		TFile.click();
		//4.//首先修改分支
		TFile.onchange=function()
		{
			var _this=this;
			if(this.value!=null && $("img[title='"+this.title+"']").length>0)
			{
				//新建img
					var NImg =document.createElement('img');
				//设置属性 
					var files2  = this.files;
					if(window.URL)
					{
						NImg.title = this.title;//file与img对应
						NImg.src= window.URL.createObjectURL(files2[0]); //创建一个object URL，并不是你的本地路径
						NImg.width = 160;
						NImg.height = 90;
						NImg.onload = function(e){
							window.URL.revokeObjectURL(this.src);//图片加载后释放 object URL
						}
						 if(files2[0].size>800*1024)
						{
							alert('该图片超过800k');
						}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(this.value))
						{
							alert('请选择正确的图片格式'+this.value);
						}else{
							//向服务器发送
							var singleFile = new FormData(document.getElementById("picForm"));
							//对应的Img src
							var TImgSrc = $("#pics_div").find("img[title='"+_this.title+"']").attr("src");
							singleFile.append("cancelImgUrl",TImgSrc.substring(TImgSrc.lastIndexOf("/")+1));
							singleFile.append("model.id",document.getElementById("hiddenImgUrl").title);
							$.ajax(
							{
								 url: "scenic_UpdateSingleImg.action",
								 type: "post",
								 data: singleFile,
								 processData: false,  // 告诉jQuery不要去处理发送的数据
									 contentType: false ,  // 告诉jQuery不要去设置Content-Type请求头,
								success: function(returnData)
								{
									if (returnData.split(";")[0]=="success")
										{
											var Pdiv = $("#pics_div").find("img[title='"+_this.title+"']").parent();
												Pdiv.title=_this.title;
												//原img删除  
												//通过TFile获取TImg
												$("#pics_div").find("img[title='"+_this.title+"']").remove();
												//设置新图片地址
												NImg.src = $("#hiddenRootUrl").html()+"/images/"+returnData.split(";")[1]
												//将Img子节点加入父节点
												Pdiv.append(NImg);
										}
									else{
											document.getElementById('pic_instruct').value="上传图片失败请联系开发人员";
											alert("操作失败！"+returnData);
										}
								}
							}
								);//ajax
						}
					}
			}
		}//onchang
	}
		}
}
	var isIE = false;
	var isIE6 = isIE
			&& ( [ /MSIE (\d)\.0/i.exec(navigator.userAgent) ][0][1] == 6);
	var newbox = document.getElementById(idname);
	newbox.style.zIndex = "9999";
	newbox.style.display = "block";
	newbox.style.position = !isIE6 ? "fixed" : "absolute";
	newbox.style.top = "49%";
	 newbox.style.left = "50%";
	newbox.style.marginTop = -newbox.offsetHeight / 2 + "px";
	newbox.style.marginLeft = -newbox.offsetWidth / 2 + "px";
	var layer = document.createElement("div");//overflow: scroll;
	layer.overflow='scroll';
	layer.id = "layer";
	layer.style.width =  "100%";
	layer.style.height = "100%";
	layer.style.position = !isIE6 ? "fixed" : "absolute";
	layer.style.left = "1%";
	layer.style.top = "1%";
	layer.style.backgroundColor = "#000";
	layer.style.zIndex = "9998";
	layer.style.opacity = "0.6";
	document.body.appendChild(layer);
	var sel = document.getElementsByTagName("select");
	for ( var i = 0; i < sel.length; i++) {
		sel[i].style.visibility = "hidden";
	}
	function layer_iestyle() {
		layer.style.width = Math.max(document.documentElement.scrollWidth,
				document.documentElement.clientWidth)+200
				+ "px";
		layer.style.height = Math.max(document.documentElement.scrollHeight,
				document.documentElement.clientHeight)
				+ "px";
	}
	function newbox_iestyle() {
		newbox.style.marginTop = document.documentElement.scrollTop
				- newbox.offsetHeight + "px";
		newbox.style.marginLeft = document.documentElement.scrollLeft
				- newbox.offsetWidth  + "px";
	}
	if (isIE) {
		layer.style.filter = "alpha(opacity=60)";
	}
	if (isIE6) {
		layer_iestyle();
		newbox_iestyle();
		window.attachEvent("onscroll", function() {
			newbox_iestyle();
		});
		window.attachEvent("onresize", layer_iestyle);
	}
	//关闭窗口
	var btn = document.getElementById(closeId);
	btn.onclick = function closeDia() {
		document.getElementById(idname).style.display = "none";
		layer.style.display = "none";
		for ( var i = 0; i < sel.length; i++) {
			sel[i].style.visibility = "visible";
		}
		//当 btn的Id为close_Class时 景区类别 
	};
}
</script>	
<!-- kindEditer -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh_CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[class="text"]', {
				cssPath : 'plugins/code/prettify.css',
				uploadJson : $('#RootUrl').html()+'/servlet/UploadAction2' +
				'',
				fileManagerJson : $('#RootUrl').html()+'/servlet/FileManagerJson',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
		
	</script>
</head>
	<body class="ContentBody">
	<div id="RootUrl" style="display: none">${pageContext.request.contextPath}</div>
		<s:form action="scenic_%{id!=null?'edit' : 'add' }" method="post"
			enctype="multipart/form-data">
			<s:hidden name="model.id"></s:hidden>
			<div class="MainDiv">
				<table width="99%" border="0" cellpadding="0" cellspacing="0"
					class="CContent">
					<tr>
						<th class="tablestyle_title">
							${id!=null?'景区修改页面' : '景区添加页面' }
						</th>
					</tr>
					<tr>
						<td class="CPanel">

							<table border="0" cellpadding="0" cellspacing="0"
								style="width: 100%">
								<tr>
									<td align="left"></td>
								</tr>

								<TR>
									<TD width="100%">
										<fieldset style="height: 100%;">
											<legend>
												<s:if test="id!=null">
													${model.name}景区修改页面
													<div id="hiddenRootUrl" style="display: none">${pageContext.request.contextPath}</div>
													<div id="hiddenImgUrl" title="${model.id}" style="display: none">${model.imageUrl}</div>
												</s:if>
												<s:else>
													景区添加页面
													<div id="hiddenImgUrl"  style="display: none" title="-1">fail</div>
												</s:else>
											</legend>
											<table border="0" cellpadding="2" cellspacing="1"
												style="width: 100%">
												<tr>
													<td nowrap align="right" width="10%">
														景区标题:
													</td>
													<td width="41%">
														<s:textfield cssClass="text" cssStyle="width: 300px" type="text"
															size="40" id="title" name="model.name" />
														<span class="red"> *<s:fielderror fieldName="name"/></span>
													</td>
													<td align="right" width="15%"></td>
													<td width="35%"></td>
												</tr>
												<tr>
													<td nowrap align="right" width="15%">
														景区图片:
													</td>
													<td >
														<input type="button" value="${id!=null?'修改图片':'选择图片'}" id="pic_instruct" class="demo" onclick="showFrame('pic_frame','close_Pics')"  />  
														<span class="red"> *<s:fielderror fieldName="scenicImage"/></span>
													</td>
												</tr>
												<tr>
													<td nowrap align="right">
														景区类别:
													</td>
													<td colspan="2" id ="ScenicClass" >
														<input id="class_tanchu" type="button" value="${id!=null?'修改类别':'选择类别'}"  ${id!=null?'':'disabled'} onclick="showFrame('treeDiv','close_Class')" />
														<span class="red"> *<s:fielderror fieldName="scenicClass"/></span>
													</td>
												</tr>
												
												<tr>
													<td align="right">
														景区级别:
													</td>
													<td>
														<s:radio name="model.level" list="{'世界级','国家级','省级','市级','其他'}" />
														<span class="red"> *<s:fielderror fieldName="level"/></span>
													</td>
												</tr>

												<tr>
													<td nowrap align="right" width="10%">
														景区所属城市:
													</td>
													<td width="41%"><!-- 这里select的name属性返回的是listKey的值 -->
													<s:select name="model.city.id" 
													list="#cityList" listKey="id" listValue="name"/>
														<span class="red"> *<s:fielderror fieldName="city"/></span>
													</td>
													<td align="right" width="15%"></td>
													<td width="35%"></td>
												</tr>
												
												<tr>
													<td nowrap align="right" height="120px">
														景区内容:
													</td>
													<td colspan="3">
														<s:textarea tabindex="container" cssClass="text" id="container0" name="model.description" rows="5" cols="80" cssStyle="width: 795px; height: 250px;"></s:textarea>
															<span class="red"> *<s:fielderror fieldName="description"/></span>
													</td>
													
												</tr>
												<tr>
													<td nowrap align="right" height="120px">
														景区路线:
													</td>
													<td colspan="3">
														<s:textarea tabindex="container" cssClass="text" id="container2" name="model.scenicLine" rows="5"
															cols="80" cssStyle="width: 795px; height: 250px;"></s:textarea>
															<span class="red"> *<s:fielderror fieldName="description"/></span>
													</td>
													
												</tr>
											</table>
											<br />
										</fieldset>
									</TD>
								</TR>
							</TABLE>
						</td>
					</tr>
					<TR>
						<TD colspan="2" align="center" height="50px">
							<input type="submit" value="保存" onclick="return saveScenic()" id="saveSub"/>
							<input type="button" value="返回" onclick="window.history.go(-1);" />
						</TD>
							<script type="text/javascript">
									function saveScenic(){
										if(confirm("确认保存？")==true)
											{
												for(var i=1;i<10;i++)
												if($("#imag"+i).val()!='')
													{
														$("#Ima"+i).attr("value",true);
													}
												else
													$(name="#Ima"+i).attr("value",false);
												return true;
											}
										else{
											return false;
										}
									}
							</script>
					</TR>
				</TABLE>
				</td>
				</tr>
				</table>
			
			</div>
		</s:form>
		
		

<div id="pic_frame" class="pic_frame" style="display: none; overflow:scroll;height: 450px;">
	<form method="post" class="mainlist" action="" encType="multipart/form-data" id="picForm" >
		<div id="pic_butn" class="pic_butn">
			<input type="button" value="添加图片" onclick="addPic()"  />
			<input type="button" onclick="PicSub()" value="${model.id!=null?'提交新增数据':'提交所有数据' }" />
			<input style="float: right;margin-right: -300px;" type="button" value="关闭窗口" id="close_Pics" />
		</div>
		<div id="pics_div" class="pics_div">
			
		</div>
	</form>
 </div>	
 <!-- 类别选择 -->
 <div id="treeDiv" style="display: none;width: 900px;height:450px; background: white; overflow:scroll;" class="treeDiv">
 <input style="float: right;right: 120px;top: 10px; position: fixed;" type="button" value="关闭窗口" id="close_Class" />
 <input style="float: right;right: 220px;top: 10px; position: fixed;" type="button" value="提交数据" onclick="subClass()" id="sub_Class" />
	 <ul id="tree" >
		<s:iterator value="#application.scenicClassList" >
			<li class=""><input type="checkbox" name="scenicClassIds" value="${id}"  id="cb_${id}" <s:property value="%{id in scenicClassIds ? 'checked':''}" /> />
				<label for="cb_${id}"><span class="folder">${name}</span></label>
				<ul>
				<s:iterator value="children">
					<li><input type="checkbox" name="scenicClassIds" value="${id}"  id="cb_${id}" <s:property value="%{id in scenicClassIds ? 'checked':''}" /> />
						<label for="cb_${id}"><span class="folder">${name}</span></label>
						<ul>
							<s:iterator value="children">
								<li><input type="checkbox" name="scenicClassIds" value="${id}"  id="cb_${id}" <s:property value="%{id in scenicClassIds ? 'checked':''}" /> />
									<label for="cb_${id}"><span class="folder">${name}</span></label>
								</li>
							</s:iterator>
						</ul>
					</li>	
				</s:iterator>
				</ul>
			</li>
		</s:iterator>
</ul>
</div>

	</body>
	
</html>
