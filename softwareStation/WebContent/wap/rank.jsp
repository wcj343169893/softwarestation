<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="${phoneModel.phoneseries.brand.name}${phoneModel.name}排行">
<p>
<jsp:include page="notices.jsp"></jsp:include>
<c:set value="rankindex.php?mid=${mid}&amp;ranks=${ranks }" var="url"></c:set>
${ranks==0 ?"日榜":"<a href='rankindex.php?mid="}${ranks==0?"":mid}${ranks==0?"":"&amp;ranks=0'>日榜</a>" }.
${ranks==1 ?"月榜":"<a href='rankindex.php?mid="}${ranks==1?"":mid}${ranks==1?"":"&amp;ranks=1'>月榜</a>" }.
${ranks==2 ?"总榜":"<a href='rankindex.php?mid="}${ranks==2?"":mid}${ranks==2?"":"&amp;ranks=2'>总榜</a>" }<br/>
----------------------<br />
<c:choose>
	<c:when test="${fn:length(pageResult.list)==0}">暂无相关软件</c:when>
	<c:otherwise>
		<c:forEach items="${pageResult.list}" var="si" varStatus="vs">
			${vs.count }.<c:if test="${si[1].recommend==1}"><img src="img/commend.gif" alt="推荐" /></c:if><a href="showsoftwareInfo.php?id=${si[1].id}&amp;mid=${mid }">[${si[1].softwareType.name }]${si[1].name }</a><br/>
		</c:forEach>
		<c:set var="page" value="${pageResult}"></c:set>
			<c:if test="${page.pageNo!=1}"><a href="rankindex.php?p=${page.pageNo-1 }&amp;mid=${mid }&amp;ranks=${ranks}">上页</a> </c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="rankindex.php?p=${page.pageNo+1 }&amp;mid=${mid }&amp;ranks=${ranks}">下页</a> </c:if> 
			<c:if test="${page.pageNo!=1}"><a href="rankindex.php?p=1&amp;mid=${mid }&amp;ranks=${ranks}">首页</a> </c:if>
			<c:if test="${page.pageNo<page.pageTotal}"><a href="rankindex.php?p=${page.pageTotal}&amp;mid=${mid }&amp;ranks=${ranks}">尾页</a></c:if>
			共${page.recTotal}个 
				<br/>${page.pageNo}/${page.pageTotal}页,至<input name="pageno" maxlength="2" size="2" format="*N" value="${page.pageNo}"/>页 <anchor>
				<go href="rankindex.php">
					<postfield name="p" value="$(pageno)" />
					<postfield name="mid" value="${mid }" />
					<postfield name="ranks" value="${ranks }" />
				</go>跳页</anchor>
	</c:otherwise>
</c:choose>
<br />---------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;${phoneModel.phoneseries.brand.name}${phoneModel.name}排行<br/>
<jsp:include page="detail.jsp"></jsp:include>
</p>
</card>
</wml>