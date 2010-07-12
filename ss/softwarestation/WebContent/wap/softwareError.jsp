<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="失败" >
<p>
<jsp:include page="notices.jsp"></jsp:include>
您选择的软件不存在！<a href="index.php?mid=${mid }">返回</a><br />
可能原因：<br />
1.请求路径有误<br />
2.此软件已经被删除或者禁止访问<br />
<a href="index.php?mid=${mid }">首页</a> &gt;软件信息<br/>
<jsp:include page="detail.jsp"></jsp:include>
</p>
</card>
</wml>