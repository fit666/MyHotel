$(function() {
	$("#textbody").load('usermsg.jsp');
	$.ajax({
		url:"../user/userInfo",
		type:"post",
		data:{},
		dataTyoe:"json",
		success:function(data){
			if(data!=null){
				var user=data.user;
				var infos=data.infos;
				var context="";
				$("#account").html(user.account);
				$("#vip").html(user.vip.vname);
				$("#money").html(user.monetary);
				$("#tel").html(user.tel);
				for(var i=0;i<infos.length;i++){
					if(infos[i].tel==user.tel){
						$("#name").html(infos[i].uname);
						$("#card").html(infos[i].idcard);
					}else{
					context+="<option>"+infos[i].uname+"--"+infos[i].sex+"--"+infos[i].tel+"--"+infos[i].idcard+"</option>"
					}
				}
				$("#option").append(context);
			}else{
				alert("登录过期");
			}
		}
	})
})
function showInfo(){
	showBox();
	closeVip();
	closePwd();
	closeOrder();
}
function showOrders(){
	$.ajax({
		url:"../order/allOrders",
		type:"post",
		data:{},
		dataType:"json",
		success:function(data){
			var context="";
			for(var i=0;i<data.length;i++){
				var dat = new Date(data[i].createtime).toJSON().substr(0, 10).replace('T', ' ');
				if(data[i].paynumber==null){
					var pay = "";
				}else{
					var pay = data[i].paynumber.substr(0, 10);
				}
				context+="<tr>"
							+"<td>"+data[i].ordernumber+"</td>"
							+"<td>"+dat+"</td>"
							+"<td>"+data[i].total+"</td>"
							+"<td>"+data[i].payway+"</td>"
							+"<td>"+pay+"</td>"
							+"<td><button class='btn btn-default btn-lg' data-toggle='modal' data-target='#myModal' onclick='addorder("+data[i].ordernumber+")' >评论</button></td>"
							+"<td><button class='btn btn-default btn-lg' onclick='refu("+data[i].ordernumber+",\""+dat+"\")' >退款</button></td>"	
							+"</tr>";
			};
			context=context+"<tr><td colspan='7'></td></tr>";
			$("#orders").html(context);
			closeVip();
			closeBox();
			closePwd();
			showOrder();
			
		}
	})
}
function addorder(a){
	$("#modal-body").css("display", "inline");
	$("#hid").val(a);
}
function reset(){
	$.ajax({
		url:"../user/rpwd",
		type:"post",
		data:{
			pwd:$("#password").val()
		},
		success:function(data){
			alert(data);
		}
	})
}
function addInfo(){
	$.ajax({
		url:"../info/addInfo",
		type:"post",
		data:{
			"uname":$("#uname").val(),
			"sex":$(':radio[name="sex"]:checked').val(),
			"idcard":$("#idcard").val(),
			"tel":$("#utel").val()
		},
		success:function(data){
			alert(data);
			if(data=="添加成功"){
				window.location.reload();
			}
		}
	})
}
function showVips(){
	$.ajax({
		url:"../vip/allVips",
		type:"post",
		data:{},
		dataType:"json",
		success:function(data){
			var context="";
			for(var i=0;i<data.length;i++){
				context+="<tr>"
							+"<td>"+data[i].vname+"</td>"
							+"<td>"+data[i].vmoney+"</td>"
							+"<td>"+data[i].discount+"折</td>"
						+"</tr>";
			}
			context=context+"<tr><td colspan='3'></td></tr>";
			$("#vips").html(context);
			closeOrder();
			closeBox();
			closePwd();
			showVip();
		}
	})
}
function refu(a,c){
	var time1 = new Date();
	var time = new Date(time1).toJSON().substr(0, 10).replace('T', ' ').replace(/\-/g,"");
		//time1.replace(/\//g,"\-");
	var time2=c.replace(/\-/g,"");
	if(time<time2){
		alert("订单已经超时");
	}else{
		window.location.href="../pay/refund?ordernumber="+a;
	}
}
function comment(){
	$.ajax({
		url:"../comment/addComment",
		type:"post",
		data:{
			"message":$("#msg").val(),
			"ordernumber":$("#hid").val()
		},
		success:function(data){
			if(data="评论成功"){
				var a =confirm("是否跳转评论页面");
				if(a){
					window.location.href="../share.html";
				}else{
					
				}
			}else{
				alert(data);
			}
		}
	})
}
function showOrder(){
	$("#order").fadeIn("fast");
}
function showBox(){
	$("#info").fadeIn("fast");
}
function showPwd(){
	closeBox();
	closeOrder();
	closeVip();
	$("#pwd").fadeIn("fast");
}
function showVip(){
	$("#vipi").fadeIn("fast");
}
function closeOrder(){
	$("#order").fadeOut("fast");
	
}
function closeBox(){
	$("#info").fadeOut("fast");
}
function closePwd(){
	$("#pwd").fadeOut("fast");
}
function closeVip(){
	$("#vipi").fadeOut("fast");
	
}
