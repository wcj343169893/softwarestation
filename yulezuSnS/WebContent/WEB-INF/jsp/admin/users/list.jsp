<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>users列表</title> 
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
</head>
<body>
<div id="container" class="container_30">
  
  <!-- content -->
  <div id="content" class="container_30">
    <div class="container_30">
      <div class="area2">
        <!-- 分页显示演示开始 -->
        <form id="listForm" action="${ctx}/admin/users/list" method="get">
          <input type="hidden" name="pageNo" id="pageNo" value="${pageData.pagination.pageNo}" />
          <input type="hidden" name="fieldName" id="fieldName" value="${pageData.compositor.fieldName}" />
          <input type="hidden" name="compositorType" id="compositorType" value="${pageData.compositor.compositorType}" />
          <table>
            <tr align="center">
              <th>id: <y:search fieldType="I" fieldList="id" matchType="EQ" />
                <input type="button" value="搜索" class="submit" onclick="search()"/>
                <a href="${ctx}/admin/users/new">添加新用户</a>|
                <a href="${ctx}/admin/users/cityList">城市管理</a>|
                <a href="${ctx}/admin/users/educationList">学历管理</a>|
                <a href="${ctx}/admin/users/honorList">头衔管理</a>
              </th>
            </tr>
          </table>
        </form>
      </div>
      <div class="area">   
          <table  class="listtable">
            <tr class="line">
				
			
				<th><a href="javascript:sort('id')">id</a></th>
			
			 	<th><a href="javascript:sort('username')">用户注册名</a></th>
			 	
				<th><a href="javascript:sort('nickname')">昵称</a></th>
			
				<th><a href="javascript:sort('loginTime')">登录日期</a></th>
			
				<th><a href="javascript:sort('mobile')">手机号码</a></th>
			
			
			
				<th>注册时间</th>
              	<th>操作</th>
            </tr>
            <c:forEach var="entity" items="${pageData.result}">
              <tr <c:if test="${!entity.visible}">bgcolor="#AFEEEE"</c:if>>
              	
					<td>${entity.id}</td>
					
					<td>${entity.username}</td>
					
					<td>${entity.nickname}</td>

					<td>${entity.loginTime}</td>
				
					<td>${entity.mobile}</td>
				
				
					<td>${entity.insertTime}</td>
                <td>
                	<a href="${ctx}/admin/users/edit/${entity.id}">修改</a> |
                	<a href="${ctx}/admin/users/userInfo/${entity.id}">详细信息</a> |
                  <c:choose>
                    <c:when test="${entity.visible}"> 
                    	有效 |  <a href="${ctx}/admin/users/unVisible/${entity.id}">无效</a>
                    </c:when>
                    <c:otherwise> 
                    	<a href="${ctx}/admin/users/visible/${entity.id}">有效</a> | 无效 
                    </c:otherwise>
                  </c:choose>
                  | <a href="/admin/friends/glist/${entity.id}">好友</a>
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
  
</div>
</body>
</html>
