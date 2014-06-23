<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
			
<%--<s:if test="%{orderMains==null || orderMains.size==0}">--%>
<%--	<table>--%>
<%--		<tr>--%>
<%--			<td  colspan="5" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>--%>
<%--		</tr>--%>
<%--	</table>--%>
<%--</s:if>--%>
<%--<s:else>--%>
<%--	--%>
<%--	<s:iterator  var="orderMain" value="orderMains" status="rowstatus">--%>
<%--		<div <s:if test="%{#orderMain.status == 4}" >--%>
<%--		         			class="orderItem_suc"--%>
<%--						</s:if>--%>
<%--						<s:else>--%>
<%--							class="orderItem" --%>
<%--						</s:else>--%>
<%--			>--%>
<%--			<table cellspacing="0" cellpadding="0" class="listTable">--%>
<%--				<tbody>--%>
<%--					<tr class="tr_focus">--%>
<%--						<td class="L" colspan="8">--%>
<%--							<span class="orderNo">订单编号: ${orderMain.serialNumber}</span>--%>
<%--							<span class="orderTime">下单时间: <s:date name="createTime" format="yyyy-MM-dd hh:mm"/></span>--%>
<%--							<span class="orderAgent">代理商：${orderMain.operator.lastName}${orderMain.operator.firstName}</span>--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--					<s:iterator  var="orderDetail" value="#orderMain.details" status="rr">--%>
<%--						<tr>--%>
<%--							<td class="L">--%>
<%--								<s:if test="{#orderDetail.good.listpic == null}" >--%>
<%--				         			<img src='<s:url value="/images/pic_def_32.png" />'/>--%>
<%--								</s:if>--%>
<%--								<s:else>--%>
<%--									<img src="<s:url value='#orderDetail.good.listpic.fileUpload.filePath'/>"/>--%>
<%--								</s:else>--%>
<%--								<s:property value="#orderDetail.good.name"/>--%>
<%--							</td>--%>
<%--							<td width="100">￥<s:property value="@com.ai.mapp.base.MoneyUtils@formatToMoney(#orderDetail.good.price)" /></td>--%>
<%--							<td width="100"><s:property value="#orderDetail.counts"/></td>--%>
<%--							<s:if test="#rr.index==0">--%>
<%--								<td width="100" rowspan="<s:property value="#orderMain.details.size"/>">--%>
<%--								 <s:property value="@com.ai.mapp.sys.util.SYSConstant@getDictName('orderTypes',#orderMain.status)" />--%>
<%--								</td>--%>
<%--								<td width="120" class="B" rowspan="<s:property value="#orderMain.details.size"/>">--%>
<%--								<s:set name="orderMainFee" value="0"></s:set>--%>
<%--								<s:if test="%{#orderMain.fee != null}" >--%>
<%--				         			<s:set name="orderMainFee" value="%{#orderMain.fee}"></s:set>--%>
<%--								</s:if>--%>
<%--									￥<s:property value="%{@com.ai.mapp.base.MoneyUtils@formatToMoney( #orderMainFee )}" />--%>
<%--								</td> --%>
<%--								<td width="100" rowspan="<s:property value="#orderMain.details.size"/>"><a href="javascript:void(0)" onclick="order_detail(<s:property value="#orderMain.id"/>)">订单详情</a></td>--%>
<%--							</s:if>--%>
<%--						</tr>--%>
<%--					</s:iterator>--%>
<%--				</tbody>--%>
<%--			</table>--%>
<%--		</div>--%>
<%--	</s:iterator>--%>
<%--</s:else>--%>

<table cellspacing="0" cellpadding="0" class="listTable">
 <thead>
	<tr>
		<th><s:text name="order.orderCode" /></th>
		<th><s:text name="order.status" /></th>
		<th><s:text name="order.totalPrice" /></th>
		<th><s:text name="order.agent" /></th>
		<th><s:text name="order.createTime" /></th>
<%--		<th><s:text name="order.detail" /></th>--%>
	</tr>
 </thead>
 <tbody>
	<s:if test="%{orderMains==null || orderMains.size==0}">
		<tr>
			<td  colspan="6" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>
		</tr>
	</s:if>
	<s:else>
		<s:iterator  var="orderMain" value="orderMains" status="rowstatus">
			<tr>
				<td class="B L"> <a href="javascript:void(0)" onclick="order_detail(<s:property value="#orderMain.id"/>)"><s:property value="#orderMain.serialNumber"/></a>&nbsp; </td>
				<td class="L" nowrap="nowrap"> <s:property value="@com.ai.mapp.sys.util.SYSConstant@getDictName('orderTypes',#orderMain.status + session.WW_TRANS_I18N_LOCALE)" /> &nbsp;</td>
				<td class="L">
					<s:set name="orderMainFee" value="0"></s:set>
					<s:if test="%{#orderMain.realFee != null}" >
	         			<s:set name="orderMainFee" value="%{#orderMain.realFee}"></s:set>
					</s:if>
						<s:property value="#attr.wholeMoneySymbol"/><s:property value="%{@com.ai.mapp.base.MoneyUtils@formatToMoney( #orderMainFee )}" />
				</td>
				<td><s:property value="#orderMain.creator.lastName"/> &nbsp; <s:property value="#orderMain.creator.firstName"/> &nbsp;</td>
				<td nowrap="nowrap"> <s:date name="createTime" format="MM/dd/yyyy hh:mm a"/> &nbsp;</td>
<%--				<td>--%>
<%--					<a href="javascript:void(0)" onclick="order_detail(<s:property value="#orderMain.id"/>)"><s:text name="order.detail.info" /></a>--%>
<%--				</td>--%>
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
         