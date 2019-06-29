function show(){
	
	debugger;
	var realname=$("#realname").val();
	var telphone=$("#telphone").val();
	var cardid=$("#cardid").val();
	var sex=$("#Sex").val();
	
	var dataJson={"realname":realname,"telphone":telphone,"cardid":cardid,"Sex":sex};
	
	$.ajax({
		url:"SYN",
		data:dataJson,
		type:"post",
		dataType:"text",
		success:function(data){
			alert("认证成功");
			
		},
	});
}