<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<html lang="zh-CN">
<body>
	程序发生了错误，有可能该页面正在调试或者是设计上的缺陷.
	<a href="/stc/auth/init">返回登陆页面</a>
	<hr width=80%>
	<h2>
		<font color=#DB1260>JSP Error Page</font>
	</h2>
	<p>
		An exception was thrown: <b><%=exception.getMessage().substring(exception.getMessage().indexOf(": ")+1, exception.getMessage().length()-1)%></b>
	</p>
	<%
		Enumeration<String> e = request.getHeaderNames();
		String key;
		while (e.hasMoreElements()) {
			key = e.nextElement();
		}
		e = request.getAttributeNames();
		while (e.hasMoreElements()) {
			key = e.nextElement();
		}
		e = request.getParameterNames();
		while (e.hasMoreElements()) {
			key = e.nextElement();
		}
	%>
	<%=request.getAttribute("javax.servlet.forward.request_uri")%><br>

</body>
</html>