<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="sideBar" style="width:465px">
	<!--Top Dealer-->
	<div class="uitopb uitopb-border">
		<ul class="tabbar">
			<li class="tabCurrent">
				<a href="javascript:void(0)">Top Dealers</a>
			</li>
		</ul>
		<div class="app_con" id="topDealers">
			<!-- %@ include file="/main/topDealer.jsp"%-->
		</div>
	</div>

	<!--Top Sale-->
<!-- 	<div class="uitopb uitopb-border"> -->
<!-- 		<ul class="tabbar"> -->
<!-- 			<li class="tabCurrent"> -->
<!-- 				<a href="javascript:void(0)">Top Sale</a> -->
<!-- 			</li> -->
<!-- 		</ul> -->
<!-- 		<div class="tabledv" style="display: block; "> -->
<!-- 			<%@ include file="/main/topSale.jsp"%> -->
<!-- 		</div> -->
<!-- 		<br/> -->
<!-- 	</div> -->
</div>
<div class="mainCon" style="width:465px">
	<!--Area Profiles-->
	<div class="uitopb uitopb-border">
		<ul class="tabbar">
			<li class="tabCurrent">
				<a href="javascript:void(0)">Regional Sales Performance</a>
			</li>
		</ul>
		<div class="app_con" id="areaProfiles">
			<!--%@ include file="/main/areaProfiles.jsp"%-->
		</div>
	</div>
</div>

<div class="clear"></div>

<!--Dealer Review-->
<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent">
			<a href="javascript:void(0)">
				Region
<%--				<select>--%>
<%--					<option >all</option>--%>
<%--				</select>--%>
				<s:select onchange="main_changeState(this)" list="%{states}" listKey="stateCode"  listValue="stateName" theme="simple" headerKey="" headerValue="all"></s:select>
				
			</a>
		</li>
	</ul>
	<div class="tab_handle">
<%--		<a href="javascript:void(0)" onclick="main_dealerReview_more()">More</a>--%>
	</div>
	<div class="app_con" style="display: block; ">
		<div style="float:left;width:455px;">
			<h3 style="text-align:center;">Deal Approvals</h3><br/>
			<div id="main_dealerReview"></div>
			
	<%--			<%@ include file="/main/dealerReview.jsp"%>--%>
		</div>
		<div style="float:right;width:455px;">
			<h3 style="text-align:center;">Orders This Month</h3><br/>
			<div id="home_inventory_order_div"></div>
	<%--			<%@ include file="/main/orderApprove.jsp"%>--%>
		</div>
		<div class="clear"></div>
		<br/>
		<div style="height:340px;width:930px;padding-top:10px;">
			<h3 style="text-align:center;">Best Selling Packages</h3><br/>
			<div id="bestSellingP"></div>
			<!-- %@ include file="/main/topDealer.jsp"%-->
		</div>
		<div class="clear"></div>
	</div>
</div>

<!--Order Approve
<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent">
			<a href="javascript:void(0)">Inventory Order Approval</a>
		</li>
	</ul>
	<div class="tab_handle">
		<a href="javascript:void(0)" onclick="main_orderApprove_more()">More</a>
	</div>
</div>
<div class="uitopb uitopb-border">
	<ul class="tabbar">
		<li class="tabCurrent">
			<a href="javascript:void(0)">Best Selling Package</a>
		</li>
	</ul>
</div>-->

<div id="main_backDiv" class="easyui-window" closed="true" ></div>
<script type="text/javascript" src="<s:url value='/main/main.js'/>"></script>
<script type="text/javascript" src="<s:url value='/main/chart.js'/>"></script>
