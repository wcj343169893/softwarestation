<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>friendgroup表单</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
<script>
	$(document).ready(function(){
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				serialVersionUID:"required",users:"required",groupName:"required",friendsList:"required"	
			}
		});
	});
	</script>
</head>
<body>
<div id="container" class="container_24">
  <!-- header -->
  <div id="header">
    <%@ include file="/WEB-INF/jsp/commons/header.jsp"%>
  </div>
  
  <!-- content -->
  <div id="content" class="container_24">
    <div class=" suffix_5 grid_14 prefix_5">
    	<div class="area">
    		<form id="inputForm" action="${ctx}/sns/friendgroup/save" method="post">
		      <input type="hidden" name="id" value="${friendgroup.id}" />
		      <table>
		      	
		        <tr>
		          <td>serialVersionUID:</td>
		          <td><input id="serialVersionUID" name="serialVersionUID" type="text" value="${friendgroup.serialVersionUID}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>users:</td>
		          <td><input id="users" name="users" type="text" value="${friendgroup.users}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>groupName:</td>
		          <td><input id="groupName" name="groupName" type="text" value="${friendgroup.groupName}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>friendsList:</td>
		          <td><input id="friendsList" name="friendsList" type="text" value="${friendgroup.friendsList}" size="30" maxlength="80" /></td>
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
  
  <!-- footer -->
  <div id="footer">
    <%@ include file="/WEB-INF/jsp/commons/footer.jsp"%>
  </div>
</div>
</body>
</html>
