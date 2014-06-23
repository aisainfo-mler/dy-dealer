package com.ai.mapp.commission.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.mapp.base.EvaluatorUtil;
import com.ai.mapp.base.Pager;
import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.sys.entity.CommissionRule;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.CommissionRuleService;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-12-12 下午05:00:48
 * 类说明:
 */

public class CommissionAction extends BaseAction {
	@Autowired
	private CommissionRuleService commissionRuleService;
	
	private List<CommissionRule>  rules;
	
	private CommissionRule ruleCondition;
	
	private CommissionRule rule;
	
	private String tmpOrginalExpress;//jsp页面编辑时表达式，用于判断里面含了哪些变量
	
	private String staticVariants;
	
	public String listCommissionRules()throws Exception{
		
		if(length == 0 || length == 16){
			setLength(50);
		}
		if(ruleCondition == null){
			ruleCondition = new CommissionRule();
		}
		
		if(page == 0){
			page = 1;
		}
		page = (offset + 1 )/length + 1;
		rules = (List<CommissionRule>) commissionRuleService.listCommissionRules(ruleCondition, page, length);
		int count = commissionRuleService.countCommissionRule(ruleCondition);
		setTotal(count);
		setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),getLength(),"#commissionRule_list"));
		return SUCCESS;
	}
	
	public String ruleDetail()throws Exception{
		if(ruleCondition == null || ruleCondition.getRuleId() == null ){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 throw new Exception("丢失 ruleId");
			 }else{
				 throw new Exception("Lost rule ID");
			 }
		}
		rule = commissionRuleService.loadCommissionRule(ruleCondition.getRuleId());
		
		//分解condition
		rule.setConditionMap(commissionRuleService.decomposeCondtionToMap(rule.getCondition()));
//		Map<String,String> map = 
		return SUCCESS;
	}

	/**
	 * <p>描述:添加一条佣金规则 </p> 
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        Zhengwj
	 * @Date          2012-12-17 下午04:30:50
	 */
	public String saveCommission()throws Exception{
		Long ruleId = null;
//		if(rule == null && StringUtil.isEmpty(rule.getBackExpress()) && StringUtil.isEmpty(rule.getRuleName())){
		if(rule == null && StringUtil.isEmpty(rule.getRuleName())){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("无规则名", false);
			 }else{
				 return returnAjaxError("Lost rule name.", false);
			 }
//		}else if(!EvaluatorUtil.isValidExpress(rule.getBackExpress())){
//			return returnAjaxError("Express have wrong on syntax rule", false);
		}else{
			try{
				ruleId = commissionRuleService.saveCommissionRule(rule,(User)getSessionValue(HTTP_SESSION_USER));
			}catch(Exception e){
				e.printStackTrace();
				return returnAjaxError(e.getMessage(), false);
			}
			
		}
		return returnAjaxSuccess(ruleId == null? "":ruleId+"", false);
	}
	
	/**
	 * <p>描述: 删除一条佣金规则</p> 
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        Zhengwj
	 * @Date          2012-12-17 下午04:31:27
	 */
	public String delRule()throws Exception{
		if(rule == null || rule.getRuleId() == null){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("丢失规则名称", false);
			 }else{
				 return returnAjaxError("Lost rule ID.", false);
			 }
		}else{
			try{
				commissionRuleService.deleteCommissionRule(rule);
			}catch(Exception e){
				e.printStackTrace();
				return returnAjaxError(e.getMessage(), false);
			}
			
		}
		return returnAjaxSuccess("", false);
	}
	
	/**
	 * <p>描述:ajax 判断是否符合表达式</p> 
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 * @author        Zhengwj
	 * @Date          2012-12-17 下午04:30:50
	 */
	public String validExpress()throws Exception{
		if(!EvaluatorUtil.isValidExpress(tmpOrginalExpress)){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("表达式存在语法错误", false);
			 }else{
				 return returnAjaxError("Express have wrong on syntax rule", false);
			 }
		}else{
			return returnAjaxSuccess("", false);
		}
	}
	public CommissionRuleService getCommissionRuleService() {
		return commissionRuleService;
	}

	public void setCommissionRuleService(CommissionRuleService commissionRuleService) {
		this.commissionRuleService = commissionRuleService;
	}

	public List<CommissionRule> getRules() {
		return rules;
	}

	public void setRules(List<CommissionRule> rules) {
		this.rules = rules;
	}

	public CommissionRule getRuleCondition() {
		return ruleCondition;
	}

	public void setRuleCondition(CommissionRule ruleCondition) {
		this.ruleCondition = ruleCondition;
	}

	public CommissionRule getRule() {
		return rule;
	}

	public void setRule(CommissionRule rule) {
		this.rule = rule;
	}

	public String getTmpOrginalExpress() {
		return tmpOrginalExpress;
	}

	public void setTmpOrginalExpress(String tmpOrginalExpress) {
		this.tmpOrginalExpress = tmpOrginalExpress;
	}

	public String getStaticVariants() {
		return staticVariants;
	}

	public void setStaticVariants(String staticVariants) {
		this.staticVariants = staticVariants;
	}
	
	
}
