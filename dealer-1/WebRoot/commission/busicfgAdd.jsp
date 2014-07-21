<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent"><a href="javascript:void(0)"> <s:text
					name="aiBusi.add" /> </a></li>
	</ul>
</div>
<div class="order_handle">
	<input type="button" value="<< &nbsp;<s:text name="aiBusi.return" />"
		class="d_button4" onclick="busicfg_return()" />
</div>
<s:form id="busicfg_save_check_form" action="/busicfg/busicfgAdd.do"
	method="post" enctype="multipart/form-data">

	<table class="detailTable" border="0" cellspacing="0" cellpadding="5">
		<tr>
			<td class="Hint">指标编码：</td>
			<td><input name="busi.subSystemId" id="subSystemId" value="1"
				type="hidden" /> <input name="busi.busiCode" notnull="true"
				id="busiCode" value="<s:property value="busi.busiCode"/>"
				type="text" size="20" onblur="checkBusiCode( this, 'subSystemId')" />
				<span style="color:red;">&nbsp; *</span><span id="UId"></span>
			</td>
		</tr>
		<tr>
			<td class="Hint">指标名称：</td>
			<td><input name="busi.remark" id="remark" notnull="true"
				type="text" size="20" value="<s:property value="busi.remark"/>" /><span
				style="color:red;">&nbsp; *</span>
			</td>
		</tr>

		<tr>
			<td class="Hint">数据类型：</td>
			<td><s:select list="map" listKey="key" value=""
					listValue="value" name="busi.dataType"></s:select>
			</td>
		</tr>
	</table>

</s:form>
<br />
<div class="C">
	<input type="button" value="<s:text name="busicfg.save" />"
		class="d_button4" onclick="save()" />
</div>
<script type="text/javascript"
	src="<s:url value="/commission/busicfgRule.js"/>"></script>
<br />
