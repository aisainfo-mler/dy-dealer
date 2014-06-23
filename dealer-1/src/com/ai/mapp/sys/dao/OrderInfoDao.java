package com.ai.mapp.sys.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.entity.OrderInfo;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午06:58:37
 * 类说明:
 */

@Repository("orderInfoDao")
public class OrderInfoDao extends HibernateDao<OrderInfo, Long> {

	@Override
	public Criteria createCriteria(Criteria c, OrderInfo t) throws Exception {
		if( t == null) return c;
		
		if(StringUtil.isEmpty(t.getSerialNumber()) == false)
		{
			c.add(Restrictions.like("serialNumber", t.getSerialNumber(),MatchMode.ANYWHERE).ignoreCase());
		}
		
		if(StringUtil.isEmpty(t.getType()) == false)
		{
			c.add(Restrictions.eq("type", t.getType()));
		}
		
		if(StringUtil.isEmpty(t.getIsAuthor())){
			if(t.getCreator() != null)
			{
				if(t.getCreator().getUserId() == null && StringUtil.isEmpty(t.getCreator().getName())){
					
				}else{
					c.createAlias("creator", "creator");
				}
//				c.createAlias("operator", "operator");
				if(t.getCreator().getUserId() != null){
					c.add(Restrictions.eq("creator.userId", t.getCreator().getUserId()));
				}
				if(!StringUtil.isEmpty(t.getCreator().getName())){
					c.add(Restrictions.sqlRestriction("concat( LAST_NAME,FIRST_NAME ) like '%" + t.getCreator().getName().replace(" ", "") + "%'"));
//					c.add(Restrictions.or(Restrictions.like("creator.firstName", t.getCreator().getName(),MatchMode.ANYWHERE), Restrictions.like("creator.lastName", t.getCreator().getName(),MatchMode.ANYWHERE)));
				}
			}
		}
		if(StringUtil.isEmpty(t.getStatus()) == false){
			c.add(Restrictions.eq("status", t.getStatus()));
		}
		
		c.addOrder(Order.desc("id"));
		
		return c;
	}

	
	public Map<String,CommonBean> getOrderNumByTypeAndCity(OrderInfo order,String stateCode,String cityCode)throws Exception{
		Collection<Object> param = new ArrayList<Object>(0);
		
		String hql = "select obj.type,count(order_id) from OrderInfo obj where 1=1 ";
		if(StringUtil.isNotEmpty(order.getStatus())){
			if(order.getStatus().startsWith("!")){
				String notStatus = order.getStatus().substring(1);
				if(notStatus.indexOf(",") != -1){
					String[] statusArr = notStatus.split(",");
					for(String unitStatus:statusArr){
//						hql += " ? ,";
						hql += " and obj.status != ? ";
						param.add(unitStatus);
					}
//					hql = hql.substring(0,hql.length() - 1) + " ) ";
				}else{
					hql += " and obj.status != ?";
					param.add(notStatus);
				} 
			}else{
				if(order.getStatus().indexOf(",") != -1){
					String[] statusArr = order.getStatus().split(",");
					hql += " and obj.status in ( ";
					for(String unitStatus:statusArr){
						hql += " ? ,";
						param.add(unitStatus);
					}
					hql = hql.substring(0,hql.length() - 1) + " ) ";
				}else{
					hql += "and obj.status = ?";
					param.add(order.getStatus());
				}
			}
			
			
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
		if(StringUtil.isNotEmpty(stateCode)){
			hql += " and obj.creator.city.state.stateCode = ? ";
			param.add(stateCode);
		}
		if(StringUtil.isNotEmpty(cityCode)){
			hql += " and obj.creator.city.cityCode = ? ";
			param.add(cityCode);
		}
		
		hql += " group by obj.type ";
		
		
		List<?> result = find(hql, param.toArray());
		
		if(result == null || result.isEmpty())
			return null;
		
		CommonBean obj = null;
		Map<String,CommonBean> result_map = new HashMap<String, CommonBean>();
		for(Object o : result)
		{
			Object[] items = (Object[])o;
			obj = new CommonBean();
			obj.setStr1((String)items[0]);//type
//			obj.setStr2((Long)items[1] + "");//count
			/** 假数据 **/
			obj.setStr2((Long)items[1]*1000 + "");//count
			result_map.put(obj.getStr1(),obj);//方便搜索
		}
		
		return result_map;
	}
	
	/**
	 * <p>描述:通过订单明细ID获得相应的批量信息</p> 
	 * @param detailIds
	 * @return CommonBean str1:goodId ,str2:startNum,str3:endNum,str4:intervalNums(数量)
	 * @throws Exception  
	 * @author        Zhengwj
	 * @Date          2014-5-14 上午11:38:24
	 */
	public List<CommonBean> getCardSequence(String detailIds)throws Exception{
		Collection<Object> param = new ArrayList<Object>(0);
		StringBuilder sb = new StringBuilder("select");
		sb.append(" Num1.good_id,")
		.append(" Num1.item_value,")
		.append(" min(Num2.item_value),")
		.append(" min(Num2.item_value) - Num1.item_value + 1")
		.append(" from hw_order_item_info Num1 inner join hw_order_item_info Num2")
		.append(" where Num2.good_id = Num1.good_id")
		.append(" AND Num1.item_value<=Num2.item_value AND ")
		.append(" (CONVERT(right(Num1.ITEM_VALUE ,10),UNSIGNED) - 1) not in (select CONVERT(right(item_value ,10),UNSIGNED) from hw_order_item_info Num3 WHERE Num3.good_id = Num1.good_id)")
		.append(" and")
		.append(" (CONVERT(right(Num2.ITEM_VALUE ,10),UNSIGNED) + 1) not in (select CONVERT(right(item_value ,10),UNSIGNED) from hw_order_item_info Num4 WHERE Num4.good_id = Num2.good_id)")
		.append(" and")
		.append(" Num1.detail_id in ( " + detailIds + " ) and Num2.detail_id in ( " + detailIds + " )")
		.append(" group by")
		.append(" Num1.good_id,")
		.append(" Num1.item_value");
		
		param.add(detailIds);
		param.add(detailIds);
		
		List<?> result = list(sb.toString());
		
		if(result == null || result.isEmpty())
			return null;

		CommonBean obj = null;
		List<CommonBean> result_list = new ArrayList<CommonBean>();
		for(Object o : result)
		{
			Object[] items = (Object[])o;
			obj = new CommonBean();
			obj.setStr1(items[0].toString());//goodId
			obj.setStr2((items[1] == null?"":items[1]) + "");//startNum
			obj.setStr3((items[2] == null?"":items[2]) + "");//endNum
			obj.setStr4((items[3] == null?"":items[3]) + "");//count
			result_list.add(obj);
		}
		return result_list;
	}
}
