<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<c:set value="0" var="isShow"></c:set>
<c:set value="" var="titles"></c:set>
<c:choose>
	<c:when test="${softwareInfo eq null}">
		<c:set value="0" var="isShow"></c:set>
		<c:set value="暂时未找到此软件" var="titles"></c:set>
	</c:when>
	<c:otherwise>
		<c:set value="1" var="isShow"></c:set>
		<c:set value="${softwareInfo.name }--${model.phoneseries.brand.name}${model.name }" var="titles"></c:set>
	</c:otherwise>
</c:choose>
<card title="${titles}">
<p>
<jsp:include page="notices.jsp"></jsp:include>
<c:choose>
	<c:when test="${softwareInfo eq null}">暂时未找到此软件</c:when>
	<c:otherwise>
	${softwareInfo.name }<br/>
	<img src="/upload/image/${softwareInfo.id}/${softwareInfo.imgPath}" alt="${softwareInfo.name }" /><br/>
	<c:if test="${!empty software_p}">
		<a href="downloadsoftware.php?id=${software_p.id}&amp;id2=${softwareInfo.id}&amp;mid=${mid}">${model.name }免费下载/${name }/${software_p.size }k</a><br/>
	</c:if>
	<c:if test="${!empty software_java}">
		<a href="downloadsoftware.php?id=${software_java.id}&amp;id2=${softwareInfo.id}&amp;mid=${mid}">${model.name }免费下载/${software_java_name }/${software_java.size }k</a><br/>
	</c:if>
	<fmt:formatDate value="${softwareInfo.createTime }" pattern="yyyy-MM-dd" var="cts"/>
	[更新]${cts}<br/>
	[性质]${softwareInfo.traffic >0 ? "共享软件" :"完全免费"}<br/>
	[安全]<a href="help.php?mid=${mid }&amp;no=4">${softwareInfo.safety} </a><br/>
	[软件简介]
		<c:choose>
			<c:when test="${more eq null}">
				${fn:substring(softwareInfo.description,0,50)}
				<c:if test="${fn:length(softwareInfo.description)>50}"><a href="showsoftwareInfo.php?id=${id}&amp;mid=${mid }&amp;more=more">详细...</a> </c:if>
			</c:when>
			<c:otherwise>
				${softwareInfo.description}
			</c:otherwise>
		</c:choose>
		
	<br/>
	[适用机型]
	<c:choose>
		<c:when test="${model eq null}">
			
		</c:when>
		<c:otherwise>
			${model.phoneseries.brand.name }${model.name }
		</c:otherwise>
	</c:choose><br/>
	<c:if test="${!empty software_p}">
		<a href="downloadsoftware.php?id=${software_p.id}&amp;id2=${softwareInfo.id}&amp;mid=${mid}">${model.name }免费下载/${name }/${software_p.size }k</a><br/>
	</c:if>
	<c:if test="${!empty software_java}">
		<a href="downloadsoftware.php?id=${software_java.id}&amp;id2=${softwareInfo.id}&amp;mid=${mid}">${model.name }免费下载/${software_java_name }/${software_java.size }k</a><br/>
	</c:if>
	<c:choose>
		<c:when test="${!empty software_p}"></c:when>
		<c:when test="${!empty software_java}"></c:when>
		<c:otherwise>暂时没有您需要的软件，请联系管理员<br/></c:otherwise>
	</c:choose>
	[下载]${download};[分类]	<a href="showst.php?mid=${mid}&amp;id=${softwareInfo.softwareType.id}">${softwareInfo.softwareType.name }</a><br/>
	<a href="dsoftwareInfo.php?id=${softwareInfo.id}&amp;mid=${mid}">顶一下!${softwareInfo.vote }</a>|<a href="listcommentary.php?sid=${softwareInfo.id }&amp;mid=${mid}">评论${fn:length(softwareInfo.commentaryList) }</a>|<a href="report.php?sid=${softwareInfo.id }&amp;mid=${mid}">报错</a>
	</c:otherwise>
</c:choose>
<br />---------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;<a href="lsst.php?mid=${mid }">软件分类</a>&gt;<a href="showst.php?mid=${mid }&amp;id=${softwareInfo.softwareType.id}">${softwareInfo.softwareType.name}</a><br/>
<jsp:include page="detail.jsp"></jsp:include>
</p>
</card>
</wml>