<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<%@page contentType="text/vnd.wap.wml;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>

<%@page import="cn.yulezu.utils.RequestUtil"%>
<%@page import="cn.yulezu.utils.Config"%>
<%@page import="cn.yulezu.sns.entity.Users"%><wml>
<card title="手机绑定">
<p>
<c:if test="${!empty errorMessage }">${errorMessage }<br/></c:if>
手机号认证:${users.mobile}<a href="/sns/reg/regist2m?uid=${users.id}">修改</a><br />
方式一:用上述手机号在5分钟之内，拨打<a href="#">13178825985</a>,<br />
电话接通后,等待系统自动挂断即可完成绑定.(本操作全免费)<br />
方式二:
编辑短信U(不区分大小写)发送到<a href="#">13178825985</a>完成绑定<br />
<a href="yulesanbu.html"><br />
下一步</a>
</p>
</card>
</wml>