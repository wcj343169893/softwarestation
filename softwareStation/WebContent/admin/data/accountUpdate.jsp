<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>推广费用</title>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
<script type="text/javascript" src="/admin/DatePicker/WdatePicker.js"></script> 
<script type="text/javascript">
	function mysubmit(){
			var at=document.getElementById("id");
			var myform=document.getElementById("myform");
			if(at.value == ""){
				myform.action="account!add.action";
			}else{
				myform.action="account!edit.action";
			}
			myform.submit();
		}
</script>
</head>
<body>
<c:set value="${account eq null ?'新增':'编辑' }" var="titles"></c:set>
<div class="page_title">管理中心 &gt; 数据管理 &gt; ${titles }推广费用</div>
	<div class="button_bar">
	</div>
	<br/>
<form action="#" method="post" id="myform">
<input type="hidden" name="id" value="${account.id }" id="id">
	<table class="query_form_table">
		<tr>
			<th>日期：</th>
			<td><input type="text" name="beginTime" onClick="WdatePicker()" value="${account.createTime }">(必填)</td>
		</tr><tr>
			<th>分类：</th>
			<td>
			<select name="atId">
				<c:forEach items="${accountTypeList}" var="accountType">
						<option value="${accountType.id }" <c:if test="${accountType.id==account.accType.id }">selected</c:if>>${accountType.name }</option>
				</c:forEach>
			</select><a href="#" onclick="to('accountType!detail.action?id=-1')">新增分类</a>
			</td>
		</tr><tr>
			<th>金额：</th>
			<td><input name="price" value="${account.price }">(必填)</td>
		</tr><tr>
			<th>说明：</th>
			<td>
				<textarea rows="8" cols="20" name="explanation">${account.explanation }</textarea>
			</td>
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