package com.ai.mapp.sys.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.EvaluatorUtil;
import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.dao.CommissionRuleDao;
import com.ai.mapp.sys.entity.CommissionRule;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-12-12 下午03:20:51
 * 类说明:
 */

@Service
@Transactional
public class CommissionRuleService {
	public final Log log = LogFactory.getLog(CommissionRuleService.class);
	
	@Autowired
	private CommissionRuleDao commissionRuleDao;
	
	public Collection<CommissionRule> listCommissionRules(CommissionRule commissionRule,int start,int limit){
		try{
			log.debug(commissionRule == null?"commissionRule is null":commissionRule.toString());
			Collection<CommissionRule> c = null;
			if(start < 0 || limit < 0){
				c = commissionRuleDao.listAll(commissionRule);
			}else{
				c = commissionRuleDao.list(commissionRule, start, limit);
			}
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Long saveCommissionRule(CommissionRule commissionRule,User operator)throws Exception{
			log.debug(commissionRule==null?"commissionRule is null":commissionRule.toString());
			CommissionRule saveR = null;
			if(commissionRule.getRuleId() == null){
				commissionRule.setUpdater(operator);
				if(operator != null){
					commissionRule.setCreator(operator);
				}
				commissionRule.setCreateTime(new Date());
				saveR = commissionRule;
			}else{
				saveR = loadCommissionRule(commissionRule.getRuleId());
				saveR.setRuleName(commissionRule.getRuleName());
				saveR.setBackType(commissionRule.getBackType());
				saveR.setCondition(commissionRule.getCondition());
				saveR.setValid(commissionRule.getValid());
				saveR.setBackExpress(commissionRule.getBackExpress());
				saveR.setPayType(commissionRule.getPayType());
				saveR.setDescription(commissionRule.getDescription());
				saveR.setCreateTime(new Date());
				saveR.setModId(commissionRule.getModId());
			}
			
			if(operator != null){
				saveR.setUpdater(operator);
			}
			saveR.setUpdateTime(new Date());
			commissionRuleDao.save(saveR);
			return saveR.getRuleId();
	}
	
	public void deleteCommissionRule(CommissionRule commissionRule){
		try{
			log.debug(commissionRule==null?"commissionRule is null":commissionRule.toString());
			commissionRuleDao.delete(commissionRule);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countCommissionRule(CommissionRule commissionRule) throws RollbackException {
		try{
			return commissionRuleDao.count(commissionRule);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	
	public CommissionRule loadCommissionRule(Long id)throws Exception{
		return commissionRuleDao.get(id);
	}
	
	/**
	 * <p>描述:x:0-100-0.5;100-200-5;y:0-100-0.7; 分解成map便于jsp页面展现</p> 
	 * @param:        @param condition
	 * @param:        @return    
	 * @return:       Map<String,List<String[]>>    
	 * @author        Zhengwj
	 * @Date          2012-12-13 下午06:13:16
	 */
	public  Map<String,List<String[]>> decomposeCondtionToMap(String condition){
		Map<String,List<String[]>> result = new LinkedHashMap<String,List<String[]>>();
		if(StringUtil.isNotEmpty(condition) && condition.indexOf(":") != -1){//一定要有变量名
			String[] conditionArr = condition.split("[:;]");
			String[] sectionArr = null;
			String key = null;
			for(int i = 0; i < conditionArr.length ; i++){
				if(StringUtil.isNotEmpty(conditionArr[i])){
					if(conditionArr[i].indexOf("~") == -1){
						key = conditionArr[i];
						result.put(key, new ArrayList<String[]>() );
					}else{
						sectionArr = conditionArr[i].split("~");
						result.get(key).add(sectionArr);
					}
				}
			}
		}
		return result;
	}
	
	public String composeConditionMapTOString(Map<String,List<String[]>> conditionMap){
		if(conditionMap == null || conditionMap.isEmpty()){
			return "";
		}
		String result = "";
		Set<String> keys = conditionMap.keySet();
		String key = null;
		List<String[]> condition = null;
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            key = it.next();
            result += key + ":";
            condition = conditionMap.get(key);
            if(condition != null){
            	for(String[] section:condition){
                	if(section != null && section.length !=0){
                		for(int i = 0; i < section.length; i++){
                			if( i != section.length - 1){
                				result += section[i] + "~";
                			}else{
                				result += section[i] + ";";
                			}
                		}
                	}
                }
            }
            
        }
		return result;
	}
	
	/**
	 * <p>描述: 获得立佣金规则</p> 
	 * @param:        @param condition
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<CommissionRule>    
	 * @author        Zhengwj
	 * @Date          2012-12-18 下午03:50:18
	 */
	public List<CommissionRule> getImmediateBackRules(CommissionRule condition)throws Exception{
		if(condition == null){
			condition = new CommissionRule();
			condition.setBackType(SYSConstant.COMMISSION_BACK_TYPE_MAKE_UP);
			condition.setValid(SYSConstant.STATE_VALID);
//			condition.setPayType(SYSConstant.COMMISSION_PAY_TYPE_IMMEDIATELY);
		}
		List<CommissionRule> rules = (List<CommissionRule>) listCommissionRules(condition, -1, 0);
		return rules;
	}
	
	
	/**
	 * <p>描述:根据订单金额值获得立返佣金值 </p> 
	 * @param:        @param fee 单位 厘
	 * @param:        @return    
	 * @return:       BigDecimal 厘   
	 * @author        Zhengwj
	 * @Date          2012-12-18 下午03:53:06
	 */
	public BigDecimal getImmediateCommissionValue(Map<String, String> variantMap,Long agentId)throws Exception{
		BigDecimal commissionValue = new BigDecimal(0);
		
		CommissionRule makeUpCommissionRule=new CommissionRule();
		makeUpCommissionRule.setBackType(SYSConstant.COMMISSION_BACK_TYPE_MAKE_UP);
		makeUpCommissionRule.setValid(SYSConstant.STATE_VALID);
		makeUpCommissionRule.setQueryType(new Long(102));
		makeUpCommissionRule.setAgentId(agentId);
		
		List<CommissionRule> conditions = getImmediateBackRules(makeUpCommissionRule);
		if(conditions == null || conditions.size() != 0){
			Map<String,List<String[]>> conditionMap = null;
			List<String[]> from_to_express = null;
			Set<String> keySet = null;
			String key = null;
			String value = null;
			String backExpress = null;
			Iterator<String> it = null;
			double from = 0.0;
			double to = 0.0;
			double doubleValue = 0.0;
			for(CommissionRule condition:conditions){
				if(StringUtil.isNotEmpty(condition.getCondition())){
					if(condition.getCondition() != null){
						conditionMap = decomposeCondtionToMap(condition.getCondition());
						keySet = variantMap.keySet();
				        for (it = keySet.iterator(); it.hasNext();) {
				            key = it.next();
				            value = variantMap.get(key);
				            doubleValue = Double.parseDouble(value);
				            from_to_express = conditionMap.get(key);//获得同变量名的条件
				            
				            if(from_to_express != null && from_to_express.size() != 0) {
								from = 0.0;
								to = 0.0;
								
								BigDecimal tmpR = new BigDecimal(0);
								for(String[] unitRection:from_to_express){
									from = Double.parseDouble(unitRection[0]);
									to = Double.parseDouble(unitRection[1]);
									backExpress = EvaluatorUtil.backExpress(unitRection[2]);
									if(from <= doubleValue/100 && doubleValue/100 <= to){
										tmpR = new BigDecimal((EvaluatorUtil.getResult(backExpress, variantMap)) + "");
										commissionValue = commissionValue.add(tmpR);
									}
								}
							}
				        }
					}
				}
			}
		}
		return commissionValue;
	}
	
	public static void main(String[] args) {
//		String condition = "x:0-100-0.5%;100-200-5%;y:0-100-0.7%;";
//		String condition = "x:50-100-1;100-200-2;";
//		Map<String,List<String[]>> r1 = decomposeCondtionToMap(condition);
//		 Set<String> key = r1.keySet();
//	        for (Iterator<String> it = key.iterator(); it.hasNext();) {
//	            String s = (String) it.next();
//	            System.out.println("dddd" + r1.get(s).size());
//	            List<String[]> from_to_rate = r1.get(s);
//	            for(String[] unitRection:from_to_rate){
//					System.out.println(unitRection[0]);
//					System.out.println(unitRection[1]);
//					System.out.println(unitRection[2]);
//				}
//	        }
		
		
//		String r2 = composeConditionMapTOString(r1);
//		System.out.println(r2);
//		double from = 0.0;
//		double to = 1000.0;
//		BigDecimal bigFee = new BigDecimal(0.5);
//		System.out.println(bigFee);
//		if(from <= bigFee.doubleValue() && bigFee.doubleValue() <= to){
//			System.out.println(true);
//		}
		
		String[] conditionArr = "t:1~5~1;x:10~300~x*0.1;".split("[:;]");
		for(int i=0;i<conditionArr.length;i++){
			System.out.println(""+i+"|"+conditionArr[i]);
		}
		
	}
	
	
}
