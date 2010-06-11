<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/jquery.treeview.css" />
	<script src="script/jquery.js" type="text/javascript"></script>
	<script src="script/jquery.treeview.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#browser").treeview();//加载tree
	});
	</script>
	<style type="text/css">
		*{font-size: 12px;}
	
		a{
			text-decoration: none;
			color: black;
		}
	</style>
<base target="main" >
</head>
<body>
<div id="main">
	<ul id="browser" class="filetree treeview-famfamfam">
		<li><span class="folder"><a href="main.jsp" target="main" title="管理中心">管理中心</a></span>
			<ul>
				<li class="closed"><span class="folder">操作系统管理</span>
					<ul>
						<li><span class="file"><a href="phone/osList.jsp">操作系统列表</a></span></li>
						<li><span class="file"><a href="phone/osUpdate.jsp">增加操作系统</a></span></li>
					</ul>
				</li>
				<li><span class="folder">品牌管理</span>
					<ul>
						<li><span class="file"><a href="phone/phoneBrandList.jsp">品牌列表</a></span></li>
					</ul>
				</li>
				<li><span class="folder">系列管理</span>
					<ul>
						<li><span class="file"><a href="phone/phoneSeriesList.jsp">系列列表</a></span></li>
					</ul>
				</li>
				<li><span class="folder">软件管理</span>
					<ul>
						<li><span class="file"><a href="software/softwareInfoList.jsp">软件列表</a></span></li>
						<li><span class="file"><a href="software/softwareInfoUpdate.jsp">新增软件</a></span></li>
						<li><span class="file"><a href="software/softwareInfoUpdate.jsp">修改软件</a></span></li>
						<li><span class="file"><a href="software/softwareInfoUpdate.jsp">查看软件</a></span></li>
					</ul>
				</li>
				<li><span class="folder">分类管理</span>
					<ul>
						<li><span class="file"><a href="software/softwareTypeList.jsp">分类列表</a></span></li>
					</ul>
				</li>
				<li><span class="folder">数据管理</span>
					<ul>
						<li><span class="file"> <a href="data/dataList.jsp">数据信息</a></span></li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</div>
</body>
</html>