<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>softwareStation</title>
</head>
<body>
主页，负责跳转(区分浏览器)
<a href="/admin/index.jsp" target="_parent">管理中心</a>
<a href="/wap/index.php" target="_parent">wap站</a>
<%
	response.sendRedirect("/index.php");
%>
</body>
</html>