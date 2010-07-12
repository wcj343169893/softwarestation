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
////////////////////////////////////////////////////////////////////////////////
/*
 *--------------- 客户端表单通用验证CheckForm(oForm) -----------------
 * 功能:通用验证所有的表单元素.
 * 使用:
 *    <form name="form1" onsubmit="return CheckForm(this)">
 *    <input type="text" name="id" check="^\S+$" warning="id不能为空,且不能含有空格"/>
 *    <input type="submit"/>
 *    </form>
 * 待实现功能:把正则表式写成个库.
 *--------------- 客户端表单通用验证CheckForm(oForm) -----------------
 */
////////////////////////////////////////////////////////////////////////////////
//主函数
function CheckForm(oForm) {
	var els = oForm.elements;
	// 遍历所有表元素
	for ( var i = 0; i < els.length; i++) {
		// 是否需要验证
		if (els[i].check) {
			// 取得验证的正则字符串
			var sReg = els[i].check;
			// 取得表单的值,用通用取值函数
			var sVal = GetValue(els[i]);
			// 字符串->正则表达式,不区分大小写
			var reg = new RegExp(sReg, "i");
			if (!reg.test(sVal)) {
				// 验证不通过,弹出错误信息
				alert(els[i].warning);
				// 验证不通过,在下一个节点显示错误信息
//				els[i].nextSibling.innerHTML=els[i].warning;
//				alert(els[i].nextSibling.value);
				// 该表单元素取得焦点,用通用返回函数
				GoBack(els[i]);
				return false;
			}
//			else{
//				els[i].nextSibling.innerHTML="*";
//			}
		}
	}
}
//此函数的功能是：返回当前节点的下一个元素节点（而不是下一个节点），参数就是当前节点的下一个节点
function getNextElement(node) {
	  if (node.nextSibling) {
	    return node.nextSibling;
	  }
	  return null;
}

// 通用取值函数分三类进行取值
// 文本输入框,直接取值el.value
// 单多选,遍历所有选项取得被选中的个数返回结果"00"表示选中两个
// 单多下拉菜单,遍历所有选项取得被选中的个数返回结果"0"表示选中一个
function GetValue(el) {
	// 取得表单元素的类型
	var sType = el.type;
	switch (sType) {
	case "text":
	case "hidden":
	case "password":
	case "file":
	case "textarea":
		return el.value;
	case "checkbox":
	case "radio":
		return GetValueChoose(el);
	case "select-one":
	case "select-multiple":
		return GetValueSel(el);
	}
	// 取得radio,checkbox的选中数,用"0"来表示选中的个数,我们写正则的时候就可以通过0{1,}来表示选中个数
	function GetValueChoose(el) {
		var sValue = "";
		// 取得第一个元素的name,搜索这个元素组
		var tmpels = document.getElementsByName(el.name);
		for ( var i = 0; i < tmpels.length; i++) {
			if (tmpels[i].checked) {
				sValue += "0";
			}
		}
		return sValue;
	}
	// 取得select的选中数,用"0"来表示选中的个数,我们写正则的时候就可以通过0{1,}来表示选中个数
	function GetValueSel(el) {
		var sValue = "";
		for ( var i = 0; i < el.options.length; i++) {
			// 单选下拉框提示选项设置为value=""
			if (el.options[i].selected && el.options[i].value != "") {
				sValue += "0";
			}
		}
		return sValue;
	}
}

// 通用返回函数,验证没通过返回的效果.分三类进行取值
// 文本输入框,光标定位在文本输入框的末尾
// 单多选,第一选项取得焦点
// 单多下拉菜单,取得焦点
function GoBack(el) {
	// 取得表单元素的类型
	var sType = el.type;
	switch (sType) {
	case "text":
	case "hidden":
	case "password":
	case "file":
	case "textarea":
		el.focus();
		var rng = el.createTextRange();
		rng.collapse(false);
		rng.select();
	case "checkbox":
	case "radio":
		var els = document.getElementsByName(el.name);
		els[0].focus();
	case "select-one":
	case "select-multiple":
		el.focus();
	}
}