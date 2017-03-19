<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head>
<script type="text/javascript">

var $table = $('#inventoryTable');
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
			url:"config/inventory/queryPagination",
			showColumns: true,
			showToggle:true,
		    columns: [
		    	{checkbox:true,align:"center",valign:"middle"},
		    	{field: 'id',title: 'ID',align:"center",valign:"middle",visible:false},
				{field: 'commodityType.name',title: '名称',align:"center",valign:"middle"},
				{field: 'stock.name',title: '仓库',align:"center",valign:"middle"},
				{field: 'commodityType.pinyin',title: '简码',align:"center",valign:"middle"},
				{field: 'commodityType.barCode',title: '条码',align:"center",valign:"middle"},
				{field: 'commodityType.trader.name',title: '供应商',align:"center",valign:"middle"},
				{field: 'commodityType.commodityKind.name',title: '分类',align:"center",valign:"middle"},
				{field: 'commodityType.recPrice',title: '进货价',align:"center",valign:"middle",formatter:function(value,row,index){return value/100}},
				{field: 'commodityType.sallPrice',title: '销售价',align:"center",valign:"middle",formatter:function(value,row,index){return value/100}},
				{field: 'number',title: '库存数',align:"center",valign:"middle"},
				{field: 'status',title: '状态',align:"center",valign:"middle",formatter:statusFormatter},
				{field: 'inTime',title: '入库时间',align:"center",valign:"middle",formatter:timeFormatter},
				{field: 'outTime',title: '出库时间',align:"center",valign:"middle",formatter:timeFormatter}
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
		var $table = $('#inventoryTable');
		$('#exportSelect').find('select').change(function () {
	    $table.bootstrapTable('refreshOptions', {
	      exportDataType: $(this).val()
	    });
	  });
	});
	
	$(function () {
		var $table = $('#inventoryTable');
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
<table id="inventoryTable" data-show-export="true"></table>
</body></html>