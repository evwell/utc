<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head>
<script type="text/javascript">

var $table = $('#commodityUnitTable');
	$(document).ready(function() {
		$table.bootstrapTable({
			toolbar:"#toolbar",
			showRefresh:true,
			striped:true,
			pagination:true,
			sidePagination: "server",
			pageList:PAGE_LIST,
			paginationLoop:false,
			method:"post",
			url:"config/commodityUnit/query",
			queryParams:function(params){
				params["typeId"]=0;
				return params;
			},
			showColumns: true,
			showToggle:true,
		    columns: [
		    	{checkbox:true,align:"center",valign:"middle"},
		    	{field: 'id',title: 'ID',align:"center",valign:"middle",visible:false},
				{field: 'unit',title: '单位名',align:"center",valign:"middle"},
				{field: 'next',title: '仓库',align:"center",valign:"middle"},
				{field: 'multiplier',title: '乘数因子',align:"center",valign:"middle"},
				{field: 'level',title: '层次',align:"center",valign:"middle"}
				]
		});
	});
	
	$(function () {
	  	$table.on('click-row.bs.table', function (e, row, $element) {
	    		$('.success').removeClass('success');
	    		$($element).addClass('success');
	  	});
	});
</script>
</head><body>
<table id="commodityUnitTable" data-show-export="true"></table>
</body></html>