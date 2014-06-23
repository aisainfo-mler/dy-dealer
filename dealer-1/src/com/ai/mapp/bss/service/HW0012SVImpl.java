package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.AgentOrder;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.service.AgentOrderService;

@Service(value="hw0012Service")
@Scope(value="singleton")
public class HW0012SVImpl extends ISVTemplate {
	
	@Autowired
	private AgentOrderService agentOrderService; 
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0012.Response rsp = new com.ai.mapp.model.HW0012.Response();
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		return rsp.toXMLString();
	}

	@Override
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		com.ai.mapp.model.HW0012.Request req = com.ai.mapp.model.HW0012.Request
			.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		
		Map<String,String> goods = new HashMap<String, String>(0);
		com.ai.mapp.model.HW0012.GoodList gl = req.getGoodList();
		
		if(req.getGoodList() != null)
		{
			com.ai.mapp.model.HW0012.Good[] goodList = gl.getGood();
			if(goodList != null && goodList.length>0)
			{
				for(com.ai.mapp.model.HW0012.Good g : goodList)
				{
					if(StringUtil.isEmpty(g.getId()) == false && StringUtil.isEmpty(g.getValue()) == false)
					goods.put(g.getId(), g.getValue());
				}
			}
		}
		
		agentOrderService.completedOrder(req.getOrderCode(), goods);
		
		param.setIfSuccess(true);
		param.setResult(req.getOrderCode());
		return param;
	}
	
}
