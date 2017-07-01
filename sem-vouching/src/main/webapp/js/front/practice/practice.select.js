$(function(){
	
	//初始化装载数据
	var url = "/vouching/practice/loadPracticeSelectInfo";
	var successCallback = function(data){
		var chapters = data.detail.chapters;
		var categorys = data.detail.categorys;
		var levels = data.detail.levels;
		for (var i = 0; i < chapters.length; i++) {
			$("#chapters").append("<option value='" +chapters[i].chapterId+ "'>" +chapters[i].name+ "</option>");
		}
		for (var i = 0; i < categorys.length; i++) {
			$("#categorys").append("<option value='" +categorys[i].id+ "'>" +categorys[i].name+ "</option>");
		}
		for (var i = 0; i < levels.length; i++) {
			$("#levels").append("<option value='" +levels[i].id+ "'>" +levels[i].name+ "</option>");
		}
	};
	Utils.common.ajax.commonAjax(url, false, null, successCallback);
	
	//生成试卷
	$("#startTest").bind("click",function(){
		var chapterId = $("#chapters").val();
		var categoryId = $("#categorys").val();
		var levelId = $("#levels").val();
		var count = $("#count").val();
		var url = "/vouching/practice/generatePracticePaper";
		var data = {
			chapterId : chapterId,
			categoryId : categoryId,
			levelId : levelId,
			count : count,
		};
		var successCallback = function(){
			var url = "/vouching/forward/forwardStartPractice";
			Utils.common.util.simpleHref(url);
		}
		Utils.common.ajax.commonAjax(url, false, data, successCallback);
	});
	
});