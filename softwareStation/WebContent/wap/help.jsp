<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>

<c:choose>
	<c:when test="${no==1}">
		<card title="手机平台说明" >
		<p>
			【塞班平台】
			   S60 V3:Symbian 60操作系统的第三版，是S60系列目前使用最广泛的智能手机操作系统.包括诺基亚N96|N95|N93|N92|N91|N86|N85|N83|N82|N81|N73|E71等机型.
			   S60 V5:Symbian 60操作系统的第五版，也是S60系列目前最新的版本.s60v5有相比s60v3更强的智能性和更强的硬件支持性.同时在GUI(操作系统交互界面）方面也是迄今为止塞班除UIQ以外最漂亮的.包括诺基亚X6|N97 |5800|5530xm|5233|5230等机型.
			   S60 V2:Symbian 60操作系统的第二版.包括诺基亚N90|N72|N70|7610|6682|6681|6680|6670|6630等机型.
			   S60 V1:Symbian 60操作系统的第一版.包括诺基亚N-GageQD|N-Gage|7650|3660|3650|3620机型.
			   UIQ3.0:Symbian UIQ是基于Symbian智能系统的操作界面之一，UIQ界面上可支持手写操作.UIQ 3.0是通过索尼爱立信P990首发的.
			   UIQ2.0:Symbian UIQ是基于Symbian智能系统的操作界面之一，UIQ界面上可支持手写操作.UIQ 2.0是通过索尼爱立信P800首发的.
			   S40 v2:Symbian 40操作系统的第二版.包括诺基亚7500|7380|7360|7270|7260|6822|6230i|6230|6170|6125|6103|6102|6101等机型.
			   S40 v3:Symbian 40操作系统的第三版.包括诺基亚8600 Luna|7610Supernova|7510Supernova|7510a|7373|7370|7212c|7070Prism|7020|6700c等机型.
			   S40 v5:Symbian 40操作系统的第五版.包括诺基亚8800 Sapphire Arte|8800 Diamond Arte|8800 Carbon Arte|7310Supernova|7210Supernova|6600fold|等机型.
			【微软平台】
			   Pocket PC:ppc是基于windows mobile系统的触摸屏智能手机.
			   Smartphone:smart phone是基于windows mobile系统的数字键盘型智能手机.
			   M8 os: M8的操作系统是MYMOBILE.是魅族科公司开发的基于微软Windows CE6.0核心的手持设备操作系统.运行于魅族M8手机上，手指触摸操作，拥有出色的用户体验，并集成通信，多媒体，网络浏览等功能.
			【谷歌平台】
			   Android:Android 是Google开发的基于Linux平台的开源手机操作系统.包括HTC宏Hero200|G4|G3|G2|G1|Espresso等机型.
			【苹果平台】 
			   iPhone 3.x :iPhone OS是苹果公司为iPhone开发的操作系统.3.x表示iphone os的固件版本号.
			   iPhone 2.x:iPhone OS是苹果公司为iPhone开发的操作系统.2.x表示iphone os的固件版本号.
			【其它平台】
			   Palm os :Palm OS平台是一个开放式软件架构、平台由硬件参考设计的Palm OS操作系统.包括PalmTreo700p|Treo680|Treo650|Treo600|Centro机型.
			   Java:J2ME(Java 2 Micro Edition)平台是目前最火暴的手机游戏平台,它还可以下载和安装一些小的应用程序..J2ME简单的可以理解为Java的移动版本，目前市场上Java游戏中支持诺基亚手机最多,该品牌部分手机存在下载程序不能超过64K的限制,运行速度还是在兼容性上索爱手机基本上都是最为出色.
			   BlackBerry os:BlackBerry OS是RIM公司推出的黑莓手机(Blackberry)专用的操作系统.
			------------------------------
			<a href="index.php?mid=${mid }">首页</a>&gt;<a href="help.php?mid=${mid }">帮助中心</a>&gt;加入书签
		</p>
		</card>
	</c:when>
	<c:when test="${no==2}">
		<card title="下载文件格式" >
		<p>
			Sis:sis文件是s60二版以前的塞班系统安装软件所用的格式.一般能支持3230|6260|6620|6630|6670|6680|6681|6682|7610|N70|N72|N90等S60二版手机机型和一版手机机型.
			sisx:sisx文件是针对s60三版,s60五版,UIQ三版的一种安装格式.一般能支持N73|N71|N75|N76|N77|N81|N82|N92|N93|N95|N61|E50|E51|E61|E62|E65|5700|E71|E90|3250|N91|5500|N80|N97|5800等机型.
			cab:cab文件是一种压缩格式文件,一个cab文件包含一个或多个文件及其安装信息文件,一般能支持window操作系统(即PocketPC系统)的手机机型.
			exe:exe是一个可执行程序,支持windowsmobile系统平台智能手机,属于绿色型软件.
			rar:rar的文件都是压缩包格式的文件,需要配合解压缩软件才能配套解压使用.
			zip:zip的文件都是压缩包格式的文件,需要配合解压缩软件才能配套解压使用.
			Pxl:pxl是一种支持脚本方式的iPhone上的软件安装包,不仅可以支持游戏类型,也可以支持系统工具.
			Ipa:ipa是Apple官方支持的程序用文件,可以通过iTunes来安装到iPhone上,通常主要ipa程序以游戏为主.
			Jar:jar 文件格式以流行的 zip文件格式为基础,jar文件是一种压缩格式手机程序安装文件,一个jar文件包含一个或多个文件及其安装信息文件,支持java功能的手机都支持该文件格式.
			Jad:jad是Java软件配置信息文件,jar才是真正的程序,你可以把两个文件放在一起安装jad,也可以直接安装jar文件.jad文件为纯文本格式,可用记事本打开.
			Apk:apk是Android Package的缩写,即Android安装包.APK是类似Symbian Sis或Sisx的文件格式.通过将APK文件直接传到Android模拟器或Android手机中执行即可安装.
			Sga:sga是Android软件的皮肤文件.
			prc:prc文件是Palm OS应用程序的安装文件,PRC格式文件直接点击安装即会自动复制到手机RAM中即可运行.一般能支持Palm OS操作系统的PalmTreo700p|Treo680|Treo650|Treo600|Centro等机型. 
			------------------------------
			<a href="index.php?mid=${mid }">首页</a>&gt;<a href="help.php?mid=${mid }">帮助中心</a>&gt;加入书签
		</p>
		</card>
	</c:when>
	<c:when test="${no==3}">
		<card title="常见安装问题" >
		<p>
			
			<a href="index.php?mid=${mid }">首页</a>&gt;<a href="help.php?mid=${mid }">帮助中心</a>&gt;常见问题
		</p>
		</card>
	</c:when>
	<c:when test="${no==4}">
		<card title="软件安全介绍" >
		<p>
			【用户帮助中心】
			=软件安全介绍=
			病毒:手机病毒是一种破坏性程序,和计算机病毒(程序)一样具有传染性,破坏性.手机病毒可利用发送短信,彩信,电子邮件,浏览网站,下载铃声,蓝牙等方式进行传播.手机病毒可能会导致用户手机死机,关机,资料被删,向外发送垃圾邮件,拨打电话等,甚至还会损毁 SIM卡,芯片等硬件.
			插件:手机插件是除了软件自身程序外,恶意开发者或者非法厂家对程序实行重新打包加入一些危害用户的可执行文件.主要体现在：自动扣费/收集用户信息/或其它损害手机系统的东西.
			暗扣:暗扣软件指未经用户主观意愿同意就发生扣费的软件.一般具有如下特征：1.安装后或启动软件后无任何资费提示即开始扣费.2.部分客户端安装后不会立即扣费,而是延时或不定时扣费,让用户很难察觉.3.资费提示模糊不清,误导用户点击收费按钮.4.资费提示弹出时,软件左右菜单设计中无返回/退出按钮,误导性的强制用户点击收费按钮.
			------------------------------
			<a href="index.php?mid=${mid }">首页</a>&gt;<a href="help.php?mid=${mid }">帮助中心</a>&gt;软件安全介绍
		</p>
		</card>
	</c:when>
	<c:when test="${no==5}">
		<card title="如何查看机型 " >
		<p>
			1.诺基亚：待机状态下按*#0000#或#92702689#。
			2.索爱：待机状态下按音量键。
			3.三星：待机状态下按*#1234#。
			4.所有手机：可通过打开手机后盖-取出电池后查看电池槽下小帖子里的型号。 
			------------------------------
			<a href="index.php?mid=${mid }">首页</a>&gt;<a href="help.php?mid=${mid }">帮助中心</a>&gt;查看机型
		</p>
		</card>
	</c:when>
	<c:when test="${no==6}">
		<card title="书签使用帮助" >
		<p>
			【书签使用帮助】
			&gt;&gt;什么是书签?
			书签是一种浏览网页的便捷上网方式,就跟IE收藏夹一样,只要将您设置机型完成后的页面添加或收藏为书签,下次您使用该书签访问天网,即可浏览到保存了您设置了机型的页面,下载到适合您手机的软件了.
		</p>
		</card>
	</c:when>
	<c:otherwise>
	<card title="帮助中心" >
	<p>
		【用户帮助中心】
		=关于本站=
		免责声明
		关于我们
		=关于软件下载=
		1.手机平台介绍
		2.下载文件格式
		3.软件安装教程
		4.病毒/插件/暗扣
		5.查看机型方法说明
		6.天网书签使用方法
		--------------------
		<a href="index.php?mid=${mid }">首页</a>&gt;帮助
		--------------------
		</p>
</card>
	</c:otherwise>
</c:choose>

</wml>