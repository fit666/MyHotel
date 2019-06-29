function insert(id) {
	var money;
	money = prompt("请输入充值金额：[提示：只能输入整数]");
	var check = /(^[1-9]\d*$)/;
	if(money==null){ 
		  return ; 
	}
	if(!check.test(money)){ 
		  alert("输入金额有问题，请检查！"); 
		  return ; 
	}
	
	$.ajax({
		url:"ChongzhiServlet",
		type:"post",
		data:{
			"id":id,
			"money":money
		},
		success:function(data){
			if(data==1){
				alert("充值成功");
			}else{
				alert("充值失败");
			}
		}
	})
	
}