<?xml version="1.0" encoding="UTF-8"?>
<%@page contentType="text/vnd.wap.wml;charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<wml>
<card title="设置我的手机">
<p>
输入您的手机型号查找适合的软件<br />
如诺基亚N97,只需输'N97'<br />
<input name="xh" size="8" emptyok="true" />
<anchor>
	<go href="" method="post">
		<postfield name="model" value="$(xh)" />
	</go> [搜索机型]
</anchor><br />

</p>
</card>
</wml>