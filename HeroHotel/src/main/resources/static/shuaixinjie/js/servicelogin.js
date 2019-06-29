function login() {
	var aname = $("#aname").val();
	var apassword = $("#apassword").val();
	var datajson = {
		"aname" : aname,
		"apassword" : apassword
	};
	$.ajax({
		url : "adminlogin",
		type : "post",
		data : datajson,
		dataType : "text",
		success : function(data) {
			if(data==-1){
				alert("用户名错误");
			}else if(data==0){
				location.href="/Hotel/service/index.jsp";
			}else{
				alert("密码错误");
			}
		}
	});
}
