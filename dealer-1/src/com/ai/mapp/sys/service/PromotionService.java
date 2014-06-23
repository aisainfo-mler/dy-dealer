package com.ai.mapp.sys.service;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.sys.dao.PromotionDao;
import com.ai.mapp.sys.entity.Promotion;
import com.ai.mapp.sys.entity.SmallLocalFile;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-8 下午02:40:13
 * 类说明:
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class PromotionService {
	public final Log log = LogFactory.getLog(PromotionService.class);
	
	@Autowired
	private PromotionDao promotionDao;
	
	@Autowired
	private SmallLocalFileService smallLocalFileService;

	public PromotionDao getPromotionDao() {
		return promotionDao;
	}

	public void setPromotionDao(PromotionDao promotionDao) {
		this.promotionDao = promotionDao;
	}
	
	public SmallLocalFileService getSmallLocalFileService() {
		return smallLocalFileService;
	}

	public void setSmallLocalFileService(SmallLocalFileService smallLocalFileService) {
		this.smallLocalFileService = smallLocalFileService;
	}

	public Collection<Promotion> listPromotions(Promotion promotion,int start,int limit)throws Exception{
		log.debug(promotion==null?"promotion is null":promotion.toString());
		Collection<Promotion> c = null;
		if(start < 0 || limit <= 0){
			c = promotionDao.listAll(promotion);
		}else{
			c = promotionDao.list(promotion, start, limit);
		}
		return c;
	}
	
	public void savePromotion(Promotion promotion)throws Exception{
		
		log.debug(promotion==null?"product is null":promotion.toString());
		Promotion saveEntity = null;
		
		if(promotion.getId() != null){
			saveEntity = loadPromotionById(promotion.getId());
			saveEntity.setEffDate(promotion.getEffDate());
			saveEntity.setExpDate(promotion.getExpDate());
			saveEntity.setRemark(promotion.getRemark());
			saveEntity.setStatus(promotion.getStatus());
			saveEntity.setType(promotion.getType());
			saveEntity.setFileIndex(promotion.getFileIndex());
			saveEntity.setCreator(promotion.getCreator());
			
			//删除老照片
			if(promotion.getImgFile() != null && saveEntity.getFileId() != null){
				smallLocalFileService.delete(saveEntity.getFileId());
			}
			
			if(promotion.getImg4File() != null && saveEntity.getFile4Id() != null){
				smallLocalFileService.delete(saveEntity.getFile4Id());
			}
			
			if(promotion.getImg5File() != null && saveEntity.getFile5Id() != null){
				smallLocalFileService.delete(saveEntity.getFile5Id());
			}
			
		}else{
			saveEntity = promotion;
			saveEntity.setCreateTime(new Date());
		}
		
		if(promotion.getImgFile() != null){
			Long fileId = smallLocalFileService.save(promotion.getImgFile());
			saveEntity.setFileId(fileId);
		}
		
		if(promotion.getImg4File() != null){
			Long file4Id = smallLocalFileService.save(promotion.getImg4File());
			saveEntity.setFile4Id(file4Id);
		}
		
		if(promotion.getImg5File() != null){
			Long file5Id = smallLocalFileService.save(promotion.getImg5File());
			saveEntity.setFile5Id(file5Id);
		}
	
		promotionDao.save(saveEntity);
	}
	
	public Promotion loadPromotionById(Long id){
		return promotionDao.get(id);
	}
	
	public int countPromotion(Promotion promotion) throws Exception {
		return promotionDao.count(promotion);
	}
	
	/**
	 * <p>描述: 删除老图片</p> 
	 * @param promotionId  
	 * @author        Zhengwj
	 * @Date          2014-5-9 下午02:12:13
	 */
	public void deleteImage(Long promotionId){
		
	}
}
