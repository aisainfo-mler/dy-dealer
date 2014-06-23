package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.service.ContractService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.yd.mapp.tibco.model.YD9004.YD9004Request;
import com.ailk.yd.mapp.tibco.model.YD9004.YD9004Request.Contract;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-4 下午05:28:04
 * 类说明:套餐信息更新(包括合约) tibco系统向dealer系统发起
 */

@Service("yd9004")
@Action(bizcode="yd9004",userCheck=false)
@Scope("prototype")
public class YD9004Action extends AbstractYDBaseActionHandler<YD9004Request,IBody>{
	@Autowired
	private ContractService  contractService;
	
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		if(this.request.getContractList() != null && this.request.getContractList().size() != 0){
			List<Contract> contractList = this.request.getContractList();
			com.ai.mapp.sys.entity.Contract localC = null;
			List<com.ai.mapp.sys.entity.Contract> cList = new ArrayList<com.ai.mapp.sys.entity.Contract>();
			for(Contract item:contractList){
				localC = new com.ai.mapp.sys.entity.Contract();
				localC.setOtherService(item.getAddedService());
				localC.setFlow(item.getData());
				localC.setFlowRate(item.getFlowFee() != null ? item.getFlowFee().toString():null);
				localC.setAnswerFree(item.getFreeCall());
				localC.setLowcost(item.getLowCost() != null ? item.getLowCost().longValue():null);
				localC.setOtherFee(item.getOtherFee() != null ? item.getOtherFee().toString():null);
				localC.setSms(item.getSms());
				localC.setVideoFee(item.getVideoFee() != null ? item.getVideoFee().toString():null);
				localC.setVideoMin(item.getVideoLen());
				localC.setCallRate(item.getVoiceFee() != null ? item.getVoiceFee().toString():null);
				localC.setDomesticCall(item.getVoiceLen());
				localC.setOptType(item.getOptType());
				localC.setMms(item.getMms());
				localC.setName(item.getName());
				cList.add(localC);
			}
//			contractService.
		}else{
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"无套餐列表或列表为空");
		}
		
	}

	public ContractService getContractService() {
		return contractService;
	}

	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	
	

}
