<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>好友分组列表</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
</head>
<body>
<div id="container" class="container_24">
<br>
  <!-- content -->
  <div id="content" class="container_24">
    <div class="container_24">
      <div class="area2">
        <a href="/admin/users/list">用户列表</a> &gt;&gt; 分组
      </div>
      <div class="area">   
          <table  class="listtable">
            <tr class="line">
            	<th>编号</th>
				<th>分组名称</th>
				<th>插入时间</th>
              	<th>修改时间</th>
            </tr>
            <c:forEach var="entity" items="${friendgroupList}" varStatus="vs">
              <tr <c:if test="${!entity.visible}">bgcolor="#AFEEEE"</c:if>>
              		<td>${vs.count}</td>
					<td><a href="/admin/friends/flist/${uid }/${entity.id}">${entity.groupName}(${fn:length(entity.friendsList) })</a></td>
					<td>${entity.insertTime}</td>
                	<td>${entity.lastUpdateTime}</td>
              </tr>
            </c:forEach>
          </table>
      </div>
    </div>
  </div>
</div>
</body>
</html>
