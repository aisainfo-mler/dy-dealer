//package com.ailk.ts.ibatis.service;
//
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ai.mapp.sys.dao.GoodsInfoDao;
//import com.ai.mapp.sys.entity.GoodsInfo;
//import com.ailk.butterfly.core.exception.BusinessException;
//import com.ailk.butterfly.core.exception.SystemException;
//import com.ailk.butterfly.core.util.DateUtils;
//import com.ailk.butterfly.sys.RequestContext;
//import com.ailk.butterfly.sys.dal.ibatis.model.IUserInfo;
//import com.ailk.ts.dal.ibatis.ProductSkuDAO;
//import com.ailk.ts.dal.ibatis.model.ProductSku;
//import com.ailk.ts.dal.ibatis.model.ProductSkuExample;
//import com.ailk.ts.dal.ibatis.model.ProductSkuExample.Criteria;
//
//@Service
//@Transactional(rollbackFor=Exception.class)
//public class ProductSkuService {
//	private static final Logger log = LoggerFactory.getLogger(ProductSkuService.class);
//	
////	@Autowired
////	private ProductSkuDAO productSkuDao;
//	
//	@Autowired
//	private GoodsInfoDao goodsInfoDao;
//
//	public GoodsInfoDao getGoodsInfoDao() {
//		return goodsInfoDao;
//	}
//
//	public void setGoodsInfoDao(GoodsInfoDao goodsInfoDao) {
//		this.goodsInfoDao = goodsInfoDao;
//	}
//
//	public ProductSku getSkuById(Integer skuId) throws BusinessException,
//			SystemException {
//		return productSkuDao.selectByPrimaryKey(skuId);
//	}
//
//	public BigDecimal getSkuPrice(Integer skuId) throws BusinessException,
//			SystemException {
//		return this.getSkuPrice(this.productSkuDao.selectByPrimaryKey(skuId));
//	}
//	
//	public BigDecimal getSkuPrice(ProductSku sku) throws BusinessException,SystemException {
//		IUserInfo user = RequestContext.getUser();		
//		Timestamp current = DateUtils.getCurrent();			
//			if(user == null){
//				//未登入，返回普通价格
//				return sku.getSailPrice();
//			}else{
//				//已登入
//				if(user.isAgent()){
//					//如果是代理商用户，返回代理商价格
//					return sku.getAgentPrice();
//				}else{
//					//普通购买者，返回普通价格
//					return sku.getSailPrice();
//				}
//			}
//	}
//
//	public List<ProductSku> getProductSkus(ProductSku sku,int start, int limit) throws BusinessException, SystemException {
//		ProductSkuExample sku_ex = this.convert2Example(sku);
//		if(start >= 0){
//			sku_ex.setLimitClauseCount(limit);
//			sku_ex.setLimitClauseStart(start);
//		}
//		return this.productSkuDao.selectByExampleWithoutBLOBs(sku_ex);
//	}
//	
//	private ProductSkuExample convert2Example(ProductSku sku){
//		ProductSkuExample pse = new ProductSkuExample();
//		Criteria cc = pse.createCriteria();
//		if(sku.getProductId()!=null){
//			cc.andProductIdEqualTo(sku.getProductId());
//		}
//		if(StringUtils.isNotEmpty(sku.getSkuName())){
//			cc.andSkuNameLike("%"+sku.getSkuName().toUpperCase()+"%");
//		}
//		if(StringUtils.isNotEmpty(sku.getIsRangephone())){
//			cc.andIsRangephoneEqualTo(sku.getIsRangephone());
//		}
//		if(StringUtils.isNotEmpty(sku.getStatus())){
//			cc.andStatusEqualTo(sku.getStatus());
//		}
//		pse.setOrderByClause("SPE_PRIORIY desc");
//		
//		return pse;
//	}
//
//	public List getSkuName(Map condition) throws BusinessException,SystemException {
//		return this.productSkuDao.selectSkuName(condition);
//	}
//
//}
