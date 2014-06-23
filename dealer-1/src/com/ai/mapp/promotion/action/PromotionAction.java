package com.ai.mapp.promotion.action;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.mapp.base.Pager;
import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.base.util.FileUtil;
import com.ai.mapp.sys.entity.Promotion;
import com.ai.mapp.sys.entity.SmallLocalFile;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.PromotionService;
import com.ai.mapp.sys.service.SmallLocalFileService;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-8 下午02:08:40
 * 类说明:广告促销
 */

public class PromotionAction extends BaseAction{
	
	private final Log log = LogFactory.getLog(PromotionAction.class);
	
	@Autowired
	private PromotionService promotionService;
	
	@Autowired
	private SmallLocalFileService smallLocalFileService;
	
	private Promotion promotion;
	
	private List<Promotion> promotionList;
	
	/**
	 * 上传的图片
	 */
	private File imgFile;
	
	private File img4File;
	
	private File img5File;
	
	/**
	 * 上传的图片全名
	 */
	private String imgFileFileName;
	
	private String img4FileFileName;
	
	private String img5FileFileName;
	
	public SmallLocalFileService getSmallLocalFileService() {
		return smallLocalFileService;
	}

	public void setSmallLocalFileService(SmallLocalFileService smallLocalFileService) {
		this.smallLocalFileService = smallLocalFileService;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public Promotion getPromotion() {
		return promotion;
	}

	public void setPromotion(Promotion promotion) {
		this.promotion = promotion;
	}

	public List<Promotion> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<Promotion> promotionList) {
		this.promotionList = promotionList;
	}

	public PromotionService getPromotionService() {
		return promotionService;
	}

	public void setPromotionService(PromotionService promotionService) {
		this.promotionService = promotionService;
	}
	
	public File getImg4File() {
		return img4File;
	}

	public void setImg4File(File img4File) {
		this.img4File = img4File;
	}

	public File getImg5File() {
		return img5File;
	}

	public void setImg5File(File img5File) {
		this.img5File = img5File;
	}

	public String getImg4FileFileName() {
		return img4FileFileName;
	}

	public void setImg4FileFileName(String img4FileFileName) {
		this.img4FileFileName = img4FileFileName;
	}

	public String getImg5FileFileName() {
		return img5FileFileName;
	}

	public void setImg5FileFileName(String img5FileFileName) {
		this.img5FileFileName = img5FileFileName;
	}

	public String add()throws Exception{
		return returnAjaxSuccess("", false);
	}
	
	/**
	 * <p>描述: 广告列表</p> 
	 * @return
	 * @throws Exception  
	 * @author        Zhengwj
	 * @Date          2014-5-8 下午04:08:57
	 */
	public String promotionList()throws Exception{
		if(promotion == null){
			promotion = new Promotion();
		}
		
		if(length == 0 || length == 16){
			setLength(50);
		}
		
		if(page == 0){
			page = 1;
		}
		page = (offset + 1 )/length + 1;
		promotionList = (List<Promotion>)promotionService.listPromotions(promotion, page, length);
		int count = promotionService.countPromotion(promotion);
		setTotal(count);
		setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),getLength(),"#promotion_list"));
		return SUCCESS;
	}
	
	/**
	 * <p>描述: 广告编辑  or 广告新增页面</p> 
	 * @return
	 * @throws Exception  
	 * @author        Zhengwj
	 * @Date          2014-5-8 下午04:09:09
	 */
	public String editPromotion()throws Exception{
		if(promotion == null || promotion.getId() == null){
			throw new BusinessException(ErrorCodeDefine.EXPECT_ERROR,LanguageInfo.PROMOTION_LOST_ID);
		}
		promotion = promotionService.loadPromotionById(promotion.getId());
		return SUCCESS;
	}
	
	/**
	 * <p>描述: 修改图片</p> 
	 * @return
	 * @throws Exception  
	 * @author        Zhengwj
	 * @Date          2014-5-8 下午04:42:23
	 */
	public String editImg()throws Exception{
		return SUCCESS;
	}
	
	private SmallLocalFile createSmallLocalFile(File file0,String file0Name) throws Exception
	{
		if(file0 == null)
			return null;
		
		if(StringUtils.isEmpty(file0Name)){
			throw new Exception(LanguageInfo.PROMOTION_NO_IMAGE);
		}
		
		if(file0 != null && file0.length() > 5242880){
			throw new Exception (LanguageInfo.PROMOTION_IMAGE_LIMIT + "5M");
		}
		
		SmallLocalFile file = new SmallLocalFile();
		file.setFileDomain(SYSConstant.SMALL_LOCAL_FILE_DOMAIN_PROMOTION);
		file.setFileExt(FileUtil.getFileExt(file0Name));
		file.setFileName(file0Name);
		file.setOptId(((User)getSessionValue(HTTP_SESSION_USER)).getUserId());
			
		FileInputStream fis = new FileInputStream(file0);
		byte[] contentB = new byte[Integer.parseInt(file0.length() + "")];
		fis.read(contentB);
		fis.close();
		file.setContentBlob(contentB);
		
		return file;
	}
	
	public String save()throws Exception{
		if(promotion == null || StringUtils.isEmpty(promotion.getRemark())){
			return returnAjaxError(LanguageInfo.PROMOTION_LOST_OBJECT_REMARK, false);
		}
		
		promotion.setImgFile(createSmallLocalFile(imgFile,imgFileFileName));
		promotion.setImg4File(createSmallLocalFile(img4File,img4FileFileName));
		promotion.setImg5File(createSmallLocalFile(img5File,img5FileFileName));
		
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
		if(promotion.getTmpStartTime() != null){
			Date start = (Date)sdf.parseObject(promotion.getTmpStartTime());
			promotion.setEffDate(start);
		}
		
		if(promotion.getTmpEndTime() != null){
			Date end = (Date)sdf.parseObject(promotion.getTmpEndTime());
			promotion.setExpDate(end);
		}
		
		User user = (User)getSessionValue(HTTP_SESSION_USER);
		promotion.setCreator(user==null?null:user.getUserId());
		promotionService.savePromotion(promotion);
		return returnAjaxSuccess(promotion.getId() + "", false);//如果是新增的就有必要
	}
}
