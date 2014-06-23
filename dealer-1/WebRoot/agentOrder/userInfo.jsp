<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<%--<div class="userPic">--%>
<%--	<s:if test="%{svnInfo.customer.showPic ==null || svnInfo.customer.showPic.fileUpload == null}" >--%>
<%--      			<img src="images/pic_def_128.png"/>--%>
<%--	</s:if>--%>
<%--	<s:else>--%>
<%--		<img src="<s:url value='%{svnInfo.customer.showPic.fileUpload.filePath}'/>" height="200"/>--%>
<%--	</s:else>--%>
<%--</div>--%>
<table border="0" cellpadding="0" cellspacing="0"  class="detailTableBorder">
	<tbody>
		<tr>
			<td class="Hint" style="width:130px">
				<s:text name="user.lastname" />
			</td>
			<td>
				<s:property value="%{svnInfo.customer.lastName}"/> &nbsp;
			</td>
			<td class="Hint" style="width:130px">
				<s:text name="user.firstname" />
			</td>
			<td style="width:130px">
				<s:property value="%{svnInfo.customer.firstName}"/> &nbsp; 
			</td>
		</tr><tr>
			<td class="Hint" style="width:130px">
				<s:text name="user.sex" />
			</td>
			<td>
				<s:if test="%{svnInfo.customer.gender == null || svnInfo.customer.gender == ''}">
					<s:text name="common.unknow" />
				</s:if>
				<s:else>
					<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('genderType',svnInfo.customer.gender + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
				</s:else>
			</td>
			<td class="Hint" style="width:130px">
				<s:text name="user.type" />
			</td>
			<td style="width:130px">
				<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('userTypes',svnInfo.customer.userType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
			</td>
		</tr><tr>
			<td class="Hint" style="width:130px">
				<s:text name="user.city" />
			</td>
			<td>
				<s:property value="%{svnInfo.customer.city.cityName}"/> &nbsp;
			</td>
			<td class="Hint" style="width:130px">
				<s:text name="user.street" />
			</td>
			<td style="width:130px">
				<s:property value="%{svnInfo.customer.street}"/> &nbsp; <s:property value="%{svnInfo.customer.street}"/>
			</td>
		</tr><tr>
			<td class="Hint" style="width:130px">
				<s:text name="user.birthday" />
			</td>
			<td>
				<s:date name="birthDay" format="MM/dd/yyyy"/> &nbsp;
			</td>
			<td class="Hint" style="width:130px">
				<s:text name="user.telephone" />
			</td>
			<td style="width:130px">
				<s:property value="%{svnInfo.customer.houseNumber}"/> &nbsp;
			</td>
		</tr><tr>
			<td class="Hint" style="width:130px">
				<s:text name="user.mobile" />
			</td>
			<td>
				<s:property value="%{svnInfo.customer.mobileNo}"/> &nbsp;
			</td>
			<td class="Hint" style="width:130px">
				<s:text name="common.contactPhone" />
			</td>
			<td style="width:130px">
				<s:property value="%{svnInfo.customer.contractPhone}"/> &nbsp;
			</td>
		</tr><tr>
			<td class="Hint" style="width:130px">
				<s:text name="user.email" />
			</td>
			<td>
				<s:property value="%{svnInfo.customer.email}"/> &nbsp;
			</td>
			<td class="Hint" style="width:130px">
				<s:text name="user.userCode" />
			</td>
			<td style="width:130px">
				<s:property value="%{svnInfo.customer.userCode}"/> &nbsp;
			</td>
		</tr><tr>
			<td class="Hint" style="width:130px">
				<s:text name="user.certify" />
			</td>
			<td>
				<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('certificateTypes',svnInfo.customer.certificateType + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
			</td>
			<td class="Hint" style="width:130px">
				<s:text name="user.title" />
			</td>
			<td style="width:130px">
				<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('userTitleType',svnInfo.customer.userTitle + session.WW_TRANS_I18N_LOCALE)}" />&nbsp;
			</td>
		</tr><tr>
			<td class="Hint" style="width:130px">
				<s:text name="user.status" />
			</td>
			<td>
				<s:property value="%{@com.ai.mapp.sys.util.SYSConstant@getDictName('stateTypes',svnInfo.customer.status + session.WW_TRANS_I18N_LOCALE)}" /> &nbsp;
			</td>
			<td class="Hint" style="width:130px">
			</td>
			<td style="width:130px">
			</td>
		</tr><tr>
			<td class="Hint" style="width:130px">
				<s:text name="user.reason" />
			</td>
			<td colspan="3" >
				<s:property value="%{svnInfo.customer.reason}"/>
			</td>
		</tr>
	</tbody>
</table>
