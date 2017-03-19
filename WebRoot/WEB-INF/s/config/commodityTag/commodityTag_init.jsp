<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#commodityTagTable').bootstrapTable({
			url:"config/commodityTag/list",
			striped:true,
			search:true,
			searchOnEnterKey:true,
			sortName:'name',
			pageList:PAGE_LIST,
			pagination:true,
			showRefresh:true,
		    columns: [
		    	{checkbox:true,align:'center'},
		    	{field: 'id',title: 'ID',align:'center'},
				{field: 'tag',title: '标签',align:'center'},
				{field: 'status',title: '状态',align:'center',formatter:statusFormatter},
				{field: 'createBy',title: '建档人',align:'center',formatter:operatorFormatter},
				{field: 'createTime',title: '建档时间',align:'center',formatter:dateFormatter},
				{field: 'modifyBy',title: '修改人',align:'center',formatter:operatorFormatter},
				{field: 'modifyTime',title: '修改时间',align:'center',formatter:dateFormatter},
				{field: '',title: '操作',align:'center',valign:"middle",formatter:actionFormatter,events:actionEvents}
				]
		});
	});
</script>
<table id="commodityTagTable" class="table"></table>