<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" class="listTable">
	<thead>
		<tr>
			<th><s:text name="aiBusi.busiCode" />
			</th>
			<th><s:text name="aiBusi.remarks" />
			</th>
			<th><s:text name="aiBusi.busitype" />
			</th>
			<th><s:text name="aiBusi.state" />
			</th>
		</tr>
	</thead>
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
					<td class="B L"><a href="javascript:void(0)"
						onclick="busicfg_detail(<s:property value="#busiLists.subSystemId"/>,this)"><s:property
								value="#busiLists.busiCode" /> </a>&nbsp; <input 
						value="<s:property value="#busiLists.busiCode"/>" type="hidden" />
					</td>
					<td class="L" nowrap="nowrap"><s:property
							value="#busiLists.remark" /> &nbsp;</td>
					<td class="L" nowrap="nowrap"><s:property
							value="@com.ai.mapp.sys.util.SYSConstant@getDictName('dataTypes',#busiLists.dataType + session.WW_TRANS_I18N_LOCALE)" />
						&nbsp;</td>
					<td class="L" nowrap="nowrap"><s:property
							value="@com.ai.mapp.sys.util.SYSConstant@getDictName('stateTypes',#busiLists.state + session.WW_TRANS_I18N_LOCALE)" />
						&nbsp;</td>
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
