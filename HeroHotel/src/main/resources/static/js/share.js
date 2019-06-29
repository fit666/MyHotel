function show(pcurrentpage){
	 $.ajax({
			url:"/comment/findAll",
			type:"post",
			data:{
				PageNum:pcurrentpage
			},
		 success:function(data){
			 if(data==null||data==""){
				 alert("暂无评论");
			 }else{
				 var div=$("<div  class='guestbook'></div>");
				 for(var i=0;i<data.length;i++){
					 var divnr=$(" <div class='guestbook_nr'></br>");
					var divid=$(" <div style='border-bottom:1px #ddd solid' id='id' >留言编号:"+data[i].id+"</div>");
					 var divmessage=$("<div id='message' >"+data[i].message+"</div>")
					 divnr.append(divid);
					 divnr.append(divmessage);
					 div.append(divnr);
					 var divform=$("<div class='guestbook_form'></div>");
					 var label=$("<label id='name'  >昵称:"+data[i].name +"   </label>");
					 var time=$("<span  style='text-align: right;' id='time' >评论时间:"+data[i].createtime+"</span>");
					 divform.append(label);
					 divform.append(time);
					 div.append(divform);
				 }
				 $("#msg").html(div); 
				 $("#currentpage").html(pcurrentpage); 
				 
			 }
		 }
		 })
	
}
//查找总页
function total(){
	 $.ajax({
			url:"/comment/findtotal",
			type:"post",
			data:{
				
			},
		 success:function(data){
			 if(data==null||data==""){
				 alert("暂无评论");
			 }else{
				 $("#totalpage").html(data);
			 }
		 }
	 })
}

show(1);
total();

//添加评论
function addComment(){
	 $.ajax({
			url:"/comment/addComment",
			type:"post",
			data:{
				message:$("#text").val(),
				name:$("#name").val()
			},
		 success:function(data){
			alert(66);
		 }
	 })
}



/*下一页*/
function nextpage(){
	var currentpage=parseInt($("#currentpage").html());
	var totalpage=parseInt($("#totalpage").html());
	if(totalpage>currentpage){
		show(currentpage+1);
	}
}

/*上一页*/
function backpage(){
	var currentpage=parseInt($("#currentpage").html());
	if(currentpage>1){
		show(currentpage-1);
	}
}
/*首页*/
function firstpage(){
	location.reload();
}

/*末页*/
function lastpage(){
	var totalpage=parseInt($("#totalpage").html());
	show(totalpage);
}