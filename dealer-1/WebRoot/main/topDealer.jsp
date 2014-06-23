<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%--<table class="listTable" cellpadding="0" cellspacing="0">--%>
<%--	<tbody>--%>
<%--		<tr>--%>
<%--			<th>Name</th>--%>
<%--			<th>Num of New Customers (Last Month)</th>--%>
<%--			<th>Sales Revenue(Last Month)</th>--%>
<%--			<th>Num of New Customers (YTD)</th>--%>
<%--			<th>Sales Revenue (YTD)</th>--%>
<%--		</tr>--%>
<%--		<s:if test="%{agentTopList==null || agentTopList.size==0}">--%>
<%--			<tr>--%>
<%--				<td  colspan="5" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>--%>
<%--			</tr>--%>
<%--		</s:if>--%>
<%--		<s:else>--%>
<%--			<s:iterator  var="agentTop" value="agentTopList" status="rowstatus">--%>
<%--				<tr>--%>
<%--					<td class="L B"><s:property value="#agentTop.str3"/> &nbsp; <s:property value="#agentTop.str2"/> &nbsp;</td>--%>
<%--					<td class="numeral"><s:property value="#agentTop.str6" escape="false"/></td>--%>
<%--					<td class="numeral"><s:property value="#attr.wholeMoneySymbol" /><s:property value="#agentTop.str7" escape="false"/></td>--%>
<%--					<td class="numeral"><s:property value="#agentTop.str5" escape="false"/></td>--%>
<%--					<td class="numeral"><s:property value="#attr.wholeMoneySymbol"/><s:property value="#agentTop.str8" escape="false"/></td>--%>
<%--				</tr>--%>
<%--			</s:iterator>	--%>
<%--		</s:else>--%>
<%--	</tbody>--%>
<%--</table>--%>

<table class="listTable" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th>Name</th>
			<th>Num of New Customers (Last Month)</th>
			<th>Sales Revenue(Last Month)</th>
			<th>Num of New Customers (YTD)</th>
			<th>Sales Revenue (YTD)</th>
		</tr>
		<tr>
			<td class="L B">Charles</td>
			<td class="numeral">266<span class="rise"></span></td>
			<td class="numeral"><s:property value="#attr.wholeMoneySymbol" />8,000.00<span class="rise"></span></td>
			<td class="numeral">3960</td>
			<td class="numeral"><s:property value="#attr.wholeMoneySymbol" />86,000.00</td>
		</tr>
		<tr>
			<td class="L B">Barton</td>
			<td class="numeral">203<span class="descend"></span></td>
			<td class="numeral"><s:property value="#attr.wholeMoneySymbol" />5,000.00<span class="descend"></span></td>
			<td class="numeral">2960</td>
			<td class="numeral"><s:property value="#attr.wholeMoneySymbol" />66,000.00</td>
		</tr>
		<tr>
			<td class="L B">Alerander</td>
			<td class="numeral">186<span class="flat"></span></td>
			<td class="numeral"><s:property value="#attr.wholeMoneySymbol" />48,000.00<span class="flat"></span></td>
			<td class="numeral">2560</td>
			<td class="numeral"><s:property value="#attr.wholeMoneySymbol" />36,000.00</td>
		</tr>
		<tr>
			<td class="L B">Anthony</td>
			<td class="numeral">136<span class="rise"></span></td>
			<td class="numeral"><s:property value="#attr.wholeMoneySymbol" />6,000.00<span class="rise"></span></td>
			<td class="numeral">6960</td>
			<td class="numeral"><s:property value="#attr.wholeMoneySymbol" />86,000.00</td>
		</tr>
		<tr>
			<td class="L B">Simpson</td>
			<td class="numeral">58<span class="rise"></span></td>
			<td class="numeral"><s:property value="#attr.wholeMoneySymbol" />1,000.00<span class="rise"></span></td>
			<td class="numeral">360</td>
			<td class="numeral"><s:property value="#attr.wholeMoneySymbol" />800.00</td>
		</tr>
	</tbody>
</table>