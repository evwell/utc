/**
 * tc.js @evwell
 */

 /**
  * 地区
  */
 function areaFormatter(value,row,index){
	 return '新疆';
 }

/**
  * 日期展现
  */
 function dateFormatter(value,row,index){return new Date(value).format("yyyy-MM-dd");}

 /**
  * 时间展现
  */
 function timeFormatter(value,row,index){return new Date(value).format("yyyy-MM-dd hh:mm:ss");}
 
 /**
  * 操作员中展现
  */
 function operatorFormatter(value,row,index){
	 var opr = value;
	 if(!isNullObject(LIST_USER)&&LIST_USER.length>0){
		 LIST_USER.forEach(function(v,index,array){if(v.id==value)opr = v.userName;});
	 }
	 return opr;
 }

/**
 * 状态展现通用格式化
 */
function statusFormatter(value, row, index) {
	var status = "-";
	switch(value){
	case 1 : status = "正常"; break;
	case 2 : status = "注销"; break;
	case 3 : status = ""; break;
	}
	return status;
}

/**
 * 操作展现格式化
 */
 function actionFormatter(value, row, index) {
    return [
        '<a class="edit ml10" href="javascript:void(0)" title="编辑">',
        '<i class="glyphicon glyphicon-edit"></i>',
        '</a>',
        '<a class="remove ml10" href="javascript:void(0)" title="删除">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('');
}
 
 /**
  * 默认调用当前页面操作方法
  */
window.actionEvents = {
    'click .edit': function (e, value, row, index) {
    	try{edit(row.id);}catch(e){if("ReferenceError".equals(e.name)){layer.alert('编辑方法还没有定义，此功能暂不可用！');}}
        
    },
    'click .remove': function (e, value, row, index) {
    	try{remove(row.id);}catch(e){
	    		if("ReferenceError".equals(e.name)){layer.alert(' 删除方法还没有定义，此功能暂不可用！');}
    		}
    }
};


/*******************************************************************************************************
 * 不常用变量展现方法
 *******************************************************************************************************/
/**
 * 客户类型展现
 */
function traderTypeFormatter(value,row,index){
	var type = "-";
	switch(value){
	case 1 : type = "供应商"; break;
	case 2 : type = "客户"; break;
	}
	return type;
}
/**
 * 客户类型展现
 */
function traderNatureFormatter(value,row,index){
	var nature = "-";
	switch(value){
	case 1 : nature = "个人"; break;
	case 2 : nature = "经销商"; break;
	case 3 : nature = "厂家"; break;
	}
	return nature;
}