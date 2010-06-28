<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	
	[更新]${softwareInfo.createTime}<br/>
	[资费]${softwareInfo.traffic >0 ?softwareInfo.traffic:"完全免费"}<br/>
	[安全]${softwareInfo.safety}<br/>
	[软件简介]${softwareInfo.description}<br/>
	[适用机型]
	<c:choose>
		<c:when test="${model eq null}">
			
		</c:when>
		<c:otherwise>
			${model.phoneseries.brand.name }${model.name }
		</c:otherwise>
	</c:choose><br/>
	<c:if test="${!empty software_p}">
		<a href="downloadsoftware.php?id=${software_p.id}&amp;id2=${softwareInfo.id}">免费下载到手机</a><br/>
	</c:if>
	<c:choose>
		<c:when test="${!empty software_java}">
			如果找不到你的机型,请下载<a href="downloadsoftware.php?id=${software_java.id}&amp;id2=${softwareInfo.id}">通用版本</a><br/>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose><br/>
<br/>
	[下载]1528332;[分类]${softwareInfo.softwareType.name}<br/>
	顶一下!13|评论233|报错<br/>
	</c:otherwise>
</c:choose>

</p>
</card>
</wml>