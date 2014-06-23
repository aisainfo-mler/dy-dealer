<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<script type="text/javascript">

	$j(function(){
		goQueryUnJoinCommissionRule();				
	});

</script>

<%@ taglib prefix="s" uri="/struts-tags"%>
	<div class="uitopb uitopb-border">
		<ul class="tabbar">
			<li class="tabCurrent">
				<a href="javascript:void(0)"><s:text name="user.joinedRule" />
				<input type="hidden" name="globalAgentId" value="${hwAgent2CommissionRule.agentId}" /> 
				<input type="hidden" name="globalRownum" value="${globalRowNum}" /> 
				<input type="hidden" name="globalDeleRowid" value="" />
				<input type="hidden" name="globalCommRuleTXT" value="" /></a>
			</li>
		</ul>
	</div>
	
	<div>
		<div id="joined_rule_list_div">
		<table cellspacing="0" cellpadding="0" class="listTable" id="joined_rule_list_tab">
 		<thead>
			<tr>
				<th><s:text name="commission.rule.id" /></th>
				<th><s:text name="commission.rule.name" /></th>
				<th><s:text name="commission.rule.express" /></th>
				<th><s:text name="common.operate" /></th>
			</tr>
 		</thead>
 		<tbody>
 		
 		<s:if test="%{joinedRuleList==null || joinedRuleList.size==0}">
		</s:if>
		<s:else>
			<s:iterator  var="joinedRule" value="joinedRuleList" status="rowstatus">
				<tr id="joined_rowid${rowstatus.count + offset}">
					<td style="text-align:center"><input type="hidden" name="joinedRuleList" value="${joinedRule.commissionRule.ruleId}">&nbsp;${joinedRule.commissionRule.ruleId}</td>
					<td>${joinedRule.commissionRule.ruleName}</td>
					<td style="text-align:center"><s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('commissionBackType',#joinedRule.commissionRule.backType + session.WW_TRANS_I18N_LOCALE)}" /></td>
					<td class="L" style="text-align:center"> 
						<a href="javascript:void(0);" onclick="goDeleJoinRule('joined_rowid${rowstatus.count + offset}','${joinedRule.ruleRelaId}')" ><s:text name="form.a.delete" /></a>
					</td>
				</tr>
			</s:iterator>	
		</s:else>
	
 		</tbody>
		</table>
		</div>
	</div>
	
	<br/>
	
	<div class="uitopb uitopb-border">
		<ul class="tabbar">
			<li class="tabCurrent">
				<a href="javascript:void(0)"><s:text name="user.unjoinedRule" /></a>
			</li>
		</ul>
	</div>
	
	<div>
		<form id="unjoin_rule_search_form">
			<input type="hidden" name="commissionRule.valid" value="1" />
			<input type="hidden" name="commissionRule.queryType" value="101" />
			<input type="hidden" name="commissionRule.agentId" value="${hwAgent2CommissionRule.agentId}" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="query" style="border-bottom:1px solid #EBEBEB">
				<tbody>
					<tr>
						<td class="Hint" width="300">
							<s:text name="commission.rule.id" />:
						</td><td>
							<input type="text" class="d_input" name="commissionRule.ruleId"/>
						</td><td class="Hint">
							<s:text name="commission.rule.name" />:
						</td><td>
							<input type="text" class="d_input" name="commissionRule.ruleName"/>
						</td><td>
							<button type="button" class="searchBtn" onclick="goQueryUnJoinCommissionRule()">
								<s:text name="common.search" />
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="unjoined_rule_list_div"></div>
		
	</div>
	
	<div style="text-align:center">
		<button type="button" class="d_button" onclick="closeEasyUIWindow();"><s:text name="common.close" /></button>
	</div>
									
	<script type="text/javascript" src="<s:url value='/user/user.js'/>"></script>
