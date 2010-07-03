<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="成功" >
<p>
<jsp:include page="notices.jsp"></jsp:include>
报错成功<a href="showsoftwareInfo.php?id=${sid}&amp;mid=${mid }">返回</a>
<br/>---------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;报错成功<br/>
<jsp:include page="detail.jsp"></jsp:include>
</p>
</card>
</wml>