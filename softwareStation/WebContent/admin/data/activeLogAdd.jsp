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
<title>新增激活记录</title>
</head>
<body>
<div class="page_title">管理中心 &gt; 数据管理&gt;  新增激活记录</div>
	<div class="button_bar">
	</div>
	<br/>
	<table class="query_form_table">
		<tr>
			<th>
				软件名称
			</th>
			<td>
				${softwareInfo.name }
			</td>
		</tr>
	</table>
	<br>
	<form action="activeLog!add.action" method="post">
	<input name="sid" type="hidden" value="${softwareInfo.id }">
	<table class='query_form_table'>
		<tr>
			<th>激活数</th>
			<td>
			<input name="number" value="${activeLog.number }">(必填)
			</td>
		</tr>
		<tr>
			<th>激单价(元)</th>
			<td>
			<input name="price" value="${activeLog.price }">(必填)
			</td>
		</tr>
		<tr>
			<th>日期</th>
			<td>
			<fmt:formatDate value="${activeLog.activeTime }" pattern="yyyy-MM-dd" var="alt"/>
			<input type="text" name="beginTime" onClick="WdatePicker()" value="${alt }" readonly="readonly" size="10">(必填)
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="保存">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>