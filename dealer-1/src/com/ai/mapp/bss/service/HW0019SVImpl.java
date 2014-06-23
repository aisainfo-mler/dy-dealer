package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.entity.SvnInfo;
import com.ai.mapp.sys.service.GoodsInfoService;
import com.ai.mapp.sys.service.OrderInfoService;
import com.ai.mapp.sys.util.SYSConstant;

@Service(value="hw0019Service")
@Scope(value="singleton")
public class HW0019SVImpl extends ISVTemplate {
	
	@Autowired
	private GoodsInfoService goodsInfoService; 
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0019.Response rsp = new com.ai.mapp.model.HW0019.Response();
		
		Collection<GoodsInfo> goods = (Collection<GoodsInfo>)param.getResult();
		
		if(goods != null || goods.isEmpty() == false)
		{
			Map<String,Collection<GoodsInfo>> good_map = new LinkedHashMap<String,Collection<GoodsInfo>>(0);
			
			for(GoodsInfo good : goods)
			{
				if(good_map.get(good.getType()) == null)
					good_map.put(good.getType(), new HashSet<GoodsInfo>(0));
				good_map.get(good.getType()).add(good);
			}
			
			for(String key : good_map.keySet())
			{
				
				if(good_map.get(key) == null || good_map.get(key).isEmpty())
					continue;
				
				com.ai.mapp.model.HW0019.GoodType gt = new com.ai.mapp.model.HW0019.GoodType();
				gt.setTypeValue(key);
				
				com.ai.mapp.model.HW0019.GoodList gl = new com.ai.mapp.model.HW0019.GoodList();
				
				for(GoodsInfo good : good_map.get(key))
				{
					com.ai.mapp.model.HW0019.Good g = new com.ai.mapp.model.HW0019.Good();
					g.setGoodId(good.getId().toString());
					g.setGoodName(good.getName());
					g.setGoodPrice(good.getPrice() == null ? "0" : good.getPrice().toString());
					g.setGoodSalePrice(good.getSalePrice() == null ? "0" : good.getSalePrice().toString());
					if(good.getListpic() != null)
					{
						g.setImage(good.getListpic().getFileUpload().getFilePath());
					}
					
					gl.addGood(g);
				}
				
				gt.setGoodList(gl);
				
				rsp.addGoodType(gt);
			}
			
			
		}
		
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);
		return rsp.toXMLString();
	}

	@Override
	public Object error(ParamObject param) throws Exception {
		return BSSConstantError.error(BSSConstantError.CODE_DATA_ERROR,param.getErrorInfo());
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		com.ai.mapp.model.HW0019.Request req = com.ai.mapp.model.HW0019.Request
			.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		GoodsInfo condition = new GoodsInfo();
		if(StringUtil.isEmpty(req.getGoodId()) == false)
			condition.setId(Long.valueOf(req.getGoodId()));
		if(StringUtil.isEmpty(req.getGoodType()) == false)
			condition.setType(req.getGoodType());
		
		Collection<GoodsInfo> goods = goodsInfoService.listAllGoodsInfos(condition);
		
		param.setIfSuccess(true);
		param.setResult(goods);
		return param;
	}
	
}
