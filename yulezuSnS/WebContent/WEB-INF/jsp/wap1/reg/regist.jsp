<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<%@page contentType="text/vnd.wap.wml;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>

<%@page import="cn.yulezu.utils.PropertiesUtil"%><wml>
<card title="<%=PropertiesUtil.getConfigProperties("appName") %>-免费注册">
<p>
免费注册娱乐族<br />
<c:if test="${!empty errorMessage }">${errorMessage }<br/></c:if>
用户名(手机号,或者英文字母,区分大小写)<br />
<input type="text" name="vAccount" maxlength="18" emptyok="false"/><br />
密码(输入英文字母、数字,不少于6位)<br />
<input type="text" name="vPassword" maxlength="18" emptyok="false"/><br />
再输入一次密码(两次输入的密码必须相同)<br />
<input type="text" name="vRPassword" maxlength="18" emptyok="false"/>
<br />
性别(选定后不可修改)<br />
<anchor>
	<go href="/sns/reg/regist" method="post">
		<postfield name="account" value="$vAccount" />
		<postfield name="password" value="$vPassword" />
		<postfield name="rPassword" value="$vRPassword" />
		<postfield name="sex" value="1" />
	</go>我是帅哥
</anchor><br />
<anchor>
	<go href="/sns/reg/regist" method="post">
		<postfield name="account" value="$vAccount" />
		<postfield name="password" value="$vPassword" />
		<postfield name="rPassword" value="$vRPassword" />
		<postfield name="sex" value="0" />
	</go>我是美女
</anchor>
<br />
已注册,请<a href="/sns/login">登录</a><br />
<a href="/sns/index">娱乐族首页</a>
</p>
</card>
</wml>