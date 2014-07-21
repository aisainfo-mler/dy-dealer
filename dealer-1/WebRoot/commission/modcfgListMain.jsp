<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" class="listTable">
	<thead>
		<tr>
			<th><s:text name="mod.modName" /></th>
			<th><s:text name="mod.modType" /></th>
			<th><s:text name="operate" /></th>

		</tr>
	</thead>
	<tbody>
		<s:if test="%{modlist==null || modlist.size==0}">
			<tr>
				<td colspan="6" style="color: red; text-align: center;"><s:text
						name="table.body.noData" />!</td>
			</tr>
		</s:if>
		<s:else>
			<s:iterator var="modlists" value="modlist" status="rowstatus">
				<tr>
					<td class="B L"><s:property value="#modlists.modName" />
					</td>
					<td class="L" nowrap="nowrap"><s:property
							value="#modlists.dataType" /> &nbsp;</td>
					<td class="L" nowrap="nowrap"><a href="javascript:void(0)"
						onclick="modcfg_detail(<s:property value="#modlists.modId"/>)"><s:text
								name="edit" />&nbsp; </a>
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
