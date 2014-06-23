package com.ai.mapp.sys.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.entity.HwCity;
import com.ai.mapp.sys.entity.HwState;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-12-3 下午03:06:42
 * 类说明: 主要用于展现主页上的  highCharts
 */

@Service
@Transactional
public class ChartViewService {
	@Autowired
	private UserService userService;
	@Autowired
	private AgentOrderService agentOrderService;
	@Autowired
	private OrderInfoService orderInfoService;
	
	public String getTopDealers(int start,int limit)throws Exception{
		
		String test1="{\"xCategories\":[\"Paul\",\"Steve\",\"David\",\"Alan\",\"Mike\"]," +
				"		\"series\":[" +
				"			{\"name\":\"No. of New Customers ( YTD )\",\"type\":\"column\",\"data\":[" +
				"				{\"y\":\"700\",\"userId\":\"888\"}," +
				"				{\"y\":\"300\",\"userId\":\"2\"}," +
				"				{\"y\":\"350\",\"userId\":\"2\"}," +
				"				{\"y\":\"600\",\"userId\":\"2\"}," +
				"				{\"y\":\"950\",\"userId\":\"1\"}]}," +
				"			{\"name\":\"No. of New Customers ( MTD )\",\"type\":\"column\",\"data\":[" +
				"				{\"dataLabels\":{\"enabled\":false,\"formatter\":\"â†“\"},\"y\":\"600\",\"userId\":\"888\"}," +
				"				{\"y\":\"280\",\"userId\":\"2\"}," +
				"				{\"y\":\"330\",\"userId\":\"2\"}," +
				"				{\"y\":\"200\",\"userId\":\"2\"}," +
				"				{\"dataLabels\":{\"enabled\":false},\"y\":\"900\",\"userId\":\"1\"}" +
				"			]}" +
				"		],\"flag\":\"true\"}";
		
//		\"Clinton\",\"Kennan\",\"Lancelot\",\"Law\",\"Micah\"
		return test1;
		
		/**
		User user = new User();
		user.setStatus(SYSConstant.STATE_VALID);
		List<CommonBean> agentTopList = userService.getTopUserAgent(user, start, limit);
		
		JSONObject json = new JSONObject();
		if(agentTopList != null && agentTopList.size() != 0){
			List<String> xCategories = new ArrayList<String>();
			
			List<JSONObject> totalNums = new ArrayList<JSONObject>();
			List<JSONObject> thisMonthNums = new ArrayList<JSONObject>();
			JSONObject totalNums_jsonUnit = null;
			JSONObject thisMonthNums_jsonUnit = null;
			JSONObject thisMonthNums_jsonDataLabel = null;
			for(CommonBean unitAgent:agentTopList){
				//x轴
				xCategories.add(unitAgent.getStr3() + " " + unitAgent.getStr2());
				totalNums_jsonUnit = new JSONObject();
				totalNums_jsonUnit.put("y",unitAgent.getStr4());
				totalNums_jsonUnit.put("userId",unitAgent.getStr1());
				totalNums.add(totalNums_jsonUnit);
//				totalNums.add(Long.parseLong("40"));
				
				thisMonthNums_jsonDataLabel = new JSONObject();
				thisMonthNums_jsonDataLabel.put("enabled",false);
				thisMonthNums_jsonDataLabel.put("formatter",unitAgent.getStr6());
				thisMonthNums_jsonUnit = new JSONObject();
				thisMonthNums_jsonUnit.put("dataLabels", thisMonthNums_jsonDataLabel);
				thisMonthNums_jsonUnit.put("y",unitAgent.getStr5());
				thisMonthNums_jsonUnit.put("userId",unitAgent.getStr1());
//				thisMonthNums_jsonUnit.put("y",Long.parseLong("6"));
				thisMonthNums.add(thisMonthNums_jsonUnit);
//				lastMonthNums.add(Long.parseLong(unitAgent.getStr7()));
//				lastMonthNums.add(Long.parseLong("4"));
			}
			
			List<JSONObject> series = new ArrayList<JSONObject>();
			JSONObject json_temp = new JSONObject();
			
			
			json_temp.put("name", "No. of New Customers ( YTD )");
			json_temp.put("type", "column");
			json_temp.put("data", totalNums.toArray());
			series.add(json_temp);
			
			json_temp = new JSONObject();
			json_temp.put("name", "No. of New Customers ( MTD )");
//			json_temp.put("cursor", "pointer");
			json_temp.put("type", "column");
			json_temp.put("data", thisMonthNums.toArray());
			series.add(json_temp);
			
//			json_temp = new JSONObject();
//			json_temp.put("name", "Num of New Customers (Last Month)");
//			json_temp.put("type", "column");
//			json_temp.put("showInLegend", false);//使没有点击按钮
//			json_temp.put("visible", false);//使线条隐藏
//			json_temp.put("data", lastMonthNums.toArray());
//			series.add(json_temp);
			
//			json.put("title", "近十二个月收入趋势图");
			json.put("xCategories", xCategories.toArray());
			json.put("series", series.toArray());
			json.put("flag", "true");
			
			return json.toString();
		}else{
			json.put("flag", "false");
			return json.toString();
		}
		
		**/
	}
	
	public String getAreaProfiles(int start,int limit)throws Exception{
		
		String test = "{\"xCategories\":[\"Jul,12\",\"Aug,12\",\"Sep,12\",\"Oct,12\",\"Nov,12\",\"Dec,12\"]," +
				"\"series\":[" +
				"{\"name\":\"Galway\",\"type\":\"line\",\"data\":[159000,219080,324657,364747,457688,659000]}," +
				"{\"name\":\"Dublin\",\"type\":\"line\",\"data\":[129000,239080,424657,264237,567688,759000]}," +
				"{\"name\":\"Cavan\",\"type\":\"line\",\"data\":[119000,119080,224657,264747,357688,359000]}" +
				"],\"flag\":\"true\"}";
		return test;
		
		/**
		AgentOrder	order = new AgentOrder();
		List<CommonBean> areaSixMonthProfiles = agentOrderService.getIncomeByCityJustByMonth(order, 0, 3);
		
		JSONObject json = new JSONObject();
		List<String> xCategories = DateUtils.getLastSixUSMonths();//x轴 月份
		
		if(areaSixMonthProfiles != null && areaSixMonthProfiles.size() != 0){
			
			List<Double> areaIncome = null;
			JSONObject json_temp = null;
//			List<Long> lastMonthNums = new ArrayList<Long>();
			List<JSONObject> series = new ArrayList<JSONObject>();
			for(CommonBean unitArea:areaSixMonthProfiles){
				
				areaIncome = new ArrayList<Double>();
				//倒放入6个月的收入
				areaIncome.add(Double.parseDouble(unitArea.getStr8()));//6
				areaIncome.add(Double.parseDouble(unitArea.getStr7()));//5
				areaIncome.add(Double.parseDouble(unitArea.getStr6()));//4
				areaIncome.add(Double.parseDouble(unitArea.getStr5()));//3
				areaIncome.add(Double.parseDouble(unitArea.getStr4()));//2
				areaIncome.add(Double.parseDouble(unitArea.getStr3()));//1
				
				json_temp = new JSONObject();
				json_temp.put("name", unitArea.getStr2());//地区名
				json_temp.put("type", "line");
				json_temp.put("data", areaIncome.toArray());
				series.add(json_temp);
			}
			
			
//			json.put("title", "近六个月收入趋势图");
			json.put("xCategories", xCategories.toArray());
			json.put("series", series.toArray());
			json.put("flag", "true");
			
			return json.toString();
		}else{
			json.put("flag", "false");
			return json.toString();
		}
		
		**/
		
	}
	
	/**
	 * <p>1.sim card ordered this month —Dealer 当月总共申请的SIM CARD
2.outstanding sim cards — 需要处理发货的SIM CARD </p> 
	 * @param:        @param cityCode
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        Zhengwj
	 * @Date          2013-1-17 下午08:17:26
	 */
	public String getOrderTypePie(String stateCode,String cityCode)throws Exception{
		
		String test = "{\"xCategories\":[\"Phone\",\"Sim\"],\"data\":[" +
				"{\"y\":25,\"orderNum\":\"3000\",\"drilldown\":{\"categories\":[\"Processing\",\"Completed\"],\"orderNums\":[\"2400\",\"600\"],\"data\":[20,5]}}," +
				"{\"y\":75,\"orderNum\":\"9000\",\"drilldown\":{\"categories\":[\"Processing\",\"Completed\"],\"orderNums\":[\"7000\",\"2000\"],\"data\":[58.33,16.66]}}" +
				"],\"flag\":\"true\"}";
		
		return test;
		/**
		AgentOrder	order = new AgentOrder();
		JSONObject json = new JSONObject();
		List<CommonBean> results = orderInfoService.getOrderPieByTypeThisMonth(stateCode,cityCode);
		
		if(results == null || results.size() == 0){
			json.put("flag", "false");
			return json.toString();
		}
		
		List<String> xCategories = new ArrayList<String>();
		List<String> xCategories_drilldown = new ArrayList<String>();
		
		xCategories_drilldown.add("Processing");
		xCategories_drilldown.add("Completed");
		JSONObject series = new JSONObject();
		JSONObject json_temp;
		JSONObject json_drilldown_temp;
		List<String> numData_temp;
		long total = 0;
		List<JSONObject> data = new ArrayList<JSONObject>();
		List<Double> drilldown_data = null;
		
		for(CommonBean bean : results){
			if("sim".equals(SYSConstant.goodType.get(bean.getStr1()))){
				xCategories.add("Sim");
			}
			if("mobile".equals(SYSConstant.goodType.get(bean.getStr1()))){
				xCategories.add("Phone");
			}
			
			total  += Long.parseLong(bean.getStr2());
			
//			total  += Long.parseLong(bean.getStr2())*100;
		}
		for(CommonBean bean : results){
			json_temp = new JSONObject();
//			json_temp.put("name", SYSConstant.orderTypes.get(bean.getStr1()));
//			json_temp.put("sliced", true);	
//			json_temp.put("selected", true);
			
			json_temp.put("y", Math.round(Long.parseLong(bean.getStr2()) * 10000 / total)/100.0);
			json_temp.put("orderNum", bean.getStr2());
			drilldown_data = new ArrayList<Double>();
			numData_temp = new ArrayList<String>();
			numData_temp.add(bean.getStr3());
			numData_temp.add(bean.getStr4());
			
				json_drilldown_temp = new JSONObject();
//			json_drilldown_temp.put("name", "MSIE versions ");
				json_drilldown_temp.put("categories", xCategories_drilldown.toArray());
				json_drilldown_temp.put("orderNums", numData_temp.toArray());
			
					drilldown_data.add(Math.round(Long.parseLong(bean.getStr3()) * 10000 / total)/100.0);
					drilldown_data.add(Math.round(Long.parseLong(bean.getStr4()) * 10000 / total)/100.0);
//					
//					drilldown_data.add(Math.round(Long.parseLong(bean.getStr3()) * 10000 * 100 / total)/100.0);
//					drilldown_data.add(Math.round(Long.parseLong(bean.getStr4()) * 10000 * 100 / total)/100.0);
			
				json_drilldown_temp.put("data", drilldown_data.toArray());
			json_temp.put("drilldown", json_drilldown_temp);
			data.add(json_temp);
		}
		
//		series.put("type", "pie");
//		series.put("name", "Order Pie");
//		series.put("data", data.toArray());
		
		json.put("xCategories", xCategories.toArray());
//		json.put("series", series);
		json.put("data", data.toArray());
		json.put("flag", "true");
		
		return json.toString();
		**/
	}
	
	public String statisticsDealerByMonths(String stateCode,String cityCode)throws Exception{
		
		String test = "{\"title\":\"2434 Total Deals In Network \",\"xCategories\":[\"Jul,12\",\"Aug,12\",\"Sep,12\",\"Oct,12\",\"Nov,12\",\"Dec,12\"]," +
				"\"series\":[" +
				"	{\"name\":\"Pending\",\"type\":\"line\",\"data\":[50,42,34,37,51,62]}," +
				"	{\"name\":\"Approved\",\"type\":\"line\",\"data\":[150,122,132,146,132,144]}," +
				"	{\"name\":\"Churn\",\"type\":\"line\",\"data\":[10,13,23,10,3,8]}],\"flag\":\"true\"}";
		
		return test;
		
		/**
		JSONObject json = new JSONObject();
		List<String> xCategories = DateUtils.getLastSixMonths();//x轴 月份
		
		//获得总在网代理商
		User condition = new User();
		if(StringUtil.isEmpty(stateCode) == false && StringUtil.isEmpty(cityCode) == false)
		{
			HwCity city = new HwCity(cityCode);
			city.setState(new HwState(stateCode));
			condition.setCity(city);
		}
		else if(StringUtil.isEmpty(cityCode) == false)
		{
			HwCity city = new HwCity(cityCode);
			condition.setCity(city);
		}
		else if(StringUtil.isEmpty(stateCode) == false)
		{
			HwCity city = new HwCity();
			city.setState(new HwState(stateCode));
			condition.setCity(city);
		}
		
		condition.setStatus(SYSConstant.STATE_VALID);
		condition.setUserType(SYSConstant.USER_TYPE_AGENT);
		
		int count = userService.countUser(condition);
		
		//获得近六个月的数据
		Map<String,List<Long>> result = userService.statisticsDealerByMonths(stateCode, cityCode, xCategories);
		
		if(result != null && !result.isEmpty()){
			
			JSONObject json_temp = null;
			List<JSONObject> series = new ArrayList<JSONObject>();
			
			Set<String> keys = result.keySet();
	        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
	            String key = (String) it.next();
	            json_temp = new JSONObject();
	            if("awaiting".equals(key)){
					json_temp.put("name","Pending" );
	            }
	            if("active".equals(key)){
					json_temp.put("name","Approved" );
	            }
	            if("inActive".equals(key)){
					json_temp.put("name","Churn" );
	            }
	            json_temp.put("type", "line");
	            json_temp.put("data", result.get(key).toArray());
	            series.add(json_temp);
	            
	        }
			
			json.put("title", count + " total dealers in network " );
			json.put("xCategories", DateUtils.getLastSixUSMonths().toArray());
			json.put("series", series.toArray());
			json.put("flag", "true");
			
			return json.toString();
		}else{
			json.put("flag", "false");
			return json.toString();
		}
		**/
	}
	
	public String getBestPackageByMonth(String stateCode,String cityCode)throws Exception{
		
		/**
		JSONObject json = new JSONObject();
		
		List<String> xCategories = DateUtils.getLastSixMonths();//x轴 月份
		
		//获得近六个月的数据
		Map<Long,List<Object>> result = agentOrderService.statBestProductByMonth(stateCode, cityCode, xCategories);
		
		if(result != null && !result.isEmpty()){
			
			JSONObject json_temp = null;
			List<JSONObject> series = new ArrayList<JSONObject>();
			
			Set<Long> keys = result.keySet();
	        for (Iterator<Long> it = keys.iterator(); it.hasNext();) {
	            Long key = (Long) it.next();
	            List<Object> values = result.get(key);
	            
	            json_temp = new JSONObject();
				json_temp.put("name",values.get(0) );
	            json_temp.put("type", "column");
	            json_temp.put("data", values.subList(1, values.size()));
	            series.add(json_temp);
	            
	        }
			
			json.put("title"," " );
			json.put("xCategories", DateUtils.getLastSixUSMonths());
			json.put("series", series.toArray());
			json.put("flag", "true");
			
			return json.toString();
		}else{
			json.put("flag", "false");
			return json.toString();
			
			
			
		}
		**/
		String test = "{\"title\":\" \",\"xCategories\":[\"Jul,12\",\"Aug,12\",\"Sep,12\",\"Oct,12\",\"Nov,12\",\"Dec,12\"]," +
				"\"series\":[" +
				"	{\"name\":\"Package for 3G 60\",\"type\":\"column\",\"data\":[664,667,988,1243,1665,2131]}," +
				"	{\"name\":\"Data Message 30\",\"type\":\"column\",\"data\":[789,2341,1454,1352,1454,678]}," +
				"	{\"name\":\"Data Message 10\",\"type\":\"column\",\"data\":[456,789,1053,766,1642,2319]}," +
				"	{\"name\":\"Voice Message 15\",\"type\":\"column\",\"data\":[534,456,766,677,677,989]}," +
				"	{\"name\":\"Package for 3G 20 (iPhone)\",\"type\":\"column\",\"data\":[345,567,677,400,600,345]}],\"flag\":\"true\"}";
		return test;
	}
}
