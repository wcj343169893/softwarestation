<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>学历列表</title>
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
        <form id="listForm" action="${ctx}/admin/users/educationSave" method="post">
          <input type="hidden" name="pageNo" id="pageNo" value="${pageData.pagination.pageNo}" />
          <input type="hidden" name="fieldName" id="fieldName" value="${pageData.compositor.fieldName}" />
          <input type="hidden" name="compositorType" id="compositorType" value="${pageData.compositor.compositorType}" />
          <table>
            <tr align="center">
              <th>
                 <a href="${ctx}/admin/users/list">用户列表</a>&gt;&gt;学历列表<br>
                                              学历添加：<input id="name" type="text" name="name" maxlength="10">
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
            <c:forEach var="entity" items="${educationList}">
              <tr>
              	
					<td>${entity.id}</td>
				
					<td>${entity.name}</td>
				
                <td>
                	<a href="${ctx}/admin/users/educationEdit/${entity.id}">修改</a>
 
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
