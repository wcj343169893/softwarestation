<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
<script type="text/javascript" src="/admin/DatePicker/WdatePicker.js"></script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报告机型列表</title>
<script type="text/javascript">
	function mysearch(p){
			var myform=	document.getElementById("myform");
			if(p==-1){
					p=document.getElementById("pageNo").value;
				}
			myform.action="reportModel!list.action?p="+p;
			myform.submit();
		}
</script>
</head>
<body>
<div class="page_title">管理中心 &gt;机型管理  &gt; 报告机型列表</div>
<div class="page_search" style="float: left; width: 820px;">
<form action="" method="post" id="myform">
品牌:<input name="brand" value="${brand }">
机型:<input name="model" value="${model }">
是否处理:
<select name="deal">
	<option value="2" <c:if test="${deal==2 }">selected</c:if>>全部</option>
	<option value="0" <c:if test="${deal==0 }">selected</c:if>>未处理</option>
	<option value="1" <c:if test="${deal==1 }">selected</c:if>>已处理</option>
</select>

日期
<input type="text" name="beginTime" onClick="WdatePicker()" value="${beginTime }" readonly="readonly">
到<input type="text" name="endTime" onClick="WdatePicker()" value="${endTime }" readonly="readonly">
<input type="button" onclick="mysearch(1)" value="查询">
</form>
</div>
<br/>
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>品牌</th>
		<th>机型</th>
		<th>是否处理</th>
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
			<c:forEach var="reportModel" items="${pageResult.list }">
				<tr>
					<td class="list_data_number">${reportModel.id }</td>
					<td class="list_data_text">${reportModel.brandName }</td>
					<td class="list_data_text">${reportModel.modelName }</td>
					<td class="list_data_text">${reportModel.deal==0?"未处理":"已处理" }</td>
					<td class="list_data_text">
					<fmt:formatDate value="${reportModel.reportTime }" pattern="yyyy-MM-dd" var="ct"/>
						${ct }
					</td>
					<td class="list_data_text">
						<c:if test="${reportModel.deal==0 }">
							<input type="button" onclick="to('reportModel!deal.action?id=${reportModel.id }')" value="处理">
						</c:if>
						<input type="button" onclick="del('reportModel!delete.action?id=${reportModel.id }')" value="删除">
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
<jsp:include page="/admin/common/pageDiv.jsp"></jsp:include>
</body>
</html>