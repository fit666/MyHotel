$(function(){
	$.ajax({
		url:"food",
		type:"post",
		data:"",
		datatype:"json",
		success:function(data){
			var str="";
			for(var j=0;j<data.length;j++){
				//str+="<li><a href='#'><div class='food-name'><p>"+data[j].fname+"</p><p>"+data[j].fdescribe+"</p></div><div class='food-price'><p>￥<span>"+data[j].fprice+"</span></p></div><div class='img-bg'></div><img src='"+data[j].image+"'></a></li>"
			  //str+='<li><a href="#"><div class="food-name"><p>玫瑰香酥西兰花</p><p>Rose fried broccoli</p></div><div class="food-price"><p>￥<span>25</span></p></div><div class="img-bg"></div><img src="imagess/foodlist1.jpg"></a></li>';
				
				"<li><a href='#'><div class='food-name'><p>"+data[j].fname+"</p><p>"+data[j].fdescribe+"</p></div><div class='food-price'><p>￥<span>"+data[j].fprice+"</span></p></div><div class='img-bg'></div><img src='"+data[j].image+"'></a></li>"
				$("#foodlists").append("<li><a href='#'><div class='food-name'><p>"+data[j].fname+"</p><p>"+data[j].fdescribe+"</p></div><div class='food-price'><p>￥<span>"+data[j].fprice+"</span></p></div><div class='img-bg'></div><img src='"+data[j].image+"'></a></li>");
			}
			
			//$("#foodlists").append(str);
			
			//$("#foodlists").trigger("create");
			
		}
	})
	
	
	
	
	$.ajax({
		url:"food",
		type:"post",
		data:"",
		datatype:"json",
		success:function(data){
			var str="";
			for(var j=0;j<data.length;j++){
			
				str+="<tr><td><input type='checkbox' name='vehicle' value='"+data[j].id+"' />选择</td><td>"+data[j].fname+"</td><td>￥"+data[j].fprice+"</td><td>"+data[j].fdescribe+"</td><td><img alt='' src='"+data[j].image+"' height='60px' width='80px'></td></tr>";
			
			}
			$("#fdlist").append(str);
			
				
			$("#fdlist").trigger("create");
		}
	})
	
	
})
	
