<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="查询软件">
<p>
<jsp:include page="notices.jsp"></jsp:include>
<c:set value="${fn:length(pageResult.list)}" var="resultSize"></c:set>
"${name }"的软件搜索结果共有${resultSize }个:<br />
<c:choose>
	<c:when test="${resultSize==0}">暂无相关软件</c:when>
	<c:otherwise>
		<c:forEach items="${pageResult.list}" var="si" varStatus="vs">
			${vs.count }.<c:if test="${si.recommend==1}"><img src="img/commend.gif" alt="推荐" /></c:if><a href="showsoftwareInfo.php?id=${si.id}&amp;mid=${mid }">[${si.softwareType.name }]${si.name }</a><br/>
		</c:forEach>
		<c:set var="page" value="${pageResult}"></c:set>
			<c:if test="${page.pageNo!=1}"><a href="searchsoftwareInfo.php?p=${page.pageNo-1 }&amp;mid=${mid }&amp;name=${name}">上页</a> </c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="searchsoftwareInfo.php?p=${page.pageNo+1 }&amp;mid=${mid }&amp;name=${name}">下页</a> </c:if> 
			<c:if test="${page.pageNo!=1}"><a href="searchsoftwareInfo.php?p=1&amp;mid=${mid }&amp;name=${name}">首页</a> </c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="searchsoftwareInfo.php?p=${page.pageTotal}&amp;mid=${mid }&amp;name=${name}">尾页</a></c:if>
			共${page.recTotal}个 
				<br/>${page.pageNo}/${page.pageTotal}页,至<input name="pageno" maxlength="2" size="2" format="*N" value="${page.pageNo}"/>页 <anchor>
				<go href="searchsoftwareInfo.php">
					<postfield name="p" value="$(pageno)" />
					<postfield name="mid" value="${mid }" />
					<postfield name="name" value="${name }" />
				</go>跳页</anchor><br/>
	</c:otherwise>
</c:choose>
--------------------<br />
【温馨提示】<br />
如未找到您需要的软件,可能因: 1.输入信息错误,请重新输入<br />
<input name="name" emptyok="true" maxlength="15"/>
 <anchor>
		<go href="searchsoftwareInfo.php" method="post">
			<postfield name="name" value="$(name)" />
			<postfield name="mid" value="${mid }" />
		</go>搜索软件
	</anchor> <br />
--------------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;搜索结果<br />
<jsp:include page="detail.jsp"></jsp:include>
</p>
</card>
</wml>