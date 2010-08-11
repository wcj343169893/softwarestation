<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>消息列表</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
</head>
<body>
<div id="container" class="container_24">
<br>
  <!-- content -->
  <div id="content" class="container_24">
    <div class="container_24">
      <div class="area2">
        <form id="listForm" action="${ctx}/admin/message/list" method="get">
          <input type="hidden" name="pageNo" id="pageNo" value="${pageData.pagination.pageNo}" />
          <input type="hidden" name="fieldName" id="fieldName" value="${pageData.compositor.fieldName}" />
          <input type="hidden" name="compositorType" id="compositorType" value="${pageData.compositor.compositorType}" />
          <table>
            <tr align="center">
              <th>标题: <y:search fieldType="S" fieldList="title" matchType="LIKE" />
                <input type="button" value="搜索" class="submit" onclick="search()"/>
              </th>
            </tr>
          </table>
        </form>
      </div>
      <div class="area">   
          <table  class="listtable">
            <tr class="line">
              <th><a href="javascript:sort('id')">序号</a></th>
              <th>FROM</th>
              <th>TO</th>
              <th><a href="javascript:sort('title')">标题</a></th>
              <th><a href="javascript:sort('content')">内容</a></th>
              <th><a href="javascript:sort('readed')">阅读状态</a></th>
              <th><a href="javascript:sort('type')">消息类型</a></th>
              <th><a href="javascript:sort('priority')">是否优先阅读</a></th>
              <th>操作</th>
            </tr>
            <c:forEach var="entity" items="${pageData.result}">
              <tr <c:if test="${!entity.visible}">bgcolor="#AFEEEE"</c:if>>
              	<td>${entity.id}</td>
              	<td>${entity.fromUser.nickname}</td>
              	<td>${entity.toUser.nickname}</td>
                <td>${entity.title}</td>
                <td>
                	<c:set value="${entity.content}" var="content"></c:set>
               		<c:choose>
                		<c:when test="${fn:length(entity.content)>10}">
                			<c:set value="${fn:substring(entity.content,0,10)}..." var="content"></c:set>
                		</c:when>
               		</c:choose>
                	${content }
                </td>
                <td>${entity.readed}</td>
                <td>${entity.type}</td>
                <td>${entity.priority}</td>
                <td>
                	<a href="${ctx}/admin/message/edit/${entity.id}">查看</a> |
                	<a href="${ctx}/admin/message/delete/${entity.id}" onClick="return confirm('删除后无法恢复,确定要删除吗?');">刪除</a> |
                  <c:choose>
                    <c:when test="${entity.visible}"> 
                    	<a href="${ctx}/admin/message/unVisible/${entity.id}">不显示</a>
                    </c:when>
                    <c:otherwise> 
                    	<a href="${ctx}/admin/message/visible/${entity.id}">显示</a> 
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </c:forEach>
          </table>
                 每页${pageData.pagination.pageSize} 条,共${pageData.pagination.totalCount}  条,第${pageData.pagination.pageNo}页,共${pageData.pagination.totalPage}页 <a href="javascript:jumpPage(1)">首页</a>
          <c:if test="${pageData.pagination.hasPrevPage}"> <a href="javascript:jumpPage(${pageData.pagination.prevPage})">上一页</a> </c:if>
          <c:if test="${pageData.pagination.hasNextPage}"> <a href="javascript:jumpPage(${pageData.pagination.nextPage})">下一页</a> </c:if>
          <a href="javascript:jumpPage(${pageData.pagination.totalPage})">末页</a>
      </div>
    </div>
  </div>
</div>
</body>
</html>
