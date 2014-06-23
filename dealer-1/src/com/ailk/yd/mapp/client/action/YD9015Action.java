package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.entity.OrderDetail;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.OrderItem;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.GoodsInfoService;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.yd.mapp.tibco.model.YD9015.YD9015Request;
import com.ailk.yd.mapp.tibco.model.YD9015.YD9015Request.Good;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-7 下午07:52:56
 * 类说明:订购库存商品订单回调（TIBCO配货完成回调）
 */

@Service("yd9015")
@Action(bizcode="yd9015",userCheck=false)
@Scope("prototype")
public class YD9015Action extends AbstractYDBaseActionHandler<YD9015Request, IBody>{
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	public OrderInfoService getOrderInfoService() {
		return orderInfoService;
	}

	public void setOrderInfoService(OrderInfoService orderInfoService) {
		this.orderInfoService = orderInfoService;
	}

	public GoodsInfoService getGoodsInfoService() {
		return goodsInfoService;
	}

	public void setGoodsInfoService(GoodsInfoService goodsInfoService) {
		this.goodsInfoService = goodsInfoService;
	}

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
//		IUserinfo user = (IUserinfo)MappContext.getAttribute(MappContext.MAPPCONTEXT_USER);
		OrderInfo order = orderInfoService.loadOrderInfo(Long.parseLong(this.request.getOrderId()));
		if(order == null){
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,LanguageInfo.YD9015_NOORDER);
		}
		if(!SYSConstant.ORDER_STATUS_PROCESSING.equals(order.getStatus())){
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,LanguageInfo.YD9015_ORDER_WRONG_STATUS + "," + LanguageInfo.YD9015_ORDER_STATUS + ":" + SYSConstant.orderTypes.get(order.getStatus() + SYSConstant.LANGUAGE_ENGLISH));
		}
		if(!SYSConstant.ORDER_PLACE_TIBCO_YES.equals(order.getPlaceTibco())){
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,LanguageInfo.YD9015_ORDER_NO_TIBCO);
		}
		List<Good> goods = this.request.getGoods();
		List<OrderItem> items = new ArrayList<OrderItem>();
		OrderItem item = null;
		GoodsInfo g = null;
		if(goods != null && goods.isEmpty() == false){
			for(Good good:goods){
				item = new OrderItem();
				g = goodsInfoService.loadGoodsInfoByBssId(good.getGoodId());
				if(g == null){
					throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,LanguageInfo.YD9015_NOGOOD);
				}
				item.setGood(g);
				item.setItemValue(good.getKeyValue());
				items.add(item);
			}
			
		}
		orderInfoService.tibcoSendOrder(order,items, this.request.getSendTime(), this.request.getSn(), this.request.getCompany(), null);
//		System.out.println(000000000);
	}

}
