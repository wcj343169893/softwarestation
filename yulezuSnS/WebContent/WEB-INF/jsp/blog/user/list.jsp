<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户列表</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
</head>
<body>
<div id="container" class="container_24">
  <!-- header -->
  <%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
  
  <!-- content -->
  <div id="content" class="container_24">
    <div class="container_24">
      <div class="area2">
        <!-- 分页显示演示开始 -->
        <form id="listForm" action="${ctx}/blog/user/list" method="get">
          <input type="hidden" name="pageNo" id="pageNo" value="${pageData.pagination.pageNo}" />
          <input type="hidden" name="fieldName" id="fieldName" value="${pageData.compositor.fieldName}" />
          <input type="hidden" name="compositorType" id="compositorType" value="${pageData.compositor.compositorType}" />
          <table>
            <tr align="center">
              <th>
              	姓名或Email: <y:search fieldType="S" fieldList="name,email" matchType="LIKE" />
              	年龄：<y:search fieldType="I" fieldList="age" matchType="EQ"/>
                <input type="button" value="搜索" class="submit" onclick="search()"/>
                <a href="${ctx}/blog/user/new">添加新用户</a>
              </th>
            </tr>
          </table>
        </form>
      </div>
      <div class="area">   
          <table  class="listtable">
            <tr class="line">
              <th>序号</th>
              <th><a href="javascript:sort('name')">名字</a></th>
              <th>电子邮箱</th>
              <th>余额</th>
              <th><a href="javascript:sort('age')">年龄</a></th>
              <th>生日</th>
              <th>组织</th>
              <th>年级</th>
              <th>插入时间</th>
              <th>操作</th>
            </tr>
            <c:forEach var="entity" items="${pageData.result}">
              <tr <c:if test="${!entity.visible}">bgcolor="#AFEEEE"</c:if>>
              <td>${entity.id}</td>
                <td>${entity.name}</td>
                <td>${entity.email}</td>
                <td>${entity.balance}</td>
                <td>${entity.age}</td>
                <td>${entity.birthday}</td>
                <td>${entity.team.name}</td>
                <td>${entity.grade.name}</td>
                <td>${entity.insertTime}</td>
                <td>
                	<a href="${ctx}/blog/user/edit/${entity.id}">修改</a> |
                	<a href="${ctx}/blog/user/delete/${entity.id}" onClick="return confirm('删除后无法恢复,确定要删除吗?');">刪除</a> |
                  <c:choose>
                    <c:when test="${entity.visible}"> 
                    	显示 |  <a href="${ctx}/blog/user/unVisible/${entity.id}">不显示</a>
                    </c:when>
                    <c:otherwise> 
                    	<a href="${ctx}/blog/user/visible/${entity.id}">显示</a> | 不显示 
                    </c:otherwise>
                  </c:choose>
                </td>
              </tr>
            </c:forEach>
          </table>
                           第${pageData.pagination.pageNo}页,共${pageData.pagination.totalPage}页 <a href="javascript:jumpPage(1)">首页</a>
          <c:if test="${pageData.pagination.hasPrevPage}"> <a href="javascript:jumpPage(${pageData.pagination.prevPage})">上一页</a> </c:if>
          <c:if test="${pageData.pagination.hasNextPage}"> <a href="javascript:jumpPage(${pageData.pagination.nextPage})">下一页</a> </c:if>
          <a href="javascript:jumpPage(${pageData.pagination.totalPage})">末页</a>
        
        <!-- 分页显示演示结束 -->
      </div>
    </div>
  </div>
  
  <!-- footer -->
  <%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
  
</div>
</body>
</html>
