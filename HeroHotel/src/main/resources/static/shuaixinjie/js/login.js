function show() {

	var username = $("#username").val();
	var password = $("#password").val();
	if(username==null||password==null||username==""||password==""){
		alert("用户名和密码均不能为空");
		return;
	}
	var dataJson = {
		"username" : username,
		"password" : password
	};
	/* alert(username+password); */
	$.ajax({
		url : "login",
		data : dataJson,
		type : "post",
		dateType : "text",
		success : function(data) {
			if (data == -1) {
				alert("用户名不存在！");
			} else if (data == 0) {
				Toast("登陆成功", 3000);
				location.href = "index.jsp";
			} else {
				alert("密码错误！");
			}
		}

	});
}
function Toast(msg, duration) {
	duration = isNaN(duration) ? 3000 : duration;
	var m = document.createElement('div');
	m.innerHTML = msg;
	m.style.cssText = "width: 10%;min-width: 150px;opacity: 0.7;height: 30px;color: rgb(255, 255, 255);line-height: 30px;text-align: center;border-radius: 5px;position: fixed;top: 50%;left: 50%;z-index: 999999;background: rgb(0, 0, 0);font-size: 12px;";
	document.body.appendChild(m);
	setTimeout(function() {
		var d = 0.5;
		m.style.webkitTransition = '-webkit-transform ' + d
				+ 's ease-in, opacity ' + d + 's ease-in';
		m.style.opacity = '0';
		setTimeout(function() {
			document.body.removeChild(m)
		}, d * 1000);
	}, duration);
}