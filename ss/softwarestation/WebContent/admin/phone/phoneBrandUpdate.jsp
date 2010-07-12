<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新品牌</title>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
</head>
<body>

<c:choose>
	<c:when test="${phoneBrand eq null}">
	<div class="page_title">管理中心 &gt; 新增品牌</div>
	<div class="button_bar">
	</div>
	<br/>
	<form action="phoneBrand!add.action" method="post">
	<table class="query_form_table">
				<tr>
		<th>操作品牌名称:</th><td><input name="name"/><input type="submit" value="新增"/></td>
			</tr>
		</table>
	</form>
	</c:when>
	<c:otherwise>
	<div class="page_title">管理中心 &gt; 更新品牌</div>
	<div class="button_bar">
	</div>
	<br/>
		<form action="phoneBrand!edit.action"  method="post">
			<table class="query_form_table">
				<tr>
					<th>编号</th><td><input name="id" readonly="readonly" value="${phoneBrand.id }"/></td>
				</tr>
				<tr>
					 <th>名称</th><td><input name="name" value="${phoneBrand.name }"/></td>
				</tr>
				<tr>
					 <th>修改时间</th><td><input name="createTime" readonly="readonly" value="${phoneBrand.createTime }"/></td>
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