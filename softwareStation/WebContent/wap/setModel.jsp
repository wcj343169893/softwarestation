<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="设置我的手机">
<p>
<a href="setmodel.php">设置机型</a> &gt; &gt;
<c:choose>
	<c:when test="${keyword eq null}">${brand.name }</c:when>
	<c:otherwise>搜索结果</c:otherwise>
</c:choose> <br/>
<c:forEach items="${pageResult.list}" var="module" varStatus="vs">
	${vs.count }.<a href="index.php?mid=${module.id }"><c:choose><c:when test="${keyword eq null}"></c:when><c:otherwise>${module.phonebrand.name }</c:otherwise></c:choose>${module.name }</a>
</c:forEach>
<c:if test="${fn:length(pageResult.list)==0}">没有可选择的机型</c:if>
<br />
</p>
</card>
</wml>