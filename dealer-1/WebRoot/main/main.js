/**
 * 用户详情
 * @return
 */
function main_dealerReview_detail(src){
	var target = $j(src).parent().parent().next('tr').children("td");
	justOpenEasyUIWindow(text_user_info,650,550,target);
}

/**
 * 订单详情
 * @return
 */
function main_orderApprove_detail(order_id){
	var url = getURL("/order/orderDetail.do");
//	var target = $j("#sub_Content");
	var data = {
			'orderMain.id':order_id,
			'isHome':"1"
	};
	openNoColsedEasyUIWindow(url,data,text_main_orderDetail,800,550,"",$j("#main_backDiv"));
}


function main_dealerReview_change_status(userId,status,textV){
	if(status == 0 || status == '0'){
		status = '0';
	}
	if(IsSpace(userId) || IsSpace(status)){
		$j.messager.alert(text_common_warning,text_user_info_lost);
		return false;
	}
	var tempD = $j("#common_backDiv textarea[name='userUnit_reason']");
	
	var reason = $j.trim(tempD.val());
	if(status == '0'){
		if(IsSpace(reason)){
			$j.messager.alert(text_common_warning,text_please_fill_reason+"!");
			tempD.focus();
			return false;
		} else if(reason.length > 100){
			$j.messager.alert(text_common_warning,text_reason_100+"!");
			tempD.focus();
			return false;
		}
	}
	$j.messager.confirm(text_please_confirm, textV, function(r){
		if(r){
			var url = getURL("/user/saveUser.do");
			var data =  {
					'userCondition.userId':userId,
					'userCondition.status':status,
					'userCondition.reason':reason
			};
			$j.ajax({ 
				url: url,
				data:data,
				beforeSend:function(xhr){
					$j.messager.progress({
						title:text_waitting,
						msg:text_saving
					});
				},
				success: function(dataObj){
					$j.messager.progress('close');
					eval("var json = " + dataObj);
					if(json.flag){
						$j.messager.alert("",text_save_ok+"!");
						closeEasyUIWindow();
						loadPage($j("#main_dealerReview"),getURL("/main/dealerReview.do"),{},function(_d){});
						
					}else{
						$j.messager.alert(text_save_false,json.msg);
					}
		        },
		        error:function(xhr,status,exception){
		        	$j.messager.progress('close');
					$j.messager.alert("",xhr.responseText);
				}
		});
		}
	});
}

/**
 * 查看更多待审批代理商
 * @return
 */
function main_dealerReview_more(){
	$j.ajaxSetup({ async : false });
	$j("#mainMenu_div a[name='6']").trigger("click");
	$j.ajaxSetup({ async : true });
	$j("#sub_category a[name='4']").trigger("click");
}

/**
 * 查看更多发货订单 
 * @return
 */
function main_orderApprove_more(){
	$j.ajaxSetup({ async : false });
	$j("#mainMenu_div a[name='2']").trigger("click");
	$j.ajaxSetup({ async : true });
	$j("#sub_category a[name='3']").trigger("click");
}

function main_getTopDealerChart_old(){
	var chart_topDealer = new Highcharts.Chart({
		   global: {
			useUTC: false
			},
	       chart: {
	           renderTo: 'topDealers',
	           type: 'column',
			   width: 380,
			   height: 180
	       },
	       title: {
	           text: 'aaa',
	           x: -20 //center
	       },
	       subtitle: {
	           text: '',
	           x: -20
	       },
	       xAxis: {
	           categories: [
	               'Charles',
	               'Barton',
	               'Alerander'
	           ]
	       },
	       yAxis: {
	           min: 0,
	           title: {
	               text: ''
	           }
	       },
	       legend: {
	           layout: 'vertical',
	           backgroundColor: '#FFFFFF',
	           align: 'left',
	           verticalAlign: 'top',
	           x: 30,
	           y: 0,
	           floating: true,
	           shadow: true
	       },
	       tooltip: {
	           formatter: function() {
	    	   	   var tipStr = "";
//	    	   	   if(this.series.name.indexOf("Revenue") != -1){
//	    	   		tipStr = "€";
//	    	   	   }
	    	   	   if(this.y == 48 ||this.y == '48' ){
	    	   		tipStr = " <strong style='color:green;'>↓</strong>";
	    	   	   }else if(this.series.name.indexOf("YTD") == -1){
	    	   		tipStr = " <strong style='color:red;'>↑</strong>";
	    	   	   }
	               return  '<b>'+ this.series.name +'</b><br/>'+
	                   this.x +': '+ this.y +tipStr;
	           }
	       },
	       plotOptions: {
	           column: {
	               pointPadding: 0.2,
	               borderWidth: 0
	           }
	       },
	           series: [
//	                    {
//	           name: 'Sales Revenue (YTD)',
//	           data: [71.5, 106.4, 194.1]
	//
//	       },
	       {
	           name: 'Num of New Customers (YTD)',
	           data: [384, 472, 720]

	       }, 
//	       {
//	           name: 'Sales Revenue(Last Month)',
//	           data: [48.9, 38.8, 83.5]
	//
//	       },
	       {
	           name: 'Num of New Customers',
	           data: [48, 59, 90]

	       }],
	       credits : {
				enabled: false,
				href : '#',
				text : '',
				style: {
					color: '#666'
				}
			}
	   });
}

function main_getAreaProfilesChart_old(){
	var chart_areaProfiles = new Highcharts.Chart({
	       chart: {
	           renderTo: 'areaProfiles',
	           type: 'line',
			   width: 380,
			   height: 180
	       },
	       title: {
	           text: '',
	           x: -20 //center
	       },
	       subtitle: {
	           text: '',
	           x: -20
	       },
	       xAxis: {
	           categories: ['Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul']
	       },
	       yAxis: {
	           title: {
	               text: 'Num of New Customers'
	           },
	           plotLines: [{
	               value: 0,
	               width: 1,
	               color: '#808080'
	           }]
	       },
	       tooltip: {
	           formatter: function() {
	                   return '<b>'+ this.series.name +'</b><br/>'+
	                   this.x +': '+ this.y;
	           }
	       },
	       legend: {
	           verticalAlign: 'bottom',
	           x: 0,
	           y: -10,
	           borderWidth: 0
	       },
	       series: [{
	           name: 'London',
	           data: [100, 200, 230, 400, 900, 500]
	       }, {
	           name: 'Manchester',
	           data: [324, 624, 700, 300, 700, 600]
	       }, {
	           name: 'Liverpool',
	           data: [99, 400, 69, 900, 450, 309]
	       }],
	       credits : {
				enabled: false,
				href : '#',
				text : '',
				style: {
					color: '#666'
				}
			}
	   });
}

function main_getTopDealerChart_new(result){
	eval("var result_json = " + result);
	var flag = result_json.flag;
	if(flag == 'false'){
		showChartError($j("#topDealers"));
		return;
	}
	chartTopDealer("topDealers", result_json.xCategories, result_json.series, "", "", 445, 200);
}


function goRefreshHomeInventoryOrder(){
	var data ={};
	var url = getURL("/main/listInventoryOrder.do");
	var target = $j("#home_inventory_order_div");
	loadPage(target,url,data,function(_d){});
}

function main_getAreaProfilesChart_new(result){
	eval("var result_json = " + result);
	var flag = result_json.flag;
	
	if(flag == 'false' || flag == false){
		showChartError($j("#areaProfiles"));
		return;
	}
	
	chart("areaProfiles", result_json.xCategories, result_json.series, "", "e.g. " + text_money_pre + " revenue, k=1000", 445, 200,true);
}

function main_getTypeDealersChart(result){
	eval("var result_json = " + result);
	var flag = result_json.flag;
	
	if(flag == 'false' || flag == false){
		showChartError($j("#main_dealerReview"));
		return;
	}
	
	chart("main_dealerReview", result_json.xCategories, result_json.series, result_json.title, "", 445, 300,false);
}

function main_orderPieChart(result){
	eval("var result_json = " + result);
	var flag = result_json.flag;

	if(flag == 'false' || flag == false){
		showChartError($j("#home_inventory_order_div"));
		return;
	}
	
	pieOrderChart("home_inventory_order_div", result_json.xCategories, result_json.data, "", 445, 300);
}

function main_bestPackage(result){
	eval("var result_json = " + result);
	var flag = result_json.flag;
	if(flag == 'false' || flag == false){
		showChartError($j("#bestSellingP"));
		return;
	}
	
	chart("bestSellingP", result_json.xCategories, result_json.series, "","", 910, 300,false);
}

/**
 * 改变省份而发生的报表变化 
 * @param src
 * @return
 */
function main_changeState(src)
{
	var selectedVal = $j(src).val();
	var url = getURL("/main/typeDealersChart.do");
	var data = {
			'stateCode':selectedVal
	}
	$j.ajax({ 
		url: url,
		data:data,
		success: function(dataObj){
			main_getTypeDealersChart(dataObj);
        },
        error:function(xhr,status,exception){
			$j.messager.alert("",xhr.responseText);
		}
	});


	url = getURL("/main/orderPieChart.do");
	$j.ajax({ 
		url: url,
		data:data,
		success: function(dataObj){
			main_orderPieChart(dataObj);
        },
        error:function(xhr,status,exception){
			$j.messager.alert("",xhr.responseText);
		}
	});
	
	url = getURL("/main/bestPackageChart.do");
	$j.ajax({ 
		url: url,
		data:data,
		success: function(dataObj){
			main_bestPackage(dataObj);
        },
        error:function(xhr,status,exception){
			$j.messager.alert("",xhr.responseText);
		}
	});
}

$j(document).ready(function() {
//	main_getTopDealerChart_old();
	var url = getURL("/main/topDealerChart.do");
	$j.ajax({ 
		url: url,
		success: function(dataObj){
			main_getTopDealerChart_new(dataObj);
        },
        error:function(xhr,status,exception){
			$j.messager.alert("",xhr.responseText);
		}
	});
	
//	main_getAreaProfilesChart_old();
	url = getURL("/main/areaProfilesChart.do");
	$j.ajax({ 
		url: url,
		success: function(dataObj){
			main_getAreaProfilesChart_new(dataObj);
        },
        error:function(xhr,status,exception){
			$j.messager.alert("",xhr.responseText);
		}
	});
	
	url = getURL("/main/typeDealersChart.do");
	$j.ajax({ 
		url: url,
		success: function(dataObj){
			main_getTypeDealersChart(dataObj);
        },
        error:function(xhr,status,exception){
			$j.messager.alert("",xhr.responseText);
		}
	});


	url = getURL("/main/orderPieChart.do");
	$j.ajax({ 
		url: url,
		success: function(dataObj){
			main_orderPieChart(dataObj);
        },
        error:function(xhr,status,exception){
			$j.messager.alert("",xhr.responseText);
		}
	});
	
	url = getURL("/main/bestPackageChart.do");
	$j.ajax({ 
		url: url,
		success: function(dataObj){
			main_bestPackage(dataObj);
        },
        error:function(xhr,status,exception){
			$j.messager.alert("",xhr.responseText);
		}
	});
	
	
});


