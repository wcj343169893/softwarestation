<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>登录</title>
</head>
<body>
<form action="/admin/login" method="post">
	用户名:<input name="account" value="mangzi"/><br />
	密码:<input type="password" name="password" value="123456"/><br />
	<input  type="submit" value="登录"/>
</form>
</body>
</html>