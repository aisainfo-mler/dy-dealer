package com.ailk.yd.mapp.client.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ai.mapp.sys.service.MobileService;
import com.ai.mapp.sys.util.JsonUtils;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;
import com.ailk.butterfly.mapp.core.annotation.Action;
import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.yd.mapp.tibco.model.YD9003.YD9003Request;
import com.ailk.yd.mapp.tibco.model.YD9003.YD9003Request.Phone;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-5 下午02:32:25
 * 类说明:	YD9003终端信息和预览图片更新
 */

@Service("yd9003")
@Action(bizcode="yd9003",userCheck=false)
@Scope("prototype")
public class YD9003Action extends AbstractYDBaseActionHandler<YD9003Request,IBody>{
	@Autowired
	private MobileService mobileService;

	@Override
	protected void doAction() throws BusinessException, SystemException,
			Exception {
		if(this.request.getPhoneList() != null && this.request.getPhoneList().size() != 0){
			List<Phone> phoneList = this.request.getPhoneList();
			com.ai.mapp.sys.entity.Mobile localM = null;
			List<com.ai.mapp.sys.entity.Mobile> pList = new ArrayList<com.ai.mapp.sys.entity.Mobile>();
			Phone item = null;
			for(int i = 0; i < phoneList.size(); i++){
				item = phoneList.get(i);
				localM = new com.ai.mapp.sys.entity.Mobile();
				localM.setBatterySpace(item.getBatterySpace());
				localM.setBatteryType(item.getBatteryType());
				localM.setBlue(item.getBlue());
				localM.setBrand(item.getBrand());
				localM.setBrowser(item.getBrowser());
				localM.setBssMobileId(item.getPhoneId());
				localM.setColor(item.getColor());
				localM.setExterior(item.getExterior());
				localM.setExtraMemory(item.getExtraMemory());
				localM.setGps(item.getGps());
				localM.setIfIntelligent(item.getIfInt());
				localM.setPicPathJson(item.getImgInfoList() == null ? null:JsonUtils.beanToJsonExceptNull(item.getImgInfoList()));
				localM.setInstructions(item.getInstructions());
				localM.setIsWideScreen(item.getIsWideScreen());
				localM.setMemory(item.getMemory());
				localM.setMobileModel(item.getModel());
				localM.setMusicType(item.getMusicType());
				localM.setNetwork(item.getNetwork());
				localM.setOptType(item.getOptType());
				localM.setOs(item.getOs());
				localM.setBssMobileId(item.getPhoneId());
				localM.setResolution(item.getResolution());
				localM.setScreen(item.getScreen());
				localM.setScreenType(item.getScreenType());
				localM.setTransmission(item.getTrans());
				localM.setVideoFormat(item.getVideoFormat());
				pList.add(localM);
			}
			mobileService.saveMobileBatch(pList);
		}else{
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,"无套餐列表或列表为空");
		}
	}

	public MobileService getMobileService() {
		return mobileService;
	}

	public void setMobileService(MobileService mobileService) {
		this.mobileService = mobileService;
	}
}
