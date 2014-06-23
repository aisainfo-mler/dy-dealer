<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
			
<table cellspacing="0" cellpadding="0" class="listTable">
 <thead>
	<tr>
		<th><s:text name="commission.rule.id" /></th>
		<th><s:text name="commission.rule.name" /></th>
<%--		<th><s:text name="commission.rule.express" /></th>--%>
<%--		<th><s:text name="commission.rule.condition" /></th>--%>
		<th><s:text name="commission.rule.desc" /></th>
		<th nowrap="nowrap"><s:text name="commission.term" /></th>
		<th nowrap="nowrap"><s:text name="Commission.code" /></th>
<%--		<th nowrap="nowrap"><s:text name="commission.rule.payType" /></th>--%>
		<th><s:text name="form.radio.isValid" /></th>
	</tr>
 </thead>
 <tbody>
	<s:if test="%{rules==null || rules.size==0}">
		<tr>
			<td  colspan="8" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>
		</tr>
	</s:if>
	<s:else>
		<s:iterator  var="unitRule" value="rules" status="rowstatus">
			<tr>
				<td>${unitRule.ruleId}</td>
				<td class="L" nowrap="nowrap"> 
					<a href="javascript:void(0);" onclick="commissionRule_detail(${unitRule.ruleId})" > <s:property value="#unitRule.ruleName" /> </a>
					&nbsp;
				</td>
<%--				<td class="L" nowrap="nowrap">--%>
<%--					<s:property value="%{@com.ai.mapp.base.EvaluatorUtil@translateExpress(#unitRule.backExpress)}" /> &nbsp;--%>
<%--				</td>--%>
<%--				<td class="L B" width="200">--%>
<%--					<div title="<s:property value="title"/>" class="ellipsis" style="float:left;width:140px;padding-left:10px;"><s:property value="#unitRule.condition"/></div>--%>
<%--				</td>--%>
				<td class="L" width="200">
					<div title="<s:property value="title"/>" class="ellipsis" style="float:left;width:140px;padding-left:10px;"><s:property value="#unitRule.description"/></div>
				</td>
				<td> <s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('commissionBackType',#unitRule.backType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;</td>
				<td>&nbsp;
						<s:if test="backType==3">
							${unitRule.modId}
						</s:if>
				</td>
<%--				<td nowrap="nowrap">--%>
<%--					<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('commissionPayType',#unitRule.payType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;--%>
<%--				</td>--%>
				<td nowrap="nowrap">
					<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('isValidMap',#unitRule.valid + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
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

         