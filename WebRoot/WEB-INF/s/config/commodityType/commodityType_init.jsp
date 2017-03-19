<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head>
<script type="text/javascript">
function edit(id){
	var idx = layer.load();
	oWin = layer.open({type:2,shade: 0,maxmin:true,scrollbar:false,title:"edit",skin:"layui-layer-rim",area:["800px","600px"],content:"config/commodityType/detail/"+id,
		end:function(){
			$('#commodityTypeTable').bootstrapTable('refresh');
			$('#commodityTypeTable').bootstrapTable('resetView');
			}
	});
	layer.close(idx);
}

function remove(id){
	layer.confirm("确定删除此行？",function(){
		var idx = layer.load();
		$.ajax({
			url:"config/commodityType/delete/",
			method:"post",
			data:JSON.stringify({id:id}),
			dataType:"json",
			contentType: "application/json",
			complete:function(data){
				layer.close(idx);
				if(data.status==200&&data.responseText=="success"){
					$('#commodityTypeTable').bootstrapTable("remove", {field:'id',values:[id]});
					layer.msg('SUCCESS!', {time:1000,icon: 1});
				}else{
					layer.alert("FAILURE!");
				}
			}
		});
	});
}
$(function(){
	$('#btn_add').on('click',function(){
		layer.open({type:2,shade: 0,maxmin:true,scrollbar:false,title:"add",skin:"layui-layer-rim",area:["800px","600px"],content:"config/commodityType/detail/"});
	});
});
var $table = $('#commodityTypeTable');
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
			url:"config/commodityType/queryPagination",
			showColumns: true,
			showToggle:true,
		    columns: [
		    	{checkbox:true,align:"center",valign:"middle"},
				{field: 'name',title: '名称',align:"center",valign:"middle"},
				{field: 'pinyin',title: '拼音简码',align:"center",valign:"middle"},
				{field: 'barCode',title: '条码',align:"center",valign:"middle"},
				{field: 'kindId',title: 'kindId',align:"center",valign:"middle",visible:false},
				{field: 'trader.name',title: '供应商',align:"center",valign:"middle"},
				{field: 'commodityKind.name',title: '商品分类',align:"center",valign:"middle"},
				{field: 'recPrice',title: '进货价',align:"center",valign:"middle",formatter:function(value,row,index){return value/100}},
				{field: 'sallPrice',title: '销售价',align:"center",valign:"middle",formatter:function(value,row,index){return value/100}},
				{field: 'status',title: '状态',align:"center",valign:"middle",formatter:statusFormatter},
				{field: 'createBy',title: '建档人',align:"center",valign:"middle",visible:false,formatter:operatorFormatter},
				{field: 'createTime',title: '建档时间',align:"center",valign:"middle",visible:false,formatter:dateFormatter},
				{field: 'modifyBy',title: '修改人',align:"center",valign:"middle",visible:false,formatter:operatorFormatter},
				{field: 'modifyTime',title: '修改时间',align:"center",valign:"middle",visible:false,formatter:dateFormatter},
                {field: 'id',title: '操作',align:'center',valign:"middle",formatter:actionFormatter,events:actionEvents}
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
		var $table = $('#commodityTypeTable');
		$('#exportSelect').find('select').change(function () {
	    $table.bootstrapTable('refreshOptions', {
	      exportDataType: $(this).val()
	    });
	  });
	});
	
	$(function () {
		var $table = $('#commodityTypeTable');
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
		<button id="btn_add" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
		</button>
		<button id="btn_delete" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
		</button>
		<button id="btn_export" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>导出
		</button>
		<select id="exportSelect" class="control">
			<option value="">当页</option>
			<option value="all">所有</option>
			<option value="selected">选择</option>
		</select>
	</div>
<table id="commodityTypeTable" data-show-export="true"></table>
</body></html>