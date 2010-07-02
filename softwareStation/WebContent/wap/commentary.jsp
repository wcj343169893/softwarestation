<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="评论">
<p>
<a href="showsoftwareInfo.php?id=${softwareInfo.id}&amp;mid=${mid }">${softwareInfo.name }</a><br />
【浏览+发表评论】<br />
<input name="content" />
<anchor>
	<go href="addcommentary.php" method="post">
		<postfield name="content" value="$(content)" />
		<postfield name="sid" value="${sid }" />
		<postfield name="mid" value="${mid }" />
	</go>提交 <br />
</anchor>
<c:choose>
	<c:when test="${fn:length(pageResult.list)==0}">
	抱歉!没有任何评论<br />
	</c:when>
	<c:otherwise>
		<c:set var="page" value="${pageResult}"></c:set>
		<c:forEach items="${page.list}" var="commentary" varStatus="vs">
		&lt;${(page.pageNo-1)*page.pageSize+vs.count }楼&gt; ${commentary.content }<br/>
		<fmt:formatDate value="${commentary.commentTime }" pattern="yyyy-MM-dd HH:mm:ss" var="ct"/>
		[${ct }]<br/>
		</c:forEach>
			<c:if test="${page.pageNo!=1}"><a href="listcommentary.php?p=${page.pageNo-1 }&amp;mid=${mid }&amp;sid=${sid}">上页</a></c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="listcommentary.php?p=${page.pageNo+1 }&amp;mid=${mid }&amp;sid=${sid}">下页</a></c:if> 
			<c:if test="${page.pageNo!=1}"><a href="listcommentary.php?p=1&amp;mid=${mid }&amp;sid=${sid}">首页</a></c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="listcommentary.php?p=${page.pageTotal}&amp;mid=${mid }&amp;sid=${sid}">尾页</a></c:if>
			共${page.recTotal}个 
				<br/>${page.pageNo}/${page.pageTotal}页,至<input name="pageno" maxlength="2" size="2" format="*N" value="${page.pageNo}"/>页 <anchor>
				<go href="listcommentary.php">
					<postfield name="p" value="$(pageno)" />
					<postfield name="mid" value="${mid }" />
					<postfield name="sid" value="${sid }" />
				</go>跳页</anchor><br/>
	</c:otherwise>
</c:choose>
 <input name="content1" />
<anchor>
	<go href="addcommentary.php"  method="post">
		<postfield name="content" value="$(content1)" />
		<postfield name="sid" value="${sid }" />
		<postfield name="mid" value="${mid }" />
	</go>提交 <br />
</anchor>
------------------------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;手机软件<br />
------------------------------<br />
</p>
</card>
</wml>