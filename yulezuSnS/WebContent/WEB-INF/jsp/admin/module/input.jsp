<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>年级表单</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
<script>
	$(document).ready(function(){
		//聚焦第一个输入框
		$("#title").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				title:"required",
				descn:"required",
				icon:"required"
			}
		});
	});
	</script>
</head>
<body>
<div id="container" class="container_24">
<br>
  <!-- content -->
  <div id="content" class="container_24">
    <div class=" suffix_5 grid_14 prefix_5">
    	<div class="area">
    		<form id="inputForm" action="${ctx}/admin/module/save" method="post">
		      <input type="hidden" name="id" value="${module.id}" />
		      <table >
		       <tr>
		          <td>标题:</td>
		          <td><input id="title" name="title" type="text" value="${module.title}" size="30" maxlength="80" /></td>
		        </tr>
		         <tr>
		          <td>图标:</td>
		          <td><input id="icon" name="icon" type="text" value="${module.icon}" size="30" maxlength="80" /></td>
		        </tr>
		        <tr>
		        	<td colspan="2">
		        		icon-sys,icon-nav,icon-add,icon-users,icon-role,icon-set,icon-log
		        	</td>
		         </tr>
		         <c:if test="${module.id!=1}">
			      <tr>
			          <td>父节点:</td>
			          <td>
			          	<select name="parent.id">
			          		<c:forEach items="${moduleList }" var="mo">
				          		<option value="${mo.id }" <c:if test="${module.parent.id==mo.id}">selected</c:if>>${mo.title }</option>
			          		</c:forEach>
			          		
			          	</select>
			          </td>
			        </tr>
		         </c:if>
		         <tr>
		          <td>描述:</td>
		          <td><input id="descn" name="descn" type="text" value="${module.descn}" size="30" maxlength="80" /></td>
		        </tr>
		        <tr>
		          <td>链接:</td>
		          <td><input id="path" name="path" type="text" value="${module.path}" size="30" maxlength="80" /></td>
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
