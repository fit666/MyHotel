
 function tijiao(){
	 
	 
	var aname=$("#aname").val();
	var atext=$("#atext").val();
	if(aname!=null&&aname!=""&&atext!=null&&atext!=""){
		
	
	
	var datajson = {
		"aname":aname,
		"atext":atext
	};
	$.ajax({
		url:"eva1",
		type:"post",
		data: datajson,
		datatype:"json",
		success:function(data){
			$("#msg").empty();
			for(var j=0; j<data.length;j++){
									
				
				var str="<div class='guestbook_lists'><h5>"+data[j].names+"<span>"+data[j].time+"</span></h5><p>"+data[j].evaluate+"</p></div>";
				$("#msg").append(str);
				
				
			}
			
		}

	})
	}
	else if(aname==null||aname==""){
		alert("昵称不得为空！");
	}else if(atext==null||atext==""){
		alert("留言内容不得为空！");
	}
	
}