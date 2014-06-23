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
		<form id="user_main_search_form">
			<input type="hidden" name="userCondition.status" value="<s:property value='%{@com.ai.mapp.sys.util.SYSConstant@STATE_WAITING_4_AUDIT}'/>" />
			<input type="hidden" name="userCondition.userType" value="<s:property value='%{@com.ai.mapp.sys.util.SYSConstant@USER_TYPE_AGENT}'/>" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="query" style="border-bottom:1px solid #EBEBEB">
				<tbody>
					<tr>
						<td class="Hint" width="520">
							<s:text name="user.name" />:
						</td>
						<td>
							<input type="text" class="d_input" name="userCondition.name"/>
						</td>
<%--						<td class="Hint">--%>
<%--							状态：--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<s:select class="d_input" name="userCondition.status" list="%{@com.ai.mapp.sys.util.SYSConstant@stateTypes}" listKey="key" listValue="value" headerKey="" headerValue="请选择" value="%{@com.ai.mapp.sys.util.SYSConstant@STATE_WAITING_4_AUDIT}" ></s:select>--%>
<%--						</td>--%>
<%--						<td class="Hint">--%>
<%--							类型：--%>
<%--						</td>--%>
<%--						<td>--%>
<%--							<s:select class="d_input" name="userCondition.userType" list="%{@com.ai.mapp.sys.util.SYSConstant@userTypes}" listKey="key" listValue="value" headerKey="" headerValue="请选择" ></s:select>--%>
<%--						</td>--%>
						<td>
							<button type="button" class="searchBtn" onclick="user_main_search();">
								<s:text name="common.search" />
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="user_main_div"></div>
	</div>
	<script type="text/javascript" src="<s:url value='/user/user.js'/>"></script>
	<script type="text/javascript">
	user_main_search();
	</script>
