//账号登录
function login(){
	// 数据校验
	// 前端格式判断
	var rm=$("#rememberMe").is(':checked'); 
	var i=0;
	if(rm){
		i=1;
	}
	var baccount = $("#account").val();
	var pwd = $("#pass").val();
	if(baccount==""||pwd==""){
		alert("请完善信息");
	}else if(baccount.length<6||baccount.length>20){
		$("#message4").html("账号不合法");
	}else if(pwd.length<6||pwd.length>12){
		$("#message4").html("密码不合法");
	}else if($("#flag").val()!=1) {
		$("#message4").html("请滑动滑块");
	}else{
		$.ajax({
			url:"/user/login",
			type:"post",
			data:{
				account:$("#account").val(),
				password: $("#pass").val(),
				rm:i
			},
		success:function(data){
			if(data!="登录成功"){
				$("#message4").html(data);
			}else{
				location.href="shuaixinjie/bookroom.html";
		}
		}
		})
	}
}

// 手机登录
function login2(){
	// 数据校验
	// 前端格式判断
	var rm=$("#rememberMe").is(':checked'); 
	var i=0;
	if(rm){
		i=1;
	}
	var baccount = $("#tell").val();
	var code = $("#code").val();
	if(baccount==""||code==""){
		alert("请完善信息");
	}else if(baccount.length!=11){
		$("#message4").html("手机号不合法");
	}else {
		$.ajax({
			url:"/user/loginTel",
			type:"post",
			data:{
				tel:$("#tell").val(),
				code:$("#code").val(),
				rm:i
			},
		success:function(data){
			alert(data);
			if(data!="登录成功"){
				$("#message4").html(data);
			}else{
				location.href="index.html";
		}
		}
		})
	}
}
//倒计时  
var countdown=20;  
function settime(val) {
	if(countdown==19){
		getcode();
	}
    if (countdown == 0) {  
        val.removeAttribute("disabled");  
        val.value="获取验证码";  
        countdown = 20;  
        
        return false;  
    } else {  
        val.setAttribute("disabled", true);  
        val.value="重新发送(" + countdown + ")";  
        countdown--;  
    }  
    setTimeout(function() {  
        settime(val);  
    },1000);  
}  

//发送验证码
//获取验证码
function getcode(){
	var btell = $("#tell").val();
	if(btell==""){
		$("#message4").html("请填写手机号");
	}else if(btell.length!=11){
		$("#message4").html("手机号不合法");
	}else{
		$.ajax({
			url:"/user/code",
			type:"post",
			data:{
				tel:$("#tell").val()
			},
			success:function(data){
				$("#message4").html(data);
			}
		})
	}
	
}

