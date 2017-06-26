<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>函电资源站</title>
<link rel="stylesheet" type="text/css"
	href="/corre/dep/bootstrap-3.3.7/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/corre/css/default.css">
<link rel="stylesheet" type="text/css" href="/corre/css/styles.css">
<link rel="stylesheet" type="text/css" href="/corre/css/index.css">

</head>
<body>

	<!-- 发送函电模态框（Modal） -->
	<div class="modal fade" id="sendCorre" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">发送函电</h4>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="recipient-name" class="control-label">收件人:</label> <input
								type="text" class="form-control" id="corre_receiver">
						</div>
						<div class="form-group">
							<label for="message-text" class="control-label">主题:</label> <input
								class="form-control" id="corre_subject" />
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="send" type="button" class="btn btn-primary">立刻发送</button>
				</div>
			</div>
		</div>
	</div>

	<!--翻译模态框 -->
	<div class="modal fade" tabindex="-1" role="dialog" id="translate">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">函电翻译</h4>
				</div>
				<div class="modal-body">
					<p id="corre_translate"></p>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<!-- nav -->
	<div class="nav-main">
		<div class="nav-box">
			<div class="nav">
				<ul id="nav" class="nav-ul"></ul>
			</div>
		</div>
	</div>
	<!-- content -->
	<div class='container'>
		<div id="categories" class='container_ui'>
			<div class='container_ui__heading'>
				<div class='header_icon'></div>
				<h1>类型站</h1>
				<div class='menu_icon'>
					<div class='div'></div>
					<div class='div'></div>
					<div class='div'></div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" src="/corre/dep/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="/corre/dep/bootstrap-3.3.7/bootstrap.min.js"></script>
<script type="text/javascript" src="/corre/js/Utils.js"></script>
<script type="text/javascript" src="/corre/js/corre.js"></script>

</html>