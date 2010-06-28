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
		<c:set value="${softwareInfo.name }" var="titles"></c:set>
	</c:otherwise>
</c:choose>
<card title="${titles}">
<p>
<c:choose>
	<c:when test="${softwareInfo eq null}">暂时未找到此软件</c:when>
	<c:otherwise>
	${softwareInfo.name }<br/>
	<img src="/upload/image/${softwareInfo.id}/${softwareInfo.imgPath}" alt="${softwareInfo.name }" /><br/>
	<c:if test="${!empty software_p}">
		<a href="downloadsoftware.php?id=${software_p.id}&amp;id2=${softwareInfo.id}&amp;mid=${mid}">${model.name }免费下载/${name }/${software_p.size }k</a><br/>
	</c:if>
	<fmt:formatDate value="${softwareInfo.createTime }" pattern="yyyy-MM-dd" var="ct"/>
	[更新]${ct}<br/>
	[资费]${softwareInfo.traffic >0 ?softwareInfo.traffic:"完全免费"}<br/>
	[安全]${softwareInfo.safety}<br/>
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
	<c:choose>
		<c:when test="${!empty software_java}">
			如果找不到你的机型,请下载<a href="downloadsoftware.php?id=${software_java.id}&amp;id2=${softwareInfo.id}">通用版本</a><br/>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	[下载]${download};[分类]${softwareInfo.softwareType.name}<br/>
	顶一下!13|评论233|报错<br/>
	</c:otherwise>
</c:choose>
<br />---------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;<a href="lsst.php?mid=${mid }">软件分类</a>&gt;<a href="showst.php?mid=${mid }&amp;id=${softwareInfo.softwareType.id}">${softwareInfo.softwareType.name}</a><br/>
</p>
</card>
</wml>