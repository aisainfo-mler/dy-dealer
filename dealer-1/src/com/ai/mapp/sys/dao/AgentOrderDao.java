package com.ai.mapp.sys.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.util.SYSConstant;

@Repository("agentOrderDao")
public class AgentOrderDao extends HibernateDao<AgentOrder, Long> {

	@Override
	public Criteria createCriteria(Criteria c, AgentOrder t) {
		
		if( t == null) return c;
	
		if(t.getOrderId() != null)
		{
			c.add(Restrictions.eq("orderId", t.getOrderId()));
		}
		
		if(t.getTibcoOrderNumber() != null)
		{
			c.add(Restrictions.eq("tibcoOrderNumber", t.getTibcoOrderNumber()));
		}
		
		if(StringUtil.isEmpty(t.getOrderCode()) == false)
		{
			c.add(Restrictions.like("orderCode", t.getOrderCode(),MatchMode.ANYWHERE).ignoreCase());
		}
		
		if(t.getCreator() != null)
		{
			c.createAlias("creator", "creator");
			
			if(StringUtil.isEmpty(t.getCreator().getUserCode()) == false)
			{
				c.add(Restrictions.eq("creator.userCode", t.getCreator().getUserCode()));
			}
			
			if(t.getCreator().getUserId() != null)
			{
				c.add(Restrictions.eq("creator.userId", t.getCreator().getUserId()));
			}
		}
		
		if(StringUtil.isEmpty(t.getStatus()) == false)
		{
			c.add(Restrictions.eq("status", t.getStatus()));
		}
	
		if(StringUtil.isEmpty(t.getPayStatus()) == false)
		{
			c.add(Restrictions.eq("payStatus", t.getPayStatus()));
		}
		
		if(StringUtil.isEmpty(t.getOrderType()) == false)
		{
			c.add(Restrictions.eq("orderType", t.getOrderType()));
		}
		
		if(StringUtil.isEmpty(t.getPayType()) == false)
		{
			c.add(Restrictions.eq("payType", t.getPayType()));
		}
		
		if(StringUtil.isEmpty(t.getSvn()) == false)
		{
			c.add(Restrictions.like("svn", t.getSvn(),MatchMode.ANYWHERE));
		}
		
		if(t.getStartTime() != null)
		{
			c.add(Restrictions.ge("createTime",t.getStartTime()));
		}
		
		if(t.getEndTime() != null)
		{
			c.add(Restrictions.le("createTime",t.getEndTime()));
		}
		
		if(StringUtil.isEmpty(t.getOrderBy()) == false){
			String[] str1 = t.getOrderBy().split(";");
			for(String str2:str1){
				if(StringUtil.isEmpty(str2) == false){
					String[] str3 = str2.split(":");
					if(str3.length > 1){
						if("desc".equalsIgnoreCase(str3[0])){
							c.addOrder(Order.desc(str3[1]));
						}else if("asc".equalsIgnoreCase(str3[0])){
							c.addOrder(Order.asc(str3[1]));
						}
						
					}else{
						c.addOrder(Order.desc(str3[0]));
					}
					
				}
			}
		}else{
			c.addOrder(Order.desc("createTime"));
		}
		
		
		return c;
	}
	
	public Map<String,Long> countIncomeByOrderType(AgentOrder order) throws Exception
	{
		Collection<Object> param = new ArrayList<Object>(0);
		param.add(order.getPayStatus());
		param.add(order.getCreator().getUserCode());
		
		String hql = "select obj.orderType,sum(obj.discountFee) from AgentOrder obj where obj.payStatus = ? and obj.creator.userCode = ? ";
		
		if(!StringUtil.isEmpty(order.getStatus()))
		{
			hql += " and obj.status = ? ";
			param.add(order.getStatus());
		}
		
		if(order.getStartTime() != null)
		{
			hql += " and obj.createTime >= ? ";
			param.add(order.getStartTime());
		}
		
		if(order.getEndTime() != null)
		{
			hql += " and obj.createTime <= ? ";
			param.add(order.getEndTime());
		}
		if(StringUtil.isEmpty(order.getOrderType()))
		
		
		hql += "group by obj.orderType ";
		
		List<?> result = find(hql, param.toArray());
		
		if(result == null || result.isEmpty())
			return null;
		
		Map<String,Long> income_map = new HashMap<String, Long>(0);
		for(Object o : result)
		{
			Object[] items = (Object[])o;
			income_map.put((String)items[0], (Long)items[1]);
		}
		
		return income_map;

	}
	
	public Map<Long,Long> countAmountByUser(AgentOrder order) throws Exception{
		Collection<Object> param = new ArrayList<Object>(0);
		param.add(order.getPayStatus());
		
		String hql = "select obj.creator.userId,sum(obj.realFee) from AgentOrder obj where obj.payStatus = ? ";
		
		if(!StringUtil.isEmpty(order.getStatus()))
		{
			hql += " and obj.status = ? ";
			param.add(order.getStatus());
		}
		
		if(order.getStartTime() != null)
		{
			hql += " and obj.createTime >= ? ";
			param.add(order.getStartTime());
		}
		
		if(order.getEndTime() != null)
		{
			hql += " and obj.createTime <= ? ";
			param.add(order.getEndTime());
		}
		if(order.getCreator() != null && order.getCreator().getUserIds() != null && order.getCreator().getUserIds().size() != 0){
			hql += " and obj.creator.userId in ( ";
			for(int i = 0; i < order.getCreator().getUserIds().size(); i ++){
				if(i != order.getCreator().getUserIds().size() - 1){
					hql += "? , ";
				}else{
					hql += "? ) ";
				}
				param.add(order.getCreator().getUserIds().get(i));
			}
//			hql += " and obj.creator.userId in ( ? ) ";
//			String userIdStr = order.getCreator().getUserIds().toString().substring(1, order.getCreator().getUserIds().toString().length() - 1);
//			param.add(userIdStr);
		}
		
		
		hql += " group by obj.creator.userCode ";
		
		List<?> result = find(hql, param.toArray());
		
		if(result == null || result.isEmpty())
			return null;
		
		Map<Long,Long> income_map = new HashMap<Long, Long>(0);
		for(Object o : result)
		{
			Object[] items = (Object[])o;
			income_map.put((Long)items[0], (Long)items[1]);
		}
		
		return income_map;
	}
	
	public static void main(String[] args) {
//		Collection<Object> param = new ArrayList<Object>(0);
//		param.add(0);
//		param.add("sdfsd");
//		List<Long> list = new ArrayList<Long>();
//		list.add(new Long(2));
//		list.add(new Long(3));
//		list.add(new Long(4));
//		param.add(list);
//		System.out.println(param);
//		for(Object o:param.toArray()){
//			System.out.println(o.getClass());
//		}
		
		long a = 100010;
		double b = (double)a;
		double c = b/(double)(1000.00);
		System.out.println(c);
		
		BigDecimal tmpB = new BigDecimal(10001);
		BigDecimal divideB = new BigDecimal(1000);
		System.out.println(tmpB.divide(divideB));
	}
	
	public Map<String,Object> countAmountByCity(AgentOrder order,int start,int limit) throws Exception{
		Collection<Object> param = new ArrayList<Object>(0);
		param.add(order.getPayStatus());
		
		String hql = "select obj.creator.city.state.stateCode,obj.creator.city.state.stateName,sum(obj.realFee) from AgentOrder obj where obj.payStatus = ? ";
		
		if(!StringUtil.isEmpty(order.getStatus()))
		{
			hql += " and obj.status = ? ";
			param.add(order.getStatus());
		}
		
		if(order.getStartTime() != null)
		{
			hql += " and obj.createTime >= ? ";
			param.add(order.getStartTime());
		}
		
		if(order.getEndTime() != null)
		{
			hql += " and obj.createTime <= ? ";
			param.add(order.getEndTime());
		}
		if(order.getCreator() != null){
			if(order.getCreator().getUserIds() != null && order.getCreator().getUserIds().size() != 0){
				hql += " and obj.creator.userId in ( ";
				for(int i = 0; i < order.getCreator().getUserIds().size(); i ++){
					if(i != order.getCreator().getUserIds().size() - 1){
						hql += "? , ";
					}else{
						hql += "? ) ";
					}
					param.add(order.getCreator().getUserIds().get(i));
				}
//				hql += " and obj.creator.userId in ( ? ) ";
//				String userIdStr = order.getCreator().getUserIds().toString().substring(1, order.getCreator().getUserIds().toString().length() - 1);
//				param.add(userIdStr);
			}
			if(order.getCreator().getCity() != null && order.getCreator().getCity().getState() != null &&!StringUtil.isEmpty(order.getCreator().getCity().getState().getStateCode())){
				hql += " and obj.creator.city.state.stateCode = ? ";
				param.add(order.getCreator().getCity().getState().getStateCode());
			}
			
			if(order.getCreator().getStateCodes() != null && order.getCreator().getStateCodes().size() != 0){
				hql += " and obj.creator.city.state.stateCode in ( ";
				for(int i = 0; i < order.getCreator().getStateCodes().size(); i++){
					if(!StringUtil.isEmpty(order.getCreator().getStateCodes().get(i))){
							if(i != order.getCreator().getStateCodes().size() - 1){
								hql += "? , ";
							}else{
								hql += "? ) ";
							}
							param.add(order.getCreator().getStateCodes().get(i));
					}
				}
			}
			
		}
		
		
		
		hql += " group by obj.creator.city.state.stateCode,obj.creator.city.state.stateName ";
		
		List<?> result = null;
		if(limit > 0 ){
			result = find(hql,start,limit, param.toArray());
		}else{
			result = find(hql, param.toArray());
		}
		
		if(result == null || result.isEmpty())
			return null;
		
		List<CommonBean> income_list = new ArrayList<CommonBean>(0);
		CommonBean obj = null;
		List<String> cityCodes = new ArrayList<String>();
		Map<String,Object> result_map = new HashMap<String, Object>(0);
		BigDecimal tmpB = null;
		BigDecimal divideB = new BigDecimal(1000);
		for(Object o : result)
		{
			Object[] items = (Object[])o;
			obj = new CommonBean();
			obj.setStr1((String)items[0]);//cityCode
			obj.setStr2((String)items[1]);//cityName
			if(items[2] != null){
				tmpB = new BigDecimal(items[2].toString());
				tmpB = tmpB.divide(divideB );
			}
			obj.setStr3(items[2] == null ? "0.00":tmpB.multiply(new BigDecimal(1000L)).toString());//income
			income_list.add(obj);
			cityCodes.add((String)items[0]);
			result_map.put("_" + (String)items[0] , obj.getStr3());//方便搜索
		}
		
		result_map.put("cityCodes", cityCodes);
		result_map.put("objs", income_list);
		return result_map;
	}
	
	
	public List<CommonBean> getTopSaleByProduct(AgentOrder order,int start,int limit)throws Exception{
		Collection<Object> param = new ArrayList<Object>(0);
		param.add(order.getPayStatus());
		
		String hql = "select obj.product.mobile.mId,obj.product.mobile.mobileModel,count(obj.orderId),sum(obj.realFee) from AgentOrder obj where obj.payStatus = ? ";
		
		if(!StringUtil.isEmpty(order.getStatus()))
		{
			hql += " and obj.status = ? ";
			param.add(order.getStatus());
		}
		
//		if(order.getStartTime() != null)
//		{
//			hql += " and obj.createTime >= ? ";
//			param.add(order.getStartTime());
//		}
//		
//		if(order.getEndTime() != null)
//		{
//			hql += " and obj.createTime <= ? ";
//			param.add(order.getEndTime());
//		}
		if(order.getCreator() != null){
			if(order.getCreator().getUserIds() != null && order.getCreator().getUserIds().size() != 0){
				hql += " and obj.creator.userId in ( ";
				for(int i = 0; i < order.getCreator().getUserIds().size(); i ++){
					if(i != order.getCreator().getUserIds().size() - 1){
						hql += "? , ";
					}else{
						hql += "? ) ";
					}
					param.add(order.getCreator().getUserIds().get(i));
				}
			}
			if(order.getCreator().getCity() != null && order.getCreator().getCity().getState() != null && !StringUtil.isEmpty(order.getCreator().getCity().getState().getStateCode())){
				hql += " and obj.creator.city.state.stateCode = ? ";
				param.add(order.getCreator().getCity().getCityCode());
			}
			
			if(order.getCreator().getStateCodes() != null && order.getCreator().getStateCodes().size() != 0){
				hql += " and obj.creator.city.state.stateCode in ( ";
				for(int i = 0; i < order.getCreator().getStateCodes().size(); i++){
					if(!StringUtil.isEmpty(order.getCreator().getStateCodes().get(i))){
							if(i != order.getCreator().getStateCodes().size() - 1){
								hql += "? , ";
							}else{
								hql += "? ) ";
							}
							param.add(order.getCreator().getStateCodes().get(i));
					}
				}
			}
			
		}
		
		hql += " group by obj.product.mobile.mId,obj.product.mobile.mobileModel ";
		
		List<?> result = null;
		if(limit > 0 ){
			result = find(hql,start,limit, param.toArray());
		}else{
			result = find(hql, param.toArray());
		}
		
		if(result == null || result.isEmpty())
			return null;
		
		List<CommonBean> income_list = new ArrayList<CommonBean>(0);
		CommonBean obj = null;
//		List<String> cityCodes = new ArrayList<String>();
		for(Object o : result)
		{
			Object[] items = (Object[])o;
			obj = new CommonBean();
			obj.setStr1(items[0] == null?"":items[0].toString());//mobileId
			obj.setStr2(items[1] == null?"":(String)items[1]);//mobileName
			obj.setStr3(items[2] == null ? "0":items[2].toString());//count
			obj.setStr4(items[2] == null ? "0":items[3].toString());//income
			income_list.add(obj);
//			cityCodes.add((String)items[0]);
		}
//		Map<String,Object> result_map = new HashMap<String, Object>(0);
//		result_map.put("cityCodes", cityCodes);
//		result_map.put("objs", income_list);
		return income_list;
	}
	
	public Map<Long,String> getBestProudct(String stateCode,String cityCode,Date startTime,Date endTime) throws Exception
	{	
		Criteria c = getSession().createCriteria(entityClass);
//		c.createAlias("creator","creator", Criteria.LEFT_JOIN);
//		c.createAlias("creator.city", "city", Criteria.LEFT_JOIN);
//		c.createAlias("city.state", "state", Criteria.LEFT_JOIN);
		c.createAlias("product", "product", Criteria.LEFT_JOIN);
		c.createAlias("product.contract", "contract", Criteria.LEFT_JOIN);
		
		if(StringUtil.isEmpty(stateCode) == false || StringUtil.isEmpty(cityCode) == false)
		{
			c.createAlias("creator","creator", Criteria.LEFT_JOIN);
			c.createAlias("creator.city", "city", Criteria.LEFT_JOIN);
			c.createAlias("city.state", "state", Criteria.LEFT_JOIN);
		}
		
		
		if(stateCode != null && StringUtil.isEmpty(stateCode) == false)
		{
			c.add(Restrictions.eq("state.stateCode", stateCode));
		}
		
		if(cityCode != null && StringUtil.isEmpty(cityCode) == false)
		{
			c.add(Restrictions.eq("city.cityCode", cityCode));
		}
		
		if(startTime != null)
			c.add(Restrictions.ge("createTime", startTime));
		
		if(endTime != null)
			c.add(Restrictions.le("createTime",endTime));
		
		c.add(Restrictions.eq("status", SYSConstant.AGENT_ORDER_STATUS_COMPLETE));
		
		ProjectionList plist = Projections.projectionList();
		
		plist.add(Projections.groupProperty("contract.contractId"));
		plist.add(Projections.groupProperty("contract.name"));
		plist.add(Projections.groupProperty("contract.contractType"));
		plist.add(Projections.alias(Projections.count("orderId"),"aliasCount"));
		
		c.addOrder(Order.desc("aliasCount"));
		
		c.setProjection(plist);
//		c.setResultTransformer(new AliasToBeanResultTransformer(MappAcceptRange.class));
		
		c.setMaxResults(5);
		List<?> result =  c.list();
		
		
		if(result == null || result.isEmpty())
			return null;
		
		Map<Long,String> result_vo = new LinkedHashMap<Long,String>(0);
		
		for(Object object : result)
		{
			Object[] values = (Object[])object;
			
			if(values.length < 2)
				continue;
			
			if(values[0] != null && values[1] != null)
				result_vo.put((Long)values[0],(String)values[1]+"("+(String)values[2]+")");
			
		}
		
		return result_vo;
		
	}
	
	public Map<String,Long> statSingleProductByMonth(String stateCode,String cityCode,Date startTime,Date endTime,Long contractId) throws Exception
	{
		if(contractId == null)
			return null;
		
		Criteria c = getSession().createCriteria(entityClass);
		c.createAlias("product", "product", Criteria.LEFT_JOIN);
		c.createAlias("product.contract", "contract", Criteria.LEFT_JOIN);
		
		
		if(StringUtil.isEmpty(stateCode) == false || StringUtil.isEmpty(cityCode) == false)
		{
			c.createAlias("creator","creator", Criteria.LEFT_JOIN);
			c.createAlias("creator.city", "city", Criteria.LEFT_JOIN);
			c.createAlias("city.state", "state", Criteria.LEFT_JOIN);
		}
		
		if(stateCode != null && StringUtil.isEmpty(stateCode) == false)
			c.add(Restrictions.eq("state.stateCode", stateCode));
		
		if(cityCode != null && StringUtil.isEmpty(cityCode) == false)
			c.add(Restrictions.eq("city.cityCode", cityCode));
		
		if(startTime != null)
			c.add(Restrictions.ge("createTime", startTime));
		
		if(endTime != null)
			c.add(Restrictions.le("createTime",endTime));
		
		c.add(Restrictions.eq("contract.contractId",contractId));
		
		c.add(Restrictions.eq("status", SYSConstant.AGENT_ORDER_STATUS_COMPLETE));
		
		ProjectionList plist = Projections.projectionList();
		
		Projection ym_p = Projections.sqlGroupProjection(
		"date_format({alias}.createTime,'%Y%m') as ym", 
		"date_format({alias}.createTime,'%Y%m')",  new String[] {"ym"},new Type[] { Hibernate.STRING });
		
		plist.add(Projections.alias(ym_p, "alias_year_month"));
		plist.add(Projections.count("orderId"));
		
		c.addOrder(Order.asc("alias_year_month"));
		
		c.setProjection(plist);
//		c.setResultTransformer(new AliasToBeanResultTransformer(MappAcceptRange.class));
		
		List<?> result =  c.list();
		
		if(result == null || result.isEmpty())
			return null;
		
		Map<String,Long> result_vo = new HashMap<String,Long>(0);
		
		for(Object object : result)
		{
			Object[] values = (Object[])object;
			
			if(values.length < 2)
				continue;
			
			System.out.println(values[0]+"   "+values[1]);
			
			if(values[0] != null)
				result_vo.put(values[0].toString(), values[1] != null? (new Long(values[1].toString())):(new Long(0)));
		}
		
		return result_vo;
		
	}
	
}
