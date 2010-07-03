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
</head>
<body>
<div id="main">
	<ul id="browser" class="filetree treeview-famfamfam">
		<li><span class="folder"><a href="main.jsp" target="workCenter" title="管理中心">管理中心</a></span>
			<ul>
				<li><span class="folder">机型管理</span>
					<ul>
						<li><span class="file"><a href="extension!list.action" target="workCenter">扩展名表</a></span></li>
						<li><span class="file"><a href="phoneOs!list.action" target="workCenter">平台列表</a></span></li>
						<!-- 
						<li><span class="file"><a href="phone/phoneOsUpdate.jsp" target="workCenter">增加平台</a></span></li>
						 -->
						<li><span class="file"><a href="phoneBrand!list.action" target="workCenter">品牌列表</a></span></li>
						<!-- 
						<li><span class="file"><a href="phone/phoneBrandUpdate.jsp" target="workCenter">新增品牌</a></span></li>
						 -->
						<li><span class="file"><a href="phoneSeries!list.action" target="workCenter">机型列表</a></span></li>
						<!-- 
						<li><span class="file"><a href="phoneSeries!addInit.action" target="workCenter">新增机型</a></span></li>
						 -->
					</ul>
				</li>
				<li><span class="folder">软件管理</span>
					<ul>
						<li><span class="file"><a href="softwareInfo!list.action?showData=0" target="workCenter">软件列表</a></span></li>
						<li><span class="file"><a href="softwareInfo!addInit.action" target="workCenter">新增软件</a></span></li>
						<li><span class="file"><a href="softwareType!list.action" target="workCenter">分类管理</a></span></li>
						<li><span class="file"><a href="data/lscommentary.action" target="workCenter">评论管理</a></span></li>
						<li><span class="file"><a href="wap/listreport.action" target="workCenter">报错管理</a></span></li>
					</ul>
				</li>
				<li><span class="folder">数据管理</span>
					<ul>
						<li><span class="file"><a href="softwareInfo!list.action?showData=1&promotionWay=3" target="workCenter">数据分析</a></span></li>
						<li><span class="file"><a href="data/account!list.action" target="workCenter">推广费用</a></span></li>
						<li><span class="file"><a href="data/accountType!list.action" target="workCenter">推广类型</a></span></li>
					</ul>
				</li>
			</ul>
		</li>
	</ul>
</div>
</body>
</html>