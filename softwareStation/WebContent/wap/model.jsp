<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="提交机型">
<p>
<jsp:include page="notices.jsp"></jsp:include>
您的手机品牌(必填):<br />
<input name="brand" /><br />
您的手机型号(必填):<br />
<input name="model" /><br />
<anchor>
	<go href="addreportModel.php" method="post">
		<postfield name="model" value="$(model)" />
		<postfield name="brand" value="$(brand)" />
		<postfield name="mid" value="${mid }" />
	</go>提交<br />
</anchor>
<a href="index.php?mid=${mid }">首页</a>&gt;提交机型<br/>
<jsp:include page="detail.jsp"></jsp:include>
</p>
</card>
</wml>