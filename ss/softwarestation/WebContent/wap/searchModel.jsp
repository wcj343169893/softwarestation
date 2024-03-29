<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="设置我的手机">
<p>
<jsp:include page="notices.jsp"></jsp:include>
<anchor>
	<go href="setmodel.php?mid=${mid }"></go>返回
</anchor>
<br />--------------------<br />
&gt;&gt;搜索"${keyword }"结果<br />
<c:choose>
	<c:when test="${fn:length(pageResult.list)==0}">
	抱歉!没有找到您的机型.<br />
	</c:when>
	<c:otherwise>
		<c:forEach items="${pageResult.list}" var="model" varStatus="vs">
		${vs.count }.<a href="suremodel.php?mid=${model.id }&amp;from=${from}">${model.phoneseries.brand.name }${model.name }</a><br />
		</c:forEach>
		<c:set var="page" value="${pageResult}"></c:set>
			<c:if test="${page.pageNo!=1}"><a href="searchmodel.php?keyword=${keyword }&amp;p=${page.pageNo-1 }&amp;mid=${mid }&amp;from=${from}">上页</a></c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="searchmodel.php?keyword=${keyword }&amp;p=${page.pageNo+1 }&amp;mid=${mid }&amp;from=${from}">下页</a></c:if> 
			<c:if test="${page.pageNo!=1}"><a href="searchmodel.php?keyword=${keyword }&amp;p=1&amp;mid=${mid }&amp;from=${from}">首页</a></c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="searchmodel.php?keyword=${keyword }&amp;p=${page.pageTotal}&amp;mid=${mid }&amp;from=${from}">尾页</a></c:if>
			共${page.recTotal}个 
				<br/>${page.pageNo}/${page.pageTotal}页,至<input name="pageno" maxlength="2" size="2" format="*N" value="${page.pageNo}"/>页 <anchor>
				<go href="searchmodel.php">
					<postfield name="p" value="$(pageno)" />
					<postfield name="keyword" value="${keyword }" />
					<postfield name="mid" value="${mid }" />
					<postfield name="from" value="${from }" />
				</go>跳页</anchor><br/>
	</c:otherwise>
</c:choose>
--------------------<br />
【温馨提示】<br />
如未找到您的机型,可能因: 1.输入机型错误,请重新输入<br />
<input name="xh" size="8" emptyok="true" />
 <anchor>
		<go href="searchmodel.php" method="post">
			<postfield name="keyword" value="$(xh)" />
			<postfield name="mid" value="${mid }" />
			<postfield name="from" value="${from }" />
		</go>搜索机型
	</anchor> <br />
(如诺基亚N73,输入N73或73)<br />
2.本站暂未收录您的机型,请您将手机品牌及型号<a href="reportModel.php?mid=${mid}">报告给我们</a>,同时建议您可返回设置机型页尝试设置手机平台 下载适合您手机的软件.<br />
3.建议您设置完机型或平台后将本站收藏(存为书签),方便您下次访问无需再次设置机型,还能节省您宝贵的流量.<br />
--------------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;<a href="setmodel.php?mid=${mid }&amp;from=${from}">设置机型</a>&gt;机型搜索<br />
<jsp:include page="detail.jsp"></jsp:include>
</p>
</card>
</wml>