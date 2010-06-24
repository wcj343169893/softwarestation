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
<title>分类列表</title>
</head>
<body>

<div class="page_title">管理中心 &gt; 分类列表</div>
<div class="button_bar">
<input type="button" value="新增分类" onclick="to('/admin/software/softwareTypeUpdate.jsp')"/>
</div>
<br/>
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>分类名称</th>
		<th>状态</th>
		<th>排序</th>
		<th>换行</th>
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
			<c:forEach var="softwareType" items="${pageResult.list }">
				<tr>
					<td class="list_data_number">${softwareType.id }</td>
					<td class="list_data_text"><a href="softwareType!detail.action?id=${softwareType.id }">${softwareType.name }</a></td>
					<td class="list_data_text">${softwareType.isShow==0 ?"隐藏":"显示" }</td>
					<td class="list_data_text">${softwareType.zindex }</td>
					<td class="list_data_text">${softwareType.isWrap == 0 ? "否":"是" }</td>
					<td class="list_data_text">
					<fmt:formatDate value="${softwareType.createTime }" pattern="yyyy-MM-dd" var="ct"/>
						${ct }
					</td>
					<td class="list_data_text">
						<input type="button" onclick="to('softwareType!detail.action?id=${softwareType.id }')" value="编辑">
						<input type="button" onclick="del('softwareType!delete.action?id=${softwareType.id }')" value="删除">
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
</body>
</html>