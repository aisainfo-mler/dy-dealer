package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.service.GoodsInfoService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0019Request;
import com.ailk.yd.mapp.client.model.HW0019Response;
import com.ailk.yd.mapp.client.model.HW0019Response.Good;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-7 下午05:28:17
 * 类说明:库存商品列表展示
 */

@Service("hw0019")
@Action(bizcode="hw0019",userCheck=true)
@Scope("prototype")
public class HW0019Action extends AbstractYDBaseActionHandler<HW0019Request, HW0019Response>{

	@Autowired
	private GoodsInfoService goodsInfoService; 
	
	public GoodsInfoService getGoodsInfoService() {
		return goodsInfoService;
	}

	public void setGoodsInfoService(GoodsInfoService goodsInfoService) {
		this.goodsInfoService = goodsInfoService;
	}

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		GoodsInfo condition = new GoodsInfo();
		if(StringUtil.isEmpty(this.request.getGoodId()) == false)
			condition.setId(Long.valueOf(this.request.getGoodId()));
		if(StringUtil.isEmpty(this.request.getGoodType()) == false)
			condition.setType(this.request.getGoodType());
		
		Collection<GoodsInfo> goods = goodsInfoService.listAllGoodsInfos(condition);
		if(goods != null || goods.isEmpty() == false)
		{
			Map<String,List<Good>> good_map = new LinkedHashMap<String,List<Good>>(0);
			Good g = null;
			for(GoodsInfo good : goods)
			{
				if(good_map.get(good.getType()) == null)
					good_map.put(good.getType(), new ArrayList<Good>(0));
				g = new Good();
				g.setBssGoodId(good.getBssId());
				g.setGoodId(good.getId().toString());
				g.setGoodName(good.getName());
				g.setGoodPrice(good.getPrice() == null?null:good.getPrice().toString());
				g.setGoodSalePrice(good.getSalePrice() == null?null:good.getSalePrice().toString());
				g.setGoodStatus(good.getStatus());
				g.setImage(good.getBssUrl());
				good_map.get(good.getType()).add(g);
			}
			this.response.setGoodTypeList(good_map);
		}
	}

}
