<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent"><a href="javascript:void(0)"> <s:text
					name="modadd" /> </a></li>
	</ul>
</div>
<div class="order_handle">
	<input type="button" value="<< &nbsp;<s:text name="aiBusi.return" />"
		class="d_button4" onclick="busicfg_return()" />
</div>
<s:form id="modcfg_save_check_form" action="/modcfg/modcfgAdd.do"
	method="post" enctype="multipart/form-data">
	<input type="hidden" name="subSystemId" id="subSystemId" value="1" />
	<table class="detailTableBorder" cellspacing="0" cellpadding="0"
		border="0">

		<tr>
			<td class="Hint">模型名称：</td>
			<td><input type="text" size="24" notnull="true" name="modName"
				id="modName" maxlength="100"
				value="<s:property value="mod.modName"/>" /><span
				style="color:red;">&nbsp; *</span>
			</td>
			<td class="Hint">模型类型：</td>
			<td><s:select list="map" listKey="key" value=""
					listValue="value" name="mod.modType"></s:select> <span
				style="color:red;">&nbsp; *</span></td>
		</tr>
		<tr>
			<td class="Hint">返佣类型名称：</td>
			<td><s:select list="maps" listKey="key" value=""
					listValue="value" name="mod.modType"></s:select> <span
				style="color:red;">&nbsp; *</span>
			</td>
		</tr>
		<tr>
			<td class="Hint">规则名称：</td>
			<td><input type="text" size="24" name="abc.ruleName"
				id="ruleName" value="<s:property value="abc.ruleName"/>"
				maxlength="100" />
			</td>
			<td colspan="2"><button type="button" class="searchBtn"
					onclick="modcfg_Add_search();">
					<s:text name="common.search" />
				</button>
			</td>
		</tr>
	</table>
	<table class="detailTableBorder" border="0" cellspacing="0"
		cellpadding="0">
		<tr>
			<th>
				<center>待选择规则</center>
			</th>
			<th width="10%">&nbsp;&nbsp;</th>
			<th>
				<center>已选择规则</center>
			</th>
		</tr>

		<tbody>
			<tr>
				<td style="height:200px">
					<div id="modcfgName_list" style="overflow-y:auto;height:200px;"></div>
				</td>
				<td><input type="button" class="d_button" value=">>选择"
					onClick="addReceivers(this);"> <input type="hidden"
					name="ruleName" id="ruleName2" value="" /> <input type="hidden"
					name="ruleId" id="ruleId2" value="" /> <br /> <br /> <input
					type="button" class="d_button" value="<<删除"
					onClick="goDeals(this);"> <br /> <br />
				</td>
				<td style="height:200px">
					<div id="selectedIDStr" style="overflow-y:auto;height:200px;"></div>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div id="selectableIDStrValue"></div>
				</td>
			</tr>
		</tbody>
	</table>

</s:form>
<br />
<div class="C">
	<input type="button" value="<s:text name="save" />" class="d_button4"
		onclick="save()" />
</div>
<script type="text/javascript"
	src="<s:url value="/commission/modcfgRule.js"/>"></script>
<br />
