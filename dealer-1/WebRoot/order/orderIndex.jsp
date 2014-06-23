<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<div class="uitopb uitopb-border">
		<ul class="tabbar">
			<li class="tabCurrent">
				<a href="javascript:void(0)"><s:text name="order.list" /></a>
			</li>
		</ul>
	</div>
	
	<div>
		<form id="order_main_search_form">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="query" style="border-bottom:1px solid #EBEBEB">
				<tbody>
					<tr>
						<td class="Hint">
							<s:text name="order.orderCode" />:
						</td>
						<td>
							<input type="text" class="d_input" name="orderMain.serialNumber"/>
						</td>
						<td class="Hint">
							<s:text name="order.agent" />:
						</td>
						<td>
							<input type="text" class="d_input" name="orderMain.creator.name"/>
						</td>
						<td class="Hint">
							<s:text name="order.status" />：
						</td>
						<td>
<%--							<s:select class="d_input" name="orderMain.status" list="%{@com.ai.mapp.sys.util.SYSConstant@orderTypes}" listKey="key" listValue="value" headerKey="" headerValue=""  ></s:select>--%>
							<select name="orderMain.status" class="d_input">
								<option >&nbsp;</option>
								<s:iterator  var="orderTypesUnit" value="%{@com.ai.mapp.sys.util.SYSConstant@orderTypesL}" >
										<option value='${orderTypesUnit}'><s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('orderTypes',#orderTypesUnit + session.WW_TRANS_I18N_LOCALE)}" /></option>
								</s:iterator>
							</select>
						</td>
						<td>
							<button type="button" class="searchBtn" onclick="order_main_search();">
								<s:text name="common.search" />
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="order_main_div"></div>
	</div>
	<script type="text/javascript" src="<s:url value="/order/order.js"/>"></script>
	<script type="text/javascript">
	order_main_search();
	</script>
