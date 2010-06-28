<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="软件站">
<p>
<c:choose>
	<c:when test="${fn:length(indexDTO.model.phoneseries.brand.name)>0 }">
	我的机型 -${indexDTO.model.phoneseries.brand.name}${indexDTO.model.name}<a href="setmodel.php?mid=${mid }">更改机型</a> <br />
	</c:when>
	<c:otherwise>
	<a href="setmodel.php">推荐!设置手机型号快速下载</a> <br />
	</c:otherwise>
</c:choose>
<img src="/img/logo.png" alt="软件站" /><br />
<a href="new.jsp">最新</a>|<a href="commend.jsp">推荐</a>|热门|<a href="plusFine.jsp">精品</a>|分类|资讯<br />
<c:set value="" var="plusFine"></c:set>
<c:forEach items="${indexDTO.software_plusFineList}" var="softwareInfo">
	<c:set value="0" var="isWrap"></c:set>
	<c:set value="${plusFine}|${softwareInfo.name}" var="plusFine"></c:set>
	<c:choose>
		<c:when test="${fn:length(plusFine) > 13}">
			<c:set value="1" var="isWrap"></c:set>
			<c:set value="" var="plusFine"></c:set>
		</c:when>
		<c:otherwise><c:set value="0" var="isWrap"></c:set></c:otherwise>
	</c:choose>
	<a href="showsoftwareInfo.php?id=${softwareInfo.id}&amp;mid=${mid }">${softwareInfo.name}</a>${isWrap ==0?"|":"<br/>" }
</c:forEach>
【今日推荐】<br />
<c:set value="" var="recommend"></c:set>
<c:set value="${fn:length(indexDTO.software_recommendList)}" var="maxlength"></c:set>
<c:forEach items="${indexDTO.software_recommendList}" var="softwareInfo" varStatus="vs">
<c:set value="${softwareInfo.name}|${recommend}" var="recommend"></c:set>
<a href="showsoftwareInfo.php?id=${softwareInfo.id}&amp;mid=${mid }">${softwareInfo.name}</a>
<c:choose>
	<c:when test="${fn:length(recommend) > 10}">
		<br/>
		<c:set value="" var="recommend"></c:set>
	</c:when>
	<c:when test="${maxlength==vs.count }">
		<br/>
		<c:set value="" var="recommend"></c:set>
	</c:when>
	<c:otherwise>|</c:otherwise>
</c:choose>
</c:forEach>
<!-- 
<c:set value="" var="hots"></c:set>
<c:forEach items="${indexDTO.software_hotList}" var="softwareInfo">
<a href="showsoftwareInfo.php?id=${softwareInfo.id}&amp;mid=${mid }">${softwareInfo.name}</a>
<c:set value="${softwareInfo.name}|${hots}" var="hots"></c:set>
<c:choose>
	<c:when test="${fn:length(hots) > 10}">
		<br/>
		<c:set value="" var="hots"></c:set>
	</c:when>
	<c:otherwise>|</c:otherwise>
</c:choose>
</c:forEach><br />
 -->
【最新更新】<br />
<c:set value="" var="newone"></c:set>
<c:set value="${fn:length(indexDTO.software_newList)}" var="maxlength"></c:set>
<c:forEach items="${indexDTO.software_newList}" var="softwareInfo" varStatus="vs">
<a href="showsoftwareInfo.php?id=${softwareInfo.id}&amp;mid=${mid }">${softwareInfo.name}</a>
<c:set value="${softwareInfo.name}|${newone}" var="newone"></c:set>
<c:choose>
	<c:when test="${fn:length(newone) > 10}">
		<br/>
		<c:set value="" var="newone"></c:set>
	</c:when>
	<c:when test="${maxlength==vs.count }">
		<br/>
		<c:set value="" var="newone"></c:set>
	</c:when>
	<c:otherwise>|</c:otherwise>
</c:choose>
</c:forEach>
【软件分类】<br />
<c:forEach items="${indexDTO.softwareTypeList}" var="softwareType"><a href="#">${softwareType.name }</a>
<c:choose>
	<c:when test="${softwareType.isWrap==1}"><br/></c:when>
	<c:otherwise>|</c:otherwise>
</c:choose>
</c:forEach>
</p>
</card>
</wml>