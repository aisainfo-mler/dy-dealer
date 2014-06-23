package com.ai.mapp.sys.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.dao.HibernateDao;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.entity.OrderItem;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午09:26:10
 * 类说明:
 */

@Repository("orderItemDao")
public class OrderItemDao extends HibernateDao<OrderItem, Long> {

	@Override
	public Criteria createCriteria(Criteria c, OrderItem t) throws Exception {
		if(t.getDetail() != null)
		{
			if(t.getDetail().getId() != null)
			{
				c.createAlias("detail", "detail");
				c.add(Restrictions.eq("detail.id", t.getDetail().getId()));
			}
		}
		
		if(t.getGood() != null)
		{
			c.createAlias("good", "good");
			if(t.getGood().getId() != null)
			{
				c.add(Restrictions.eq("good.id", t.getGood().getId()));
			}
			
			if(t.getGood().getType() != null)
			{
				c.add(Restrictions.eq("good.type", t.getGood().getType()));
			}
			
			if(t.getGood().getGoodIds() != null && t.getGood().getGoodIds().isEmpty() == false)
			{
				c.add(Restrictions.in("good.id", t.getGood().getGoodIds()));
			}
			
		}
		
		if(StringUtil.isEmpty(t.getStatus()) == false)
		{
			c.add(Restrictions.eq("status", t.getStatus()));
		}
		if(StringUtil.isEmpty(t.getFrom()) == false)
		{
			if(t.getFrom().startsWith("!")){
				c.add(Restrictions.ne("from", t.getFrom().substring(1)));
			}else{
				c.add(Restrictions.eq("from", t.getFrom()));
			}
		}
		if(StringUtil.isEmpty(t.getItemValue()) == false)
		{
			c.add(Restrictions.eq("itemValue", t.getItemValue()));
		}
		
		if(t.getOperator() != null)
		{
			c.createAlias("operator", "operator");
			if(StringUtil.isEmpty(t.getOperator().getUserCode()) == false)
			{
				c.add(Restrictions.eq("operator.userCode", t.getOperator().getUserCode()));
			}
		}
		
		if(t.getAgent() != null)
		{
			c.createAlias("agent", "agent");
			if(StringUtil.isEmpty(t.getAgent().getUserCode()) == false)
			{
				c.add(Restrictions.eq("agent.userCode", t.getAgent().getUserCode()));
			}
		}
		
		if(StringUtil.isEmpty(t.getOrderBy()) == false)
		{
			c.setProjection(Projections.groupProperty(t.getOrderBy()));
		}
		
		if(StringUtil.isEmpty(t.getGroupBy()) == false)
		{
			c.setProjection(Projections.groupProperty(t.getGroupBy()));
		}
		
		return c;
	}
	
	/**
	  * <p>同步更新 order_item表</p> 
	  * @return 
	  * @return String 
	 */
	public void deleteOrderItemByDetailId(Long detailId){
		if(detailId == null || StringUtil.isEmpty(detailId.toString())){
			return;
		}
		String sql = " delete from HW_ORDER_ITEM_INFO where DETAIL_ID = '" + detailId + "' ";
		getSession().createSQLQuery(sql).executeUpdate();
			
	}
	
	
	public Map<Long,Long> countGood(String userCode,String goodType,Long goodId,String status)
	{
		List<Object> param = new ArrayList<Object>(0);
		String hql = "select obj.good.id,count(obj.good.id) from OrderItem obj where 1=1 ";
		if(StringUtil.isEmpty(userCode) == false)
		{
			hql += " and obj.agent.userCode = ? ";
			param.add(userCode);
		}
		if(goodId != null)
		{
			hql += " and obj.good.id = ? ";
			param.add(goodId);
		}
		
		if(StringUtil.isEmpty(goodType) == false)
		{
			hql += " and obj.good.type = ? ";
			param.add(goodType);
		}
		
		if(StringUtil.isEmpty(status) == false)
		{
			hql += " and obj.status = ? ";
			param.add(status);
		}
		
		hql += " group by obj.good.id ";
		
		System.out.println(hql);
		
		
		List<?> result = find(hql,param.toArray());
		
		if(result == null || result.isEmpty())
			return null;
		
		Map<Long,Long> ret_map = new HashMap<Long,Long>(0);
		
		for(Object object : result)
		{
			Object[] ret = (Object[])object;
			ret_map.put((Long)ret[0], (Long)ret[1]);
		}
		
		return ret_map;
	}
	
	/**
	  * <p>同步更新 order_item表</p> 
	  * @return 
	  * @return String 
	 */
	public void updateOrderItemStatusByDetailId(Long detailId,String status){
		if(detailId == null || StringUtil.isEmpty(detailId.toString())){
			return;
		}
		String sql = " update HW_ORDER_ITEM_INFO set ITEM_STATUS = '" + status + "' where DETAIL_ID = '" + detailId + "' ";
		getSession().createSQLQuery(sql).executeUpdate();
			
	}
	
	public void resetAllTemp(){
		String sql = " update hw_order_item_info set ITEM_STATUS='0' where ITEM_STATUS='4' and temp_eff_time<SYSDATE() ";
		getSession().createSQLQuery(sql).executeUpdate();
	}
	
	public void setOrderItemStatus(String orderCode,String stat,OrderItem condition)
	{
		Collection<String> param = new ArrayList<String>(0);
		param.add(stat);
		param.add(orderCode);
		
//		String hql = " update OrderItem obj set obj.status = ? where obj.detail.order.serialNumber = ? ";
		String hql = " update HW_ORDER_ITEM_INFO set ITEM_STATUS = '" + stat + "' where detail_id in ( select b.detail_id from hw_order_info a,hw_order_detail b where a.order_id=b.order_id and a.SERIAL_NUMBER = '" + orderCode + "')  ";
		if(StringUtil.isEmpty(condition.getStatus()) == false)
		{
//			hql += " and obj.status = ? ";
//			param.add(condition.getStatus());
			hql += " and ITEM_STATUS = '" + condition.getStatus() + "' ";
		}
		
//		batchExecute(hql, param.toArray());
		getSession().createSQLQuery(hql).executeUpdate();
		
	}
}
