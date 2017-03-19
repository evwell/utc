<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../_header.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	$('#submit').on('click',function(){
		var json = getFormJson('detailForm');
		var idDom = document.getElementById("id");
		var url = "config/commodityType/save";
		if(!isNullObject(idDom.value)&&trim(idDom.value).length>0){
			url = "config/commodityType/update";
		}
		$.ajax({
			method: 'post',
		    url: url,
		    dataType:'JSON',
		    contentType: "application/json",
		    data: JSON.stringify(json),
		    complete:function(data){
		    	if(data.status==200){
		    		closeWindow();
		    		layer.alert("操作成功!");
		    	}
		    }
		});
	});
});
</script>

	<div class="container">
		<form class="form-horizontal" role="form" id="detailForm">
				<div id="legend">
					<legend>明细表</legend>
					<input type="text" hidden="true" value="${vo.id}" name="id" id="id">
				</div>
				<div class="form-group">
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">简名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${vo.name}" name="name">
						</div>
					</div>
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">商品全名</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${vo.fullName}" name="fullName">
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">拼音码</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${vo.pinyin}" name="pinyin">
						</div>
					</div>
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">状态</label>
						<div class="col-sm-8">
							<select id="" class="selectpicker show-tick form-control" name="status">
								<option value="1" <c:if test="${1==vo.status}">selected</c:if>>正常</option>
								<option value="2" <c:if test="${2==vo.status}">selected</c:if>>注销</option>
							</select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">编码</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${vo.code}" name="code">
						</div>
					</div>
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">条码</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${vo.barCode}" name="barCode">
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">供应商</label>
						<div class="col-sm-8">
							<select id="" class="selectpicker show-tick form-control" name="trader.id">
								<c:forEach items="${LIST_SUPPLIER}" var="trader">
									<option value="${trader.id}"
									<c:if test="${trader.id==vo.trader.id}">selected</c:if>
									>${trader.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">地区</label>
						<div class="col-sm-8">
							<select id="" class="selectpicker show-tick form-control" name="">
								<optgroup label="湖南">
									<option>长沙</option>
									<option selected>邵阳</option>
									<option>娄底</option>
								</optgroup>
							</select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">分类</label>
						<div class="col-sm-8">
							<select id="" class="selectpicker show-tick form-control" name="commodityKind.id">
								<c:forEach items="${LIST_COMMODITYKIND}" var="commodityKind">
								 <c:if test="${commodityKind.pid==0}">
								 	<optgroup label="${commodityKind.name}">
								 		<c:forEach items="${LIST_COMMODITYKIND}" var="commodityKind2">
								 			<c:if test="${commodityKind.id==commodityKind2.pid}">
								 				<option value="${commodityKind2.id}"
								 				<c:if test="${commodityKind2.id==vo.commodityKind.id}">selected</c:if>
								 				>${commodityKind2.name}</option>
								 			</c:if>
								 		</c:forEach>
								 	</optgroup>
								 </c:if>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">标签</label>
						<div class="col-sm-8">
							<label id="tagLabel"></label>
							<button class="btn btn-default">...</button>
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">规格</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${vo.standard}" name="standard">
						</div>
					</div>
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">型号1</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${vo.type1}" name="type1">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">型号2</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${vo.type2}" name="type2">
						</div>
					</div>
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">型号3</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${vo.type3}" name="type3">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">进价</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${vo.recPrice}" name="recPrice">
						</div>
					</div>
					<div class="col-sm-6">
						<label class="col-sm-4 control-label" for="">销售价</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" value="${vo.sallPrice}" name="sallPrice">
						</div>
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-6">
						<input type="button" value="提交" id="submit">
					</div>
					<div class="col-sm-6">
						<input type="button" value="关闭" onclick="closeWindow()">
					</div>
				</div>
		</form>
	</div>
