<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户详细信息</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
<script>
	$(document).ready(function(){
		//聚焦第一个输入框
		$("#name").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				//sexy:"required",stature:"required",birthday:"required",weight:"required",qq:"required",email:"required",phone:"required",duty:"required",hobby:"required",blood:"required",cid:"required",constellation:"required",marry:"required",education:"required",sign:"required",eid:"required"	
				birthday:{
			     dateISO:"yyyy-MM-dd"
				}
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
    		<form id="inputForm" action="${ctx}/admin/users/userInfoSave" method="post">
		      <input type="hidden" name="id" value="${userinfo.id}" />
		      <input type="hidden" name="uid" value="${userinfo.users.id}" />
		      <table>
		        
		        <tr>
		          <td>性别:</td>
		          <td>
		          	<select name="sexy">
		          		<option value="1">男</option>
		          		<option value="0">女</option>
		          	</select>
		          </td>
		        </tr>
		        
		        <tr>
		          <td>身高:</td>
		          <td><input id="stature" name="stature" type="text" value="${userinfo.stature}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>生日:</td>
		          <td>
		          <fmt:formatDate value="${userinfo.birthday}" pattern="yyyy-MM-dd" var="brithday"/>
		          	<input id="birthday" name="birthday" type="text" value="${brithday}" size="30" maxlength="80" />
		          </td>
		        </tr>
		        
		        <tr>
		          <td>体重:</td>
		          <td><input id="weight" name="weight" type="text" value="${userinfo.weight}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>QQ:</td>
		          <td><input id="qq" name="qq" type="text" value="${userinfo.qq}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>email:</td>
		          <td><input id="email" name="email" type="text" value="${userinfo.email}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>电话:</td>
		          <td><input id="phone" name="phone" type="text" value="${userinfo.phone}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>职业:</td>
		          <td><input id="duty" name="duty" type="text" value="${userinfo.duty}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>爱好:</td>
		          <td><input id="hobby" name="hobby" type="text" value="${userinfo.hobby}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>血型:</td>
		          <td><input id="blood" name="blood" type="text" value="${userinfo.blood}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>城市:</td>
		          <td>
		          	<select name="city">
		          		<c:forEach items="${cityList}" var="city">
		          			<option value="${city.id }">${city.name }</option>
		          		</c:forEach>
		          	</select>
		          </td>
		        </tr>
		        
		        <tr>
		          <td>星座:</td>
		          <td><input id="constellation" name="constellation" type="text" value="${userinfo.constellation}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>婚恋:</td>
		          <td><input id="marry" name="marry" type="text" value="${userinfo.marry}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        
		        <tr>
		          <td>签名:</td>
		          <td><input id="sign" name="sign" type="text" value="${userinfo.sign}" size="30" maxlength="80" /></td>
		        </tr>
		        
		        <tr>
		          <td>学历:</td>
		          <td>
		          	<select name="education">
		          		<c:forEach items="${educationList}" var="education">
		          			<option value="${education.id }">${education.name }</option>
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
