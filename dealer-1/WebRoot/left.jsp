<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<s:set name="menuid" value="%{menuId}" ></s:set>
	<s:if test="%{session.subMenu[ #menuid ] == null || session.subMenu[ #menuid ].size == 0}" >
		
	</s:if>
	<s:else>
		<ul class="siBody">
	    	<s:iterator  var="sub" value="%{session.subMenu[ #menuid ]}" status="ss">
	    		<li>
	    			<a href="javascript:void(0)" name="${sub.id}" class="funItem" onclick="home_leftOpen('${sub.url}',this)">
						${sub.name}
					</a>
	    		</li>
	    	</s:iterator>
		</ul>
	</s:else>
