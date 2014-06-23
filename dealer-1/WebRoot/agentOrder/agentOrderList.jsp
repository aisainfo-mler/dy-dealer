<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
			

<table cellspacing="0" cellpadding="0" class="listTable">
 <thead>
	<tr>
		<th rowspan="2"><s:text name="order.orderCode" /></th>
		<th rowspan="2"><s:text name="user.svn" /></th>
		<th rowspan="2"><s:text name="common.type" /></th>
		<th rowspan="2"><s:text name="order.status" /></th>
<%--		<th rowspan="2"><s:text name="order.agent" /></th>--%>
<%--		<th rowspan="2"><s:text name="common.money" /></th>--%>
		<th rowspan="2"><s:text name="order.realPay" /></th>
		<th rowspan="2"><s:text name="order.payTime" /></th>
<%--		<th rowspan="2"><s:text name="common.user" /><br><s:text name="user.detail" /></th>--%>
	</tr>
 </thead>
 <tbody>
	<s:if test="%{agentOrderList==null || agentOrderList.size==0}">
		<tr>
			<td  colspan="9" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>
		</tr>
	</s:if>
	<s:else>
		<s:iterator  var="agentOrderUnit" value="agentOrderList" status="rowstatus">
			<tr>
				<td class="B L">
					<s:if test="%{#agentOrderUnit.svn==null || #agentOrderUnit.svn.length()==0}">
						<s:property value="%{#agentOrderUnit.orderCode}"/>&nbsp;
					</s:if>
					<s:else>
						<a href="javascript:void(0)" style="text-decoration:none;" onclick="agentOrder_detail(<s:property value="%{#agentOrderUnit.orderId}"/>,'<s:property value="%{#agentOrderUnit.orderCode}"/>')" > 
							<s:property value="%{#agentOrderUnit.orderCode}"/>&nbsp;
						</a>
					</s:else>
					
			    </td>
				<td width="100">
				<s:if test='%{#agentOrderUnit.svn==null || #agentOrderUnit.svn==""}'>
					------
				</s:if>
				<s:else>
						<s:property value="%{#agentOrderUnit.svn}"/> &nbsp; 
				</s:else>
				</td>
				<td nowrap="nowrap"><s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('agentOrderTypes',#agentOrderUnit.orderType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp; </td>
				<td nowrap="nowrap">
					<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('agentOrderStatus',#agentOrderUnit.status + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
				</td>
<%--				<td> <s:property value="%{#agentOrderUnit.creator.lastName}"/>&nbsp; <s:property value="%{#agentOrderUnit.creator.firstName}"/></td>--%>
<%--				<td>--%>
<%--					<s:set name="agentOrderSaleFee" value="0"></s:set>--%>
<%--					<s:if test="%{#agentOrderUnit.saleFee != null}" >--%>
<%--	         			<s:set name="agentOrderSaleFee" value="%{#agentOrderUnit.saleFee}"></s:set>--%>
<%--					</s:if>--%>
<%--						<s:property value="#attr.wholeMoneySymbol"/><s:property value="%{@com.ai.mapp.base.MoneyUtils@formatToMoney( #agentOrderSaleFee )}" />--%>
<%--				</td class="L">--%>
				<td>
					<s:set name="agentOrderRealFee" value="0"></s:set>
					<s:if test="%{#agentOrderUnit.realFee != null}" >
	         			<s:set name="agentOrderRealFee" value="%{#agentOrderUnit.realFee}"></s:set>
					</s:if>
						<s:property value="#attr.wholeMoneySymbol"/><s:property value="%{@com.ai.mapp.base.MoneyUtils@formatToMoney( #agentOrderRealFee )}" />
				</td>
				<td  nowrap="nowrap"> <s:date name="payTime" format="MM/dd/yyyy hh:mm a"/> &nbsp;</td>
<%--				<td>--%>
<%--//					<s:if test="%{#agentOrderUnit.status == 0}" >--%>
<%--//	         			<input type="button" value="审核通过" onclick="agentOrder_audit(<s:property value="%{#agentOrderUnit.orderId}"/>)" />--%>
<%--//					</s:if>--%>
<%--					<a href="javascript:void(0)" style="text-decoration:none;" onclick="agentOrder_lookUserInfo('<s:property value="%{#agentOrderUnit.svn}"/>')" ><s:text name="common.detail" /></a>--%>
<%--//					<a href="javascript:void(0)" style="text-decoration:none;" onclick="$j(this).parent().parent().next('tr').toggle();" >查看产品</a>--%>
<%--				</td>--%>
			</tr>
<%--			<tr style="display:none">--%>
<%--				<td class="Hint">产品信息</td>--%>
<%--				<td colspan="7">--%>
<%--					<s:property value="%{#agentOrderUnit.product.actTypeDes}"/>&nbsp;--%>
<%--				</td>--%>
<%--			</tr>--%>
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
         