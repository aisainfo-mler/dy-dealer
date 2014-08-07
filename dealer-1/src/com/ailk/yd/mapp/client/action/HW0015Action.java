package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
import com.ailk.ts.dal.ibatis.model.Repository;
import com.ailk.ts.dal.ibatis.model.SelfDefineRep;
import com.ailk.ts.ibatis.service.RepService;
import com.ailk.ts.ibatis.service.RepositoryService;
import com.ailk.yd.mapp.client.model.HW0015Request;
import com.ailk.yd.mapp.client.model.HW0015Response;
import com.ailk.yd.mapp.client.model.HW0015Response.Good;
import com.ailk.yd.mapp.client.model.HW0015Response.GoodType;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-6 下午10:40:57
 * 类说明:
 */

@Service("hw0015")
@Action(bizcode = "hw0015", userCheck = true)
@Scope("prototype")
public class HW0015Action extends AbstractYDBaseActionHandler<HW0015Request, HW0015Response> {

	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private RepService repService;
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		
		//User user = userService.loadUserByUserCode(this.getUserinfo().getUserName());
		String userCode = this.getUserinfo().getUserName();
		Integer userId = this.getUserinfo().getUserId();
		
		List<Repository> reps = repositoryService.getRepsByUserId(userId.longValue());
		
		if(reps == null || reps.size() == 0){
			throw new Exception("该代理商不存在仓库");
		}
		
		Repository rep = reps.get(0);
		
		Long gId = StringUtil.isEmpty(this.request.getGoodId())?null:Long.valueOf(this.request.getGoodId());
		
		
		SelfDefineRep cond = new SelfDefineRep();
		cond.setSkuId(gId);
		cond.setRepositoryCode(rep.getRepCode());
		
		List<SelfDefineRep> unsale= repService.getSelfRep(cond, -1, 0);
		
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
		GoodsInfo condition = new GoodsInfo();
		condition.setGoodIds(count_map.keySet());
		
		Collection<GoodsInfo> goods = goodsInfoService.listAllGoodsInfos(condition);
		if(goods!=null){
			for(GoodsInfo good : goods)
			{
				good.setGoodNum(count_map.get(good.getId()));
			}
		}
		
		//------------------------
		List<GoodType>  gts = new ArrayList<GoodType>();
		
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
					GoodType gt = new GoodType();
					gt.setTypeValue(type);
					
					List<Good> gl = new ArrayList<Good>();
					
					for(GoodsInfo good : good_map.get(type) )
					{
						if(good.getGoodNum() == null || good.getGoodNum().length == 0)
							continue;
						
						Good g = new Good();
						
						Long unsaleL = good.getGoodNum()[0] == null ? 0 : good.getGoodNum()[0];
						Long saleL = good.getGoodNum()[1] == null ? 0 : good.getGoodNum()[1];
						Long cancelL = good.getGoodNum()[2] == null ? 0 : good.getGoodNum()[2];
						
						Long total = unsaleL+saleL+cancelL;
						g.setGoodName(good.getName() == null ? "" : good.getName());
						g.setUnSale(unsaleL.toString());
						g.setSale(saleL.toString());
						g.setAll(total.toString());
						
						gl.add(g);
					}
					
					gt.setGoodList(gl);
					gts.add(gt);
				}
				this.response.setGoodTypes(gts);
			}
		}
		
	}

}
