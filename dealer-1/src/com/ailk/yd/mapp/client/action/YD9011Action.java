package com.ailk.yd.mapp.client.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.service.GoodsInfoService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.yd.mapp.tibco.model.YD9011.YD9011Request;
import com.ailk.yd.mapp.tibco.model.YD9011.YD9011Request.Good;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-7 上午10:32:35
 * 类说明:批量导入商品列表
 */

@Service("yd9011")
@Action(bizcode="yd9011",userCheck=false)
@Scope("prototype")
public class YD9011Action extends AbstractYDBaseActionHandler<YD9011Request, IBody>{
	
	@Autowired
	private GoodsInfoService goodsInfoService;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		
		if(this.request.getGoodList() != null && this.request.getGoodList().size() != 0){
			List<Good> goodList = this.request.getGoodList();
			com.ai.mapp.sys.entity.GoodsInfo localG = null;
			List<com.ai.mapp.sys.entity.GoodsInfo> gList = new ArrayList<com.ai.mapp.sys.entity.GoodsInfo>();
			Good item = null;
			for(int i = 0; i < goodList.size(); i++){
				item = goodList.get(i);
				localG = new com.ai.mapp.sys.entity.GoodsInfo();
				localG.setBssId(item.getGoodId());
				localG.setBssUrl(item.getPic());
				localG.setComments(item.getComment());
				localG.setCreateTime(new Date());
//				localG.setCreator(creator)
				localG.setName(item.getName());
				localG.setPrice(item.getPrice() == null?null:item.getPrice().longValue());
				localG.setSalePrice(item.getSalePrice() == null?null:item.getSalePrice().longValue());
				localG.setOptType(item.getOptType());
				gList.add(localG);
			}
			goodsInfoService.saveGoodBatch(gList);
		}else{
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"无套餐列表或列表为空");
		}
	}

	public GoodsInfoService getGoodsInfoService() {
		return goodsInfoService;
	}

	public void setGoodsInfoService(GoodsInfoService goodsInfoService) {
		this.goodsInfoService = goodsInfoService;
	}

}
