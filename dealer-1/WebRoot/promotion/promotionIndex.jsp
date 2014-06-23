<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

	<div class="uitopb uitopb-border">
		<ul class="tabbar">
			<li class="tabCurrent">
				<a href="javascript:void(0)"><s:text name="promotion.list" /></a>
			</li>
		</ul>
		<div class="tab_handle">
			<a href="javascript:void(0)" class="add" onclick="promotion_edit();"><s:text name="user.joinRule" /></a>
		</div>
	</div>
	<div>
		<form id="promotion_query_form">
			<input type="hidden" name="promotion.status" value="<s:property value='%{@com.ai.mapp.sys.util.SYSConstant@STATE_WAITING_4_AUDIT}'/>" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="query" style="border-bottom:1px solid #EBEBEB">
				<tbody>
					<tr>
						<td class="Hint">
							<s:text name="user.status" />:
						</td>
						<td>
							<select id="promotion.status" class="d_input" name="promotion.status">
								<option value="">&nbsp;</option>
								<option value="1" selected><s:text name="common.valid" /></option>
								<option value="0"><s:text name="common.inValid" /></option>
							</select>
						</td>
						<td class="Hint">
							<s:text name="promotion.type" />:
						</td>
						<td>
							<select name="promotion.type" class="d_input" >
								<option value="">&nbsp;</option>
								<option value="1"><s:text name="promotion.type.promotion" /></option>
								<option value="2"><s:text name="promotion.type.home" /></option>
								<option value="3"><s:text name="promotion.type.newUser" /></option>
							</select>
						</td>
						<td>
							<button type="button" class="searchBtn" onclick="promotion_search();">
								<s:text name="common.search" />
							</button>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div id="promotion_list"></div>
	</div>
	<script type="text/javascript" src="<s:url value="/promotion/promotion.js"/>"></script>
	<script type="text/javascript">
	promotion_search();
	</script>
