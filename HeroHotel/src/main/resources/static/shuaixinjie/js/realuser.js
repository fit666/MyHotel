function reall() {
	var realname = $("#realname").val();
	var cardid = $("#cardid").val();
	if(realname==null||realname==""){
		alert("姓名为必填项！");
		return false;
	}
	if(cardid==null||cardid==""){
		alert("身份证号码为必填项！");
		return false;
	}
	var regName =/^[\u4e00-\u9fa5]{2,4}$/; 
	if(!regName.test(realname)){ 
	  alert("真实姓名填写有误"); 
	   return false; 
	}
	var regIdNo = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
	if(!regIdNo.test(cardid)){ 
	  alert("身份证号填写有误"); 
	  return false; 
	}
	return true;
}