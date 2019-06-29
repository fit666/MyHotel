function checkpwd() {
	var oldpwd = $("#pwd1").val();
	var newpwd = $("#pwd2").val();
	var chnewpwd = $("#pwd3").val();
	
	if(oldpwd==null||newpwd==null||chnewpwd==null||oldpwd==""||newpwd==""||chnewpwd==""){
		alert("所有项均不能为空！");
		return ;
	}
	if(newpwd!=chnewpwd){
		alert("两次输入的新密码不一致！");
		return ;
	}
	
	$.ajax({
		url:"UserUpdatePwdServlet",
		type:"post",
		data:{
			"oldpwd":oldpwd,
			"newpwd":newpwd
		},
		success:function(data){
			if(data==33){
				alert("您输入的原密码有误！");
			}else if(data==1){
				alert("密码修改成功！请重新登录！");
				location.href="/Hotel/UserReLoginServet";
			}else{
				alert("对不起，由于未知原因，密码修改失败！");
			}
		}
		
	})
	
}