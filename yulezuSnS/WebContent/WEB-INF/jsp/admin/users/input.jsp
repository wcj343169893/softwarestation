<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户表单</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
<script>
	$(document).ready(function(){
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
			username:"required",nickname:"required",password:"required",scope:"required",integral:"required",charm:"required",money:"required",eash:"required",mobile:"required",hid:"required"	
			}
		});
	});
	</script>
</head>
<body>
<div id="container" class="container_24">
  
  <!-- content -->
  <div id="content" class="container_24">
    <div class=" suffix_5 grid_14 prefix_5">
    	<div class="area">
    		<form id="inputForm" action="${ctx}/admin/users/save" method="post">
		      <input type="hidden" name="id" value="${users.id}" />
		      <table>
		        
		        <tr>
		          <td>昵称:</td>
		          <td><input id="nickname" name="nickname" type="text" value="${users.nickname}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>用户注册名:</td>
		          <td><input id="username" name="username" type="text" value="${users.username}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>密码:</td>
		          <td><input id="password" name="password" type="text" value="${users.password}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>经验:</td>
		          <td><input id="scope" name="scope" type="text" value="${users.scope}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>积分:</td>
		          <td><input id="integral" name="integral" type="text" value="${users.integral}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>魅力:</td>
		          <td><input id="charm" name="charm" type="text" value="${users.charm}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>U币:</td>
		          <td><input id="money" name="money" type="text" value="${users.money}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>U金:</td>
		          <td><input id="eash" name="eash" type="text" value="${users.eash}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>手机号码:</td>
		          <td><input id="mobile" name="mobile" type="text" value="${users.mobile}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>头衔类型:</td>
		          <td>
		          	<select name="honor">
		          		<c:forEach items="${honorList }" var="honor">
		          			<option <c:if test="${!empty honor.id &&  users.honor.id==honor.id}">selected</c:if> value="${honor.id }">${honor.name}</option>
		          		</c:forEach>
		          	</select>
		          </td>
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
