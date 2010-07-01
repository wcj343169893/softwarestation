<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="设置我的手机">
<p>
<anchor>
	<go href="setmodel.php?mid=${mid }"></go>返回
</anchor>
<br />
<c:choose>
	<c:when test="${keyword eq null}">${brand.name }机型专区</c:when>
	<c:otherwise>搜索结果</c:otherwise>
</c:choose> <br/>
--------------------<br />
<c:choose>
	<c:when test="${fn:length(pageResult.list)==0}">非常抱歉，暂时没有您的手机型号！我们将尽快完善<br/></c:when>
	<c:otherwise>
		<c:forEach items="${pageResult.list}" var="model" varStatus="vs">
			<c:choose><c:when test="${keyword eq null}"><a href="suremodel.php?mid=${model.id }">${model.name }</a></c:when><c:otherwise><a href="index.php?mid=${model.id }">${vs.count }.${model.phoneseries.brand.name } ${model.name }</a><br/></c:otherwise></c:choose>
		</c:forEach><br/>
		<c:set var="page" value="${pageResult}"></c:set>
			<c:if test="${page.pageNo!=1}"><a href="setmodel.php?bid=${bid }&amp;p=${page.pageNo-1 }&amp;mid=${mid }">上页</a> </c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="setmodel.php?bid=${bid }&amp;p=${page.pageNo+1 }&amp;mid=${mid }">下页</a> </c:if> 
			<c:if test="${page.pageNo!=1}"><a href="setmodel.php?bid=${bid }&amp;p=1&amp;mid=${mid }">首页</a> </c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="setmodel.php?bid=${bid }&amp;p=${page.pageTotal}&amp;mid=${mid }">尾页</a></c:if>
			共${page.recTotal}个<br/>
			${page.pageNo}/${page.pageTotal}页,至<input name="pageno" maxlength="2" size="2" format="*N" value="${page.pageNo}"/>页 <anchor><go href="setmodel.php?bid=${bid }&amp;mid=${mid }">
			<postfield name="p" value="$(pageno)" />
				</go>跳页</anchor><br/>
					<!-- 
					【机型系列】<br/>
					N开头|E|8|7|6|5|3|其他<br/>
					(如7610选择'7')<br/>
					 -->
	</c:otherwise>
</c:choose>
【温馨提示】<br />
1.建议您设置完机型或平台后将本站收藏(存为书签),方便您下次访问无需再次设置,还能节省您宝贵的流量!<br />
2.如未找到您的机型,请您将手机品牌及机型报告给我们.<br />
--------------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;<a href="setmodel.php?mid=${mid }">设置机型</a>&gt;${brand.name }<br />
--------------------<br />
</p>
</card>
</wml>