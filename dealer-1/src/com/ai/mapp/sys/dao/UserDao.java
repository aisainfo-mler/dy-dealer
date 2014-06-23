package com.ai.mapp.sys.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.util.SYSConstant;

@Repository("userDao")
public class UserDao extends HibernateDao<User, Long> {

	@Override
	public Criteria createCriteria(Criteria c, User t) {
		
		if( t == null) return c;
	
		if(t.getUserId() != null)
		{
			c.add(Restrictions.eq("userId", t.getUserId()));
		}
		
		if(t.getCreator() != null){
			if(t.getCreator().getUserIds() != null && t.getCreator().getUserIds().size() != 0){
				if(t.getCreator().getUserIds().size() == 1)
				{
					c.add(Restrictions.eq("creator.userId", t.getCreator().getUserIds().iterator().next()));
				}
				else
				{
					c.add(Restrictions.in("creator.userId", t.getCreator().getUserIds()));
				}
			}
			
		}
		
		if(StringUtil.isEmpty(t.getUserCode()) == false)
		{
			c.add(Restrictions.eq("userCode", t.getUserCode()));
//			c.add(Restrictions.or(Restrictions.eq("userCode", t.getUserCode()),
//					Restrictions.or(Restrictions.eq("email", t.getUserCode()), Restrictions.eq("mobileNo", t.getUserCode()))));
		}
		
		if(StringUtil.isEmpty(t.getPassword()) == false)
		{
			c.add(Restrictions.eq("password", t.getPassword()));
		}
		
		if(StringUtil.isEmpty(t.getEmail()) == false)
		{
			c.add(Restrictions.like("email", t.getEmail(),MatchMode.ANYWHERE));
		}
		
		if(StringUtil.isEmpty(t.getName()) == false)
		{
//			c.add(Restrictions.or(Restrictions.like("firstName", t.getName(),MatchMode.ANYWHERE).ignoreCase(), Restrictions.like("lastName", t.getName(),MatchMode.ANYWHERE).ignoreCase())
//			);
			c.add(Restrictions.sqlRestriction("concat( LAST_NAME,FIRST_NAME ) like '%" + t.getName().replace(" ", "") + "%'"));
		}
		
		if(StringUtil.isEmpty(t.getStatus()) == false)
		{	
			if(t.getStatus().startsWith("!")){
				c.add(Restrictions.ne("status", t.getStatus().substring(1)));
			}else{
				c.add(Restrictions.eq("status", t.getStatus()));
			}
		}
		if(StringUtil.isEmpty(t.getUserType()) == false)
		{
			c.add(Restrictions.eq("userType", t.getUserType()));
		}
	
//		if(t.getUserIds() != null){
//			c.add(Restrictions.in("userIds", t.getUserIds()));
//		}
		if(t.getStartTime() != null)
		{
			c.add(Restrictions.ge("createTime", t.getStartTime()));
		}
		
		if(t.getEndTime() != null)
		{
			c.add(Restrictions.le("createTime", t.getEndTime()));
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
			c.addOrder(Order.desc("userId"));
		}
		
		
		
		return c;
	}

	/**
	 * <p>描述: 获得发展人最多的用户id</p> 
	 * @param:        @param condition
	 * @param:        @param limit
	 * @param:        @return    
	 * @return:       List<String>    
	 * @author        Zhengwj
	 * @Date          2012-10-5 下午09:13:57
	 */
	public Map<String,Object> getTopUserIds(User condition,int start,int limit)throws Exception{
		List<Long> userIds = null;
		List<CommonBean> objs = null;
		Map<String,Object> resultMap = null;
		/**
		 * select count(OPERATOR_ID) as users from hw_operator group by CREATEOR  order by users
		 */
		
		Collection<Object> param = new ArrayList<Object>(0);
		String hql = "select obj.creator.userId,obj.creator.firstName,obj.creator.lastName, count(obj.userId) from User as obj where 1=1 ";
		
		String userIdStr = null;
		
		if(condition != null){
			if(condition.getCreator() != null){
				if(!StringUtil.isEmpty(condition.getCreator().getUserType()))
				{
					hql += " and obj.creator.userType = ? ";
					param.add(condition.getCreator().getUserType());
				}
				
				if(condition.getCreator().getUserIds() != null && condition.getCreator().getUserIds().size() != 0)
				{
					hql += " and obj.creator.userId in ( ";
					for(int i = 0; i < condition.getCreator().getUserIds().size(); i ++){
						if(i != condition.getCreator().getUserIds().size() - 1){
							hql += "? , ";
						}else{
							hql += "? ) ";
						}
						param.add(condition.getCreator().getUserIds().get(i));
					}
//					hql += " and obj.creator.userId in ( ? ) ";
					userIdStr = condition.getUserIds().toString().substring(1, condition.getUserIds().toString().length() - 1);
//					param.add(userIdStr);
				}
				
				if(!StringUtil.isEmpty(condition.getCreator().getStatus()))
				{
					hql += " and obj.creator.status = ? ";
					param.add(condition.getCreator().getStatus());
				}
				if(condition.getCreator().getStartTime() != null)
				{
					hql += " and obj.createTime >= ? ";
					param.add(condition.getCreator().getStartTime());
				}
				
				if(condition.getCreator().getEndTime() != null)
				{
					hql += " and obj.createTime <= ? ";
					param.add(condition.getCreator().getEndTime());
				}
				
			}
			
		}
		
		if(!StringUtil.isEmpty(condition.getStatus()))
		{
			hql += " and obj.status = ? ";
			param.add(condition.getStatus());
		}
		
		if(condition.getStartTime() != null)
		{
			hql += " and obj.createTime >= ? ";
			param.add(condition.getStartTime());
		}
		
		if(condition.getEndTime() != null)
		{
			hql += " and obj.createTime <= ? ";
			param.add(condition.getEndTime());
		}
		
		
		hql += " and obj.userType = ? ";
		param.add(SYSConstant.USER_TYPE_CUSTOMER);
		
		
		hql += "group by obj.creator.userId,obj.creator.firstName,obj.creator.lastName";
		
		if( condition.getCreator() != null && condition.getCreator().getUserIds() != null && condition.getCreator().getUserIds().size() != 0){
			hql += " order by field( obj.creator.userId , " + userIdStr +" ) ";
		}else{
			hql += " order by count(obj.userId) desc ";
			
		}
		
		
		List<?> result = null;
		if(limit > 0 ){
			result = find(hql,start,limit, param.toArray());
		}else{
			result = find(hql, param.toArray());
		}
		
		if(result != null){
			userIds = new ArrayList<Long>(0);
			objs = new ArrayList<CommonBean>(0);
			CommonBean tmpObj = null;
			for(Object o : result)
			{
				tmpObj = new CommonBean();
				Object[] items = (Object[])o;
				userIds.add((Long)(items[0]));
				tmpObj.setStr1(items[0].toString());//userid
				tmpObj.setStr2(items[1] == null ? "":items[1].toString());//firstName
				tmpObj.setStr3(items[2] == null ? "":items[2].toString());//lastName
//				tmpObj.setStr4(items[3] == null ? "0":items[3].toString());//count
				/**假数据**/
				tmpObj.setStr4(items[3] == null ? "0":(((Long)items[3]).longValue()+(long)1000)+"");//count
				objs.add(tmpObj);
			}
			resultMap = new HashMap<String,Object>();
			resultMap.put("userIds", userIds);
			resultMap.put("objs", objs);
		}
		return resultMap;
	}
	
	public Map<String,Long> getUsersByCity(User condition,int start,int limit)throws Exception{
		List<Long> userIds = null;
		List<CommonBean> objs = null;
		Map<String,Long> resultMap = null;
	
		
		Collection<Object> param = new ArrayList<Object>(0);
		String hql = "select obj.creator.city.state.stateCode, count(obj.userId) from User as obj where 1=1 ";
		
		String userIdStr = null;
		
		if(condition != null){
			if(condition.getCreator() != null){
				if(!StringUtil.isEmpty(condition.getCreator().getUserType()))
				{
					hql += " and obj.creator.userType = ? ";
					param.add(condition.getCreator().getUserType());
				}
				
				if(condition.getCreator().getUserIds() != null && condition.getCreator().getUserIds().size() != 0)
				{
					hql += " and obj.creator.userId in ( ";
					for(int i = 0; i < condition.getCreator().getUserIds().size(); i ++){
						if(i != condition.getCreator().getUserIds().size() - 1){
							hql += "? , ";
						}else{
							hql += "? ) ";
						}
						param.add(condition.getCreator().getUserIds().get(i));
					}
//					hql += " and obj.creator.userId in ( ? ) ";
//					userIdStr = condition.getUserIds().toString().substring(1, condition.getUserIds().toString().length() - 1);
//					param.add(userIdStr);
				}
				
				if(!StringUtil.isEmpty(condition.getCreator().getStatus()))
				{
					hql += " and obj.creator.status = ? ";
					param.add(condition.getCreator().getStatus());
				}
				if(condition.getCreator().getStartTime() != null)
				{
					hql += " and obj.createTime >= ? ";
					param.add(condition.getCreator().getStartTime());
				}
				
				if(condition.getCreator().getEndTime() != null)
				{
					hql += " and obj.createTime <= ? ";
					param.add(condition.getCreator().getEndTime());
				}
				
				if(condition.getCreator().getStateCodes() != null && condition.getCreator().getStateCodes().size() != 0 ){
					hql += " and obj.creator.city.state.stateCode in ( ";
					for(int i = 0; i < condition.getCreator().getStateCodes().size(); i++){
						if(!StringUtil.isEmpty(condition.getCreator().getStateCodes().get(i))){
								if(i != condition.getCreator().getStateCodes().size() - 1){
									hql += "? , ";
								}else{
									hql += "? ) ";
								}
								param.add(condition.getCreator().getStateCodes().get(i));
						}
					}
				}
				
			}
			
		}
		
		if(!StringUtil.isEmpty(condition.getStatus()))
		{
			hql += " and obj.status = ? ";
			param.add(condition.getStatus());
		}
		
		if(condition.getStartTime() != null)
		{
			hql += " and obj.createTime >= ? ";
			param.add(condition.getStartTime());
		}
		
		if(condition.getEndTime() != null)
		{
			hql += " and obj.createTime <= ? ";
			param.add(condition.getEndTime());
		}
		
		
		hql += "group by obj.creator.city.state.stateCode";
		
		if( condition.getCreator() != null && condition.getCreator().getUserIds() != null && condition.getCreator().getUserIds().size() != 0){
			hql += " order by field( obj.creator.userId , " + userIdStr +" ) ";
		}else{
			hql += " order by count(obj.userId) ";
			
		}
		
		
		List<?> result = null;
		if(limit > 0 ){
			result = find(hql,start,limit, param.toArray());
		}else{
			result = find(hql, param.toArray());
		}
		
		
		if(result != null){
			resultMap = new HashMap<String,Long>();
			objs = new ArrayList<CommonBean>(0);
			CommonBean tmpObj = null;
			for(Object o : result)
			{
				tmpObj = new CommonBean();
				Object[] items = (Object[])o;
				resultMap.put(items[0].toString(), (Long)items[1]);
			}
			
		}
		return resultMap;
	}
	
	
	/**
	 * 按月统计等待处理的Dealer申请
	 * @param stateCode 省份编码
	 * @param cityCode 城市编码
	 * @param startTime 时间
	 * @param endTime 结束时间
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> statAwaitingDealerByMonth(String stateCode,String cityCode,Date startTime,Date endTime) throws Exception
	{
		Criteria c = getSession().createCriteria(entityClass);
		c.createAlias("city", "city", Criteria.LEFT_JOIN);
		c.createAlias("city.state", "city.state", Criteria.LEFT_JOIN);
		
		if(stateCode != null && StringUtil.isEmpty(stateCode) == false)
		{
			c.add(Restrictions.eq("city.state.stateCode", stateCode));
		}
		
		if(cityCode != null && StringUtil.isEmpty(cityCode) == false)
		{
			c.add(Restrictions.eq("city.cityCode", cityCode));
		}
		
		if(startTime != null)
			c.add(Restrictions.ge("createTime", startTime));
		
		if(endTime != null)
			c.add(Restrictions.le("createTime",endTime));
		
		c.add(Restrictions.eq("userType", SYSConstant.USER_TYPE_AGENT));
//		c.add(Restrictions.eq("status", SYSConstant.STATE_WAITING_4_AUDIT));
		
		ProjectionList plist = Projections.projectionList();
		
		Projection ym_p = Projections.sqlGroupProjection(
				"date_format({alias}.CREATETIME,'%Y%m') as ym", 
				"date_format({alias}.CREATETIME,'%Y%m')",  new String[] {"ym"},new Type[] { Hibernate.STRING });
		
		plist.add(Projections.alias(ym_p, "alias_year_month"));
		plist.add(Projections.count("userId"));
		
		c.addOrder(Order.asc("alias_year_month"));
		
		c.setProjection(plist);
//		c.setResultTransformer(new AliasToBeanResultTransformer(MappAcceptRange.class));
		
		List<?> result =  c.list();
		
		if(result == null || result.isEmpty())
			return null;
		
		Map<String,String> result_vo = new HashMap<String,String>(0);
		
		for(Object object : result)
		{
			Object[] values = (Object[])object;
			
			if(values.length < 2)
				continue;
			
			System.out.println(values[0]+"   "+values[1]);
			
			if(values[0] != null)
				result_vo.put(values[0].toString(), values[1] != null?values[1].toString():"0");
		}
		
		return result_vo;
	}
	
	/**
	 * 按月统计审批通过的Dealer申请
	 * @param stateCode 省份编码
	 * @param cityCode 城市编码
	 * @param startTime 时间
	 * @param endTime 结束时间
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> statActiveDealerByMonth(String stateCode,String cityCode,Date startTime,Date endTime) throws Exception
	{
		Criteria c = getSession().createCriteria(entityClass);
		c.createAlias("city", "city", Criteria.LEFT_JOIN);
		c.createAlias("city.state", "city.state", Criteria.LEFT_JOIN);
		
		if(stateCode != null && StringUtil.isEmpty(stateCode) == false)
			c.add(Restrictions.eq("city.state.stateCode", stateCode));
		
		if(cityCode != null && StringUtil.isEmpty(cityCode) == false)
			c.add(Restrictions.eq("city.cityCode", cityCode));
		
		if(startTime != null)
			c.add(Restrictions.ge("approveTime", startTime));
		
		if(endTime != null)
			c.add(Restrictions.le("approveTime",endTime));
		
		c.add(Restrictions.eq("userType", SYSConstant.USER_TYPE_AGENT));
//		c.add(Restrictions.eq("status", SYSConstant.STATE_VALID));
		
		ProjectionList plist = Projections.projectionList();
		
		Projection ym_p = Projections.sqlGroupProjection(
		"date_format({alias}.approve_time,'%Y%m') as ym", 
		"date_format({alias}.approve_time,'%Y%m')",  new String[] {"ym"},new Type[] { Hibernate.STRING });
		
		plist.add(Projections.alias(ym_p, "alias_year_month"));
		plist.add(Projections.count("userId"));
		
		c.addOrder(Order.asc("alias_year_month"));
		
		c.setProjection(plist);
//		c.setResultTransformer(new AliasToBeanResultTransformer(MappAcceptRange.class));
		
		List<?> result =  c.list();
		
		if(result == null || result.isEmpty())
			return null;
		
		Map<String,String> result_vo = new HashMap<String,String>(0);
		
		for(Object object : result)
		{
			Object[] values = (Object[])object;
			
			if(values.length < 2)
				continue;
			
			System.out.println(values[0]+"   "+values[1]);
			
			if(values[0] != null)
				result_vo.put(values[0].toString(), values[1] != null?values[1].toString():"0");
		}
		
		return result_vo;
	}
	
	/**
	 * 按月统计审批通过的Dealer申请
	 * @param stateCode 省份编码
	 * @param cityCode 城市编码
	 * @param startTime 时间
	 * @param endTime 结束时间
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> statInActiveDealerByMonth(String stateCode,String cityCode,Date startTime,Date endTime) throws Exception
	{
		Criteria c = getSession().createCriteria(entityClass);
		c.createAlias("city", "city", Criteria.LEFT_JOIN);
		c.createAlias("city.state", "city.state", Criteria.LEFT_JOIN);
		
		if(stateCode != null && StringUtil.isEmpty(stateCode) == false)
			c.add(Restrictions.eq("city.state.stateCode", stateCode));
		
		if(cityCode != null && StringUtil.isEmpty(cityCode) == false)
			c.add(Restrictions.eq("city.cityCode", cityCode));
		
		if(startTime != null)
			c.add(Restrictions.ge("denyTime", startTime));
		
		if(endTime != null)
			c.add(Restrictions.le("denyTime",endTime));
		
		c.add(Restrictions.eq("userType", SYSConstant.USER_TYPE_AGENT));
//		c.add(Restrictions.eq("status", SYSConstant.STATE_INVALID));
		
		ProjectionList plist = Projections.projectionList();
		
		Projection ym_p = Projections.sqlGroupProjection(
		"date_format({alias}.DENY_TIME,'%Y%m') as ym", 
		"date_format({alias}.DENY_TIME,'%Y%m')",  new String[] {"ym"},new Type[] { Hibernate.STRING });
		
		plist.add(Projections.alias(ym_p, "alias_year_month"));
		plist.add(Projections.count("userId"));
		
		c.addOrder(Order.asc("alias_year_month"));
		
		
//		plist.add(Projections.groupProperty("approveTime"));
//		plist.add(Projections.rowCount());
//		
//		c.addOrder(Order.asc("approveTime"));
		
		c.setProjection(plist);
//		c.setResultTransformer(new AliasToBeanResultTransformer(MappAcceptRange.class));
		
		List<?> result =  c.list();
		
		if(result == null || result.isEmpty())
			return null;
		
		Map<String,String> result_vo = new HashMap<String,String>(0);
		
		for(Object object : result)
		{
			Object[] values = (Object[])object;
			
			if(values.length < 2)
				continue;
			
			System.out.println(values[0]+"   "+values[1]);
			
			if(values[0] != null)
				result_vo.put(values[0].toString(), values[1] != null?values[1].toString():"0");
		}
		
		return result_vo;
	}
	
}
