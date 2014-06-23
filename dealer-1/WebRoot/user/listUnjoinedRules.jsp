<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
			
<table cellspacing="0" cellpadding="0" class="listTable">
 <thead>
	<tr>
		<th><s:text name="commission.rule.id" /></th>
		<th><s:text name="commission.rule.name" /></th>
		<th><s:text name="commission.rule.express" /></th>
		<th><s:text name="common.operate" /></th>
	</tr>
 </thead>
 <tbody>
	<s:if test="%{unjoinedRuleList==null || unjoinedRuleList.size==0}">
		<tr>
			<td  colspan="4" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>
		</tr>
	</s:if>
	<s:else>
		<s:iterator  var="unjoinedRule" value="unjoinedRuleList" status="rowstatus">
			<tr>
				<td align="center">
				<s:set name="ruleBackType" value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('commissionBackType',#unjoinedRule.backType + session.WW_TRANS_I18N_LOCALE)}"></s:set>
				<input type="hidden" name="un_ruleid_${unjoinedRule.ruleId}" value="${unjoinedRule.ruleId}@#@${unjoinedRule.ruleName}@#@${ruleBackType}" />
				${unjoinedRule.ruleId}</td>
				<td align="center">${unjoinedRule.ruleName}</td>
				<td align="center"><s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('commissionBackType',#unjoinedRule.backType + session.WW_TRANS_I18N_LOCALE)}" /></td>
				<td class="L" style="text-align:center"> 
					<a href="javascript:void(0);" onclick="goJoinCommissionRule(${unjoinedRule.ruleId})" ><s:text name="user.joinRule" /></a>
				</td>
			</tr>
		</s:iterator>	
	</s:else>
 </tbody>
</table>
<s:if test='%{ pagerHeader == null || pagerHeader == "" }'>
	<br/>
</s:if>
<s:else>
	<s:property value="pagerHeader" escape="false" />
</s:else>

         