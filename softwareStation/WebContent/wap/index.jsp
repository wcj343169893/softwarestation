<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="软件站">
<p>
<c:choose>
	<c:when test="${mid>0 }">
	我的机型 -${mid }<a href="setmodel.php?mid=${mid }">更改机型</a> <br />
	</c:when>
	<c:otherwise>
	未设置机型<a href="setmodel.php">设置机型</a> <br />
	</c:otherwise>
</c:choose>
<img src="/img/logo.png" alt="软件站" /><br />
软件|游戏|主题|必备|品牌<br />
免费|世博|资讯|在线咨询<br />
6月9日更新:软件14|游戏11<br />
[推荐]<br />
看机构资金尽在益盟操盘手 <br />
上网缓慢,请用手机任务管理 <br />
移动看书软件,畅销小说免量看<br />
【实用软件】闹钟管理器<br />
QQ2010.天气.QQ浏览.词典<br />
看书.酷狗音乐.360卫士.飞流 <br />
操盘手.南方金媒.通话记事<br />
【手机软件】最新.最热<br />
系统|常用|聊天|地图|通讯<br />
查询|安全|字典|音乐|阅读<br />
输入|图像|拍照|视频|其他<br />
</p>
</card>
</wml>