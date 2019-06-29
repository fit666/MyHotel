
function reserve(){
	
	 var arr=0;
	  $("input[type='checkbox']:checked").each(function(){ 
	      /* arr.push(this.value);*/
		  arr=this.value;
	  })
	  var datajson = {
		"arr":arr,		
	};
	 
	$.ajax({
		url:"reserve",
		type:"post",
		data:datajson,
		datatype:"json",
		success:function(data){
			
			alert("预定成功！");
			
		}
		
	})
	
}