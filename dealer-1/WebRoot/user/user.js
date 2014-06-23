
/**
 * 用户搜索
 * @return
 */
function user_main_search(){
	var data = getFormData($j("#user_main_search_form"),{});

	var url = getURL("/user/userList.do");
	var target = $j("#user_main_div");
	loadPage(target,url,data,function(_d){
	});
}

/**
 * 修改状态
 * @return
 */
function user_change_status(userId,status,textV){
	if(status == 0 || status == '0'){
		status = '0';
	}
	if(IsSpace(userId) || IsSpace(status)){
		$j.messager.alert(text_common_warning,text_user_info_lost);
		return false;
	}
	var tempD = $j("#common_backDiv textarea[name='userUnit_reason']");
	
	var reason = $j.trim(tempD.val());
	if(status == '0'){
		if(IsSpace(reason)){
			$j.messager.alert(text_common_warning,text_please_fill_reason+"!");
			tempD.focus();
			return false;
		} else if(reason.length > 100){
			$j.messager.alert(text_common_warning,text_reason_100+"!");
			tempD.focus();
			return false;
		}
	}
	$j.messager.confirm(text_please_confirm, textV + "?", function(r){
		if(r){
			var url = getURL("/user/saveUser.do");
			var data =  {
					'userCondition.userId':userId,
					'userCondition.status':status,
					'userCondition.reason':reason
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
						closeEasyUIWindow();
						var l = $j("#userManager_main_search_form");
						if(l.length == 0){
							user_list_refresh();
						}else{
							userManager_main_search();
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
 * 用户列表刷新
 * @return
 */
function user_list_refresh(){
	var url = getURL("/userSimple/userIndex.do");
	loadPage($j("#sub_Content"),url,{},function(_d){});
}

/**
 * 用户详情
 * @return
 */
function user_detail(src){
	var target = $j(src).parent().parent().next('tr').children("td");
	justOpenEasyUIWindow(text_user_info,650,550,target);
}

/**
 * 用户管理界面下的用户搜索
 * @return
 */
function userManager_main_search(){
	var data = getFormData($j("#userManager_main_search_form"),{});

	var url = getURL("/user/userManagerList.do");
	var target = $j("#user_main_div");
	loadPage(target,url,data,function(_d){
	});
}

/**
 * 打开用户修改密码
 * @return
 */
function userManager_openChangePassword(userId,userCode){
	if(IsSpace(userId)){
		$j.messager.alert(text_common_warning,text_user_info_lost);
		return false;
	}else{
		$j("input[name='userManager_changePwUserId']").val(userId);
		$j("td[name='userManager_changePwUserCode']").html(userCode);
//		var title = text_change_pwd+"：" + userCode;
		var title = text_change_pwd;
		justOpenEasyUIWindow(title,300,250,$j("#userManager_changePassword_div"));
	}
}

/**
 * 保存修改的密码
 * @return
 */
function userManager_changePassword(){
	var userId = $j("#common_backDiv input[name='userManager_changePwUserId']:hidden").val();
	if(IsSpace(userId)){
		$j.messager.alert(text_common_warning,text_user_info_lost);
		return false;
	}
	
	if(commonValidate($j("#common_backDiv form[name='userManager_changePassword_form']")) == false){
		return false;
	}
	
	var password1 = $j("#common_backDiv input[name='userConditionFirstPassword']").val();
	var password2 = $j("#common_backDiv input[name='userConditionSecondPassword']").val();
	if(password1 != password2){
		$j.messager.alert(text_common_warning,text_pwd_unsame);
		return false;
	}
	
	
	$j.messager.confirm(text_please_confirm,text_change_pwd, function(r){
		if(r){
			var url = getURL("/user/savePassword.do");
			var data = {
					'userCondition.userId':userId,
					'userCondition.password':password1
			}
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
						closeEasyUIWindow();
						$j("form[name='userManager_changePassword_form']").reset();
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


function goCommissionRuleSet(userId){
	var data = {
			'hwAgent2CommissionRule.agentId':userId
			};
	var url = getURL("/user/ruleSetInit.do");
	openEasyUIWindow(url,data,text_user_setRules,800,500,"");
}

function goQueryUnJoinCommissionRule(){
	var queryForm=$j('#unjoin_rule_search_form');
	var data={};
	if(queryForm != null && queryForm != undefined){
		data = getFormData(queryForm,data);
	}
	var url = getURL("/user/listUnjoinedRules.do");
	var target = $j("#unjoined_rule_list_div");
	loadPage(target,url,data,function(_d){});
}

function goJoinCommissionRuleBack(newRuleRelaId){
		var unjoinRuleTXT=$j("input[name='globalCommRuleTXT']").val();
		var iStart=parseInt($j("input[name='globalRownum']").val());
		iStart++;
		var objTb=document.getElementById("joined_rule_list_tab"); 	 
		var newTR=objTb.insertRow(objTb.rows.length-0);  	
		var cell1=newTR.insertCell(0);
		var cell2=newTR.insertCell(1);
		var cell3=newTR.insertCell(2);
		var cell4=newTR.insertCell(3);
		newTR.id="joined_rowid"+iStart;
		var item=unjoinRuleTXT.split("@#@");
		//if(checkIsExistActivityMobile(item[0])) continue;
		cell1.innerHTML="<input type='hidden' name='joinedRuleList' value='"+item[0]+"'>&nbsp;"+item[0]+"";	 
		cell2.innerHTML="&nbsp;"+item[1]+"";	 
		cell3.innerHTML="&nbsp;"+item[2]+"";
		cell4.innerHTML="<a href='javascript:void(0)' onclick=\"goDeleJoinRule('"+newTR.id+"','"+newRuleRelaId+"')\">Delete</a>";
		cell4.className="L";
		cell4.style.cssText="text-align:center";
		$j("input[name='globalRownum']").val(iStart);
}


function goDeleJoinRuleBack(){
	var sRowid=$j("input[name='globalDeleRowid']").val();
	var objTb=document.getElementById("joined_rule_list_tab");
	var pos=-1;
	for(var i=0;i<objTb.rows.length;i++){
		if(objTb.rows[i].id==sRowid){		
			pos=i;
			break;
		}
	}
	if(pos>-1){
		objTb.deleteRow(pos);
	}
}


function goDeleJoinRule(sRowid,ruleRelaId){
	$j("input[name='globalDeleRowid']").val(sRowid);
	$j.messager.confirm(text_user_delConfirm,text_user_areYouSure + '?', function(r){
		if(r){
			var url = getURL("/user/deleJoinedRule.do");
			var data = {
					'hwAgent2CommissionRule.ruleRelaId':ruleRelaId
			}
			$j.ajax({ 
				url: url,
				data:data,
				beforeSend:function(xhr){
					$j.messager.progress({
						title:text_waitting,
						msg:text_user_Operating + ' ...'
					});
				},
				success: function(dataObj){
					$j.messager.progress('close');
					eval("var json = " + dataObj);
					if(json.flag){
						goDeleJoinRuleBack();
					}else{
						$j.messager.alert('',json.msg);
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


function goJoinCommissionRule(unJoinRuleId){
	var unjoinRuleTXT=$j("input[name='un_ruleid_"+unJoinRuleId+"']").val();
	var agentId=$j("input[name='globalAgentId']").val();
	$j("input[name='globalCommRuleTXT']").val(unjoinRuleTXT);
	var item=unjoinRuleTXT.split("@#@");
	
	if(checkIsExistJoinedRule(item[0])){
		return;
	}
	
	$j.messager.confirm(text_user_joinConfirm,text_user_areYouSure + '?', function(r){
		if(r){
			var url = getURL("/user/saveJoinRule.do");
			var data = {
					'hwAgent2CommissionRule.agentId':agentId,
					'hwAgent2CommissionRule.commissionRule.ruleId':item[0]
			}
			$j.ajax({ 
				url: url,
				data:data,
				beforeSend:function(xhr){
					$j.messager.progress({
						title:text_waitting,
						msg:text_user_Operating + ' ...'
					});
				},
				success: function(dataObj){
					$j.messager.progress('close');
					eval("var json = " + dataObj);
					if(json.flag){
						goJoinCommissionRuleBack(json.ruleRelaId);
					}else{
						$j.messager.alert('',json.msg);
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

function checkIsExistJoinedRule(ruleId){
	var joinRuleList=$j("input[name='joinedRuleList']");
	var L=joinRuleList.length;
	for(var i=0;i<L;i++){
		if(joinRuleList[i].value==ruleId) return true;
	}
	return false;
}
