<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
<script type="text/javascript" src="/admin/DatePicker/WdatePicker.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评论列表</title>
<script type="text/javascript">
	function mysearch(p){
			var myform=	document.getElementById("myform");
			if(p==-1){
					p=document.getElementById("pageNo").value;
				}
			myform.action="lscommentary.action?p="+p;
			myform.submit();
		}
</script>
</head>
<body>
<div class="page_title">管理中心 &gt;数据管理 &gt;评论</div>
<div class="page_search" style="float: left; width: 600px;">
<form action="" method="post" id="myform">
评论内容:<input name="content" value="${content }">
日期
<input type="text" name="beginTime" onClick="WdatePicker()" value="${beginTime }" readonly="readonly">
到<input type="text" name="endTime" onClick="WdatePicker()" value="${endTime }" readonly="readonly">
<input type="button" onclick="mysearch(1)" value="查询">
</form>
</div>
<div class="button_bar" style="float: right;">
</div>
<br/>
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>软件名称</th>
		<th>评论机型</th>
		<th>内容</th>
		<th>评论时间</th>
		<th>操作</th>	
	</tr>
	<c:choose>
		<c:when test="${pageResult.list eq null}">
			<tr >
				<td colspan="6" align="center">暂无数据</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="commentary" items="${pageResult.list }">
				<tr>
					<td class="list_data_number">${commentary.id }</td>
					<td class="list_data_text"><a href="softwareInfo!detail.action?id=${commentary.softwareInfo.id }">${commentary.softwareInfo.name }</a></td>
					<td class="list_data_text">${commentary.phoneModel.name }</td>
					<td class="list_data_text">${commentary.content}</td>
					<td class="list_data_text">
					<fmt:formatDate value="${commentary.commentTime }" pattern="yyyy-MM-dd" var="ct"/>
						${ct }
					</td>
					<td class="list_data_text">
						<input type="button" onclick="del('commentary!delete.action?id=${commentary.id}')" value="删除">
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

</body>
</html>