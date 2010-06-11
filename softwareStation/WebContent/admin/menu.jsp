<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base target="main" >
</head>
<body>
机型管理<br>
<ul>
	<li>操作系统管理
		<ul>
			<li><a href="phone/osList.jsp">操作系统列表</a></li>
			<li><a href="phone/osUpdate.jsp">增加操作系统</a></li>
		</ul>
	</li>
	<li><a href="phone/phoneBrandList.jsp">品牌管理</a></li>
	<li>系列管理
		<ul>
			<li><a href="phone/phoneSeriesList.jsp">系列列表</a></li>
		</ul>
	</li>
</ul>
<a href="software/softwareTypeList.jsp">分类管理</a><br>
软件管理<br>
<ul>
	<li><a href="software/softwareInfoList.jsp">软件列表</a></li>
	<li><a href="software/softwareInfoUpdate.jsp">新增软件</a></li>
	<li><a href="software/softwareInfoUpdate.jsp">修改软件</a></li>
	<li><a href="software/softwareInfoUpdate.jsp">查看软件</a></li>
</ul>
  <a href="data/dataList.jsp">数据管理</a><br>
</body>
</html>