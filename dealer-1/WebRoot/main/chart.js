function chart(renderTo, xCategories, data, title, yTitle, width, height,isMoney){
	var options = {
		global: {
			useUTC: false
		},
		chart: {
			renderTo : renderTo,
			width: width,
			height: height,
			margin: [30, 30, 60, 70],
			shadow: true
		},
		title: {
			text: title,
			x: -20
		},
		subtitle: {
	           text: '',
	           x: -20
	    },
		xAxis: {
			categories: []
		},
		yAxis: {
			title: {
				text: yTitle
			},
//			lineWidth: 1,
			min: 0
		},
		legend: {
//	           layout: 'vertical',
//	           align: 'right',
	           verticalAlign: 'bottom',
//	           x: 0,
	           y: -10,
	           borderWidth: 0
	    },
		tooltip: {
			formatter: function() {
	    		if(isMoney){
	    			return  '<b>'+ this.series.name +'</b><br/>'+
	    			this.x +':' + text_money_pre + this.y ;
	    		}else{
	    			return  '<b>'+ this.series.name +'</b><br/>'+
	    			this.x +':'+ this.y;
	    		}
		    	
			}
		},
		plotOptions: {
	           column: {
	               pointPadding: 0.2,
	               borderWidth: 0
	           }
	    },
		credits : {
			enabled: false,
			href : '#',
			text : '',
			style: {
				color: '#666'
			}
		},
		series:[]
	};
	
	//x轴坐标数据
	for(var i = 0; i < xCategories.length; i++){
		options.xAxis.categories.push(xCategories[i]);
	}

	for(var i = 0; i < data.length; i++){
		options.series.push(data[i]);		
	}

	var chart = new Highcharts.Chart(options);
};


function chartTopDealer(renderTo, xCategories, data, title, yTitle, width, height){
	var options = {
		global: {
			useUTC: false
		},
		chart: {
			renderTo : renderTo,
			width: width,
			height: height,
			margin: [30, 30, 50, 60],
			shadow: true
		},
		title: {
			text: title,
			x: -20
		},
		subtitle: {
	           text: '',
	           x: -20
	    },
		xAxis: {
			categories: []
		},
		yAxis: {
			tickInterval:200,
			title: {
				text: yTitle
			},
//			lineWidth: 1,
			/*
			labels : {  
                formatter : function() {
                 return this.value + '€';  

                }  

            },  
            */
			max: 1000,
			min: 0
		},
		legend: {
//	           layout: 'vertical',
//	           backgroundColor: '#FFFFFF',
//	           align: 'left',
	           verticalAlign: 'bottom',
//	           x: 7,
	           y: 0,
	           floating: true,
	           shadow: true,
	           borderWidth: 0
	    },
	    tooltip: {
			formatter: function() {
		    	var s = "";
		    	var ss = "";
		    	 if (this.point.dataLabels) {
		    		 ss = this.point.dataLabels.formatter;
		    		 if("-" == ss){
//		    			 s = " <strong style='color:yellow;'>-</strong>";
		    			 s = " <b>-</b>";
		    			 
		    		 }else if("↑" == ss){
//		    			 s = " <strong style='color:red;'>↑</strong>";
		    			 s = " <b>↑</b>";
		    		 }else{
//		    			 s = " <strong style='color:green;'>↓</strong>";
		    			 s = " <b>↓</b>";
		    		 }
//		    		 alert(s);
		    	 }
		    	 return '<b>'+ this.series.name +'</b><br/>'+
		            this.x +': '+ this.y + s;
			}
		},
		plotOptions: {
	           column: {
	               pointPadding: 0.2,
	               borderWidth: 0
	           },
	           series: {
	                cursor: 'pointer',
	                point: {
	                    events: {
	                        click: function() {
	        	   				var dataN = this.series.name;
	        	   				var ifThisMonth = "1";
	        	   				if(dataN.indexOf("(YTD)") != -1){
	        	   					ifThisMonth = "0";
	        	   				}
//	                            alert ('Category: '+ this.series.name +', value: '+ this.userId);
	        	   				openTopDealerTable(this.userId,ifThisMonth);
	                        }
	                    }
	                }
	            }
	    },
		credits : {
			enabled: false,
			href : '#',
			text : '',
			style: {
				color: '#666'
			}
		},
		series:[]
	};
	
	//x轴坐标数据
	for(var i = 0; i < xCategories.length; i++){
		options.xAxis.categories.push(xCategories[i]);
	}

	for(var i = 0; i < data.length; i++){
		
//		alert(data[i]);
		options.series.push(data[i]);		
	}

	var chart = new Highcharts.Chart(options);
};


function showChartError(target, html){
//	if(html){
		target.html("<div style=\"border-radius: 5px;height:170px;border: 1px solid #eee;padding:130px 0 0;text-align:center;\"><span style='color: red; font-weight: bold;'>" + text_chart_noData + "!<span></div>");
//	}else{
//		target.html("<div style=\"border-radius: 5px;height:170px;border: 1px solid #eee;padding:130px 0 0;text-align:center;\"><span style='color: red; font-weight: bold;'>No Data!<span></div>");
//	}
}

/**
 * 点击最高发展人 人数信息
 * @param userId
 * @param ifYtd
 * @return
 */
function openTopDealerTable(userId,ifThisMonth){
	if(IsSpace(userId)){
		return false;
	}
	var url = getURL("/mainSimple/topDealerDetailIndex.do");
	var data = {
			'creatorId':parseInt(userId),
			'ifThisMonth':ifThisMonth
	};
	openEasyUIWindow(url,data,text_customer_list,700, 340,"");
}

//piechart
function pieChart(renderTo, data, title, width, height){
	var options = {
		colors: ["#89A54E", "#9982B4", "#ABC1E6", "#5E8BC0", "#aaeeee", "#ff0066", "#eeaaee", 
			 		"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
		chart: {
			renderTo : renderTo,
			width: width,
			height: height,
			shadow: true
		},
		title: {
			text: title,
			style: {
				color: '#3E576F'
			}	
		},
		tooltip: {
			backgroundColor: 'rgba(0, 0, 0, 0.75)',
			style: {
				color: '#F0F0F0'
			},
			formatter: function() {
				return '<b>'+ this.point.name +'</b>: ' + this.y + '%';
			}
		},
		toolbar: {
			itemStyle: { 
				color: 'silver'
			}
		},
		credits : {
			enabled: false
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				size: '65%',
				dataLabels: {
					enabled: true,
					connectorWidth: 1,
					distance: 4,
					formatter: function() {
						if(this.y > 0)return this.point.name;
					},
					style: {
						font: '10px Trebuchet MS, Verdana, sans-serif'
					}
				}
			}
		},	
		legend: {
			enabled: false,
			
			itemStyle: {
				color: '#666'
			},
			itemHoverStyle: {
				color: '#FFF'
			},
			itemHiddenStyle: {
				color: '#444'
			}
		},
		series:[]
	};

	options.series.push(data);
		
	var chart = new Highcharts.Chart(options);
	
};

function pieOrderChart(renderTo, xCategories, data, title, width, height){
	var browserData = [];
    var versionsData = [];
    var colors = Highcharts.getOptions().colors;
    for (var i = 0; i < data.length; i++) {
        data[i].color = colors[i];
        data[i].drilldown.color = colors[i];
        // add browser data
        browserData.push({
            name: xCategories[i],
            y: data[i].y,
            orderNum:data[i].orderNum,
            color: colors[i]
        });

        // add version data
        for (var j = 0; j < data[i].drilldown.data.length; j++) {
            var brightness = 0.2 - (j / data[i].drilldown.data.length) / 5 ;
            versionsData.push({
                name: data[i].drilldown.categories[j],
                y: data[i].drilldown.data[j],
                orderNum:data[i].drilldown.orderNums[j],
                color: Highcharts.Color(data[i].color).brighten(brightness).get()
            });
        }
    }
   var chart = new Highcharts.Chart({
        chart: {
            renderTo: renderTo,
            width: width,
			height: height,
            type: 'pie',
            shadow: true,
            margin: [8, 130, 20, 120]
        },
        title: {
            text: " "
        },
        plotOptions: {
            pie: {
                shadow: false
            }
        },
        tooltip: {
			backgroundColor: 'rgba(0, 0, 0, 0.75)',
			style: {
				color: '#F0F0F0'
			},
			formatter: function() {
//				return '<b>'+ this.point.name +'</b>: ' + this.y + '%';
				return '<b>'+ this.point.name +'</b>: ' + this.point.orderNum ;
			}
		},
        series: [{
            name: 'Browsers',
            data: browserData,
            size: '70%',
            dataLabels: {
                formatter: function() {
                    return this.y > 5 ? this.point.name : null;
                },
                color: 'white',
                distance: -40
            }
        }, {
            name: 'Versions',
            data: versionsData,
            innerSize: '60%',
            dataLabels: {
                formatter: function() {
                    // display only if larger than 1
                    return this.y > 1 ? '<b>'+ this.point.name +':</b> '+ this.y +'%'  : null;
                }
            }
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