/**
 * 详情
 * 
 * @param subSystemId,ruleCode
 */
function rulecfg_detail(ruleId) {
	var url = getURL("/rulecfg/rulecfgEdit.do");
	var target = $j("#sub_Content");
	var data = {
		'ruleId' : ruleId
	};
	loadPage(target, url, data, function(_d) {
	});
}
/**
 * 查询
 */
function rulecfg_search() {
	var data = getFormData($j("#rule_main_search_form"), {});
	var url = getURL("/rulecfg/rulecfgListMain.do");
	var target = $j("#rulecfg_list");
	loadPage(target, url, data, function(_d) {
	});
}
/**
 * 添加查询
 */
function rulecfg_Add_search() {
	var data = getFormData($j("#rulecfg_save_check_form"), {});
	var url = getURL("/rulecfg/getBusiCfgList.do");
	var target = $j("#rulecfgName_list");
	loadPage(target, url, data, function(_d) {
	});

}
/**
 * 指标返回
 * 
 * @return
 */
function rulecfg_return() {
	var url = getURL("/rulecfgSimple/rulecfgIndex.do");
	loadPage($j("#sub_Content"), url, {}, function(_d) {
	});
}

/**
 * 添加一条指标
 * 
 * @return
 */
function rulecfg_add() {
	var data = getFormData($j("#rulecfg_save_check_form"), {});
	var url = "";
	var target = $j("#sub_Content");
	url = getURL("/rulecfg/rulecfgAdd.do");
	loadPage(target, url, {}, function(_d) {
	});
}

function save() {
	$j.messager.confirm("操作提示", "您确定要执行操作吗？", function(r) {
		if (r) {
//			var data = getFormData($j("#rulecfg_save_check_form"), {});
			var data = $j("#rulecfg_save_check_form").serialize(),
			var url = getURL("/rulecfg/save.do");
			var target = $j("#sub_Content");
			$j.ajax({
				url : url,
				data : data,
				beforeSend : function(xhr) {
					$j.messager.progress({
						title : text_waitting,
						msg : text_saving
					});
				},
				success : function(dataObj) {
					$j.messager.progress('close');
					eval("var json = " + dataObj);
					if (json.flag) {
						$j.messager.alert("", +"！");
						$j.messager.alert("", json.msg + "！", '', function() {
							rulecfg_return();
						});

					} else {
						$j.messager.alert("", json.msg + "！");
					}
				},
				error : function(xhr, status, exception) {
					$j.messager.progress('close');
					$j.messager.alert("", xhr.responseText);
				}
			});
		}
	});
}

/**
 * 编辑
 */
function rulecfg_edit() {
	var data = getFormData($j("#rule_edit_search_form"), {});
	var url = "";
	var target = $j("#sub_Content");
	url = getURL("/rulecfg/update.do");
	loadPage(target, url, {}, function(_d) {
	});
}
/**
 * 查询指标的类型
 */
function dataTypesList() {
	var data = null;
	var url = getURL("/rulecfgSimple/rulecfgAdd.do");
	var target = $j("#sub_Content");
	loadPage(target, url, data, function(_d) {
	});
}

function showSelect(obj) {
	var busiCode = $j(obj).parent().find("#busiCode").val();
	var remark = $j(obj).parent().find("#remark").val();
	$j('#selectableIDStrValue').html(
			"<center>模型：" + remark + "(" + busiCode + ")" + "</cenetr>");
}

function addReceiver(obj) {
	var busiCode = $j(obj).parent().find("#busiCode").val();
	var dataType = $j(obj).parent().find("#dataType").val();
	$j("#ruleBody")
			.append(
					"<tr><td>"
							+ busiCode
							+ "<input type='hidden' name='dataTypeList' value='"
							+ dataType
							+ "' /><input type='hidden' name='busiCodeList' value='"
							+ busiCode
							+ "' /></td><td><select name='operationList' notnull='true' ><option value='=='>==</option><option value='>='>>=</option><option value='>'>></option><option value='<='><=</option><option value='<'><</option></select></td><td><input type='text' name='oValueList'   value=''/></td><td><a onclick='goDeal(this)'>删除</a></td></tr>");

}

function goDeal(thiss) {
	$j(thiss).parent().parent().remove();
}
