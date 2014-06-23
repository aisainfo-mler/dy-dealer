package com.ai.mapp.sys.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.service.ImageService;
import com.ai.mapp.base.util.ConvertUtils;
import com.ai.mapp.base.util.DateUtils;
import com.ai.mapp.base.util.FileUtil;
import com.ai.mapp.sys.dao.FileUploadDao;
import com.ai.mapp.sys.entity.FileManageCondition;
import com.ai.mapp.sys.entity.FileUpload;
import com.ai.mapp.sys.entity.Mobile;
import com.ai.mapp.sys.util.LanguageInfo;
import com.ai.mapp.sys.util.PO2VOUtils;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author zwj
 * @version 创建时间：2012-4-5 下午07:15:30
 * 类说明
 */


@Service
@Scope("singleton")
@Transactional
public class FileUploadService {
	
	private final Logger log =  Logger.getLogger(FileUploadService.class);
	
	@Autowired
	private FileUploadDao fileUploadDao;
	
	@Autowired
	private MobileService mobileService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private FileRelationService fileRelationService;
	
	@Value("${UPLOAD_ATTACHE_ROOTPATH}")
	private String rootPath;
	
	@Value("${UPLOAD_ATTACHE_IP}")
	private String uploadIp;
	
	@Value("${UPLOAD_APP_VERSION_DIR}")
	private String versionPath;
	
	
	/**
	  * <p>取出全部对应文件</p> 
	  * @param fileUpload
	  * @return 
	  * @return Collection<FileUpload> 
	 */
	public Collection<FileUpload> listFileUpload(FileUpload fileUpload){
		try{
			log.debug(fileUpload == null?"fileUpload is null":fileUpload.toString());
			
			Collection<FileUpload> c = fileUploadDao.listAll(fileUpload);
			
			return c;
			
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String saveFileUpload(List<FileUpload> list,Long appId){
		try{
			if(list == null || list.size() == 0){
				return ConvertUtils.converReturnJson(false,"缺少保存对象 ！");
			}
			String result = "";
			for(int i = 0; i < list.size(); i++){
				if(list.get(i) != null){
					list.get(i).setCreateDate(new Date());
					list.get(i).setFileMappingId(appId);
					fileUploadDao.save(list.get(i));
					result += list.get(i).getFileId();
					if( i != list.size()){
						result += ",";
					}
				}
			}
			return ConvertUtils.converReturnJson(true,result);
		}catch (Exception e) {
			e.printStackTrace();
			return ConvertUtils.converReturnJson(false, e.getMessage());
		}
		
	}
	
	/**
	 * 保存上传文件信息，并且根据fileType所对应的FileManageCondition设置，
	 * 对文件进行校验和处理，处理过的文件保存成新的fileUpload记录
	 * return 原图对象
	 * @param m
	 * @param file
	 * @throws Exception
	 */
	public List<FileUpload> saveAndUploadFileUpload(FileUpload m,File file) throws Exception
	{
		Collection<String> paths = new HashSet<String>(0);
		
		try{
			
			if(m == null || m.getFileTypes() == null || m.getFileTypes().isEmpty())
				throw new Exception(LanguageInfo.FILETYPE_UNKNOW + "," + LanguageInfo.CANNOT_UPLOAD);
			
			if(m == null || m.getUseDomain() == null || StringUtils.isEmpty(m.getUseDomain()))
				throw new Exception(LanguageInfo.FILEDOMAIN_UNKNOW + "," + LanguageInfo.CANNOT_UPLOAD);
			
			Collection<String> fileTypes = m.getFileTypes();

			
			/**
			 * 如果文件中有图片类型，校验文件是否为图片
			 */
			if(m.getFileTypes() != null 
					&& (fileTypes.contains("0") || fileTypes.contains("1") || fileTypes.contains("2")) 
					&& imageService.isImage(file) == false)
			{
				log.error("您选择的上传的类型为图片,必须为图片文件才能上传");
				
				throw new Exception(LanguageInfo.MUST_UPLOAD_IMG);
			}
			
			
			FileUpload originM = null;
			List<FileUpload> savedM = new ArrayList<FileUpload>();
			FileUpload tmpM = null;
			
			for(String fileType : fileTypes)
			{
				/**
				 * 设置附件对象的属性
				 */
				m.setFileType(fileType);	
				FileUpload mfu = createFileUpload(m);
				mfu.setFileExt(m.getFileExt() == null?SYSConstant.FILE_EXT_IMAGE:m.getFileExt());
				
				File oldFile = new File(getWebRoot()+m.getFilePath());
				oldFile.deleteOnExit();
				
				String fileName = FileUtil.getFileName(mfu.getFileName())+"."+FileUtil.getFileExt(mfu.getFileName());
				
				String saveFileName = System.currentTimeMillis()+""+Math.round(5)+"_"+mfu.getFileType()+"." + FileUtil.getFileExt(mfu.getFileName());
				
				/**图片相对路径**/
				String relative_saveFilePath = rootPath+"/"+DateUtils.getDateString(new Date(), "yyyyMMdd")+"/"+saveFileName;
				
				/**保存文件至服务器上目录，绝对路径**/
				String dirPath = getWebRoot()+rootPath+System.getProperty("file.separator")+DateUtils.getDateString(new Date(), "yyyyMMdd");
				
				log.debug("fileName:"+m.getFileName()+",  后缀："+FileUtil.getFileExt(m.getFileName()));
				
				String saveFilePath = dirPath + System.getProperty("file.separator") + saveFileName;//保存的文件名
		
				log.debug("上传路径："+dirPath);
				log.debug("文件路径："+saveFilePath);
				
				/**初始化路径**/
				File dir = new File(dirPath);
				if(dir.exists() == false) dir.mkdirs();
				
				/**
				 * 上传成功后保存记录
				 */
				mfu.setFileName(fileName);
				mfu.setFilePath(relative_saveFilePath);
				mfu.setFileSize(file.length());
				mfu.setCreateDate(new Date());
				
				log.debug("fileType = "+mfu.getFileType());
				
				tmpM = saveAndUploadFileUpload(mfu, file, new File(saveFilePath));
				savedM.add(tmpM);
				
				if(SYSConstant.FILE_TYPE_ORIGINAL.equals(fileType)){
					originM = tmpM;
				}
				
				paths.add(saveFilePath);
				
			}
			
			for(FileUpload tmp:savedM){
				doSomethingAfterSaveFileUpload(tmp,originM);
			}
			return savedM;
			
		}catch (Exception e) {
			/**
			 * 当出错了，删除之前已经上传的文件
			 */
			if(paths != null && paths.isEmpty() == false)
			{
				for(String path : paths)
				{
					new File(path).deleteOnExit();
					log.debug("删除文件："+path);
				}
			}
			
			e.printStackTrace();
			throw e;
		}
	}
	
	
	

	/**
	 * 保存附件对象并上传附件
	 * @param m
	 * @param file
	 * @param saveFile
	 * @throws Exception
	 */
	private FileUpload saveAndUploadFileUpload(FileUpload m,File file,File saveFile) throws Exception
	{
		
		/**文件校验**/
//		if(saveFile.exists()){
//			throw new Exception(LanguageInfo.FILE_UNEXIST);
//		}
		
		FileManageCondition fmc = SYSConstant.fileManageConditions.get(m.getFileType());
		
		if(fmc == null || fmc.isIfExtract() == false)
		{
			FileUtil.uploadFile(file, saveFile);
		}
		else
		{
			imageService.extractPhoto(file.getPath(), saveFile.getPath(), fmc.getExtractBase());
		}
		m.setFileSize(saveFile.length());
		
		if ( imageService.isImage(saveFile) ) {
			BufferedImage img = ImageIO.read(saveFile);
			m.setFileWidth(img.getWidth());
			m.setFileHeight(img.getHeight());
		}
		
		fileUploadDao.save(m);
		log.debug("fileUploadDao.save.id:" + m.getFileId());
		
		return m;
		
	}
	
	public void doSomethingAfterSaveFileUpload(FileUpload fileUpload,FileUpload originFileUpload) throws Exception
	{
		if("mapp_mobile_info".equals(fileUpload.getUseDomain()) && "0".equals(fileUpload.getFileType()))
		{
			Mobile mobile =  mobileService.loadMobileById(fileUpload.getFileMappingId());
//			mobile.setListPic(fileUpload);
			mobileService.saveMobile(mobile);
		}
		else if("mapp_mobile_info".equals(fileUpload.getUseDomain()) && "1".equals(fileUpload.getFileType()))
		{
			Mobile mobile =  mobileService.loadMobileById(fileUpload.getFileMappingId());
//			mobile.setDetailPic(fileUpload);
			mobileService.saveMobile(mobile);
		}
//		else if(SYSConstant.TABLE_MAPP_APP_VERSION.equals(fileUpload.getUseDomain()))
//		{
//			FileRelation fileRelation = new FileRelation();
//			fileRelation.setCreator(fileUpload.getFileUploadMan());
//			fileRelation.setStatus(SYSConstant.STATUS_OFFICIAL.toString());
//			fileRelation.setTableDomain(SYSConstant.TABLE_MAPP_APP_VERSION);
//			fileRelation.setObjectId(fileUpload.getFileMappingId());
//			fileRelation.setFileUpload(fileUpload);
//			fileRelationService.saveFileRelation(fileRelation);
//		}
//		// mapp_portal_article_title 表  fileRelation表里不存在临时状态  有就正式  (对于产品logo临时上传时的图 不予以保存关系，避免无效数据的生成),临时的控制可以tableDomain里完成
//		else if (SYSConstant.TABLE_MAPP_PORTAL_ARTICLE_TITLE.equals(fileUpload.getUseDomain())
//				&& fileUpload.getFileMappingId() != null
//				&& SYSConstant.STATUS_OFFICIAL.toString().equals(fileUpload.getFileStat())) 
//		{
//			// zwj 对Mapp_file_relation 表进行维护
//			FileRelation fileRelation = new FileRelation();
//			fileRelation.setCreator(fileUpload.getFileUploadMan());
//			fileRelation.setStatus(SYSConstant.STATUS_OFFICIAL.toString());
//			fileRelation.setTableDomain(SYSConstant.TABLE_MAPP_PORTAL_ARTICLE_TITLE);
//			fileRelation.setObjectId(fileUpload.getFileMappingId());
//			fileRelation.setFileUpload(fileUpload);
//			fileRelationService.saveFileRelation(fileRelation);
//		
//			//zwj  关于title的任意一次正式上传都可能导致logo图的变更,title的缩略图无处不在
////			if ( SYSConstant.FILE_TYPE_LIST.equals(fileUpload.getFileType()) && SYSConstant.STATUS_OFFICIAL.equals(fileUpload.getFileStat())) { // 列表图
////				ArticleTitle at = articleTitleService.loadArticleTitleById(fileUpload.getFileMappingId());
////				at.setListPic(fileRelation);
////				articleTitleService.saveArticleTitle(at);
////			}
//			
//		} else if ( SYSConstant.TABLE_MAPP_PORTAL_ARTICLE_CONTENT.equals(fileUpload.getUseDomain())) {
//			FileRelation fileRelation = new FileRelation();
//			fileRelation.setCreator(fileUpload.getFileUploadMan());
//			fileRelation.setStatus(SYSConstant.STATUS_OFFICIAL.toString());
//			fileRelation.setTableDomain(SYSConstant.TABLE_MAPP_PORTAL_ARTICLE_CONTENT);
//			fileRelation.setObjectId(fileUpload.getFileMappingId());
//			fileRelation.setFileUpload(fileUpload);
//			fileRelationService.saveFileRelation(fileRelation);
//		}
		
		if(originFileUpload != null && !SYSConstant.FILE_TYPE_ORIGINAL.equals(fileUpload.getFileType())){
			fileUpload.setParent(originFileUpload);
			fileUploadDao.save(fileUpload);
		}
	}
	
	private void checkFile(FileUpload m,File file) throws Exception
	{
		
		FileManageCondition fmc = SYSConstant.fileManageConditions.get(m.getFileType());
		
		if(fmc == null) return;
		
		String fileSize = fmc.getFileSize();
		if( fileSize != null && !"".equalsIgnoreCase(fileSize)){
			if((file.length()-100)/1024 > Integer.parseInt(fileSize)){
				throw new Exception(SYSConstant.getDictName("fileTypes", m.getFileType())+ LanguageInfo.TOO_BIG + "(" + LanguageInfo.LIMIT + fileSize + "K)");
			}
		}
		
		return;
	}
	
	private FileUpload createFileUpload(FileUpload m) throws Exception
	{
		FileUpload mfu = null;
		
		if(m.getOverrideFlag() != null && m.getOverrideFlag() != 0){
			if("0".equals(m.getFileType()) || "1".equals(m.getFileType()))
			{
				mfu = loadMobileImageByType(m.getFileMappingId(),m.getFileType(),m.getUseDomain());
			}
			
			
			if(mfu != null)
			{
				mfu.setFileComment(m.getFileComment());
				mfu.setFileIndex(m.getFileIndex());
				mfu.setFileUploadMan(m.getFileUploadMan());

				return mfu;
			}
		}
		
		
		Collection<String> ex_props = new HashSet<String>();
		ex_props.add("fileId");
		return PO2VOUtils.po2voNoCollection(FileUpload.class, m, true,ex_props);
	
	}
	
	public void deleteAttache(Long fileId)throws Exception
	{
		
		if(fileId == null)
			throw new Exception(LanguageInfo.NO_IMG_ID);
		
		FileUpload fileUpload = loadFileUploadById(fileId);
		
		
		String filePath = getWebRoot()+fileUpload.getFilePath();
		File file = new File(filePath);
		
		file.delete();
		
		fileUploadDao.delete(fileUpload);
    }
	
	public FileUpload loadMobileImageByType(Long mappingId ,String fileType,String useDomain)
	{
		FileUpload mfu = new FileUpload();
		mfu.setFileType(fileType);//
		mfu.setUseDomain(useDomain);
		mfu.setFileMappingId(mappingId);
		mfu.setFileStat("1");
		
		Collection<FileUpload> mfu_list = fileUploadDao.list(mfu,1,1);
		
		if(mfu_list == null || mfu_list.isEmpty())
			return null;
		
		return mfu_list.iterator().next();
		
	}
	
	public void saveTempFileUpload(FileUpload file) throws Exception
	{
		file.setFileStat("0");
		fileUploadDao.save(file);
	}
	
	private String getWebClassesPath() {
		String path = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		return path;

	}

	private String getWebInfPath() throws IllegalAccessException {
		String path = getWebClassesPath();
		if (path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF") + 8);
		} else {
			throw new IllegalAccessException("路径获取错误");
		}
		return path;
	}

	public String getWebRoot() throws IllegalAccessException {
		String path = getWebClassesPath();
		if (path.indexOf("WEB-INF") > 0) {
			path = path.substring(0, path.indexOf("WEB-INF/classes"));
		} else {
			throw new IllegalAccessException("路径获取错误");
		}
		return path;
	}
	
	public FileUpload loadFileUploadById(Long id) throws Exception
	{
		return fileUploadDao.get(id);
	}
	
	public FileUpload saveFileUpload(FileUpload entity)
	{
		fileUploadDao.save(entity);
		return entity;
	}
	
	
	/**逻辑删除图片*/
	public void deleteImage(Long fileId) throws Exception {
		
		if(fileId == null)
			throw new Exception(LanguageInfo.NO_IMG_ID);
		
		FileUpload fileUpload = loadFileUploadById(fileId);
		fileUpload.setFileStat(SYSConstant.STATE_INVALID);
		fileUploadDao.save(fileUpload);
    }
	
}
