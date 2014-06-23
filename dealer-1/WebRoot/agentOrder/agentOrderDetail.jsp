<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent">
			<a href="javascript:void(0)">
				<s:text name="order.information" />  
			</a>
		</li>
	</ul>
</div>
<table cellspacing="0" cellpadding="0" class="detailTableBorder">
 <tbody>
	<tr>
		<td class="Hint" style="width:130px"><s:text name="order.orderCode" /></td>
		<td ><s:property value="%{agentOrderCondition.orderCode}"/>&nbsp;</td>
		<td class="Hint" style="width:130px"><s:text name="user.svn" /></td>
		<td style="width:130px"><s:property value="%{agentOrderCondition.svn}"/>&nbsp;</td>
	</tr><tr>
		<td class="Hint" style="width:130px"><s:text name="common.type" /></td>
		<td><s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('agentOrderTypes',agentOrderCondition.orderType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;</td>
		<td class="Hint" style="width:130px"><s:text name="order.status" /></td>
		<td style="width:130px"><s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('agentOrderStatus',agentOrderCondition.status + session.WW_TRANS_I18N_LOCALE)}" />&nbsp;</td>
	</tr><tr>
		<td class="Hint" style="width:130px"><s:text name="order.agent" /></td>
		<td><s:property value="%{agentOrderCondition.creator.lastName}"/>&nbsp; <s:property value="%{agentOrderCondition.creator.firstName}"/></td>
		<td class="Hint" style="width:130px"><s:text name="common.money" /></td>
		<td style="width:130px">
			<s:set name="agentOrderSaleFee" value="0"></s:set>
			<s:if test="%{agentOrderCondition.saleFee != null}" >
        			<s:set name="agentOrderSaleFee" value="%{agentOrderCondition.saleFee}"></s:set>
			</s:if>
				<s:property value="#attr.wholeMoneySymbol"/><s:property value="%{@com.ai.mapp.base.MoneyUtils@formatToMoney( #agentOrderSaleFee )}" />
		</td>
	</tr><tr>
		<td class="Hint" style="width:130px"><s:text name="order.realPay" /></td>
		<td>
			<s:set name="agentOrderRealFee" value="0"></s:set>
			<s:if test="%{agentOrderCondition.realFee != null}" >
        			<s:set name="agentOrderRealFee" value="%{agentOrderCondition.realFee}"></s:set>
			</s:if>
				<s:property value="#attr.wholeMoneySymbol"/><s:property value="%{@com.ai.mapp.base.MoneyUtils@formatToMoney( #agentOrderRealFee )}" />
		</td>
		<td class="Hint" style="width:130px"><s:text name="order.payTime" /></td>
		<td style="width:130px"> <s:date name="agentOrderCondition.payTime" format="MM/dd/yyyy hh:mm a"/> &nbsp;</td>
	</tr>
 </tbody>
</table>
<br/>
<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent">
			<a href="javascript:void(0)">
				<s:text name="order.customer.information" />  
			</a>
		</li>
	</ul>
</div>
<%@ include file="/agentOrder/userInfo.jsp"%>