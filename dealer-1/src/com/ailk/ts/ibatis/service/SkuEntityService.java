package com.ailk.ts.ibatis.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

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
import com.ailk.ts.dal.ibatis.model.RepOptRecord;
import com.ailk.ts.dal.ibatis.model.RepSellDetail;
import com.ailk.ts.dal.ibatis.model.RepStockDetail;
import com.ailk.ts.dal.ibatis.model.SkuEntity;
import com.ailk.ts.dal.ibatis.model.SkuEntityExample;
import com.ailk.ts.dal.ibatis.model.RepExample.Criteria;

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

	
	public SkuEntityExample convert2Example(SkuEntity entity, boolean ifExactly) {
		return this.convert2ExampleWithParam(entity, ifExactly, false);
	}

	public SkuEntityExample convert2ExampleWithParam(SkuEntity entity,
			boolean ifExactly, boolean isOnlyForCard) {

		SkuEntityExample entity_ex = new SkuEntityExample();
		SkuEntityExample.Criteria criteria = entity_ex.createCriteria();

		if (entity == null) {
			return entity_ex;
		}

		if (entity.getEntityId() != null) {
			criteria.andEntityIdEqualTo(entity.getEntityId());
		}
		
		if(StringUtils.isNotEmpty(entity.getRepositoryCode())){
			criteria.andRepositoryCodeEqualTo(entity.getRepositoryCode());
		}
		
		if(entity.getSkuid() != null){
			criteria.andSkuidEqualTo(entity.getSkuid());
		}
		
		if(StringUtils.isNotEmpty(entity.getRepositoryCode())){
			criteria.andRepositoryCodeEqualTo(entity.getRepositoryCode());
		}
		
		if(StringUtils.isNotEmpty(entity.getTargetRepcode())){
			criteria.andTargetRepcodeEqualTo(entity.getTargetRepcode());
		}
		
		if(entity.getSkuid() != null){
			criteria.andSkuidEqualTo(entity.getSkuid());
		}
		if (isOnlyForCard == false) {
			// 查询一般的商品时
			if (entity.getSkuid() != null) {
				criteria.andSkuidEqualTo(entity.getSkuid());
			}
		} else {
			// 查询卡时，如果没有传skuid，则查询出全部的白卡+成卡
			if (entity.getSkuid() != null) {
				criteria.andSkuidEqualTo(entity.getSkuid());
			} else {
				List<ViewCache> cardTypes = this.cacheService
						.findCacheByKey("CARD_TYPE");
				List<Integer> skuIds = new ArrayList();
				for (Iterator it = cardTypes.iterator(); it.hasNext();) {
					ViewCache vc = (ViewCache) it.next();
					skuIds.add(Integer.parseInt(vc.getpValue()));
				}
				criteria.andSkuidIn(skuIds);
			}

		}

		if (StringUtils.isNotEmpty(entity.getRepositoryCode())) {
			if (ifExactly) {
				criteria.andRepositoryCodeEqualTo(entity.getRepositoryCode());
			} else {
				criteria.andRepositoryCodeLike("%" + entity.getRepositoryCode()
						+ "%");
			}
		}
		if (isOnlyForCard == false) {
			// 查询一般的商品
			if (StringUtils.isNotEmpty(entity.getImei())) {
				criteria.andImeiEqualTo(entity.getImei());
			}
		} else {
			// 查询卡
			if (StringUtils.isNotEmpty(entity.getImei())) {
				criteria.andImeiLike("%" + entity.getImei() + "%");
			}
		}

		if (StringUtils.isNotEmpty(entity.getSerial())) {
			criteria.andSerialEqualTo(entity.getSerial());
		}

		if (StringUtils.isNotEmpty(entity.getStatus())) {
			criteria.andStatusEqualTo(entity.getStatus());
		}

		return entity_ex;

	}

	
	public int countSkuEntity(SkuEntity entity) throws BusinessException,
			SystemException {
		return this.countSkuEntity(entity, false);
	}

	public int countSkuEntity(SkuEntity entity, boolean onlyForCard)
			throws BusinessException, SystemException {
		SkuEntityExample entity_ex = convert2ExampleWithParam(entity, false,
				onlyForCard);
		// mode_ex.setDistinct(true);
		return skuEntityDAO.countByExample(entity_ex);
	}

	
	public List<SkuEntity> getSkuEntity(SkuEntity entity, int start, int limit)
			throws BusinessException, SystemException {
		return this.getSkuEntity(entity, start, limit, false);
	}

	
	public List<SkuEntity> getSkuEntity(SkuEntity entity, int start, int limit,
			boolean onlyForCard) throws BusinessException, SystemException {
		SkuEntityExample entity_ex = convert2ExampleWithParam(entity, false,
				onlyForCard);
		// mode_ex.setDistinct(true);
		if (start >= 0) {
			entity_ex.setLimitClauseCount(limit);
			entity_ex.setLimitClauseStart(start);
		}

		List<SkuEntity> list = skuEntityDAO.selectByExample(entity_ex);

		return list;
	}

	
	public void updateSkuEntityStatus(String targetStatus, Integer entityId,
			Integer orderId, Integer optId, String optType)
			throws BusinessException, SystemException {
		SkuEntity entity_new = new SkuEntity();
		entity_new.setStatus(targetStatus);

		SkuEntityExample example = new SkuEntityExample();
		example.createCriteria().andEntityIdEqualTo(entityId);
		if (SYSConstant.SKU_STATUS_USER.equals(targetStatus)) {
			GoodsInfo product = goodsInfoService.loadGoodsInfo(getSkuEntityById(
					entityId).getSkuid().longValue());
			Integer months = product.getServiceMonth();// 获得维保时间 单位月
			Timestamp now = new Timestamp(System.currentTimeMillis());
			entity_new.setModifyTime(now);
			entity_new.setServiceStart(now);
			if (months != null) {
				entity_new.setServiceEnd(DateUtils.addMonth(now, months));
			}
		}
		skuEntityDAO.updateByExampleSelective(entity_new, example);
		if (!SYSConstant.SELL_DETAIL_OPTTYPE_USER_2_CHANNEL
						.equals(optType)) {
			repSellDetailService.createSecondSellDetail(orderId, entityId,
					optId, optType);
		}

	}
	
	public SkuEntity getSkuEntityById(Integer entityId)
		throws BusinessException, SystemException {
	return skuEntityDAO.selectByPrimaryKey(entityId);
	
	}
	
	public void updateSkuEntityStatus(String targetStatus,
			List<Integer> entityIds, Integer orderId, Integer optId,
			String optType, String orderDomain) throws BusinessException,
			SystemException {

		if (SYSConstant.SKU_STATUS_USER.equals(targetStatus)) {
		//----------后改
			//orderItemDAO.salerSkuEntity(SYSConstant.SKU_STATUS_USER, entityIds);
		} else {
			SkuEntity entity_new = new SkuEntity();
			entity_new.setStatus(targetStatus);
			entity_new.setModifyTime(new Timestamp(System.currentTimeMillis()));

			SkuEntityExample example = new SkuEntityExample();
			example.createCriteria().andEntityIdIn(entityIds);
			skuEntityDAO.updateByExampleSelective(entity_new, example);
		}

		// 创建销售记录
//		if (SysConstant.ORDER_DOMAIN_FLOW.equals(orderDomain)) {
			for (Integer entityId : entityIds) {
				repSellDetailService.createSecondSellDetail(orderId, entityId,
						optId, optType);
			}
//		}

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
			Integer orderId, Integer optId, String optType)
			throws BusinessException, SystemException {
		SkuEntity entity = commonQueryService.selectSkuEntity(imeiSerno, imeiSerno);
		updateSkuEntityStatus(targetStatus, entity.getEntityId(), orderId,
				optId, optType);

	}

	
	public void outSkuEntites(List<SkuEntity> entites, Integer optId,
			Integer orderId) throws BusinessException, SystemException {
		Map skuidGroup = new HashMap();
		for (Iterator it = entites.iterator(); it.hasNext();) {
			SkuEntity se = (SkuEntity) it.next();
			Integer skuid = se.getSkuid();
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
			Integer optId, Integer orderId) throws BusinessException, SystemException {
		if (CollectionUtils.isEmpty(entites)) {
			return;
		}
		
		// 沉淀库存量操作记录
		Map repSkuIdMap = new HashMap();// 保存库存操作表的id
		Map repSkuCountMap = new HashMap();// 保存库存操作表的操作记录数
	
		Map<Integer,String> orderDetailSkuMap = new HashMap<Integer,String>();//detail 库存item_ids的保存
		
		for (Iterator it = entites.iterator(); it.hasNext();) {
			SkuEntity skuEntity = (SkuEntity) it.next();
			
			SkuEntity seFromDb = checkIfImeiExist(skuEntity,
					SYSConstant.SELL_DETAIL_OPTTYPE_OSOONS_2_CHANNEL);
			String rc = seFromDb.getRepositoryCode();
			Integer si = seFromDb.getSkuid();
			
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
			Integer sn = recordRepOpt(Integer.parseInt(skuidd), repCode, cnt,
					optId, SYSConstant.SELL_DETAIL_OPTTYPE_OSOONS_2_CHANNEL);// 返回的主键
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
					SYSConstant.SELL_DETAIL_OPTTYPE_OSOONS_2_CHANNEL);
			String repCode = seFromDb.getRepositoryCode();
			Integer entityId = seFromDb.getEntityId();
			int skuid = seFromDb.getSkuid();
			String key = repCode + "~" + skuid;
			if (!repSkuIdMap.containsKey(key)) {
				throw new BusinessException("仓库编码：" + repCode + " skuid:"
						+ skuid + " 找不到操作流水id！");
			}
			int optSn = (Integer) repSkuIdMap.get(key);

			// 更新库存量
			int repOptId = freshRep(skuid, repCode, 1,
					SYSConstant.SELL_DETAIL_OPTTYPE_OSOONS_2_CHANNEL);
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
	private void recordOutEntity(Integer optId, Integer entityId,
			Integer serialNo, Integer orderId) {
		RepSellDetail rsdd = new RepSellDetail();
		rsdd.setEntityId(entityId);
		rsdd.setOpterId(optId);
		rsdd.setOptTime(new Timestamp(System.currentTimeMillis()));
		rsdd.setOptType(SYSConstant.SELL_DETAIL_OPTTYPE_OSOONS_2_CHANNEL);
		rsdd.setOrderId(orderId);
		rsdd.setRepOptId(serialNo);
		rsdd.setOrderDomain("01");
		this.repSellDetailDAO.insert(rsdd);
	}

	@SuppressWarnings("unchecked")
	public void insertSkuEntites(List<SkuEntity> entites, Integer optId)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		if (CollectionUtils.isEmpty(entites)) {
			return;
		}

		// 沉淀库存量操作记录
		Integer skuid = entites.get(0).getSkuid();
		String repCode = entites.get(0).getRepositoryCode();
		int count = entites.size();
		@SuppressWarnings("unused")
		Integer serialNo = recordRepOpt(skuid, repCode, count, optId,
				SYSConstant.SELL_DETAIL_OPTTYPE_2_REP);// 返回的主键
		// 更新库存量
		int repOptId = freshRep(skuid, repCode, count,
				SYSConstant.SELL_DETAIL_OPTTYPE_2_REP);

		// 沉淀商品进货明细，沉淀商品实体表
		for (Iterator it = entites.iterator(); it.hasNext();) {
			SkuEntity skuEntity = (SkuEntity) it.next();
			// 沉淀商品实体表
			checkIfImeiExist(skuEntity, SYSConstant.SELL_DETAIL_OPTTYPE_2_REP);
			this.skuEntityDAO.insert(skuEntity);
			Integer entityId = skuEntity.getEntityId();
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
				if (!SYSConstant.SKU_STATUS_OSOONS.equals(skuEntites.get(0)
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
	private void recoderRepStockDetail(Integer optId, int repOptId,
			Integer entityId) {
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
	private int freshRep(int skuId, String repCode, int count, String optType)
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
	private int recordRepOpt(int skuId, String repCode, int count,
			Integer optId, String optType) {
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

	public void targetRep(List<Integer> entityIds, String inRepCode)
			throws BusinessException, SystemException {
		SkuEntityExample entity_ex = new SkuEntityExample();
		entity_ex.createCriteria().andEntityIdIn(entityIds);
		List<SkuEntity> entities = skuEntityDAO.selectByExample(entity_ex);
		
		if(entities != null && entities.size() != 0 ){
			Map<String,Integer> sku_rep = new HashMap<String,Integer>();
			for(SkuEntity entity:entities){
				if(StringUtils.equals(entity.getStatus(), SYSConstant.SKU_STATUS_USER) || StringUtils.equals(entity.getStatus(), SYSConstant.SKU_STATUS_OSOONS)){
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
	        	Integer skuId = Integer.parseInt(skuId_repCode[0]);
	            String outRepCode = skuId_repCode[1];
//	            System.out.println(skuId);
//	            System.out.println(outRepCode);
//	            System.out.println(sku_rep.get(outRepCode));
	            repService.updateRepCount(skuId, inRepCode, outRepCode, sku_rep.get(k));//代理商入库
	        }
	        
	        SkuEntity record = new SkuEntity();
	        record.setTargetRepcode(inRepCode);
	        skuEntityDAO.updateByExampleSelective(record, entity_ex);
		}
		
	}


}
