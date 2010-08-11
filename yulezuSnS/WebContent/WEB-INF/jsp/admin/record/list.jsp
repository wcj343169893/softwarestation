<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统日志</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
</head>
<body>
<div id="container" class="container_24">
<br>
  <!-- content --> 
  <div id="content" class="container_24">
    <div class="container_24">
      <div class="area2">
        <form id="listForm" action="${ctx}/admin/record/list" method="get">
          <input type="hidden" name="pageNo" id="pageNo" value="${pageData.pagination.pageNo}" />
          <input type="hidden" name="fieldName" id="fieldName" value="${pageData.compositor.fieldName}" />
          <input type="hidden" name="compositorType" id="compositorType" value="${pageData.compositor.compositorType}" />
          <table>
            <tr align="center">
            <!-- 
              <th>时间: <y:search fieldType="D" fieldList="insertTime" matchType="GE" />至
              <y:search fieldType="D" fieldList="insertTime" matchType="LE" />
                <input type="button" value="搜索" class="submit" onclick="search()"/>
              </th>
             -->
            </tr>
          </table>
        </form>
      </div>
      <div class="area">   
          <table  class="listtable">
            <tr class="line">
              <th><a href="javascript:sort('id')">序号</a></th>
              <th>管理员账户</th>
              <th><a href="javascript:sort('body')">内容</a></th>
              <th><a href="javascript:sort('insertTime')">操作时间</a></th>
            </tr>
            <c:forEach var="entity" items="${pageData.result}">
              <tr <c:if test="${!entity.visible}">bgcolor="#AFEEEE"</c:if>>
               	<td>${entity.id}</td>
               	<td>${entity.administator.account}</td>
                <td>
                	<c:set value="" var="body"></c:set>
	               <c:choose>
	               		<c:when test="${fn:length(entity.body)>30}">
	               			<c:set value="${fn:substring(entity.body,0,30)}..." var="body"></c:set>
	               		</c:when>
	               		<c:otherwise>
	               			<c:set value="${entity.body}" var="body"></c:set>
	               		</c:otherwise>
	               </c:choose>
               	 <c:out value="${body}" escapeXml="true" ></c:out>
                
                </td>
                <td>${entity.insertTime}</td>
              </tr>
            </c:forEach>
          </table>
                 每页${pageData.pagination.pageSize} 条,第${pageData.pagination.pageNo}页,共${pageData.pagination.totalPage}页 <a href="javascript:jumpPage(1)">首页</a>
          <c:if test="${pageData.pagination.hasPrevPage}"> <a href="javascript:jumpPage(${pageData.pagination.prevPage})">上一页</a> </c:if>
          <c:if test="${pageData.pagination.hasNextPage}"> <a href="javascript:jumpPage(${pageData.pagination.nextPage})">下一页</a> </c:if>
          <a href="javascript:jumpPage(${pageData.pagination.totalPage})">末页</a>
      </div>
    </div>
  </div>
</div>
</body>
</html>
