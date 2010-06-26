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
<title>品牌列表</title>
</head>
<body>

<div class="page_title">管理中心 &gt; 品牌列表</div>
<div class="button_bar">
<input type="button" value="新增品牌" onclick="to('/admin/phone/phoneBrandUpdate.jsp')"/>
</div>
<br/>
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>品牌名称</th>
		<th>创建时间</th>
		<th>操作</th>
	</tr>
	<c:choose>
		<c:when test="${pageResult.list eq null}">
			<tr >
				<td colspan="3" align="center">暂无数据</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="phoneBrand" items="${pageResult.list }">
				<tr>
					<td class="list_data_number">${phoneBrand.id }</td>
					<td class="list_data_text"><a href="phoneBrand!detail.action?id=${phoneBrand.id }">${phoneBrand.name }</a></td>
					<td class="list_data_text">
					<fmt:formatDate value="${phoneBrand.createTime }" pattern="yyyy-MM-dd" var="ct"/>
						${ct }
					</td>
					<td class="list_data_text">
						<input type="button" onclick="to('phoneBrand!detail.action?id=${phoneBrand.id }')" value="编辑">
						<input type="button" onclick="del('phoneBrand!delete.action?id=${phoneBrand.id }')" value="删除">
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<div class="pager">
<c:set var="page" value="${pageResult}"></c:set> 共${page.recTotal }条记录 每页<input
			value="${page.pageSize}" size="2" id="pageSize"/>条 第<input value="${page.pageNo}" size="2"/>页/共${page.pageTotal}页
		<a href="phoneBrand!list.action?p=1">第一页</a> <c:if test="${page.pageNo!=1}"><a href="phoneBrand!list.action?p=${page.pageNo-1 }">上一页</a> </c:if>
				<c:if test="${page.pageNo<page.pageTotal}"><a href="phoneBrand!list.action?p=${page.pageNo+1 }">下一页</a> </c:if> <a
			href="phoneBrand!list.action?p=${page.pageTotal}">最后一页</a> 
		</div>
</body>
</html>