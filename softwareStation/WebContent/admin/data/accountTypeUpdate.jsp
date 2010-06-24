<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>推广类型</title>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
<script type="text/javascript" src="/admin/DatePicker/WdatePicker.js"></script> 
<script type="text/javascript">
	function mysubmit(){
			var at=document.getElementById("id");
			var myform=document.getElementById("myform");
			if(at.value == ""){
				myform.action="accountType!add.action";
			}else{
				myform.action="accountType!edit.action";
			}
			myform.submit();
		}
</script>
</head>
<body>
<c:set value="${accountType eq null ?'新增':'编辑' }" var="titles"></c:set>
<div class="page_title">管理中心 &gt; 数据管理 &gt;${titles }推广类型</div>
	<div class="button_bar">
	</div>
	<br/>
<form action="" method="post" id="myform">
	<input type="hidden" value="${accountType.id }" name="id" id="id">
	<table class="query_form_table">
		<tr>
			<th>名称：</th>
			<td><input name="name" value="${accountType.name }"></td>
		</tr>
		<tr>
			<td colspan="2" style="text-align: center;">
				<input type="button" onclick="mysubmit()" value="${titles }">
			</td>
		</tr>
	</table>
</form>
</body>
</html>