$(function(){
	
	//隐藏分页处理
	$("#joinedPager").hide();
	$("#unjoinedPager").hide();
	
	//装载试卷信息
	initLoadData("unjoinedList",0);
	initLoadData("joinedList",1);
	
	//参加考试事件
	joinExam();
	
});

/**
 * 根据试卷状态装载试卷信息
 * 
 * @param joinStatus
 */
function initLoadData(tbodyId,joinStatus){
	var loading = layer.load();
	var url = "/vouching/exam/loadJoinExamInfo";
	var data = {
		pageNum : 1,
		joinStatus : joinStatus
	};
	var successCallback = function(data){
		$("#" + tbodyId).children().remove();
		var datas = null;
		if(joinStatus == 1){
			datas = data.detail.joinedExams;
			if (datas != null && datas.length >= 1) {
				for (var i = 0; i < datas.length; i++) {
					$("#" + tbodyId).append("<tr id='" + datas[i].examId + "'></tr>");
					var tr = $("#" + tbodyId).children().eq(i);
					tr.append("<td class='teatbbai' align='center'>" + datas[i].exam.name + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].exam.bak + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].statusName + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].exam.formatCreateDate + "</td>");
				}
				Utils.common.pager.front.loadPage(data);
				$("#joinedPager").show();
				Utils.common.pager.front.registerEvent(loadJoinedForPage);
			} else {
				$("#joinedPager").hide();
				$("#" + tbodyId).append("<tr><td colspan='4' class='teatbbai' align='left' style='font-family: 黑体;color:read;'>暂无已经参加的试卷信息！</td></tr>");
			}
		} else {
			datas = data.detail.unjoinedExams;
			if (datas != null && datas.length >= 1) {
				for (var i = 0; i < datas.length; i++) {
					$("#" + tbodyId).append("<tr id='" + datas[i].examId + "'></tr>");
					var tr = $("#" + tbodyId).children().eq(i);
					tr.append("<td class='teatbbai' align='center'>" + datas[i].name + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].bak + "</td>");
					tr.append("<td class='teatbbai' align='center'>" + datas[i].formatCreateDate + "</td>");
					tr.append("<td class='teatbbai' align='center'><a name='joinExam' examId='"+datas[i].examId+"' href='javascript:;'>参加考试</a></td>");
				}
				Utils.common.pager.front.loadPage1(data);
				$("#unjoinedPager").show();
				Utils.common.pager.front.registerEvent1(loadUnjoinedForPage);
			} else {
				$("#unjoinedPager").hide();
				$("#" + tbodyId).append("<tr><td colspan='4' class='teatbbai' align='left' style='font-family: 黑体;'>暂无尚未参加的试卷信息！</td></tr>");
			}
		}
		layer.close(loading);
	};
	Utils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}

/**
 * 装载已经参加过的考试信息
 * 
 * @param pageNum
 */
function loadJoinedForPage(pageNum){
	var loading = layer.load();
	var url = "/vouching/exam/loadJoinExamInfo";
	var data = {
		pageNum : pageNum,
		joinStatus : 1
	};
	var successCallback = function(data){
		$("#joinedList").children().remove();
		datas = data.detail.joinedExams;
		if (datas != null && datas.length >= 1) {
			for (var i = 0; i < datas.length; i++) {
				$("#joinedList").append("<tr id='" + datas[i].examId + "'></tr>");
				var tr = $("#joinedList").children().eq(i);
				tr.append("<td class='teatbbai' align='center'>" + datas[i].exam.name + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].exam.bak + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].statusName + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].exam.formatCreateDate + "</td>");
			}
			Utils.common.pager.front.loadPage(data);
			$("#joinedPager").show();
			Utils.common.pager.front.registerEvent(loadJoinedForPage);
		} else {
			$("#joinedPager").hide();
			$("#joinedList").append("<tr><td colspan='3' class='teatbbai' align='left' style='font-family: 黑体;color:read;'>暂无激活的试卷信息！</td></tr>");
		}
		layer.close(loading);
	};
	Utils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}

/**
 * 装载已经参加过的考试信息
 * 
 * @param pageNum
 */
function loadUnjoinedForPage(pageNum){
	var loading = layer.load();
	var url = "/vouching/exam/loadJoinExamInfo";
	var data = {
		pageNum : pageNum,
		joinStatus : 0
	};
	var successCallback = function(data){
		$("#unjoinedList").children().remove();
		datas = data.detail.unjoinedExams;
		if (datas != null && datas.length >= 1) {
			for (var i = 0; i < datas.length; i++) {
				$("#unjoinedList").append("<tr id='" + datas[i].examId + "'></tr>");
				var tr = $("#unjoinedList").children().eq(i);
				tr.append("<td class='teatbbai' align='center'>" + datas[i].name + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].bak + "</td>");
				tr.append("<td class='teatbbai' align='center'>" + datas[i].formatCreateDate + "</td>");
				tr.append("<td class='teatbbai' align='center'><a target='_blank' name='joinExam' examId='"+datas[i].examId+"' href='javascript:;'>参加考试</a></td>");
			}
			Utils.common.pager.front.loadPage1(data);
			$("#unjoinedPager").show();
			Utils.common.pager.front.registerEvent1(loadUnjoinedForPage);
		} else {
			$("#unjoinedPager").hide();
			$("#unjoinedList").append("<tr><td colspan='4' class='teatbbai' align='left' style='font-family: 黑体;color:read;'>暂无激活的试卷信息！</td></tr>");
		}
		joinExam();
		layer.close(loading);
	};
	Utils.common.ajax.commonAjax(url, false, data, successCallback, null, loading);
}

/**
 * 绑定参加考试事件
 */
function joinExam(){
	
	$("a[name='joinExam']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			var examId = $(this).attr("examId");
			var url = "/vouching/exam/startExam";
			var data = {
				examId : examId
			};
			var successCallback = function(data){
				var url = "/vouching/forward/forwardStartExam";
				Utils.common.util.simpleHref(url);
			};
			Utils.common.ajax.commonAjax(url, false, data, successCallback);
		});
	});
}