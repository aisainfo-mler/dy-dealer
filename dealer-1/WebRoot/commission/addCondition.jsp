<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:set name="commission_addCondition_liflag" value='0'></s:set>
<br/>
<table class="detailTableBorder" border="0" cellpadding="0" cellspacing="0">
	<s:iterator  var="variant" value="%{@com.ai.mapp.sys.util.SYSConstant@variantMapL}" status="rt">
		<s:if test="%{#variant == @com.ai.mapp.sys.util.SYSConstant@VARIANT_COMMISSION_RULE_REBATE}">
		</s:if>
<%--		<s:elseif test="%{tmpOrginalExpress.indexOf( #variant.key ) != -1 && ruleCondition.condition.indexOf( #variant.key ) == -1}">--%>
		<s:elseif test="%{ruleCondition.condition.indexOf( #variant ) == -1}">
			<s:set name="commission_addCondition_liflag" value='1'></s:set>
			  <tr>
			    <td class="R"><input type="radio" name="commissionRule_addCondtionR" value="<s:property value='#variant'/>" data-name="<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('variantMap',#variant + session.WW_TRANS_I18N_LOCALE)}"/>"/>&nbsp;</td>
			    <td><s:property value='#variant'/> :<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('variantMap',#variant + session.WW_TRANS_I18N_LOCALE)}"/></td>
			  </tr>
		</s:elseif>
	</s:iterator>
	<s:if test="%{#commission_addCondition_liflag == 0 || #commission_addCondition_liflag == '0'}">
		<tr>
			<td>无可添加的条件</td>
		</tr>
	</s:if>
</table>
<br/>
<s:if test="%{#commission_addCondition_liflag == '1' || #commission_addCondition_liflag == 1}">
	<div class="C">
		<input type="button" class="d_button4" value="<s:text name="form.button.confirm" />" onclick="commissionRule_addCondition_confirm()"/>
	</div>
</s:if>
