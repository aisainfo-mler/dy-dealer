<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

 <div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent">
			<a href="javascript:void(0)">
				<s:text name="order.detail.info" />
			</a>
		</li>
	</ul>
 </div>
 <input type="hidden" name="orderDetail_isHome" value="${isHome}"/>
	<div class="order_handle">
<%--		<s:if test='%{orderMain.status == "2"}'>--%>
<%--			<s:if test="%{orderMain.expressNumber == null || orderMain.expressNumber == ''}">--%>
<%--				<input type="button" value="<s:text name="order.send" />" class="d_button4" onclick="order_open_send(${orderMain.id})"/>--%>
<%--			</s:if>--%>
<%--		</s:if>--%>
		<s:if test='%{orderMain.status == "2"}'><!-- 正在处理的单子  没有向TIBCO发起的要发起 -->
			<s:if test="%{orderMain.placeTibco == null || orderMain.placeTibco == 0 || orderMain.placeTibco == '0'}">
				<input type="button" value="<s:text name="order.send" />" class="d_button4" onclick="order_tibco(${orderMain.id})"/>
			</s:if>
		</s:if>
		<s:if test="%{isHome == null || isHome == '0' || isHome == 0}">
			<input type="button" value="<< &nbsp;<s:text name="order.return" />" class="d_button4" onclick="order_detail_return()"/>
		</s:if>
	</div>
	
	<div class="orderDetailAgent">
		<h3><s:text name="agent.info" /></h3>
		<table class="detailTable" border="0" cellspacing="0" cellpadding="5">
		    <tr>
		        <td class="Hint"><s:text name="agent.name" />:&nbsp;</td>
		        <td class="B orange">${orderMain.creator.lastName}&nbsp;${orderMain.creator.firstName}</td>
		       	<td class="Hint"><s:text name="account.info" />:&nbsp;</td>
		        <td>
		        	<s:if test="%{orderMain.creator.accounts == null || orderMain.creator.accounts.size==0}">
						&nbsp;
					</s:if>
					<s:else>
						${orderMain.creator.accounts[0].name}
					</s:else>
		        </td>
		    </tr>
		    <tr>
		        <td class="Hint" valign="top" width="100"><s:text name="agent.contactPhone" />:&nbsp;</td>
		        <td>${orderMain.creator.mobileNo}</td>
		        <td class="Hint"><s:text name="agent.city" />:&nbsp;</td>
		        <td colspan="1">${orderMain.creator.city.cityName}</td>
<!-- 		        <td class="Hint" valign="top"><s:text name="order.createTime" />：</td> -->
<!-- 		        <td width="150"> -->
<!-- 		        	<s:date name="orderMain.createTime" format="MM/dd/yyyy hh:mm a"/> -->
<!-- 		        </td> -->
		    </tr>
<!-- 		    <tr> -->
<!-- 		        <td class="Hint"><s:text name="agent.city" />：</td> -->
<!-- 		        <td colspan="3">${orderMain.creator.city.cityName}</td> -->
<!-- 		    </tr> -->
		</table>
		<br/>
	</div>
	<div class="orderInfo">
		<h3><s:text name="order.information" /></h3>
		<table class="detailTable" border="0" cellspacing="0" cellpadding="5">
			<tbody>
				<tr><td class="Hint"><s:text name="order.orderCode" />:&nbsp;</td><td>${orderMain.serialNumber}</td></tr>
<%--				<tr><td class="Hint"><s:text name="order.paynum" /> ：</td><td>${orderMain.blankserialNumber}</td></tr>--%>
				<tr><td class="Hint"><s:text name="order.createTime" />:&nbsp;</td><td><s:date name="orderMain.createTime" format="MM/dd/yyyy hh:mm a"/></td></tr>
<!-- 				<tr><td class="Hint"><s:text name="order.payTime" /> ：</td><td><s:date name="orderMain.payTime" format="MM/dd/yyyy hh:mm a"/></td></tr> -->
			</tbody>
		</table>
	</div>
	<div class="orderDetail">
	    <table class="listTable" border="0" cellspacing="0" cellpadding="0">
       		<thead>
       			<tr>
       				<th><s:text name="good.name" /></th>
       				<th><s:text name="good.price" /></th>
       				<th><s:text name="order.detail.count" /></th>
<%--       				<th><s:text name="order.detail.discount" /></th>--%>
       				<th><s:text name="order.detail.items" /></th>
       				<th><s:text name="order.totalPrice" /></th>
       				<th><s:text name="order.expressCharge" /></th>
       			</tr>
       		</thead>
       		<tbody>
       			<s:iterator  var="orderDetail" value="orderDetails" status="rr">
					<tr>
						<td>
							<span class="funIcon">
								<s:if test="%{#orderDetail.good.listpic == null}" >
				         			<img src='<s:url value="/images/pic_def_32.png" />'/>
								</s:if>
								<s:else>
									<img src="<s:url value='%{#orderDetail.good.listpic.fileUpload.filePath}'/>"/>
								</s:else>
							</span>
							&nbsp;&nbsp;
							<s:property value="#orderDetail.good.name"/>
						</td>
						<td><s:property value="#attr.wholeMoneySymbol"/><s:property value="@com.ai.mapp.base.MoneyUtils@formatToMoney(#orderDetail.good.price)" /></td>
						<td><span name="order_detail_counts">
								<s:if test="%{#orderDetail.counts==null}">
									0
									<input type="hidden" name="order_item_count" value="0">
								</s:if>
								<s:else>
									<s:property value="#orderDetail.counts"/>
									<input type="hidden" name="order_item_count" value="${orderDetail.counts}">
								</s:else>
							</span>
							<s:if test="%{#orderDetail.items==null || #orderDetail.items.size==0}">
								<s:set name="orderDetailValueNum" value="0" />
							</s:if>
							<s:else>
								<s:set name="orderDetailValueNum" value="%{#orderDetail.items.size}" />
							</s:else>
							<s:if test="%{orderMain.status != 0 && orderMain.status != '0'}">
								<s:if test="%{orderMain.expressNumber == null || orderMain.expressNumber == ''}">
									&nbsp;&nbsp;(&nbsp;<span class="red"><s:text name="order.detail.hadInput" /> &nbsp;<span name="order_detail_num_${orderDetail.id}"><s:property value="#orderDetailValueNum"/></span> &nbsp;<s:text name="common.unit" /></span>&nbsp;)
									<input type="hidden" name="order_item_count_processed" id="order_item_count_processed${orderDetail.id}" value="<s:property value="#orderDetailValueNum"/>">
								</s:if>
							</s:if>
						</td>
						<td>
							<s:if test='%{orderMain.status == "2" && (orderMain.expressNumber == null || orderMain.expressNumber == "") && (orderMain.placeTibco == 1 || orderMain.placeTibco == "1")}'>
<%--							<s:if test='%{orderMain.status == "2" && (orderMain.expressNumber == null || orderMain.expressNumber == "")}'>--%>
								<a href="javascript:void(0);" onclick="order_itemList('${orderMain.expressNumber}',${orderDetail.id},${orderDetail.good.id},<s:property value="%{#orderDetail.counts}"/>,${orderMain.creator.userId})"><s:text name="order.detail.process" /></a>
							</s:if>
							<s:else>
								<s:property value="@com.ai.mapp.sys.util.SYSConstant@getDictName('orderTypes',orderMain.status + session.WW_TRANS_I18N_LOCALE)" />
							</s:else>
						</td>
						<s:set name="orderMainExpressCharge" value="0"></s:set>
						<s:if test="%{orderMain.expressCharge != null}" >
		         			<s:set name="orderMainExpressCharge" value="%{orderMain.expressCharge}"></s:set>
						</s:if>
						<s:if test="#rr.index==0">
							<td rowspan="<s:property value="orderDetails.size"/>"> <s:property value="#attr.wholeMoneySymbol"/> <s:property value="%{@com.ai.mapp.base.MoneyUtils@formatToMoney( orderMain.fee )}" /></td>
							<td rowspan="<s:property value="orderDetails.size"/>">&nbsp; <s:property value="#attr.wholeMoneySymbol"/> <s:property value="%{@com.ai.mapp.base.MoneyUtils@formatToMoney( #orderMainExpressCharge )}" /></td>
						</s:if>
					</tr>
				</s:iterator>
       		</tbody>
       	</table>
		<div class="R" style="padding-top:10px;font-size:14px;">
		<s:if test="%{orderMain.status != 0 && orderMain.status != '0'}">
			<%--<s:text name="order.realPay" /> ：--%>
			<s:text name="order.total" /> ：
		</s:if>
		<s:else>
			<%--<s:text name="order.shouldPay" /> ：--%>
			<s:text name="order.total" /> ：
		</s:else>
							
    		
    		<span class="red B">
    			<s:property value="#attr.wholeMoneySymbol"/>
    			<s:set name="orderMainFee" value="0"></s:set>
				<s:if test="%{orderMain.realFee != null}" >
         			<s:set name="orderMainFee" value="%{orderMain.realFee}"></s:set>
				</s:if>
				
    			<s:set name="realPay" value="%{#orderMainExpressCharge + #orderMainFee}" />
    			<s:property value="@com.ai.mapp.base.MoneyUtils@formatToMoney( #realPay )" />
    		</span>  
    	</div>
		<br/>
		<hr/>
	</div>
	<s:if test="%{hwOrderShipmentList!= null && hwOrderShipmentList.size > 0}">
		<div class="orderDetailLogs">
			<h3><s:text name="order.sendInfo" /></h3>
            <ul>
                
            	<s:if test="%{hwOrderShipmentList==null || hwOrderShipmentList.size==0}">
					<li>
                    	<span class="odl-text"><s:text name="table.body.noData" />!</span>
               		</li>
				</s:if>
				<s:else>
					<s:iterator  var="shipment" value="hwOrderShipmentList" status="rowstatus">
						<li>
                    		<span class="odl-time"><s:date name="createTime" format="MM/dd/yyyy hh:mm a"/></span>
                    		<span class="odl-text">
                    		<s:if test="%{#rowstatus.count==1 && #shipment.shipStatus==1}">
                    			<s:text name="order.orderCode" />: <s:property value="orderCode" />,&nbsp;
                    			<s:text name="order.sendNum" />: <s:property value="expressNumber" />,&nbsp;
                    			<s:property value="expressCompanyName" />,&nbsp;
                    			<s:text name="order.shipment.already" /> <s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('shipmentStatus',#shipment.shipStatus + session.WW_TRANS_I18N_LOCALE)}" />.&nbsp;<br/>
                    			<s:text name="order.shipment.recipients" />: <s:property value="recipient" />,&nbsp; <s:property value="recipientAddress" />&nbsp;
                    		</s:if>
                    		<s:else>
                    			<s:text name="common.customer" />,&nbsp;
                    			<s:text name="order.shipment.already" /> <s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('shipmentStatus',#shipment.shipStatus + session.WW_TRANS_I18N_LOCALE)}" />.
                    		</s:else>
                    		</span>
                		</li>
					</s:iterator>	
				</s:else>
				
            </ul>
        </div>
	</s:if>
	<s:if test="%{isHome == null || isHome == '0' || isHome == 0}">
	
	</s:if>
	<s:else>
	<div class="C">
		<input type="button" value="Close" onclick="closeEasyUIWindow($j('#main_backDiv'))" class="d_button4"/>
	</div>
	</s:else>
	<script type="text/javascript" src="<s:url value="/order/order.js"/>"></script>