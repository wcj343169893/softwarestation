<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<%@page contentType="text/vnd.wap.wml;charset=utf-8"%>

<%@page import="cn.yulezu.utils.PropertiesUtil"%><wml>
<card title="+<%=PropertiesUtil.getConfigProperties("appName") %>导航+<%=PropertiesUtil.getConfigProperties("domain") %>">
<p>
<a href="/sns/login">登录</a>
<a href="/sns/reg/regist">注册</a>
</p>
</card>
</wml>