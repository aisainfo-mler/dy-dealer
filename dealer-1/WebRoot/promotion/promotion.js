/**
 * 广告搜索
 * @return
 */
function promotion_search(){
	var data = getFormData($j("#promotion_query_form"),{});

	var url = getURL("/promotion/promotionList.do");
	var target = $j("#promotion_list");
	loadPage(target,url,data,function(_d){
	});
}

/**
 * 进入添加页面/编辑
 * @return
 */
function promotion_edit(promotionId){
	var url = "";
	var target = $j("#sub_Content");
	var data = null;
	if(IsSpace(promotionId)){
		url = getURL("/promotionSimple/editPromotion.do");
	}else{
		url = getURL("/promotion/editPromotion.do");
		data = {
				'promotion.id':promotionId
		};
	}
	
	loadPage(target,url,data,function(_d){
	});
}


function promotion_save_check(){
	var form = $j("#promotion_detail_form");
	
	form.validate({
		errorElement :"div",// 使用"div"标签标记错误， 默认:"label"
		wrapper:"li",// 使用"li"标签再把上边的errorELement包起来
		errorClass :"WarningClew",// 错误提示的css类名"error"
		focusCleanup:true// 当未通过验证的元素获得焦点时,并移除错误提示
//		onfocusout:true
	});
	
	if(form.validate().form() == false)
	{
//		$j.messager.alert("Warning","数据有误!");
		return false;
	}
	
	return true;
}

/**
 * 保存
 * @return
 */
function promotion_save(){
	if(promotion_save_check()){
//		var data = getFormData($j("#promotion_detail_form"),{});
		var options = { 
//				data:data,
				beforeSend:function(xhr){
					$j.messager.progress({
						title:'请稍等',
						msg:'正在保存...'
					});
				},
				success: function(dataObjca){
					$j.messager.progress('close');
					eval("var json = " + dataObjca);
					if(json.flag){
						$j.messager.alert("","保存成功！",'',function(){
//							promotionId = $j("input[name='promotion.id']").val();
							promotionId = json.msg;
							promotion_edit(promotionId);
						});
						
					}else{
						$j.messager.alert("保存失败",json.msg);
					}
		        },
		        error:function(xhr,status,exception){
		        	$j.messager.progress('close');
					$j.messager.alert("",xhr.responseText);
				}
		};
		$j('#promotion_detail_form').ajaxSubmit(options);
	}
}


/**
 * 明细返回
 * @return
 */
function promotion_return(){
	var url = getURL("/promotionSimple/promotionIndex.do");
	loadPage($j("#sub_Content"),url,{},function(_d){});
}
