<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="软件">
<p>
【${model.name }软件分类】<br />
<c:forEach items="${softwareTypeList}" var="softwareType"><a href="showst.php?mid=${mid}&amp;id=${softwareType.id}">${softwareType.name }</a>
<c:choose>
	<c:when test="${softwareType.isWrap==1}"><br/></c:when>
	<c:otherwise>|</c:otherwise>
</c:choose>
</c:forEach>
<br />---------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;软件分类<br/>
</p>
</card>
</wml>