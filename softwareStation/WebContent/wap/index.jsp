<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="手机软件 - 361软件站">
<p>
<jsp:include page="notices.jsp"></jsp:include>
<c:if test="${indexDTO eq null}">
<%
	response.sendRedirect("/wap/index.php");
%>
</c:if>
<c:choose>
	<c:when test="${fn:length(indexDTO.model.phoneseries.brand.name)>0 }">
	机型 -${indexDTO.model.phoneseries.brand.name}${indexDTO.model.name}<a href="setmodel.php?mid=${mid }&amp;from=/index.php">[更改]</a> <br />
	</c:when>
	<c:otherwise>
	<a href="setmodel.php?from=/index.php">推荐!设置手机型号快速下载</a> <br />
	</c:otherwise>
</c:choose>
<img src="/img/logo.gif" alt="361软件站" /><br />
网址:361rj.com<br />
<a href="newsindex.php?mid=${mid }">最新</a>|<a href="commendindex.php?mid=${mid }">推荐</a>|<a href="rankindex.php?mid=${mid }">排行</a>|<a href="lsst.php?mid=${mid }">分类</a><br />
<c:forEach items="${indexDTO.software_topsList}" var="softwareInfo">
	<a href="showsoftwareInfo.php?id=${softwareInfo.id}&amp;mid=${mid }">${softwareInfo.shortName}</a><br/>
</c:forEach>
【今日精品】<br />
<c:set value="" var="plusFine"></c:set><c:set value="${fn:length(indexDTO.software_plusFineList)}" var="maxlength"></c:set><c:forEach items="${indexDTO.software_plusFineList}" var="softwareInfo" varStatus="vs"><c:set value="${plusFine}|${softwareInfo.name}" var="plusFine"></c:set><a href="showsoftwareInfo.php?id=${softwareInfo.id}&amp;mid=${mid }">${softwareInfo.shortName}</a><c:choose><c:when test="${fn:length(plusFine) > 10}"><br/><c:set value="" var="plusFine"></c:set></c:when><c:when test="${maxlength==vs.count }"><br/><c:set value="" var="plusFine"></c:set></c:when><c:otherwise>|</c:otherwise></c:choose></c:forEach>
<%
/*
【今日推荐】<br />
<c:set value="" var="recommend"></c:set><c:set value="${fn:length(indexDTO.software_recommendList)}" var="maxlength"></c:set><c:forEach items="${indexDTO.software_recommendList}" var="softwareInfo" varStatus="vs"><c:set value="${softwareInfo.name}|${recommend}" var="recommend"></c:set><a href="showsoftwareInfo.php?id=${softwareInfo.id}&amp;mid=${mid }">${softwareInfo.shortName}</a><c:choose><c:when test="${fn:length(recommend) > 10}"><br/><c:set value="" var="recommend"></c:set></c:when><c:when test="${maxlength==vs.count }"><br/><c:set value="" var="recommend"></c:set></c:when><c:otherwise>|</c:otherwise></c:choose></c:forEach>
*/
%>
 <!-- 搜索 -->
<input name="name" maxlength="15" size="8"/><anchor><go href="searchsoftwareInfo.php" method="post"><postfield name="name" value="$(name)" /><postfield name="mid" value="${mid }" /></go>搜软件|</anchor><anchor><go href="searchmodel.php" method="post"><postfield name="keyword" value="$(name)" /><postfield name="mid" value="${mid }" /></go>搜机型</anchor><br />
【软件分类】<br /><c:forEach items="${indexDTO.softwareTypeList}" var="softwareType"><a href="showst.php?mid=${mid}&amp;id=${softwareType.id}">${softwareType.name }</a><c:choose><c:when test="${softwareType.isWrap==1}"><br/></c:when><c:otherwise>|</c:otherwise></c:choose></c:forEach>
【361服务】<br />
友链|<a href="help.php?mid=${mid }&amp;no=6">收藏</a>|<a href="help.php?mid=${mid }&amp;no=8">合作</a>|<a href="help.php?mid=${mid }&amp;no=7">关于</a><br />
<jsp:include page="detail.jsp"></jsp:include>
</p>
</card>
</wml>