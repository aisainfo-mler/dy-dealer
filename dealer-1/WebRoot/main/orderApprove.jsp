<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!-- Order ID	Dealer Name	Status	Charges	Operate -->
<table cellspacing="0" cellpadding="0" class="listTable">
 <tbody>
 	<tr>
 		<th>Order ID</th>
		<th>Dealer Name</th>
		<th>Status</th>
		<th>Payable</th>
<%--		<th>Operate</th>--%>
 	</tr>
	<s:if test="%{orderMains==null || orderMains.size==0}">
		<tr>
			<td  colspan="5" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>
		</tr>
	</s:if>
	<s:else>
		<s:iterator  var="orderMain" value="orderMains" status="rowstatus">
			<tr>
				<td class="B L"> <a href="javascript:void(0)" onclick="main_orderApprove_detail(<s:property value="#orderMain.id"/>)"><s:property value="#orderMain.serialNumber"/>&nbsp;</a> </td>
				<td> <s:property value="#orderMain.creator.lastName"/> &nbsp; <s:property value="#orderMain.creator.firstName"/></td>
				<td class=" L"> <s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('orderTypes',#orderMain.status + session.WW_TRANS_I18N_LOCALE)}"/>&nbsp;</td>
				<td class=" L">
					<s:set name="orderMainFee" value="0"></s:set>
					<s:if test="%{#orderMain.realFee != null}" >
	         			<s:set name="orderMainFee" value="%{#orderMain.realFee}"></s:set>
					</s:if>
					<s:property value="#attr.wholeMoneySymbol"/><s:property value="%{@com.ai.mapp.base.MoneyUtils@formatToMoney( #orderMainFee )}" />
				</td>
<%--				<td>--%>
<%--					<a href="javascript:void(0)" onclick="main_orderApprove_detail(<s:property value="#orderMain.id"/>)"><s:text name="order.detail.info" /></a>--%>
<%--				</td>--%>
			</tr>
		</s:iterator>	
	</s:else>
 </tbody>
</table>

<%--<table class="listTable" cellpadding="0" cellspacing="0">--%>
<%--	<tbody>--%>
<%--		<tr>--%>
<%--			<th>Order ID</th>--%>
<%--			<th>Dealer Name</th>--%>
<%--			<th>Staffid</th>--%>
<%--			<th>Price</th>--%>
<%--			<th>Operate</th>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">--%>
<%--				2012092911174754--%>
<%--			</td>--%>
<%--			<td>Howard Haines</td>--%>
<%--			<td>32343</td>--%>
<%--			<td class="numeral">17,996.00</td>--%>
<%--			<td>--%>
<%--				<a href="javascript:void(0)">Detail</a>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">--%>
<%--				2012092911174754--%>
<%--			</td>--%>
<%--			<td>Jenny Glover</td>--%>
<%--			<td>32341</td>--%>
<%--			<td class="numeral">593.00</td>--%>
<%--			<td>--%>
<%--				<a href="javascript:void(0)">Detail</a>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">--%>
<%--				2012092911192354--%>
<%--			</td>--%>
<%--			<td>Eugene Rippy</td>--%>
<%--			<td>32345</td>--%>
<%--			<td class="numeral">886.00</td>--%>
<%--			<td>--%>
<%--				<a href="javascript:void(0)">Detail</a>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">--%>
<%--				2012100112460754--%>
<%--			</td>--%>
<%--			<td>Burt Thomas</td>--%>
<%--			<td>32346</td>--%>
<%--			<td class="numeral">6,998.00</td>--%>
<%--			<td>--%>
<%--				<a href="javascript:void(0)">Detail</a>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">--%>
<%--				2012100112697011--%>
<%--			</td>--%>
<%--			<td>Karen Baldwin</td>--%>
<%--			<td>32348</td>--%>
<%--			<td class="numeral">12,999.00</td>--%>
<%--			<td>--%>
<%--				<a href="javascript:void(0)">Detail</a>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--	</tbody>--%>
<%--</table>--%>
