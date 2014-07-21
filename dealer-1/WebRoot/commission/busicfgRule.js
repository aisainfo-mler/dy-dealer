/**
 * 详情
 * 
 * @param subSystemId,busiCode
 */
function busicfg_detail(subSystemId, obj) {
	var url = getURL("/busicfg/busicfgEdit.do");
	var target = $j("#sub_Content");
	var data = {
		'busi.busiCode' : $j(obj).next().attr("value"),
		'busi.subSystemId' : subSystemId
	};
	loadPage(target, url, data, function(_d) {
	});
}
/**
 * 查询
 */
function busicfg_search() {
	var data = getFormData($j("#busic_main_search_form"), {});
	var url = getURL("/busicfg/busicfgListMain.do");
	var target = $j("#busicfg_list");
	loadPage(target, url, data, function(_d) {
	});
}

/**
 * 指标返回
 * 
 * @return
 */
function busicfg_return() {
	var url = getURL("/busicfgSimple/busicfgIndex.do");
	loadPage($j("#sub_Content"), url, {}, function(_d) {
	});
}

/**
 * 添加一条指标
 * 
 * @return
 */
function save() {
	var busiCode = $j("#busicfg_save_check_form input[name='busi.busiCode']")
			.val();
	var remark = $j("#busicfg_save_check_form input[name='busi.remark']").val();
	if (IsSpace(busiCode)) {
		$j.messager.alert(text_common_warning, "please enter busiCode",
				text_common_warning);
		$j("#busicfg_save_check_form input[name='busi.busiCode']").focus();
		return;
	}
	if (IsSpace(remark)) {
		$j.messager.alert(text_common_warning, "please enter remark",
				text_common_warning);
		$j("#busicfg_save_check_form input[name='busi.remark']").focus();
		return;
	}
	$j.messager.confirm("操作提示", "您确定要执行操作吗？", function(r) {
		if (r) {
			var data = getFormData($j("#busicfg_save_check_form"), {});
			var url  = getURL("/busicfg/save.do");
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
function busicfg_edit() {
	var remark = $j("#busicfg_edit_check_form input[name='busi.remark']").val();
	if (IsSpace(remark)) {
		$j.messager.alert(text_common_warning, "please enter remark",
				text_common_warning);
		$j("#busicfg_edit_check_form input[name='busi.remark']").focus();
		return;
	}
	$j.messager.confirm("操作提示", "您确定要执行操作吗？", function(r) {
		if (r) {
			var data = getFormData($j("#busicfg_edit_check_form"), {});
			var url = "";
			var target = $j("#sub_Content");
			url = getURL("/busicfg/update.do");
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
 * 查询指标的类型
 */
function busicfg_add() {
	var data = null;
	var url = getURL("/busicfg/busicfgAdd.do");
	var target = $j("#sub_Content");
	loadPage(target, url, data, function(_d) {
	});
}

/**
 * 验证指标编码
 * 
 * @param thiss
 * @param subSystemId
 */
function checkBusiCode(thiss, subSystemId) {
	if (thiss.value != "") {
		var subSystem_Id = $j("#" + subSystemId).val();
		var url = getURL("/busicfg/checkOnlyUser.do");
		var data = {
			'busi.busiCode' : thiss.value,
			'busi.subSystemId' : subSystem_Id
		};
		var xx = $j("#UId");
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
				if (json.result == "true") {
					xx.html("该指标已经存在！");
					thiss.focus();
					thiss.value = "";
				} else {
					xx.html("正常可用！");
				}
			},
			error : function(xhr, status, exception) {
				$j.messager.progress('close');
				$j.messager.alert("", xhr.responseText);
			}
		});

	}

}