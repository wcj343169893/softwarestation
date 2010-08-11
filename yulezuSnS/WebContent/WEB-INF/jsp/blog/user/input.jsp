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
				name:"required",
				email:"required email",
				balance:"required number",
				age:"required digits",
				birthday:"required dateISO"
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
    		<form id="inputForm" action="${ctx}/blog/user/save" method="post">
		      <input type="hidden" name="id" value="${user.id}" />
		      <table >
		        <tr>
		          <td>名字:</td>
		          <td><input id="name" name="name" type="text" value="${user.name}" size="30" maxlength="80" /></td>
		        </tr>
		        <tr>
		          <td>邮箱:</td>
		          <td><input id="email" name="email" type="text" value="${user.email}" size="30" maxlength="80" /></td>
		        </tr>
		        <tr>
		          <td>余额:</td>
		          <td><input id="balance" name="balance" type="text" value="${user.balance}" size="30" maxlength="80" /></td>
		        </tr>
		        <tr>
		          <td>年龄:</td>
		          <td><input id="age" name="age" type="text" value="${user.age}" size="30" maxlength="80" /></td>
		        </tr>
		        <tr>
		          <td>生日:</td>
		          <td><input id="birthday" name="birthday" type="text" value="${user.birthday}" size="30" maxlength="80" /></td>
		        </tr>
		        <tr>
		          <td>组织:</td>
		          <td><select name="team.id">
		              <c:forEach var="team" items="${teamList}"> <option value="${team.id}" 
		                <c:if test="${user.team.id==team.id}">selected="selected"</c:if>
		                >${team.name }
		                </option>
		              </c:forEach>
		            </select></td>
		        </tr>
		         <tr>
		          <td>年级:</td>
		          <td><select name="grade.id">
		              <c:forEach var="grade" items="${gradeList}"> <option value="${grade.id}" 
		                <c:if test="${user.grade.id==grade.id}">selected="selected"</c:if>
		                >${grade.name }
		                </option>
		              </c:forEach>
		            </select></td>
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
