/**
 * zTree @evwell
 */
function zTreeOnClick(event, treeId, treeNode){
	alert("没有定义点击事件！");
}

function beforeRemove(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	zTree.selectNode(treeNode);
	if (treeNode.children != null && treeNode.children.length != null
			&& treeNode.children.length > 0) {
		return false;
	}
	return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}

function beforeRename(treeId, treeNode, newName, isCancel) {
	if (newName.length == 0) {
		alert("节点名称不能为空.");
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		setTimeout(function() {
			zTree.editName(treeNode)
		}, 10);
		return false;
	}
	return true;
}

function showRemoveBtn(treeId, treeNode) {
	if (treeNode.id == '1') {
		return false;
	}
	if (treeNode.children != null && treeNode.children.length != null
			&& treeNode.children.length > 0) {
		return false;
	}
	return true;
}

function showRenameBtn(treeId, treeNode) {
	if (treeNode.id == '1') {
		return false;
	}
	return true;
}

function getTime() {
	var now = new Date(), h = now.getHours(), m = now.getMinutes(), s = now
			.getSeconds(), ms = now.getMilliseconds();
	return (h + ":" + m + ":" + s + " " + ms);
}

var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0)
		return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_" + treeNode.tId);
	if (btn)
		btn.bind("click", function() {
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			zTree.addNodes(treeNode, {
				id : (100 + newCount),
				pId : treeNode.id,
				name : "new node" + (newCount++)
			});
			return false;
		});
}

function removeHoverDom(treeId, treeNode) {
	$("#addBtn_" + treeNode.tId).unbind().remove();
}

function menuAjaxDataFilter(treeId,parentNode,responseData){
    for (var i = 0; i < responseData.length; i++) {
        var item = responseData[i];
        item.target = "_self";
        if (null != item.resourceString) {
        }
    }
     
    return responseData;
}
/*
 * 建树
 */

function commonZtree(config) {
	var url = config.url;
	var method = ("method" in config)?config.method:"POST";
	var dataType = ("dataType" in config)?config.dataType:"text";
	var idKey = ("idKey" in config)?config.idKey:"id";
	var pIdKey = ("pIdKey" in config)?config.pIdKey:"pId";
	var keyName = ("keyName" in config)?config.keyName:"name";
	var contentType= ("contentType" in config)?config.contentType:"application/x-www-form-urlencoded";
	var setting = {
		async : {
			enable : true,
			url : url,
			method : method,
			dataType : dataType,
			dataFilter : menuAjaxDataFilter
		},
		view : {
			addHoverDom : addHoverDom,
			removeHoverDom : removeHoverDom,
			dblClickExpand : false,
			selectedMulti : false
		},
		edit : {
			enable : true,
			editNameSelectAll : true,
			showRemoveBtn : showRemoveBtn,
			showRenameBtn : showRenameBtn
		},
		data : {
			simpleData : {
				enable : true,
				idKey : idKey,
				pIdKey : pIdKey
			},
			key : {
				name : keyName
			}
		},
		callback : {
			onClick: zTreeOnClick,
			beforeDrag : false,
			beforeRemove : beforeRemove,
			beforeRename : beforeRename,
			onRemove : true,
			onRename : true,
			onNodeCreated : true
		}
	};
	$.fn.zTree.init($(config.dom), setting);
}

/*
 * 更新树
 */
function update(treeObj,url) {
	var nodes = treeObj.transformToArray(treeObj.getNodes());
	alert(JSON.stringify(nodes));
	$.ajax({
		url:url,processData: false,type:"post", data: nodes,
		success:function(){
			
		}
	});
}

