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
<title>激活记录</title>
</head>
<body>
<div class="page_title">管理中心 &gt; 数据管理&gt;  激活记录</div>
	<div class="button_bar">
	</div>
	<br/>
	<table class="data_list_table">
		<tr>
			<th>
				软件名称
			</th>
			<td>
				${softwareInfo.name }
			</td>
			<th>
				软件分类
			</th>
			<td>
				${softwareInfo.softwareType.name }
			</td>
		</tr>
	</table>
	<br>
	<table class='data_list_table'>
				<tr>
					<th>编号</th>
					<th>激活数量</th>
					<th>单价(元)</th>
					<th>总价(元)</th>
					<th>日期</th>
					<th>操作</th>
				</tr>
	</table>
	<c:forEach items="${softwareInfo.activeLogList}" var="activeLog">
		<form action="activeLog!edit.action" method="post">
		<input name="sid" value="${softwareInfo.id }" type="hidden" />		
		<table class="query_form_table">
			<tr>
				<td class="list_data_text">
				${activeLog.id }
					<input name="id" value="${activeLog.id }" readonly="readonly" type="hidden"> 
				</td>
				<td class="list_data_text">
					<input name="number" value="${activeLog.number }">
				</td>
				<td class="list_data_text">
					<input name="price" value="${activeLog.price }">
				</td>
				<td class="list_data_text">
					${activeLog.price*activeLog.number }
				</td>
				<td class="list_data_text">
					<fmt:formatDate value="${activeLog.activeTime }" pattern="yyyy-MM-dd" var="alt"/>
					<input type="text" name="beginTime" onClick="WdatePicker()" value="${alt }" readonly="readonly" size="10">
				</td>
				<td style="text-align: center;">
					<input type="submit" value="修改">
					<input type="button" value="删除">
				</td>
			</tr>
		</table>
		</form>
	</c:forEach>
</body>
</html>