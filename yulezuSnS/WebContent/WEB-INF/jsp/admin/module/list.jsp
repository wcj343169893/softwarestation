<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>模块列表</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
</head>
<body>
<div id="container" class="container_24">
<br>
  <!-- content -->
  <div id="content" class="container_24">
    <div class="container_24">
      <div class="area2">
        <form id="listForm" action="${ctx}/admin/module/list" method="get">
          <input type="hidden" name="pageNo" id="pageNo" value="${pageData.pagination.pageNo}" />
          <input type="hidden" name="fieldName" id="fieldName" value="${pageData.compositor.fieldName}" />
          <input type="hidden" name="compositorType" id="compositorType" value="${pageData.compositor.compositorType}" />
          <table>
            <tr align="center">
              <th>标题: <y:search fieldType="S" fieldList="title" matchType="LIKE" />
                <input type="button" value="搜索" class="submit" onclick="search()"/>
                <a href="${ctx}/admin/module/new">添加新模块</a>
              </th>
            </tr>
          </table>
        </form>
      </div>
      <div class="area">   
          <table  class="listtable">
            <tr class="line">
              <th><a href="javascript:sort('id')">序号</a></th>
              <th><a href="javascript:sort('title')">标题</a></th>
               <th>父节点</th>
              <th><a href="javascript:sort('icon')">图标</a></th>
              <th><a href="javascript:sort('descn')">描述</a></th>
              <th><a href="javascript:sort('path')">链接</a></th>
              <th>操作</th>
            </tr>
            <c:forEach var="entity" items="${pageData.result}">
              <tr <c:if test="${!entity.visible}">bgcolor="#AFEEEE"</c:if>>
              	<td>${entity.id}</td>
                <td>${entity.title}</td>
                 <td>${entity.parent.title}</td>
                <td>${entity.icon}</td>
                <td>${entity.descn}</td>
                <td>${entity.path}</td>
                <td>
                	<a href="${ctx}/admin/module/edit/${entity.id}">修改</a> |
                	<a href="${ctx}/admin/module/delete/${entity.id}" onClick="return confirm('删除后无法恢复,确定要删除吗?');">刪除</a> |
                  <c:choose>
                    <c:when test="${entity.visible}"> 
                    	显示 |  <a href="${ctx}/admin/module/unVisible/${entity.id}">不显示</a>
                    </c:when>
                    <c:otherwise> 
                    	<a href="${ctx}/admin/module/visible/${entity.id}">显示</a> | 不显示 
                    </c:otherwise>
                  </c:choose>
                </td>
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
