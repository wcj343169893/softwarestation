function reload(){
	window.location.reload();
}
function help(msg){
	alert('欢迎使用'+msg);
}

function to(url){
	window.location.href=url;
}
function back(){
	history.go(-1);
}
function save(url){
	alert('保存成功！');
	to(url);
}
function add(url){
	alert('新增成功！');
	to(url);
}
function del(url){
	if (window.confirm("确定删除?")){
		to(url);
	}
	return false;
}
function mouseOvers(obj){
	//obj.style.background="#E3E3E3";
	//alert(obj.style.background);
}
function mouseOuts(obj){
	//obj.style.background="#3399CC";
}
function setCurTime(oid){
	var now=new Date();
	var year=now.getYear();
	var month=now.getMonth();
	var day=now.getDate();
	var hours=now.getHours();
	var minutes=now.getMinutes();
	var seconds=now.getSeconds();
	var timeString = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
	var oCtl = document.getElementById(oid);
	oCtl.value = timeString;
}