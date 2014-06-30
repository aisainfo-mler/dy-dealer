package com.ailk.ts.ibatis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.OrderItemDao;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.ts.dal.ibatis.RepDAO;
import com.ailk.ts.dal.ibatis.model.Rep;
import com.ailk.ts.dal.ibatis.model.RepExample;
import com.ailk.ts.dal.ibatis.model.SelfDefineRep;
import com.ailk.ts.dal.ibatis.model.RepExample.Criteria;

@Service
@Transactional(rollbackFor=Exception.class)
public class RepService {

	@Autowired
	private RepDAO repDao;
	
	@Autowired
	private CommonQueryService commonQueryService;



	private RepExample conver2Example(SelfDefineRep rep) {
		RepExample re = new RepExample();
		Criteria cc = re.createCriteria();
		if (rep.getSkuId() != null)
			cc.andSkuidEqualTo(rep.getSkuId());
//		re.setProductId(rep.getProductId());
//		re.setProductTypeId(rep.getProductTypeId());
//		re.setBrandId(rep.getBrandId());
		return re;
	}

	public RepDAO getRepDao() {
		return repDao;
	}

	public void setRepDao(RepDAO repDao) {
		this.repDao = repDao;
	}


	public List<SelfDefineRep> getSelfRep(SelfDefineRep cond, int start,
			int limit) throws BusinessException, SystemException {
		Map<String,Object> condM = new HashMap<String, Object>();
		if(cond != null ){
			if(cond.getBrandId() != null){
				condM.put("brandId", cond.getBrandId());
			}
			if(StringUtils.isNotEmpty(cond.getCondTypeId())){
				if(cond.getCondTypeId().startsWith("!")){
					condM.put("notTypeId", Integer.parseInt(cond.getCondTypeId().substring(1)));
				}else{
					condM.put("typeId", Integer.parseInt(cond.getCondTypeId()));
				}
			}
			if(cond.getProductId() != null){
				condM.put("productId", cond.getProductId());
			}
			if(cond.getSkuId() != null){
				condM.put("skuId", cond.getSkuId());
			}
			if(cond.getDeptId() != null){
				condM.put("deptId", cond.getDeptId());
			}
			if(cond.getRepositoryCode() != null){
				condM.put("repCode", cond.getRepositoryCode());
			}
		}
		return commonQueryService.selectRep(condM, start, limit);
	}

	public void updateRepCount(Long skuId,Long inRepCode,Long outRepCode,Integer count) throws BusinessException, SystemException {
		if(count == null || count.intValue() == 0){
			throw new BusinessException("9999","仓库变更数量不能为零");
		}
		SelfDefineRep cond = new SelfDefineRep();
		cond.setSkuId(skuId);
		
		//入库操作
		if(inRepCode != null){
			cond.setRepositoryCode(inRepCode);
			List<SelfDefineRep> inReps = getSelfRep(cond, -1, -1);
			if(inReps != null && inReps.isEmpty() == false){
				Rep dbRep = null;
				for(SelfDefineRep rep:inReps){
					dbRep = new Rep();
					dbRep.setId(rep.getId());
					dbRep.setCount(rep.getCount() == null? 0:rep.getCount() + count);
					repDao.updateByPrimaryKeySelective(dbRep);
				}
			}else{
				Rep dbRep = new Rep();
				dbRep.setSkuid(skuId);
				dbRep.setRepositoryCode(inRepCode);
				dbRep.setCount(count);
				repDao.insertSelective(dbRep);
			}
		}
		
		//出库操作
		if(outRepCode != null){
			cond.setRepositoryCode(outRepCode);
			List<SelfDefineRep> outReps = getSelfRep(cond, -1, -1);
			if(outReps != null && outReps.isEmpty() == false){
				Rep dbRep = null;
				for(SelfDefineRep rep:outReps){
					dbRep = new Rep();
					dbRep.setId(rep.getId());
					dbRep.setCount(rep.getCount() - count);
					repDao.updateByPrimaryKeySelective(dbRep);
				}
			}
		}
		
	}
}
