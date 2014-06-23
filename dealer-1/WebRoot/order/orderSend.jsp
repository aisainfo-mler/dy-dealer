<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<form id="order_send_form">
	<input type="hidden" value="${orderMain.id}" name="orderMain.id" />
	<input type="hidden" name="orderMain.expressCompanyName" /> 
	<table class="detailTableBorder"  cellspacing="0" cellpadding="0">
		    <tr>
		        <td class="Hint"><s:text name="order.sendCompany" />：</td>
		        <td><select name="orderMain.expressCompanyNO" class="d_input">
		        	<option value="SF"><s:text name="order.sendShunf" /></option>
		        	<option value="ST"><s:text name="order.sendShent" /></option>
		        	<option value="YD"><s:text name="order.sendYunda" /></option>
		        	<option value="ZYWL"><s:text name="order.sendFree" /></option>
		        </select></td>
		    </tr>
		    <tr>
		        <td class="Hint"><s:text name="order.sendNum" />：</td>
		        <td><input type="text" class="d_input" size="40" name="orderMain.expressNumber"/></td>
		    </tr>
	</table>
	<br/>
	<div align="center">
		<input type="button" class="d_button4" value="<s:text name="order.send" />" onclick="order_confirm_send()"/>
	</div>
</form>