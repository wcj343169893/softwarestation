<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<%@page contentType="text/vnd.wap.wml;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>

<%@page import="cn.yulezu.utils.RequestUtil"%>
<%@page import="cn.yulezu.utils.Config"%>
<%@page import="cn.yulezu.sns.entity.Users"%><wml>
<card title="登陆成功" ontimer="<%=RequestUtil.encodeURL(request,response,"/sns/myspace") %>">
<timer value="30" />
<p>
	成功登录!欢迎您：${loginusers.nickname },您的ID:${loginusers.id }<br />
本页自动登陆到空间首页,请加空间首页为书签,以后可自动登陆哦^_^<br />
提示:每日第一次登录将增加经验20.使用双倍经验卡可以增加经验40点<br />
如果没自动跳转,<a href="<%=RequestUtil.encodeURL(request,response,"/sns/myspace") %>">点击此处进入首页</a> 
</p>
</card>
</wml>