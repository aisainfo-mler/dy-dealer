package com.ai.mapp.agent.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.mapp.base.Pager;
import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.SvnInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.SvnInfoService;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj   如新装、充值订单
 * @version 创建时间：2012-9-22 下午03:12:08
 * 类说明:
 */

public class AgentOrderAction extends BaseAction{
	@Autowired
	private AgentOrderService agentOrderService;
	@Autowired
	private SvnInfoService svnInfoService;

	private AgentOrder agentOrderCondition;
	
	private List<AgentOrder> agentOrderList;
	
	private SvnInfo svnInfo;
	
	public String agentOrderList()throws Exception{
		if(agentOrderCondition == null){
			agentOrderCondition = new AgentOrder();
		}
		if(length == 0 || length == 16){
			setLength(50);
		}
		
		if(page == 0){
			page = 1;
		}
		page = (offset + 1 )/length + 1;
		agentOrderList = (List<AgentOrder>) agentOrderService.listAgentOrders(agentOrderCondition, page, length);
		int count = agentOrderService.countAgentOrder(agentOrderCondition);
		setTotal(count);
		setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),getLength(),"#agentOrder_main_div"));
		return SUCCESS;
	}
	
	/**
	 * <p>只是修改订单信息</p> 
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 */
	public String saveAgentOrder()throws Exception{
		if(agentOrderCondition == null || agentOrderCondition.getOrderId() == null){
			 if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("丢失订单信息", false);
			 }else{
				 return returnAjaxError("Lost order ID", false);
			 }
		}
		try{
			AgentOrder agentOrder = agentOrderService.loadAgentOrder(agentOrderCondition.getOrderId());
			if(agentOrder == null){
				 if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
					 return returnAjaxError("数据库订单丢失", false);
				 }else{
					 return returnAjaxError("Lost order in dataBase", false);
				 }
			}
			agentOrder.setStatus(agentOrderCondition.getStatus());
			agentOrder.setUpdateTime(new Date());
			agentOrder.setUpdater((User)getSessionValue(HTTP_SESSION_USER));
			agentOrderService.saveAgentOrder(agentOrder);
			return returnAjaxSuccess("", false);
			
		}catch(Exception e){
			e.printStackTrace();
			return returnAjaxError(e.getMessage(), false);
		}
		
	}
	
	/**
	 * <p>订单审批通过</p> 
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       String    
	 */
	public String auditAgentOrder()throws Exception{
		if(agentOrderCondition == null || agentOrderCondition.getOrderId() == null){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("丢失订单信息", false);
			 }else{
				 return returnAjaxError("Lost order ID", false);
			 }
		}
		try{
			AgentOrder agentOrder = agentOrderService.loadAgentOrder(agentOrderCondition.getOrderId());
			if(agentOrder == null){
				if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
					 return returnAjaxError("数据库订单丢失", false);
				 }else{
					 return returnAjaxError("Lost order in dataBase", false);
				 }
			}
			agentOrder.setStatus(SYSConstant.AGENT_ORDER_STATUS_OPENING);
			agentOrder.setUpdateTime(new Date());
			agentOrder.setUpdater((User)getSessionValue(HTTP_SESSION_USER));
			agentOrderService.auditOrder(agentOrder);
			return returnAjaxSuccess("", false);
			
		}catch(Exception e){
			e.printStackTrace();
			return returnAjaxError(e.getMessage(), false);
		}
	}
	
	public String agentOrderDetail()throws Exception{
		if(agentOrderCondition == null || agentOrderCondition.getOrderId() == null){
			 if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("丢失订单信息", false);
			 }else{
				 return returnAjaxError("Lost order ID", false);
			 }
		}
		agentOrderCondition = agentOrderService.loadAgentOrder(agentOrderCondition.getOrderId());
		if(agentOrderCondition == null){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 return returnAjaxError("数据库订单丢失", false);
			 }else{
				 return returnAjaxError("Lost order in dataBase", false);
			 }
		}
		if(agentOrderCondition == null || StringUtil.isEmpty(agentOrderCondition.getSvn())){
			throw new Exception("lost svnNum");
		}
		svnInfo = svnInfoService.loadSvnInfoBySvn(agentOrderCondition.getSvn(),null);
		return SUCCESS;
	}
	
	public String userInfo()throws Exception{
		if(agentOrderCondition == null || StringUtil.isEmpty(agentOrderCondition.getSvn())){
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				 throw new Exception("丢失 svnNum");
			 }else{
				 throw new Exception("Lost svnNum");
			 }
		}
		svnInfo = svnInfoService.loadSvnInfoBySvn(agentOrderCondition.getSvn(),null);
		return SUCCESS;
	}
	
	public AgentOrderService getAgentOrderService() {
		return agentOrderService;
	}

	public void setAgentOrderService(AgentOrderService agentOrderService) {
		this.agentOrderService = agentOrderService;
	}

	public AgentOrder getAgentOrderCondition() {
		return agentOrderCondition;
	}

	public void setAgentOrderCondition(AgentOrder agentOrderCondition) {
		this.agentOrderCondition = agentOrderCondition;
	}

	public List<AgentOrder> getAgentOrderList() {
		return agentOrderList;
	}

	public void setAgentOrderList(List<AgentOrder> agentOrderList) {
		this.agentOrderList = agentOrderList;
	}

	public SvnInfoService getSvnInfoService() {
		return svnInfoService;
	}

	public void setSvnInfoService(SvnInfoService svnInfoService) {
		this.svnInfoService = svnInfoService;
	}

	public SvnInfo getSvnInfo() {
		return svnInfo;
	}

	public void setSvnInfo(SvnInfo svnInfo) {
		this.svnInfo = svnInfo;
	}

	
}
