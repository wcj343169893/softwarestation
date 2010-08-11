<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<%@page contentType="text/vnd.wap.wml;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>

<%@page import="cn.yulezu.utils.RequestUtil"%>
<%@page import="cn.yulezu.utils.Config"%>
<%@page import="cn.yulezu.sns.entity.Users"%><wml>
<card title="注册成功-娱乐族">
<p>
	欢迎成为娱乐族的用户,请记住你的用户名和密码! <br />
用户名:${users.username}<br />
密码:${users.password }<br />
<br />
<c:if test="${!empty errorMessage }">${errorMessage }<br/></c:if>
请输入昵称(给自己起个炫酷的名字,建议使用中文)<br />
<input type="text" name="vNickname" emptyok="false"/>
<br />
你的生日(格式:19800512)<br />
<input name="vBirthday" type="text" emptyok="false"/>
<br />
<anchor>
	<go href="/sns/reg/regist2n" method="post">
		<postfield name="uid" value="${users.id}" />
		<postfield name="nickname" value="$vNickname" />
		<postfield name="birthday" value="$vBirthday" />
	</go>下一步
</anchor><br />
<br />
<a href="/sns/index">娱乐族首页</a>
</p>
</card>
</wml>