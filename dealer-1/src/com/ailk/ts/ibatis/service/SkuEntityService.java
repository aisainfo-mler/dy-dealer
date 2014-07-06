package com.ailk.ts.ibatis.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.service.GoodsInfoService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.sys.dal.ibatis.model.ViewCache;
import com.ailk.butterfly.sys.service.IViewCacheService;
import com.ailk.ts.bean.SkuEntityWrapper;
import com.ailk.ts.dal.ibatis.RepDAO;
import com.ailk.ts.dal.ibatis.RepOptRecordDAO;
import com.ailk.ts.dal.ibatis.RepSellDetailDAO;
import com.ailk.ts.dal.ibatis.RepStockDetailDAO;
import com.ailk.ts.dal.ibatis.SkuEntityDAO;
import com.ailk.ts.dal.ibatis.model.ProductSku;
import com.ailk.ts.dal.ibatis.model.Rep;
import com.ailk.ts.dal.ibatis.model.RepExample;
import com.ailk.ts.dal.ibatis.model.RepExample.Criteria;
import com.ailk.ts.dal.ibatis.model.RepOptRecord;
import com.ailk.ts.dal.ibatis.model.RepSellDetail;
import com.ailk.ts.dal.ibatis.model.RepStockDetail;
import com.ailk.ts.dal.ibatis.model.Repository;
import com.ailk.ts.dal.ibatis.model.SkuEntity;
import com.ailk.ts.dal.ibatis.model.SkuEntityExample;

/**
 * @author Zhengwj
 * @version 创建时间：2013-5-13 下午04:17:57 类说明:
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class SkuEntityService{
	private static final Logger log = LoggerFactory
			.getLogger(SkuEntityService.class);

	@Autowired
	private IViewCacheService cacheService;
	@Autowired
	private SkuEntityDAO skuEntityDAO;

	@Autowired
	private RepDAO repDao;

	@Autowired
	private RepStockDetailDAO repStockDetailDao;

	@Autowired
	private RepOptRecordDAO repOptRecodDao;

	@Autowired
	private RepSellDetailService repSellDetailService;

//	@Autowired
//	private ProductSkuService productSkuService;
	@Autowired
	private GoodsInfoService goodsInfoService;

//	@Autowired
//	private SkuDAO skuDAO;

//	@Autowired
//	private OrderItemDAO orderItemDAO;

	@Autowired
	private RepSellDetailDAO repSellDetailDAO;

	
//	@Autowired
//	private IOrderService orderService;
	
	@Autowired
	private RepService repService;
	
	@Autowired
	private CommonQueryService commonQueryService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	
	public SkuEntityWrapper getSkuByImeiOrSerial(String identfier)
			throws BusinessException, SystemException {
		SkuEntity skuEntity = commonQueryService.selectSkuEntity(identfier, identfier);
		if (skuEntity == null) {
			return null;
		} else {
			SkuEntityWrapper wrapper = new SkuEntityWrapper();
			wrapper.setEntity(skuEntity);
			GoodsInfo sku = this.goodsInfoService.loadGoodsInfo(skuEntity
					.getSkuid().longValue());
			wrapper.setSkuName(sku.getName());
			return wrapper;
		}
	}

	

	public SkuEntityExample convert2Example(SkuEntity entity,
			boolean ifExactly) {

		SkuEntityExample entity_ex = new SkuEntityExample();
		SkuEntityExample.Criteria criteria = entity_ex.createCriteria();

		if (entity == null) {
			return entity_ex;
		}

		if (entity.getEntityId() != null) {
			criteria.andEntityIdEqualTo(entity.getEntityId());
		}
		
		if(entity.getRepositoryCode() != null){
			criteria.andRepositoryCodeEqualTo(entity.getRepositoryCode());
		}
		
		if(entity.getSkuid() != null){
			criteria.andSkuidEqualTo(entity.getSkuid());
		}
		if(ifExactly){
			if(StringUtils.isNotEmpty(entity.getImei())){
				criteria.andImeiEqualTo(entity.getImei());
			}
		}else{
			if(StringUtils.isNotEmpty(entity.getImei())){
				criteria.andImeiLike("%" + entity.getImei() + "%");
			}
		}
		if(entity.getTargetRepcode() != null){
			criteria.andTargetRepcodeEqualTo(entity.getTargetRepcode());
		}
		
		if(entity.getSkuid() != null){
			criteria.andSkuidEqualTo(entity.getSkuid());
		}
		
		if (StringUtils.isNotEmpty(entity.getSerial())) {
			criteria.andSerialEqualTo(entity.getSerial());
		}

		if (StringUtils.isNotEmpty(entity.getStatus())) {
			criteria.andStatusEqualTo(entity.getStatus());
		}

		return entity_ex;

	}

	public List<SkuEntity> getSkuEntityByGoodIdAndStatus(Long goodId,String status,int start, int limit)throws Exception{
		SkuEntity entity = new SkuEntity();
		entity.setSkuid(goodId);
		entity.setStatus(status);
		
		return getSkuEntity(entity, start, limit);
	}
	
	/**
	 * <p>描述: 统计sku_entity</p> 
	 * @param goodId
	 * @param status
	 * @param targetRep
	 * @return
	 * @throws Exception  
	 * @author        Zhengwj
	 * @Date          2014-7-2 下午03:55:28
	 */
	public Map<Long,Integer> countSkuEntityByGoodIdAndStatus(Long goodId,String status,Long targetRep)throws Exception{
		SkuEntity entity = new SkuEntity();
		entity.setSkuid(goodId);
		entity.setStatus(status);
		entity.setTargetRepcode(targetRep);
		List<Map> result = commonQueryService.countSkuEntity(entity);
		Map<Long,Integer> goodId_count = new HashMap<Long, Integer>();
		if(result != null && result.size() != 0){
			for(Map tmp:result){
				goodId_count.put((Long)(tmp.get("SKUID")), (Integer)(tmp.get("CC")));
			}
		}
		return goodId_count;
	}
	
	public int countSkuEntity(SkuEntity entity) throws BusinessException,
			SystemException {
		return this.countSkuEntity(entity, false);
	}

	public int countSkuEntity(SkuEntity entity,boolean ifExactly)
			throws BusinessException, SystemException {
		SkuEntityExample entity_ex = convert2Example(entity, ifExactly);
		// mode_ex.setDistinct(true);
		return skuEntityDAO.countByExample(entity_ex);
	}

	
	public List<SkuEntity> getSkuEntity(SkuEntity entity, int start, int limit) throws BusinessException, SystemException {
		SkuEntityExample entity_ex = convert2Example(entity, false);
		// mode_ex.setDistinct(true);
		if (start >= 0) {
			entity_ex.setLimitClauseCount(limit);
			entity_ex.setLimitClauseStart(start);
		}

		List<SkuEntity> list = skuEntityDAO.selectByExample(entity_ex);

		return list;
	}

	/**
	 * <p>描述: 商品状态变更 库存变更  ,如销售终端 </p> 
	 * @param targetStatus  SKU_STATUS_USER 表示销售给用户  此时一定要传targetRepcode
	 * @param entityId
	 * @param orderId
	 * @param optId
	 * @param optType
	 * @param targetRepcode
	 * @throws BusinessException
	 * @throws SystemException  
	 * @author        Zhengwj
	 * @Date          2014-7-1 下午07:27:45
	 */
	public void updateSkuEntityStatus(String targetStatus, Long entityId,
			Long orderId, Long optId, String optType,Long targetRepcode)
			throws BusinessException, SystemException {
		SkuEntity entity = skuEntityDAO.selectByPrimaryKey(entityId);
		Map<Long,Integer> goodId_count = new HashMap<Long, Integer>();
		List<Long> entityIds = new ArrayList<Long>();
		List<SkuEntity> entities = new ArrayList<SkuEntity>();
		entityIds.add(entityId);
		entities.add(entity);
		goodId_count.put(entity.getEntityId(), 1);
		updateSkuEntityStatus(targetStatus,entities, entityIds, goodId_count, orderId, optId, optType, targetRepcode);

	}
	
	/**
	 * <p>描述: 销售终端或卡</p> 
	 * @param entityId
	 * @param orderId
	 * @param userId
	 * @throws Exception  
	 * @author        Zhengwj
	 * @Date          2014-7-2 下午03:33:35
	 */
	public void saleSkuEntity(List<String> imeiList,Long orderId, Long userId)throws Exception
	{
		if(imeiList == null || imeiList.isEmpty())
		{
			log.info("no resource sale");
			return;
		}
		
		List<Repository> reps = repositoryService.getRepsByUserId(userId);
		if(reps == null || reps.size() == 0){
			throw new Exception("该代理商无仓库，请建仓库");
		}
		updateSkuEntityStatusByImeis(SYSConstant.SKU_STATUS_USER, imeiList, orderId, userId, SYSConstant.SELL_DETAIL_OPTTYPE_CHANNEL_2_USER, reps.get(0).getRepCode());
	}
	
	public SkuEntity getSkuEntityById(Long entityId)
		throws BusinessException, SystemException {
	return skuEntityDAO.selectByPrimaryKey(entityId);
	
	}
	
	/**
	 * <p>描述: </p> 
	 * @param targetStatus
	 * @param entities
	 * @param entityIds
	 * @param goodId_count
	 * @param orderId
	 * @param optId
	 * @param optType
	 * @param targetRepcode  目标仓库  代理商销售出去时，目标仓库是他自己的库，代理商确认收货时该值也要传，目标仓库也是他自己的库
	 * @throws BusinessException
	 * @throws SystemException  
	 * @author        Zhengwj
	 * @Date          2014-7-5 下午12:19:42
	 */
	private void updateSkuEntityStatus(String targetStatus,List<SkuEntity> entities,
			List<Long> entityIds,Map<Long,Integer> goodId_count, Long orderId, Long optId,
			String optType, Long targetRepcode) throws BusinessException,
			SystemException {

		if (SYSConstant.SKU_STATUS_USER.equals(targetStatus)) {//代理商销售出去时
			commonQueryService.salerSkuEntity(SYSConstant.SKU_STATUS_USER, entityIds);
			//卖出去的东西要减库存
			Set<Long> key = goodId_count.keySet();
	        for (Iterator<Long> it = key.iterator(); it.hasNext();) {
	            Long s = it.next();
	            repService.updateRepCount(s, null, targetRepcode, goodId_count.get(s));
	        }
			/**
	      //当一个渠道（或人）有多个仓库时就应该走这个方法 ,目前是登陆人一人一个仓库,代理商确认收货时只入他的 一个库
	      if(entities != null && entities.size() != 0){
	    	  for(SkuEntity entity:entities){
	  				repService.updateRepCount(entity.getSkuid(), null, entity.getTargetRepcode(), 1);
				}
	      }
			*/
			
	        
		} else {
			SkuEntity entity_new = new SkuEntity();
			entity_new.setStatus(targetStatus);
			entity_new.setModifyTime(new Timestamp(System.currentTimeMillis()));

			//如果仓库有变，则变
			if(targetRepcode != null){
//				entity_new.setTargetRepcode(targetRepcode);
				//要开始改变库存
				targetRep(entityIds, targetRepcode);//里面有对targetRepcode的变化保存
			}
				SkuEntityExample example = new SkuEntityExample();
				example.createCriteria().andEntityIdIn(entityIds);
				skuEntityDAO.updateByExampleSelective(entity_new, example);
			
			
		}

		// 创建销售记录
		for (Long entityId : entityIds) {
			repSellDetailService.createSecondSellDetail(orderId, entityId,
					optId, optType);
		}

	}
	
	/**
	 * <p>描述: </p> 
	 * @param targetStatus
	 * @param imeis
	 * @param orderId
	 * @param optId
	 * @param optType
	 * @param targetRepcode 目标仓库  代理商销售出去时，目标仓库是他自己的库，代理商确认收货时该值也要传，目标仓库也是他自己的库
	 * @throws BusinessException
	 * @throws SystemException  
	 * @author        Zhengwj
	 * @Date          2014-7-5 下午12:32:04
	 */
	public void updateSkuEntityStatusByImeis(String targetStatus,List<String> imeis, Long orderId, Long optId,String optType, Long targetRepcode) throws BusinessException,SystemException {
		if(imeis != null && imeis.size() != 0){
			SkuEntityExample example = new SkuEntityExample();
			example.createCriteria().andImeiIn(imeis);
			List<SkuEntity> entities = skuEntityDAO.selectByExample(example);
			if(entities != null && entities.size() != 0)
			{
				List<Long> itemIds = new ArrayList<Long>();
				Map<Long,Integer> skuId_count = new HashMap<Long, Integer>();
				for(SkuEntity entity:entities)
				{
					itemIds.add(entity.getEntityId());
					
					if(skuId_count.containsKey(entity.getSkuid())){
						skuId_count.put(entity.getSkuid(), skuId_count.get(entity.getSkuid()) + 1);
					}else{
						skuId_count.put(entity.getSkuid(), 1);
					}
				}
				updateSkuEntityStatus(targetStatus, entities,itemIds, skuId_count,orderId, optId, optType, targetRepcode);
			}
			
		}
		
	}
	
	// TODO 合约机不现实2G号码 需进一步确认*
//	mSelectedBusiType = CommConstant.PACKAGE_3G;
	public SkuEntityWrapper getUniqueEntityFullInfo(SkuEntity entity)
			throws BusinessException, SystemException {
		SkuEntityExample entity_ex = convert2Example(entity, true);
		List<SkuEntity> list = skuEntityDAO.selectByExample(entity_ex);

		SkuEntityWrapper dto = null;
		if (list == null || !list.isEmpty()) {
			return null;
		} else if (list.size() > 1) {
			throw new BusinessException("9999", "编码库存有重复记录");
		} else {
			dto = new SkuEntityWrapper();
			dto.setEntity(list.get(0));
			goodsInfoService.loadGoodsInfo(list.get(0).getSkuid().longValue());
		}
		return dto;
	}


	
	public SkuEntityWrapper getUniqueEntityByImei(String imei)
			throws BusinessException, SystemException {
		SkuEntityExample entity_ex = new SkuEntityExample();
		entity_ex.createCriteria().andImeiEqualTo(imei);
		List<SkuEntity> list = skuEntityDAO.selectByExample(entity_ex);

		SkuEntityWrapper dto = null;
		if (list == null || list.isEmpty()) {
			return null;
		} else if (list.size() > 1) {
			throw new BusinessException("9999", "编码库存有重复记录");
		} else {
			dto = new SkuEntityWrapper();
			dto.setEntity(list.get(0));
			GoodsInfo sku = goodsInfoService.loadGoodsInfo(list.get(0)
					.getSkuid().longValue());
			dto.setGoodInfo(sku);
			dto.setRepSellDetail(repSellDetailService
					.findLastByRepSellDetailEntityId(list.get(0).getEntityId()));
//			if (sku != null && StringUtils.isNotEmpty(sku.getSkuPropValues())) {
//				List<SkuProDto> proList = getSkuProDtos(sku.getSkuPropValues());
//				dto.setProperties(proList);
//			}
		}
		return dto;
	}



	
	public void updateSkuEntityStatus(String targetStatus, String imeiSerno,
			Long orderId, Long optId, String optType,Long targetRepCode)
			throws BusinessException, SystemException {
		SkuEntity entity = commonQueryService.selectSkuEntity(imeiSerno, imeiSerno);
		updateSkuEntityStatus(targetStatus, entity.getEntityId(), orderId,
				optId,optType,targetRepCode);

	}

	/**
	 * <p>描述:重庆代码 预留 </p> 
	 * @param entites
	 * @param optId
	 * @param orderId
	 * @throws BusinessException
	 * @throws SystemException  
	 * @author        Zhengwj
	 * @Date          2014-7-1 下午08:24:15
	 */
	public void outSkuEntites(List<SkuEntity> entites, Long optId,
			Long orderId) throws BusinessException, SystemException {
		Map skuidGroup = new HashMap();
		for (Iterator it = entites.iterator(); it.hasNext();) {
			SkuEntity se = (SkuEntity) it.next();
			Long skuid = se.getSkuid();
			// String rs = se.getRepositoryCode();
			if (skuid == null) {
				throw new BusinessException("", "商品skuid为空，请确认！");
			}
			// if(StringUtils.isBlank(rs)){
			// throw new BusinessException("","商品所属仓库为空，请确认！");
			// }
			String key = skuid + "";
			if (skuidGroup.containsKey(key)) {
				List l = (List) skuidGroup.get(key);
				l.add(se);
			} else {
				List l = new ArrayList();
				l.add(se);
				skuidGroup.put(key, l);
			}
		}
		Set keys = skuidGroup.keySet();
		for (Iterator it = keys.iterator(); it.hasNext();) {
			String key = (String) it.next();
			List ses = (List) skuidGroup.get(key);
			outSkuEntitesGroupBySkuid(ses, optId, orderId);
		}

	}

	/**
	 * @param entites
	 * @param optId
	 * @param orderId
	 * @throws BusinessException
	 * @throws SystemException 
	 */
	@SuppressWarnings("unchecked")
	private void outSkuEntitesGroupBySkuid(List<SkuEntity> entites,
			Long optId, Long orderId) throws BusinessException, SystemException {
		if (CollectionUtils.isEmpty(entites)) {
			return;
		}
		
		// 沉淀库存量操作记录
		Map repSkuIdMap = new HashMap();// 保存库存操作表的id
		Map repSkuCountMap = new HashMap();// 保存库存操作表的操作记录数
	
		Map<Long,String> orderDetailSkuMap = new HashMap<Long,String>();//detail 库存item_ids的保存
		
		for (Iterator it = entites.iterator(); it.hasNext();) {
			SkuEntity skuEntity = (SkuEntity) it.next();
			
			SkuEntity seFromDb = checkIfImeiExist(skuEntity,
					SYSConstant.SELL_DETAIL_OPTTYPE_TIBCO_2_CHANNEL);
			Long rc = seFromDb.getRepositoryCode();
			Long si = seFromDb.getSkuid();
			
			/**
			 * 记录下item_ids
			 */
			if(orderDetailSkuMap.containsKey(si) == false){
				orderDetailSkuMap.put(si, "");
			}
			String entityIds = orderDetailSkuMap.get(si);
			orderDetailSkuMap.put(si, entityIds + seFromDb.getEntityId() + "," );
			
			
			String key = rc + "~" + si;
			if (repSkuCountMap.containsKey(key)) {
				repSkuCountMap.put(key,
						((Integer) repSkuCountMap.get(key)).intValue() + 1);
			} else {
				repSkuCountMap.put(key, 1);
			}
		}
		
		
		
		Set keys = repSkuCountMap.keySet();
		for (Iterator it = keys.iterator(); it.hasNext();) {
			String key = (String) it.next();
			String repCode = key.split("~")[0];
			String skuidd = key.split("~")[1];
			int cnt = ((Integer) repSkuCountMap.get(key)).intValue();
			Long sn = recordRepOpt(Long.parseLong(skuidd), Long.parseLong(repCode), cnt,
					optId, SYSConstant.SELL_DETAIL_OPTTYPE_TIBCO_2_CHANNEL);// 返回的主键
			repSkuIdMap.put(key, sn);
		}

		// 沉淀商品出货明细，更新商品实体表
		
		/**
		 * 更新order_detail表,沉淀item_ids
		 */
		//后改
//		Map<Integer,String> orderDetailMap = new HashMap<Integer,String>();//detail 库存item_ids的保存
//		for(OrderDetail detail:details){
//			 orderDetailMap.put(detail.getOrderDetailId(), orderDetailSkuMap.get(detail.getItemSkuId()));
//		}
//		orderService.updateOrderDetail4item(orderDetailMap);
		
		
		//沉淀商品出货明细，更新商品实体表
		for (Iterator it = entites.iterator(); it.hasNext();) {
			SkuEntity skuEntity = (SkuEntity) it.next();
			// 更新商品实体表
			SkuEntity seFromDb = checkIfImeiExist(skuEntity,
					SYSConstant.SELL_DETAIL_OPTTYPE_TIBCO_2_CHANNEL);
			Long repCode = seFromDb.getRepositoryCode();
			Long entityId = seFromDb.getEntityId();
			Long skuid = seFromDb.getSkuid();
			String key = repCode + "~" + skuid;
			if (!repSkuIdMap.containsKey(key)) {
				throw new BusinessException("仓库编码：" + repCode + " skuid:"
						+ skuid + " 找不到操作流水id！");
			}
			Long optSn = (Long) repSkuIdMap.get(key);

			// 更新库存量
			Long repOptId = freshRep(skuid, repCode, 1,
					SYSConstant.SELL_DETAIL_OPTTYPE_TIBCO_2_CHANNEL);
			SkuEntity se = new SkuEntity();
			se.setEntityId(entityId);
			se.setStatus(SYSConstant.SKU_STATUS_CHANNEL);
			se.setModifyTime(new Timestamp(System.currentTimeMillis()));
			this.skuEntityDAO.updateByPrimaryKeySelective(se);
			// 沉淀出货明细
			recordOutEntity(optId, entityId, optSn, orderId);

		}
	}

	/**
	 * 出货记录
	 * 
	 * @param optId
	 * @param entityId
	 * @param serialNo
	 * @param orderId
	 */
	private void recordOutEntity(Long optId, Long entityId,
			Long serialNo, Long orderId) {
		RepSellDetail rsdd = new RepSellDetail();
		rsdd.setEntityId(entityId);
		rsdd.setOpterId(optId);
		rsdd.setOptTime(new Timestamp(System.currentTimeMillis()));
		rsdd.setOptType(SYSConstant.SELL_DETAIL_OPTTYPE_TIBCO_2_CHANNEL);
		rsdd.setOrderId(orderId);
		rsdd.setRepOptId(serialNo);
		rsdd.setOrderDomain("01");
		this.repSellDetailDAO.insert(rsdd);
	}

	@SuppressWarnings("unchecked")
	public void insertSkuEntites(List<SkuEntity> entites, Long optId)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		if (CollectionUtils.isEmpty(entites)) {
			return;
		}

		// 沉淀库存量操作记录
		Long skuid = entites.get(0).getSkuid();
		Long repCode = entites.get(0).getRepositoryCode();
		int count = entites.size();
		@SuppressWarnings("unused")
		Long serialNo = recordRepOpt(skuid, repCode, count, optId,
				SYSConstant.SELL_DETAIL_OPTTYPE_2_REP);// 返回的主键
		// 更新库存量
		Long repOptId = freshRep(skuid, repCode, count,
				SYSConstant.SELL_DETAIL_OPTTYPE_2_REP);

		// 沉淀商品进货明细，沉淀商品实体表
		for (Iterator it = entites.iterator(); it.hasNext();) {
			SkuEntity skuEntity = (SkuEntity) it.next();
			// 沉淀商品实体表
			checkIfImeiExist(skuEntity, SYSConstant.SELL_DETAIL_OPTTYPE_2_REP);
			this.skuEntityDAO.insert(skuEntity);
			Long entityId = skuEntity.getEntityId();
			// 沉淀进货明细
			recoderRepStockDetail(optId, serialNo, entityId);

		}
	}

	/**
	 * 验证skuid下imei是否已经存在。出库的话返回SkuEntity，入库的话返回值没有意义
	 * 
	 * @param skuEntity
	 * @param optType
	 * @return entityId
	 * @throws BusinessException
	 */
	private SkuEntity checkIfImeiExist(SkuEntity skuEntity, String optType)
			throws BusinessException {
		SkuEntityExample see = new SkuEntityExample();
		com.ailk.ts.dal.ibatis.model.SkuEntityExample.Criteria cc = see
				.createCriteria();
		cc.andImeiEqualTo(skuEntity.getImei());
		cc.andSkuidEqualTo(skuEntity.getSkuid());
		List<SkuEntity> skuEntites = this.skuEntityDAO.selectByExample(see);
		int cbe = skuEntites.size();
		if (StringUtils.equals(SYSConstant.SELL_DETAIL_OPTTYPE_2_REP, optType)) {
			// 入库
			if (cbe > 0)
				throw new BusinessException("", "IMEI:" + skuEntity.getImei()
						+ "  已经存在！");
			else
				return null;

		} else {
			// 出库
			if (cbe != 1)
				throw new BusinessException("", "IMEI:" + skuEntity.getImei()
						+ "  不存在，或者存在多条！");
			else {
				if (!SYSConstant.SKU_STATUS_TIBCO.equals(skuEntites.get(0)
						.getStatus())) {
					// 出库的时候，状态不是1:在平台库。报错
					throw new BusinessException("", "IMEI:"
							+ skuEntity.getImei() + "  状态不正确，应该为01 在平台库，实际上为"
							+ skuEntites.get(0).getStatus() + " 在代理商库");
				}
				return skuEntites.get(0);
			}
		}
	}

	/**
	 * 沉淀商品进货明细
	 * 
	 * @param optId
	 *            操作员id
	 * @param repOptId
	 *            库存量操作id
	 * @param entityId
	 *            商品实体id
	 */
	private void recoderRepStockDetail(Long optId, Long repOptId,
			Long entityId) {
		RepStockDetail rsd = new RepStockDetail();
		rsd.setEntityId(entityId);
		rsd.setOpterId(optId);
		rsd.setOptTime(new Timestamp(System.currentTimeMillis()));
		rsd.setOptType(SYSConstant.SELL_DETAIL_OPTTYPE_2_REP);
		rsd.setRepOptId(repOptId);
		this.repStockDetailDao.insert(rsd);
	}

	/**
	 * 刷新库存表
	 * 
	 * @param skuId
	 * @param repCode
	 *            仓库编码
	 * @param count
	 *            操作数量
	 * @param optType
	 *            操作类型
	 * @return
	 * @throws BusinessException
	 */
	private Long freshRep(Long skuId, Long repCode, int count, String optType)
			throws BusinessException {
		RepExample re = new RepExample();
		Criteria cri = re.createCriteria();
		cri.andSkuidEqualTo(skuId);
		cri.andRepositoryCodeEqualTo(repCode);
		List<Rep> result = this.repDao.selectByExample(re);
		if (result != null && result.size() > 0) {
			Rep rep = result.get(0);
			int resultCount = 0;
			if (StringUtils.equals(optType,
					SYSConstant.SELL_DETAIL_OPTTYPE_2_REP)) {
				// 入库
				resultCount = rep.getCount() + count;
			} else {
				// 出库
				resultCount = rep.getCount() - count;
				if (resultCount < 0) {
					throw new BusinessException("", "出库失败！出库数量：" + count
							+ "，实际库存量为：" + rep.getCount());
				}
			}

			rep.setCount(resultCount);
			this.repDao.updateByPrimaryKey(rep);
			return rep.getId();
		} else {
			if (StringUtils.equals(optType,
					SYSConstant.SELL_DETAIL_OPTTYPE_2_REP)) {
				// 入库
				Rep rep = new Rep();
				rep.setCount(count);
				rep.setRepositoryCode(repCode);
				rep.setSkuid(skuId);
				this.repDao.insert(rep);
				return rep.getId();
			} else {
				throw new BusinessException("", "出库失败！库存量表中没有记录，请确认商品是否已入库！");
			}
		}
	}

	/**
	 * 库存量操作记录沉淀。返回库存量操作流水ID
	 * 
	 * @param skuId
	 * @param repCode
	 * @param count
	 * @param optId
	 * @param optType
	 * @return
	 */
	private Long recordRepOpt(Long skuId, Long repCode, int count,
			Long optId, String optType) {
		RepOptRecord record = new RepOptRecord();
		record.setCount(count);
		record.setCreateTime(new Timestamp(System.currentTimeMillis()));
		if (StringUtils.equals(optType, SYSConstant.SELL_DETAIL_OPTTYPE_2_REP)) {
			// 入库
			record.setInputRepCode(repCode);
		} else {
			// 出库
			record.setOutputRepCode(repCode);
		}
		record.setOptId(optId);
		record.setSkuid(skuId);
		// record.setOptType(SysConstant.SELL_DETAIL_OPTTYPE_2_REP);
		record.setOptType(optType);
		repOptRecodDao.insert(record);
		return record.getSerialNo();
	}

	public void targetRep(List<Long> entityIds, Long inRepCode) throws BusinessException, SystemException {
		SkuEntityExample entity_ex = new SkuEntityExample();
		entity_ex.createCriteria().andEntityIdIn(entityIds);
		List<SkuEntity> entities = skuEntityDAO.selectByExample(entity_ex);
		
		if(entities != null && entities.size() != 0 ){
			Map<String,Integer> sku_rep = new HashMap<String,Integer>();
			for(SkuEntity entity:entities){
				if(StringUtils.equals(entity.getStatus(), SYSConstant.SKU_STATUS_USER)
//						|| StringUtils.equals(entity.getStatus(), SYSConstant.SKU_STATUS_TIBCO)
				)
				{
					throw new BusinessException("9999","商品库存状态已发生变更，无法确认到代理商库");
				}
				if(sku_rep.containsKey(entity.getSkuid() + "_" + entity.getRepositoryCode()) == false){
					sku_rep.put(entity.getSkuid() + "_" + entity.getRepositoryCode(), 1);
				}else{
					Integer count = sku_rep.get(entity.getSkuid() + "_" + entity.getRepositoryCode());
					sku_rep.put(entity.getSkuid() + "_" + entity.getRepositoryCode(), count + 1);
				}
			}
			
			Set<String> key = sku_rep.keySet();
	        for (Iterator<String> it = key.iterator(); it.hasNext();) {
	        	String k = it.next();
	        	String[] skuId_repCode = k.split("_");
	        	Long skuId = Long.parseLong(skuId_repCode[0]);
	            String outRepCode = skuId_repCode[1];
//	            System.out.println(skuId);
//	            System.out.println(outRepCode);
//	            System.out.println(sku_rep.get(outRepCode));
	            repService.updateRepCount(skuId, inRepCode, Long.parseLong(outRepCode), sku_rep.get(k));//代理商入库
	        }
	        
	        SkuEntity record = new SkuEntity();
	        record.setTargetRepcode(inRepCode);
	        skuEntityDAO.updateByExampleSelective(record, entity_ex);
		}
		
	}

	/**
	 * <p>描述:确认产品是否在代理商库</p> 
	 * @return  
	 * @author        Zhengwj
	 * @Date          2014-7-1 下午08:26:12
	 */
	public Map<String,Object> checkAgentImeiExist(String imei,Long agentId)throws BusinessException, SystemException {
		SkuEntityWrapper sku = getSkuByImeiOrSerial(imei);
		Map<String,Object> result = new HashMap<String, Object>();
		if(sku == null){
			result.put("ifExist", false);
			result.put("msg", "无产品信息");
		}else{
			List<Repository> reps = repositoryService.getRepsByUserId(agentId);
			if(reps == null || reps.size() == 0){
				result.put("ifExist", false);
				result.put("msg", "无代理商仓库信息");
			}else{
				for(Repository rep:reps){
					if(rep.getRepCode() == sku.getEntity().getTargetRepcode()){
						result.put("ifExist", true);
						result.put("msg", "");
						break;
					}
				}
				if(result.containsKey("ifExist") == false){
					result.put("ifExist", false);
					result.put("msg", "该产品还存在于TIBCO平台库");//默认在平台库,暂未有二级，可后改
				}
			}
			
		}
		return result;
		
	}


	public IViewCacheService getCacheService() {
		return cacheService;
	}


	public void setCacheService(IViewCacheService cacheService) {
		this.cacheService = cacheService;
	}


	public SkuEntityDAO getSkuEntityDAO() {
		return skuEntityDAO;
	}


	public void setSkuEntityDAO(SkuEntityDAO skuEntityDAO) {
		this.skuEntityDAO = skuEntityDAO;
	}


	public RepDAO getRepDao() {
		return repDao;
	}


	public void setRepDao(RepDAO repDao) {
		this.repDao = repDao;
	}


	public RepStockDetailDAO getRepStockDetailDao() {
		return repStockDetailDao;
	}


	public void setRepStockDetailDao(RepStockDetailDAO repStockDetailDao) {
		this.repStockDetailDao = repStockDetailDao;
	}


	public RepOptRecordDAO getRepOptRecodDao() {
		return repOptRecodDao;
	}


	public void setRepOptRecodDao(RepOptRecordDAO repOptRecodDao) {
		this.repOptRecodDao = repOptRecodDao;
	}


	public RepSellDetailService getRepSellDetailService() {
		return repSellDetailService;
	}


	public void setRepSellDetailService(RepSellDetailService repSellDetailService) {
		this.repSellDetailService = repSellDetailService;
	}


	public GoodsInfoService getGoodsInfoService() {
		return goodsInfoService;
	}


	public void setGoodsInfoService(GoodsInfoService goodsInfoService) {
		this.goodsInfoService = goodsInfoService;
	}


	public RepSellDetailDAO getRepSellDetailDAO() {
		return repSellDetailDAO;
	}


	public void setRepSellDetailDAO(RepSellDetailDAO repSellDetailDAO) {
		this.repSellDetailDAO = repSellDetailDAO;
	}


	public RepService getRepService() {
		return repService;
	}


	public void setRepService(RepService repService) {
		this.repService = repService;
	}


	public CommonQueryService getCommonQueryService() {
		return commonQueryService;
	}


	public void setCommonQueryService(CommonQueryService commonQueryService) {
		this.commonQueryService = commonQueryService;
	}


	public RepositoryService getRepositoryService() {
		return repositoryService;
	}


	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

}
