$(function() {
	
	//菜单栏切换
	$(".leftnav h2").click(function() {
		$(this).next().slideToggle(200);
		$(this).toggleClass("on");
	});
	$(".leftnav ul li a").click(function() {
		$("#a_leader_txt").text($(this).text());
		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
	});
	
	//注销登录
	$("#logout").bind("click",function(){
		var url = "/vouching/u/lt";
		var token = $("#token").val();
		var data = {
		    token : token
		};
		var successCallback = function(data){
			var url = "/vouching/f/fbl";
			Utils.common.util.simpleHref(url);
		};
		Utils.common.ajax.commonAjax(url, false, data, successCallback, null);
	});
	
});