<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理主页</title>
</head>
<frameset rows="10,*" border="1">
	<frame name="top" src="top.jsp" scrolling="no"  noresize="noresize">
	<frameset cols="160,*" border="1">
		<frame name="menu" src="menu.jsp"  scrolling="auto" noresize="noresize">
		<frame name="workCenter" src="main.jsp" scrolling="auto"  noresize="noresize">
	</frameset>
</frameset>
</html>