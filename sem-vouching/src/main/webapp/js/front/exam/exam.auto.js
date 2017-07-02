$(function(){
	
	//装载页面信息
	var url = "/vouching/exam/loadExamAutoInfo";
	var successCallback = function(data){
		var questionTypes = data.detail.questionTypes;
		var levels = data.detail.levels;
		//装载题型
		for (var i = 0; i < questionTypes.length; i++) {
			var scoreId = questionTypes[i].tag + "Score";
			$("#questionList").append("<tr><td width='120' align='left'>"+questionTypes[i].name+"</td><td align='left'><select id='"+questionTypes[i].tag+"'><option value='5'>5</option><option value='10'>10</option><option value='15'>15</option></select></td><td width='120'>总分数:</td><td><input id='"+scoreId+"'/></td></tr>");
		}
		//装载难度
		for (var i = 0; i < levels.length; i++) {
			$("#levels").append("<option value='"+levels[i].id+"'>"+levels[i].name+"</option>");
		}
	};
	Utils.common.ajax.commonAjax(url, true, null, successCallback);
	
	//考试名称校验
	$("#name").bind("focusout",function(){
		examNameValidate();
	});
	$("#name").bind("focusin",function(){
		$("#errorMsg").text("");
	});
	
	//生成试卷
	$("#saveExam").bind("click",function(){
		if (examNameValidate()) {
			var url = "/vouching/exam/generateAutoExam";
			var data = {
				name : $("#name").val(),
				bak : $("#bak").val(),
				radioCount : $("#radio").val(),
				blankCount : $("#blank").val(),
				clozeCount : $("#cloze").val(),
				phraseCount : $("#phrase").val(),
				translateCount : $("#translate").val(),
				radioScore : $("#radioScore").val(),
				blankScore : $("#blankScore").val(),
				clozeScore : $("#clozeScore").val(),
				phraseScore : $("#phraseScore").val(),
				translateScore : $("#translateScore").val(),
				level : $("#levels").val()
			};
			var successCallback = function(data){
				layer.msg("试卷生成成功!");
				var url = "/vouching/forward/forwardPreview?examId=" + data.detail.examId;
				Utils.common.util.simpleHref(url);
			};
			Utils.common.ajax.commonAjax(url, false, data, successCallback);
		}
	});
	
});

/**
 * 考试名称校验
 * 
 * @returns {Boolean}
 */
function examNameValidate(){
	if (Utils.common.util.isNotNullOrBlank(name)) {
		var flag = true;
		var url = "/vouching/exam/validateExamName";
		var data = {
			name : $("#name").val()
		};
		var successCallback = function(data){
			$("#errorMsg").text("");
		};
		var faildCallback = function(data){
			$("#errorMsg").text(data.errorCode);
			flag = false;
		};
		Utils.common.ajax.commonAjax(url, false, data, successCallback, faildCallback);
		return flag;
	} else {
		$("#errorMsg").text("请输入考试名称!");
		return false;
	}
}