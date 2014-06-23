package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.entity.Promotion;
import com.ai.mapp.sys.entity.SmallLocalFile;
import com.ai.mapp.sys.service.PromotionService;
import com.ai.mapp.sys.service.SmallLocalFileService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.yd.mapp.client.model.HW0031Request;
import com.ailk.yd.mapp.client.model.HW0031Response;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-12 下午02:00:31
 * 类说明:
 */

@Service("hw0031")
@Action(bizcode = "hw0031", userCheck = true)
@Scope("prototype")
public class HW0031Action extends AbstractYDBaseActionHandler<HW0031Request, HW0031Response>{
	
	@Autowired
	private PromotionService promotionService;
	
//	@Autowired
//	private YD0006Action yd0006;
	
	@Autowired
	private SmallLocalFileService smallLocalFileService;
	
	public PromotionService getPromotionService() {
		return promotionService;
	}

	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		int start = this.request.getStart() == null ? 0 : this.request.getStart();
		int limit = this.request.getSize() == null ? 16 : this.request.getSize();
		Promotion condition = new Promotion();
		condition.setType(this.request.getPromotionType());
		Collection<Promotion> promotions = promotionService.listPromotions(condition, start, limit);
		
		if(promotions != null && promotions.isEmpty() == false){
			com.ailk.yd.mapp.client.model.HW0031Response.Promotion p = null;
			SmallLocalFile image = null;
			List<com.ailk.yd.mapp.client.model.HW0031Response.Promotion> ps = new ArrayList<com.ailk.yd.mapp.client.model.HW0031Response.Promotion>();
			for(Promotion promotion:promotions){
				p = new com.ailk.yd.mapp.client.model.HW0031Response.Promotion();
				p.setPromotionType(promotion.getType());
				p.setRemark(promotion.getRemark());
				p.setImageIndex(promotion.getFileIndex());
//				if(promotion.getFileId() != null){
//					image = smallLocalFileService.getById(promotion.getFileId());
//					p.setExt(image.getFileExt());
//					p.setImageData(p.getImageData());
//				}
				Map<String,Long> imageMap = new HashMap<String, Long>();
				imageMap.put("0",promotion.getFileId());
				imageMap.put("4",promotion.getFile4Id());
				imageMap.put("5",promotion.getFile5Id());
				p.setImageMap(imageMap);
				ps.add(p);
			}
		
			this.response.setPromotionImages(ps);
		}
		
	}

}
