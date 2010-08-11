<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>消息</title>
<%@ include file="/WEB-INF/jsp/commons/meta.jsp"%>
</head>
<body>
<div id="container" class="container_24">
<br>
  <!-- content -->
  <div id="content" class="container_24">
    <div class=" suffix_5 grid_14 prefix_5">
    	<div class="area">
    		<form id="inputForm" action="${ctx}/admin/message/save" method="post">
		      <input type="hidden" name="id" value="${message.id}" />
		      <table >
		       <tr>
		          <td>标题:</td>
		          <td>${message.title}</td>
		        </tr>
		        <tr>
		          <td>FROM:</td>
		          <td>${message.fromUser.nickname}</td>
		        </tr>
		        <tr>
		          <td>TO:</td>
		          <td>${message.toUser.nickname}</td>
		        </tr>
		        <tr>
		          <td>阅读状态:</td>
		          <td>${message.readed}</td>
		        </tr>
		         <tr>
		          <td>内容:</td>
		          <td><textarea rows="10" cols="60" id="content" name="content">${message.content}</textarea>
		          </td>
		        </tr>
		        <tr>
		          <td colspan="2">
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
