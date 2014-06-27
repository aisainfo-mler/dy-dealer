//package com.ailk.ts.ibatis.service;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.ailk.butterfly.core.exception.BusinessException;
//import com.ailk.butterfly.core.exception.SystemException;
//import com.ailk.ts.dal.ibatis.RepDAO;
//import com.ailk.ts.dal.ibatis.model.Rep;
//import com.ailk.ts.dal.ibatis.model.RepExample;
//import com.ailk.ts.dal.ibatis.model.SelfDefineRep;
//import com.ailk.ts.dal.ibatis.model.RepExample.Criteria;
//import com.ailk.ts.handwork.dal.ibatis.interfaces.MyRepDAO;
//import com.ailk.ts.handwork.dal.ibatis.interfaces.OrderItemDAO;
//import com.ailk.ts.handwork.dal.ibatis.model.MyRep;
//import com.ailk.ts.service.IRepService;
//
//public class RepServiceImpl implements IRepService {
//
//	@Autowired
//	private RepDAO repDao;
//	
//	@Autowired
//	private MyRepDAO myRepDao;
//	
//	@Autowired
//	private OrderItemDAO orderItemDAO;
//
//	@Override
//	public int countRep(MyRep rep) throws BusinessException, SystemException {
//		RepExample re = this.conver2Example(rep);
//		int count = this.myRepDao.countByExample(re);
//		return count;
//	}
//
//	@Override
//	public List<MyRep> getRep(MyRep rep, int start, int limit)
//			throws BusinessException, SystemException {
//		RepExample re = this.conver2Example(rep);
//		re.setLimitClauseStart(start);
//		re.setLimitClauseCount(limit);
//
//		return this.myRepDao.selectByExample(re);
//	}
//
//	private RepExample conver2Example(MyRep rep) {
//		RepExample re = new RepExample();
//		Criteria cc = re.createCriteria();
//		if (rep.getSkuid() != null)
//			cc.andSkuidEqualTo(rep.getSkuid());
//		re.setProductId(rep.getProductId());
//		re.setProductTypeId(rep.getProductTypeId());
//		re.setBrandId(rep.getBrandId());
//		return re;
//	}
//
//	public RepDAO getRepDao() {
//		return repDao;
//	}
//
//	public void setRepDao(RepDAO repDao) {
//		this.repDao = repDao;
//	}
//
//	public MyRepDAO getMyRepDao() {
//		return myRepDao;
//	}
//
//	public void setMyRepDao(MyRepDAO myRepDao) {
//		this.myRepDao = myRepDao;
//	}
//
//	public OrderItemDAO getOrderItemDAO() {
//		return orderItemDAO;
//	}
//
//	public void setOrderItemDAO(OrderItemDAO orderItemDAO) {
//		this.orderItemDAO = orderItemDAO;
//	}
//
//	@Override
//	public List<SelfDefineRep> getSelfRep(SelfDefineRep cond, int start,
//			int limit) throws BusinessException, SystemException {
//		Map<String,Object> condM = new HashMap<String, Object>();
//		if(cond != null ){
//			if(cond.getBrandId() != null){
//				condM.put("brandId", cond.getBrandId());
//			}
//			if(StringUtils.isNotEmpty(cond.getCondTypeId())){
//				if(cond.getCondTypeId().startsWith("!")){
//					condM.put("notTypeId", Integer.parseInt(cond.getCondTypeId().substring(1)));
//				}else{
//					condM.put("typeId", Integer.parseInt(cond.getCondTypeId()));
//				}
//			}
//			if(cond.getProductId() != null){
//				condM.put("productId", cond.getProductId());
//			}
//			if(cond.getSkuId() != null){
//				condM.put("skuId", cond.getSkuId());
//			}
//			if(cond.getDeptId() != null){
//				condM.put("deptId", cond.getDeptId());
//			}
//			if(StringUtils.isNotEmpty(cond.getRepositoryCode())){
//				condM.put("repCode", cond.getRepositoryCode());
//			}
//		}
//		return orderItemDAO.selectRep(condM, start, limit);
//	}
//
//	@Override
//	public void updateRepCount(Integer skuId,String inRepCode,String outRepCode,Integer count) throws BusinessException, SystemException {
//		if(count == null || count.intValue() == 0){
//			throw new BusinessException("9999","仓库变更数量不能为零");
//		}
//		SelfDefineRep cond = new SelfDefineRep();
//		cond.setSkuId(skuId);
//		
//		//入库操作
//		if(StringUtils.isNotEmpty(inRepCode)){
//			cond.setRepositoryCode(inRepCode);
//			List<SelfDefineRep> inReps = getSelfRep(cond, -1, -1);
//			if(inReps != null && inReps.isEmpty() == false){
//				Rep dbRep = null;
//				for(SelfDefineRep rep:inReps){
//					dbRep = new Rep();
//					dbRep.setId(rep.getId());
//					dbRep.setCount(rep.getCount() == null? 0:rep.getCount() + count);
//					repDao.updateByPrimaryKeySelective(dbRep);
//				}
//			}else{
//				Rep dbRep = new Rep();
//				dbRep.setSkuid(skuId);
//				dbRep.setRepositoryCode(inRepCode);
//				dbRep.setCount(count);
//				repDao.insertSelective(dbRep);
//			}
//		}
//		
//		//出库操作
//		if(StringUtils.isNotEmpty(outRepCode)){
//			cond.setRepositoryCode(outRepCode);
//			List<SelfDefineRep> outReps = getSelfRep(cond, -1, -1);
//			if(outReps != null && outReps.isEmpty() == false){
//				Rep dbRep = null;
//				for(SelfDefineRep rep:outReps){
//					dbRep = new Rep();
//					dbRep.setId(rep.getId());
//					dbRep.setCount(rep.getCount() - count);
//					repDao.updateByPrimaryKeySelective(dbRep);
//				}
//			}
//		}
//		
//	}
//}
