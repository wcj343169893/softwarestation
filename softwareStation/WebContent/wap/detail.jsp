<%@page import="java.util.Date"%>
<%@page import="cn.common.util.Tool"%><%@page contentType="text/vnd.wap.wml;charset=utf-8"%>
<%
String times=Tool.dateFormatString(new Date(),"yyyy-MM-dd HH:mm");
out.print("---------------<br />苏ICP备10088131<br/>361软件站:361rj.com <br/> <img src=\"/img/clock.gif\" alt=\"361报时\" />"+times);
%>

