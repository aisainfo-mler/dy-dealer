<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="%{isWrite == 1}">
	<table class="detailTable" border="0" cellpadding="0" cellspacing="5">
		<tr>
			<td class="Hint">
				<s:text name="order.item.inputSelect" />:
			</td>
			<td>
				<select onchange="order_chooseItemInputType(this)">
					<option value="2"><s:text name="order.item.batch" /></option>
					<option value="1"><s:text name="order.item.manual" /></option>
					<option value="0"><s:text name="order.item.scan" /></option>
				</select>
			</td>
			<td><input type="text" class="d_input" readOnly id="order_manual_inputItem_text" style="display:none;"/></td>
			<td><input type="button" class="d_button4" value="<s:text name="order.item.input" />" disabled id="order_manual_inputItem_button" onclick="order_inputItem('','${isWrite}')"  style="display:none;"/></td>
			<td><input type="button" class="d_button4" value="<s:text name="order.item.startScan" />" onclick="order_machine_inputItem()" id="order_machine_inputItem_button" style="display:none;"/></td>
		</tr>
		<tr id="order_inputItem_batch_tr" >
			<td class="Hint">
				<label>&nbsp;<s:text name="common.forExample" />：</td>
			<td class="gray">02101000000 <br/>--<br/>02103000000</label>
			</td>
			<td width="150"><input type="text" id="order_inputItem_batch_from"/><br/>--<br/><input type="text" id="order_inputItem_batch_end"/></td>
			<td><input type="button" class="d_button4" value="<s:text name="order.item.input" />" id="order_manual_inputItem_button" onclick="order_batch_inputItem('${isWrite}')"/></td>
		</tr>
	</table>
</s:if>
	<div class="cargoInfo d_input">
		
		<s:if test="%{orderItems==null || orderItems.size==0}">
		</s:if>
		<s:else>
	        <s:iterator  var="orderItem" value="orderItems" status="rowstatus">
	        	<div 
	        		<s:if test="%{#orderItem.length() >= 18}">
	        			class="cargoInfoItem_batch"
	        		</s:if>
	        		<s:else>
	        			class="cargoInfoItem"
	        		</s:else>
	        	>
	        		<span name="order_item_span">${orderItem}</span>
	        		<s:if test="%{isWrite == 1}">
	        			<a href='javascript:void(0);' class='closeBtn' onclick="order_deleteItem(this)" title="<s:text name="order.item.deleteItem" />">X</a>
	        		</s:if>
	        	</div>
	        </s:iterator>
	     </s:else>	
    </div>
    <s:if test="%{isWrite == 1}">
    	<s:set name="orderItemTotalNum" value="%{orderDetail.counts}"></s:set>
    	<div style="padding:10px;">
    		 <s:text name="order.item.hadInput" />:&nbsp; <b name="order_item_num" class="red"><s:property value="%{orderItemValueNum}"/></b> &nbsp;&nbsp;<s:text name="order.item.left" />:&nbsp;<b name="order_item_leftNum" class="red"><s:property value="%{#orderItemTotalNum - orderItemValueNum}"/></b>&nbsp;
    	</div>
    	<input type="hidden" name="order_item_num_stard" value="${orderDetail.counts}"/>
    </s:if>
	<s:form id="order_saveOrder_form" action="/order/saveItems.do" method="post">
		<input type="hidden" value="${orderItemValues}" name="orderItemValues" />
		<input type="hidden" value="${orderItemBatchValues}" name="orderItemBatchValues" />
		<input type="hidden" value="${orderDetail.id}" name="orderDetail.id" />
		<input type="hidden" value="${orderDetail.good.id}" name="orderDetail.good.id" />
		<input type="hidden" value="${orderDetail.agentId}" name="orderDetail.agentId" />
	</s:form>
	<s:if test="%{isWrite == 1}">
		<div align="center">
			<br/>
			<input type="button" class="d_button4" value="<s:text name="common.save" />" onclick="order_itemSave()"/>
		</div>
	</s:if>