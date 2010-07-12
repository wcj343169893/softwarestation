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
<title>支持扩展名列表</title>
</head>
<body>

<div class="page_title">管理中心 &gt; 支持扩展名列表</div>
<div class="button_bar">
<input type="button" value="新增扩展名" onclick="to('extension!detail.action?id=-1')"/>
</div>
<br/>
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>名称</th>
		<th>操作</th>
	</tr>
	<c:choose>
		<c:when test="${pageResult.list eq null}">
			<tr >
				<td colspan="3" align="center">暂无数据</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="extension" items="${pageResult.list }">
				<tr>
					<td class="list_data_number">${extension.id }</td>
					<td class="list_data_text"><a href="extension!detail.action?id=${extension.id }">${extension.name }</a></td>
					<td class="list_data_text">
						<input type="button" onclick="to('extension!detail.action?id=${extension.id }')" value="编辑">
						<input type="button" onclick="del('extension!delete.action?id=${extension.id }')" value="删除">
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<div class="pager">
		<c:set var="page" value="${pageResult}"></c:set> 共${page.recTotal }条记录 每页<input
			value="${page.pageSize}" size="2" id="pageSize"/>条 第<input value="${page.pageNo}" size="2"/>页/共${page.pageTotal}页
		<a href="javascript:mysearch(1)">第一页</a> <c:if test="${page.pageNo!=1}"><a href="javascript:mysearch(${page.pageNo-1 })">上一页</a> </c:if>
				<c:if test="${page.pageNo<page.pageTotal}"><a href="javascript:mysearch(${page.pageNo+1 })">下一页</a> </c:if> <a
			href="javascript:mysearch(${page.pageTotal})">最后一页</a> 转到<input
			value="${page.pageNo}" size="2" id="pageNo" />页
		<button onclick="javascript:mysearch(-1)">GO</button>
</div>
<script type="text/javascript">
function mysearch(p){
	if(p==-1){
			p=document.getElementById("pageNo").value;
	}
	to('extension!list.action?p='+p);
}
</script>
</body>
</html>