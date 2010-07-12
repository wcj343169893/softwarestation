<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="报错" >
<p>
<jsp:include page="notices.jsp"></jsp:include>
<a href="showsoftwareInfo.php?id=${softwareInfo.id}&amp;mid=${mid }">${softwareInfo.name }</a><br />
类型<select name="rid">
<c:forEach items="${reports}" var="c">
	<option value="${c.key }">${c.value }</option>
</c:forEach>
</select><br />
附言<input name="ps" maxlength="20"/><br />
<anchor>
	<go href="addreport.php" method="post">
		<postfield name="rid" value="$(rid)" />
		<postfield name="sid" value="${sid }" />
		<postfield name="mid" value="${mid }" />
		<postfield name="ps" value="$(ps)" />
	</go>提交<br />
</anchor>
 友情提醒:管理员会核实每一个报错,如果属实将会被奖励5朵大红花,恶意报错将会被惩罚! 
<br/>---------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;${softwareInfo.name }报错<br/>
<jsp:include page="detail.jsp"></jsp:include>
</p>
</card>
</wml>