package com.ailk.ts.ibatis.service;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.ts.dal.ibatis.RepSellDetailDAO;
import com.ailk.ts.dal.ibatis.model.RepSellDetail;
import com.ailk.ts.dal.ibatis.model.RepSellDetailExample;

@Service
@Transactional(rollbackFor=Exception.class)
public class RepSellDetailService {
	
	private static final Logger log = LoggerFactory.getLogger(RepSellDetailService.class);
	
	@Autowired
	private RepSellDetailDAO repSellDetailDAO;

	/**
	 * <p>描述: 对于加盟商出售DEVICE  是没有库存操作记录ID的</p> 
	 * @param detail
	 * @param optId
	 * @return
	 * @throws BusinessException
	 * @throws SystemException    
	 * @return:       Integer    
	 * @Date          2013-5-13 下午05:12:45
	 */
	public Long createSecondSellDetail(Long orderId, Long entityId,
			Long optId,String optType) throws BusinessException, SystemException {
		RepSellDetail detail = new RepSellDetail();
		detail.setEntityId(entityId);
		detail.setOpterId(optId);
		detail.setOptTime(new Timestamp(System.currentTimeMillis()));
		detail.setOrderId(orderId);
		detail.setOptType(optType);
		detail.setOrderDomain(SYSConstant.ORDER_DOMAIN_ORDER);
		return repSellDetailDAO.insertSelective(detail);
	}

	/**
	 * 寻找一个商品的相关库存操作记录信息
	 */
	public List<RepSellDetail> findRepSellDetailByEntityId(Long entityId)throws BusinessException, SystemException {
		RepSellDetailExample detail_ex = new RepSellDetailExample();
		RepSellDetailExample.Criteria criteria = detail_ex.createCriteria();
		criteria.andEntityIdEqualTo(entityId);
		detail_ex.setOrderByClause("OPT_TIME desc");
		return repSellDetailDAO.selectByExample(detail_ex);
	}
	
	
	/**
	 * 寻找一个商品的最近库存操作记录信息
	 */
	public RepSellDetail findLastByRepSellDetailEntityId(Long entityId)throws BusinessException, SystemException {
		List<RepSellDetail> list = findRepSellDetailByEntityId(entityId);
		if(list != null && list.isEmpty() == false){
				return list.get(0);
		}else{
			return null;
		}
		
	}
}
