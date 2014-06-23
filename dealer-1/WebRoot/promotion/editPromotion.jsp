<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent">
			<a href="javascript:void(0)">
				<s:text name="promotion.detail" />
			</a>
		</li>
	</ul>
</div>
	<div class="order_handle">
		<input type="button" value="<< &nbsp;<s:text name="order.return" />" class="d_button4" onclick="promotion_return()"/>
	</div>
	<s:form id="promotion_detail_form" action="/promotion/save.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="promotion.id" value="${promotion.id}" />
		<table class="detailTable" border="0" cellspacing="0" cellpadding="5">
		    <tr>
		        <td class="Hint"><s:text name="promotion.effDate" />:&nbsp;</td>
		        <td>
		        	<input type="text" name="promotion.tmpStartTime" maxlength="20" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="Wdate d_input"  value="<s:date name="promotion.effDate" format="yyyy-MM-dd"/>"/>
		        </td>
		       	<td class="Hint"><s:text name="promotion.expDate" />:&nbsp;</td>
		        <td><input type="text" name="promotion.tmpEndTime" maxlength="20" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="Wdate d_input" value="<s:date name="promotion.expDate" format="yyyy-MM-dd"/>"/></td>
		    </tr>
		    
		    <tr>
		        <td class="Hint"><s:text name="user.status" />:&nbsp;</td>
				<td>
<%--					<s:select cssClass="d_input" name="promotion.status" list="#{'0':'无效','1':'有效'}" listKey="key"  listValue="value"></s:select>--%>
					<select id="promotion.status" class="d_input" name="promotion.status">
						<option value="">&nbsp;</option>
						<option value="1" 
							<s:if test="%{promotion.status!=0}">selected</s:if>
						><s:text name="common.valid" /></option>
						<option value="0"
							<s:if test="%{promotion.status==0}">selected</s:if>
						><s:text name="common.inValid" /></option>
					</select>
				</td>
				<td class="Hint"><s:text name="promotion.type" />:&nbsp;</td>
				<td>
					<select name="promotion.type" class="d_input" >
						<option value="1" <s:if test="%{promotion.type!=2 && promotion.type!=3 && promotion.type!=4}">selected</s:if> >
							<s:text name="promotion.type.promotion" />
						</option>
						<option value="2" <s:if test="%{promotion.type==2}">selected</s:if> >
							<s:text name="promotion.type.home" />
						</option>
						<option value="3" <s:if test="%{promotion.type==3}">selected</s:if> >
							<s:text name="promotion.type.newUser" />
						</option>
						<option value="4" <s:if test="%{promotion.type==4}">selected</s:if> >
							<s:text name="promotion.type.topUp" />
						</option>
					</select>
				</td>
		    </tr>
		    <tr>
		        <td class="Hint" valign="top" ><s:text name="form.text.remark" />:&nbsp;</td>
		        <td colspan="3">
<%--		        <s:textfield name="promotion.remark" id="promotion.remark"></s:textfield>--%>
		        	<input type="text"  size="100" class="d_input {required:true,messages:{required:'<s:text name="promotion.inputRemark" />'},maxlength:50}"  size="35" name="promotion.remark" value="${promotion.remark}" />
		        </td>
		    </tr>
		    <tr>
		    	<td class="Hint" valign="top" ><s:text name="promotion.imgIndex" />:&nbsp;</td>
		        <td colspan="3">
<%--		        <s:textfield name="promotion.remark" id="promotion.remark"></s:textfield>--%>
		        	<input type="text"  size="2" class="d_input {number:true,maxlength:10}" name="promotion.fileIndex" value="${promotion.fileIndex}" />
		        </td>
		     </tr>
		 </table>
		 <!--  -->
		 <table class="listTable" border="0" cellspacing="0" cellpadding="5">
		     
		      <tr>
		     	 <th class="Hint" valign="top" ><s:text name="promotion.img.thumbnail" /></th>
		     	 <th class="Hint" valign="top" ><s:text name="promotion.img.iphone4" /></th>
		     	 <th class="Hint" valign="top" ><s:text name="promotion.img.iphone5" /></th>
		     </tr>
		     <tr>
		     	<td>
		     	 	<s:if test="%{promotion.fileId != null}">
						<img src="appImage.so?slfid=${promotion.fileId}" name="appImg_oo"  width="160"></img>
					</s:if>
		     	 </td>
		     	 <td>
		     	 	<s:if test="%{promotion.file4Id != null}">
						<img src="appImage.so?slfid=${promotion.file4Id}" name="appImg_oo" width="160"></img>
					</s:if>
		     	 </td>
		     	 <td>
		     	 	<s:if test="%{promotion.file5Id != null}">
						<img src="appImage.so?slfid=${promotion.file5Id}" name="appImg_oo"  width="160"></img>
					</s:if>
		     	 </td>
		     </tr>
		     <tr>
		     	 <td class="Hint C" valign="top" ><input type="file" name="imgFile"/></td>
		     	 <td class="Hint C" valign="top" ><input type="file" name="img4File"/></td>
		     	 <td class="Hint C" valign="top" ><input type="file" name="img5File"/></td>
		     </tr>
		</table>
	  </s:form>
	  <br/>
	<div class="C">
		<input type="button" value="<s:text name="common.save" />" class="d_button4" onclick="promotion_save()"/>
	</div>
	<script type="text/javascript" src="<s:url value="/promotion/promotion.js"/>"></script>
	<br/>
