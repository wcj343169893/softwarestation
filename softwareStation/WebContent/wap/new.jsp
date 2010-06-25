<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="最新">
<p>
<c:choose>
	<c:when test="${mid>0 }">
	我的机型 -${mid }<a href="setmodel.php?mid=${mid }">更改机型</a> <br />
	</c:when>
	<c:otherwise>
	未设置机型<a href="setmodel.php">设置机型</a> <br />
	</c:otherwise>
</c:choose>
<img src="/img/logo.png" alt="软件站" /><br />
最新|热门|精品|分类|资讯<br />
<c:set value="" var="plusFine"></c:set>
<c:forEach items="${indexDTO.software_plusFineList}" var="softwareInfo">
<a href="#">${softwareInfo.name}</a>
<c:set value="${softwareInfo.name}|${plusFine}" var="plusFine"></c:set>
<c:choose>
	<c:when test="${fn:length(plusFine) > 10}">
		<br/>
		<c:set value="" var="plusFine"></c:set>
	</c:when>
	<c:otherwise>|</c:otherwise>
</c:choose>
</c:forEach><br />
【今日推荐】<br />
<c:set value="" var="recommend"></c:set>
<c:forEach items="${indexDTO.software_recommendList}" var="softwareInfo">
<a href="#">${softwareInfo.name}</a>
<c:set value="${softwareInfo.name}|${recommend}" var="recommend"></c:set>
<c:choose>
	<c:when test="${fn:length(recommend) > 10}">
		<br/>
		<c:set value="" var="recommend"></c:set>
	</c:when>
	<c:otherwise>|</c:otherwise>
</c:choose>
</c:forEach><br />
【人气】<br />
<c:set value="" var="hots"></c:set>
<c:forEach items="${indexDTO.software_hotList}" var="softwareInfo">
<a href="#">${softwareInfo.name}</a>
<c:set value="${softwareInfo.name}|${hots}" var="hots"></c:set>
<c:choose>
	<c:when test="${fn:length(hots) > 10}">
		<br/>
		<c:set value="" var="hots"></c:set>
	</c:when>
	<c:otherwise>|</c:otherwise>
</c:choose>
</c:forEach><br />
【最新更新】<br />
<c:set value="" var="newone"></c:set>
<c:forEach items="${indexDTO.software_newList}" var="softwareInfo">
<a href="#">${softwareInfo.name}</a>
<c:set value="${softwareInfo.name}|${newone}" var="newone"></c:set>
<c:choose>
	<c:when test="${fn:length(newone) > 10}">
		<br/>
		<c:set value="" var="newone"></c:set>
	</c:when>
	<c:otherwise>|</c:otherwise>
</c:choose>
</c:forEach><br />
【软件分类】<br />
<c:forEach items="${indexDTO.softwareTypeList}" var="softwareType"><a href="#">${softwareType.name }</a>
<c:choose>
	<c:when test="${softwareType.isWrap==1}"><br/></c:when>
	<c:otherwise>|</c:otherwise>
</c:choose>
</c:forEach><br />
</p>
</card>
</wml>