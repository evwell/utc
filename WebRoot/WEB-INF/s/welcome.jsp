<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="_header.jsp"%>


<head>
<title>STC</title>
<script type="text/javascript">
$(window).resize(function() {
	tcInit();
});

function tcInit(){
	$("#top").css("height", "80px");
	$("#mid").css("height", $(window).height()-110);
	$("#foot").css("height", "30px");
}

	$(document).ready(function() {
		tcInit();
		$.ajax({
			type : "POST",
			url : "menu/list",
			success : function(data) {
				var menu = menuAccordion(data);
				$("#menuAccordion").append(menu);
			},
		});
	});
</script>
</head>

<body>
	<div id="welcome">
		<div id="top">
			<nav class="navbar navbar-default navbar-static-top" role="navigation">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse" data-target="#navbar"
							aria-expanded="false" aria-controls="navbar">
							<span class="sr-only">Toggle navigation</span> 
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="auth/init">Home</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse">
						<ul id="menuUL" class="nav navbar-nav">
						</ul>
					</div>
					<!--/.nav-collapse -->
				</div>
			</nav>
		</div>
		<div id="mid">
			<div class="col-sm-1">
				<div id="menuAccordion"></div>
			</div>
			<div class="col-sm-11">
				<div id="main">
					<h1>Welcome!</h1>
				</div>
			</div>
		</div>
		<div id="foot">
			<footer class="footer">
				<p>Footer Text</p>
			</footer>
		</div>

	</div>
</body>
