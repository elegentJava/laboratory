var receivers;

$(function(){
	
	//装载班级信息
	var url = "/vouching/user/loadClassList";
	var successCallback = function(data){
		var classes = data.detail.classes;
		if(classes!=null){
			for (var i = 0; i < classes.length; i++) {
				$("#classList").append("<option value='"+classes[i].classId+"'>"+classes[i].className+"</option>");
			}
			loadReceiver(classes[0].classId);
		}
	};
	Utils.common.ajax.commonAjax(url, false, null, successCallback);
	
	//班级选项改变事件
	$("#classList").bind("change",function(){
		var classId = $(this).val();
		loadReceiver(classId);
	});
	
	//发送邮件
	$("#send").unbind("click");
	$("#send").bind("click",function(){
		var subject = $("#subject").val();
		var content = $("#content").val();
		if (Utils.common.util.isNotNullOrBlank(subject)) {
			if (Utils.common.util.isNotNullOrBlank(content)) {
				if (Utils.common.util.isNotNullOrBlank(receivers)) {
					var url = "/vouching/email/sendEmail";
					var data = {
						subject : subject,
						content : content,
						receivers : receivers
					};
					var successCallback = function(data){
						layer.msg("发送成功!");
					};
					Utils.common.ajax.commonAjax(url, false, data, successCallback, null, null);
				} else {
					Utils.common.tip.errorAlert("收件人不能为空!");
				}
			} else {
				Utils.common.tip.errorAlert("内容不能为空!");
			}
		} else {
			Utils.common.tip.errorAlert("主题不能为空!");
		}
	});
	
	
});

/**
 * 装载收件人
 * 
 * @param classId
 */
function loadReceiver(classId){
	var url = "/vouching/user/loadUsersInClass";
	var data = {
		classId : classId
	};
	var successCallback = function(data){
		var users = data.detail.users;
		$("#mutiClass").children().remove();
		if(users != null){
			for (var i = 0; i < users.length; i++) {
				$("#mutiClass").append("<option name='receiver' value='"+users[i].userId+"' email='"+users[i].email+"'>"+users[i].name+"</option>");
			}
		}
		addReceiver();
	};
	Utils.common.ajax.commonAjax(url, false, data, successCallback);
}

/**
 * 添加收件人事件
 */
function addReceiver(){
	$("option[name='receiver']").bind("click",function(){
		var selectedObj = $("option[name='receiver']:selected");
		var receiverEmails = "";
		receivers = "";
		for (var i = 0; i < selectedObj.length; i++) {
			if (i != selectedObj.length - 1) {
				receiverEmails += $(selectedObj[i]).attr("email") + "<" + $(selectedObj[i]).text() + ">" + ";";
				receivers += $(selectedObj[i]).attr("value") + ";";
			} else {
				receiverEmails += $(selectedObj[i]).attr("email") + "<" + $(selectedObj[i]).text() + ">";
				receivers += $(selectedObj[i]).attr("value");
			}
		}
		$("#receivers").attr("value",receiverEmails);
	});
}