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
<title>数账单</title>
<script type="text/javascript">
	function mysearch(p){
			var myform=	document.getElementById("myform");
			if(p==-1){
					p=document.getElementById("pageNo").value;
				}
			myform.action="bop!list.action?p="+p;
			myform.submit();
		}
</script>
</head>
<body>

<div class="page_title">管理中心 &gt; 数据管理 &gt; 账单</div>
<div class="page_search" style="float: left; width: 600px;">
<form action="" method="post" id="myform">日期
<input type="text" name="beginTime" onClick="WdatePicker()" value="${beginTime }" readonly="readonly">
到<input type="text" name="endTime" onClick="WdatePicker()" value="${endTime }" readonly="readonly">
<input type="button" onclick="mysearch(1)" value="查询">
</form>
</div>
<br/>
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>推广费用</th>
		<th>激活数量</th>
		<th>激活总价</th>
		<th>盈亏</th>
		<th>日期</th>
	</tr>
	<c:forEach items="${pageResult.list}" var="bop">
		<tr>
			<td>${bop.id }</td>
			<c:set value="0" var="ac_price"></c:set>
			<c:set value="0" var="al_price"></c:set>
			<c:set value="0" var="al_number"></c:set>
			<c:set value="0" var="ac_al"></c:set>
			<c:forEach items="${bop.accountList}" var="account">
				<c:set value="${ac_price+account.price}" var="ac_price"></c:set>
			</c:forEach>
			<c:forEach var="activelog" items="${bop.activeLogList}">
				<c:set value="${al_price+(activelog.number*activelog.price)}" var="al_price"></c:set>
				<c:set value="${al_number+activelog.number}" var="al_number"></c:set>
			</c:forEach>
			<c:set value="${al_price-ac_price }" var="ac_al"></c:set>
			<fmt:formatNumber value="${al_price }" type="number" pattern="0.00"  var="al_p_"/>
			<fmt:formatNumber value="${ac_price }" type="number" pattern="0.00"  var="ac_price_"/>
			<td>${ac_price_ }</td>
			<td>${al_number }</td>
			<td>${al_p_ }</td>
			<td>
				<c:choose>
					<c:when test="${ac_al <= 0}">
						<span class="red_test">
							<fmt:formatNumber value="${ac_al }" type="number" pattern="0.00"  var="ac_al_"/>
							${ac_al_ }
						</span>
					</c:when>
					<c:otherwise>
						<span class="red_test">
							<fmt:formatNumber value="${ac_al }" type="number" pattern="0.00"  var="ac_al_"/>
							${ac_al_ }
						</span>
					</c:otherwise>
				</c:choose>
			</td>
			<fmt:formatDate pattern="yyyy-MM-dd" value="${bop.createtime }" var="ct"></fmt:formatDate>
			<td>${ct }</td>
		</tr>
	</c:forEach>
</table>
<jsp:include page="/admin/common/pageDiv.jsp"></jsp:include>
</body>
</html>