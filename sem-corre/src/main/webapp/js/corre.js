var URL_LOAD_CATEGORY = "/corre/c/lcc";
var URL_LOAD_CORRE = "/corre/c/lc";
var URL_SEND_CORRE = "/corre/c/sc";

var currentSubjuect;
var currentContent;
var currentTranslate;

function loadCategory(data) {
	var successCallback = function(data) {
		var count = 1;
		var categories = data.detail.categories;
		if (categories != null && categories.length != 0) {
			//
			$("#categories>div:not(.container_ui__heading)").remove();
			for (var i = 0; i < categories.length; i++) {
				var first = categories[i];
				$("#nav").append("<li><a href='javascript:;' fatherId='"+first.ccid+"' name='nav' ><span>"+first.content+"</span></a></li>");
				var seconds = first.sonCategories;
				for (var j = 0; j < seconds.length; j++) {
					var second = seconds[j];
					var thirds = second.sonCategories;
					for (var k = 0; k < thirds.length; k++) {
						generateCategories(count,first.content,second.content,thirds[k].content,thirds[k].ccid);
						count++;
					}
				}
				break;
			}
		}
	};
	Utils.ajax.commonAjax(URL_LOAD_CATEGORY, false, data, successCallback);
}

function generateCategories(no, first, second, third, ccid) {
	$("#categories").append("<input id='message-"+no+"' type='checkbox'>");
	$("#categories").append("<label ccid='"+ccid+"' for='message-"+no+"' href='#move'></label>");
	$("#categories>label").eq(no-1).append("<div class='container_ui__item'><div class='face'><img src='/corre/images/1.jpg'><div class='color_bar one'><p>正在阅读</p><span>立刻阅读</span></div></div><h2>"+third+"</h2><div class='dot active'></div><h3>"+second+"</h3><h4>"+first+"</h4></div>");
	$("#categories>label").eq(no-1).append("<div class='container_ui__expand' id='close'><div class='heading'><div class='heading_head'></div><label for='message-"+no+"'>x</label></div><div class='body'><div class='user'><div class='face'><img src='/corre/images/1.jpg'/></div><div class='details'><h2>"+second+"</h2><h3 id='subject"+ccid+"'>"+third+"</h3></div></div><div class='content'><p id='content"+ccid+"'></p><input type='button' value='查看翻译' name='showTranslate' class='btn btn-default btn-sm' data-toggle='modal' data-target='#translate'/><button name='send' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#sendCorre'>发送函电</button></div></div></div>");
	showCorre();
	fillSubject();
	showTranslate();
}

function showTranslate(){
	$("input[name='showTranslate']").each(function(){
		$(this).bind("click",function(){
			$("#corre_translate").html(currentTranslate);
		});
	});
}

function fillSubject(){
	$("button[name='send']").each(function(){
		$(this).bind("click",function(){
			$("#corre_subject").attr("value",currentSubjuect);
		});
	});
}

function showCorre(){
	$("label[href='#move']").each(function(){
		$(this).unbind("click");
		$(this).bind("click",function(){
			var ccid = $(this).attr("ccid");
			var data = {
				ccid : ccid
			};
			var successCallback = function(data){
				$("#content"+ccid).html(data.detail.corre.correEnglish);
				currentContent = data.detail.corre.correEnglish;
				currentTranslate = data.detail.corre.correChinese;
				currentSubjuect = $("#subject"+ccid).text();
			};
			Utils.ajax.commonAjax(URL_LOAD_CORRE, false, data, successCallback);
		});
	});
}

function sendCorre(){
	$("#send").bind("click",function(){
		var data = {
			receiver : $("#corre_receiver").val(),
			subject : $("#corre_subject").val(),
			content : currentContent
		};
		var successCallback = function(data){
			$("sendCorre").hide();
		};
		Utils.ajax.commonAjax(URL_SEND_CORRE, false, data, successCallback);
	});
}

$(function() {
	loadCategory(null);
	sendCorre();
});