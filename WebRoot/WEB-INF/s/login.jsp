<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="_header.jsp"%>
<html lang="zh-CN">
<title>STC</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#submit").on("click", function(e) {
			var userName = $("#userName").val();
			var password = $("#password").val();
			userName = "admin";
			password = "123";
			$.post("auth/login",{cid : "0",userName : userName,password : password},function(data, textStatus, jqXHR){
				if ('success'.equals(data.msg)) {
					location = location;
				} else {
					layer.tips(data.msg,"#submit",{tips: [1, '#3595CC'],time: 4000});
				}
			});
		});
		
		$("#register").click(function() {
			$.get("auth/register",function(response,status,xhr){layer.tips(response,"#register",{tips: [1, '#3595CC'],time: 4000});});
		});
		
		$("#delete").click(function() {
			$.post("auth/delete/1",function(response,status,xhr){layer.tips(response,"#delete",{tips: [1, '#3595CC'],time: 4000});});
		});
	});
</script>
<style type="text/css">
html, body {
	height: 100%; /*设置html和body的width和height为100%，可使全屏生效*/
	width: 100%;
	margin: 0px; /*设置上下左右的相对位置为0，可避免超出范围出现滚动条*/
}
h1{
margin: auto;
align: center;
}
.footer {
	height: 5%;
	width: 100%;
	text-align: center;
}

.login {
	height: 95%;
	display: -webkit-flex;
	display: flex;
	-webkit-align-items: center;
	align-items: center;
	-webkit-justify-content: center;
	justify-content: center;
}

</style>

<body>
	<div class="container login" ><h1>${SYSTEM_NAME}</h1>
		<input id="cid" name="cid" type="hidden" value="0" />

		<table class="loginTable">
			<tr>
				<td colspan="3"><div id="msg"
						style="color: red; text-align: center">${msg}</div></td>
			</tr>
			<tr>
				<td style="text-align: right">USERNAME:</td>
				<td><input id="userName" name="userName" type="text"
					placeholder="用户" /></td>
				<td></td>
			</tr>
			<tr>
				<td style="text-align: right">PASSWORD:</td>
				<td><input id="password" name="password" type="password"
					placeholder="密码" /></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td colspan="3" style="text-align: center">
				<button id="submit" type="button" class="btn btn-primary">Login</button>
				<button id="register" type="button" class="btn">Register</button>
				<button id="delete" type="button" class="btn btn-danger">delete</button>
				</td>
			</tr>
		</table>
	</div>
	<div class="footer">Copyright TC</div>
</body>
</html>