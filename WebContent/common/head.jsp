<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function setDateTime(){
		var date=new Date();
		var day=date.getDay();
		var week;
		switch(day){
		case 0:week="星期日";break;
		case 1:week="星期一";break;
		case 2:week="星期二";break;
		case 3:week="星期三";break;
		case 4:week="星期四";break;
		case 5:week="星期五";break;
		case 6:week="星期六";break;
		}
		var today=date.getFullYear()+"年"+(date.getMonth()+1)+"月"+date.getDate()+"日  "+week+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		document.getElementById("today").innerHTML=today;
	}
window.setInterval("setDateTime()", 1000);
</script>

</head>
<body>
<div class="row-fluid">
	<div class="span12">
		<div class="head">
			<div class="headLeft">
				<img src="${pageContext.request.contextPath}/images/logo.png"/>
			</div>
			<div class="headRight">
				欢迎管理员：<font color="red">${currentUser.userName }</font>&nbsp;&nbsp;&nbsp;<font id="today" class="currentDateTime"></font>
			</div>
		</div>
	</div>
</div>
</body>
</html>