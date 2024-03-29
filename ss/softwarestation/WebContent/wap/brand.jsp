<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="设置我的手机">
<p>
<jsp:include page="notices.jsp"></jsp:include>
	<input name="xh" size="8" emptyok="true" />
	<anchor>
		<go href="searchmodel.php" method="post">
			<postfield name="keyword" value="$(xh)" />
			<postfield name="from" value="${from }" />
			<postfield name="mid" value="${mid }" />
		</go>快速搜索机型
	</anchor><br />
	如诺基亚N73,只需输'N73'<br />
	&gt; &gt;<a href="help.php?no=5&amp;mid=${mid}">如何查看自已的手机型号</a><br />
	【诺基亚】<br/>
	<c:set value="0" var="maxsize"></c:set>
	<c:forEach items="${brandDTO.phoneModelList}" var="model" varStatus="vs"><a href="suremodel.php?mid=${model.id }&amp;from=${from}">${model.name}</a><c:choose><c:when test="${vs.count % 5 == 0}"><br/></c:when><c:otherwise>|</c:otherwise></c:choose></c:forEach><a href="setmodel.php?bid=1&amp;mid=${mid }&amp;p=1&amp;from=${from}">诺基亚全部机型</a><br />
	【其他品牌】<br/>
	<c:set value="${fn:length(brandDTO.phoneBrandList)-1}" var="maxsize"></c:set>
	<c:forEach items="${brandDTO.phoneBrandList}" var="brand" varStatus="vs" begin="1"><a href="setmodel.php?bid=${brand.id}&amp;mid=${mid }&amp;p=1&amp;from=${from}">${brand.name}</a><c:choose><c:when test="${vs.count % 4 == 0 ||maxsize== vs.count}"><br/></c:when><c:otherwise>|</c:otherwise></c:choose></c:forEach>
	【温馨提示】<br/>
	1.建议您设置完机型或平台后将本站<a href="help.php?no=6&amp;mid=${mid}">收藏(存为书签)</a>,方便您下次访问无需再次设置,还能节省您宝贵的流量!<br/>
	2.如未找到您的机型,请您将手机品牌及机型<a href="reportModel.php?mid=${mid}">报告给我们</a>.<br/>
	<a href="index.php?mid=${mid }">首页</a> &gt;设置机型<br/>
	<jsp:include page="detail.jsp"></jsp:include>
</p>
</card>
</wml>