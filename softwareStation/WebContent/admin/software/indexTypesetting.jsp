<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>I主页排版</title>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
<script type="text/javascript">
 var xmlHttp;
function addFile(ele1,ele2)
 {
	var index_str=document.getElementById(ele1);
	ajaxFunction("index!write.action?fileName="+ele2+"&index_str="+index_str.value);
 }
function ajaxFunction(url){
	try
    {
   // Firefox, Opera 8.0+, Safari
    xmlHttp=new XMLHttpRequest();
    }
 catch (e)
    {
  // Internet Explorer
   try
      {
      xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
      }
   catch (e)
      {
      try
         {
         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
         }
      catch (e)
         {
         alert("您的浏览器不支持AJAX！");
         return false;
         }
      }
    }
 	if(xmlHttp){ 
		 xmlHttp.open("POST",url, true);
		 xmlHttp.setRequestHeader("Content-Type","text/html;charset=UTF-8");
		 xmlHttp.onreadystatechange = complete; 
		 xmlHttp.send(null);
	 } 
}
 function complete(){
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) { 
			//alert(xmlHttp.responseText);
				//document.getElementById("name_error").innerHTML=xmlHttp.responseText;
				if(xmlHttp.responseText==1){
					alert("保存成功");
				}else if(xmlHttp.responseText==2){
					alert("删除成功");
				}else{
					//alert("失败");
					}
			}
	}
}
var divId=1;
      //动态生成单纯的div
      function CreateOuterDiv()
      {
      	//alert(divId);
      	
         // if(document.getElementById("abc").value==1){
      	//	divId=document.getElementById("abc").value;
         // }
      	//alert(divId);
      		var txtName="";
          	var obj=document.createElement("div");
          	obj.id="myDiv"+divId;
	        var ta= document.createElement("textarea");
	        ta.id="new"+divId;
	        txtName=ta.id+".txt";
	        ta.rows="10";
	        ta.cols="50";
       	 	obj.appendChild(ta);
        	var btn=  document.createElement("input");
        	btn.type="button";
        	btn.value="保存到"+txtName;
        	btn.onclick=function(){addFile(ta.id,txtName);};
        	
        	var btn_remove=  document.createElement("input");
        	btn_remove.type="button";
        	btn_remove.value="删除";
        	btn_remove.onclick=function(){moveDiv(obj.id,txtName);};
        	
        	obj.appendChild(btn);
        	obj.appendChild(btn_remove);
         		document.body.appendChild(obj);    
          divId++;
          //alert(divId);
      }
      function moveDiv(ele1,ele2){
	      	ajaxFunction("index!delete.action?fileName="+ele2);
	      	var mydiv=document.getElementById(ele1);
	      	document.body.removeChild(mydiv);
         }
</script>
<style type="text/css">
	div{
		float: left;
		width: 400px;
	}
</style>
</head>
<body>
<div class="page_title">管理中心 &gt; 软件管理&gt; 主页排版</div>
<input type="button" onclick="CreateOuterDiv()" value="创建">
<br>
<c:if test="${empty maps}">
	<div id="myDiv0">
		<textarea rows="10" cols="50" id="index_str0"></textarea>
		<input type="button" value="保存到0.txt" onclick="addFile('index_str0','0.txt')">
		<input type="button" value="删除" onclick="moveDiv('myDiv0','0.txt')">
	</div>
</c:if>
<c:set value="0" var="fileCount"></c:set>
<c:forEach items="${maps}" var="m" varStatus="vs">
	<div id="myDiv${vs.count }">
		<textarea rows="10" cols="50" id="index_str${vs.count }">${m.value }</textarea>
		<input type="button" value="保存到${m.key }" onclick="addFile('index_str${vs.count }','${m.key }')">
		<input type="button" value="删除" onclick="moveDiv('myDiv${vs.count }','${m.key }')">
	</div>
	<c:set value="${vs.count}" var="fileCount"></c:set>
</c:forEach>
<input type="hidden" id="abc" value="${fileCount }">
</body>
</html>