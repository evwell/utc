<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="_header.jsp"%>
<html lang="zh-CN">
<head>
<title>TC</title>
<style type="text/css">
html, body {
	height: 100%; /*设置html和body的width和height为100%，可使全屏生效*/
	width: 100%;
	margin: 0px; /*设置上下左右的相对位置为0，可避免超出范围出现滚动条*/
}

.footer {
	height: 5%;
	width: 100%;
	text-align:center;
}

.vertical-container {
	height: 95%;
	display: -webkit-flex;
	display: flex;
	-webkit-align-items: center;
	align-items: center;
	-webkit-justify-content: center;
	justify-content: center;
}

div.login {
	margin: auto;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: 150px;
	margin-top: -50px;
}

table.loginTable {
	border-collapse: collapse;
	border: 0px;
	width: 400px;
}
</style>

</head>
<body>
	register
	<div class="footer">Copyright TC</div>
</body>
</html>