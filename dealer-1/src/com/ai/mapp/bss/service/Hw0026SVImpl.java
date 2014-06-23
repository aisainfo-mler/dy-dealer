package com.ai.mapp.bss.service;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.entity.ResultJson;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.service.AgentOrderService;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.util.LanguageInfo;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-12-7 下午04:38:34
 * 类说明:
 */

@Service("hw0026Service")
@Scope("singleton")
public class Hw0026SVImpl extends ISVTemplate {

	@Autowired
	private AgentOrderService agentOrderService; 
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0026.Response rsp = new com.ai.mapp.model.HW0026.Response();
		
		ResultJson result = (ResultJson)param.getResult();
		if(!result.getFlag()){
			rsp.setMSG(result.getMsg());
			rsp.setRspCode(BSSConstantError.CODE_DATA_ERROR);
		}
		else
		{
			rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		}
		
		return rsp.toXMLString();
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		String requestContent = (String) param.getParameter(BSSConstantParam.REQUESTCONTENT);
		com.ai.mapp.model.HW0026.Request request = com.ai.mapp.model.HW0026.Request.unmarshal(new StringReader(requestContent));
	ResultJson result = null;
	if(StringUtil.isEmpty(request.getOrderCode()) && StringUtil.isEmpty(request.getDealerOrderCode())){
		result = new ResultJson();
		result.setFlag(false);
		result.setMsg(LanguageInfo.ORDERCODE_IS_EMPTY);
	}else{
		String userCode = (String)param.getParameter(BSSConstantParam.USERCODE);
		if(!StringUtil.isEmpty(request.getOrderCode())){
			result = agentOrderService.cancelOrder(request.getOrderCode());
		}
		if(!StringUtil.isEmpty(request.getDealerOrderCode())){
			result = orderInfoService.cancelOrder(request.getDealerOrderCode(),userCode);
		}
	}
	
	param.setIfSuccess(true);
	param.setResult(result);
	return param;
	}

}
