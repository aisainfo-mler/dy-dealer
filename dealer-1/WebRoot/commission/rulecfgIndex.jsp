<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent"><a href="javascript:void(0)"> <s:text
					name="rule.list" /> </a>
		</li>
	</ul>
</div>
<div class="tab_handle">
	<a class="add" href="javascript:void(0)" onclick="rulecfg_add()"><s:text
			name="rule.add" /> </a>

</div>
<div>
	<form id="rule_main_search_form">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="query" style="border-bottom:1px solid #EBEBEB">
			<tbody>
				<tr>
					<td class="Hint" width="520"><s:text name="rule.ruleName" />:</td>
					<td><input type="text" class="d_input" name="rule.ruleName" />
					</td>
					<td>
						<button type="button" class="searchBtn"
							onclick="rulecfg_search();">
							<s:text name="common.search" />
						</button></td>
				</tr>
			</tbody>
		</table>
	</form>
	<div id="user_main_div"></div>
</div>

<div id="rulecfg_list"></div>
<script type="text/javascript"
	src="<s:url value="/commission/rulecfgRule.js"/>"></script>
<script type="text/javascript">
	rulecfg_search();
</script>