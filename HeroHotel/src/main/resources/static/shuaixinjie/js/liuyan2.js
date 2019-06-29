
 $(document).ready(function(){
	 $.ajax({
			url:"evaluate",
			type:"post",
		data:"",
			datatype:"json",
			success:function(data){
				for(var j=0; j<data.length;j++){
					var str="<div class='guestbook_lists'><h5>"+data[j].names+"<span>"+data[j].time+"</span></h5><p>"+data[j].evaluate+"</p></div>";
					$("#msg").append(str);
				}
				
			}
				
		})

 })
 
 
 
 
 