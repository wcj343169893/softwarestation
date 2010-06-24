<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改软件类型</title>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
</head>
<body>

<c:choose>
	<c:when test="${softwareType eq null}">
	<div class="page_title">管理中心 &gt; 新增软件类型</div>
	<div class="button_bar">
	</div>
	<br/>
	<form action="softwareType!add.action" method="post">
	<table class="query_form_table">
			<tr>
				<th>软件类型名称:</th><td><input name="name"/></td>
			</tr>
			<tr>
				<th>排序:</th><td><input name="zindex"/></td>
			</tr>
			<tr>
				<th>是否换行:</th>
				<td>
					<input type="radio" name="isWrap" checked="checked" value="1" id="isWrapY">
					<label for="isWrapY">是</label>
					<input type="radio" name="isWrap" value="0" id="isWrapN">
					<label for="isWrapN">否</label>
				</td>
			</tr>
			<tr>
				<th>是否显示:</th>
				<td>
					<input type="radio" name="isShow" checked="checked" value="1" id="shows">
					<label for="shows">显示</label>
					<input type="radio" name="isShow" value="0" id="eables">
					<label for="eables">隐藏</label>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="新增"/>
				</td>
			</tr>
		</table>
	</form>
	</c:when>
	<c:otherwise>
	<div class="page_title">管理中心 &gt; 修改软件类型</div>
	<div class="button_bar">
	</div>
	<br/>
		<form action="softwareType!edit.action"  method="post">
			<table class="query_form_table">
				<tr>
					<th>编号</th><td><input name="id" readonly="readonly" value="${softwareType.id }"/></td>
				</tr>
				<tr>
					 <th>名称</th><td><input name="name" value="${softwareType.name }"/></td>
				</tr>
				<tr>
					<th>排序:</th><td><input name="zindex" value="${softwareType.zindex }"/></td>
				</tr>
				<tr>
				<th>是否换行:</th>
				<td>
					<input type="radio" name="isWrap" <c:if test="${softwareType.isWrap == 1}">checked</c:if> value="1" id="isWrapY">
					<label for="isWrapY">是</label>
					<input type="radio" name="isWrap" <c:if test="${softwareType.isWrap == 0}">checked</c:if> value="0" id="isWrapN">
					<label for="isWrapN">否</label>
				</td>
				</tr>
				<tr>
					<th>是否显示:</th>
					<td>
						<input type="radio" name="isShow" value="1" <c:if test="${softwareType.isShow == 1}">checked</c:if> id="shows">
						<label for="shows">显示</label>
						<input type="radio" name="isShow" value="0" <c:if test="${softwareType.isShow == 0}">checked</c:if> id="eables">
						<label for="eables">隐藏</label>
					</td>
				</tr>
				<tr>
					 <th>修改时间</th><td><input name="createTime" readonly="readonly" value="${softwareType.createTime }"/></td>
				</tr>
				<tr>
					 <td colspan="2" align="center"><input type="submit" value="修改"/></td>
				</tr>
			</table>
		</form>
	</c:otherwise>
</c:choose>
</body>
</html>