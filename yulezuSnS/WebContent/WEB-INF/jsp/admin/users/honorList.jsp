<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>honor列表</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
<script>
	$(document).ready(function(){
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#listForm").validate({
			rules: {
				name:"required"	
			}
		});
	});
	</script>
</head>
<body>
<div id="container" class="container_24">
  
  <!-- content -->
  <div id="content" class="container_24">
    <div class="container_24">
      <div class="area2">
        <!-- 分页显示演示开始 -->
        <form id="listForm" action="${ctx}/admin/users/honorSave" method="post">
          <table>
            <tr align="center">
              <th>
              		<a href="${ctx}/admin/users/list">用户列表</a>&gt;&gt;头衔类型列表<br>
                	添加头衔类型:<input type="text" maxlength="10" name="name" >
                	<input type="submit" value="提交"><br>
                	<a href="${ctx}/admin/users/ranklevelList">头衔等级管理</a>
              </th>
            </tr>
          </table>
        </form>
      </div>
      <div class="area">   
          <table  class="listtable">
            <tr class="line">
			
				<th><a href="javascript:sort('id')">ID</a></th>
			
				<th><a href="javascript:sort('name')">名称</a></th>

              	<th>操作</th>
            </tr>
            <c:forEach var="entity" items="${honorList}">
              <tr >
              	
				
					<td>${entity.id}</td>
				
					<td><a href="${ctx}/admin/users/rankList/${entity.id}">${entity.name}</a></td>
				
                <td>
                	<a href="${ctx}/admin/users/honorEdit/${entity.id}">修改</a>
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
