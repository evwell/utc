/**
 * tc.js @evwell
 */

/*
*	生成下拉菜单    jquery.js JSON2.js
*/
function menu(data){
	var subNav = "";
	for(i=0;i<data.length;i++){
		if(data[i].pid==0){
			subNav = subNav + "<li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">" + data[i].name + "</a>";
			var tmpNav = "";
			for(j=0;j<data.length;j++){
				if(data[i].id==data[j].pid){
					tmpNav = tmpNav + "<li><a href=\"javascript:so(\'"+data[j].url+"\')\">" + data[j].name + "</a></li>";
					}
			}
			if(tmpNav.length>1){tmpNav = "<ul class=\"dropdown-menu\" role=\"menu\">" + tmpNav + "</ul>"}
			subNav = subNav + tmpNav + "</li>";
		}
	}
	return subNav;
}

function menuAccordion(data){
	var collapse = "";
	var subNav = "<div class=\"panel-group\" id=\"accordion\">";
	for(i=0;i<data.length;i++){
		if(data[i].pid==0){
			collapse = "collapse" + i;
			subNav = subNav + "<div class=\"panel panel-default\"><div class=\"panel-heading\" data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#" + collapse + "\"><a class=\"accordion-toggle\">" + data[i].name + "</a></div>";
			subNav = subNav + "<div id=\"" + collapse + "\" class=\"panel-collapse collapse\" style=\"height: 0px;\"><div class=\"panel-body\"><ul class=\"nav nav-pills nav-stacked\">";
			var tmpNav = "";
			for(j=0;j<data.length;j++){
				if(data[i].id==data[j].pid){
					tmpNav = tmpNav + "<li><a href=\"javascript:so(\'"+data[j].url+"\')\">" + data[j].name + "</a></li>";
					}
			}
			subNav = subNav + tmpNav + "</ul></div></div>";
		}
	}
	return subNav + "</div>";
}

function so(url){
	$.ajax({
		url:url,
		method:"post",
		success:function(data){$("#main").html(data);}
	});
}


function openPostWindow(url, data, name) {
	var tempForm = document.createElement("form");
	tempForm.id = "tempForm1";
	tempForm.method = "post";
	tempForm.action = url;
	tempForm.target = name;
	var hideInput = document.createElement("input");
	hideInput.type = "hidden";
	hideInput.name = "content"
	hideInput.value = data;
	tempForm.appendChild(hideInput);
	tempForm.attachEvent("onsubmit", function() {
		openWindow(name);
	});
	document.body.appendChild(tempForm);
	tempForm.fireEvent("onsubmit");
	tempForm.submit();
	document.body.removeChild(tempForm);
}
function openWindow(name){ 
	oWin = window.open('about:blank',name,'width=800,height=600,resizable=no,location=no,status=no,menubar=no,toolbar=no'); 
}

function closeWindow() {
	var userAgent = navigator.userAgent;
	if (userAgent.indexOf("Firefox") != -1 || userAgent.indexOf("Chrome") != -1) {
		window.location.href = "about:blank";
	} else {
		window.opener = null;
		window.open("", "_self");
		window.close();
	}
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭
}

/*
 * 表单数据转成JSON数据,支持二级的子JSON,如name=supplier.id value=1则是supplier:{id:1}
 * 此代码有待优化
 */
function getFormJson(frm) { //frm：form表单的id
	var o = {};
	var a = $("#" + frm).serializeArray();
	$.each(a, function() {
		var arr = this.name.split(".");
		if(arr.length==2){
			var tmp = {};
			if (tmp[arr[1]] !== undefined) {
				if (!tmp[arr[1]].push) {
					tmp[arr[1]] = [ tmp[arr[1]] ];
				}
				tmp[arr[1]].push(this.value || '');
			} else {
				tmp[arr[1]] = this.value || '';
			}
			if (o[arr[0]] !== undefined) {
				if (!o[arr[0]].push) {
					o[arr[0]] = [ o[arr[0]] ];
				}
				o[arr[0]].push(tmp);
			} else {
				o[arr[0]] = tmp;
			}
		}else{
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		}
	});
	return o;
}
