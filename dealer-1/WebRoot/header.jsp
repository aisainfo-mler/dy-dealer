<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <head>
    <base href="<%=basePath%>" />
    <title><s:text name="index.tiltle" /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta content="IE=EmulateIE7" http-equiv="X-UA-Compatible" />
	<meta http-equiv='Expires' content='0'/> 
	<meta http-equiv='Pragma' content='No-cache'/> 
	<meta http-equiv='Cache-Control' content='No-cache'/>
	<meta name="description" content="DEALER MOBILITY"/>
  	<meta name="keywords" content="DEALER MOBILITY"/>
  	
	<script type="text/javascript" defer="defer" src="<%=request.getContextPath() %>/script/My97DatePicker/WdatePicker.js"></script>
	<link rel="stylesheet" href="<s:url value='/css/global.css'/>" type="text/css" />
  	<link rel="stylesheet" type="text/css" href="<s:url value='/script/easyui/themes/gray/easyui.css'/>" />
	<link rel="stylesheet" type="text/css" href="<s:url value='/script/easyui/themes/icon.css'/>" />
<%-- jquery 	--%>
	<script type="text/javascript" src="<s:url value='/script/jquery-1.7.1.min.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/script/highcharts/highcharts.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/script/easyui/jquery.easyui.min.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/script/easyui/locale/easyui-lang-en.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/script/jquery.validate.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/script/jquery.metadata.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/script/jquery.form.latest.js'/>"></script>
<%--	<script type="text/javascript" src="<s:url value='/ckeditor/ckeditor.js'/>"></script>--%>
	<script type="text/javascript" src="<s:url value='/script/common.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/script/page.js'/>"></script>
<%--	<script type="text/javascript" src="<s:url value='/dwr/engine.js' />" ></script>--%>
	<script type="text/javascript" src="<s:url value='/script/constant.js'/>"></script>
	<script type="text/javascript" src="<s:url forceAddSchemeHostAndPort="true" value='/script/mapp.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/script/jquery-validation/messages_cn.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/home.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/script/jquery.history.js'/>" ></script> 
	<script type="text/javascript">
		var $j = jQuery.noConflict();
		
		var _basePath = "<%=request.getContextPath()%>";
		var _lang = "<%=session.getAttribute("WW_TRANS_I18N_LOCALE")%>";
		_lang = $j.trim(_lang);
		if(_lang==undefined || _lang=="" || _lang==null){
			_lang = "en_US";
<%--			<%session.setAttribute("WW_TRANS_I18N_LOCALE","en_US");%>--%>
		}
		
		$j.ajaxSetup({
			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
			error:function(XMLHttpRequest, textStatus, errorThrown)
			{	
				alert("failure,reason："+XMLHttpRequest.responseText);
			},
			cache:false
		});
		$j.extend($j.messager.defaults, {ok: "Ok",cancel:"Close"});
		 
	</script>
	<script type="text/javascript" src="<s:url value='/script/jquery-validation/methods_cn.js'/>"></script>
<s:if test="%{session.WW_TRANS_I18N_LOCALE.toString() == 'zh_CN'}" >
	<script type="text/javascript" src="<s:url value='/script/text_cn.js'/>" ></script>
</s:if>
<s:else>
	<script type="text/javascript" src="<s:url value='/script/text_en.js'/>" ></script>
</s:else>
  </head>
