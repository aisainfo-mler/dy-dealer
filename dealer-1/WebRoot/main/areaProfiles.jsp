<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="listTable" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th>City</th>
			<th>Num of New Customers (Last Month)</th>
			<th>Sales Revenue (Last Month)</th>
			<th>Growth Rate (MoM)</th>
			<th>Growth Rate (YoY)</th>
		</tr>
		<s:if test="%{areaProfileList==null || areaProfileList.size==0}">
			<tr>
				<td  colspan="5" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>
			</tr>
		</s:if>
		<s:else>
			<s:iterator  var="areaProfile" value="areaProfileList" status="rowstatus">
				<tr>
					<td class="L B"><s:property value="#areaProfile.str2"/> &nbsp;</td>
					<td class="numeral"><s:property value="#areaProfile.str6" escape="false"/></td>
					<td class="numeral"><s:property value="#attr.wholeMoneySymbol" /><s:property value="#areaProfile.str3"/></td>
					<td class="numeral"><s:property value="#areaProfile.str4" escape="false"/></td>
					<td class="numeral"><s:property value="#areaProfile.str5" escape="false"/></td>
				</tr>
			</s:iterator>	
		</s:else>
	</tbody>
</table>

<%--<table class="listTable" cellpadding="0" cellspacing="0">--%>
<%--	<tbody>--%>
<%--		<tr>--%>
<%--			<th>City</th>--%>
<%--			<th>Monthly Develop User</th>--%>
<%--			<th>Monthly Amount</th>--%>
<%--			<th>Month-on-Month</th>--%>
<%--			<th>year-on-year</th>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">London</td>--%>
<%--			<td class="numeral">3690</td>--%>
<%--			<td class="numeral">208,000.00</td>--%>
<%--			<td class="numeral"><span class="red">13%</span></td>--%>
<%--			<td class="numeral"><span class="red">11%</span></td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">Birmingham</td>--%>
<%--			<td class="numeral">1690</td>--%>
<%--			<td class="numeral">108,000.00</td>--%>
<%--			<td class="numeral"><span class="red">10%</span></td>--%>
<%--			<td class="numeral"><span class="red">8%</span></td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">Liverpool</td>--%>
<%--			<td class="numeral">1390</td>--%>
<%--			<td class="numeral">110,000.00</td>--%>
<%--			<td class="numeral"><span class="red">5%</span></td>--%>
<%--			<td class="numeral"><span class="red">8%</span></td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">Edinburgh</td>--%>
<%--			<td class="numeral">890</td>--%>
<%--			<td class="numeral">98,000.00</td>--%>
<%--			<td class="numeral"><span class="orange">3%</span></td>--%>
<%--			<td class="numeral"><span class="orange">3%</span></td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">Manchester</td>--%>
<%--			<td class="numeral">590</td>--%>
<%--			<td class="numeral">68,000.00</td>--%>
<%--			<td class="numeral"><span class="green">-5%</span></td>--%>
<%--			<td class="numeral"><span class="green">-3%</span></td>--%>
<%--		</tr>--%>
<%--	</tbody>--%>
<%--</table>--%>