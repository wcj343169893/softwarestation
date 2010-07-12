<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="成功" ontimer="${from}">
<timer value="1" />
<p>
<jsp:include page="notices.jsp"></jsp:include>
设置机型成功!<br />
您的手机：${phoneModel.name }<br />
操作系统:${phoneModel.phoneseries.os.name }<br />
如没跳转请点击<a href="${from}">返回</a>
<br/>---------------<br />
<a href="index.php?mid=${mid }">首页</a>&gt;设置机型<br/>
<jsp:include page="detail.jsp"></jsp:include>
</p>
</card>
</wml>