$(function(){
	
	$("#errorMsg").hide();
	
	$("#login").bind("click",function(){
		var account = $("#account").val();
		var password = $("#password").val();
		if(Utils.common.util.isNotNullOrBlank(account) && Utils.common.util.isNotNullOrBlank(password)){
			$("#password").text(hex_md5(password));
			var url = "/vouching/u/lg";
			var data = {
				flag : false,
				account : account,
				password : hex_md5(password)
			};
			var successCallback = function(data){
				var url = "/vouching/f/fbi";
				Utils.common.util.simpleHref(url);	
			};
			var faildCallback = function(data){
				$("#errorMsg>div>ul>li").text(data.errorCode);
				$("#errorMsg").show();
			};
			Utils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
		}
	});
	
});