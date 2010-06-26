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
<c:choose>
	<c:when test="${fn:length(pageResult.list)==0}">非常抱歉，暂时没有您的手机型号！我们将尽快完善</c:when>
	<c:otherwise>
		<c:forEach items="${pageResult.list}" var="model" varStatus="vs">
			<c:choose><c:when test="${keyword eq null}"><a href="index.php?mid=${model.id }">${model.name }</a></c:when><c:otherwise><a href="index.php?mid=${model.id }">${vs.count }.${model.phoneseries.brand.name } ${model.name }</a><br/></c:otherwise></c:choose>
		</c:forEach><br/>
		<c:set var="page" value="${pageResult}"></c:set>
		${page.recTotal}个
				<a href="setmodel.php?bid=${bid }&amp;p=1">首页</a> <c:if test="${page.pageNo!=1}"><a href="setmodel.php?bid=${bid }&amp;p=${page.pageNo-1 }">上页</a> </c:if>
						<c:if test="${page.pageNo<page.pageTotal}"><a href="setmodel.php?bid=${bid }&amp;p=${page.pageNo+1 }">下页</a> </c:if> <a
					href="setmodel.php?bid=${bid }&amp;p=${page.pageTotal}">尾页</a> 
					<br/>${page.pageNo}/${page.pageTotal}至<input name="" maxlength="2" size="2"/>页 <anchor><go href=""></go> 跳页<br /></anchor>
	</c:otherwise>
</c:choose>

</p>
</card>
</wml>