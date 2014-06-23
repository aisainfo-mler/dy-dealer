<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!--头部-->
<div class="header">
  	<div class="logo"><s:property value="getText('index.tiltle')" /></div>
  	<div class="loginInfo">
	    <ul>
	      <li class="welcome"><s:text name="welcome.msg" />,&nbsp;<a href="javascript:void(0);">${session.loginUser.lastName}&nbsp;${session.loginUser.firstName}</a> ｜</li>
	      <li class="exit"><a href="javascript:void(0);" onclick="closeAlert()"><s:text name="home.logout" /></a></li>
	    </ul>
  	</div>
  	<!--导航-->
	<div class="nav" name="mainMenu_div" id="mainMenu_div">
	  <ul>
	    <s:iterator  var="mainMenu" value="%{session.mainMenu}" status="rowstatus">
			<li>
				<s:set name="menuIsWhole" value="0"></s:set>
				<s:if test="%{#mainMenu.isWhole != null && #mainMenu.isWhole != '' }" >
         			<s:set name="menuIsWhole" value="%{#mainMenu.isWhole}"></s:set>
				</s:if>
				<a href="javascript:void(0)" name="${mainMenu.id}" onclick="home_changeMenu('${rowstatus.index}',this,'${mainMenu.url}',${mainMenu.id},'<s:property value="#menuIsWhole"/>')">${mainMenu.name}</a>
			</li>
		</s:iterator>
	  </ul>
	</div>
  	
  	<input type="text" id="tempFocus" style="display: none; width: 1px;"/>
</div>