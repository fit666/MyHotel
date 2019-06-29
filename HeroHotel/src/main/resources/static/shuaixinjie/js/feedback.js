function but() {


	var fankui = $("#feedback").val();
	var dataJson = {
		"feedback" : fankui
	};
	$.ajax({
		url : "feedback",
		data : dataJson,
		type : "post",
		dataType : "text",
		success : function(data) {
			alert("提交成功");
		},

	});
}

function chongzhi() {
	debugger;
	var money = $("#money").val();
	
	
	if (money < 0) {
		alert("充值数额不能小于零");
		return;
	} else if (money < 3000 && money>0) {
	alert("充值成功");
	}else{
		alert("充值成功，恭喜您成为终生会员！！！");
	}
	var dataJson = {
		"money" : money,
		
	};
	$.ajax({
		url : "chongzhi",
		data : dataJson,
		type : "post",
		dataType : "text",
		success : function(data) {
			
		},
	});
}

//$(function() {
//	$.ajax({
//		url : "showmoney",
//		dataType : "json",
//		type : "post",
//		success : function(data) {
//			//$("#usermsg").empty();
//			var str = "";
//			for (var i = 0; i < data.length; i++) {
//				debugger;
//										var str = "";
//										str += "<tr>";
//										str += "<td style=\"width: 100px\">";
//										str += data[i].id;
//										str += "</td>";
//										str += "<td style=\"width: 100px\">";
//										str += data[i].password;
//										str += "</td>";
//										str += "<td style=\"width: 100px\">";
//										str += data[i].username;
//										str += "</td>";
//										
//										str += "</tr>";
//										$("#usermsg").append(str);
//
//									}
//
//		}

		//	$.each(data,function(i,value){
//				var type = value.usertype;
//				if(type==0){
//					type="普通用户";
//				}else{
//					type="VIP用户";
//				}
//				str+="<p>";
//				str+="用户名："+value.username;
//				str+="账户余额："+value.money;
//				str+="账户类型："+type;
//				str+="</p>";
//			})
			//$("#usermsg").append(str);
	
//	});
//});
$(function() {
	$.ajax({
		url : "showmoney",
		dataType : "json",
		type : "post",
		success : function(data) {
			for (var i = 0; i < data.length; i++) {
debugger;
				var str = "";
				str += "<tr>";
				str += "<td style=\"width: 100px\">";
				str += data[i].username;
				str += "</td>";
				str += "<td style=\"width: 100px\">";
				str += data[i].id;
				str += "</td>";
				str += "<td style=\"width: 100px\">";
				str += data[i].password;
				str += "</td>";
				str += "<td style=\"width: 100px\">";
				str += data[i].usertype;
				str += "</td>";
				str += "</tr>";
				$("#usermsg").append(str);

			}

		}

	});
	});
