
/**
 * 订单搜索
 * @return
 */
function order_main_search(){
	var data = getFormData($j("#order_main_search_form"),{});

	var url = getURL("/order/listOrderMain.do");
	var target = $j("#order_main_div");
	loadPage(target,url,data,function(_d){
	});
}

/**
 * 进入明细
 * @return
 */
function order_detail(order_id){
//	var data = getFormData($j("#order_main_search"),{});

	var url = getURL("/order/orderDetail.do");
	var target = $j("#sub_Content");
	var data = {
			'orderMain.id':order_id
	};
	loadPage(target,url,data,function(_d){
	});
} 

/**
 * 发货前的清单校验以及物流信息检查
 * @return
 */
function order_send_pre(){
	var count1=document.getElementsByName("order_item_count");
	var count2=document.getElementsByName("order_item_count_processed");
	for(var i=0;i<count1.length;i++){
		var t1=parseInt(count1[i].value);
		var t2=parseInt(count2[i].value);
		if(t1!=t2){
			$j.messager.alert(text_common_warning,text_send_good_list_unsame+"！");
			return false;
		}
	}
	return true;
}

/**
 * 打开发货界面
 * @return
 */
function order_open_send(orderInfoId){
	if(!order_send_pre()){
		return false;
	}
	var url = getURL("/orderSimple/orderSend.do");
	var data = {
			'orderMain.id':orderInfoId
	};
	openEasyUIWindow(url,data,text_send_good,450, 300,"");
}

/**
 * 确认发货
 * @return
 */
function order_confirm_send(){
	var sendNum = $j("#order_send_form input[name='orderMain.expressNumber']").val();
	if(IsSpace(sendNum)){
		$j.messager.alert(text_common_warning,"please enter expressNum",text_common_warning);
		$j("#order_send_form input[name='orderMain.expressNumber']").focus();
		return;
	}
	var objCompany=$j("#order_send_form select[name='orderMain.expressCompanyNO']")[0];
	$j("#order_send_form input[name='orderMain.expressCompanyName']").val(objCompany.options[objCompany.selectedIndex].text)
	
	$j.messager.confirm(text_please_confirm, text_send_good+"?", function(r){
		if(r){
			var url = getURL("/order/saveSend.do");
			var data = {};
			data = getFormData($j("#order_send_form"),data);
			$j.ajax({ 
				url: url,
				data:data,
				beforeSend:function(xhr){
					$j.messager.progress({
						title:text_waitting,
						msg:text_saving
					});
				},
				success: function(dataObj){
					$j.messager.progress('close');
					eval("var json = " + dataObj);
					if(json.flag){
						$j.messager.alert("",text_save_ok+"！");
//						window.location.reload(true);
						closeEasyUIWindow();
						var isHome = $j("input[name='orderDetail_isHome']").val();
						if(isHome == '1' || isHome == 1){//如果是主页上的订单处理
							closeEasyUIWindow($j("#main_backDiv"));
							goRefreshHomeInventoryOrder();
						}else{
							order_detail_return();
						}
					}else{
						$j.messager.alert(text_save_false,json.msg);
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
 * 打开货物清单
 * @param expressNum  运单号  如果有  则不能修改货物清单
 * @param detailId
 * @return
 */
function order_itemList(expressNum,detailId,goodId,counts,agentId){
	
	var url = getURL("/order/orderItem.do");
	var data = {
			'orderDetail.id':detailId,
			'orderDetail.good.id':goodId,
			'orderDetail.counts':counts,
			'orderDetail.agentId':agentId
	};
	
	if(IsSpace(expressNum)){//如果没有物流信息  则表示可修改货物清单，  之后关联权限
		data['isWrite'] = '1';
	}else{
		data['isWrite'] = '0';
	}
	
//	alert(data['isWrite']);
	openEasyUIWindow(url,data,text_good_list,660, 400,"");
}

/**
 * 更换输入方式
 * @return
 */
function order_chooseItemInputType(src){
	$j("#order_manual_inputItem_text").val("");
	var srcValue = $j(src).val();
	if(srcValue == "0"){//机器扫描
		$j("#order_inputItem_batch_tr").hide();
		$j("#order_manual_inputItem_text").attr("readOnly",true).hide();
		$j("#order_manual_inputItem_button").attr("disabled",true).hide();
		$j("#order_machine_inputItem_button").removeAttr("disabled").show();
	}else if(srcValue == "1"){//手动输入 
		$j("#order_inputItem_batch_tr").hide();
		$j("#order_manual_inputItem_text").removeAttr("readOnly").show();
		$j("#order_manual_inputItem_button").removeAttr("disabled").show();
		$j("#order_machine_inputItem_button").attr("disabled",true).hide();
	}else{//批量输入
		$j("#order_manual_inputItem_text").attr("readOnly",true).hide();
		$j("#order_manual_inputItem_button").attr("disabled",true).hide();
		$j("#order_machine_inputItem_button").attr("disabled",true).hide();
		
		$j("#order_inputItem_batch_tr").show();
	}
}

/**
 * 机器扫描输入
 * @return
 */
function order_machine_inputItem(){
	
}

/**
 * 写入Item信息
 * @return
 */
function order_inputItem(itemValue,isWrite){
	var stardardNum = parseInt($j("input[name='order_item_num_stard']:hidden").val());//标准输入量
	
	var orderItemNum = $j("b[name='order_item_num']").html();//实际输入量 
	var orderItemLeftNum = $j("b[name='order_item_leftNum']").html();//剩余输入量 
	if(!IsSpace(orderItemNum)){
		orderItemNum = parseInt(orderItemNum);
	}else{
		orderItemNum = 0;
	}
	if(!IsSpace(orderItemLeftNum)){
		orderItemLeftNum = parseInt(orderItemLeftNum);
	}else{
		orderItemLeftNum = 0;
	}
	if(orderItemNum + 1 > stardardNum){
		$j.messager.alert(text_common_warning,text_good_number_ok+"！");
		return false;
	}
	if(IsSpace(itemValue)){
		itemValue = $j.trim($j("#order_manual_inputItem_text").val());
		$j("#order_manual_inputItem_text").val("");
	}
	if(IsSpace(itemValue)){//不能为空
		$j.messager.alert(text_common_warning,text_data_not_write,text_common_warning);
		return;
	}
	
	var result_json = order_itemValuelimit(itemValue);
	if(!result_json.flag){
		$j.messager.alert(text_common_warning,result_json.msg,text_common_warning);
		return;
	}
	
	if(IsSpace(itemValue)){//不能为空
		$j.messager.alert(text_common_warning,text_data_not_write,text_common_warning);
		return;
	}
	var orderItemValues = $j("input[name='orderItemValues']").val();
	if(order_itemValue_isDuplicate(itemValue,"","")){//不能重复
		$j.messager.alert(text_common_warning,text_data_has_duplicate,text_common_warning);
		return;
	}else{
		var cargoInfoCon=document.createElement('div');
		cargoInfoCon.className="cargoInfoItem";
		cargoInfoCon.innerHTML="<span name=\"order_item_span\">" + itemValue + "</span>";
		if(isWrite == 1 || isWrite == '1'){
			cargoInfoCon.innerHTML += "<a href=\"javascript:void(0);\" class=\"closeBtn\" onclick=\"order_deleteItem(this)\">X</a>";
		}
		var cargoInfo = $j("div .cargoInfo").get(0);
		cargoInfo.appendChild(cargoInfoCon);
		orderItemValues += ",;_1_" + itemValue ;//1表示手动输入
		
		$j("b[name='order_item_num']").html(orderItemNum + 1);
		$j("b[name='order_item_leftNum']").html(orderItemLeftNum - 1)
		$j("input[name='orderItemValues']").val(orderItemValues);
	}
	
}

/**
 * 批量输入  定单信息
 * @param itemValue
 * @param isWrite
 * @return
 */
function order_batch_inputItem(isWrite){
	var stardardNum = parseInt($j("input[name='order_item_num_stard']:hidden").val());//标准输入量
	
	var orderItemNum = $j("b[name='order_item_num']").html();//实际输入量 
	var orderItemLeftNum = $j("b[name='order_item_leftNum']").html();//剩余输入量 
	if(!IsSpace(orderItemNum)){
		orderItemNum = parseInt(orderItemNum);
	}else{
		orderItemNum = 0;
	}
	if(!IsSpace(orderItemLeftNum)){
		orderItemLeftNum = parseInt(orderItemLeftNum);
	}else{
		orderItemLeftNum = 0;
	}
	
	if(orderItemNum + 1 > stardardNum){//满了不能输
		$j.messager.alert(text_common_warning,text_good_number_ok+"！");
		return false;
	}
	
	
	var fromStr = $j.trim($j("#order_inputItem_batch_from").val());
	var endStr = $j.trim($j("#order_inputItem_batch_end").val());
	
	
	if(/^\d+$/g.test(fromStr) && /^\d+$/g.test(endStr) && (fromStr.length == endStr.length) && (fromStr.length <= 18)){
		
		var unitRemark = fromStr + "--" + endStr;
		var fromNum = StringToNum(fromStr);
		var endNum = StringToNum(endStr);
		
		var total = endNum - fromNum;
		if(total < 0){
			total = -total;
		}
		total += 1;
		if(stardardNum < (orderItemNum + total)){//量多了也不能输
			$j.messager.alert(text_common_warning,text_good_number_pass +"！");
			return false;
		}
		if(order_itemValue_isDuplicate("",fromStr,endStr)){//不能重复
			$j.messager.alert(text_common_warning,text_data_has_duplicate,text_common_warning);
			return false;
		}else{
			$j("#order_inputItem_batch_from").val("");
			$j("#order_inputItem_batch_end").val("");
			var orderItemBatchValues = $j("input[name='orderItemBatchValues']").val();
			var cargoInfoCon=document.createElement('div');
			if(fromStr.length >= 9){
				cargoInfoCon.className="cargoInfoItem_batch";
			}else{
				cargoInfoCon.className="cargoInfoItem";
			}
//			cargoInfoCon.innerHTML="<span name=\"order_item_span\">" + unitRemark + "(" + total + text_total_unit + ")</span>";
			cargoInfoCon.innerHTML="<span name=\"order_item_span\">" + unitRemark + "(" + total + ")</span>";
			unitRemark += "--" + total + ";";
			if(isWrite == 1 || isWrite == '1'){
				cargoInfoCon.innerHTML += "<a href=\"javascript:void(0);\" class=\"closeBtn\" onclick=\"order_deleteItem(this)\">X</a>";
			}
			var cargoInfo = $j("div .cargoInfo").get(0);
			cargoInfo.appendChild(cargoInfoCon);
			orderItemBatchValues += unitRemark ;
			
			$j("b[name='order_item_num']").html(orderItemNum + total);
			$j("b[name='order_item_leftNum']").html(orderItemLeftNum - total)
			$j("input[name='orderItemBatchValues']").val(orderItemBatchValues);
		}
	}else{
		$j.messager.alert(text_common_warning,text_must_number +"！");
		return;
	}
	
	
	
}

/**
 * 对输入的值判断是否有重复
 * @param itemValue 单个值  扫描、手动
 * @param fromStr  批量
 * @param endStr
 * @return
 */
function order_itemValue_isDuplicate(itemValue,fromStr,endStr){
	var flag = false;
	var orderItemValues = $j("input[name='orderItemValues']").val();//单个输入群体
	var orderItemBatchValues = $j("input[name='orderItemBatchValues']").val();//批量输入群体
	if(IsSpace(fromStr) && IsSpace(endStr)){//单个输入
		if((orderItemValues + ",").indexOf("_" + itemValue + ",") != -1){//在单个输入群体比较  前面加,是因为每个单元的开始符号  保证过滤一同值不等长的  如  001和00
			flag = true;//有重复
			return flag;
		}else{
			if(IsSpace(orderItemBatchValues)){
				flag = false;
				return flag;
			}else if(/^\d+$/g.test(itemValue)){//如果是数字才比较
				
				var batchArr = orderItemBatchValues.split(";");
				var batchUnitFrom = null;
				var batchUnitEnd = null;
				var batchUnitArr = null;
				var itemNum = StringToNum(itemValue);
				for(var i = 0; i < batchArr.length; i++){
					batchUnitArr = batchArr[i].split("--");
					if(batchUnitArr[0].length == itemValue.length){//必须等长
						batchUnitFrom = StringToNum(batchUnitArr[0]);
						batchUnitEnd = StringToNum(batchUnitArr[1]);
						if(batchUnitEnd >= batchUnitFrom && itemNum >= batchUnitFrom && itemNum <= batchUnitEnd){
							flag = true;//有重复
							return flag;
						}else if(batchUnitFrom >= batchUnitEnd && itemNum <= batchUnitFrom && itemNum >= batchUnitEnd){
							flag = true;//有重复
							return flag;
						}
					}else if(i == batchArr.length - 1){
						flag = false;
					}
				}
				return flag;
			}else{//如果非数字
				flag = false;
				return flag;
			}
		}
	}else if(/^\d+$/g.test(fromStr) && /^\d+$/g.test(endStr)){//批量输入
		//与批量
		var batchArr = orderItemBatchValues.split(";");
		var batchUnitFrom = null;
		var batchUnitEnd = null;
		var batchUnitArr = null;
		var fromNum = StringToNum(fromStr);
		var endNum = StringToNum(endStr);
		for(var i = 0; i < batchArr.length; i++){
			batchUnitArr = batchArr[i].split("--");
			if(fromStr.length == batchUnitArr[0].length){//必须等长
				batchUnitFrom = StringToNum(batchUnitArr[0]);
				batchUnitEnd = StringToNum(batchUnitArr[1]);
				if(batchUnitEnd >= batchUnitFrom && ((fromNum >= batchUnitFrom && fromNum <= batchUnitEnd) || (endNum >= batchUnitFrom && endNum <= batchUnitEnd))){
					flag = true;//有重复
					return flag;
				}else if(batchUnitFrom >= batchUnitEnd && ((fromNum <= batchUnitFrom && fromNum >= batchUnitEnd)||(endNum <= batchUnitFrom && endNum >= batchUnitEnd))){
					flag = true;//有重复
					return flag;
				}
			}else if(i == batchArr.length - 1){
				flag = false;
			}
		}
		
		var singleArr = orderItemValues.split(/,;_\d?_/g);
		var singleNum = null;
		for(var i = 0; i < singleArr.length; i++){
			if(/^\d+$/g.test(singleArr[i]) && singleArr[i].length == fromStr.length){
				singleNum = StringToNum(singleArr[i]);
				if(endNum >= fromNum && singleNum >= fromNum && singleNum <= endNum){
					flag = true;//有重复
					return flag;
				}else if(fromNum >= endNum && singleNum <= fromNum && singleNum >= endNum){
					flag = true;//有重复
					return flag;
				}
			}
		}
		return flag;
	}
}

/**
 * 删除item 小块 orderItemValues  以,;分隔
 * @param src
 * @return
 */
function order_deleteItem(src){
	var unitItemValue = $j(src).siblings("span").html();
	var orderItemValues = null;
	var batchFlag = false;
	var fromStr = null;
	var endStr = null;
	var total = 1;
	if(unitItemValue.indexOf("--") != -1){
		batchFlag = true;
		orderItemValues = $j("input[name='orderItemBatchValues']").val();
		var startIndex = unitItemValue.indexOf("(");
		var endIndex = unitItemValue.indexOf(")");
		var totalStr = unitItemValue.substring(startIndex + 1,endIndex);
		unitItemValue = unitItemValue.substring(0,startIndex) + "--" + totalStr; 
		total = parseInt(totalStr);
	}else{
		orderItemValues = $j("input[name='orderItemValues']").val();
	}
	
	var orderItemNum = $j("b[name='order_item_num']").html();
	var orderItemLeftNum = $j("b[name='order_item_leftNum']").html();//剩余输入量 
	if(!IsSpace(orderItemNum)){
		orderItemNum = parseInt(orderItemNum);
		$j("b[name='order_item_num']").html(orderItemNum - total);
	}
	if(!IsSpace(orderItemLeftNum)){
		orderItemLeftNum = parseInt(orderItemLeftNum);
		$j("b[name='order_item_leftNum']").html(orderItemLeftNum + total);
	}
	var itemValue = null;
	if(batchFlag){
		itemValue = unitItemValue + ";";
		$j("input[name='orderItemBatchValues']").val(orderItemValues.replace(itemValue,""));
	}else{
		eval("itemValue = /,;_\\d?_" + unitItemValue + "/g");
		$j("input[name='orderItemValues']").val(orderItemValues.replace(itemValue,""));
	}
	
	var itemDiv = $j(src).parent("div").remove();
}
/**
 * 保存item信息
 * @return
 */
function order_itemSave(){
	var orderItemValues = $j("input[name='orderItemValues']").val();
	var orderItemBatchValues = $j("input[name='orderItemBatchValues']").val()
	if(IsSpace(orderItemValues) && IsSpace(orderItemBatchValues)){
		$j.messager.alert(text_common_warning,text_lack_good,text_common_warning);
		return;
	}
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
								var orderItemNum = $j("b[name='order_item_num']").html();
								
//								var ll = orderItemValues.split(',;').length;
								var detailId = $j("input[name='orderDetail.id']").val();
								$j("span[name='order_detail_num_"+ detailId +"']").html(orderItemNum);
								$j("#order_item_count_processed"+detailId).val(orderItemNum);
								closeEasyUIWindow();
							});
							
						}else{
							$j.messager.alert(text_save_false,json.msg);
						}
			        },
			        error:function(xhr,status,exception){
			        	$j.messager.progress('close');
						$j.messager.alert("",xhr.responseText);
					}
			};
			$j("#order_saveOrder_form").ajaxSubmit(options);
		}
	});
	
}

/**
 * 明细返回
 * @return
 */
function order_detail_return(){
	var url = getURL("/orderSimple/orderIndex.do");
	loadPage($j("#sub_Content"),url,{},function(_d){});
}

function order_itemValuelimit(itemValue){
	var result = {};
	result.flag = true;
	if(/^[A-Za-z0-9_]+$/.test(itemValue)){
		if(itemValue.length > 18){
			result.flag = false;
			result.msg = text_itemValue_tooLong;
		}
	}else{
		result.flag = false;
		result.msg = text_usercode_check;
	}
	return result;
}

/**
 * 向TIBCO确认发起订单
 * @return
 */
function order_tibco(orderId){
//	alert("Tibco");
	$j.messager.confirm(text_please_confirm, text_confirm2tibco + "?", function(r){
		if(r){
			var url = getURL("/order/placeTibco.do");
			var data =  {
					'orderMain.id':orderId
			};
			$j.ajax({ 
				url: url,
				data:data,
				beforeSend:function(xhr){
					$j.messager.progress({
						title:text_waitting,
						msg:text_saving
					});
				},
				success: function(dataObj){
					$j.messager.progress('close');
					eval("var json = " + dataObj);
					if(json.flag){
						$j.messager.alert("",text_save_ok+"！");
						order_detail_return();
						
					}else{
						$j.messager.alert(text_save_false,json.msg);
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
