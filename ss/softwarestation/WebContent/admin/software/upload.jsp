<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css"> .file { font: 10pt; color: black; } </style>
<script language="javascript">
var i=0;
      function addFile() {
       i++;

     currRow=conditionTable.insertRow();
     cellc=currRow.insertCell();
     cellcContext= "<input type='file' NAME='file("+(i-1)+")'>&nbsp;&nbsp;<button onclick='removeFile();'>删除</button><br>";
     cellc.innerHTML=cellcContext;
     //alert(cellcContext);

    }
function findTD(o){
if (o.nodeName=="TR"||o.nodeName=="TABLE") return;
if(o.nodeName=="TD")
return (o);
else
return (o.parentElement);
}
function removeFile(){
  o = findTD(event.srcElement);
  //alert(o.parentElement.rowIndex*1);
conditionTable.deleteRow(o.parentElement.rowIndex*1);


}
      function uploadFile(){
            
          document.form1.action="uploadAction.do";  
          document.form1.submit();
      }


</script>
</head>
<body>
<form enctype="multipart/form-data" name="form1" method="post">

      <div id="fileDiv"  style='top:150px;left:0px;overflow-x:auto;overflow-y:auto ;border-style:outset;border-width:1pt;border-color:  black;'>
       <table id=conditionTable border=1  >
</table>
<a href="javascript:void(addFile());" class="file">添加附件</a>  <a href="javascript:void(uploadFile());" class="file">上传附件</a> 
       </div>
</form>
</body>
</html>