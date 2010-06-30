<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="${phoneModel.phoneseries.brand.name}${phoneModel.name}最新">
<p>
以下是软件列表：
<c:choose>
	<c:when test="${isJava == 1}"><a href="newsindex.php?p=1&amp;isJava=0&amp;mid=${mid }">显示通用版</a></c:when>
	<c:when test="${isJava == 0}"><a href="newsindex.php?p=1&amp;isJava=1&amp;mid=${mid }">过滤通用版</a></c:when>
	<c:otherwise></c:otherwise>
</c:choose><br />
<c:choose>
	<c:when test="${fn:length(pageResult.list)==0}">暂无相关软件</c:when>
	<c:otherwise>
		<c:forEach items="${pageResult.list}" var="si" varStatus="vs">
			${vs.count }.<c:if test="${si.recommend==1}"><img src="img/commend.gif" alt="推荐" /></c:if><a href="showsoftwareInfo.php?id=${si.id}&amp;mid=${mid }">[${si.softwareType.name }]${si.name }</a><br/>
		</c:forEach>
		<c:set var="page" value="${pageResult}"></c:set>
			<c:if test="${page.pageNo!=1}"><a href="newsindex.php?p=${page.pageNo-1 }&amp;mid=${mid }">上页</a> </c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="newsindex.php?p=${page.pageNo+1 }&amp;mid=${mid }">下页</a> </c:if> 
			<c:if test="${page.pageNo!=1}"><a href="newsindex.php?p=1&amp;mid=${mid }">首页</a> </c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="newsindex.php?p=${page.pageTotal}&amp;mid=${mid }">尾页</a></c:if>
			共${page.recTotal}个 
				<br/>${page.pageNo}/${page.pageTotal}页,至<input name="pageno" maxlength="2" size="2" format="*N" value="${page.pageNo}"/>页 <anchor>
				<go href="newsindex.php">
					<postfield name="p" value="$(pageno)" />
					<postfield name="mid" value="${mid }" />
				</go>跳页</anchor>
	</c:otherwise>
</c:choose>
<br/>---------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;${phoneModel.phoneseries.brand.name}${phoneModel.name}最新<br/>
</p>
</card>
</wml>