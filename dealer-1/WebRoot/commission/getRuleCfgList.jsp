<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" class="listTable">

	<tbody>
		<s:if test="%{ruleList==null || ruleList.size==0}">
			<tr>
				<td colspan="4" style="color: red; text-align: center;"><s:text
						name="table.body.noData" />!</td>
			</tr>
		</s:if>
		<s:else>
			<s:iterator var="ruleLists" value="ruleList" status="rowstatus">
				<tr>
					<td class="L" nowrap="nowrap" onclick="showSelect(this);"
						ondblclick="addReceiver(this);"><input type="hidden"
						name="ruleName" id="ruleName"
						value="<s:property value="#ruleLists.ruleName" />" /> <input
						type="hidden" name="ruleId" id="ruleId"
						value="<s:property value="#ruleLists.ruleId" />" /> <s:property
							value="#ruleLists.ruleName" /></td>
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
