<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/css.jsp"%>
<link href="/vouching/css/front/style2.css" rel="stylesheet" type="text/css" />
<title>词汇查询</title>
</head>
<body>
<div>
	<table width="960" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="600" align="center" valign="top" class="left_td">
				<form method="post" action="#">
					<table border="0" cellspacing="0" cellpadding="0" class="left_td_tb">
						<tr><td>请输入您要查询的英（中）文单词</td></tr>
						<tr>
							<td>
								<input type="text" size="20" id="word"/> 
								<input id="query" type="button" value="提交查询" />
							</td>
						</tr>
						<tr>
							<td id="glossarySources"></td>
						</tr>
					</table>
				</form>
			</td>
			<td align="center" valign="top" style="background: url(/vouching/images/bg1.jpg) no-repeat right bottom;" class="right_td">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80" rowspan="2">
							<img src="/vouching/images/pic_user1.jpg" width="80" height="70" />
						</td>
						<td class="weizhi"><strong> 首页 -> 资源平台 -> 单词查询</strong></td>
					</tr>
					<tr><td class="weizhi_under">&nbsp;</td></tr>
				</table>
				<table width="519" border="0" cellspacing="1" cellpadding="0" class="content_table1">
					<thead>
						<tr class="teatb" style="color: #fff;" height="25px" align="center;">
							<td width="30%" ><strong>查询结果</strong></td>
							<td width="40%" ><strong>翻译</strong></td>
							<td width="30%"><strong>词汇来源</strong></td>
						</tr>
					</thead>
					<tbody id="glossaryList"></tbody>
					<tfoot id="pager">
						<%@include file="../common/pager.jsp"%>
					</tfoot>
				</table>
			</td>
		</tr>
	</table>
	<jsp:include page="../common/foot.jsp" />
</div>
</body>
<%@include file="../common/js.jsp" %>
<script type="text/javascript" src="/vouching/js/front/resource/glossary.js"></script>
</html>