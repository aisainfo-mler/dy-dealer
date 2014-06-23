function commissionRule_detail(commissionId){
	var url = getURL("/commission/ruleDetail.do");
	var target = $j("#sub_Content");
	var data = {
			'ruleCondition.ruleId':commissionId
	};
	loadPage(target,url,data,function(_d){
	});
}

function commissionRule_search(){
//	var data = getFormData($j("#order_main_search_form"),{});
	var data = {};
	
	var url = getURL("/commission/listCommissionRules.do");
	var target = $j("#commissionRule_list");
	loadPage(target,url,data,function(_d){
	});
}

/**
 * 明细返回
 * @return
 */
function commissionRule_return(){
	var url = getURL("/commissionSimple/commissionIndex.do");
	loadPage($j("#sub_Content"),url,{},function(_d){});
}

/**
 * 明细修改
 * @return
 */
function commissionRule_edit(){
	$j("#commissionRule_detail_look").hide();
	$j("#commissionRule_editB").hide();
	$j("#commissionRule_detail_edit").show();
	$j("#commissionRule_cancelB").show();
}

/**
 * 取消明细修改
 * @return
 */
function commissionRule_cancelEdit(){
	$j("#commissionRule_detail_edit").hide();
	$j("#commissionRule_cancelB").hide();
	$j("#commissionRule_detail_look").show();
	$j("#commissionRule_editB").show();
}

/**
 * 添加条件
 * @return
 */
function commissionRule_addCondition(){
//	var oExpress = $j.trim($j("#commissionRule_orginalExpress").val());
//	if(IsSpace(oExpress)){
//		$j.messager.alert(text_common_warning,text_commissionRule_pleaseExpress,"",function(){
//			$j("#commissionRule_orginalExpress").focus();
//		});
//		return false;
//	}else{
		var tmpCondition = $j("#commissionRule_tmpCondition").val();
		var data = {
//				'tmpOrginalExpress':oExpress,
				'ruleCondition.condition':tmpCondition
			};
			var url = getURL("/commissionSimple/addCondition.do");
			openEasyUIWindow(url,data,text_commissionRule_addVariable,250,270,"");
//	}
}

/**
 * 点击确定条件
 * @return
 */
function commissionRule_addCondition_confirm(){
	var selectedCondR = $j("input[name='commissionRule_addCondtionR']:checked");
	if(selectedCondR.length == 0){
		$j.messager.alert(text_common_warning,text_commissionRule_pleaseCondition);
		return false;
	}else{
		closeEasyUIWindow();
		var tmpCondition = $j("#commissionRule_tmpCondition").val();
		$j("#commissionRule_tmpCondition").val(tmpCondition + "#{" +selectedCondR.val() + "}");
		var createDiv = document.createElement('div');
		$j("#commission_conditionDiv").append(createDiv);
		var variantName = selectedCondR.attr("data-name");
		$j(createDiv).html(commissionRule_addConditionTable_html(selectedCondR.val(),variantName));
	}
}


/**
 * 添加区间
 * @return
 */
function commissionRule_addRection(src){
	var conditionTable = $j(src).parent().siblings("table").get(0);
	var indexT = conditionTable.rows.length - 1;
	var tmpId = $j(conditionTable).attr("id");
	tmpId = tmpId.substring(tmpId.lastIndexOf("_") + 1);
//	conditionTable = $j("#" + "commissionRule_test").get(0);
	var conditionRow = conditionTable.insertRow(-1);
	var conditionRowCell = conditionRow.insertCell(-1);
	conditionRowCell.className = "L";
	conditionRowCell.innerHTML = text_commissionRule_condition_from + "：<input type=\"text\" name=\"commissionRule_condition_from_" + tmpId + "_" + indexT + "\" class=\"d_input {required:true,min:0}\"/><span class=\"required\">*</span>&nbsp;&nbsp;&nbsp;" + text_commissionRule_condition_to + "：<input name=\"commissionRule_condition_to_" + tmpId + "_" + indexT + "\" type=\"text\" class=\"d_input {required:true,min:0}\"/><span class=\"required\">*</span>";
	conditionRowCell = conditionRow.insertCell(-1);
	conditionRowCell.innerHTML = "<input name=\"commissionRule_condition_rate_" + tmpId + "_" + indexT + "\" type=\"text\" class=\"d_input {required:true,expressValid:[1, 30]}\"/><span class=\"required\">*</span>";
//	conditionRow.innerHTML = commissionRule_addRection_html();
}
/**
 * 添加表格HTML
 * @return
 */
function commissionRule_addConditionTable_html(variantKey,variantName){
	var orderType = $j("#commissionRule_orderTypeVariant").val();
	var orderTypeHtml = "";
	if(orderType == variantKey){
		orderTypeHtml = $j("#commissionRule_variant_orderTypeD").html();
	}
	var addHtml = "<div class=\"R\"> <input type=\"button\" value=\"" + text_commissionRule_delCondition + "\" onclick=\"commissionRule_delCondition(this,'" + variantKey + "')\" class=\"d_buttonThin4\"/></div>" +
				  "<table class=\"listTable\" border=\"0\" cellspacing=\"0\" cellpadding=\"5\" id=\"commissionRule_condition_table_" + variantKey + "\">" + 
					  "<tr><th class=\"L\" style=\"width:75%\">" + variantName + "&nbsp;" + orderTypeHtml +"</th>" +
					  	   "<th>" + text_commissionRule_condition_rateEx + "</th></tr>" + 
					  "<tr>" + 
						  "<td class=\"L\">" + text_commissionRule_condition_from + "：<input name=\"commissionRule_condition_from_" + variantKey + "_0\" type=\"text\" class=\"d_input {required:true,min:0}\" /><span class=\"required\">*</span>&nbsp;&nbsp;" + text_commissionRule_condition_to + "：<input type=\"text\" name=\"commissionRule_condition_to_" + variantKey + "_0\" class=\"d_input {required:true,min:0}\" /><span class=\"required\">*</span></td>" +
						  "<td><input name=\"commissionRule_condition_rate_" + variantKey + "_0\" type=\"text\" class=\"d_input {required:true,expressValid:[1, 30]}\" /><span class=\"required\">*</span></td>" + 
					  "</tr>" + 
				  "</table>" + 
				  "<div class=\"R\" style=\"padding:10px;\">" + 
					  "<a href=\"javascript:void(0)\" onclick=\"commissionRule_addRection(this)\" class=\"add\" >" + text_commissionRule_addRection + "</a> " + 
					  "<a href=\"javascript:void(0)\" onclick=\"commissionRule_delRection(this)\" class=\"delete\"> " + text_commissionRule_delRection + "</a> " + 
				  "</div>";
	return addHtml;
}

/**
 * 保存规则
 * @return
 */
function commissionRule_save(){
	if(commissionRule_save_check()){
		commissionRule_save_setDate();
		var data = getFormData($j("#commissionRule_detail_editF"),{});
		$j.messager.confirm(text_please_confirm, text_save+"?", function(r){
			if(r){
				var options = { 
						beforeSend:function(xhr){
							$j.messager.progress({
								title:text_waitting,
								msg:text_saving
							});
						},
						success: function(dataObjca){
							$j.messager.progress('close');
							eval("var json = " + dataObjca);
							if(json.flag){
								$j.messager.alert("",text_save_ok+"！",'',function(){
									commissionRule_detail(json.msg);
								});
								
							}else{
								$j.messager.alert(text_save_false,json.msg,"",function(){
									if(json.msg.indexOf("xpress")){//预防express的大小 写
										$j("#commissionRule_orginalExpress").focus();
										$j("#commissionRule_variants_ul").find("b").attr("class","red");
									}
								});
							}
				        },
				        error:function(xhr,status,exception){
				        	$j.messager.progress('close');
							$j.messager.alert("",xhr.responseText);
						}
				};
				$j("#commissionRule_detail_editF").ajaxSubmit(options);
			}
		});
	}
	
}


/**
 * 保存前校验
 * @return
 */
function commissionRule_save_check(){
	//validate
	$j.ajaxSetup({ async : false });
	var ruleForm = $j("#commissionRule_detail_editF");
	var expressMsg = text_commissionRule_forExampleExpress;
	if($j.validator.methods['expressValid'] == null || $j.validator.methods['expressValid'] == undefined){
		$j.validator.addMethod("expressValid",function(value,element,param){
			var ajaxFlag = false;
			if(commissionRule_validOriginal($j.trim(element.value))){
				var length = this.getLength(jQuery.trim(value), element);
				if(this.optional(element) || ( length >= param[0] && length <= param[1])){
					var url = getURL("/commission/validExpress.do");
					var backEx = commissionRule_getBackExpressByOriginal($j.trim(element.value));
					$j.ajax({ 
						url: url,
						data:{
							'tmpOrginalExpress':backEx
						},
						success: function(dataObj){
							eval("var json = " + dataObj);
							ajaxFlag = json.flag;
				        }
					});
				}
			}else{
				$j("#commissionRule_variants_ul").find("b").attr("class","red");
				expressMsg = text_commissionRule_pleaseRightVar;
			}
			return ajaxFlag;
		},expressMsg);
	}

	ruleForm.validate({
		errorElement :"div",// 使用"div"标签标记错误， 默认:"label"
		wrapper:"li",// 使用"li"标签再把上边的errorELement包起来
		errorClass :"WarningClew",// 错误提示的css类名"error"
		focusCleanup:true// 当未通过验证的元素获得焦点时,并移除错误提示
//		onfocusout:true
	});
	
	if(ruleForm.validate().form() == false)
	{
		$j.ajaxSetup({ async : true });
		return false;
	}else{
		$j.ajaxSetup({ async : true });
		return true;	 
	}
	
	
	
}

/**
 * 保存前的取值
 * @return
 */
function commissionRule_save_setDate(){
	
	//1.设置表达式
	var oExpress = $j.trim($j("#commissionRule_orginalExpress").val());
	var backExpress = commissionRule_getBackExpressByOriginal(oExpress);
	$j("input[name='rule.backExpress']").val(backExpress);
	
	//2.设置条件式
	var tables = $j("#commissionRule_detail_editF table[id^='commissionRule_condition_table_']");
	var tcell = null;
	var conditions = "";
	var tmpId = "";
	for(var i = 0; i < tables.length; i++){
		tmpId = $j(tables[i]).attr("id");
		conditions  += tmpId.substring(tmpId.lastIndexOf("_") + 1) + ":";
		if(tables[i].rows.length >= 2){
			for(var j = 1; j < tables[i].rows.length; j++){
				tcell = tables[i].rows[j].cells[0];
				conditions += $j(tcell).children("input").eq(0).val() + "~";//from
				conditions += $j(tcell).children("input").eq(1).val() + "~";//to
				tcell = tables[i].rows[j].cells[1];
				conditions += $j(tcell).children("input").val() + ";";//rate(不含%)
			}
		}
	}
//	alert(conditions);
	$j("input[name='rule.condition']").val(conditions);
}

/**
 * 判断原表达式是否符合规范
 * @return
 */
function commissionRule_validOriginal(oExpress){
	//判断表达式是否合规范
	var flag = true;//是否单独字母标识   以排除sin log 等表达式内的函数名
	var resultFlag = true;//返回的结果标识  变量名是否符合当前库存
	var rateFlag = false;//是否含有rate变量名
	var rateVariant = $j("#commissionRule_rateVariant").val();
	var allVariants = commissionRule_getAllStaticVariants();
	if(IsSpace(oExpress)){
		return false;
	}else{
		for(var i = 0; i < oExpress.length; i++){
			if(/^[a-zA-Z]$/g.test(oExpress.charAt(i))){
				if( i != 0){
					if(/^[a-zA-Z]$/g.test(oExpress.charAt(i - 1))){
						flag = false;
					}
				}else if(i != oExpress.length - 1){
					if(/^[a-zA-Z]$/g.test(oExpress.charAt(i + 1))){
						flag = false;
					}
				}
				
				if(flag){
					if(allVariants.indexOf(oExpress.charAt(i)) == -1){
						resultFlag = false;
					}else if(oExpress.charAt(i) == rateVariant){
						rateFlag = true;
					}
					
				}
				
			}else{
				flag = false;
			}
			flag = true;
		}
		return resultFlag;	
	}
}

/**
 * 获得#{}可计算表达式
 * @return
 */
function commissionRule_getBackExpressByOriginal(oExpress){
	var backExpress = "";
	var flag = true;
	for(var i = 0; i < oExpress.length; i++){
		if(/^[a-zA-Z]$/g.test(oExpress.charAt(i))){
			if( i != 0){
				if(/^[a-zA-Z]$/g.test(oExpress.charAt(i - 1))){
					flag = false;
				}
			}else if(i != oExpress.length - 1){
				if(/^[a-zA-Z]$/g.test(oExpress.charAt(i + 1))){
					flag = false;
				}
			}
			
		}else{
			flag = false;
		}
		if(flag){
			backExpress += "#{" + oExpress.charAt(i) + "}";
		}else{
			backExpress += oExpress.charAt(i);
		}
		flag = true;
	}
	return backExpress;
}
/**
 * 删除一个条件
 * @param src
 * @return
 */
function commissionRule_delCondition(src,tabledId){
	var tmpCondition = $j("#commissionRule_tmpCondition").val();
	$j("#commissionRule_tmpCondition").val(tmpCondition.replace("#{" + tabledId + "}",""));
	$j(src).parent().parent().remove();
}

/**
 * 删除一个条件里的一个范围
 * @return
 */
function commissionRule_delRection(src){
	var conditionTable = $j(src).parent().siblings("table").get(0);
	if(conditionTable.rows.length <= 2){
		return false;
	}else{
		conditionTable.deleteRow(-1);
	}
}

/**
 * 添加一条佣金
 * @return
 */
function commissionRule_addRule(){
	var url = getURL("/commissionSimple/ruleDetail.do");
	var target = $j("#sub_Content");
	loadPage(target,url,{},function(_d){
	});
}

/**
 * 删除一条佣金
 * @return
 */
function commissionRule_delete(ruleId){
	
	$j.messager.confirm(text_please_confirm, text_delete+"?", function(r){
		if(r){
			var url = getURL("/commission/delRule.do");
			var data = {'rule.ruleId':ruleId};
			$j.ajax({ 
				url: url,
				data:data,
				beforeSend:function(xhr){
					$j.messager.progress({
						title:text_waitting,
						msg:text_deleting
					});
				},
				success: function(dataObj){
					$j.messager.progress('close');
					eval("var json = " + dataObj);
					if(json.flag){
						$j.messager.alert("",text_delete_ok+"！");
						commissionRule_return();
					}else{
						$j.messager.alert(text_delete_false,json.msg);
					}
		        },
		        error:function(xhr,status,exception){
		        	$j.messager.progress('close');
					$j.messager.alert("",xhr.responseText);
				}
		});
		}
	});
}

/**
 * 获得所有的变量名字符串
 * @return
 */
function commissionRule_getAllStaticVariants(){
	var str = "";
	var lis = $j("#commissionRule_variants_ul").children("li");
	for(var i = 0; i < lis.length; i++){
		str += lis.eq(i).attr("data-key");
	}
	return str;
}

function changeRuleBackType(oBackType){
	$j("input[name='rule.modId']").val("");
	if(oBackType.value=='3'){
		$j("#trCommissionCode").css({display:''});
	}else{
		$j("#trCommissionCode").css({display:'none'});
	}
}

