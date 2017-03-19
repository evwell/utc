<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head>
<script type="text/javascript">

var $table = $('#logTable');
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
			url:"analysis/log/queryPagination",
			showColumns: true,
			showToggle:true,
		    columns: [
		    	{field: 'id',title: 'ID',align:"center",valign:"middle"},
				{field: 'operator',title: '操作员',align:"center",valign:"middle",formatter:operatorFormatter},
				{field: 'ip',title: 'IP',align:"center",valign:"middle"},
				{field: 'cmd',title: '命令',align:"center",valign:"middle"},
				{field: 'content',title: '备注',align:"center",valign:"middle"},
				{field: 'createTime',title: '操作时间',align:"center",valign:"middle",formatter:timeFormatter}
				]
		});
	});
	
	$(function () {
	  	$table.on('click-row.bs.table', function (e, row, $element) {
	    		$('.success').removeClass('success');
	    		$($element).addClass('success');
	  	});
	});
	
	$(function () {
		var $table = $('#logTable');
		$('#exportSelect').find('select').change(function () {
	    $table.bootstrapTable('refreshOptions', {
	      exportDataType: $(this).val()
	    });
	  });
	});
	
	$(function () {
		var $table = $('#logTable');
		$('#btn_export').click(function () {
	  	$table.tableExport({
	      type: 'excel',
	      escape: false
	    });
	  });
	});
</script>
</head><body>
	<div id="toolbar" class="btn-group">
		<button id="btn_export" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>导出
		</button>
		<select id="exportSelect" class="control">
			<option value="">当页</option>
			<option value="all">所有</option>
			<option value="selected">选择</option>
		</select>
	</div>
<table id="logTable" data-show-export="true"></table>
</body></html>