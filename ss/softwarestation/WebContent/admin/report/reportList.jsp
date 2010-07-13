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
<title>报错列表</title>
<script type="text/javascript">
	function mysearch(p){
			var myform=	document.getElementById("myform");
			if(p==-1){
					p=document.getElementById("pageNo").value;
				}
			myform.action="listreport.action?p="+p;
			myform.submit();
		}
</script>
</head>
<body>
<div class="page_title">管理中心 &gt;数据管理 &gt;报错</div>
<div class="page_search" style="float: left; width: 800px;">
<form action="" method="post" id="myform">
报错类型
<select name="rid">
	<option value="0">全部</option>
	<c:forEach items="${reports}" var="rs">
		<option value="${rs.key }" <c:if test="${rs.key==rid }">selected</c:if>>${rs.value }</option>
	</c:forEach>
</select>

报错附言:<input name="ps" value="${ps }">
日期
<input type="text" name="beginTime" onClick="WdatePicker()" value="${beginTime }" readonly="readonly">
到<input type="text" name="endTime" onClick="WdatePicker()" value="${endTime }" readonly="readonly">
<input type="button" onclick="mysearch(1)" value="查询">
</form>
</div>
<div class="button_bar" style="float: right;">
</div>
<br/>
<c:set value="0" var="allMoney"></c:set>
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>软件名称</th>
		<th>报错机型</th>
		<th>报错类型</th>
		<th>附言</th>
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
			<c:forEach var="report" items="${pageResult.list }">
				<tr>
					<td class="list_data_number">${report.id }</td>
					<td class="list_data_text"><a href="softwareInfo!detail.action?id=${report.softwareInfo.id }">${report.softwareInfo.name }</a></td>
					<td class="list_data_text">${report.phoneModel.phoneseries.brand.name } - ${report.phoneModel.name }</td>
					<td class="list_data_text">${reports[report.rid]}</td>
					<td class="list_data_text">${report.ps}</td>
					<td class="list_data_text">
					<fmt:formatDate value="${report.reportTime }" pattern="yyyy-MM-dd" var="ct"/>
						${ct }
					</td>
					<td class="list_data_text">
						<input type="button" onclick="del('report!delete.action?id=${report.id }')" value="删除">
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