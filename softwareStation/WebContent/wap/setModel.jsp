<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="设置我的手机">
<p>
<a href="setmodel.php">设置机型</a> &gt; &gt;  ${brand.name }<br/>
<c:forEach items="${pageResult.list}" var="module">
	<a href="index.jsp?mid=${module.id }">${module.name }</a>
</c:forEach>
<c:if test="${fn:length(pageResult.list)==0}">没有可选择的机型</c:if>
<br />
</p>
</card>
</wml>