<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="_header.jsp"%>
<html lang="zh-CN">
<script  type="text/javascript">
	$(document).ready(function() {
		$("#logout").click(function() {
			$.ajax({
				type : "GET",
				url : "logout",
				success : function(data) {
					location = 'init';
				},
				error : function(data) {
					alert(data);
					$("#msg").html(data);
				}

			});
		});
	});
</script>
<style type="text/css">
</style>
</head>
<body>
topNav
</body>
</html>