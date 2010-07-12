<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
<script type="text/javascript" src="/admin/DatePicker/WdatePicker.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激活编辑</title>
</head>
<body>
<div class="page_title">管理中心 &gt; 数据管理&gt;  激活编辑</div>
	<div class="button_bar">
	</div>
	<br/>
	<form action="" method="post" id="softwareInfoForm1">
	<table class="query_form_table">
		<tr>
			<th>
				软件名称
			</th>
			<td>
				aaa
				<input name="sid" type="hidden" value="11">
			</td>
		</tr>
		<tr>
			<th>
				激活数量
			</th>
			<td>
				<input name="number" value="11">
			</td>
		</tr>
		<tr>
			<th>
				激活单价(元)
			</th>
			<td>
				<input name="price" value="11">
			</td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;">
				<input type="submit" value="保存">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>