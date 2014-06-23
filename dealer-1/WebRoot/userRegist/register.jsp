<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="<s:url value='/user/user.js'/>"></script>
<div id="upload_left_div" style="display:block">
	<div class="uitopb uitopb-border">
		<ul class="tabbar">
			<li class="tabCurrent">
				<a href="javascript:void(0)">
					账号信息
				</a>
			</li>
		</ul>
		<div id="version_button" class="tab_handle" ></div>
	</div>
	<div id="version_info_div"  class="tabledv" >
		<input type="hidden" id="user_userId" value="<s:property value="user.userId"/>" />
		<table class="detailTableBorder" cellspacing="0" cellpadding="0" >
			<tr>
				<td class="Hint" style="width:150px"><span class="required">*</span>帐号：</td>
				<td colspan="3"><s:textfield name="user.userCode" /> </td>
			</tr>
			<tr>
				<td class="Hint"><span class="required">*</span>密码：</td>
				<td colspan="3"><s:password name="user.password" /></td>
			</tr>
			<tr>
				<td class="Hint"><span class="required">*</span>确认密码：</td>
				<td colspan="3"><s:password name="user.password1" /></td>
			</tr>
			<tr>
				<td class="Hint"><span class="required">*</span>email： </td>
				<td><s:property value="user.email"/></td>
				<td class="Hint"><span class="required">*</span>手机号码：</td>
				<td><s:property value="user.mobileNo"/></td>
			</tr>
		</table>
	</div>
	
	<br/>
	<div id="file_list_div" style="display: none;">
		<div class="uitopb uitopb-border">
			<ul class="tabbar">
				<li class="tabCurrent">
					<a href="javascript:void(0)">
						详细信息
					</a>
				</li>
			</ul>
			<div class="tab_handle" ><a class="add" href="javascript:void(0)" onclick="addFile('02');return false;" title="上传应用文件">上传应用文件</a></div>
		</div>
		
		<div id="version_attache_div" class="tabledv">
			<table class="detailTableBorder" cellspacing="0" cellpadding="0" >
				<tr>
					<td class="Hint" style="width:150px"><span class="required">*</span>FirstName：</td>
					<td colspan="3"><s:textfield name="user.firstName" /> </td>
				</tr>
				<tr>
					<td class="Hint"><span class="required">*</span>LastName：</td>
					<td colspan="3"><s:password name="user.lastName" /></td>
				</tr>
				<tr>
					<td class="Hint"><span class="required">*</span>性别：</td>
					<td><s:password name="user.gender" /></td>
					<td class="Hint"><span class="required">*</span>生日： </td>
					<td><s:textfield value="user.birthDay"/></td>
				</tr>
				<tr>
					<td class="Hint"><span class="required">*</span>证件类型：</td>
					<td><s:textfield value="user.certificateType"/></td>
					<td class="Hint"><span class="required">*</span>证件号：</td>
					<td><s:textfield value="user.certificateNo"/></td>
				</tr>
				<tr>
					<td class="Hint"><span class="required">*</span>Email：</td>
					<td><s:textfield value="user.email"/></td>
					<td class="Hint"><span class="required">*</span>手机号码：</td>
					<td><s:textfield value="user.mobileNo"/></td>
				</tr>
				<tr>
					<td class="Hint"><span class="required">*</span>地址：</td>
					<td colspan="3"><s:textfield value="user.address"/></td>
				</tr>
				<tr>
					<td class="Hint"><span class="required">*</span>邮编：</td>
					<td><s:property value="user.postCode"/></td>
					<td class="Hint"><span class="required">*</span>联系电话：</td>
					<td><s:property value="user.contractPhone"/></td>
				</tr>
 				<tr style="display: none;">
					<td class="Hint"><span class="required">*</span>城市：</td>
					<td><s:property value="user.city"/></td>
					<td class="Hint"><span class="required">*</span>街道：</td>
					<td><s:property value="user.street"/></td>
				</tr>
				<tr style="display: none;">
					<td class="Hint"><span class="required">*</span>楼名：</td>
					<td><s:property value="user.building"/></td>
					<td class="Hint"><span class="required">*</span>房号：</td>
					<td><s:property value="user.houseNumber"/></td>
				</tr>
				
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
<!--

	if(IsSpace($j("#version_id").val())){
		versionInfoForm();
	}else{
		versionInfo($j("#version_id").val());
		$j("#file_list_div").show();
	}
//-->
</script>



