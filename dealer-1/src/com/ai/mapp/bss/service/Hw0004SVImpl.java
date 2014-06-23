package com.ai.mapp.bss.service;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.Contract;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.service.ContractService;
import com.ai.mapp.sys.service.ProductService;

@Service("hw0004Service")
@Scope("singleton")
public class Hw0004SVImpl extends ISVTemplate {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ContractService contractService;

	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0004.Response rsp = new com.ai.mapp.model.HW0004.Response();
		
		com.ai.mapp.model.HW0004.DataList dl = new com.ai.mapp.model.HW0004.DataList();
		
		Product product = (Product) param.getResult();
		
		if(product != null && product.getContract() != null )
		{
			com.ai.mapp.model.HW0004.Item item = new com.ai.mapp.model.HW0004.Item();
			
			Contract contract = product.getContract();
			
			com.ai.mapp.model.HW0004.Include in = new com.ai.mapp.model.HW0004.Include();
			in.setPrice(product.getPrice() == null?"":product.getPrice().toString());
			in.setValidDate(product.getValidTime() == null?"":DateUtils.getDateString(product.getValidTime(),"yyyy-MM-dd"));
			in.setInValidDate(product.getInvalidTime() == null?"":DateUtils.getDateString(product.getInvalidTime(),"yyyy-MM-dd"));
			in.setFlowSize(contract.getFlow() == null?"":contract.getFlow().toString());
			in.setFreeCall(contract.getAnswerFree() == null?"":contract.getAnswerFree().toString());
			in.setSms(contract.getSms() == null?"":contract.getSms().toString());
			in.setVoiceLen(contract.getDomesticCall() == null?"":contract.getDomesticCall().toString());
			in.setLowCost(contract.getLowcost() == null?"":contract.getLowcost().toString());
			in.setName(contract.getName() == null?"":contract.getName().toString());
			in.setValidMonth("1");
			item.setInclude(in);
			
			com.ai.mapp.model.HW0004.Charge charge = new com.ai.mapp.model.HW0004.Charge();
			charge.setFlowFee(contract.getFlowRate() == null?"":contract.getFlowRate().toString());
			charge.setOtherFee(contract.getPresent() == null?"":contract.getPresent().toString());
			charge.setVideoFee(contract.getVideoFee() == null?"":contract.getVideoFee().toString());
			charge.setVoiceFee(contract.getCallRate() == null?"":contract.getCallRate().toString());
			item.setCharge(charge);
			
			com.ai.mapp.model.HW0004.Present present = new com.ai.mapp.model.HW0004.Present();
			present.setAddedService(contract.getOtherService() == null?"":contract.getOtherService().toString());
			present.setM("");
			present.setT("");
			present.setVideoLen(contract.getVideoMin() == null?"":contract.getVideoMin().toString());
			item.setPresent(present);
			
			dl.addItem(item);
		}
		
		rsp.setDataList(dl);
				
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);

		return rsp.toXMLString();
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		String requestContent = (String) param.getParameter(BSSConstantParam.REQUESTCONTENT);
		com.ai.mapp.model.HW0004.Request request = com.ai.mapp.model.HW0004.Request.unmarshal(new StringReader(requestContent));
		
		Product product = new Product();
		Contract contract = null;
		
		if(StringUtil.isEmpty(request.getProductId()) == false)
		{
			product = productService.loadProduct(Long.valueOf(request.getProductId()));
//			contract = product == null ? null : product.getContract();
		}
		 else if(StringUtil.isEmpty(request.getContractId()) == false)
		{
			contract = contractService.loadContractByContractId(request.getContractId());
			product.setContract(contract);
		}
		
		param.setIfSuccess(true);
		param.setResult(product);
		return param;
	}

	@Override
	/**
	 * 错误处理
	 */
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,
				param.getErrorInfo());
	}

}
