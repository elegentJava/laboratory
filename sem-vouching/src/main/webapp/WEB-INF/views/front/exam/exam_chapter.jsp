<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="../common/css.jsp"%>
<link href="/vouching/css/front/style2.css" rel="stylesheet" type="text/css" />
<title>章节设置</title>
</head>

<body>
	<table width="50%" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="600" align="center" valign="top" class="left_td"></td>
			<td align="center" valign="top" class="right_td">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="80" rowspan="3">
							<img src="/vouching/images/pic_user1.jpg" width="80" height="70" /></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" class="weizhi">&nbsp; 
							<strong>首页 ->考试平台 ->章节设置</strong>
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				<table width="90%" border="0" cellspacing="0" cellpadding="0">
					<tr><td height="20"></td></tr>
				</table>
				<table width="90%" border="0" cellspacing="1" cellpadding="0" class="content_table1">
					<thead>
						<tr><td colspan="2" class="teatbbai" align="left">&nbsp;已激活章节列表</td></tr>
						<tr class="teatb" style="color: #fff;" height="25px" align="center">
							<td width="191" align="center">点击改变激活状态</td>
							<td width="136" align="center">章节名</td>
						</tr>
					</thead>
					<tbody id="activeList"></tbody>
				</table>
				<table width="90%" border="0" cellspacing="1" cellpadding="0" class="content_table1">
					<thead>
						<tr><td colspan="2" class="teatbbai" align="left">&nbsp;待激活章节列表</td></tr>
						<tr class="teatb" style="color: #fff;" height="25px" align="center">
							<td width="191" align="center">点击改变激活状态</td>
							<td width="136" align="center">章节名</td>
						</tr>
					</thead>
					<tbody id="inactiveList"></tbody>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr><td height="20"></td></tr>
				</table>
			</td>
		</tr>
	</table>
	<jsp:include page="../common/foot.jsp" />
</body>
<%@include file="../common/js.jsp" %>
<script type="text/javascript" src="/vouching/js/front/exam/exam.chapter.js"></script>
</html>