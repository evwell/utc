<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#commodityTagTable').bootstrapTable({
			url:"config/commodityTag/list",
			striped:true,
			search:true,
			searchOnEnterKey:true,
			sortName:'name',
			pagination:true,
			showRefresh:true,
		    columns: [
		    	{checkbox:true,align:'center'},
		    	{field: 'id',title: 'ID',align:'center',sortable:true},
				{field: 'tag',title: '标签',align:'center',sortable:true},
				{field: 'status',title: '状态',align:'center',sortable:true},
				{field: 'createBy',title: '建档人',align:'center',sortable:true},
				{field: 'createTime',title: '建档时间',align:'center',sortable:true},
				{field: 'modifyBy',title: '修改人',align:'center',sortable:true},
				{field: 'modifyTime',title: '修改时间',align:'center',sortable:true}
				]
		});
	});
</script>
<table id="commodityTagTable" class="table"></table>