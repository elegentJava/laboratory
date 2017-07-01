$(function(){
	
	$("#errorMsg").hide();
	
	$("#login").bind("click",function(){
		var account = $("#account").val();
		var password = $("#password").val();
		if (Utils.common.util.isNotNullOrBlank(account) && Utils.common.util.isNotNullOrBlank(password)) {
			$("#password").text(hex_md5(password));
			var url = "/vouching/user/login";
			var data = {
				flag : true,
				account : account,
				password : hex_md5(password)
			};
			var successCallback = function(data){
				var url = "/vouching/forward/forwardFrontIndex";
				Utils.common.util.simpleHref(url);	
			};
			var faildCallback = function(data){
				$("#errorMsg").text(data.errorCode);
				$("#errorMsg").show();
			};
			Utils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
		} else {
			$("#errorMsg").text("账号或密码不能为空!");
			$("#errorMsg").show();
		}
	});
	
});