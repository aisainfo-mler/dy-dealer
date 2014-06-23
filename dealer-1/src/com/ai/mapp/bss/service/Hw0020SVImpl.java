package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.model.HW0020.GoodList;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.entity.OrderDetail;
import com.ai.mapp.sys.entity.OrderInfo;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.service.UserService;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-25 下午03:06:04
 * 类说明:
 */

@Service("hw0020Service")
@Scope("singleton")
public class Hw0020SVImpl extends ISVTemplate {
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private UserService userService;
	
	protected Object convertResponse(ParamObject param) throws Exception 
	{
		com.ai.mapp.model.HW0020.Response rsp = new com.ai.mapp.model.HW0020.Response();
		String orderNo = (String)param.getResult();
		rsp.setOrderCode(orderNo);
		
	
		
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		
		return rsp.toXMLString();
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		String requestContent = (String) param.getParameter(BSSConstantParam.REQUESTCONTENT);
		com.ai.mapp.model.HW0020.Request request = com.ai.mapp.model.HW0020.Request.unmarshal(new StringReader(requestContent));
		
		OrderDetail orderDetail = null;
		List<OrderDetail> list = new ArrayList<OrderDetail>();
		GoodList goodList = request.getGoodList();
		if(goodList != null){
			com.ai.mapp.model.HW0020.Good[] goods = goodList.getGood();
			if(goods != null && goods.length != 0){
				for(com.ai.mapp.model.HW0020.Good g : goods)
				{
					orderDetail = new OrderDetail();
					orderDetail.setCounts(Long.parseLong(g.getCount()));
					orderDetail.setGood(new GoodsInfo(Long.parseLong(g.getGoodId())));
					orderDetail.setCreateTime(new Date());
					list.add(orderDetail);
				}
			}
			
		}
		String userCode = (String)param.getParameter(BSSConstantParam.USERCODE);
		
		User user = userService.loadUserByUserCode(userCode);
		
		OrderInfo orderInfo = orderInfoService.generateOrder(list, user);
		
		param.setIfSuccess(true);
		param.setResult(orderInfo.getSerialNumber());

//		Product condition = new Product();
		
//		if(StringUtils.isEmpty(request.getPhoneId()) ==  false)
//			condition.setMobile(new Mobile(Long.valueOf(request.getPhoneId())));
//		if(StringUtils.isEmpty(request.getProductType()) ==  false)
//			condition.setActType(request.getProductType());
//		if(StringUtils.isEmpty(request.getRecommend()) ==  false)
//			condition.setRecommend(request.getRecommend());
//		if(StringUtils.isEmpty(request.getProductId()) ==  false)
//			condition.setRangeId(Long.valueOf(request.getProductId()));
//		
//		/** 与付费是不存在卡的,0 预付费，1,带终端后付费 ，2，后付费 sim，3 data-sim 都是不带终端的**/
//		if ("0".equals(request.getPayType()))
//		{
//			condition.setPayType("0");
//			condition.setSpecialSearch(Product.SPECIALSEARCH_NOT_HAS_MOBILE);
//		}
//		else if("1".equals(request.getPayType()))
//		{
//			condition.setPayType("1");
//			condition.setSpecialSearch(Product.SPECIALSEARCH_HAS_MOBILE);
//		}
//		else if("2".equals(request.getPayType()))
//		{
//			condition.setPayType("2");
//			condition.setSpecialSearch(Product.SPECIALSEARCH_NOT_HAS_MOBILE);
//		}
//		else if("3".equals(request.getPayType()))
//		{
//			condition.setPayType("3");
//			condition.setSpecialSearch(Product.SPECIALSEARCH_NOT_HAS_MOBILE);
//		}
//		else
//			throw new Exception("payType " + LanguageInfo.CANNOT_SURE);
//			
//		
//		Collection<Product> ranges = productService.listAllProducts(condition);
//		
//		param.setIfSuccess(true);
//		param.setResult(ranges);
		return param;
	}
	
	@Override
	/**
	 * 错误处理
	 */
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}
	
}
