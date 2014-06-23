
/**
 * 用户搜索
 * @return
 */
function agentOrder_main_search(){
	var data = getFormData($j("#agentOrder_main_search_form"),{});

	var url = getURL("/agentOrder/agentOrderList.do");
	var target = $j("#agentOrder_main_div");
	loadPage(target,url,data,function(_d){
	});
}

/**
 * 进行审核
 * @return
 */
function agentOrder_audit(orderId){
	$j.messager.confirm(text_please_confirm, text_ok+"?", function(r){
		if(r){
			var url = getURL("/agentOrder/auditAgentOrder.do");
			var data = {
					'agentOrderCondition.orderId':orderId
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
 * 查看用户信息
 * @return
 */
function agentOrder_lookUserInfo(svnNum){
	if(IsSpace(svnNum)){
		$j.messager.alert(text_common_warning,text_svn_lose);
		return false;
	}else{
		var data = {
				'agentOrderCondition.svn':svnNum
			};
			var url = getURL("/agentOrder/userInfo.do");
//			var target = $j("#agentOrder_main_div");
			openEasyUIWindow(url,data,text_user_info,650,550,"");
	}
	
}

/**
 * 查看订单明细
 * @return
 */
function agentOrder_detail(orderId,orderCode){
	var data = {
			'agentOrderCondition.orderId':orderId
		};
	var url = getURL("/agentOrder/agentOrderDetail.do");
	openEasyUIWindow(url,data,text_main_orderDetail,650,550,"");
}
