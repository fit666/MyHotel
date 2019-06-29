formValidate({
	actOnCla : '.valid_ipt', // 作用于什么选择器上 推荐标签：输入框input 下拉框select
								// 文本区域textarea
	isStartVali : false, // 是否页面加载后立即验证一次
	subBtn : '#sub_btn', // 提交按钮，可以是a标签
	subBtn_gray : true, // 提交后按钮是否进入等待状态
	subBtn_gray_img : 'images/wait_ico.gif', // 按钮等待状态图标（如果是空文本则不加图标）
	other_valiFunc : function() {
		// 其他验证条件 加入非表单验证的其他布尔函数。比如日期。如果表单提交超过指定日期，也是不能提交的！
		// 两个条件都必须满足的话可以： return dateJudge()&&dateJudge1();
		return yes_rule();
	},
	success_func : function() {
		// 通过验证后执行
//		location.href = "注册提示.html";
	}
});

// 表单的其他验证方法
// 验证密码方法
function ensure_pass(id) {
	var cla = $('#' + id);
	if (cla.val() == $('#pass').val()) {
		return true;
	} else
		return false;
}

// 其他验证条件 加入非表单验证的其他布尔函数。比如日期。如果表单提交超过指定日期，也是不能提交的！
// 是否同意条款
function yes_rule() {
	if ($('#yes_rule').is(':checked')) {
		return true;
	} else {
		prompt_frm('您还没有同意瑞祺酒店会员《注册服务条款》', 0)
		return false;
	}
}

function show() {

	var txt = document.getElementById("username");

	var pwd = document.getElementById("pass");

	var supwd = document.getElementById("ensure_pass");

	if (txt.value.length < 5 || txt.value.length > 15) {
		alert("用户名不合法");
		return false;
	}
	if (pwd.value.length < 6 || pwd.value.length > 12) {
		alert("密码设置不合法");
		return false;
	}
	if (pwd.value !== supwd.value) {
		alert("两次密码输入不一致");
		return false;
	} 
	if(yes_rule()){
		reg();
	}
}
function reg() {
	var username = $("#username").val();
	var password = $("#pass").val();
	var dataJson = {
		"username" : username,
		"pass" : password
	};

	$.ajax({
		url : "register",
		data : dataJson,
		dataType : "text",
		type : "post",
		success : function(data) {
			if(data==1){
				location.href = "regsuccess.jsp?name="+username;
			}else{
				alert("对不起！由于未知原因，本次注册失败！");
			}
		}
	});
}