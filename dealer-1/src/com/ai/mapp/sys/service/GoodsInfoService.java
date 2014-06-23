package com.ai.mapp.sys.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.dao.GoodsInfoDao;
import com.ai.mapp.sys.entity.Contract;
import com.ai.mapp.sys.entity.GoodsInfo;
import com.ai.mapp.sys.entity.Mobile;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;

@Service
@Transactional
public class GoodsInfoService {
	
	public final Log log = LogFactory.getLog(GoodsInfoService.class);
	
	@Autowired
	private GoodsInfoDao goodsInfoDao;
	
	public Collection<GoodsInfo> listGoodsInfos(GoodsInfo goodsInfo,int start,int limit){
		try{
			log.debug(goodsInfo==null?"goodsInfo is null":goodsInfo.toString());
			
			Collection<GoodsInfo> c = goodsInfoDao.list(goodsInfo, start, limit);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Collection<GoodsInfo> listAllGoodsInfos(GoodsInfo goodsInfo){
		try{
			log.debug(goodsInfo==null?"goodsInfo is null":goodsInfo.toString());
			
			Collection<GoodsInfo> c = goodsInfoDao.listAll(goodsInfo);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveGoodsInfo(GoodsInfo goodsInfo){
		try{
			log.debug(goodsInfo==null?"goodsInfo is null":goodsInfo.toString());
			goodsInfoDao.save(goodsInfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteGoodsInfo(GoodsInfo goodsInfo){
		try{
			log.debug(goodsInfo==null?"goodsInfo is null":goodsInfo.toString());
			goodsInfoDao.delete(goodsInfo);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countGoodsInfo(GoodsInfo goodsInfo) throws RollbackException {
		try{
			return goodsInfoDao.count(goodsInfo);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	
	public GoodsInfo loadGoodsInfo(Long id)
	{
		return goodsInfoDao.get(id);
	}
	
	public GoodsInfo loadGoodsInfoByBssId(String bssId)
	{
		GoodsInfo cond = new GoodsInfo();
		cond.setBssId(bssId);
		Collection<GoodsInfo> goods = listAllGoodsInfos(cond);
		if(goods != null && goods.isEmpty() == false){
			return goods.iterator().next();
		}
		return null;
	}
	
	/**
	 * <p>描述:批量导入商品列表 </p>   
	 * @author        Zhengwj
	 * @Date          2014-5-8 上午10:22:53
	 */
	public void saveGoodBatch(List<GoodsInfo> goodList)throws Exception{
		log.debug(goodList==null?"goodList is null":goodList.toString());
		if(goodList != null && goodList.isEmpty() == false){
			for(int i = 0;i < goodList.size(); i++){
				
			}
		}
		
		if(goodList == null || goodList.size() == 0){
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"商品列表为空");
		}
		GoodsInfo tmp = null;
		GoodsInfo good = null;
		for(int i = 0; i < goodList.size(); i++){
			good = goodList.get(i);
			tmp = loadGoodsInfoByBssId(good.getBssId());
			
			if(StringUtils.isNotEmpty(good.getOptType())){//如果操作类型不为空的话
				if(StringUtils.equals(SYSConstant.OPTTYPE_INSERT, good.getOptType())){
					if(tmp != null){
						throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"第" + (i + 1) + "条的商品ID已存在！");
					}
					goodsInfoDao.save(good);
				}else{
					if(tmp != null){
						throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"第" + (i + 1) + "条的商品ID不存在,不能做更新或删除操作！");
					}
					if(StringUtils.equals(SYSConstant.OPTTYPE_UPDATE, good.getOptType())){
						tmp.setBssId(good.getBssId());
						tmp.setBssUrl(good.getBssUrl());
						tmp.setComments(good.getComments());
						tmp.setUpdateTime(new Date());
						tmp.setName(good.getName());
						tmp.setPrice(good.getPrice());
						tmp.setSalePrice(good.getSalePrice());
					}else if(StringUtils.equals(SYSConstant.OPTTYPE_DELETE, good.getOptType())){
						tmp.setCreateTime(new Date());
						tmp.setStatus(SYSConstant.STATE_INVALID);
					}
					goodsInfoDao.save(tmp);
				} 
			}else{
				
			}
		}
	}
}
