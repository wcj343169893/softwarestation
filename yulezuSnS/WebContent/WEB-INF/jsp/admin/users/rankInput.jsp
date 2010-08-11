<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>honor表单</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
<script>
	$(document).ready(function(){
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
						name:"required",grade:"required"
			}
		});
	});
	</script>
</head>
<body>
<div id="container" class="container_30">


  <!-- content -->
  <div id="content" class="container_30">
    <div class=" suffix_5 grid_14 prefix_5">
    	<div class="area">
    		<form id="inputForm" action="${ctx}/admin/users/rankSave" method="post" enctype="multipart/form-data">
		      <input type="hidden" name="id" value="${rank.id}" />
		      <input type="hidden" name="honor" value="${honor.id}" />
		      <font color="red">${message}</font>
		      <table>

		        <tr>
		          <td>头衔分类:</td>
		          <td>${honor.name}</td>
		        </tr>
	        
		        <tr>
		          <td>头衔名称:</td>
		          <td><input id="name" name="name" type="text" value="${rank.name}" size="30" maxlength="10" /></td>
		        </tr>
		        
		        <tr>
		          <td>头衔等级:</td>
		          <td>
		          	<select name="ranklevel">
		          		<c:forEach items="${ranklevelList}" var="ranklevelList">
		          			<option <c:if test="${rank.ranklevel.level == ranklevelList.level }">selected</c:if> value="${ranklevelList.level }">${ranklevelList.level}</option>
		          		</c:forEach>
		          	</select>
		          </td>
		        </tr>		        
		        
		        <tr>
		          <td>图片:</td>
		          <td><input id="img" name="img" type="file"/><font color="red">(注：修改时，此项不填表示不修改此项!)</font></td>
		        </tr>			        
		        
		        <tr>
		          <td colspan="2"><input class="submit" type="submit" value="提交" />
		            &nbsp;
		            <input class="submit" type="button" value="返回" onClick="history.back()" /></td>
		        </tr>
		      </table>
		    </form>
    	</div>
    	
    </div>
  </div>  
  
</div>
</body>
</html>
