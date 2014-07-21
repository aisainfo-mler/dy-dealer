<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent"><a href="javascript:void(0)"> <s:text
					name="aiBusi.list" /> </a></li>
	</ul>
</div>
<div class="tab_handle">
	<a class="add" href="javascript:void(0)" onclick="busicfg_add()"><s:text
			name="aiBusi.add" /> </a>

</div>
<div>
	<form id="busic_main_search_form">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="query" style="border-bottom:1px solid #EBEBEB">
			<tbody>
				<tr>
					<td class="Hint" width="520"><s:text name="aiBusi.remarks" />:</td>
					<td><input type="text" class="d_input" name="busi.remark"  value="<s:property value="busi.remark"/>"/>
					</td>
					<td>
						<button type="button" class="searchBtn"
							onclick="busicfg_search();">
							<s:text name="common.search" />
						</button>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<div id="user_main_div"></div>
</div>

<div id="busicfg_list"></div>
<script type="text/javascript"
	src="<s:url value="/commission/busicfgRule.js"/>"></script>
<script type="text/javascript">
	busicfg_search();

	
</script>