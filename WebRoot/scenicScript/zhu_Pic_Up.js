var isUpAdd=false;//更新 增加
var isAdd=false;//非更新增加
window.URL = window.URL || window.webkitURL;
	function addPic(){
	var PicNum = document.getElementById('pics_div').children.length;
	if(PicNum>15){alert('不能超过最大上传图片数16');}else{
		var picsDiv = document.getElementById('pics_div');
		//获取img标签里的title最大值
		var imgs= $(picsDiv).find("img");
		var subNum = 0//file标识
			for(var i =0;i<imgs.length;i++)
			{
				if(subNum<parseInt($(imgs[i]).attr("title")))
				{subNum =parseInt($(imgs[i]).attr("title")); }
			}
		var fileInput = document.createElement('input');
		fileInput.title = subNum+1;
		fileInput.type='file';
		fileInput.name='scenicImage';
		fileInput.className='fileInput';
		fileInput.click();//好不容易，自动打开图片选择
		fileInput.onchange=function()
		{
			//首先修改分支
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
							//
							var Pdiv = $("#pics_div").find("img[title='"+this.title+"']").parent();
							Pdiv.title=this.title;
							//原img删除  TImg获取不到
							//通过TFile获取TImg
							$("#pics_div").find("img[title='"+this.title+"']").remove();
							//将Img子节点加入父节点
							Pdiv.append(NImg);
						}
					}
			}
			//第一次添加分支
			else if (this.value!=null && $("img[title='"+this.title+"']").length==0)
			{
				isAdd=true;
				//如果 div中数据没有fail  则为更新 增加 则修改标识
				if(document.getElementById("hiddenImgUrl").innerText!="fail")
				{
					isUpAdd=true;
				}
				var PicDiv = document.createElement('div');
				var PicImg = document.createElement('img');
				var PicsDiv = document.getElementById('pics_div');
				var PicButn = document.getElementById('pic_butn');
				//
				var files  = this.files;
				if(window.URL)
				{
					PicImg.title = this.title;//file与img对应
					PicImg.src= window.URL.createObjectURL(files[0]); //创建一个object URL，并不是你的本地路径
					PicImg.width = 160;
					PicImg.height = 90;
					PicImg.onload = function(e){
						window.URL.revokeObjectURL(this.src);//图片加载后释放 object URL
					}
					 if(files[0].size>800*1024)
					{
						alert('该图片超过800k');
					}else if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(this.value))
					{
						alert('请选择正确的图片格式'+this.value);
					}else{
						//取消按钮
						var btnCancel = document.createElement('input');
						btnCancel.className='btnCancel';
						btnCancel.type='button';
						btnCancel.value='取消';
						btnCancel.bottom = '0';
						btnCancel.style.width='70px';
						btnCancel.style.height= '25px';
						btnCancel.onclick=function()
						{
							//移去file标签和img
							$("input[title='"+this.nextSibling.nextSibling.title+"']").remove();
							this.parentNode.parentNode.removeChild(this.parentNode);
						}
						PicDiv.appendChild(btnCancel);
						//修改按钮
						var btnUpdate = document.createElement('input');
						btnUpdate.className='btnUpdate';
						btnUpdate.type='button';
						btnUpdate.value='修改';
						btnUpdate.bottom = '0';
						btnUpdate.style.width='70px';
						btnUpdate.style.height= '25px';
						//把input file加入PicButn
						PicButn.appendChild(this);
						PicDiv.appendChild(btnUpdate);
						PicDiv.appendChild(PicImg);//img放入div 最后
						PicsDiv.appendChild(PicDiv);
						//按钮改变事件置后
						btnUpdate.onclick=function()
						{
							//0.获取img对应父类的div 用于装入新的img标签
							var TDiv = this.parentNode;
							//1获取兄弟img节点
							var TImg  = TDiv.children[2];
							//2.找到对应file标签
							var TFile = $("input[title='"+TImg.title+"']");
							//3.打开file
							TFile.click();
							
						}
					}
				}else if(window.FileReader){
			//opera不支持createObjectURL/revokeObjectURL方法。我们用FileReader对象来处理
					var reader = new FileReader();
					reader.readAsDataURL(files[0]);
					reader.onload = function(e){
						
						PicImg.src = this.result;
						PicImg.width = 160;
						PicImg.height = 90;
						if(e.total>800*1024){
							alert('该图片超过800k');
						}else{
							PicDiv.appendChild(PicImg);//img放入div
							PicsDiv.appendChild(PicDiv);
						}
					}
				}else{
					//ie
					this.select();
					this.blur();
					var nfile = document.selection.createRange().text;
					document.selection.empty();
					PicImg.src = nfile;
					PicImg.width = 160;
					PicImg.height = 90;
					PicImg.onload=function(){
					  alert(nfile+","+PicImg.fileSize + " bytes");
					}

					if(PicImg.fileSize>800*1024){
							alert('该图片超过800k');
					}else{
						PicDiv.appendChild(PicImg);//img放入div
						PicsDiv.appendChild(PicDiv);
					}
					//fileList.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src='"+nfile+"')";
				}
			}
		}
	}
	}	
function PicSub(){
	//先判断是否添加数据
if(!isAdd)
{return alert("请添加一些图片");}
//否则添加了
//类别按钮
	$("#class_tanchu").attr("disabled",false);
	var picForm= new FormData(document.getElementById("picForm"));
	document.getElementById("close_Pics").click();
	picForm.append("model.name",document.getElementById("title").value);
	//如果 div中数据没有fail  则为更新 增加
	if($("#hiddenImgUrl").html()!="fail" && isUpAdd)
	{
		picForm.append("isUpdateAdd","add");
		picForm.append("scenicID",document.getElementById("hiddenImgUrl").title);
	}
	$.ajax(
			{
				 url: "subscenic_ImgUp.action",
				 type: "post",
				 dataType: "text",
				 data: picForm,
				 processData: false,  // 告诉jQuery不要去处理发送的数据
 				 contentType: false ,  // 告诉jQuery不要去设置Content-Type请求头,
				success: function(returnData)
				{
					if (returnData=="success")
						{
							var picInstruct = document.getElementById('pic_instruct');
							picInstruct.value='图片已提交';
							$(picInstruct).attr("disabled","disabled");
							//取消 input file
							var inFiles = document.getElementById('pic_butn').children;
							for(var z=3;z<inFiles.length;z++)
							{inFiles[z].remove();}
						}
					else{
							document.getElementById('pic_instruct').value="上传图片失败请联系开发人员add";
							alert("操作失败2！"+returnData);
						}
				}
			}
				);
}	
	