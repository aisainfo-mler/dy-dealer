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
		<form id="agentOrder_main_search_form">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="query" style="border-bottom:1px solid #EBEBEB">
				<tbody>
					<tr>
						<td class="Hint">
							<s:text name="order.orderCode" />:
						</td>
						<td>
							<input type="text" class="d_input" name="agentOrderCondition.orderCode"/>
						</td>
						<td class="Hint">
							<s:text name="order.status" />：
						</td>
						<td>
<%--							<s:select class="d_input" name="agentOrderCondition.status" list="%{@com.ai.mapp.sys.util.SYSConstant@agentOrderStatus}" listKey="key" listValue="value" headerKey="" headerValue="" value="%{@com.ai.mapp.sys.util.SYSConstant@AGENT_ORDER_STATUS_WAITTING}" ></s:select>--%>
							<select name="agentOrderCondition.status">
								<option >&nbsp;</option>
								<s:iterator  var="agentOrderStatusUnit" value="%{@com.ai.mapp.sys.util.SYSConstant@agentOrderStatusL}" >
										<option value='${agentOrderStatusUnit}'
											<s:if test="%{@com.ai.mapp.sys.util.SYSConstant@AGENT_ORDER_STATUS_WAITTING == #agentOrderStatusUnit}" >
												selected="selected"
											</s:if>
										><s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('agentOrderStatus',#agentOrderStatusUnit + session.WW_TRANS_I18N_LOCALE)}" /></option>
								</s:iterator>
							</select>
							
						</td>
						<td class="Hint">
							<s:text name="common.type" />：
						</td>
						<td>
<%--							<s:select class="d_input" name="agentOrderCondition.orderType" list="%{@com.ai.mapp.sys.util.SYSConstant@agentOrderTypes}" listKey="key" listValue="value" headerKey="" headerValue="" ></s:select>--%>
								<select name="agentOrderCondition.orderType">
									<option >&nbsp;</option>
									<s:iterator  var="agentOrderTypesUnit" value="%{@com.ai.mapp.sys.util.SYSConstant@agentOrderTypesL}" >
											<option value='${agentOrderTypesUnit}'
												<s:if test="%{@com.ai.mapp.sys.util.SYSConstant@AGENT_ORDER_STATUS_WAITTING == #agentOrderTypesUnit}" >
													selected="selected"
												</s:if>
											><s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('agentOrderTypes',#agentOrderTypesUnit + session.WW_TRANS_I18N_LOCALE)}" /></option>
									</s:iterator>
								</select>
						</td>
						<td>
							<button type="button" class="searchBtn" onclick="agentOrder_main_search();">
								<s:text name="common.search" />
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="agentOrder_main_div"></div>
	</div>
	<script type="text/javascript" src="<s:url value="/agentOrder/agentOrder.js"/>"></script>
	<script type="text/javascript">
	agentOrder_main_search();
	</script>
