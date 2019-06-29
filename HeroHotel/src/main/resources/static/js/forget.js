//获取验证码
function getcode(){
	var btell = $("#tel").val();
	if(btell==""){
		$("#message4").html("请填写手机号");
	}else if(btell.length!=11){
		$("#message4").html("手机号不合法");
	}else{
		$.ajax({
			url:"/user/code",
			type:"post",
			data:{
				tel:$("#tel").val()
			},
			success:function(data){
				$("#message4").html(data);
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

//提交
function commit(){
	alert(66);
	//前端数据校验
	var baccount = $("#account").val();
	var pwd = $("#pass").val();
	var btell = $("#tel").val();
	var code = $("#code").val();
	if(baccount==""||pwd==""||btell==""||code==""){
		alert("请完善信息");
	}else if(baccount.length<6||baccount.length>20){
		$("#message1").html("账号不合法");
	}else if(pwd.length<6||pwd.length>12){
		$("#message2").html("密码不合法");
	}else if(btell.length!=11){
		$("#message3").html("手机号不合法");
	}else{
		$.ajax({
			url:"/user/forget",
			type:"post",
			data:{
				account:$("#account").val(),
				password: $("#pass").val(),
				tel:$("#tel").val(),
				code:$("#code").val()
			},
		success:function(data){
			alert(data);
			if(data!="修改成功"){
				$("#message4").html(data);
			}else{
				location.href="login.html";
		}
		}
		})
	}
}
