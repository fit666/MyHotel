//获取验证码
function getcode(){
	alert(66);
}

//注册
function register(){
	//前端格式判断
	var baccount = $("#account").val();
	var pwd = $("#pass").val();
	var supwd = $("#ensure_pass").val();
	var btell = $("#tell").val();
	var code = $("#code").val();
	if(baccount==""||pwd==""||supwd==""||btell==""||code==""){
		alert("请完善信息");
	}else if(baccount.length<6||baccount.length>20){
		$("#message1").html("账号不合法");
	}else if(pwd.length<6||pwd.length>12){
		$("#message2").html("密码不合法");
		if(supwd!=pwd){
			$("#message2").html("两次密码不一致");
		}
	}else if(btell.length!=11){
		$("#message3").html("手机号不合法");
	}else {
		$.ajax({
			url:"/account/register",
			type:"post",
			data:{
				account:$("#account").val(),
				password: $("#pass").val(),
				tell:$("#tell").val()
			},
		success:function(data){
			alert(data);
			if(data!="注册成功"){
				$("#message4").html(data);
			}else{
				alert(77);
				location.href="login.html";
		}
		}
		})
	}
}