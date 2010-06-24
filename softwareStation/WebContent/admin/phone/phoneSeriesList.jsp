<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>机型列表</title>
</head>
<body>

<div class="page_title">管理中心 &gt; 机型列表</div>
<div class="button_bar">
<input type="button" value="新增机型" onclick="to('/admin/phoneSeries!addInit.action')"/>
</div>
<br/>
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>操作系统</th>
		<th>品牌</th>
		<th>机型</th>
		<th>创建时间</th>
		<th>操作</th>
	</tr>
	<c:choose>
		<c:when test="${pageResult.list eq null}">
			<tr >
				<td colspan="6" align="center">暂无数据</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="phoneSeries" items="${pageResult.list }">
				<tr>
					<td class="list_data_number">${phoneSeries.id }</td>
					<td class="list_data_text">${phoneSeries.os.name }</td>
					<td class="list_data_text">${phoneSeries.brand.name }</td>
					<td class="list_data_text"><a href="phoneSeries!detail.action?id=${phoneSeries.id }">${phoneSeries.name }</a></td>
					<td class="list_data_text">
					<fmt:formatDate value="${phoneSeries.createTime }" pattern="yyyy-MM-dd" var="ct"/>
						${ct }
					</td>
					<td class="list_data_text">
						<input type="button" onclick="to('phoneSeries!detail.action?id=${phoneSeries.id }')" value="编辑">
						<input type="button" onclick="del('phoneSeries!delete.action?id=${phoneSeries.id }')" value="删除">
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
</body>
</html>