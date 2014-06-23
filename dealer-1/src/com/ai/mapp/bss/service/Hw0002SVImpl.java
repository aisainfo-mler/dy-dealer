package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.Contract;
import com.ai.mapp.sys.entity.Mobile;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.service.MobileService;
import com.ai.mapp.sys.service.ProductService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.util.JsonUtil;

@Service("hw0002Service")
@Scope("singleton")
public class Hw0002SVImpl extends ISVTemplate {

	@Autowired
	private MobileService mobileService;

	@Autowired
	private ProductService productService;

	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0002.Response rsp = new com.ai.mapp.model.HW0002.Response();
		
		Collection<Mobile> mobiles = (Collection<Mobile>) param.getResult();

		if (mobiles != null && mobiles.isEmpty() == false) 
		{
			com.ai.mapp.model.HW0002.PhoneList pl = new com.ai.mapp.model.HW0002.PhoneList();
			
			for(Mobile mobile : mobiles)
			{
				com.ai.mapp.model.HW0002.Phone phone = new com.ai.mapp.model.HW0002.Phone();
				if(StringUtils.isNotBlank(mobile.getPicPathJson())){
					LinkedHashMap pic = (LinkedHashMap) JsonUtil.fromJsonString(mobile.getPicPathJson(), LinkedHashMap.class);
					if(!pic.isEmpty()){
						String picUrl = null;
						for (Iterator it = pic.keySet().iterator(); it
								.hasNext();) {
							String key = (String) it.next();//key为缩略图，value为大图
							if(picUrl==null){
								//约定取第一张照片的缩略图放到列表界面
								picUrl = key;
							}
						}
						phone.setListPic(picUrl);
					}
				}
				else
				phone.setListPic("");
				phone.setBrand(StringUtil.isEmpty(mobile.getTerminal_brand_s())?"-":mobile.getTerminal_brand_s());
				phone.setModel(mobile.getMobileModel());
				phone.setPhoneId(mobile.getMobileId().toString());
				
				if(mobile.getProduct() != null)
				{
					Product p = mobile.getProduct();
					Contract c = p.getContract();
					
					com.ai.mapp.model.HW0002.Product product = new com.ai.mapp.model.HW0002.Product();
					
					product.setActType(p.getActType());
					product.setAllFee(p.getAllFee() == null?"":String.valueOf(p.getAllFee()*1000));
					product.setBackMonth(p.getThawMon() == null?"":String.valueOf(p.getThawMon()*1000));
					product.setContractId(c == null?"":c.getContractId().toString());
					product.setPhoneId(mobile == null?"":mobile.getMobileId().toString());
					product.setPreStore(p.getPrestore() == null?"":String.valueOf(p.getPrestore()*1000));
					product.setPreStoreDesc(p.getStoredDesc() == null?"":p.getStoredDesc().toString());
					product.setPrice(c == null || c.getContractFee() == null?"":String.valueOf(c.getContractFee()*1000));
					product.setProductDesc(c == null || c.getName() == null?"":c.getName().toString());
					product.setProductFee(p.getPrice() == null?"":String.valueOf(p.getPrice()*1000));
					product.setProductId(p.getRangeId() == null?"":p.getRangeId().toString());
					product.setRecommend(StringUtil.isEmpty(p.getRecommend())?"":p.getRecommend().toString());
					product.setResFee(p.getResourcesFee() == null?"":String.valueOf(p.getResourcesFee()*1000));
					product.setTerm(p.getPeriod());
					
					phone.setProduct(product);
				}
				pl.addPhone(phone);
			}
			
			rsp.setPhoneList(pl);
		}

		
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);

		return rsp.toXMLString();
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		String requestContent = (String) param.getParameter(BSSConstantParam.REQUESTCONTENT);
		com.ai.mapp.model.HW0002.Request request = com.ai.mapp.model.HW0002.Request.unmarshal(new StringReader(requestContent));
		
		Collection<Mobile> mobiles = new ArrayList<Mobile>(0);
		
		if("1".equals(request.getRecommend()))
		{
			Product condition = new Product();
			if(StringUtil.isEmpty(request.getMobileModel()) == false)
			{
				Mobile mobile = new Mobile();
				mobile.setMobileModel(request.getMobileModel());
				condition.setMobile(mobile);
			}
			condition.setRecommend(request.getRecommend());
			condition.setSpecialSearch(Product.SPECIALSEARCH_HAS_MOBILE);
			
			Collection<Product> products = productService.listProducts(condition, Integer.valueOf(request.getStart()), Integer.valueOf(request.getSize()));
		
			for(Product p : products)
			{
				Mobile mobile = p.getMobile();
				mobile.setProduct(p);
				mobiles.add(mobile);
			}
			
			param.setIfSuccess(true);
			param.setResult(mobiles);
			return param;
		}
		else
		{
			Mobile condition = new Mobile();
			if(StringUtil.isEmpty(request.getMobileModel()) == false)
			{
				condition.setMobileModel(request.getMobileModel());
			}
			condition.setSpecialSearch(Mobile.SPECIALSEARCH_POSTPAY_HAS_PRODUCT);
			mobiles = mobileService.listMobile(condition, Integer.valueOf(request.getStart()), Integer.valueOf(request.getSize()));
			for(Mobile m : mobiles)
			{
				m.setProduct(productService.getRecommendProductByMobile(m.getMobileId()));
			}
		}
		
		param.setIfSuccess(true);
		param.setResult(mobiles);
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
