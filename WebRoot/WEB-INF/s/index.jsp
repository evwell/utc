﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<ul id="myTab" class="nav nav-pills">
						<li><a href="#report" data-toggle="tab">360报表</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div id="foot">
			<footer class="footer">
				<p>Footer Text</p>
			</footer>
		</div>
	</div>
	<div id="myTabContent" class="tab-content"><div class="tab-pane fade in active" id="report"><p>菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p></div></div>
</body>
