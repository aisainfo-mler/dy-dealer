package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.sys.entity.CommonBean;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.yd.mapp.client.model.HW0034Request;
import com.ailk.yd.mapp.client.model.HW0034Response;
import com.ailk.yd.mapp.client.model.HW0034Response.Good;


/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-13 下午07:02:28
 * 类说明:代理商收货校验
 */

@Service("hw0034")
@Action(bizcode = "hw0034", userCheck = true)
@Scope("prototype")
public class HW0034Action extends AbstractYDBaseActionHandler<HW0034Request, HW0034Response> {
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}


	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}


	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		Map<String, Object> itemsMap = orderInfoService.getItemsInfoByOrderId(this.request.getOrderCode());
		List<CommonBean> goods = (List<CommonBean>)itemsMap.get("list");
		Map<Long,String> goodTranslate = (Map<Long,String>)itemsMap.get("goodMap");
		Map<Long,List<Good>> itemValues = new HashMap<Long, List<Good>>();
		Good lg = null; 
		if(goods != null && goods.isEmpty() == false){
			for(CommonBean good:goods){
				if(itemValues.get(Long.parseLong(good.getStr1())) == null){
					itemValues.put(Long.parseLong(good.getStr1()), new ArrayList<Good>());
				}
				lg = new Good();
				lg.setGoodId(Long.parseLong(good.getStr1()));
				lg.setGoodName(goodTranslate.get(Long.parseLong(good.getStr1())));
				lg.setGoodType(StringUtils.isNotEmpty(good.getStr6()) ? good.getStr6():SYSConstant.GOOD_TYPE_SIM);
				lg.setImei(good.getStr5());
				lg.setStartNum(good.getStr2());
				lg.setEndNum(good.getStr3());
				itemValues.get(Long.parseLong(good.getStr1())).add(lg);
			}
		}
		this.response.setItemValues(itemValues);
	}

}
