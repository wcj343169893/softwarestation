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
		$("#account").focus();
		//为inputForm注册validate函数
		$("#inputForm").validate({
			rules: {
				account:"required",
				password:{
					required:true,
					rangelength:[6,16]
				}
			}
		});
	});
	</script>
	<script type="text/javascript">
	function checks(classNames,elem){
		var myform=document.getElementById("inputForm");
		for(var i=0;i<myform.length;i++){
			var e=myform.elements[i];
			if(e.type=="checkbox" && e.className==classNames){
				if (elem.checked) {
					e.checked=true;
				} else {
					e.checked=false;
				}
			}	
		}
	}
	function checkMain(ids){
			var i=document.getElementById(ids);
			if(!i.checked){
				i.checked=true;
				}
		}
</script>
</head>
<body>
<div id="container" class="container_24">
<br>
  <!-- content -->
  <div id="content" class="container_24">
    <div class=" suffix_5 grid_14 prefix_5">
    	<div class="area">
    		<form id="inputForm" action="${ctx}/admin/administator/save" method="post">
		      <input type="hidden" name="id" value="${administator.id}" />
		      <table >
		      <tr>
		          <td>账号:</td>
		          <td><input id="account" name="account" type="text" value="<c:out value="${administator.account}" escapeXml="true"></c:out>" size="30" maxlength="80" /></td>
		        </tr>
		        <tr>
		          <td>密码:</td>
		          <td><input id="password" name="password" type="password" value="${administator.password}" size="30" maxlength="80" /></td>
		        </tr>
		        <tr>
		          <td>权限:</td>
		          <td>
				  	<c:forEach var="module" items="${moduleList }" varStatus="vs">
			        	<c:if test="${module.parent.id==1 }">
			        		<c:set value="false" var="ischecked"></c:set>
			        		<c:forEach items="${administator.modules}" var="am">
			        			<c:if test="${am.id==module.id}">
			        				<c:set value="true" var="ischecked"></c:set>
			        			</c:if>
			        		</c:forEach>
			        		<input type="checkbox" value="${module.id }" id="id_${vs.count }"  name="module" class="class_${vs.count }" onclick="checks('class_${vs.count }',this)" <c:if test="${ischecked }">checked</c:if> /><label for="id_${vs.count }"><strong>${module.title }:</strong></label>
			        		<c:forEach var="chmodule" items="${module.children}" varStatus="vs2">
				        		<c:set value="false" var="ischecked"></c:set>
				        		<c:forEach items="${administator.modules}" var="am">
				        			<c:if test="${am.id==chmodule.id}">
				        				<c:set value="true" var="ischecked"></c:set>
				        			</c:if>
				        		</c:forEach>
			        			<input type="checkbox" value="${chmodule.id }" id="id_${vs.count }_${vs2.count}" onclick="checkMain('id_${vs.count }')" class="class_${vs.count }" name="module" <c:if test="${ischecked }">checked</c:if>/><label for="id_${vs.count }_${vs2.count}">${chmodule.title }</label>
			        		</c:forEach>
			        		<br/>
			        	</c:if>
		        	</c:forEach>
		        	
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
