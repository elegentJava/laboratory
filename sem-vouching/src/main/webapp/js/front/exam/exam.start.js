$(function(){
	
	//装载试卷信息
	loadStartExam();
	
	//提交试卷
	submitExam();
	
});

function changeColor(){
	$("div[name='changeColor']").each(function(){
		$(this).hover(function(){
			$(this).css("background","#FFBFFF");
		},function(){
			$(this).css("background","");
		});
	});
}

/**
 * 装载考试页面
 */
function loadStartExam() {
	var loading = layer.load();
	var url = "/vouching/exam/loadStartExamInfo";
	var successCallback = function(data){
		var exam = data.detail.exam;
		var radios = data.detail.radios;
		var blanks = data.detail.blanks;
		var phrases = data.detail.phrases;
		var clozes = data.detail.clozes;
		var translates = data.detail.translates;
		
		//装载试卷概要内容
		$("#examName").text("试卷名称："+data.detail.examName);
		$("#bak").text("备注："+data.detail.bak);
		
		//装载分数信息
		$("#radioScore").text(exam.radioScore);
		$("#blankScore").text(exam.blankScore);
		$("#phraseScore").text(exam.phraseScore);
		$("#translateScore").text(exam.translateScore);
		$("#clozeScore").text(exam.clozeScore);
		
		//装载单选题
		$("#radiosList").children().remove();
		if (radios != null && radios.length > 1) {
			for (var i = 0; i < radios.length; i++) {
				$("#radiosList").append("<div name='changeColor'></div>");
				var div = $("#radiosList").children().eq(i);
				div.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + radios[i].question + "<span>("+ exam.radioScore/radios.length +"分)</span></td>");
				for (var j = 0; j < radios[i].options.length; j++) {
					if (j != 0) {
						div.append("<tr><td align='left'><input value='"+(j+1)+"' name='radios"+i+"' type='radio'/>&nbsp;&nbsp;"+ radios[i].options[j] +"</td></tr>");
					} else {
						div.append("<tr><td align='left'><input value='"+(j+1)+"' name='radios"+i+"' type='radio'/>&nbsp;&nbsp;"+ radios[i].options[j] +"</td></tr>");
					}
				}
				div.append("<tr><td style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#radiosList").append("<div><tr><td>该试卷暂未添加单项选择试题</td></tr></div>");
		}
		
		//装载名词解释题
		$("#phrasesList").children().remove();
		if (phrases != null && phrases.length > 1) {
			for (var i = 0; i < phrases.length; i++) {
				$("#phrasesList").append("<div name='changeColor'></div>");
				var div = $("#phrasesList").children().eq(i);
				div.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + phrases[i].question + "<span>("+ exam.phraseScore/phrases.length +"分)</span></td>");
				div.append("<tr><td align='left'><input size='30px'/></td></tr>");
				div.append("<tr><td style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#phrasesList").append("<div><tr><td>该试卷暂未添加名词翻译试题</td></tr></div>");
		}
		
		//装载填空题
		$("#blanksList").children().remove();
		if (blanks != null && blanks.length > 1) {
			for (var i = 0; i < blanks.length; i++) {
				$("#blanksList").append("<div name='changeColor'></div>");
				var div = $("#blanksList").children().eq(i);
				div.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + blanks[i].question + "<span>("+ exam.blankScore/blanks.length +"分)</span></td>");
				div.append("<tr><td align='left'><input size='30px'/></td></tr>");
				div.append("<tr><td style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#blanksList").append("<div><tr><td>该试卷暂未添加名词填空试题</td></tr></div>");
		}
		
		//装载语句翻译题
		$("#translatesList").children().remove();
		if (translates != null && translates.length > 1) {
			for (var i = 0; i < translates.length; i++) {
				$("#translatesList").append("<div name='changeColor'></div>");
				var div = $("#translatesList").children().eq(i);
				div.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + translates[i].question + "<span>("+ exam.translateScore/translates.length +"分)</span></td>");
				div.append("<tr><td align='left'><input size='130px'/></td></tr>");
				div.append("<tr><td style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#translatesList").append("<div><tr><td>该试卷暂未添加语句翻译试题</td></tr></div>");
		}
		
		//装载完型填空题
		$("#clozesList").children().remove();
		if (clozes != null && clozes.length > 1) {
			for (var i = 0; i < clozes.length; i++) {
				$("#clozesList").append("<div name='changeColor'></div>");
				var div = $("#clozesList").children().eq(i);
				div.append("<td align='left'><font style='color: red'>第&nbsp;" + (i+1) + "&nbsp;题</font>：" + clozes[i].question + "<span>("+ exam.clozeScore/clozes.length +"分)</span></td>");
				div.append("<tr><td align='left'><input size='130px'/></td></tr>");
				div.append("<tr><td style='border: 1px dashed #ccc;border-bottom: none;'></td></tr>");
			}
		} else {
			$("#clozesList").append("<div><tr><td>该试卷暂未添加完型填空试题</td></tr></div>");
		}
		
		//备份ExamId
		$("#examId").attr("value",data.detail.examId);
		changeColor();
		layer.close(loading);
	};
	Utils.common.ajax.commonAjax(url, false, null, successCallback, null, loading);
}

/**
 * 提交试卷
 */
function submitExam() {
	$("#submitExam").bind("click",function(){
		var radiosObj = $("#radiosList").find("input:checked");
		var phrasesObj = $("#phrasesList").find("input");
		var clozesObj = $("#clozesList").find("input");
		var blanksObj = $("#blanksList").find("input");
		var translatesObj = $("#translatesList").find("input");
		var radioAnswers = new Array();
		var phraseAnswers = new Array();
		var clozeAnswers = new Array();
		var blankAnswers = new Array();
		var translateAnswers = new Array();
		for (var i = 0; i < radiosObj.length; i++) {
			radioAnswers[i] = $(radiosObj[i]).val();
		}
		for (var i = 0; i < phrasesObj.length; i++) {
			phraseAnswers[i] = $(phrasesObj[i]).val();
		}
		for (var i = 0; i < clozesObj.length; i++) {
			clozeAnswers[i] = $(clozesObj[i]).val();
		}
		for (var i = 0; i < blanksObj.length; i++) {
			blankAnswers[i] = $(blanksObj[i]).val();
		}
		for (var i = 0; i < translatesObj.length; i++) {
			translateAnswers[i] = $(translatesObj[i]).val();
		}
		var url = "/vouching/exam/generateUserPaper";
		var data = {
		    examId : $("#examId").val(),
		    radioAnswers : radioAnswers,
		    phraseAnswers : phraseAnswers,
		    clozeAnswers : clozeAnswers,
		    blankAnswers : blankAnswers,
		    translateAnswers : translateAnswers
		};
		var successCallback = function(data){
			layer.msg("试卷保存成功!");
			Utils.common.util.simpleHref("/vouching/forward/forwardJoinExam");
		};
		Utils.common.ajax.commonAjax(url, false, data, successCallback, null ,null);
	});
}