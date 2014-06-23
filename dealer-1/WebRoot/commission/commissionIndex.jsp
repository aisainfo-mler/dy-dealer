<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent">
			<a href="javascript:void(0)">
				<s:text name="commission.rule.list" />
			</a>
		</li>
	</ul>
</div>
<div class="tab_handle">
	<a class="add" href="javascript:void(0)" onclick="commissionRule_addRule()"><s:text name="commission.rule.add" /></a>
</div>
<div id="commissionRule_list">

</div>
<script type="text/javascript" src="<s:url value="/commission/commissionRule.js"/>"></script>
<script type="text/javascript">
	commissionRule_search();
</script>