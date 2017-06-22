var URL_LOAD_CATEGORY = "/corre/c/lcc";
var URL_LOAD_CORRE = "/corre/c/lc";

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
	$("#categories>label").eq(no-1).append("<div class='container_ui__expand' id='close'><div class='heading'><div class='heading_head'></div><label for='message-"+no+"'>x</label></div><div class='body'><div class='user'><div class='face'><img src='/corre/images/1.jpg'/></div><div class='details'><h2>"+second+"</h2><h3>"+third+"</h3></div></div><div class='content'><p id='content"+ccid+"'></p><span>Receiver:</span><input type='text' id='receiver'/><button>Send Now!</button></div></div></div>");
	$()
	showCorre();
}


function navClick(){
	$("a[name='nav']").each(function(){
		$(this).bind("click",function(){
			var data = {
				fatherId : $().attr("fatherId")
			};
			loadCategory(data);
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
			};
			Utils.ajax.commonAjax(URL_LOAD_CORRE, false, data, successCallback);
		});
	});
}

$(function() {
	loadCategory(null);
});