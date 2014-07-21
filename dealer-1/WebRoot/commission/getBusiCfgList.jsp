<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" class="listTable">

	<tbody>
		<s:if test="%{busiList==null || busiList.size==0}">
			<tr>
				<td colspan="6" style="color: red; text-align: center;"><s:text
						name="table.body.noData" />!</td>
			</tr>
		</s:if>
		<s:else>
			<s:iterator var="busiLists" value="busiList" status="rowstatus">
				<tr>
					<td class="L" nowrap="nowrap" onclick="showSelect(this);"
						ondblclick="addReceiver(this);"><input type="hidden"
						name="remark" id="remark"
						value="<s:property value="#busiLists.remark" />" /> <input
						type="hidden" name="dataType" id="dataType"
						value="<s:property value="#busiLists.dataType" />" /> <input
						type="hidden" name="busiCode" id="busiCode"
						value="<s:property value="#busiLists.busiCode" />" /> <s:property
							value="#busiLists.remark" />( <s:property
							value="#busiLists.busiCode" />)</td>
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
