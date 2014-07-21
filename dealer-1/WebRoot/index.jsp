<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:text name="index.tiltle" />
</title>
<meta content="IE=EmulateIE7" http-equiv="X-UA-Compatible" />
<meta http-equiv='Expires' content='0' />
<meta http-equiv='Pragma' content='No-cache' />
<meta http-equiv='Cache-Control' content='No-cache' />
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
<link rel="stylesheet" href="<s:url value='/css/global.css'/>"
	type="text/css" />
<script type="text/javascript"
	src="<s:url value='/script/jquery-1.7.1.min.js'/>"></script>
<script type="text/javascript"
	src="<s:url value='/script/jquery-ui.min.js'/>"></script>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
%>
<script type="text/javascript">
	var $j = jQuery.noConflict();
	var baseUrl = "<%=request.getContextPath()%>";
	var currentUrl = "<%=basePath + request.getRequestURI()%>";
	var _lang = "<%=session.getAttribute("WW_TRANS_I18N_LOCALE")%>";
	_lang = $j.trim(_lang);
	if (_lang == undefined || _lang == "" || _lang == null) {
		_lang = "en_US";
	}
</script>

</head>

<body>

	<s:set name="indexLang" value="%{lang}"></s:set>
	<s:if test="%{session.lang == 'zh_CN' || session.lang == 'en_US'}">
		<s:set name="indexLang" value="%{session.lang}"></s:set>
	</s:if>
	<!--头部-->
	<div class="header">
		<div class="logo">
			<s:property value="getText('index.tiltle')" />
		</div>
		<div class="language">
			<s:if test="%{#indexLang != null && #indexLang == 'zh_CN'}">
			语言：<a target="_self" href="<s:url value='/lang/en.do' />">English</a>
			</s:if>
			<s:if
				test="%{#indexLang == null || #indexLang == '' || #indexLang == 'en_US'}">
			Language：<a target="_self" href="<s:url value='/lang/cn.do' />">中文</a>
			</s:if>
		</div>
	</div>
	<div class="mainBody">
		<div class="loginAd">
			<img src="<s:url value='/images/login_ad.jpg'/>" />
		</div>
		<div class="loginBox">
			<h1>
				<s:property value="getText('index.userLogin')" />
			</h1>
			<form id="login"
				action="<s:url value='/common/check.do?request_locale=zh_CN' />"
				method="post">
				<div class="loginCon">
					<ul>
						<li class="user"><input id="username" name="username"
							type="text"
							placeholder="<s:property value="getText('index.inputUserName')" />"
							onfocus="this.style.color='#000';this.value=''" autofocus
							required /></li>
						<li class="password"><input id="password" name="password"
							type="password" value=""
							placeholder="<s:property value="getText('index.inputPassword')" />"
							onfocus="this.style.color='#000';this.value=''" required /></li>
						<li class="checkcode"><input id="checkCode" name="checkCode"
							type="text" value=""
							placeholder="<s:property value="getText('index.inputCheckCode')" />"
							onfocus="this.style.color='#000';this.value=''" required /> <img
							style="margin-right:2px;" height="35" src="Kaptcha.jpg"
							id="code"
							onclick="this.src='Kaptcha.jpg?now=' + new Date().getTime()"
							alt="看不清，点击换一张" /></li>

					</ul>
					<div class="loginBtn" style="margin: 30px 0 0;">
						<input type="button" id="submit1" onclick="checkLogin(1);"
							value="<s:property value="getText('index.login')" />" />
						<div class="loadingBig"
							style="margin: 10px 0; background-position: 25% 0;color:#000;display:none">
							<s:property value="getText('index.logining')" />
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--底部信息-->
	<div class="footer">
		<div class="footerCon">
			<div class="supportSevice">
				<h3>
					<s:property value="getText('foot.support')" />
				</h3>
				<ul>
					<li><s:property value="getText('foot.askBeforeSale')" />：(86)10-82166666</li>
					<li></li>
				</ul>
			</div>

			<div class="copyright">
				<s:text name="asiaCoTitle" />
				<br /> © 2012 AsiaInfo-Linkage Company. All right Reserved.
			</div>

			<div class="certificates">
				<%--			<a href="javascript:void(0)" target="_blank">--%>
				<%--				<img src="http://storage.aliyun.com/aliyun_portal_storage/content8401340010279.gif" width="22" height="26" border="0" />--%>
				<%--			</a>--%>
				<%--			<a href="javascript:void(0)" target="_blank">--%>
				<%--				<img src="http://storage.aliyun.com/aliyun_portal_storage/content7311340010303.gif" width="24" height="28" border="0" />--%>
				<%--			</a>--%>
			</div>
		</div>
	</div>

	<%--<form id="login" action="<s:url value='/common/login.do?request_locale=%{requestLang}' />" method="post" >
    <fieldset id="inputs">
        <input id="username" name="username" type="text" placeholder="Username" autofocus required>   
        <input id="password" name="password" type="password" placeholder="Password" required>
    </fieldset>
    <fieldset id="actions">
        <input type="button" class="submit" id="submit1" value="<s:property value="getText('index.login')" />" onclick="checkLogin(1)" />&nbsp;&nbsp;&nbsp;
        <input type="button" class="submit" value="<s:property value="getText('index.crmLogin')" />" onclick="checkLogin(2)" style="margin:0px 0 0 25px;"/>
        <a href="<s:url value='/lang/lang.do?requestLang=zh_CN' />" target="_self"><s:text name="index.chinese" /></a>&nbsp;<a target="_self" href="<s:url value='/lang/lang.do?requestLang=en_US' />"><s:text name="index.english" /></a>
    </fieldset>
</form>
--%>
	<script type="text/javascript"
		src="<s:url value='/script/text_en.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/index.js'/>"></script>
</body>
</html>
