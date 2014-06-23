package com.ai.mapp.sys.service;

import java.util.Collection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.entity.HwAgent2CommissionRule;
import com.ai.mapp.sys.entity.User;

@Service
@Transactional
public class HwAgent2CommissionRuleService {
	
	public final Log log = LogFactory.getLog(HwAgent2CommissionRuleService.class);
	
	@Autowired
	private com.ai.mapp.sys.dao.HwAgent2CommissionRuleDao hwAgent2CommissionRuleDao;
	
	public Collection<HwAgent2CommissionRule> lisHwAgent2CommissionRule(HwAgent2CommissionRule hwAgent2CommissionRule,int start,int limit){
		try{
			log.debug(hwAgent2CommissionRule==null?"hwAgent2CommissionRule is null":hwAgent2CommissionRule.toString());
			Collection<HwAgent2CommissionRule> c = hwAgent2CommissionRuleDao.list(hwAgent2CommissionRule, start, limit);
			return c;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection<HwAgent2CommissionRule> listAllHwAgent2CommissionRule(HwAgent2CommissionRule hwAgent2CommissionRule){
		try{
			log.debug(hwAgent2CommissionRule==null?"hwAgent2CommissionRule is null":hwAgent2CommissionRule.toString());
			Collection<HwAgent2CommissionRule> c = hwAgent2CommissionRuleDao.listAll(hwAgent2CommissionRule);
			return c;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void deleteHwAgent2CommissionRule(HwAgent2CommissionRule hwAgent2CommissionRule){
		try{
			log.debug(hwAgent2CommissionRule==null?"hwAgent2CommissionRule is null":hwAgent2CommissionRule.toString());
			hwAgent2CommissionRuleDao.delete(hwAgent2CommissionRule);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countHwAgent2CommissionRule(HwAgent2CommissionRule hwAgent2CommissionRule) throws RollbackException {
		try{
			return hwAgent2CommissionRuleDao.count(hwAgent2CommissionRule);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	
	public void saveHwAgent2CommissionRule(HwAgent2CommissionRule hwAgent2CommissionRule)throws Exception{
		log.debug(hwAgent2CommissionRule==null?"hwAgent2CommissionRule is null":hwAgent2CommissionRule.toString());
		hwAgent2CommissionRuleDao.save(hwAgent2CommissionRule);
	}
	
}
