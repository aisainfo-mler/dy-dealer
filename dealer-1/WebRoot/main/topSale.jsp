<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<table class="listTable" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th>Product</th>
			<th>Monthly Sale Count</th>
			<th>Monthly Sale Amount</th>
		</tr>
		<s:if test="%{topSaleMobileList==null || topSaleMobileList.size==0}">
			<tr>
				<td  colspan="5" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>
			</tr>
		</s:if>
		<s:else>
			<s:iterator  var="topSaleMobile" value="topSaleMobileList" status="rowstatus">
				<tr>
					<td class="L B"><s:property value="#topSaleMobile.str2"/> &nbsp;</td>
					<td class="numeral"><s:property value="#topSaleMobile.str3" /></td>
					<td class="numeral"><s:property value="#attr.wholeMoneySymbol" /><s:property value="%{@com.ai.mapp.base.MoneyUtils@formatToMoney( #topSaleMobile.str4 )}" /></td>
				</tr>
			</s:iterator>	
		</s:else>
	</tbody>
</table>


<%--<table class="listTable" cellpadding="0" cellspacing="0">--%>
<%--	<tbody>--%>
<%--		<tr>--%>
<%--			<th>Product</th>--%>
<%--			<th>Monthly Sale Count</th>--%>
<%--			<th>Monthly Sale Amount</th>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B" nowrap="nowrap">--%>
<%--				iPhone 4S 286 white--%>
<%--			</td>--%>
<%--			<td class="numeral">330</td>--%>
<%--			<td class="numeral">813,800.00</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B" nowrap="nowrap">--%>
<%--				Galaxy S III 286--%>
<%--			</td>--%>
<%--			<td class="numeral">260</td>--%>
<%--			<td class="numeral">659,200.00</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B" nowrap="nowrap">--%>
<%--				Ultra Internet 46--%>
<%--			</td>--%>
<%--			<td class="numeral">150</td>--%>
<%--			<td class="numeral">9,030.00</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B" nowrap="nowrap">--%>
<%--				Ultra Internet 96--%>
<%--			</td>--%>
<%--			<td class="numeral">37</td>--%>
<%--			<td class="numeral">3,200.00</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B" nowrap="nowrap">--%>
<%--				Month Pass - 3.5G--%>
<%--			</td>--%>
<%--			<td class="numeral">25</td>--%>
<%--			<td class="numeral">625.00</td>--%>
<%--		</tr>--%>
<%--	</tbody>--%>
<%--</table>--%>