<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">

	$j(function(){
		var ruleBackType = $j("select[name='rule.backType']");
		if(ruleBackType.length==1){
			if(ruleBackType[0].value==3){
				$j("#trCommissionCode").css({display:''});
			}
		}
	});

</script>

<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent">
			<a href="javascript:void(0)">
				<s:text name="commission.rule.detail" />
			</a>
		</li>
	</ul>
</div>
<div class="order_handle">
	<a href="javascript:void(0)" onclick="commissionRule_return()" class="back"><s:text name="order.return" /></a>
	<s:if test="%{rule!=null && rule.ruleId!=null}">
		<a id="commissionRule_editB" href="javascript:void(0)" onclick="commissionRule_edit()" class="modify"><s:text name="form.a.edit" /></a>
		
		<a href="javascript:void(0)" class="delete" onclick="commissionRule_delete(${rule.ruleId})"><s:text name="form.a.delete" /></a>
	</s:if>
</div>
<div id="commissionRule_detail_look" 
	<s:if test="%{rule==null || rule.ruleId==null}">
		style="display:none;"
	</s:if>
><!-- 查看 -->
	<table class="detailTableBorder" border="0" cellspacing="0" cellpadding="5">
	    <tr>
	        <td class="Hint"><s:text name="commission.rule.name" /></td>
	        <td class="B orange" colspan="3">${rule.ruleName}</td>
<%--	       	<td class="Hint"><s:text name="commission.rule.backType" />：</td>--%>
<%--	        <td>--%>
<%--	        	<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('commissionBackType',rule.backType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;--%>
<%--	        </td>--%>
	    </tr>
	    <tr>
<%--	        <td class="Hint" valign="top" width="100"><s:text name="commission.rule.payType" />：</td>--%>
<%--	        <td>--%>
<%--				<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('commissionPayType',rule.payType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;--%>
<%--			</td>--%>
			<td class="Hint"><s:text name="commission.term" /></td>
	        <td>
	        	<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('commissionBackType',rule.backType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
	        </td>
	        <td class="Hint"><s:text name="form.radio.isValid" /></td>
	        <td colspan="1">
	        	<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('isValidMap',rule.valid + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
			</td>
	    </tr>
			
			<s:if test="rule.backType == 3">
			<tr>
		        <td class="Hint" valign="top" width="100"><s:text name="commission.rule.desc" /></td>
	        <td>${rule.description}</td>
			<td class="Hint"><s:text name="Commission.code" /></td>
			<td>&nbsp;${rule.modId}</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
		        <td class="Hint" valign="top" width="100"><s:text name="commission.rule.desc" /></td>
	        <td>${rule.description}</td>
			<td class="Hint"></td>
			<td></td>
			</tr>
		</s:else>
	</table>
	<s:if test="%{rule.conditionMap==null || rule.conditionMap.isEmpty()}">
	</s:if>
	<s:else>
		<s:iterator  var="mapUnit" value="rule.conditionMap" status="rowstatus">
			<br/>
			<table class="listTable" border="0" cellspacing="0" cellpadding="5" >
				<tr>
					<th class="L" style="width:75%"><s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('variantMap',#mapUnit.key + session.WW_TRANS_I18N_LOCALE)}" />
					<s:if test="%{#mapUnit.key == @com.ai.mapp.sys.util.SYSConstant@VARIANT_COMMISSION_RULE_ORDERTYPE}">
			        	(<s:iterator  var="variant" value="%{@com.ai.mapp.sys.util.SYSConstant@agentOrderTypes}" status="rt">
			        		  <span ><b> <s:property value="#variant.key"/></b>：<s:property value="#variant.value"/>;</span> 
			        	</s:iterator>)
					</s:if>
					</th>
					<th><s:text name="commission.rule.condition.rateEx" /></th>
				</tr>
				<s:iterator  var="conditionUnit" value="#mapUnit.value" status="ss">
					<tr>
						<td class="L"><s:text name="commission.rule.condition.from" />：<s:property value="#conditionUnit[0]"/>&nbsp;&nbsp;<s:text name="commission.rule.condition.to" />：<s:property value="#conditionUnit[1]"/></td>
<%--						<td><s:property value="#conditionUnit[2]"/>%</td>--%>
						<td><s:property value="%{@com.ai.mapp.base.EvaluatorUtil@translateExpress(#conditionUnit[2])}" /></td>
					</tr>
				</s:iterator>
			</table>
		</s:iterator>	
	</s:else>
</div>
<div id="commissionRule_detail_edit" 
	<s:if test="%{rule!=null && rule.ruleId!=null}">
		style="display:none;"
	</s:if>
><!-- 修改 -->
  <s:form id="commissionRule_detail_editF" action="/commission/saveCommission.do" method="post">
	<table class="detailTableBorder" border="0" cellspacing="0" cellpadding="5">
	    <tr>
	        <td class="Hint"><s:text name="commission.rule.name" /></td>
	        <td colspan="3"><input type="text" value="${rule.ruleName}" name="rule.ruleName" size="99" class="d_input {required:true,messages:{required:'<s:text name="commission.rule.pleaseName" />'},maxlength:50}" /><span class="required">*</span></td>
<%--	       	<td class="Hint"><s:text name="commission.rule.backType" />：</td>--%>
<%--	        <td>--%>
<%--	        	<s:select class="d_input" name="rule.backType" list="%{@com.ai.mapp.sys.util.SYSConstant@commissionBackType}" listKey="key" listValue="value"  value="%{rule.backType}" ></s:select>--%>
<%--	        	<span class="required">*</span>--%>
<%--	        </td>--%>
	    </tr>
	    <tr>
<%--	        <td class="Hint" valign="top" width="100"><s:text name="commission.rule.payType" />：</td>--%>
<%--	        <td>--%>
<%--				<s:select class="d_input"  name="rule.payType" list="%{@com.ai.mapp.sys.util.SYSConstant@commissionPayType}" listKey="key" listValue="value"  value="%{rule.payType}" ></s:select>--%>
<%--				<span class="required">*</span>--%>
<%--			</td>--%>
			<td class="Hint"><s:text name="commission.term" /></td>
	        <td>
<%--	        	<s:select class="d_input" name="rule.backType" list="%{@com.ai.mapp.sys.util.SYSConstant@commissionBackType}" listKey="key" listValue="value"  value="%{rule.backType}" onchange="changeRuleBackType(this)"></s:select>--%>
	        	<select name="rule.backType" class="d_input" onchange="changeRuleBackType(this)">
					<s:iterator  var="commissionBackTypeUnit" value="%{@com.ai.mapp.sys.util.SYSConstant@commissionBackTypeL}" >
							<option value='${commissionBackTypeUnit}'><s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('commissionBackType',#commissionBackTypeUnit + session.WW_TRANS_I18N_LOCALE)}" /></option>
					</s:iterator>
				</select>
	        	<span class="required">*</span>
	        </td>
	        <td class="Hint"><s:text name="form.radio.isValid" /></td>
	        <td colspan="1">
<%--	        	<s:select class="d_input" name="rule.valid" list="%{@com.ai.mapp.sys.util.SYSConstant@isValidMap}" listKey="key" listValue="value"  value="%{rule.valid}" ></s:select>--%>
	        	<select name="rule.valid" class="d_input" >
					<s:iterator  var="isValidMapLUnit" value="%{@com.ai.mapp.sys.util.SYSConstant@isValidMapL}" >
						<option value='${isValidMapLUnit}'><s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('isValidMap',#isValidMapLUnit + session.WW_TRANS_I18N_LOCALE)}" /></option>
					</s:iterator>
				</select>
	        	<span class="required">*</span>
			</td>
	    </tr>
	    
	    <tr>
	        <td class="Hint" valign="top" width="100"><s:text name="commission.rule.desc" /></td>
	        <td colspan="3"><input type="text" value="${rule.description}" name="rule.description" size="99" class="d_input {maxlength:128}"/></td>
	    </tr>
	    
	    <tr id="trCommissionCode" style="display:none">
	        <td class="Hint" valign="top" width="100"><s:text name="Commission.code" /></td>
	        <td colspan="3"><input type="text" value="${rule.modId}" name="rule.modId" size="23" class="d_input {maxlength:10,digits:true}"/></td>
	    </tr>
	    
	    <tr style="display:none">
	        <td class="Hint" valign="top" width="100"></td>
	        <td></td>
			<td class="Hint" valign="top" width="100"><s:text name="commission.rule.variantDesc" />：</td>
	        <td><ul id="commissionRule_variants_ul">
		        	<s:iterator  var="variant" value="%{@com.ai.mapp.sys.util.SYSConstant@variantMapL}" status="rt">
		        		<s:if test="%{#variant == @com.ai.mapp.sys.util.SYSConstant@VARIANT_COMMISSION_RULE_REBATE}">
						</s:if>
						<s:else>
		        		  <li data-key="<s:property value="#variant"/>"><b><s:property value="#variant"/> </b>：<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('variantMap',#variant + session.WW_TRANS_I18N_LOCALE)}"/></li> 
						</s:else>
		        	</s:iterator>
		        </ul>
	        </td>
	    </tr>
	   
	    
<%--	    <tr>--%>
<%--	    	<td colspan="2"></td>--%>
<%--	        <td class="Hint" valign="top" width="100"><s:text name="commission.rule.variantDesc" />：</td>--%>
<%--	        <td><ul id="commissionRule_variants_ul">--%>
<%--	        	<s:iterator  var="variant" value="%{@com.ai.mapp.sys.util.SYSConstant@variantMap}" status="rt">--%>
<%--	        		<s:if test="%{#variant.key == @com.ai.mapp.sys.util.SYSConstant@VARIANT_COMMISSION_RULE_REBATE}">--%>
<%--					</s:if>--%>
<%--					<s:else>--%>
<%--	        		  <li data-key="<s:property value="#variant.key"/>"><b><s:property value="#variant.key"/> </b>：<s:property value="#variant.value"/></li> --%>
<%--					</s:else>--%>
<%--	        	</s:iterator>--%>
<%--	        </ul>--%>
<%--	        </td>--%>
<%--	    </tr>--%>
	</table>
	
	<div style="padding:10px;">
		<input type="button" value="<s:text name="commission.rule.addCondition" />" onclick="commissionRule_addCondition()" class="d_buttonThin4"/>
		<input type="hidden" name="rule.condition" value="${rule.condition}" />
<%--		<input type="hidden" id="commissionRule_tmpCondition" value="${rule.condition}" />--%>
		<input type="hidden" id="commissionRule_tmpCondition" value="<s:property value="%{@com.ai.mapp.base.EvaluatorUtil@backExpress(rule.condition)}" />" />
		
		<input type="hidden" id="commissionRule_rateVariant" value="<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@VARIANT_COMMISSION_RULE_REBATE}"/>" /><!-- 用于检测rate变量名 -->
		<input type="hidden" id="commissionRule_orderTypeVariant" value="<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@VARIANT_COMMISSION_RULE_ORDERTYPE}"/>" /><!-- 用于检测订单类型变量名 -->
		<input type="hidden" name="rule.ruleId" value="${rule.ruleId}" />
	</div>
	
	<div id="commission_conditionDiv">
		<s:if test="%{rule.conditionMap==null || rule.conditionMap.isEmpty()}">
		</s:if>
		<s:else>
			<s:iterator  var="mapUnit" value="rule.conditionMap" status="rowstatus">
			  <div>
				<div class="R">
			  		<input type="button" value="<s:text name="commission.rule.delCondition" />" onclick="commissionRule_delCondition(this,'<s:property value="#mapUnit.key"/>')" class="d_buttonThin4"/>
			  	</div>
			 	<table class="listTable" border="0" cellspacing="0" cellpadding="5" id="commissionRule_condition_table_<s:property value="#mapUnit.key"/>">
					<tr>
						<th class="L" style="width:75%">
							<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('variantMap',#mapUnit.key + session.WW_TRANS_I18N_LOCALE)}" />
							<s:if test="%{#mapUnit.key == @com.ai.mapp.sys.util.SYSConstant@VARIANT_COMMISSION_RULE_ORDERTYPE}">
					        	(<s:iterator  var="variant" value="%{@com.ai.mapp.sys.util.SYSConstant@agentOrderTypes}" status="rt">
					        		  <span ><b><s:property value="#variant.key"/> </b>：<s:property value="#variant.value"/>;</span> 
					        	</s:iterator>)
							</s:if>
						</th>
						<th>
							<s:text name="commission.rule.condition.rateEx" />
						</th>
<%--						<th>操作</th>--%>
					</tr>
					<s:iterator  var="conditionUnit" value="#mapUnit.value" status="ss">
						<tr>
							<td class="L">
								<s:text name="commission.rule.condition.from" />：<input type="text" name="commissionRule_condition_from_<s:property value="#mapUnit.key"/>_<s:property value="#ss.index"/>" class="d_input {required:true,min:0}" value="<s:property value="#conditionUnit[0]"/>" /><span class="required">*</span>&nbsp;&nbsp;
								<s:text name="commission.rule.condition.to" />：<input type="text" name="commissionRule_condition_to_<s:property value="#mapUnit.key"/>_<s:property value="#ss.index"/>" class="d_input {required:true,min:0}"  value="<s:property value="#conditionUnit[1]"/>"/><span class="required">*</span></td>
							<td><input type="text" class="d_input {required:true,expressValid:[1, 30]}" name="commissionRule_condition_rate_<s:property value="#mapUnit.key"/>_<s:property value="#ss.index"/>" value="<s:property value="#conditionUnit[2]"/>"/><span class="required">*</span></td>
<%--							<td><a href="javascript:void(0)" class="delete">删除</a></td>--%>
						</tr>
					</s:iterator>
				</table>
				<div class="R" style="padding:10px;">
					<a href="javascript:void(0)" onclick="commissionRule_addRection(this)" class="add"><s:text name="commission.rule.addRection" /></a>
					<a href="javascript:void(0)" onclick="commissionRule_delRection(this)" class="delete"><s:text name="commission.rule.delRection" /></a>
				</div>
			  </div>
			</s:iterator>
		</s:else>
		
	</div>
	<div class="C" style="padding:10px;">
		<input type="button" id="commissionRule_cancelB" value="<s:text name="wxtx.news.btnCancel" />" onclick="commissionRule_cancelEdit()" class="d_buttonThin4" style="display:none;"/>
		<input type="button" value="<s:text name="common.save" />" onclick="commissionRule_save()" class="d_button4"/>
	</div>
	</s:form>
	<div id="commissionRule_variant_orderTypeD" style="display:none;">
<%--    	(<s:iterator  var="variant" value="%{@com.ai.mapp.sys.util.SYSConstant@agentOrderTypes}" status="rt">--%>
<%--    		  <span data-key="<s:property value="#variant.key"/>"><b><s:property value="#variant.key"/> </b>：<s:property value="#variant.value"/>;</span> --%>
<%--    	</s:iterator>)--%>
			(<s:iterator  var="variant" value="%{@com.ai.mapp.sys.util.SYSConstant@agentOrderTypesL}" status="rt">
	    		  <span data-key="<s:property value="#variant"/>"><b><s:property value="#variant"/> </b>：<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('agentOrderTypes',#variant + session.WW_TRANS_I18N_LOCALE)}"/>;</span> 
	    	</s:iterator>)
    </div>
</div>