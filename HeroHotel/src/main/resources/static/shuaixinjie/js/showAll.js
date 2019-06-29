/**
 * 
 */
function bookroom(){
	window.location.href = "bookroom.html";
}
$(function(){
	$.ajax({
		url:"/housetype/showAll",
		type:"post",
		data:{},
		dataType:"json",
		success:function(data){
			var context ="";
			for(var i=0;i<data.length;i++){
				context+="<div class='one-fourth travel'>"
                 +"<div class='post-item'>"
                     +"<div class='image-place'>"
                        + " <img src='"+data[i].imgurl+"' alt='image' /></div>"
                     +"<div class='post-content'>"
                          +"<h2 class='post-title'><a href='browse-detail.html'>"+data[i].hname+"</a></h2>"
                          +"<p class='post-excerpt'>"+data[i].hname+"客房</p>"
                      +"</div>"
                      +"<div class='post-meta'>"
                          +"<span class='comment-count' id='room' >$:"+data[i].price+"</span>"
                          +"<a class='read-more' href='javascript:void(0);' onclick='bookroom()'>立即预定</a></div>"
                 +"</div>"
             +"</div>"
			}
			$("#contain").prepend(context);
		}
	})
})