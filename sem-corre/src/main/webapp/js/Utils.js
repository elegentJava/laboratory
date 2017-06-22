var Utils = {

	// 数据模块
	datas : {
		errorUrl : "",
		successFlag : "success",
	},

	// AJAX操作
	ajax : {
		commonAjax : function(url, async, data, successCallback, faildCallback) {
			var formatData = {
				params : data
			};
			if (data == null) {
				var temp = {
					unused : ""
				};
				formatData = {
					params : temp
				};
			}
			$.ajax({
				type : "post",
				url : url,
				async : async,
				dataType : 'json',
				contentType : 'application/json',
				data : JSON.stringify(formatData),
				success : function(data) {
					if (Utils.datas.successFlag == data.result) {
						if (successCallback != null) {
							successCallback(data);
						}
					} else {
						faildCallback(data);
					}
				},
				error : function(data) {
					VCUtils.common.util.simpleHref(VCUtils.datas.errorUrl);
				}
			});
		}
	},

	// 简单的工具类
	util : {

		isNotNullOrBlank : function(val) {
			if (val == null || $.trim(val) == "") {
				return false;
			}
			return true;
		},

		simpleHref : function(url) {
			window.location.href = url;
		},
	}

};