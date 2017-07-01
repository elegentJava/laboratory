$(function(){
	
	//修改密码
	$("#modify").bind("click",function(){
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		var confirmPassword = $("#confirmPassword").val();
		if (Utils.common.util.isNotNullOrBlank(oldPassword)
				&& Utils.common.util.isNotNullOrBlank(newPassword)
				&& Utils.common.util.isNotNullOrBlank(confirmPassword)) {
			if (newPassword == confirmPassword) {
				var url = "/vouching/user/modifyPassword";
				var data = {
					oldPassword : hex_md5(oldPassword),
					newPassword : hex_md5(newPassword),
					confirmPassword : hex_md5(confirmPassword)
				};
				var successCallback = function(data){
					layer.msg("密码修改成功!");
				};
				Utils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
			} else {
				Utils.common.tip.errorAlert("两次输入的密码不一致!");
			}
		} else {
			Utils.common.tip.errorAlert("输入密码不能为空!");
		}
	});
});