$(function(){
	
	//菜单切换
	$("#menus>li").each(function(){
		$(this).bind("mouseover",function(){
			var selected = $(".m_li_a");
			for (var i = 0; i < selected.length; i++) {
				$(selected[i]).attr("class","m_li");
			}
			var id = $(this).attr("id");
			$(this).attr("class","m_li_a");
			selected =  $(".s_li_a");
			for (var i = 0; i < selected.length; i++) {
				$(selected[i]).attr("class","s_li");
				$(selected[i]).css("display","none");
			}
			$("li[name = '"+id+"']").attr("class","s_li_a");
			$("li[name = '"+id+"']").css("display","block");
		});
	});
	
	//注销登录
	$("#logout").bind("click",function(){
		var url = "/vouching/user/logout";
		var successCallback = function(data){
			var url = "/vouching/forward/forwardFrontLogin";
			Utils.common.util.simpleHref(url);
		};
		Utils.common.ajax.commonAjax(url, false, null, successCallback);
	});
	
	//装载导航栏信息
	var url = "/vouching/user/loadNavigate";
	var successCallback = function(data){
		$("#credit").text(data.detail.credit);
		$("#unreadCount").text(data.detail.unreadCount);
	};
	Utils.common.ajax.commonAjax(url, false, null, successCallback);
	
});

