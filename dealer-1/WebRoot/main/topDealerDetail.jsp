<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table cellspacing="0" cellpadding="0" class="listTable">
 <thead>
	<tr>
		<th><s:text name="table.head.serialno" /></th>
		<th>Customer ID</th>
		<th><s:text name="userManager.tel" /></th>
		<th><s:text name="main.customer.createTime" /></th>
	</tr>
 </thead>
 <tbody>
	<s:if test="%{customerList==null || customerList.size==0}">
		<tr>
			<td  colspan="4" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>
		</tr>
	</s:if>
	<s:else>
		<s:iterator  var="unitC" value="customerList" status="rowstatus">
			<tr>
				<td align="center">${rowstatus.count + offset}</td>
				<td class="L B" nowrap="nowrap"> 
					<s:property value="#unitC.userId"/> &nbsp;
				</td>
				<td>
					<s:property value="#unitC.contractPhone"/> &nbsp;
				</td>
				<td>
					<s:date name="createTime" format="MM/dd/yyyy hh:mm a"/> &nbsp;
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