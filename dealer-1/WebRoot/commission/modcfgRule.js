/**
 * 详情
 * 
 * @param subSystemId,modCode
 */
function modcfg_detail(modId) {
	var url = getURL("/modcfg/modcfgEdit.do");
	var target = $j("#sub_Content");
	var data = {
		'modId' : modId
	};
	loadPage(target, url, data, function(_d) {
	});
}
/**
 * 查询
 */
function modcfg_search() {
	var data = getFormData($j("#mod_main_search_form"), {});
	var url = getURL("/modcfg/modcfgListMain.do");
	var target = $j("#modcfg_list");
	loadPage(target, url, data, function(_d) {
	});
}

function modcfg_Add_search() {
	var data = getFormData($j("#modcfg_save_check_form"), {});
	var url = getURL("/modcfg/getRuleCfgList.do");
	var target = $j("#modcfgName_list");
	loadPage(target, url, data, function(_d) {
	});
}
/**
 * 模块返回
 * 
 * @return
 */
function modcfg_return() {
	var url = getURL("/modcfgSimple/modcfgIndex.do");
	loadPage($j("#sub_Content"), url, {}, function(_d) {
	});
}

/**
 * 添加一条指标
 * 
 * @return
 */
function save() {

	$j.messager.confirm("操作提示", "您确定要执行操作吗？", function(r) {
		if (r) {
			var data = getFormData($j("#modcfg_save_check_form"), {});
			var url = "";
			var target = $j("#sub_Content");
			url = getURL("/modcfg/save.do");
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
							busicfg_return();
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
function modcfg_edit() {
	var data = null;
	var url = "";
	var target = $j("#sub_Content");
	url = getURL("/modcfg/update.do");
	loadPage(target, url, {}, function(_d) {
	});
}

/**
 * 查询指标的类型
 */
function modcfg_add() {
	var data = null;
	var url = getURL("/modcfg/modcfgAdd.do");
	var target = $j("#sub_Content");
	loadPage(target, url, data, function(_d) {
	});
}

function showSelect(obj) {
	var ruleName = $j(obj).parent().find("#ruleName").val();
	var ruleId = $j(obj).parent().find("#ruleId").val();
	$j('#selectableIDStrValue').html("<center>规则：" + ruleName + "</cenetr>");
	$j("#ruleName2").val(ruleName);
	$j("#ruleId2").val(ruleId);
}

function addReceiver(obj) {
	var ruleName = $j(obj).parent().find("#ruleName").val();
	var ruleId = $j(obj).parent().find("#ruleId").val();
	$j("#selectedIDStr").append(
			"<tr><td ondblclick='goDeal(this)'>" + ruleName
					+ "<input type='hidden' name='ruleIds' value='" + ruleId
					+ "' /></td></tr>");

}

function addReceivers(obj) {
	var ruleName = $j("#ruleName2").val();
	var ruleId = $j("#ruleId2").val();
	$j("#selectedIDStr").append(
			"<tr><td ondblclick='goDeal(this)'>" + ruleName
					+ "<input type='hidden' name='ruleIds' value='" + ruleId
					+ "' /></td></tr>");

}

function goDeal(thiss) {
	$j(thiss).parent().remove();
}
