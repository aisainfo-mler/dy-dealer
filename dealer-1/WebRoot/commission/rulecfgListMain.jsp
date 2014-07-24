<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" class="listTable">
	<thead>
		<tr>
			<th><s:text name="rule.ruleName" /></th>
			<th><s:text name="rule.rule" /></th>
			<th><s:text name="rule.modType" /></th>
			<th><s:text name="operate" /></th>
		</tr>
	</thead>
	<tbody>
		<s:if test="%{ruleList==null || ruleList.size==0}">
			<tr>
				<td colspan="6" style="color: red; text-align: center;"><s:text
						name="table.body.noData" />!</td>
			</tr>
		</s:if>
		<s:else>
			<s:iterator var="ruleLists" value="ruleList" status="rowstatus">
				<tr>
					<td class="B L"><s:property value="#ruleLists.ruleName" /></td>
					<td class="L" nowrap="nowrap"><s:property
							value="#ruleLists.rule" /> &nbsp;</td>
					<td class="L" nowrap="nowrap"><s:property
							value="@com.ai.mapp.sys.util.SYSConstant@getDictName('dataTypes',#ruleLists.modType + session.WW_TRANS_I18N_LOCALE)" />
						&nbsp;<s:property value="#ruleLists.modType"/></td>
					<td class="L" nowrap="nowrap"><a href="javascript:void(0)"
						onclick="rulecfg_detail(<s:property value="#ruleLists.ruleId"/>)"><s:text
								name="edit" />&nbsp;</a>
					</td>
				</tr>
			</s:iterator>
		</s:else>
	</tbody>
</table>

<s:if test='%{ pagerHeader == null || pagerHeader == "" }'>
	<br />
</s:if>
<s:else>
	<s:property value="pagerHeader" escape="false" />
</s:else>
