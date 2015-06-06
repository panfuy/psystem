<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>员工信息管理</title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>css/liger_ui/css/ligerui-all.css" />
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.2.js">
		</script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.form.js">
		</script>
		<script type="text/javascript" src="<%=basePath%>js/ligerui.all.js">
		</script>
		<script type="text/javascript">
function itemclick(item) {
	alert(item.text);
}
$(function() {
	$("#toptoolbar").ligerToolBar( {
		items : [ {
			text : '增加',
			click : itemclick
		}, {
			line : true
		}, {
			text : '修改',
			click : itemclick
		}, {
			line : true
		}, {
			text : '删除',
			click : itemclick
		} ]
	});
});
</script>
	</head>
	<body>
		<div id="toptoolbar"></div>
	</body>
</html>