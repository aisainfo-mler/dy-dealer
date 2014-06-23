package com.ai.mapp.bss.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
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
import com.ai.mapp.sys.entity.FileRelation;
import com.ai.mapp.sys.entity.Mobile;
import com.ai.mapp.sys.entity.Product;
import com.ai.mapp.sys.service.FileRelationService;
import com.ai.mapp.sys.service.MobileService;
import com.ai.mapp.sys.service.ProductService;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.util.JsonUtil;

@Service("hw0003Service")
@Scope("singleton")
public class Hw0003SVImpl extends ISVTemplate {

	@Autowired
	private MobileService mobileService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileRelationService fileRelationService;

	protected Object convertResponse(ParamObject param) throws Exception {
		com.ai.mapp.model.HW0003.Response rsp = new com.ai.mapp.model.HW0003.Response();
		
		Mobile mobile = (Mobile) param.getResult();
		com.ai.mapp.model.HW0003.PhoneInfo phone = new com.ai.mapp.model.HW0003.PhoneInfo();
		
		phone.setBatterySpace(mobile.getBatterySpace() == null?"":mobile.getBatterySpace().toString());
		phone.setBlue(mobile.getBlue() == null?"":mobile.getBlue().toString());
		phone.setBrand(mobile.getTerminal_brand_s() == null?"":mobile.getTerminal_brand_s().toString());
		phone.setBrowser(mobile.getBrowser() == null?"":mobile.getBrowser().toString());
		phone.setExterior(mobile.getExterior() == null?"":mobile.getExterior().toString());
		phone.setExtraMemory(mobile.getExtraMemory() == null?"":mobile.getExtraMemory().toString());
		phone.setGps(mobile.getGps() == null?"":mobile.getGps().toString());
		phone.setIfInt(mobile.getIfIntelligent() == null?"":mobile.getIfIntelligent().toString());
		phone.setInstructions(mobile.getInstructions() == null?"":mobile.getInstructions().toString());
		phone.setIsWideScreen(mobile.getIsWideScreen() == null?"":mobile.getIsWideScreen().toString());
		phone.setMemory(mobile.getMemory() == null?"":mobile.getMemory().toString());
		phone.setModel(mobile.getMobileModel() == null?"":mobile.getMobileModel().toString());
		phone.setMusicType(mobile.getMusicType() == null?"":mobile.getMusicType().toString());
		phone.setNetWork(mobile.getNetwork() == null?"":mobile.getNetwork().toString());
		phone.setOS(mobile.getOs() == null?"":mobile.getOs().toString());
		phone.setPhoneId(mobile.getMobileId() == null?"":mobile.getMobileId().toString());
		phone.setResolution(mobile.getResolution() == null?"":mobile.getResolution().toString());
		phone.setScreen(mobile.getScreen() == null?"":mobile.getScreen().toString());
		phone.setScreenType(mobile.getScreenType() == null?"":mobile.getScreenType().toString());
		phone.setTrans(mobile.getVideoFormat() == null?"":mobile.getVideoFormat().toString());
		phone.setVideoFormat(mobile.getVideoFormat() == null?"":mobile.getVideoFormat().toString());
		
		rsp.setPhoneInfo(phone);
		
//		if(mobile.getRelations() != null && mobile.getRelations().isEmpty() == false)
//		{
//			com.ai.mapp.model.HW0003.ImgInfoList imgList = new com.ai.mapp.model.HW0003.ImgInfoList();
//			
//			for(FileRelation relation : mobile.getRelations())
//			{
//				com.ai.mapp.model.HW0003.Image image = new com.ai.mapp.model.HW0003.Image();
//				
//				if(relation.getFileUpload() != null)
//				{
//					
//					image.setUrl(relation.getFileUpload().getFilePath());
//					
//					if(relation.getFileUpload().getParent() != null)
//					{
//						image.setOrgiUrl(relation.getFileUpload().getParent().getFilePath());
//					}
//				}
//				
//				
//				imgList.addImage(image);
//			}
//		}
			
		
			com.ai.mapp.model.HW0003.ImgInfoList imgList = new com.ai.mapp.model.HW0003.ImgInfoList();
//			imgList.addImage(image);
			if(StringUtils.isNotBlank(mobile.getPicPathJson())){
				LinkedHashMap pic = (LinkedHashMap) JsonUtil.fromJsonString(mobile.getPicPathJson(), LinkedHashMap.class);
				if(!pic.isEmpty()){
					String picUrl = null;
					for (Iterator it = pic.keySet().iterator(); it
							.hasNext();) {
						String key = (String) it.next();//key为缩略图，value为大图
						com.ai.mapp.model.HW0003.Image image = new com.ai.mapp.model.HW0003.Image();
						image.setOrgiUrl((String) pic.get(key));
						image.setUrl(key);
						imgList.addImage(image);
					}
				}
				rsp.setImgInfoList(imgList);
			}
			
		
		

				
		rsp.setRspCode(BSSConstantParam.RESPONSECODE_SUCCESS);

		return rsp.toXMLString();
	}

	@Override
	protected ParamObject send(ParamObject param) throws Exception {
		
		String requestContent = (String) param.getParameter(BSSConstantParam.REQUESTCONTENT);
		com.ai.mapp.model.HW0003.Request request = com.ai.mapp.model.HW0003.Request.unmarshal(new StringReader(requestContent));
		
		if(StringUtil.isEmpty(request.getPhoneId()))
			throw new Exception(LanguageInfo.PHONE_ID_NULL);
		
		Mobile mobile = mobileService.loadMobileByMobileId(Long.valueOf(request.getPhoneId()));
		
		if(mobile == null)
			throw new Exception(LanguageInfo.MOBILE_UNEXIST + "("+request.getPhoneId()+")");
		
//		Collection<FileRelation> relations = fileRelationService.listFileRelationByMobile(mobile.getMobileId(), SYSConstant.FILE_TYPE_SHOW);
//		mobile.setRelations(relations);
		
		param.setIfSuccess(true);
		param.setResult(mobile);
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
