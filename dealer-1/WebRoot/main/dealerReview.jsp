<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<table class="listTable" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<th>Account ID</th>
			<th>Dealer Name</th>
			<th>Created</th>
<%--			<th>Operate</th>--%>
		</tr>
		<s:if test="%{dealerList==null || dealerList.size==0}">
			<tr>
				<td  colspan="4" style="color: red; text-align: center;"><s:text name="table.body.noData" />!</td>
			</tr>
		</s:if>
		<s:else>
			<s:set name="stateValid" value="%{@com.ai.mapp.sys.util.SYSConstant@STATE_VALID}"></s:set>
			<s:set name="stateInvalid" value="%{@com.ai.mapp.sys.util.SYSConstant@STATE_INVALID}"></s:set>
			<s:iterator  var="dealer" value="dealerList" status="rowstatus">
				<tr>
					<td class="L B"><a href="javascript:void(0)"  onclick="main_dealerReview_detail(this)" ><s:property value="#dealer.userCode"/></a></td>
					<td class="L"><s:property value="%{#dealer.lastName}"/> &nbsp; <s:property value="%{#dealer.firstName}"/></td>
					<td class="L"><s:date name="createTime" format="MM/dd/yyyy hh:mm a"/> &nbsp;</td>
<%--					<td>--%>
<%--						<a href="javascript:void(0)" style="text-decoration:none;" onclick="main_dealerReview_detail(this)" ><s:text name="common.detail" /></a>--%>
<%--					</td>--%>
				</tr>
				<tr style="display:none">
				<td colspan="4">
					<div class="userPic">
						<s:if test="%{#dealer.showPic ==null || #dealer.showPic.fileUpload == null}" >
		         			<img src="images/pic_def_128.png"/>
						</s:if>
						<s:else>
							<img src="<s:url value='%{#dealer.showPic.fileUpload.filePath}'/>" height="200"/>
						</s:else>
					</div>
					<table border="0" cellpadding="0" cellspacing="0"  class="detailTableBorder">
						<tbody>
							<tr>
								<td class="Hint">
									<s:text name="user.lastname" />：
								</td>
								<td>
									<s:property value="%{#dealer.lastName}"/> &nbsp;
								</td>
								<td class="Hint">
									<s:text name="user.firstname" />：
								</td>
								<td>
									<s:property value="%{#dealer.firstName}"/> &nbsp; 
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.sex" />：
								</td>
								<td>
									<s:if test="%{#dealer.gender == null || #dealer.gender == ''}">
										<s:text name="common.unknow" />
									</s:if>
									<s:else>
										<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('genderType',#dealer.gender + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
									</s:else>
								</td>
								<td class="Hint">
									<s:text name="user.type" />：
								</td>
								<td>
									<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('userTypes',#dealer.userType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.city.cityName" />：
								</td>
								<td>
									<s:property value="%{#dealer.city.cityName}"/> &nbsp;
								</td>
								<td class="Hint">
									<s:text name="user.street" />：
								</td>
								<td>
									<s:property value="%{#dealer.street}"/>&nbsp; 
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
									<s:property value="%{#dealer.houseNumber}"/> &nbsp;
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.mobile" />：
								</td>
								<td>
									<s:property value="%{#dealer.mobileNo}"/> &nbsp;
								</td>
								<td class="Hint">
									<s:text name="common.contactPhone" />：
								</td>
								<td>
									<s:property value="%{#dealer.contractPhone}"/> &nbsp;
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.email" />：
								</td>
								<td>
									<s:property value="%{#dealer.email}"/> &nbsp;
								</td>
								<td class="Hint">
									<s:text name="user.userCode" />：
								</td>
								<td>
									<s:property value="%{#dealer.userCode}"/> &nbsp;
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.certify" />：
								</td>
								<td>
									<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('certificateTypes',#dealer.certificateType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
								</td>
								<td class="Hint">
									<s:text name="user.title" />：
								</td>
								<td>
									<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('userTitleType',#dealer.userTitle + session.WW_TRANS_I18N_LOCALE)}" />&nbsp;
								</td>
							</tr><tr>
								<td class="Hint">
									<s:text name="user.status" />：
								</td>
								<td>
									<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('stateTypes',#dealer.status + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
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
									<textarea rows="3" cols="35"  class="d_input" name="userUnit_reason"><s:property value="%{#dealer.reason}"/></textarea>
								</td>
							</tr>
						</tbody>
					</table>
					<br/>
					<div align="center">
						<input type="button" class="d_button6" value="<s:text name="common.pass" />" onclick="main_dealerReview_change_status(<s:property value="%{#dealer.userId}"/>,<s:property value="#stateValid"/>,'<s:text name="user.passAudit" />')"/>
						<input type="button" class="d_buttonThin6" value="<s:text name="common.noPass" />" onclick="main_dealerReview_change_status(<s:property value="%{#dealer.userId}"/>,<s:property value="#stateInvalid"/>,'<s:text name="user.noPassAudit" />')"/>
					</div>
				</td>
			</tr>
			</s:iterator>	
		</s:else>
	</tbody>
</table>

<%--<table class="listTable" cellpadding="0" cellspacing="0">--%>
<%--	<tbody>--%>
<%--		<tr>--%>
<%--			<th>Account ID</th>--%>
<%--			<th>Dealer Name</th>--%>
<%--			<th>Create Time</th>--%>
<%--			<th>Operate</th>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">--%>
<%--				Haines@gmail.com--%>
<%--			</td>--%>
<%--			<td>Howard Haines</td>--%>
<%--			<td>09/29/2012 03:08 PM</td>--%>
<%--			<td>--%>
<%--				<a href="javascript:void(0)">Detail</a>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">--%>
<%--				Glover@gmail.com--%>
<%--			</td>--%>
<%--			<td>Jenny Glover</td>--%>
<%--			<td>09/26/2012 02:06 AM</td>--%>
<%--			<td>--%>
<%--				<a href="javascript:void(0)">Detail</a>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">--%>
<%--				Eugene@hotmail.com--%>
<%--			</td>--%>
<%--			<td>Eugene Rippy</td>--%>
<%--			<td>09/26/2012 02:05 AM</td>--%>
<%--			<td>--%>
<%--				<a href="javascript:void(0)">Detail</a>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">--%>
<%--				Thomas@hotmail.com--%>
<%--			</td>--%>
<%--			<td>Burt Thomas</td>--%>
<%--			<td>09/26/2012 02:04 AM</td>--%>
<%--			<td>--%>
<%--				<a href="javascript:void(0)">Detail</a>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td class="L B">--%>
<%--				Baldwin@hotmail.com--%>
<%--			</td>--%>
<%--			<td>Karen Baldwin</td>--%>
<%--			<td>09/26/2012 01:57 AM</td>--%>
<%--			<td>--%>
<%--				<a href="javascript:void(0)">Detail</a>--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--	</tbody>--%>
<%--</table>--%>