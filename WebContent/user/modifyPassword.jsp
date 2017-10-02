<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
function submitForm(){
	var userId='${currentUser.userId}';
	alert(userId);
	
	var password='${currentUser.password}';
	var oldPassword=$("#oldPassword").val();
	var newPassword=$("#newPassword").val();
	var newPassword2=$("#newPassword2").val();
	if(oldPassword==null||oldPassword==""){
		alert("原密码不能为空");
		return false;
	}
	if(newPassword==null || newPassword==""){
		alert("新密码不能为空！");
		return false;
	}
	if(newPassword2==null || newPassword2==""){
		alert("确认新密码不能为空！");
		return false;
	}
	if(password!=oldPassword){
		alert("原密码错误！");
		return false;
	}
	if(newPassword!=newPassword2){
		alert("确认新密码不一致！");
		return false;
	}
	
	$.post("user!save",{userId:userId,password:newPassword},
	      function(result){
		var result=eval('('+result+')');
		if(result.errorMsg){
			alert(result.errorMsg);
		}else{
			alert("密码修改成功,下一次登陆生效");
			resetValue();
		}
	}		
	);
}

function resetValue(){
	$("#oldPassword").val("");
	$("#newPassword").val("");
	$("#newPassword2").val("");
}
</script>
</head>
<body>
<div class="data_list">
	<div class="data_content">
		<table align="center">
			<tr>
				<td><label>用户名:</label></td>
				<td><input type="text" id="userName" value="${currentUser.userName}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td><label>原密码:</label></td>
				<td><input type="password" id="oldPassword" /></td>
			</tr>
			<tr>
				<td><label>新密码：</label></td>
				<td><input type="password" id="newPassword" /></td>
			</tr>
			<tr>
				<td><label>确认新密码：</label></td>
				<td><input type="password" id="newPassword2" /></td>
			</tr>
			<tr>
				<td>
					<input type="button" class="btn  btn-primary" value="提交" onclick="submitForm()"/>
				</td> 
				<td>
					<input type="button" class="btn  btn-primary" value="重置" onclick="resetValue()"/>
				</td>
			</tr>
			
		</table>
	</div>
</div>
</body>
</html>