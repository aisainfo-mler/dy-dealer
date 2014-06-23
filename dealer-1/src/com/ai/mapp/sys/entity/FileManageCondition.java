package com.ai.mapp.sys.entity;

/**
 * @author zwj
 * @version 创建时间：2012-4-3 下午12:10:00
 * 类说明
 */

public class FileManageCondition 
{
	private String fileIds; //文件ID集合串（，分隔）
	private String fileNames; //文件名称集合串（，分隔）
	private String pathId;  //ftp 路径
	private String maxNum; //最大文件上传数
	private String fileSize; //文件大小
	private String fileExt; //文件后缀名
	private String readOnly; //是否只读
	private boolean  ifExtract;
	private int extractBase; //图片压缩参数
	
	private String rtnFileIds;
	private String rtnFileNames;
	
	
	public FileManageCondition() {
		super();
	}

	/**
	 * 
	 * @param pathId 路径id
	 * @param fileSize 文件大小限制
	 * @param fileExt 文件后缀
	 * @param readOnly 是否只读
	 * @param ifExtract 是否压缩
	 * @param extractBase 压缩标准
	 */
	public FileManageCondition(String pathId, String fileSize, String fileExt,
			String readOnly,boolean ifExtract,int extractBase) {
		super();
		this.pathId = pathId;
		this.fileSize = fileSize;
		this.fileExt = fileExt;
		this.readOnly = readOnly;
		this.ifExtract = ifExtract;
		this.extractBase = extractBase;
	}
	
	
	public String getFileIds() {
		return fileIds;
	}
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
	public String getFileNames() {
		return fileNames;
	}
	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}
	public String getPathId() {
		return pathId;
	}
	public void setPathId(String pathId) {
		this.pathId = pathId;
	}
	public String getMaxNum() {
		return maxNum;
	}
	public void setMaxNum(String maxNum) {
		this.maxNum = maxNum;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public String getReadOnly() {
		return readOnly;
	}
	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}
	public String getRtnFileIds() {
		return rtnFileIds;
	}
	public void setRtnFileIds(String rtnFileIds) {
		this.rtnFileIds = rtnFileIds;
	}
	public String getRtnFileNames() {
		return rtnFileNames;
	}
	public void setRtnFileNames(String rtnFileNames) {
		this.rtnFileNames = rtnFileNames;
	}
	public int getExtractBase() {
		return extractBase;
	}
	public void setExtractBase(int extractBase) {
		this.extractBase = extractBase;
	}

	public boolean isIfExtract() {
		return ifExtract;
	}

	public void setIfExtract(boolean ifExtract) {
		this.ifExtract = ifExtract;
	}
	
	
}
