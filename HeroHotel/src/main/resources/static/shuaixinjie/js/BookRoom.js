window.onload = function() {
	var nowDate = new Date();
	var str = nowDate.getFullYear() + "-" + (nowDate.getMonth() + 1) + "-"
			+ nowDate.getDate() + "       " + nowDate.getHours() + ":"
			+ nowDate.getMinutes();
	document.getElementById("flight-from").value = str;
}




function openwindow(){
	//获取弹窗得div
	var modal = document.getElementById('myModal');
	// 获取 <span> 元素，用于关闭弹窗 （X）
	var span = document.getElementsByClassName("close")[0];
	//获取弹窗中得确定按钮
	var ok=document.getElementsByClassName("ok")[0];
	//获取弹窗中得取消按钮
	var no=document.getElementsByClassName("no")[0];
	//窗体弹出
	modal.style.display = "block";
	//点击窗体ok
	ok.onclick=function(){
		//执行弹出窗体得确定后得操作
		alert("执行确定按钮点击得操作");
		//关闭窗口
		modal.style.display = "none";
	}
	//点击窗体取消按钮
	no.onclick=function(){
		//直接关闭窗口
		modal.style.display = "none";
	}
	// 点击 <span> (x), 关闭弹窗
	span.onclick = function() {
		//直接关闭窗口
		modal.style.display = "none";
	}
	// 在用户点击其他地方时，关闭弹窗
	window.onclick = function(event) {
		//点击窗口外内容，关闭窗口
		if (event.target == modal) modal.style.display = "none";
	}
}

function reduce1() {
	if($("#flight-depart").val()==""){
		alert("请先选择入住时间段")
	}else {
		var number=parseInt($("#single").html())
		if(number==0){
			alert("非法操作~")
		}else {
			$("#single").html(number-1)
		}
	}

}
function add1() {
	if($("#flight-depart").val()==""){
		alert("请先选择入住时间段")
	}else {
		var singlenumber=parseInt($("#singlenumber").html())
		var number=parseInt($("#single").html())
		if(singlenumber==number){
			alert("非法操作~")
		}else {
			$("#single").html(number+1)
		}
	}

}
function reduce2() {
	if($("#flight-depart").val()==""){
		alert("请先选择入住时间段")
	}else {
		var number=parseInt($("#double").html())
		if(number==0){
			alert("非法操作~")
		}else {
			$("#double").html(number-1)
		}
	}

}
function add2() {
	if($("#flight-depart").val()==""){
		alert("请先选择入住时间段")
	}else {
		var singlenumber=parseInt($("#doublenumber").html())
		var number=parseInt($("#double").html())
		if(singlenumber==number){
			alert("非法操作~")
		}else {
			$("#double").html(number+1)
		}
	}

}
function reduce3() {
	if($("#flight-depart").val()==""){
		alert("请先选择入住时间段")
	}else {
		var number=parseInt($("#tri").html())
		if(number==0){
			alert("非法操作~")
		}else {
			$("#tri").html(number-1)
		}
	}

}
function add3() {
	if($("#flight-depart").val()==""){
		alert("请先选择入住时间段")
	}else {
		var singlenumber=parseInt($("#trinumber").html())
		var number=parseInt($("#tri").html())
		if(singlenumber==number){
			alert("非法操作~")
		}else {
			$("#tri").html(number+1)
		}
	}

}
function reduce4() {
	if($("#flight-depart").val()==""){
		alert("请先选择入住时间段")
	}else {
		var number=parseInt($("#four").html())
		if(number==0){
			alert("非法操作~")
		}else {
			$("#four").html(number-1)
		}
	}

}
function add4() {
	if($("#flight-depart").val()==""){
		alert("请先选择入住时间段")
	}else {
		var singlenumber=parseInt($("#fournumber").html())
		var number=parseInt($("#four").html())
		if(singlenumber==number){
			alert("非法操作~")
		}else {
			$("#four").html(number+1)
		}
	}

}