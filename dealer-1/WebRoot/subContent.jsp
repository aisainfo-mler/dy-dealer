<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="sideBar" id="sub_category">
	<%@ include file="/left.jsp"%>
</div>
<input type="hidden" name="current_menu_url" value="${menuUrl}" />
<div id="sub_Content" class="mainCon">
	
</div>
<script type="text/javascript">
$j(document).ready(function(){
	var menu_url = $j("input[name='current_menu_url']").val();
	if(!IsSpace(menu_url)){
		menu_url = getURL(menu_url);
		loadPage($j("#sub_Content"),menu_url,{},function(_d){});
	}else{
		$j("#sub_category li").eq(0).children("a").trigger("click");
	}
});

</script>
