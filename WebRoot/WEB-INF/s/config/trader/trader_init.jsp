<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">

$(function(){
	$('#btn_add').on('click',function(){
		layer.open({type:2,shade: 0,maxmin:true,scrollbar:false,title:"add",skin:"layui-layer-rim",area:["800px","600px"],content:"config/trader/detail"});
	});
});

function edit(id){
	var idx = layer.load();
	oWin = layer.open({type:2,shade: 0,maxmin:true,scrollbar:false,title:"edit",skin:"layui-layer-rim",area:["800px","600px"],content:"config/trader/detail/"+id,
		end:function(){
			$('#traderTable').bootstrapTable('refresh');
			$('#traderTable').bootstrapTable('resetView');
			}
	});
	layer.close(idx);
}
	$(document).ready(function() {
		$('#traderTable').bootstrapTable({
			url:"config/trader/queryPagination",
			queryParams:function(params){params["tradeType"]=${sos};return params;},
			toolbar:"#toolbar",
			showRefresh:true,
			striped:true,
			pagination:true,
			sidePagination: "server",
			pageList:PAGE_LIST,
			paginationLoop:false,
			method:"post",
			showColumns: true,
			showToggle:true,
		    columns: [
		    	{checkbox:true,align:'center'},
		    	{field: 'nature',title: '类型',align:'center',formatter:traderNatureFormatter},
		    	{field: 'name',title: '名称',align:'center'},
		    	{field: 'fullName',title: '全名',align:'center',visible:false},
		    	{field: 'firmName',title: '挂牌',align:'center',visible:false},
		    	{field: 'contacts',title: '联系人',align:'center'},
		    	{field: 'tel1',title: '电话',align:'center'},
		    	{field: 'phone1',title: '移动电话',align:'center'},
		    	{field: 'address',title: '地址',align:'center'},
		    	{field: 'status',title: '状态',align:'center',formatter:statusFormatter},
		    	{field: 'compDsc',title: '描述',align:'center'},
		    	{field: 'area',title: '地区',align:'center',formatter:areaFormatter},
		    	{field: 'comment',title: '备注',align:'center'},
		    	{field: 'id',title: '操作',align:'center',valign:"middle",formatter:actionFormatter,events:actionEvents}
				]
		});
	});
</script>
<div id="toolbar" class="btn-group">
		<button id="btn_add" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
		</button>
		<button id="btn_edit" type="button" class="btn btn-default">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
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
<table id="traderTable" data-show-export="true"></table>