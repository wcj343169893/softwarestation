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
<title>软件列表</title>
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
<jsp:useBean id="now" class="java.util.Date" /> 
<div class="page_title">管理中心 &gt; 软件列表</div>
<div class="page_search" style="float: left; width: 800px;">
<form action="" method="post" id="myform">
<input type="hidden" name="showData" value="${showData }"/>软件分类  
<select name="softwareTypeId">
	<option value="0">全部</option>
	<c:forEach items="${softwareTypeList }" var="stl">
		<option value="${stl.id }" <c:if test="${softwareTypeId==stl.id}">selected</c:if>>${stl.name }</option>
	</c:forEach>
</select>  
软件开发商:<input name="producer" value="${producer }">
软件名称:<input type="text" name="name" value="${name }">更新日期
<input type="text" name="beginTime" onClick="WdatePicker()" value="${beginTime }" readonly="readonly" size="10">
到<input type="text" name="endTime" onClick="WdatePicker()" value="${endTime }" readonly="readonly" size="10">
<input type="button" onclick="mysearch(1)" value="查询">
</form>
</div>
<br/>
<c:set value="0" var="cs"></c:set>
<c:set value="0" var="ds"></c:set>
<c:set value="0" var="as"></c:set>
<c:set value="0" var="allmoney"></c:set>
<fmt:formatDate value="${yesterday }" pattern="yyyy-MM-dd" var="n"/>
<table class="data_list_table">
	<tr>
		<th>编号</th>
		<th>软件名称</th>
		<th>分类</th>
		<c:if test="${showData==0}">
			<th>软件数量</th>
			<th>开发商</th>
		</c:if>
		<th>点击</th>
		<th>下载</th>
		<th>激活</th>
		<th>单价(元)</th>
		
		<th>收入(元)</th>
		<th>下载/点击</th>
		<th>激活/下载</th>
		<th>激活/点击</th>
		<th>收入/点击</th>
		<th>置顶</th>
		<th>显示</th>
		<th>加精</th>
		<th>推荐</th>
		<th>方式</th>
		<th>时间</th>
		<th>操作</th>
	</tr>
	<c:choose>
		<c:when test="${pageResult.list eq null}">
			<tr >
				<td colspan="12" align="center">暂无数据</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="softwareInfo" items="${pageResult.list }">
				<tr>
					<td class="list_data_number">${softwareInfo.id }</td>
					<td class="list_data_text"><a href="softwareInfo!detail.action?id=${softwareInfo.id }">${fn:substring(softwareInfo.name,0,10) }</a></td>
					<td class="list_data_text">${softwareInfo.softwareType.name}</td>
					<c:if test="${showData==0}">
						<td class="list_data_text">${softwareInfo.number}</td>
						<td class="list_data_text">${fn:substring(softwareInfo.producer,0,8)}</td>
					</c:if>
					<c:set value="0" var="dowload"></c:set>
					<c:set value="0" var="click"></c:set>
					<!-- 下载数 -->
					<c:forEach items="${softwareInfo.downloadLogList }" var="downloadLog">
						<fmt:formatDate value="${downloadLog.downloadTime }" pattern="yyyy-MM-dd" var="dlt"/>
						<c:if test="${dlt==n }">
							<c:set value="${dowload+downloadLog.number}" var="dowload"></c:set>
						</c:if>
					</c:forEach>
					<!-- 点击数 -->
					<c:forEach items="${softwareInfo.clickLogList }" var="clickLog">
						<fmt:formatDate value="${clickLog.clickTime }" pattern="yyyy-MM-dd" var="dlt"/>
						<c:if test="${dlt==n }">
							<c:set value="${click+clickLog.number}" var="click"></c:set>
						</c:if>
					</c:forEach>
					
					<c:set value="${click+cs}" var="cs"></c:set>
					<c:set value="${dowload+ds}" var="ds"></c:set>
					<c:set value="0" var="clid"></c:set>
					<td class="list_data_text">${click}</td>
					<td class="list_data_text">${dowload}</td>
					<td class="list_data_text">
					<c:set value="0" var="stp"></c:set>
					<c:set value="0" var="alu"></c:set>
					<c:set value="录入" var="actTitle"></c:set>
					<c:set value="" var="atTime"></c:set>
					<!-- 激活数 -->
					<c:forEach items="${softwareInfo.activeLogList }" var="activeLog">
						<fmt:formatDate value="${activeLog.activeTime }" pattern="yyyy-MM-dd" var="at"/>
							<c:if test="${at==n }">
								<c:set value="${activeLog.number }" var="alu"></c:set>
								<c:set value="${activeLog.number+as }" var="as"></c:set>
								<c:set value="${activeLog.price }" var="stp"></c:set>
								<c:set value="${activeLog.id }" var="clid"></c:set>
								<c:set value="${activeLog.activeTime }" var="atTime"></c:set>
								<c:set value="编辑" var="actTitle"></c:set>
							</c:if>
					</c:forEach>
					${alu }
					</td>
					<td class="list_data_text"><span class="blue_test">${stp}</span></td>
					<c:set value="${stp*alu+allmoney}" var="allmoney"></c:set>
					<td><span class="red_test">${stp*alu }</span></td>
					<fmt:formatNumber value="${click != 0 ? (dowload/click) : 0 }" type="number" pattern="0.00%"  var="dc_"/>
					<td>${dc_ }</td>
					<fmt:formatNumber value="${dowload!=0 ? (alu/dowload) : 0 }" type="number" pattern="0.00%"  var="ad_"/>
					<td>${ad_ }</td>
					<fmt:formatNumber value="${click!=0 ? (alu/click) : 0 }" type="number" pattern="0.00%"  var="ac_"/>
					<td>${ac_ }</td>
					<fmt:formatNumber value="${click!=0 ? (stp*alu/click) : 0  }" type="number" pattern="0.00"  var="sa_"/>
					<td><span class="red_test">${sa_ }</span></td>
					<td class="list_data_number">${softwareInfo.tops==0?"<span class='red_test'>否</span>":"是"}</td>
					<td class="list_data_number">${softwareInfo.isShow==0?"<span class='red_test'>否</span>":"是"}</td>
					<td class="list_data_number">${softwareInfo.plusFine==0?"<span class='red_test'>否</span>":"是"}</td>
					<td class="list_data_number">${softwareInfo.recommend==0?"<span class='red_test'>否</span>":"是"}</td>
					<td class="list_data_number">${softwareInfo.promotionWay == 0?'提成':'免费'}</td>
					<td class="list_data_text">
						<fmt:formatDate value="${showData==0 ? softwareInfo.createTime : atTime }" pattern="yyyy-MM-dd" var="ct"/>
						${ct }
					</td>
					<td class="list_data_text">
					<c:choose>
						<c:when test="${showData==1}">
							<input type="button" onclick="to('activeLog!detail.action?sid=${softwareInfo.id }&id=${clid }')" value="${actTitle }">
							<input type="button" onclick="to('activeLog!list.action?sid=${softwareInfo.id }')" value="历史"/>
						</c:when>
						<c:otherwise>
							<input type="button" onclick="to('softwareInfo!detail.action?id=${softwareInfo.id }')" value="编辑">
						<input type="button" onclick="del('softwareInfo!delete.action?id=${softwareInfo.id }')" value="删除">
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</table>
	<div class="pager">
	点击:${cs } 下载:${ds } 激活:${as } 收入:${allmoney }
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