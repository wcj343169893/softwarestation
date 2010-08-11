<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>好友列表</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
</head>
<body>
<div id="container" class="container_24">
<br>
  <!-- content -->
  <div id="content" class="container_24">
    <div class="container_24">
      <div class="area2">
	  <a href="/admin/users/list">用户列表</a> &gt;&gt; <a href="/admin/friends/glist/${uid}">分组</a> &gt;&gt; 组员
      </div>
      <div class="area">   
          <table  class="listtable">
            <tr class="line">
            	<th>编号</th>
				<th>昵称</th>
				<th>备注</th>
				<th>分组</th>
				<th>插入时间</th>
              	<th>修改时间</th>
            </tr>
            <c:forEach var="entity" items="${friendsList}" varStatus="vs">
              <tr <c:if test="${!entity.visible}">bgcolor="#AFEEEE"</c:if>>
              		<td>${vs.count}</td>
					<td>${entity.friend.nickname}</td>
					<td>${entity.nameTemp}</td>
					<td>${entity.friendgroup.groupName}</td>
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
