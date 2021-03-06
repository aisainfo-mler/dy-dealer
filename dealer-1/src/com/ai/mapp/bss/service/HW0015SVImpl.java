package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.bss.entity.ParamObject;
import com.ai.mapp.bss.util.BSSConstantError;
import com.ai.mapp.bss.util.BSSConstantParam;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.service.GoodsInfoService;
import com.ai.mapp.sys.service.OrderItemService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.ts.dal.ibatis.model.Repository;
import com.ailk.ts.dal.ibatis.model.SelfDefineRep;
import com.ailk.ts.ibatis.service.RepService;
import com.ailk.ts.ibatis.service.RepositoryService;
import com.ailk.ts.ibatis.service.SkuEntityService;

@Service(value="hw0015Service")
@Scope(value="singleton")
public class HW0015SVImpl extends ISVTemplate {
	
	@Autowired
	private OrderItemService orderItemService; 
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private SkuEntityService skuEntityService;
	
	@Autowired
	private RepService repService;
	
	@Override
	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0015.Response rsp = new com.ai.mapp.model.HW0015.Response();
		
		Collection<GoodsInfo> goods = (Collection<GoodsInfo>)param.getResult();
		
		if(goods != null && goods.isEmpty() == false)
		{
			Map<String,List<GoodsInfo>> good_map = new HashMap<String, List<GoodsInfo>>(0);
			
			for(GoodsInfo good : goods )
			{
				if(good_map.get(good.getType()) == null)
					good_map.put(good.getType(),new ArrayList<GoodsInfo>(0));
				
				good_map.get(good.getType()).add(good);
			}
			
			if(good_map.isEmpty() == false)
			{
				for(String type : good_map.keySet())
				{
					com.ai.mapp.model.HW0015.GoodType gt = new com.ai.mapp.model.HW0015.GoodType();
					gt.setTypeValue(type);
					
					com.ai.mapp.model.HW0015.GoodList gl = new com.ai.mapp.model.HW0015.GoodList();
					
					for(GoodsInfo good : good_map.get(type) )
					{
						if(good.getGoodNum() == null || good.getGoodNum().length == 0)
							continue;
						
						com.ai.mapp.model.HW0015.Good g = new com.ai.mapp.model.HW0015.Good();
						
						Long unsale = good.getGoodNum()[0] == null ? 0 : good.getGoodNum()[0];
						Long sale = good.getGoodNum()[1] == null ? 0 : good.getGoodNum()[1];
						Long cancel = good.getGoodNum()[2] == null ? 0 : good.getGoodNum()[2];
						
						Long total = unsale+sale+cancel;
						g.setGoodName(good.getName() == null ? "" : good.getName());
						g.setUnSale(unsale.toString());
						g.setSale(sale.toString());
						g.setAll(total.toString());
						
						gl.addGood(g);
					}
					
					gt.setGoodList(gl);
					
					rsp.addGoodType(gt);
				}
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
		
		com.ai.mapp.model.HW0015.Request req = com.ai.mapp.model.HW0015.Request
			.unmarshal(new StringReader((String)param.getParameter(BSSConstantParam.REQUESTCONTENT)));
		
		String userCode = (String)param.getParameter(BSSConstantParam.USERCODE);
		
		Object uid = param.getParameter(BSSConstantParam.USERID);
		
		Long userId = Long.valueOf(uid.toString());
		
		List<Repository> reps = repositoryService.getRepsByUserId(userId);
		
		if(reps == null || reps.size() == 0){
			param.setIfSuccess(false);
			param.addError("this agent have no resposity");
			return param;
		}
		
		Repository rep = reps.get(0);
		
		Long gId = StringUtil.isEmpty(req.getGoodId())?null:Long.valueOf(req.getGoodId());
		
//		Map<Long,Long> unsale= orderItemService.countGood(userCode, req.getGoodType(),gId, SYSConstant.ITEM_STATUS_UNUSE);
//		
//		Map<Long,Long> sale= orderItemService.countGood(userCode, req.getGoodType(),gId, SYSConstant.ITEM_STATUS_USED);
//		
//		Map<Long,Long> cancel= orderItemService.countGood(userCode, req.getGoodType(),gId, SYSConstant.ITEM_STATUS_CANCEL);
		
		SelfDefineRep cond = new SelfDefineRep();
		cond.setSkuId(gId);
		cond.setRepositoryCode(rep.getRepCode());
		
		List<SelfDefineRep> unsale= repService.getSelfRep(cond, -1, 0);
		
//		Map<Long,Integer> sale = skuEntityService.countSkuEntityByGoodIdAndStatus(goodId, status, targetRep);
		
		
		Map<Long,Long[]> count_map = new HashMap<Long, Long[]>(0);
		
		if(unsale != null && unsale.isEmpty() == false)
		{
		
			for(SelfDefineRep tmp : unsale)
			{
				if(count_map.containsKey(tmp.getSkuId()) == false)
					count_map.put(tmp.getSkuId(), new Long[3]);
				
				if(count_map.get(tmp.getSkuId()) == null || count_map.get(tmp.getSkuId()).length == 0)
					count_map.put(tmp.getSkuId(), new Long[3]);
				
				count_map.get(tmp.getSkuId())[0] = tmp.getCount().longValue();
			}
		
		}
		/**
		if(unsale != null && unsale.isEmpty() == false)
		{
		
			for(Long goodId : unsale.keySet())
			{
				if(count_map.containsKey(goodId) == false)
					count_map.put(goodId, new Long[3]);
				
				if(count_map.get(goodId) == null || count_map.get(goodId).length == 0)
					count_map.put(goodId, new Long[3]);
				
				count_map.get(goodId)[0] = unsale.get(goodId);
			}
		
		}
		
		if(sale != null && sale.isEmpty() == false)
		{
			for(Long goodId : sale.keySet())
			{
				if(count_map.containsKey(goodId) == false)
					count_map.put(goodId, new Long[3]);
				
				if(count_map.get(goodId) == null || count_map.get(goodId).length == 0)
					count_map.put(goodId, new Long[3]);
				
				count_map.get(goodId)[1] = sale.get(goodId);
			}
		}
		
		if(cancel != null && cancel.isEmpty() == false)
		{
			for(Long goodId : cancel.keySet())
			{
				if(count_map.containsKey(goodId) == false)
					count_map.put(goodId, new Long[3]);
				
				if(count_map.get(goodId) == null || count_map.get(goodId).length == 0)
					count_map.put(goodId, new Long[3]);
				
				count_map.get(goodId)[2] = cancel.get(goodId);
			}
		}
*/
		GoodsInfo condition = new GoodsInfo();
		condition.setGoodIds(count_map.keySet());
		
		Collection<GoodsInfo> goods = goodsInfoService.listAllGoodsInfos(condition);
		if(goods!=null){
			for(GoodsInfo good : goods)
			{
				good.setGoodNum(count_map.get(good.getId()));
			}
			param.setResult(goods);
		}
		
		param.setIfSuccess(true);
		return param;
	}

	public OrderItemService getOrderItemService() {
		return orderItemService;
	}

	public void setOrderItemService(OrderItemService orderItemService) {
		this.orderItemService = orderItemService;
	}

	public GoodsInfoService getGoodsInfoService() {
		return goodsInfoService;
	}

	public void setGoodsInfoService(GoodsInfoService goodsInfoService) {
		this.goodsInfoService = goodsInfoService;
	}

	public RepositoryService getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public SkuEntityService getSkuEntityService() {
		return skuEntityService;
	}

	public void setSkuEntityService(SkuEntityService skuEntityService) {
		this.skuEntityService = skuEntityService;
	}

	public RepService getRepService() {
		return repService;
	}

	public void setRepService(RepService repService) {
		this.repService = repService;
	}
	
}
