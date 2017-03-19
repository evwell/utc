<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="s/lib/zTree_v3/css/metroStyle/metroStyle.css" type="text/css">
<script type="text/javascript" src="s/lib/zTree_v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		commonZtree({
			dom : "#commodityKindTree",
			url : "config/commodityKind/list",
			method : "get",
			dataType : "json",
			idKey : "id",
			pIdKey : "pid",
			keyName : "name"
		});
		$.ajax({
			url : "config/commodityType/init",
			success : function(data) {
				$("#commodityTypeDetail").html(data);
			}
		});
	});

	function zTreeOnClick(event, treeId, treeNode) {
		var idx = layer.load();
		if (isNullObject(treeNode.children)) {
			$('#commodityTypeTable').bootstrapTable("refresh", {
				method : "post",
				url : 'config/commodityType/queryPagination',
				query : {"commodityKind" : {id : treeNode.id}}
			});
		} else {
			$('#commodityTypeTable').bootstrapTable("refresh", {
				method : "post",
				url : 'config/commodityType/queryPagination',
				query : {"commodityKind" : {pid : treeNode.id}}
			});
		}
		$('#commodityTypeTable').bootstrapTable('resetView');
		layer.close(idx);
	}
</script>

<div id="commodityKindTree" class="ztree col-sm-2"></div>
<div id="txt"></div>
<div id="commodityTypeDetail" class="col-sm-10"></div>

