
function checkframe(number) {
	$("#textbody").empty();
	switch (number) {
	case 0:
		$("#textbody").load('room_order.jsp');
		break;
	case 1:
		$("#textbody").load('food_order.jsp');
		break;
	case 2:
		$("#textbody").load('openvip.jsp');
		break;
	case 3:
		$("#textbody").load('vipreghts.jsp');
		break;
	case 6:
		$("#textbody").load('usermsg.jsp');
		break;
	case 7:
		$("#textbody").load('realuser.jsp');
		break;
	case 8:
		$("#textbody").load('updatepwd.jsp');
		break;
	}
}