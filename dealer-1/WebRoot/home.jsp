<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.net.URLEncoder"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<!--头部-->
<%@ include file="/top.jsp"%>
<%--<s:set name="wholeMoneySymbol" value="'￥'" scope="application"/>--%>
<s:set name="wholeMoneySymbol" value="'Rs '" scope="application"/>
<div class="mainBody" id="mainBody_div" name="mainBody_div">
</div>
<!--底部信息-->
<%@ include file="/foot.jsp"%>
<script type="text/javascript">
$j(document).ready(function(){
	$j("#mainMenu_div ul").children("li").eq(0).children("a").trigger("click");
});
</script>
</body>

</html>