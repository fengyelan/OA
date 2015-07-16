<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>

<head>
<%@include file="/WEB-INF/jsp/public/common.jspf"  %>
	<title>首页</title>
	<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery_treeview/jquery.cookie.js"></script>
</head>

<frameset rows="100,*,25" framespacing=0 border=0 frameborder="0">
		<frame noresize name="TopMenu" scrolling="no" src="${pageContext.request.contextPath}/home_top.action">
		<frameset cols="180,*" id="resize">
			<frame noresize name="menu" scrolling="yes" src="${pageContext.request.contextPath}/home_left.action">
			<frame noresize name="right" scrolling="yes" src="${pageContext.request.contextPath}/home_right.action">
		</frameset>
		<frame noresize name="status_bar" scrolling="no" src="${pageContext.request.contextPath}/home_bottom.action">
	</frameset>

	<noframes><body>
</body>
</noframes>
</html>
