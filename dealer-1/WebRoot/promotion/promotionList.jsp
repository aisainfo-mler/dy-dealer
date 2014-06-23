<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
			

<table cellspacing="0" cellpadding="0" class="listTable">
 <thead>
	<tr>
		<th><s:text name="promotion.id" /></th>
		<th nowrap><s:text name="promotion.effDate" /></th>
		<th nowrap><s:text name="promotion.expDate" /></th>
		<th><s:text name="user.status" /></th>
		<th nowrap><s:text name="common.type" /></th>
		<th><s:text name="form.text.remark" /></th>
	</tr>
 </thead>
 <tbody>
	<s:if test="%{promotionList==null || promotionList.size==0}">
		<tr>
			<td  colspan="6" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>
		</tr>
	</s:if>
	<s:else>
		<s:set name="stateValid" value="%{@com.ai.mapp.sys.util.SYSConstant@STATE_VALID}"></s:set>
		<s:set name="stateInvalid" value="%{@com.ai.mapp.sys.util.SYSConstant@STATE_INVALID}"></s:set>
		<s:set name="stateWaiting" value="%{@com.ai.mapp.sys.util.SYSConstant@STATE_WAITING_4_AUDIT}"></s:set>
		<s:iterator  var="promotionUnit" value="promotionList" status="rowstatus">
			<tr>
				<td class="B L"> <a href="javascript:void(0)" onclick="promotion_edit(<s:property value='%{#promotionUnit.id}'/>)" ><s:property value="%{#promotionUnit.id}"/></a>&nbsp; </td>
				<td nowrap> <s:date name="effDate" format="MM/dd/yyyy hh:mm a"/> &nbsp;</td>
				<td nowrap> <s:date name="expDate" format="MM/dd/yyyy hh:mm a"/> &nbsp;</td>
				<td> <s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('stateTypes',#promotionUnit.status + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;</td>
<%--				<td nowrap> <s:date name="createTime" format="MM/dd/yyyy hh:mm a"/> &nbsp;</td>--%>
				<td nowrap>
					<s:if test="%{#promotionUnit.type==1}">
						<s:text name="promotion.type.promotion" />
					</s:if>
					<s:if test="%{#promotionUnit.type==2}">
						<s:text name="promotion.type.home" />
					</s:if>
					<s:if test="%{#promotionUnit.type==3}">
						<s:text name="promotion.type.newUser" />
					</s:if>
					<s:if test="%{#promotionUnit.type==4}">
						<s:text name="promotion.type.topUp" />
					</s:if>		
				</td>
				<td class="L">
					<s:property value="%{#promotionUnit.remark}"/>&nbsp;
				</td>
<%--				<td>--%>
<%--					<s:if test="%{ #userUnit.status == #stateInvalid }">--%>
<%--						<input type="button" value="有效" onclick="user_change_status(<s:property value="%{#userUnit.userId}"/>,<s:property value="#stateValid"/>,'置为有效')"/>--%>
<%--					</s:if>--%>
<%--					<s:elseif test="%{ #userUnit.status == #stateValid }">--%>
<%--						<input type="button" value="无效" onclick="user_change_status(<s:property value="%{#userUnit.userId}"/>,<s:property value="#stateInvalid"/>,'置为无效')"/>--%>
<%--					</s:elseif>--%>
<%--					<s:elseif test="%{#userUnit.status == #stateWaiting }">--%>
<%--						<input type="button" value="通过" onclick="user_change_status(<s:property value="%{#userUnit.userId}"/>,<s:property value="#stateValid"/>,'通过审核')"/>--%>
<%--					</s:elseif>--%>
					
<!-- 					<a href="javascript:void(0)" style="text-decoration:none;" onclick="user_detail(this)" ><s:text name="common.detail" /></a> -->
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
         