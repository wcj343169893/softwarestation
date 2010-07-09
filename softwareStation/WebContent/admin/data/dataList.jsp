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
<title>数据分析</title>
<script type="text/javascript">
	function mysearch(p){
			var myform=	document.getElementById("myform");
			if(p==-1){
					p=document.getElementById("pageNo").value;
				}
			myform.action="softwareInfo!list.action?p="+p;
			myform.submit();
		}
</script>
</head>
<body>

<div class="page_title">管理中心 &gt; 数据管理 &gt; 收支</div>
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
		<th>dd</th>
	</tr>
	<tr>
		<td>ss</td>
	</tr>
</table>
<jsp:include page="/admin/common/pageDiv.jsp"></jsp:include>
</body>
</html>