<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent"><a href="javascript:void(0)"> <s:text
					name="Edit" /> </a>
		</li>
	</ul>
</div>
<div class="order_handle">
	<input type="button" value="<< &nbsp;<s:text name="return" />"
		class="d_button4" onclick="rulecfg_return()" />
</div>
<s:form id="rulecfg_update_check_form" action="/rulecfg/rulecfgEdit.do"
	method="post" enctype="multipart/form-data">

	<table class="detailTable" border="0" cellspacing="0" cellpadding="5">
		<tr>
			<td class="Hint">规则名称：</td>
			<td><input name="abc.subSystemId" id="subSystemId" value="1"
				type="hidden" /> <input type="hidden" name="abc.ruleId"
				value="<s:property value="abc.ruleId"/>" /> <input
				name="abc.ruleName" notnull="true" id="ruleName"
				value="<s:property value="abc.ruleName"/>" type="text" size="17" />
				<span style="color:red;">&nbsp; *</span></td>
			<td class="Hint">模型类型：</td>
			<td><s:select list="map" listKey="key" value=""
					listValue="value"
					name="abc.modType"></s:select> <span style="color:red;">&nbsp;
					*</span>
			</td>
		</tr>
		<tr>
			<td class="Hint">指标名称：</td>
			<td><input name="busi.remark" notnull="true" type="text"
				size="17" value="<s:property value="busi.remark"/>" /></td>
			<td colspan="2">
				<button type="button" class="searchBtn"
					onclick="rulecfg_Add_search();">
					<s:text name="common.search" />
				</button></td>
		</tr>
	</table>
	<table class="detailTableBorder" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<th>
				<center>可选指标</center>
			</th>
		</tr>
		<tr>
			<td>
				<div id="rulecfgName_list"
					style="height: 200px;overflow-y:auto;width:100%"></div></td>
		</tr>
	</table>
	<div style="height: 200px;overflow-y:auto;width:100%">
		<table class="listTable" border="0" cellspacing="0" cellpadding="0">
			<thead>
				<tr>
					<th>指标</th>
					<th>操作符</th>
					<th>值</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="ruleBody">
			</tbody>
		</table>
	</div>
</s:form>
<br />
<div class="C">
	<input type="button" value="<s:text name="save" />" class="d_button4"
		onclick="rulecfg_edit()" />
</div>
<script type="text/javascript"
	src="<s:url value="/commission/rulecfgRule.js"/>"></script>
<br />
