<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="st" uri="/struts-tags"%>
 <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="cn.common.util.Config"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改软件信息</title>
<link rel="stylesheet" href="/admin/css/style.css" />
<script type="text/javascript" src="/admin/script/common.js"></script>
	<script language="javascript">
		function create()
			{
				var rows=document.getElementById("rows").value;
				if(rows>0){
				var phoneOs_s=document.getElementById("phoneOs_s").value;
				var flag=true;
				var data = "";
				var phoneOs_s2=phoneOs_s.split(",");
				data += "<table class='query_form_table' id='conditionTable'><tbody>";
				var ids=0;
				for(var i=0;i<rows;i++)
				{
					data += "<tr id='tr"+i+"'>";
					data+="<th>软件:</th>";
					data+="<th>平台:</th>";
					data+="<td>";
					data+="<input type='checkbox' id='all"+ids+"' class='clazz"+i+"' onclick=\"checks('clazz"+i+"',this,1)\"/><label for='all"+ids+"'>全选</label>";
					data+="</td>";
					data+="<td>";
					var indexs=1;
					for(var j=0;j<phoneOs_s2.length;j++){
						if(j%2==0){
							data+="<input type='checkbox' value='"+i+"_"+phoneOs_s2[j]+"' id='cb"+ids+"' class='clazz"+i+"' name='softwareForm.phoneOs'/>"+"<label for='cb"+ids+"'>"+phoneOs_s2[j+1]+"</label>";
							if(indexs%3==0){
								data+="<br/>";
							}
							indexs++;
							ids++;
						}
					}
				
					data+="<th>文件:</th>";
					data+="<td><input type='file' name='softwareForm.upload'/>&nbsp;&nbsp;<input type='button' onclick='removeFile();' value='删除'></td>";
					data += "</tr>";
				}
				data += "</tbody></table>";
				//alert(data);
				document.getElementById("softwares").innerHTML = data;
				}
			}
		function findTD(o){
			if (o.nodeName=="TR"||o.nodeName=="TABLE") 
				return;
			if(o.nodeName=="TD")
				return (o);
			else
				return (o.parentElement);
			}
		function removeFile(){  
			o = findTD(event.srcElement);  
			//alert(o.parentElement.rowIndex*1);
			var number=document.getElementById("rows");
			//alert(number);
			number.value=number.value-1;
			//alert(number.value);
			conditionTable.deleteRow(o.parentElement.rowIndex*1);
		}
		function addFile(){
			var numb=document.getElementById("number").value;
			var rows=document.getElementById("rows").value;
			if(rows>0){
			var phoneOs_s=document.getElementById("phoneOs_s").value;
			var flag=true;
			var data = "";
			var phoneOs_s2=phoneOs_s.split(",");
			data += "<table class='query_form_table' id='conditionTable'><tbody>";
			var ids=0;
			for(var i=0;i<rows;i++)
			{
				data += "<tr id='tr"+i+"'>";
				data+="<td>新增软件:</td>";
				data+="<td>";
				data+="<input type='checkbox' id='all"+ids+"' class='clazz"+i+"' onclick=\"checks('clazz"+i+"',this,1)\"/><label for='all"+ids+"'>全选</label>";
				data+="</td>";
				data+="<td>";
				var indexs=1;
				for(var j=0;j<phoneOs_s2.length;j++){
					if(j%2==0){
						data+="<input type='checkbox' value='"+(numb+i)+"_"+phoneOs_s2[j]+"' id='cb"+ids+"' class='clazz"+i+"' name='softwareForm.phoneOs'/>"+"<label for='cb"+ids+"'>"+phoneOs_s2[j+1]+"</label>";
						if(indexs%3==0){
							data+="<br/>";
						}
						indexs++;
						ids++;
					}
				}
				data+="</td>";
				data+="<td><input type='file' name='softwareForm.upload'/>&nbsp;&nbsp;</td><td><input type='button' onclick='removeFile();' value='删除'></td>";
				data += "</tr>";
			}
			data += "</tbody></table>";
			//alert(data);
			document.getElementById("softwares_update").innerHTML = data;
			numb=parseInt(numb)+parseInt(rows);
			//alert(numb);
			}
		}
		function sssignment(el,o){
			var element =	document.getElementById(el);
			element.value=o.value;
			//alert(element.value);
			}
		function checks(classNames,elem,indexs){
			var myform=document.getElementById("softwareInfoForm"+indexs);
			for(var i=0;i<myform.length;i++){
				var e=myform.elements[i];
				if(e.type=="checkbox" && e.className==classNames){
					if (elem.checked) {
						//e.disabled="";
						e.checked=true;
					} else {
						e.checked=false;
						//e.disabled="disabled";
					}
				}	
			}
		}
			
</script>
<script type="text/javascript">
 var xmlHttp;
function ajaxFunction()
 {
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
 var name=document.getElementById("name");
 	if(xmlHttp){ 
		 xmlHttp.open("POST","softwareInfo!add_name.action?name="+name.value, true);
		 xmlHttp.setRequestHeader("Content-Type","text/html;charset=UTF-8");
		 xmlHttp.onreadystatechange = complete; 
		 xmlHttp.send(null);
	 } 
 }
 function complete(){
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) { 
			//alert(xmlHttp.responseText);
				document.getElementById("name_error").innerHTML=xmlHttp.responseText;
			}
	}
}
</script>
</head>
<body>
<input type="hidden" value="${phoneOs_s }" id="phoneOs_s"/>
<c:choose>
	<c:when test="${softwareInfo eq null}">
	<div class="page_title">管理中心 &gt; 新增软件信息</div>
	<div class="button_bar">
	</div>
	<br/>
	<form action="softwareInfo!add.action" method="post" enctype="multipart/form-data" id="softwareInfoForm1">
	<table class="query_form_table">
			<tr>
				<th>文件数量:</th>
				<td colspan="3"><input id="rows" name="softwareForm.number"/>
					<input type="button" onclick="create()" value="设置"/>
				</td>
			</tr>
			<tr>
				<th>软件全称:</th><td colspan="3"><input name="softwareForm.name" id="name" onblur="ajaxFunction()"/><span id="name_error" class="red_test"></span> </td>
				<!-- 
				<th>软件简称:</th><td><input name="softwareForm.shortName"/></td>
				 -->
			</tr>
			<tr>
				<th>分类:</th>
				<td>
					<select name="softwareTypeId">
						<c:forEach items="${softwareTypeList }" var="softwareType">
							<option value="${softwareType.id }">${softwareType.name }</option>
						</c:forEach>
					</select>
				</td>
				<th>安全:</th>
				<td>
					<input type="text" name="softwareForm.safety" value="无病毒/无暗扣/无插件" />
				</td>
			</tr>
			<tr>
				<th>资费:</th>
				<td>
					<select name="softwareForm.traffic">
						<option value="0">使用完全免费</option>
						<option value="1">共享软件</option>
					</select>
				</td>
				<th>提示:</th>
				<td>
					<select name="softwareForm.prompt">
						<option value="无提示">无提示</option>
						<option value="需要签证后才能安装">需要签证后才能安装</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>开发商:</th>
				<td>
					<input type="text" name="softwareForm.producer" value="">
				</td>
				<th>是否允许重命名:</th>
				<td>
					<input type="radio" name="softwareForm.isRename"  value="1" id="isRenameY">
					<label for="isRenameY">是</label>
					<input type="radio" name="softwareForm.isRename" checked="checked" value="0" id="isRenameN">
					<label for="isRenameN">否</label>
				</td>
			</tr>
			<tr>
			<!-- 
				<th>是否置顶:</th>
				<td>
					<input type="radio" name="softwareForm.tops"  value="1" id="topsY">
					<label for="topsY">是</label>
					<input type="radio" name="softwareForm.tops" checked="checked" value="0" id="topsN">
					<label for="topsN">否</label>
				</td>
			 -->
				<th>是否推荐:</th>
				<td>
					 <input  name="softwareForm.tops" type="hidden"  value="0"/>
					  <input  name="softwareForm.plusFine" type="hidden"  value="0"/>
					<input type="radio" name="softwareForm.recommend" checked="checked" value="1" id="recommendY">
					<label for="recommendY">是</label>
					<input type="radio" name="softwareForm.recommend" value="0" id="recommendN">
					<label for="recommendN">否</label>
				</td>
				<!-- 
			</tr>
			<tr>
				 -->
				<th>是否显示:</th>
				<td>
					<input type="radio" name="softwareForm.isShow" checked="checked" value="1" id="isShowY">
					<label for="isShowY">是</label>
					<input type="radio" name="softwareForm.isShow" value="0" id="isShowN">
					<label for="isShowN">否</label>
				</td>
				<!-- 
				<th>是否加精:</th>
				<td>
					<input type="radio" name="softwareForm.plusFine" checked="checked" value="1" id="plusFineY">
					<label for="plusFineY">是</label>
					<input type="radio" name="softwareForm.plusFine" value="0" id="plusFineN">
					<label for="plusFineN">否</label>
				</td>
				 -->
			</tr>
			<tr>
				<th>推广方式:</th>
				<td>
					<select name="softwareForm.promotionWay">
						<option value="0">提成</option>
						<option value="1">免费</option>
					</select>
				</td>
				<th>截图:</th>
				<td>
					<input type="file" name="softwareForm.image">
				</td>
			</tr>
			<tr>
				<th>简介:</th>
				<td colspan="3">
					<textarea rows="10" cols="40" name="softwareForm.description"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="softwares">
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value="新增"/>
				</td>
			</tr>
		</table>
	</form>
	</c:when>
	<c:otherwise>
	<div class="page_title">管理中心 &gt; 修改软件信息</div>
	<div class="button_bar">
	</div>
	<br/>
		<form action="softwareInfo!edit.action" method="post" enctype="multipart/form-data" id="softwareInfoForm2">
			<table class="query_form_table">
			<tr>
				<th>文件数量:</th>
				<td colspan="3">
				<c:set value="${fn:length(softwareInfo.softwareList)}" var="softwareNumbers"></c:set>
					<input id="number" type="hidden"  name="" value="${softwareNumbers }"/>
					<input id="rows" value="1"/>
					<input type="button" onclick="addFile()" value="增加"/>
				</td>
			</tr>
			<tr>
				<th>软件名称:</th>
				<td colspan="3">
					<input name="softwareForm.name" value="${softwareInfo.name }"/>
					<input type="hidden" value="${softwareInfo.id }" name="softwareForm.id">
				</td>
				<!-- 
				<th>软件简称:</th><td><input name="softwareForm.shortName" value="${softwareInfo.shortName }"/></td>
				 -->
			</tr>
			<tr>
				<th>分类:</th>
				<td>
					<select name="softwareTypeId">
						<c:forEach items="${softwareTypeList }" var="softwareType">
							<option value="${softwareType.id }" <c:if test="${softwareInfo.softwareType.id==softwareType.id }">selected</c:if>>${softwareType.name }</option>
						</c:forEach>
					</select>
				</td>
				<th>安全:</th>
				<td>
					<input type="text" name="softwareForm.safety" value="无病毒/无暗扣/无插件" value="${softwareInfo.safety }"/>
				</td>
			</tr>
			<tr>
				<th>资费:</th>
				<td>
					<select name="softwareForm.traffic">
						<option value="0" <c:if test="${softwareInfo.traffic == 0}">selected</c:if>>使用完全免费</option>
						<option value="1" <c:if test="${softwareInfo.traffic == 1}">selected</c:if>>共享软件</option>
					</select>
				</td>
				<th>提示:</th>
				<td>
					<select name="softwareForm.prompt">
						<option value="无提示" <c:if test="${softwareInfo.prompt eq '无提示'}">selected</c:if>>无提示</option>
						<option value="需要签证后才能安装" <c:if test="${softwareInfo.prompt eq '需要签证后才能安装'}">selected</c:if>>需要签证后才能安装</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>开发商:</th>
				<td>
					<input type="text" name="softwareForm.producer" value="${softwareInfo.producer }" maxlength="180">
				</td>
				<th>是否允许重命名:</th>
				<td>
					<input type="radio" name="softwareForm.isRename" <c:if test="${softwareInfo.isRename == 1}">checked</c:if> value="1" id="isRenameY">
					<label for="isRenameY">是</label>
					<input type="radio" name="softwareForm.isRename" <c:if test="${softwareInfo.isRename == 0}">checked</c:if> value="0" id="isRenameN">
					<label for="isRenameN">否</label>
				</td>
			</tr>
			<tr>
			<!-- 
				<th>是否置顶:</th>
				<td>
					<input type="radio" name="softwareForm.tops"  <c:if test="${softwareInfo.tops == 1}">checked</c:if> value="1" id="topsY">
					<label for="topsY">是</label>
					<input type="radio" name="softwareForm.tops"  <c:if test="${softwareInfo.tops == 0}">checked</c:if> value="0" id="topsN">
					<label for="topsN">否</label>
				</td>
			 -->
				<th>是否推荐:</th>
				<td> <input  name="softwareForm.tops" type="hidden"  value="0"/>
					  <input  name="softwareForm.plusFine" type="hidden"  value="0"/>
					<input type="radio" name="softwareForm.recommend" <c:if test="${softwareInfo.recommend == 1}">checked</c:if> value="1" id="recommendY">
					<label for="recommendY">是</label>
					<input type="radio" name="softwareForm.recommend" <c:if test="${softwareInfo.recommend == 0}">checked</c:if> value="0" id="recommendN">
					<label for="recommendN">否</label>
				</td>
				<!-- 
			</tr>
			<tr>
				 -->
				<th>是否显示:</th>
				<td>
					<input type="radio" name="softwareForm.isShow" <c:if test="${softwareInfo.isShow == 1}">checked</c:if> value="1" id="isShowY">
					<label for="isShowY">是</label>
					<input type="radio" name="softwareForm.isShow"  <c:if test="${softwareInfo.isShow == 0}">checked</c:if> value="0" id="isShowN">
					<label for="isShowN">否</label>
				</td>
				<!-- 
				<th>是否加精:</th>
				<td>
					<input type="radio" name="softwareForm.plusFine" <c:if test="${softwareInfo.plusFine == 1}">checked</c:if> value="1" id="plusFineY">
					<label for="plusFineY">是</label>
					<input type="radio" name="softwareForm.plusFine" <c:if test="${softwareInfo.plusFine == 0}">checked</c:if> value="0" id="plusFineN">
					<label for="plusFineN">否</label>
				</td>
				 -->
			</tr>
			<tr>
				<th>推广方式:</th>
				<td colspan="3">
					<select name="softwareForm.promotionWay">
						<option value="0" <c:if test="${softwareInfo.promotionWay == 0}">selected</c:if>>提成</option>
						<option value="1" <c:if test="${softwareInfo.promotionWay == 1}">selected</c:if>>免费</option>
					</select>
					
				</td>
			</tr>
			<tr>
				<th>截图:</th>
				<td>
				 <img alt="" src="/upload/image/${softwareInfo.id }/${softwareInfo.imgPath }" width="80px" height="80px"><br>
				<input type="file" name="softwareForm.image">
				<input type="hidden" name="softwareForm.imgPath" value="${softwareInfo.imgPath }">
				</td>
				<th>简介:</th>
				<td>
					<textarea rows="10" cols="40" name="softwareForm.description">${softwareInfo.description }</textarea>
				</td>
			</tr>
			
			</table>
			
			<br/>
				<table class='query_form_table'>
				<tr>
					<th>编号</th>
					<th colspan="2">支持平台</th>
					<th>软件地址</th>
					<th>操作</th>
				</tr>
				<c:if test="${softwareNumbers > 0}">
					<c:forEach items="${softwareInfo.softwareList }" var="software" varStatus="status">
						<tr>
							<td>
								${software.id }
								<input type="hidden" value="${status.index }_${software.id }" name="softwareForm.softwareId">
							</td>
							<td >
								<input type="checkbox" onclick="checks('old_${status.index }',this,2)" id="f${status.index }"><label for="f${status.index }">全选</label>
							</td>
							<td>
								<c:forEach items="${phoneOsList }" var="os" varStatus="s">
								<c:set value="" var="isChecked"></c:set>
									<c:forEach items="${software.phoneOsList}" var="myos">
										<c:choose>
											<c:when test="${os.id == myos.id }">
												<c:set value="checked" var="isChecked"></c:set>
											</c:when>
										</c:choose>
									</c:forEach>
									<input type="checkbox" name="softwareForm.phoneOs_update" class="old_${status.index }" id="old_${status.index }_${s.index }" value="${status.index }_${os.id }" ${isChecked }/><label for="old_${status.index }_${s.index }">${os.name }</label>
									<c:if test="${s.count %3 == 0 }"><br/> </c:if>
								</c:forEach>
							</td>
							<td>${software.downloadPath }
								<input type="hidden" value="${software.downloadPath }" name="softwareForm.sufn" id="${status.index }_${software.id }">
							<br><input type="file" name="softwareForm.upload_update" onchange="sssignment('${status.index }_${software.id }',this)"/>
							</td>
							<td><input type="button" onclick="del('software!delete.action?id=${software.id }&id2=${softwareInfo.id }')" value="删除"></td>
						</tr>
					</c:forEach>
					</c:if>
				</table>
				<br/>
			<div id="softwares_update">
			</div>
			<p align="center">
			<input type="submit" value="确定修改"/>
			</p>
		</form>
	</c:otherwise>
</c:choose>
</body>
</html>