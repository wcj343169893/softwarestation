<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改手机平台</title>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
<script type="text/javascript">
	function mysubmit(index){
			var myform=document.getElementById("myform");
				myform.action=index==0?"phoneOs!add.action":"phoneOs!edit.action";
				myform.submit();
			}
</script>
</head>
<body>
<c:set value="${phoneOs eq null ? '新增' : '编辑' }" var="titles"></c:set>
<c:set value="${phoneOs eq null ? 0 : 1 }" var="idx"></c:set>
<div class="page_title">管理中心 &gt; ${titles }平台</div>
<div class="button_bar">
</div>
<br/>
	<form action=""  method="post" id="myform">
	<input name="id" readonly="readonly" type="hidden" value="${phoneOs.id }"/>
		<table class="query_form_table">
			<tr>
				 <th>名称</th><td><input name="name" value="${phoneOs.name }"/></td>
			</tr>
			<tr>
				 <th>支持扩展名</th>
				 <td>
				 	<c:forEach items="${extensionList}" var="extension" varStatus="vs">
				 	<c:set value="" var="isChecked"></c:set>
				 		<c:forEach items="${phoneOs.extensionList }" var="ext">
				 			<c:if test="${ext.id==extension.id}">
				 				<c:set value="checked" var="isChecked"></c:set>
				 			</c:if>
				 		</c:forEach>
				 		<input type="checkbox" name="extensions" value="${extension.id }" id="ext${extension.id }" ${isChecked }/><label for="ext${extension.id }">${extension.name }</label>
				 		<c:if test="${vs.count %3==0}"><br/> </c:if>
				 	</c:forEach>
				 </td>
			</tr>
			<tr>
				 <td colspan="2" align="center"><input type="button" onclick="mysubmit(${idx})" value="${titles }"/></td>
			</tr>
		</table>
	</form>
</body>
</html>