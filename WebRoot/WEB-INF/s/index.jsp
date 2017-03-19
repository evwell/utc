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

div {
	margin: 0px;
}

#top {
	height: 100px;
	position: absolute;
	z-index: 10;
	width: 100%;
	top: 0px;
	overflow: no;
	z-index: 10;
	width: 100%;
	top: 0px;
	overflow: no;
}

#left {
	position: absolute;
	width: 200px;
	height: 100%;
	z-index: 5;
	overflow: auto;
}

#nav {
	margin-top: 100px;
}

#right {
	position: absolute;
	height: 100%;
	width: 100%;
	overflow: auto;
}

#show {
	margin: 100px 0px 0px 200px;
}

#footer {
	height: 30px;
	position: absolute;
	width: 100%;
	bottom: 0px;
	overflow: no;
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
}

a:link, a:visited {
	display: block;
	width: 120px;
	font-weight: bold;
	color: #FFFFFF;
	background-color: #bebebe;
	text-align: center;
	padding: 4px;
	text-decoration: none;
	text-transform: uppercase;
}

a:hover, a:active {
	background-color: #cc0000;
}
</style>

<script type="text/javascript">
	//重置页面高度
	function resize() {
		$("div.secondrightFrame").css("height", parent.computeIFrame() - 60);
	}
</script>
</head>
<body>

	<div id="top"></div>
	<div id="left">
		<div id="nav">
			<ul>
				<s:iterator id='menu1' value='menuList' status='st'>
					<s:if test="#menu1.level==1">
						<li id='menu-<s:property value="#menu1.id"/>'><ul>
								<s:property value="#menu1.name" />
								<s:iterator id='menu2' value='menuList' status='s2'>
									<s:if test="#menu2.parnetId==#menu1.id">
										<li><a href='<s:property value="#menu2.url" />'
											title='<s:property value="#menu2.name" />'><s:property
													value="#menu2.name" /></a></li>
									</s:if>
								</s:iterator>
							</ul></li>
					</s:if>
				</s:iterator>
			</ul>
		</div>
	</div>
	<div id="right">
		<div id="show">ri</div>
	</div>
	<div id="footer">footer</div>

</body>
</html>