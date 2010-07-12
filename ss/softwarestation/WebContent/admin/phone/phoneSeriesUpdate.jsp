<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改机型</title>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
</head>
<body>

<c:choose>
	<c:when test="${phoneSeries eq null}">
	<div class="page_title">管理中心 &gt; 新增机型</div>
	<div class="button_bar">
	</div>
	<br/>
	<form action="phoneSeries!add.action" method="post">
	<table class="query_form_table">
			<tr>
				<th>请选择操作系统:</th>
				<td>
					<select name="phoneOsId">
						<c:forEach items="${phoneOsList }" var="phoneOs">
							<option value="${phoneOs.id }">${phoneOs.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>请选择品牌:</th>
				<td>
					<select name="phoneBrandId">
						<c:forEach items="${phoneBrandList }" var="phoneBrand">
							<option value="${phoneBrand.id }">${phoneBrand.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>机型名称:</th>
				<td><textarea rows="10" cols="40" name="models"></textarea><span style="color: red;">*用,号隔开</span>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="新增"/></td>
			</tr>
			
		</table>
	</form>
	</c:when>
	<c:otherwise>
	<div class="page_title">管理中心 &gt; 更新机型</div>
	<div class="button_bar">
	</div>
	<br/>
		<form action="phoneSeries!edit.action"  method="post">
			<table class="query_form_table">
				<tr>
					<th>编号:</th><td><input name="id" readonly="readonly" value="${phoneSeries.id }"/></td>
				</tr>
				<tr>
				<th>操作系统:</th>
				<td>
					<select name="phoneOsId">
						<c:forEach items="${phoneOsList }" var="phoneOs">
							<option value="${phoneOs.id }" <c:if test="${phoneOs.id==phoneSeries.os.id }">selected</c:if>>${phoneOs.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th>品牌:</th>
				<td>
					<select name="phoneBrandId">
						<c:forEach items="${phoneBrandList }" var="phoneBrand">
							<option value="${phoneBrand.id }" <c:if test="${phoneBrand.id==phoneSeries.brand.id }">selected</c:if>>${phoneBrand.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
				<tr>
					 <th>修改时间:</th><td><input name="createTime" readonly="readonly" value="${phoneSeries.createTime }"/></td>
				</tr>
				<tr>
				<th>机型名称:</th>
				<td>
					<textarea rows="10" cols="40" name="models" style="word-spacing: normal">${models }</textarea><span style="color: red;">*用,号隔开</span>
				</td>
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