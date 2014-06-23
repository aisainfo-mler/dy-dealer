package com.ai.mapp.user.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ai.mapp.base.Pager;
import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.base.util.MD5;
import com.ai.mapp.sys.entity.CommissionRule;
import com.ai.mapp.sys.entity.HwAgent2CommissionRule;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.CommissionRuleService;
import com.ai.mapp.sys.service.HwAgent2CommissionRuleService;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-22 上午11:25:50
 * 类说明:
 */

public class UserAction extends BaseAction{
	
	@Autowired
//	@Qualifier(value="mappUserService")
	private UserService mappUserService;
	@Autowired
	private CommissionRuleService commissionRuleService;
	@Autowired
	private HwAgent2CommissionRuleService hwAgent2CommissionRuleService;
	
	private User userCondition;
	private CommissionRule commissionRule;
	private HwAgent2CommissionRule hwAgent2CommissionRule;
	private List<User> userList;
	private List<HwAgent2CommissionRule> joinedRuleList;
	private List<CommissionRule> unjoinedRuleList;
	private int globalRowNum=0;

	public String userList()throws Exception{
		if(userCondition == null){
			userCondition = new User();
		}
		if(length == 0 || length == 16){
			setLength(50);
		}
		OrderInfo orderInfo = new OrderInfo();
		
		if(page == 0){
			page = 1;
		}
		page = (offset + 1 )/length + 1;
		userList = (List<User>) mappUserService.listUsers(userCondition, page, length);
		int count = mappUserService.countUser(userCondition);
		setTotal(count);
		//setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),getLength(),"#user_main_div"));
		setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),10,"#user_main_div"));
		return SUCCESS;
	}
	
	
	public String saveUser()throws Exception{
		if(userCondition == null || userCondition.getUserId() == null){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("丢失用户信息", false);
			 }else{
				 return returnAjaxError("Lost user ID", false);
			 }
		}
		try{
			User user = mappUserService.loadUser(userCondition.getUserId());
			if(user == null){
				if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
					 return returnAjaxError("数据库用户丢失", false);
				 }else{
					 return returnAjaxError("Lost user in dataBase", false);
				 }
			}
			
			user.setUpdateTime(new Date());
			user.setUpdater((User)getSessionValue(HTTP_SESSION_USER));
			user.setReason(userCondition.getReason());
//			if(SYSConstant.STATE_WAITING_4_AUDIT.equals(user.getStatus()) && SYSConstant.STATE_VALID.equals(userCondition.getStatus())){//对新建用户审批通过进行创建账户信息
			if(SYSConstant.STATE_VALID.equals(userCondition.getStatus())){//若为通过，保存后要判断是否有帐户信息，在没有的情况下要新建帐户
				user.setStatus(userCondition.getStatus());
				user.setApproveTime(new java.util.Date());
				mappUserService.auditPassUser(user,(User)getSessionValue(HTTP_SESSION_USER));
			}else{
				user.setStatus(userCondition.getStatus());
				user.setDenyTime(new java.util.Date());
				mappUserService.saveUser(user);
			}
			
			
			return returnAjaxSuccess("", false);
		}catch(Exception e){
			e.printStackTrace();
			return returnAjaxError(e.getMessage(), false);
		}
		
	}
	
	public String userManagerList()throws Exception{
		if(userCondition == null){
			userCondition = new User();
		}
		
		if(StringUtil.isEmpty(userCondition.getStatus())){
			userCondition.setStatus("!" + SYSConstant.STATE_WAITING_4_AUDIT);
		}
		
		if(length == 0 || length == 16){
			setLength(50);
		}
		OrderInfo orderInfo = new OrderInfo();
		
		if(page == 0){
			page = 1;
		}
		page = (offset + 1 )/length + 1;
		userList = (List<User>) mappUserService.listUsers(userCondition, page, length);
		int count = mappUserService.countUser(userCondition);
		setTotal(count);
		setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),getLength(),"#user_main_div"));
		return SUCCESS;
		
	}
	
	public String ruleSetInit() throws Exception{
		
		if(hwAgent2CommissionRule!=null && hwAgent2CommissionRule.getAgentId()!=null){
			joinedRuleList=(List<HwAgent2CommissionRule>)hwAgent2CommissionRuleService.listAllHwAgent2CommissionRule(hwAgent2CommissionRule);
			if(joinedRuleList!=null && joinedRuleList.size()>0){
				globalRowNum=joinedRuleList.size();
			}
		}
		
		return SUCCESS;
	}
	
	public String listUnjoinedRules()throws Exception{
		
		if(length == 0 || length == 16){
			setLength(5);
		}
		if(commissionRule == null){
			commissionRule = new CommissionRule();
		}
		
		if(page == 0){
			page = 1;
		}
		page = (offset + 1 )/length + 1;
		unjoinedRuleList = (List<CommissionRule>) commissionRuleService.listCommissionRules(commissionRule, page, length);
		int count = commissionRuleService.countCommissionRule(commissionRule);
		setTotal(count);
		setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),getLength(),"#unjoined_rule_list_div"));
		return SUCCESS;
	}
	
	public String deleJoinedRule() throws Exception{
		if(hwAgent2CommissionRule == null || hwAgent2CommissionRule.getRuleRelaId() == null){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("数据错误", false);
			 }else{
				 return returnAjaxError("Data Error!", false);
			 }
		}
		try{
			hwAgent2CommissionRuleService.deleteHwAgent2CommissionRule(hwAgent2CommissionRule);
			return returnAjaxSuccess("", false);
		}catch(Exception e){
			e.printStackTrace();
			return returnAjaxError(e.getMessage(), false);
		}
	}
	
	public String saveJoinRule() throws Exception{
		if(hwAgent2CommissionRule == null || hwAgent2CommissionRule.getAgentId()==null 
				|| hwAgent2CommissionRule.getCommissionRule()== null || hwAgent2CommissionRule.getCommissionRule().getRuleId()==null){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("数据错误", false);
			 }else{
				 return returnAjaxError("Data Error!", false);
			 }
		}
		try{
			hwAgent2CommissionRule.setCreator(getLogincode());
			hwAgent2CommissionRule.setCreatorTime(new java.util.Date());
			hwAgent2CommissionRuleService.saveHwAgent2CommissionRule(hwAgent2CommissionRule);
			jsonResult="{\"flag\":true,\"ruleRelaId\":\"" + hwAgent2CommissionRule.getRuleRelaId() + "\"}";
			return "jsonResult";
		}catch(Exception e){
			e.printStackTrace();
			return returnAjaxError(e.getMessage(), false);
		}
	}
	

	public User getUserCondition() {
		return userCondition;
	}

	public void setUserCondition(User userCondition) {
		this.userCondition = userCondition;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public String savePassword()throws Exception{
		if(userCondition == null || userCondition.getUserId() == null || StringUtil.isEmpty(userCondition.getPassword())){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("丢失用户信息或修改的密码", false);
			 }else{
				 return returnAjaxError("Lost user's ID or new password", false);
			 }
		}
		try{
			User user = mappUserService.loadUser(userCondition.getUserId());
			if(user == null){
				if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
					 return returnAjaxError("数据库用户丢失", false);
				 }else{
					 return returnAjaxError("Lost user in dataBase", false);
				 }
			}
			user.setUpdateTime(new Date());
			user.setUpdater((User)getSessionValue(HTTP_SESSION_USER));
			user.setPassword(MD5.toMD5(userCondition.getPassword()));
			mappUserService.saveUser(user);
			return returnAjaxSuccess("", false);
		}catch(Exception e){
			e.printStackTrace();
			return returnAjaxError(e.getMessage(), false);
		}
		
	}


	public CommissionRuleService getCommissionRuleService() {
		return commissionRuleService;
	}


	public void setCommissionRuleService(CommissionRuleService commissionRuleService) {
		this.commissionRuleService = commissionRuleService;
	}


	public CommissionRule getCommissionRule() {
		return commissionRule;
	}


	public void setCommissionRule(CommissionRule commissionRule) {
		this.commissionRule = commissionRule;
	}

	public List<CommissionRule> getUnjoinedRuleList() {
		return unjoinedRuleList;
	}


	public void setUnjoinedRuleList(List<CommissionRule> unjoinedRuleList) {
		this.unjoinedRuleList = unjoinedRuleList;
	}


	public HwAgent2CommissionRuleService getHwAgent2CommissionRuleService() {
		return hwAgent2CommissionRuleService;
	}


	public void setHwAgent2CommissionRuleService(
			HwAgent2CommissionRuleService hwAgent2CommissionRuleService) {
		this.hwAgent2CommissionRuleService = hwAgent2CommissionRuleService;
	}


	public HwAgent2CommissionRule getHwAgent2CommissionRule() {
		return hwAgent2CommissionRule;
	}


	public void setHwAgent2CommissionRule(
			HwAgent2CommissionRule hwAgent2CommissionRule) {
		this.hwAgent2CommissionRule = hwAgent2CommissionRule;
	}


	public List<HwAgent2CommissionRule> getJoinedRuleList() {
		return joinedRuleList;
	}


	public void setJoinedRuleList(List<HwAgent2CommissionRule> joinedRuleList) {
		this.joinedRuleList = joinedRuleList;
	}


	public int getGlobalRowNum() {
		return globalRowNum;
	}


	public void setGlobalRowNum(int globalRowNum) {
		this.globalRowNum = globalRowNum;
	}


	public UserService getMappUserService() {
		return mappUserService;
	}


	public void setMappUserService(UserService mappUserService) {
		this.mappUserService = mappUserService;
	}
	
}
