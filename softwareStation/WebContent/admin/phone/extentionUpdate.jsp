<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改支持扩展名</title>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
<script type="text/javascript">
	function mysubmit(index){
			var myform=document.getElementById("myform");
				myform.action=index==0?"extension!add.action":"extension!edit.action";
				myform.submit();
			}
</script>
</head>
<body>
<c:set value="${extension eq null ? '新增' : '编辑' }" var="titles"></c:set>
<c:set value="${extension eq null ? 0 : 1 }" var="idx"></c:set>
<div class="page_title">管理中心 &gt; ${titles }支持扩展名</div>
<div class="button_bar">
</div>
<br/>
	<form action=""  method="post" id="myform">
	<input name="id" readonly="readonly" type="hidden" value="${extension.id }"/>
		<table class="query_form_table">
			<tr>
				 <th>名称</th><td><input name="name" value="${extension.name }"/>例如:
					 <span class="red_test">
						 <strong>.jar</strong> 
					 </span>
				 </td>
			</tr>
			<tr>
				 <td colspan="2" align="center"><input type="button" onclick="mysubmit(${idx})" value="${titles }"/></td>
			</tr>
		</table>
	</form>
</body>
</html>