<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<%@page contentType="text/vnd.wap.wml;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>

<%@page import="cn.yulezu.utils.RequestUtil"%>
<%@page import="cn.yulezu.utils.Config"%>
<%@page import="cn.yulezu.sns.entity.Users"%><wml>
<card title="手机认证">
<p>
【娱乐第二步:认证】<br />
<br />
<c:if test="${!empty errorMessage }">${errorMessage }<br/></c:if>
用户名:${users.username}<br />
输入您的手机:(免费认证,我们不收取任何信息费)<br />
<input type="text" name="vMobile" format="*N"/><br />
<anchor>
	<go href="/sns/reg/regist2m" method="post">
		<postfield name="uid" value="${users.id}" />
		<postfield name="mobile" value="$vMobile" />
	</go>下一步
</anchor><br />
嫌麻烦?<anchor><go href="/sns/login" method="post"><postfield name="account" value="${users.username}"/><postfield name="password" value="${users.password}"/><postfield name="t" value="general"/><postfield name="statues" value="1"/></go>跳过此页</anchor>
</p>
</card>
</wml>