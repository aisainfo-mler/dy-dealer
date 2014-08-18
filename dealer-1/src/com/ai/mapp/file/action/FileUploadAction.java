package com.ai.mapp.file.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.bss.util.BSSConstant;
import com.ai.mapp.sys.entity.AppVersion;
import com.ai.mapp.sys.entity.FileUpload;
import com.ai.mapp.sys.service.AppVersionService;
import com.ai.mapp.sys.service.SysPropService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.mapp.core.ErrorCodeDefine;
import com.ailk.butterfly.mapp.core.MappConstant;
import com.ailk.butterfly.mapp.core.MappContext;
import com.ailk.yd.mapp.model.UserInfo;
import com.ailk.yd.mapp.tibco.TibcoCache;
import com.ailk.yd.mapp.tibco.action.YD0023Action;
import com.ailk.yd.mapp.tibco.model.YD0023.YD0023Request;
import com.ailk.yd.mapp.tibco.model.YD0023.YD0023Response;

/**
 * @author zwj
 * @version 创建时间：2012-4-17 下午01:55:51
 * 类说明
 */

public class FileUploadAction extends BaseAction {
	
	private static final Logger log =  Logger.getLogger(FileUploadAction.class);
	protected ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private SysPropService sysPropService;
	
	@Autowired
	private AppVersionService appVersionService;
	
	@Autowired
	private YD0023Action yd0023;
	/**
	 * 上传的图片
	 */
	private List<File> imgFile;
	/**
	 * 上传的图片全名
	 */
	private List<String> imgFileFileName;
	
	private FileUpload fileUpload;
	
	private String jsonResult;
	private String creator;
	
	private String orderId;
	
	/**
	 * 下载应用文件的关键字
	 */
	private String downAppFileItemKey;
	
	private String url;
	
	private File filePoa;
	
	private File filePoi;
	
	private File fileCus;
	
	private byte file;
	

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}


	public String sendToTibco(){
		try{
			if(request.getSession().getAttribute(MappConstant.MAPP_SESSION_USER) == null){
				IUserinfo user = (IUserinfo)request.getSession().getAttribute(MappConstant.MAPP_SESSION_USER);
				throw new BusinessException(ErrorCodeDefine.NO_USER_INFO);
			}
			
			
			Map<String, String> tibcoUrls = new HashMap<String, String>();
			
			FileInputStream fis = null;
			if(filePoa!= null){
				fis = new FileInputStream(filePoa);
				byte[] file = new byte[Integer.parseInt(filePoa.length() + "")];
				fis.read(file);
				String fc = new BASE64Encoder().encode(file);
				YD0023Request yd23 = new YD0023Request(fc);
				YD0023Response g2t = yd0023.post2Tibco(yd23, null,false);
				tibcoUrls.put("POA",g2t.getUrl());
				fis.close();
			}
			
			if(filePoi != null){
				fis = new FileInputStream(filePoi);
				byte[] file = new byte[Integer.parseInt(filePoi.length() + "")];
				fis.read(file);
				String fc = new BASE64Encoder().encode(file);
				YD0023Request yd23 = new YD0023Request(fc);
				YD0023Response g2t = yd0023.post2Tibco(yd23, null,false);
				tibcoUrls.put("POI",g2t.getUrl());
				fis.close();
			}
			
			if(fileCus != null){
				fis = new FileInputStream(fileCus);
				byte[] file = new byte[Integer.parseInt(fileCus.length() + "")];
				fis.read(file);
				String fc = new BASE64Encoder().encode(file);
				YD0023Request yd23 = new YD0023Request(fc);
				YD0023Response g2t = yd0023.post2Tibco(yd23, null,false);
				tibcoUrls.put("CUS",g2t.getUrl());
				fis.close();
			}
	        
//	        final String webinf = this.getClass().getResource("/").getPath();
//	        String filePath = "/PoaPoi/";
//			final String dir = webinf+filePath;
//			final File dirFile = new File(dir);
//			if(dirFile.exists()==false)
//				dirFile.mkdir();
////			StringUtil.uploadFile(file, "12312312", dir);
//			StringUtil.uploadFile(filePoa, "12312312", dir);
//			jsonResult = mapper.writeValueAsString("uljlkjljlkjlk");
			tibcoUrls.put("exceptionCode", "0000");
			jsonResult = mapper.writeValueAsString(tibcoUrls);
			
		}catch (Exception e) {
			e.printStackTrace();
			jsonResult = "{'exceptionCode':'9999','msg':'" + e.getMessage() + "'}";
		}
		return "jsonResult";
    }

	public String download()throws Exception{
		if(StringUtils.isEmpty(downAppFileItemKey)){
			return ERROR;
		}
		//获得公共服务器路径
		String hostUrl = (String)((Map)TibcoCache.dicts.get("ROOT")).get(SYSConstant.MAPP_UPLOAD_URL_HTTP);
		if(StringUtils.isEmpty(hostUrl)){
			return ERROR;
		}
		hostUrl += request.getContextPath();
		//获得应用
		AppVersion app = appVersionService.loadByItemKey(downAppFileItemKey);
		if(app == null){
			throw new Exception("there's no app of " + downAppFileItemKey + ",check your itemKey");
		}
		
		String updateUrl = app.getUpdateUrl();
		String front = updateUrl.substring(0,updateUrl.indexOf("."));
		String end = updateUrl.substring(updateUrl.indexOf("."));
		
		if(end.indexOf("plist") != -1){
			url = SYSConstant.IOS_DOWNLOAD_FRONT + hostUrl + updateUrl;
		}else{
			updateUrl =  app.getLastVersion();
			updateUrl = front + "_" + updateUrl + end;
			url = hostUrl + updateUrl;
		}
		
		return SUCCESS;
	}
	public ObjectMapper getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public SysPropService getSysPropService() {
		return sysPropService;
	}

	public void setSysPropService(SysPropService sysPropService) {
		this.sysPropService = sysPropService;
	}

	public AppVersionService getAppVersionService() {
		return appVersionService;
	}

	public void setAppVersionService(AppVersionService appVersionService) {
		this.appVersionService = appVersionService;
	}

	public YD0023Action getYd0023() {
		return yd0023;
	}

	public void setYd0023(YD0023Action yd0023) {
		this.yd0023 = yd0023;
	}

	public List<File> getImgFile() {
		return imgFile;
	}

	public void setImgFile(List<File> imgFile) {
		this.imgFile = imgFile;
	}

	public List<String> getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(List<String> imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDownAppFileItemKey() {
		return downAppFileItemKey;
	}

	public void setDownAppFileItemKey(String downAppFileItemKey) {
		this.downAppFileItemKey = downAppFileItemKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static Logger getLog() {
		return log;
	}

	public File getFilePoa() {
		return filePoa;
	}

	public void setFilePoa(File filePoa) {
		this.filePoa = filePoa;
	}

	public File getFilePoi() {
		return filePoi;
	}

	public void setFilePoi(File filePoi) {
		this.filePoi = filePoi;
	}

	public File getFileCus() {
		return fileCus;
	}

	public void setFileCus(File fileCus) {
		this.fileCus = fileCus;
	}

	public byte getFile() {
		return file;
	}

	public void setFile(byte file) {
		this.file = file;
	}
	
	private void uploadFile(File img, String ornNum, String dir)throws UnsupportedEncodingException, IOException,
	FileNotFoundException {
		FileInputStream fis = new FileInputStream(img);
		byte[] buffer = new byte[Integer.parseInt(img.length() + "")];
		String fileName = ornNum + ".jpg";
		File f = new File(dir + fileName);
		System.err.println(f.getAbsolutePath());
		if(f.exists()==false){
			f.createNewFile();
		}
		int length = 0;  
		//读取myFile文件输出到toFile文件中  
		FileOutputStream fos = new FileOutputStream(f);
        while ((length = fis.read(buffer)) > 0) {  
        	fos.write(buffer, 0, length);  
        }  
		
		fos.flush();
		fos.close();
		fis.close();
	}

}
