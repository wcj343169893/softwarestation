<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>honor列表</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>

</head>
<body>
<div id="container" class="container_24">
  
  <!-- content -->
  <div id="content" class="container_24">
    <div class="container_24">
      <div class="area2">
        <!-- 分页显示演示开始 -->
        <form id="listForm" action="${ctx}/admin/users/ranklevelSave" method="post">
          <table>
            <tr align="center">
              <th>
              		<a href="${ctx}/admin/users/list">用户列表</a>&gt;&gt;头衔等级列表<br>
                	<a href="${ctx}/admin/users/ranklevelInput">添加头衔等级</a>
              </th>
            </tr>
          </table>
        </form>
      </div>
      <div class="area">   
          <table  class="listtable">
            <tr class="line">
			
				<th>等级</th>
				
				<th>经验值</th>
				
              	<th>操作</th>
            </tr>
            <c:forEach var="entity" items="${ranklevelList}">
              <tr>
				
					<td>${entity.level}</td>
					
					<td>${entity.rankvalue}</td>
				
                <td>
                	<a href="${ctx}/admin/users/ranklevelEdit/${entity.level}">修改</a>|
                	<a href="${ctx}/admin/users/ranklevelDelete/${entity.level}" onClick="return confirm('删除后无法恢复,确定要删除吗?');">刪除</a>
                </td>
              </tr>
            </c:forEach>
          </table>
        
        <!-- 分页显示演示结束 -->
      </div>
    </div>
  </div>
  
</div>
</body>
</html>
