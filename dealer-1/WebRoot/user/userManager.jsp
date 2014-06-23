<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<div class="uitopb uitopb-border">
		<ul class="tabbar">
			<li class="tabCurrent">
				<a href="javascript:void(0)"><s:text name="user.list" /></a>
			</li>
		</ul>
	</div>
	
	<div>
		<form id="userManager_main_search_form">
<%--			<input type="hidden" name="userCondition.status" value="<s:property value='%{@com.ai.mapp.sys.util.SYSConstant@STATE_WAITING_4_AUDIT}'/>" />--%>
			<input type="hidden" name="userCondition.userType" value="<s:property value='%{@com.ai.mapp.sys.util.SYSConstant@USER_TYPE_AGENT}'/>" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="query" style="border-bottom:1px solid #EBEBEB">
				<tbody>
					<tr>
						<td class="Hint" width="300">
							<s:text name="user.name" />:
						<td>
							<input type="text" class="d_input" name="userCondition.name"/>
						</td>
						<td class="Hint">
							<s:text name="user.status" />:
						</td>
						<td>
							<select id="userCondition_status" class="d_input" name="userCondition.status">
								<option value="">&nbsp;</option>
								<option value="1"><s:text name="common.valid" /></option>
								<option value="0"><s:text name="common.inValid" /></option>
							</select>
<%--							<s:select class="d_input" name="userCondition.status" list="%{@com.ai.mapp.sys.util.SYSConstant@stateTypes}" listKey="key" listValue="value" headerKey="" headerValue="请选择" ></s:select>--%>
						</td>
<%--						<td class="Hint">--%>
<%--							类型：--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<s:select class="d_input" name="userCondition.userType" list="%{@com.ai.mapp.sys.util.SYSConstant@userTypes}" listKey="key" listValue="value" headerKey="" headerValue="请选择" ></s:select>--%>
<%--						</td>--%>
						<td>
							<button type="button" class="searchBtn" onclick="userManager_main_search();">
								<s:text name="common.search" />
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="user_main_div"></div>
		<div style="display:none" id="userManager_changePassword_div">
			<form name="userManager_changePassword_form">
				<input type="hidden" name="userManager_changePwUserId" />
				<table cellspacing="0" cellpadding="0" class="detailTableBorder">
					<tbody>
						<tr>
							<td class="Hint">
								<s:text name="user.userCode" />
							</td>
							<td name="userManager_changePwUserCode">
								
							</td>
						</tr><tr>
							<td class="Hint">
								<s:text name="common.password" />
							</td>
							<td>
								<input type="password" class="d_input {required:true,password:[1, 6],messages:{required:'<s:text name="user.pleaseInputPw" /> '}}" name="userConditionFirstPassword"/>
							</td>
						</tr><tr>
							<td class="Hint">
								<s:text name="common.confirmPassword" />
							</td>
							<td>
								<input type="password" class="d_input {required:true,password:[1, 6],messages:{required:'<s:text name="user.pleaseConfirmPw" />'}}" name="userConditionSecondPassword"/>
							</td>
						</tr><tr>
							<td colspan="2" class="C">
								<button type="button" class="d_button" onclick="userManager_changePassword();">
									<s:text name="common.save" />
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript" src="<s:url value='/user/user.js'/>"></script>
	<script type="text/javascript">
	userManager_main_search();
	</script>
