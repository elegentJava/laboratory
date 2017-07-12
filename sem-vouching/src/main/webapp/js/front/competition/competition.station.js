$(function(){
	// 装载竞技排行榜
	var url = "/vouching/competition/loadRankInfo";
	var successCallback = function(data){
		var ranks = data.detail.ranks;
		for (var i = 0; i < ranks.length; i++) {
			$("#rankList").append("<tr></tr>");
			var tr = $("#rankList").children().eq(i);
			tr.append("<td class='bgbai'>"+ranks[i].name+"</td>");
			tr.append("<td class='bgbai'>"+ranks[i].credit+"</td>");
			tr.append("<td class='bgbai'>"+ranks[i].isOnlineName+"</td>");
		}
	};
	Utils.common.ajax.commonAjax(url, false, null, successCallback);
	
	//加入匹配队列
	$("#match").bind("click",function(){
		var url = "/vouching/competition/joinQueue";
		var successCallback = function(data){
			layer.open({
				  type: 1 ,
				  area: ['430px', '250px'] ,
				  shade: 0.6 ,
				  anim: 3 ,
				  closeBtn: 1,
				  title : '匹配中,请等待...' ,
				  content: '<div style="padding:10px;"><div style="padding:10px;"><table><tbody><tr id="competitionQueue"></tr></tbody></table></div><div style="text-align: center;"><h5 id="info">匹配中....</h5></div></div>',
				  success : function(){
					  $(this).everyTime('1s','validateMatchQuery',function(){
						  var url = "/vouching/competition/matching";
						  var successCallback = function(data){
							  $("#competitionQueue").children().remove();
							  var users = data.detail.users;
							  for (var i = 0 ; i < users.length ; i++) {
								  $("#competitionQueue").append("<td id='" +users[i].userId+ "'></td>");
								  var td = $("#competitionQueue>td").eq(i);
								  td.append("<img src='/vouching/images/pic_user1.jpg' width='70px' height='60px'/>");
								  td.append("<h3 style='text-align: center;'>" +users[i].name+ "</h3>");	  
							  }
							  if (data.detail.isDone) {
								  $(this).stopTime("validateMatchQuery");
								  $("#info").text("匹配成功!");
								  $(this).oneTime('2s',function(){//匹配成功后，1s后跳转
									  window.parent.menu.location.href = "/vouching/forward/forwardCompetitionExam";
									});
								  }
						  };
						  var faildCallback = function(data){
							  //nope
						  }
						  Utils.common.ajax.commonAjax(url, false, null, successCallback, faildCallback);
						},0,true);
				  }
				});
		};
		Utils.common.ajax.commonAjax(url, false, null, successCallback);
	});
	
});