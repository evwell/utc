
/**
 * 全局变量
 */
var LIST_USER,LIST_SUPPLIER,LIST_CUSTOMER,LIST_MENU,LIST_COMMODITYKIND,LIST_COMMODITYTYPE,LIST_STOCK;
//表格每页显示记录数选择配置
var PAGE_LIST = [5,15,25,50,100];


(function(){
	if(typeof(LIST_USER)=="undefined"){
		$.ajax({
			 url:"readSysteDataCache",
			 success:function(data){
				 LIST_USER=data.LIST_USER;
				 LIST_SUPPLIER=data.LIST_SUPPLIER;
				 LIST_CUSTOMER=data.LIST_CUSTOMER;
				 LIST_STOCK=data.LIST_STOCK;
				 LIST_COMMODITYKIND=data.LIST_COMMODITYKIND;
				 LIST_COMMODITYTYPE=data.LIST_COMMODITYTYPE;
			 }
		 });
	}
 }());
 
 

 function isNullObject(obj){
	 return (typeof(obj)=="undefined")?true:false;
 }

String.prototype.equals = function(str){
	if(isNullObject(this)||this=="") return false;
	if(isNullObject(str)||str=="") return false;
	return (this == str);
}

function trim(str){
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str){
    return str.replace(/(^\s*)/g,"");
}
function rtrim(str){
    return str.replace(/(\s*$)/g,"");
}
/**
 * 时间格式化
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
