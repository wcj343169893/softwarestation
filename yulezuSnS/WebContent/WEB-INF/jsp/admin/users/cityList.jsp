<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>城市列表</title>
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
        <form id="listForm" action="${ctx}/admin/users/citySave" method="post">
          <table>
            <tr align="center">
              <th>
                <a href="${ctx}/admin/users/list">用户列表</a>&gt;&gt;城市列表<br>
                                          城市添加：<input id="name" type="text" name="name" maxlength="5">
                <input type="submit" value="提交">
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
            <c:forEach var="entity" items="${cityList}">
              <tr >
					<td>${entity.id}</td>
				
					<td>${entity.name}</td>
                <td>
                	<a href="${ctx}/admin/users/cityEdit/${entity.id}">修改</a> |
                	
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
