<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
			

<table cellspacing="0" cellpadding="0" class="listTable">
 <thead>
	<tr>
		<th><s:text name="user.userCode" /></th>
		<th><s:text name="user.name" /></th>
		<th><s:text name="user.createTime" /></th>
		<th><s:text name="user.status" /></th>
<!-- 		<th><s:text name="common.type" /></th> -->
		<th><s:text name="common.contactPhone" /></th>
		<th><s:text name="common.operate" /></th>
	</tr>
 </thead>
 <tbody>
	<s:if test="%{userList==null || userList.size==0}">
		<tr>
			<td  colspan="7" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>
		</tr>
	</s:if>
	<s:else>
		<s:set name="stateValid" value="%{@com.ai.mapp.sys.util.SYSConstant@STATE_VALID}"></s:set>
		<s:set name="stateInvalid" value="%{@com.ai.mapp.sys.util.SYSConstant@STATE_INVALID}"></s:set>
		<s:set name="stateWaiting" value="%{@com.ai.mapp.sys.util.SYSConstant@STATE_WAITING_4_AUDIT}"></s:set>
		<s:iterator  var="userUnit" value="userList" status="rowstatus">
			<tr>
				<td class="B L"> <a href="javascript:void(0)" onclick="user_detail(this)" ><s:property value="%{#userUnit.userCode}"/></a>&nbsp; </td>
				<td class=" L"><s:property value="%{#userUnit.lastName}"/> &nbsp; <s:property value="%{#userUnit.firstName}"/> &nbsp;</td>
				<td nowrap="nowrap"> <s:date name="createTime" format="MM/dd/yyyy hh:mm a"/> &nbsp;</td>
				
				<td> <s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('stateTypes',#userUnit.status + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;</td>
<!-- 				<td> -->
<!-- 					<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('userTypes',#userUnit.userType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp; -->
<!-- 				</td> -->
				<td  class=" L">
					<s:property value="%{#userUnit.contractPhone}"/>&nbsp;
				</td>
				<td>
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
						<a href="javascript:void(0)" style="text-decoration:none;" onclick="userManager_openChangePassword( <s:property value='%{#userUnit.userId}'/>,'<s:property value="%{#userUnit.userCode}"/>' )" ><s:text name="user.changePwShort" /></a>
						|&nbsp;<a href="javascript:void(0)" style="text-decoration:none;" onclick="goCommissionRuleSet(<s:property value='%{#userUnit.userId}'/>)" ><s:text name="user.rule" /></a>
				</td>
			</tr>
			<tr style="display:none">
<%--				<td class="Hint">地址信息</td>--%>
<%--				<td colspan="6">--%>
<%--					<s:property value="%{#userUnit.address}"/>&nbsp;--%>
<%--				</td>--%>
				<td colspan="7">
					<div class="userPic">
						<s:if test="%{#userUnit.showPic ==null || #userUnit.showPic.fileUpload == null}" >
		         			<img src="images/pic_def_128.png"/>
						</s:if>
						<s:else>
							<img src="<s:url value='%{#userUnit.showPic.fileUpload.filePath}'/>" height="200"/>
						</s:else>
					</div>
					<table border="0" cellpadding="0" cellspacing="0" class="detailTableBorder">
						<tbody>
							<tr>
								<td class="Hint">
									<s:text name="user.lastname" />：
								</td>
								<td>
									<s:property value="%{#userUnit.lastName}"/> &nbsp;
								</td>
								<td class="Hint">
									<s:text name="user.firstname" />：
								</td>
								<td>
									<s:property value="%{#userUnit.firstName}"/> &nbsp; 
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.sex" />：
								</td>
								<td>
									<s:if test="%{#userUnit.gender == null || #userUnit.gender == ''}">
										<s:text name="common.unknow" />
									</s:if>
									<s:else>
										<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('genderType',#userUnit.gender + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
									</s:else>
								</td>
								<td class="Hint">
									<s:text name="user.type" />：
								</td>
								<td>
									<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('userTypes',#userUnit.userType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.city.cityName" />：
								</td>
								<td>
									<s:property value="%{#userUnit.city.cityName}"/> &nbsp;
								</td>
								<td class="Hint">
									<s:text name="user.street" />：
								</td>
								<td>
									<s:property value="%{#userUnit.street}"/>&nbsp; 
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.birthday" />：
								</td>
								<td>
									<s:date name="birthDay" format="MM/dd/yyyy"/> &nbsp;
								</td>
								<td class="Hint">
									<s:text name="user.telephone" />：
								</td>
								<td>
									<s:property value="%{#userUnit.houseNumber}"/> &nbsp;
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.mobile" />：
								</td>
								<td>
									<s:property value="%{#userUnit.mobileNo}"/> &nbsp;
								</td>
								<td class="Hint">
									<s:text name="common.contactPhone" />：
								</td>
								<td>
									<s:property value="%{#userUnit.contractPhone}"/> &nbsp;
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.email" />：
								</td>
								<td>
									<s:property value="%{#userUnit.email}"/> &nbsp;
								</td>
								<td class="Hint">
									<s:text name="user.userCode" />：
								</td>
								<td>
									<s:property value="%{#userUnit.userCode}"/> &nbsp;
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.certify" />：
								</td>
								<td>
									<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('certificateTypes',#userUnit.certificateType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
								</td>
								<td class="Hint">
									<s:text name="user.title" />：
								</td>
								<td>
									<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('userTitleType',#userUnit.userTitle + session.WW_TRANS_I18N_LOCALE)}" />&nbsp;
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.status" />：
								</td>
								<td>
									<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('stateTypes',#userUnit.status + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
								</td>
								<td class="Hint">
								</td>
								<td>
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.reason" />：
								</td>
								<td colspan="3">
									<textarea rows="3" cols="35"  class="d_input" name="userUnit_reason"><s:property value="%{#userUnit.reason}"/></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					<br/>
					<div align="center">
						<s:if test="%{ #userUnit.status == #stateInvalid }">
							<input type="button" value="<s:text name="user.changeValid" />" class="d_button6" onclick="user_change_status(<s:property value="%{#userUnit.userId}"/>,<s:property value="#stateValid"/>,'<s:text name="user.changeValid" />')"/>
						</s:if>
						<s:elseif test="%{ #userUnit.status == #stateValid }">
							<input type="button" value="<s:text name="user.changeInValid" />" class="d_button6" onclick="user_change_status(<s:property value="%{#userUnit.userId}"/>,<s:property value="#stateInvalid"/>,'<s:text name="user.changeInValidMs" />')"/>
						</s:elseif>
						<input type="button" value="<s:text name="wxtx.news.btnCancel" />" class="d_button6" onclick="closeEasyUIWindow()"/>
<%--						<input type="button" class="d_buttonThin6" value="通过" onclick="user_change_status(<s:property value="%{#userUnit.userId}"/>,<s:property value="#stateValid"/>,'通过审核')"/>--%>
<%--						<input type="button" class="d_buttonThin6" value="不通过" onclick="user_change_status(<s:property value="%{#userUnit.userId}"/>,<s:property value="#stateInvalid"/>,'不通过')"/>--%>
					</div>
				</td>
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
         