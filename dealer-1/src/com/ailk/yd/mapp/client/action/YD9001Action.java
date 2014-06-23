package com.ailk.yd.mapp.client.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.service.DealerDataService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.yd.mapp.tibco.model.YD9001.YD9001Request;

/**
 * @author mler
 * @version 创建时间：2014-5-5 下午12:24:27
 * 类说明:合约保存
 */

@Service("yd9001")
@Action(bizcode="yd9001",userCheck=false)
@Scope("prototype")
public class YD9001Action extends AbstractYDBaseActionHandler<YD9001Request,IBody>{
	@Autowired
	private DealerDataService dealerDataService;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		
		if(this.request.getProductFileUrl() == null)
			throw new Exception("无更新信息");
		
		
		dealerDataService.updateProductInfoByFile(this.request.getProductFileUrl());
		
	}
}
