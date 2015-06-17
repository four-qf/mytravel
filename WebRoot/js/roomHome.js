$(function(){
	$(".processed .imgMou").hover(function(){  
		$('.liBgDiv', this).stop().animate({"opacity":'1'},{queue:false,duration:300});  
	}, function() {  
		$('.liBgDiv', this).stop().animate({"opacity":'0'},{queue:false,duration:300});  
	});
});