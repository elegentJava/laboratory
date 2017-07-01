$(function(){
	
	//装载信息
	var url = "/vouching/email/loadEmailDetail";
	var successCallback = function(data){
		var email = data.detail.email;
		if (email != null) {
			$("#subject").attr("value",email.subject);
			$("#sender").attr("value",email.sendName);
			$("#receiver").attr("value",email.receiveName);
			$("#content").text(email.content);
		}
	};
	Utils.common.ajax.commonAjax(url, false, null, successCallback);

});