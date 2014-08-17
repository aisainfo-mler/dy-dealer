package com.ai.mapp.file.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import com.ai.mapp.sys.entity.FileUpload;
//import com.ai.mapp.sys.entity.FileUpload;
import com.ai.mapp.sys.service.AppVersionService;
import com.ai.mapp.sys.service.SysPropService;
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
	
	
	private List<String> tibcoUrls;
	
	private String url;
	
	private byte[] file1;
	private String file1Name;
	
	private byte[] file2;
	private String file2Name;
	
	private byte[] file3;
	private String file3Name;
	
	private byte[] file;
	

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}


	public String sendToTibco()throws Exception{
//		if(StringUtils.isEmpty(creator)){
//			throw new Exception("您无权上传文件");
//		}
		InputStream in = request.getInputStream();
		Map<String, Object> map = request.getParameterMap();
		Set<String> key = map.keySet();
        for (Iterator<String> it = key.iterator(); it.hasNext();) {
            String s = (String) it.next();
            System.out.println(s + ":" + map.get(s));
        }
		
//		if(imgFile == null || imgFile.size() == 0){
//			throw new Exception("未选择上传的文件");
//		}
		
//		List<FileUpload> list = new ArrayList<FileUpload>();
//		for(File img:imgFile){
//			FileInputStream fis = new FileInputStream(img);
//			fis.read();
//			byte[] file = new byte[Integer.parseInt(img.length() + "")];
//			fis.read(file);
//			String fc = new BASE64Encoder().encode(file);
//			YD0023Request yd23 = new YD0023Request(fc);
//			YD0023Response g2t = yd0023.post2Tibco(yd23, null,false);
//			tibcoUrls.add(g2t.getUrl());
//		}
        
        final String webinf = this.getClass().getResource("/").getPath();
        String filePath = "/PoaPoi/";
		final String dir = webinf+filePath;
		final File dirFile = new File(dir);
		if(dirFile.exists()==false)
			dirFile.mkdir();
//		StringUtil.uploadFile(file, "12312312", dir);
		StringUtil.uploadFile(file1, "12312312", dir);
		jsonResult = mapper.writeValueAsString(tibcoUrls);
		return "jsonResult";
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

	public List<String> getTibcoUrls() {
		return tibcoUrls;
	}

	public void setTibcoUrls(List<String> tibcoUrls) {
		this.tibcoUrls = tibcoUrls;
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

	public byte[] getFile1() {
		return file1;
	}

	public void setFile1(byte[] file1) {
		this.file1 = file1;
	}

	public String getFile1Name() {
		return file1Name;
	}

	public void setFile1Name(String file1Name) {
		this.file1Name = file1Name;
	}

	public byte[] getFile2() {
		return file2;
	}

	public void setFile2(byte[] file2) {
		this.file2 = file2;
	}

	public String getFile2Name() {
		return file2Name;
	}

	public void setFile2Name(String file2Name) {
		this.file2Name = file2Name;
	}

	public byte[] getFile3() {
		return file3;
	}

	public void setFile3(byte[] file3) {
		this.file3 = file3;
	}

	public String getFile3Name() {
		return file3Name;
	}

	public void setFile3Name(String file3Name) {
		this.file3Name = file3Name;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

}
