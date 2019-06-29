
function openvip(money) {
	var check = $("input[type='checkbox']").is(':checked');
	if(!check){
		alert("请您先详细阅读并同意《酒店VIP服务须知》");
		return;
	}
	var con;
	con=confirm("你确定要开通年费VIP吗？这将消费￥888.00!"); //在页面上弹出对话框
	if(con==false){
		return;
	}
	if(money<888){
		alert("对不起，您的余额不足！当前余额：￥"+money);
		return;
	}
	$.ajax({
		url:"OpenVipServlet",
		type:"post",
		success:function(data){
			if(data==1){
				alert("恭喜您成为我们的年费会员，快去享受VIP专属特权吧！");
			}else{
				alert("对不起，由于未知原因，开通失败！");
			}
		}
	})
}