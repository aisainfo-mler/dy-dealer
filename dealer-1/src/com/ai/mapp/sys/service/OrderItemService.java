package com.ai.mapp.sys.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.sys.dao.OrderItemDao;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.entity.OrderDetail;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.OrderItem;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;
import com.ai.mapp.sys.entity.User;
import com.ailk.ts.dal.ibatis.SkuEntityDAO;
import com.ailk.ts.dal.ibatis.model.SkuEntity;
import com.ailk.ts.ibatis.service.SkuEntityService;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-19 下午09:28:22
 * 类说明:
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class OrderItemService {
	public final Log log = LogFactory.getLog(OrderItemService.class);
	
	@Autowired
	private OrderItemDao orderItemDao;
	
	@Autowired
	private SkuEntityService skuEntityService;
	
	public OrderItemDao getOrderItemDao() {
		return orderItemDao;
	}

	public void setOrderItemDao(OrderItemDao orderItemDao) {
		this.orderItemDao = orderItemDao;
	}

	public SkuEntityService getSkuEntityService() {
		return skuEntityService;
	}

	public void setSkuEntityService(SkuEntityService skuEntityService) {
		this.skuEntityService = skuEntityService;
	}

	public Collection<OrderItem> listOrderItems(OrderItem orderItem,int start,int limit)throws Exception{
		try{
			log.debug(orderItem==null?"orderItem is null":orderItem.toString());
			
			Collection<OrderItem> c = null;
			if(start < 0){
				c = orderItemDao.listAll(orderItem);
			}else{
				c = orderItemDao.list(orderItem, start, limit);
			}
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
//			return null;
		}
	}
	
	public void saveOrderItem(OrderItem orderItem)throws Exception{
		log.debug(orderItem==null?"orderItem is null":orderItem.toString());
		orderItemDao.save(orderItem);
	}
	
	public void deleteOrderItem(OrderItem orderItem)throws Exception{
		log.debug(orderItem==null?"orderItem is null":orderItem.toString());
		orderItemDao.delete(orderItem);
	}
	
	public int countOrderItem(OrderItem orderItem) throws Exception{
		return orderItemDao.count(orderItem);
	}
	
	public OrderItem loadOrderItem(Long id)throws Exception{
		return orderItemDao.get(id);
	}
	
	/**
	 * <p>获得所有values 用,;隔开  方便页面增加校验是否重复</p> 
	 * @param:        @param items
	 * @param:        @return    
	 * @return:       String    
	 */
	public Map<String,Object> getItemValuesNoBatch(OrderItem condition)throws Exception{
		condition.setFrom("!" + SYSConstant.ITEM_FROM_BATCH);//非批量上传的
		condition.setGroupBy(null);//取消排组信息
		List<OrderItem> orderItems = (List<OrderItem>)listOrderItems(condition, -1, 0);
		if(orderItems == null || orderItems.size() == 0){
			return null;
		}else{
			String hiddenValues = "";
			List<String> frontValues = new ArrayList<String>();
			Map<String,Object> result = new HashMap<String,Object>();
			for(OrderItem item:orderItems){
				if(!StringUtil.isEmpty(item.getItemValue())){
					if(!StringUtil.isEmpty(item.getFrom())){
						hiddenValues += ",;_" + item.getFrom() + "_" + item.getItemValue();
					}else{
						hiddenValues += ",;__" + item.getItemValue();
					}
					frontValues.add(item.getItemValue());
				}
			}
			result.put("hiddenValues", hiddenValues);
			result.put("frontValues", frontValues);
			result.put("hadNum", orderItems.size());
			return result;
		}
	}
	
	/**
	 * <p>获得批量的values</p> 
	 * @param:        @param items
	 * @param:        @return    
	 * @return:       String    
	 */
	public Map<String,Object> getItemValuesBatch(OrderItem condition)throws Exception{
		condition.setFrom(SYSConstant.ITEM_FROM_BATCH);
		condition.setGroupBy("batchGroup");//此时只会取到batchGroup的信息
		Collection orderItems = listOrderItems(condition, -1, 0);
		if(orderItems == null || orderItems.size() == 0){
			return null;
		}else{
			String hiddenValues = "";
			List<String> frontValues = new ArrayList<String>();
			Map<String,Object> result = new HashMap<String,Object>();
			String[] tmpArr = null;
			int hadNum = 0;
			for(Object item:orderItems){//实为String  格式
				if(item != null && !StringUtil.isEmpty(item.toString())){
					hiddenValues += item + ";";
					tmpArr = item.toString().split("--");
					if(tmpArr != null && tmpArr.length == 3){
						frontValues.add(tmpArr[0] + "--" + tmpArr[1] + "(" + tmpArr[2] + ")");
						hadNum += Integer.parseInt(tmpArr[2]);
					}
				}
				
			}
			result.put("hiddenValues", hiddenValues);
			result.put("frontValues", frontValues);
			result.put("hadNum", hadNum);
			return result;
		}
	}
	
	public Map<String,Object> getItemsInfo(OrderItem condition)throws Exception{
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> result1 = getItemValuesNoBatch(condition);
		Map<String,Object> result2 = getItemValuesBatch(condition);
		List<String> frontValues = null;
		int hadNum = 0;
		
		if(result2 != null && !result2.isEmpty()){
			String orderItemBatchValues = (String) result2.get("hiddenValues");
			hadNum = (Integer)result2.get("hadNum");
			result.put("orderItemBatchValues", orderItemBatchValues);
			frontValues = (List<String>) result2.get("frontValues");
		}
		if(result1 != null && !result1.isEmpty()){
			String orderItemValues = (String) result1.get("hiddenValues");
			hadNum += ((Integer)result1.get("hadNum"));
			result.put("orderItemValues", orderItemValues);
			
			if(frontValues == null){
				frontValues = (List<String>) result1.get("frontValues");
			}else{
				frontValues.addAll((List<String>) result1.get("frontValues"));
			}
		}
		result.put("frontValues", frontValues);
		result.put("hadNum", hadNum);
		
		return result;
	}
	
	/**
	 * <p>根据item的value字符串保存orderItem信息 </p> 
	 * @param:        @throws Exception    
	 * @return:       void    
	 */
	public void saveItemsByValueString(OrderDetail detail,String itemValues,String orderItemBatchValues,User operator)throws Exception{
		if(StringUtil.isEmpty(itemValues)&&StringUtil.isEmpty(orderItemBatchValues)){
			return;
		}
		//先删除原先的关系 
		orderItemDao.deleteOrderItemByDetailId(detail.getId());
		//
//		String[] valueArr = itemValues.split(",;_\\d?_");
		if(!StringUtil.isEmpty(itemValues)){
			String[] valueArr = itemValues.split(",;_");
			OrderItem item = null;
			SkuEntity entity = null;
			List<SkuEntity> entities = new ArrayList<SkuEntity>();
			String[] tmpArr = null;
			for(String value:valueArr){
				if(!StringUtil.isEmpty(value)){
					item = new OrderItem();
					entity = new SkuEntity();
				
					item.setCreateTime(new Date());
					item.setStatus(SYSConstant.ITEM_STATUS_TEMP);//先临时 ，后发货中,等客户收到货后才置为未使用,开户成功后才为使用中
					item.setDetail(detail);
					item.setAgent(new User(detail.getAgentId()));
					if(value.indexOf("_") != -1){
						tmpArr = value.split("_");
						item.setItemValue(tmpArr[1]);
						entity.setImei(tmpArr[1]);
						if(!StringUtil.isEmpty(tmpArr[0])){
							item.setFrom(tmpArr[0]);//0:scan  1:manual  2:batch
						}
					}else{
						item.setItemValue(value);
						entity.setImei(value);
					}
					item.setGood(detail.getGood());
					if(operator != null){
						item.setOperator(operator);
					}
					saveOrderItem(item);
					
					entity.setModifyTime(com.ailk.butterfly.core.util.DateUtils.getCurrent());
					entity.setRepositoryCode(SYSConstant.REP_CODE_TIBCO);//目前存在于TIBCO仓库,始于TIBCO仓库
//					entity.setTargetRepcode(SYSConstant.REP_CODE_TIBCO);//目前存在于TIBCO仓库,始于TIBCO仓库
					entity.setSkuid(detail.getGood().getId());
					entity.setStatus(SYSConstant.SKU_STATUS_TIBCO);
//					entity.setOper
					entities.add(entity);
					
				}
				skuEntityService.insertSkuEntites(entities, operator.getUserId());
			}
		}
		if(!StringUtil.isEmpty(orderItemBatchValues)){
			
			saveItemValuesByBatch(detail,orderItemBatchValues,operator);
		}
	}
	
	public long countItem(String userCode,String goodType,String status) throws Exception
	{
		OrderItem condition = new OrderItem();
		condition.setGood(new GoodsInfo());
		condition.getGood().setType(goodType);
		condition.setStatus(status);
		condition.setOperator(new User(userCode));
		
		return countOrderItem(condition);
	}
	
	public long countItem(String userCode,Collection<Long> goodIds,String status) throws Exception
	{
		OrderItem condition = new OrderItem();
		condition.setGood(new GoodsInfo());
		condition.getGood().setGoodIds(goodIds);
		condition.setStatus(status);
		condition.setOperator(new User(userCode));
		
		return countOrderItem(condition);
	}
	
	public OrderItem loadOrderItemByValue(String itemValue) throws Exception
	{
		OrderItem condition = new OrderItem();
		condition.setItemValue(itemValue);
		
		Collection<OrderItem> items = listOrderItems(condition, 0, 1);
		
		if(items == null || items.isEmpty())
			return null;
		
		return items.iterator().next();
					
		
	}
	
	public Map<Long,Long> countGood(String userCode,String goodType,Long goodId,String status)
	{
		return orderItemDao.countGood(userCode, goodType,goodId, status);
	}
	
	public OrderItem loadOrderItem(Long userId,String goodType,String value) throws Exception
	{
		if(StringUtil.isEmpty(goodType) || StringUtil.isEmpty(value) || userId == null)
			throw new Exception(LanguageInfo.PARAMETER_IS_NOT_ENOUGH);
		OrderItem condition = new OrderItem();
		if(StringUtil.isEmpty(goodType) == false)
		{
			GoodsInfo good = new GoodsInfo();
			good.setType(goodType);
			condition.setGood(good);
		}
		condition.setItemValue(value);
		condition.setOperator(new User(userId));
		
		Collection<OrderItem> items = listOrderItems(condition, 0, 1);
		
		if(items == null || items.isEmpty())
			return null;
		
		return items.iterator().next();
		
	}
	
	/**
	 * 使用Item
	 * @param userCode
	 * @param goodType
	 * @param value
	 * @throws Exception
	 */
	public void useItem(Long userId,String goodType,String value) throws Exception
	{
		
		if(StringUtil.isEmpty(goodType) || StringUtil.isEmpty(value))
			return;
		
		OrderItem item = loadOrderItem(userId,goodType, value);
		if(item != null)
		{
			if(SYSConstant.ITEM_STATUS_UNUSE.equals(item.getStatus())  == false)
				throw new Exception(item.getItemValue() + " " + LanguageInfo.ITEM_STATUS_ERROR);
			item.setStatus(SYSConstant.ITEM_STATUS_USED);
			saveOrderItem(item);
		}
	}
	
	/**
	 * 重置Item,使有效
	 * @param userCode
	 * @param goodType
	 * @param value
	 * @throws Exception
	 */
	public void resetItem(Long userId,String goodType,String value) throws Exception
	{
		
		if(StringUtil.isEmpty(goodType) || StringUtil.isEmpty(value))
			return;
		
		OrderItem item = loadOrderItem(userId,goodType, value);
		if(item != null)
		{
			if(SYSConstant.ITEM_STATUS_USED.equals(item.getStatus())  == false)
				throw new Exception(item.getItemValue()+" " + LanguageInfo.ITEM_HAD_USED);
			item.setStatus(SYSConstant.ITEM_STATUS_UNUSE);
			saveOrderItem(item);
		}
	}
	
	/**
	 * 使用Item
	 * @param userCode
	 * @param goodType
	 * @param value
	 * @throws Exception
	 */
	public void useItemTemp(Long userId,String goodType,String value) throws Exception
	{
		
		if(StringUtil.isEmpty(goodType) || StringUtil.isEmpty(value))
			return;
		
		OrderItem item = loadOrderItem(userId,goodType, value);
		if(item != null)
		{
//			if(SYSConstant.ITEM_STATUS_UNUSE.equals(item.getStatus())  == false)
//				throw new Exception(item.getItemValue() + " " + LanguageInfo.ITEM_STATUS_ERROR);
			if(!StringUtils.equals(item.getStatus(), SYSConstant.ITEM_STATUS_UNUSE)){
				throw new Exception(value+":"+LanguageInfo.ITEM_ERR_STATUS_NOT_UNUSE);
			}
			item.setStatus(SYSConstant.ITEM_STATUS_TEMP);
			item.setTempEffTime(DateUtils.addMinutes(new Date(), 30));
			saveOrderItem(item);
		}else{
			throw new Exception(value+":"+LanguageInfo.ITEM_ERR_NOT_EXIST);
		}
	}
	/** 收货Item **/
	public void setOrderItemHasReceived(String orderCode) throws Exception
	{
		OrderItem condition = new OrderItem();
		condition.setStatus(SYSConstant.ITEM_STATUS_SENDING);
		orderItemDao.setOrderItemStatus(orderCode, SYSConstant.ITEM_STATUS_UNUSE,condition);
		
	}
	
	public void updateStatusByDetailId(Long detailId,String status)throws Exception{
		if(detailId == null || StringUtil.isEmpty(status)){
			return;
		}else{
			orderItemDao.updateOrderItemStatusByDetailId(detailId,status);
		}
	}
	
	public void resetAllTemp(){
		orderItemDao.resetAllTemp();
	}
	
	
	/**
	 * <p>描述: </p> 
	 * @param:        @param detail
	 * @param:        @param batchString  00001--00010--10  from--end--total
	 * @param:        @param operator
	 * @param:        @throws Exception    
	 * @return:       void    
	 * @author        Zhengwj
	 * @Date          2012-12-5 下午09:37:11
	 */
	public void saveItemValuesByBatch(OrderDetail detail,String batchString,User operator)throws Exception{
		if(StringUtil.isEmpty(batchString)){
			return;
		}
		String[] remarkArr = batchString.split(";");
		String[] fromEndNumArr = null;
		SkuEntity entity = null;
		List<SkuEntity> entities = new ArrayList<SkuEntity>();
		OrderItem item = null;
		int i = 0;
		for(String unitRemark:remarkArr){
			i++;
			if(StringUtil.isEmpty(unitRemark)){
				continue;
			}else{
				fromEndNumArr = unitRemark.split("--");
				if(fromEndNumArr != null && fromEndNumArr.length >= 2){
					
					String valueArr[] = StringUtil.getUnitNumString(fromEndNumArr[0],fromEndNumArr[1],true);
					if(valueArr != null){
						for(String value:valueArr){
							item = new OrderItem();
							item.setCreateTime(new Date());
							item.setStatus(SYSConstant.ITEM_STATUS_TEMP);//先临时 ，后发货中,等客户收到货后才置为未使用,开户成功后才为使用中
							item.setFrom(SYSConstant.ITEM_FROM_BATCH);
							item.setBatchGroup(unitRemark);
//							item.setBatchGroup(i);
							item.setDetail(detail);
							item.setAgent(new User(detail.getAgentId()));
							item.setItemValue(value);
							item.setGood(detail.getGood());
							if(operator != null){
								item.setOperator(operator);
							}
							saveOrderItem(item);
							
							entity = new SkuEntity();
							entity.setImei(value);
							entity.setModifyTime(com.ailk.butterfly.core.util.DateUtils.getCurrent());
							entity.setRepositoryCode(SYSConstant.REP_CODE_TIBCO);//目前存在于TIBCO仓库,始于TIBCO仓库
//							entity.setTargetRepcode(SYSConstant.REP_CODE_TIBCO);//目前存在于TIBCO仓库,始于TIBCO仓库
							entity.setSkuid(detail.getGood().getId());
							entity.setStatus(SYSConstant.SKU_STATUS_TIBCO);
//							entity.setOper
							entities.add(entity);

						}
					}
				}
				
			}
		}
		skuEntityService.insertSkuEntites(entities, operator.getUserId());
	}
	
}
