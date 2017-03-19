<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath(); %>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">

<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<script type="text/javascript" src="<%=basePath%>/s/lib/jquery-1.12.0/jquery-1.12.0.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/lib/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/lib/bootstrap/3.3.0/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/lib/bootstrap-table/1.11.0/bootstrap-table.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/lib/bootstrap-table/1.11.0/bootstrap-table-zh-CN.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/lib/layer-v3.0.1/layer.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/lib/tableExport/tableExport.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/lib/bootstrap-select/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/lib/bootstrap-select/js/i18n/defaults-zh_CN.min.js"></script>

<script type="text/javascript" src="<%=basePath%>/s/js/provincesData.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/js/jquery.provincesCity.js">
$(".province").ProvinceCity();
</script> 

<script type="text/javascript" src="<%=basePath%>/s/js/json2.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/js/sys.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/js/tc.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/js/tree.js"></script>
<script type="text/javascript" src="<%=basePath%>/s/js/tables.js"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/s/lib/jquery-ui-1.12.1/jquery-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s/lib/jquery-ui-1.12.1/jquery-ui.structure.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s/lib/jquery-ui-1.12.1/jquery-ui.theme.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s/lib/bootstrap/3.3.0/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s/lib/bootstrap-table/1.11.0/bootstrap-table.min.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/s/lib/bootstrap-select/css/bootstrap-select.min.css"/>

<link rel="stylesheet" type="text/css" href="<%=basePath%>/s/css/style.css"/>
<script type="text/javascript">
/**
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
 */
	
</script>
