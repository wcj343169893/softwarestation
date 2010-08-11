<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<%@page contentType="text/vnd.wap.wml;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/commons/taglibs.jsp"%>

<%@page import="cn.yulezu.utils.PropertiesUtil"%><wml>
<card title="<%=PropertiesUtil.getConfigProperties("appName") %>-有奖登录">
<p>
	欢迎登录<%=PropertiesUtil.getConfigProperties("applicationName") %><br/>
	<a href="/sns/reg/regist">还不是会员?立即注册</a><br/>
	<c:if test="${!empty errorMessage }">${errorMessage }<br/></c:if>
         手机号或帐号:<br/>
    <input name="vAccount" size="18" maxlength="18" value="13996041461"/><br/>
          用户密码:<br/>
    <input name="vPassword" type="password" size="18" maxlength="11" value="123456"/><br/>  
         状态:<select name="vStatues">
         	<option value="1">在线</option>
         	<option value="0">隐身(VIP)</option>
         </select><br />
    <anchor title="普通登录">
      <go href="/sns/login" method="post">
        <postfield name="account" value="$vAccount"/>
        <postfield name="password" value="$vPassword"/>
        <postfield name="t" value="general"/>
        <postfield name="statues" value="$vStatues"/>
      </go>普通登录</anchor>(原书签有效)<br/>
	 <anchor title="安全登录">
      <go href="/sns/login" method="post">
        <postfield name="account" value="$vAccount"/>
        <postfield name="password" value="$vPassword"/>
        <postfield name="t" value="safe"/>
        <postfield name="statues" value="$vStatues"/>
      </go>安全登录</anchor>(原书签失效)<br/>
    <a href="findPwd.jsp">忘记密码,我要找回</a><br/>
</p>
</card>
</wml>