	var pageindex=1;//当前页
	var pagecount=1;//总页数
	var msgnumber=0;//总数据量
	var pagesize=5;//每页数据条数
	$(function() {
		
		getUserList();
		
		$.ajax({
			url:"UserFoodOrderCountServlet",
			type:"POST",
			dataType:"text",
			success:function(data){
				msgnumber = parseInt(data);
				pagecount = (msgnumber%pagesize==0)?msgnumber/pagesize:Math.ceil(msgnumber/pagesize);
			}
		});
	});
	function swtichpage(param) {
		switch (param) {
		case 0:
			if(pageindex!=1){
				pageindex=1;
				getUserList();
			}
			break;

		case 1:
			if(pageindex>1){
				pageindex--;
				getUserList();
			}
			break;
			
		case 2:
			if(pageindex<pagecount){
				pageindex++;
				getUserList();
			}
			break;
			
		case 3:
			if(pageindex!=pagecount){
				pageindex=pagecount
				getUserList();
			}
			break;
		}
	};
	//获取单页数据
	function getUserList() {
		var userlist = $("#tbody");
		$.ajax({
			url:"UserFoodOrderLimitServlet",
			type:"POST",
			data:{"pageindex":pageindex,"pagesize":pagesize},
			dataType:"json",
			success:function(data){
				userlist.empty();
				var item = "";
				$.each(data, function(i, order) {
					var state = order.status;
					switch(state){
					case 0:
						state="待用餐";
						break;
					case 1:
						state="已用餐";
						break;
					case 2:
						state="已退单";
						break;
					case 3:
						state="退单中";
						break;
					}
					item += "<tr>";
					item += "<td>"+order.id+"</td><td>"+order.time+"</td>";
					item += "<td>"+order.roomid+"</td><td>"+order.foodid+"</td>";
					item += "<td>"+order.fprice+"</td><td>"+order.mealtime+"</td><td>"+state+"</td>";
					item += "<td>";
					if(order.status==0){
						item += "<button class='layui-btn layui-btn-xs layui-btn-normal' onclick='tuidan("+order.id+")'>退单</button>";
					}else{
						item += "<button disabled='disabled'>退单</button>";
					}
					item += "</td>";
					item += "</tr>";
				});
				userlist.append(item);
				$("#fontNum").text("当前第"+pageindex+"页 ,共"+pagecount+"页,"+msgnumber+"条记录");
			}
		});
	};
	
	function tuidan(id) {
		var con;
		con=confirm("你确定要退掉此订单吗?订单号："+id); //在页面上弹出对话框
		if(con==false){
			return;
		}
		$.ajax({
			url:"BackFoodOrderServlet",
			type:"post",
			data:{"id":id},
			dataType:"text",
			success:function(data){
				if(data==1){
					alert("退单申请已提交！")
				}else{
					alert("对不起，由于未知原因，退单申请失败！")
				}
			}
		})
	}
	