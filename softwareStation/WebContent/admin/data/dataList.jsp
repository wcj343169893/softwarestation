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

<div class="page_title">管理中心 &gt; 数据管理 &gt; 数据分析</div>
<div class="page_search" style="float: left; width: 600px;">
<form action="" method="post" id="myform">
软件名称:<input type="text" name="name">更新日期
<input type="text" name="beginTime" onClick="WdatePicker()" value="${beginTime }" readonly="readonly">
到<input type="text" name="endTime" onClick="WdatePicker()" value="${endTime }" readonly="readonly">
<input type="button" onclick="mysearch(1)" value="查询">
</form>
</div>
<div class="button_bar" style="float: right;">
查询的总数（点击，下载，激活，收入）
</div>
<br/>
<c:set value="0" var="cs"></c:set>
<c:set value="0" var="ds"></c:set>
<c:set value="0" var="as"></c:set>
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>软件名称</th>
		<th>分类</th>
		<th>软件数量</th>
		<th>点击</th>
		<th>下载</th>
		<th>激活</th>
		<th>显示</th>
		<th>加精</th>
		<th>推荐</th>
		<th>方式</th>
		<th>创建时间</th>
		<th>操作</th>
	</tr>
	<c:choose>
		<c:when test="${pageResult.list eq null}">
			<tr >
				<td colspan="13" align="center">暂无数据</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="softwareInfo" items="${pageResult.list }">
				<tr>
					<td class="list_data_number">${softwareInfo.id }</td>
					<td class="list_data_text"><a href="softwareInfo!detail.action?id=${softwareInfo.id }">${softwareInfo.name }</a></td>
					<td class="list_data_text">${softwareInfo.softwareType.name}</td>
					<td class="list_data_text">${softwareInfo.number}</td>
					<c:set value="0" var="dowload"></c:set>
					<c:set value="${softwareInfo.click}" var="click"></c:set>
					<c:forEach items="${softwareInfo.softwareList }" var="software">
						<c:set value="${dowload+software.download}" var="dowload"></c:set>
						<c:set value="${click+software.click}" var="click"></c:set>
					</c:forEach>
					<td class="list_data_text">${click}</td>
					<c:set value="${click+cs}" var="cs"></c:set>
					<td class="list_data_text">${dowload}</td>
					<c:set value="${dowload+ds}" var="ds"></c:set>
					<td class="list_data_text">激活</td>
					<td class="list_data_number">${softwareInfo.isShow==0?"<span class='red_test'>否</span>":"是"}
					</td>
					<td class="list_data_number">${softwareInfo.plusFine==0?"<span class='red_test'>否</span>":"是"}</td>
					<td class="list_data_number">${softwareInfo.recommend==0?"<span class='red_test'>否</span>":"是"}</td>
					<td class="list_data_number">${softwareInfo.promotionWay==0?"提成":"免费"}</td>
					<td class="list_data_text">
						<fmt:formatDate value="${softwareInfo.createTime }" pattern="yyyy-MM-dd" var="ct"/>
						${ct }
					</td>
					<td class="list_data_text">
						<input type="button" onclick="to('softwareInfo!detail.action?id=${softwareInfo.id }')" value="编辑">
						<input type="button" onclick="del('softwareInfo!delete.action?id=${softwareInfo.id }')" value="删除">
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
	<div class="pager">
	查询的每页的（点击:${cs }，下载:${ds }，激活，收入，转换率3个）
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