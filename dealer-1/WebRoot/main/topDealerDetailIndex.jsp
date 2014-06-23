<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<form id="main_topDealerDetailF">
	<input type="hidden" name="creatorId" value="${creatorId}"/>
	<input type="hidden" name="ifThisMonth" value="${ifThisMonth}"/>
</form>
<div id="main_topDealerDetailD">

</div>
<script type="text/javascript">
	$j(document).ready(function(){
		var userId = $j("input[name='creatorId']").val();
		var ifThisMonth = $j("input[name='ifThisMonth']").val();
		if(IsSpace(userId)){
			return false;
		}
		var url = getURL("/main/topDealerDetail.do");
		var data = {
				'creatorId':parseInt(userId),
				'ifThisMonth':ifThisMonth
		};
		var target = $j("#main_topDealerDetailD");
		loadPage(target,url,data,function(_d){});

	});
		
</script>