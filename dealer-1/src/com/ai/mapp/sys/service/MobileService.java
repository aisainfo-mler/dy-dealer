package com.ai.mapp.sys.service;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.error.RollbackException;
import com.ai.mapp.sys.dao.MobileDao;
import com.ai.mapp.sys.entity.Contract;
import com.ai.mapp.sys.entity.Mobile;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.util.PO2VOUtils;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;

@Service
@Scope("singleton")
@Transactional
public class MobileService {
	
	public Log log = LogFactory.getLog(MobileService.class);
	
	@Autowired
	private MobileDao mobileDao;
	
	public Collection<Mobile> listMobile(Mobile mobile,int start,int limit){
		try{
			System.out.println(mobile==null?"mobile is null":mobile.toString());
			
			Collection<Mobile> c = mobileDao.list(mobile, start, limit);
			
			return c;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void saveMobile(Mobile mobile){
		try{
			System.out.println(mobile==null?"mobile is null":mobile.toString());
			mobileDao.save(mobile);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveMobileBatch(List<Mobile> mobileList)throws Exception{
		log.debug(mobileList == null?"mobileList is null":mobileList.toString());
		if(mobileList == null || mobileList.size() == 0){
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"终端列表为空");
		}
		Mobile tmp = null;
		Mobile mobile = null;
		for(int i = 0; i < mobileList.size(); i++){
			mobile = mobileList.get(i);
			tmp = loadMobileByBssMobileId(mobile.getBssMobileId());
			if(StringUtils.isNotEmpty(mobile.getOptType())){//如果操作类型不为空的话
				if(StringUtils.equals(SYSConstant.OPTTYPE_INSERT, mobile.getOptType())){
					if(tmp != null){
						throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"第" + (i + 1) + "条的商品ID已存在！");
					}
					mobileDao.save(mobile);
				}else{
					if(tmp != null){
						throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"第" + (i + 1) + "条的商品ID不存在,不能做更新或删除操作！");
					}
					if(StringUtils.equals(SYSConstant.OPTTYPE_UPDATE, mobile.getOptType())){
//						tmp = PO2VOUtils.po2voNoCollection(Mobile.class, mobile, true);
						tmp.setBatterySpace(mobile.getBatterySpace());
						tmp.setBatteryType(mobile.getBatteryType());
						tmp.setBlue(mobile.getBlue());
						tmp.setBrand(mobile.getBrand());
						tmp.setBrowser(mobile.getBrowser());
						tmp.setBssMobileId(mobile.getBssMobileId());
						tmp.setColor(mobile.getColor());
						tmp.setDetailInfoUrl(mobile.getDetailInfoUrl());
						tmp.setDetailPic(mobile.getDetailPic());
						tmp.setExterior(mobile.getExterior());
						tmp.setExtraMemory(mobile.getExtraMemory());
						tmp.setFeature(mobile.getFeature());
						tmp.setFetchStatus(mobile.getFetchStatus());
						tmp.setFilePath(mobile.getFilePath());
						tmp.setFm(mobile.getFm());
						tmp.setGps(mobile.getGps());
						tmp.setIfBrandRecommend(mobile.getIfBrandRecommend());
						tmp.setInstructions(mobile.getInstructions());
						tmp.setIsWideScreen(mobile.getIsWideScreen());
						tmp.setIfIntelligent(mobile.getIfIntelligent());
						tmp.setInstructions(mobile.getInstructions());
						tmp.setListPic(mobile.getListPic());
						tmp.setMemory(mobile.getMemory());
						tmp.setMms(mobile.getMms());
						tmp.setMobileModel(mobile.getMobileModel());
						tmp.setMusicType(mobile.getMusicType());
						tmp.setNetwork(mobile.getNetwork());
						tmp.setOffice(mobile.getOffice());
						tmp.setOs(mobile.getOs());
						tmp.setPatternModel(mobile.getPatternModel());
						tmp.setPhoneSize(mobile.getPhoneSize());
						tmp.setPixels(mobile.getPixels());
						tmp.setPresent(mobile.getPresent());
						tmp.setPrice(mobile.getPrice());
						tmp.setPriceRange(mobile.getPriceRange());
						tmp.setProduct(mobile.getProduct());
						tmp.setPromotion(mobile.getPromotion());
						tmp.setRelations(mobile.getRelations());
						tmp.setResolution(mobile.getResolution());
						tmp.setScreen(mobile.getScreen());
						tmp.setScreenType(mobile.getScreenType());
						tmp.setSpecialSearch(mobile.getSpecialSearch());
						tmp.setTerminal_brand_s(mobile.getTerminal_brand_s());
						tmp.setTerminal_os_s(mobile.getTerminal_os_s());
						tmp.setTerminal_sellFeature_s(mobile.getTerminal_sellFeature_s());
						
					}else if(StringUtils.equals(SYSConstant.OPTTYPE_DELETE, mobile.getOptType())){
						tmp.setStatus(SYSConstant.STATE_INVALID);
					}
					mobileDao.save(tmp);
				} 
			}else{
				
			}
		}
	}
	
	public void deleteMobile(Mobile mobile){
		try{
			System.out.println(mobile==null?"mobile is null":mobile.toString());
			mobileDao.delete(mobile);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int countMobile(Mobile mobile) throws RollbackException {
		try{
			return mobileDao.count(mobile);
		}catch (Exception e) {
			e.printStackTrace();
			throw new RollbackException(e);
		}
	}
	
	public Mobile loadMobileByMobileId(Long mobileId) throws Exception
	{
		//return mobileDao.get(id);
		
		Mobile condition = new Mobile(mobileId);
		
		Collection<Mobile> mobiles = listMobile(condition, 0, 1);
		
		if(mobiles == null || mobiles.isEmpty())
			return null;
		
		return mobiles.iterator().next();
	}
	
	public Mobile loadMobileByBssMobileId(String bssMobileId) throws Exception
	{
		//return mobileDao.get(id);
		
		Mobile condition = new Mobile();
		condition.setBssMobileId(bssMobileId);
		
		Collection<Mobile> mobiles = listMobile(condition, 0, 1);
		
		if(mobiles == null || mobiles.isEmpty())
			return null;
		
		return mobiles.iterator().next();
	}

	public Mobile loadMobileById(Long mobileId) throws Exception
	{
		return mobileDao.get(mobileId);
	}
	
}
