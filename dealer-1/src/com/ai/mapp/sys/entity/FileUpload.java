package com.ai.mapp.sys.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * @author zwj
 * @version 创建时间：2012-4-5 下午06:54:29
 * 类说明
 */

@Entity
@Table(name="HW_FILE_UPLOAD")
public class FileUpload implements java.io.Serializable {
	
	private Long fileId;
	private String fileName;
	private String filePath;
	private String fileStat;
	private String fileUploadMan;
	private Long fileMappingId;
	private String appVersion;
	private Date createDate;
	private Long fileSize;
	private String optrType;
	private String pathId;
	private String useDomain;
	private String fileType;
	private String fileIndex;
	private String fileComment;
	private FileUpload parent;
	
	private Integer fileHeight;
	private Integer fileWidth;
	private String fileExt;
	
	
	public FileUpload() {
		super();
	}
	
	public FileUpload(Long fileId) {
		super();
		this.fileId = fileId;
	}
	@Id
	@Column(name="FILE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_FILE_UPLOAD_GEN")
	@SequenceGenerator(name="HW_FILE_UPLOAD_GEN",sequenceName="HW_FILE_UPLOAD_SEQ")
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	@Column(name="FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name="FILE_PATH")
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Column(name="FILE_STAT")
	public String getFileStat() {
		return fileStat;
	}
	public void setFileStat(String fileStat) {
		this.fileStat = fileStat;
	}
	
	@Column(name="FILE_UPLOAD_MAN")
	public String getFileUploadMan() {
		return fileUploadMan;
	}
	public void setFileUploadMan(String fileUploadMan) {
		this.fileUploadMan = fileUploadMan;
	}
	
	
	@Column(name="FILE_MAPPING_ID")
	public Long getFileMappingId() {
		return fileMappingId;
	}
	public void setFileMappingId(Long fileMappingId) {
		this.fileMappingId = fileMappingId;
	}
	
	@Column(name="APP_VERSION")
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	
	@Column(name="CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	@Column(name="FILE_SIZE")
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	
	@Column(name="OPTR_TYPE")
	public String getOptrType() {
		return optrType;
	}
	public void setOptrType(String optrType) {
		this.optrType = optrType;
	}
	
	@Column(name="PATH_ID")
	public String getPathId() {
		return pathId;
	}
	public void setPathId(String pathId) {
		this.pathId = pathId;
	}
	
	@Column(name="USE_DOMAIN")
	public String getUseDomain() {
		return useDomain;
	}
	public void setUseDomain(String useDomain) {
		this.useDomain = useDomain;
	}
	
	@Column(name="FILE_TYPE")
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Column(name="FILE_COMMENT")
	public String getFileComment() {
		return fileComment;
	}
	public void setFileComment(String fileComment) {
		this.fileComment = fileComment;
	}
	
	@Column(name="FILE_INDEX")
	public String getFileIndex() {
		return fileIndex;
	}
	public void setFileIndex(String fileIndex) {
		this.fileIndex = fileIndex;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=FileUpload.class)
	@JoinColumn(name="origId",referencedColumnName="file_Id",nullable=true)
	public FileUpload getParent() {
		return parent;
	}
	public void setParent(FileUpload parent) {
		this.parent = parent;
	}
	
	@Column(name="FILE_LENGTH")
	public Integer getFileHeight() {
		return fileHeight;
	}
	public void setFileHeight(Integer fileHeight) {
		this.fileHeight = fileHeight;
	}
	
	@Column(name="FILE_WIDTH")
	public Integer getFileWidth() {
		return fileWidth;
	}
	public void setFileWidth(Integer fileWidth) {
		this.fileWidth = fileWidth;
	}
	
	@Column(name="FILE_EXT")
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}



	/**
	 * 参数载体
	 */
	
	private Collection<String> fileTypes;//只是web上的类型   默认类型
	
	@Transient
	public Collection<String> getFileTypes() {
		return fileTypes;
	}
	public void setFileTypes(Collection<String> fileTypes) {
		this.fileTypes = fileTypes;
	}
	
	/**
	 * 上传的图片是否覆盖老版本 0：代表否
	 */
	private Integer overrideFlag;

	@Transient
	public Integer getOverrideFlag() {
		return overrideFlag;
	}
	public void setOverrideFlag(Integer overrideFlag) {
		this.overrideFlag = overrideFlag;
	}
	
	
	private List<FileRelation> fileRelation;

	@OneToMany(mappedBy="fileUpload",fetch=FetchType.LAZY,targetEntity=FileRelation.class)
    @JoinColumn(name="FILE_ID")
    @OrderBy("fileIndex")
	public List<FileRelation> getFileRelation() {
		return fileRelation;
	}
	public void setFileRelation(List<FileRelation> fileRelation) {
		this.fileRelation = fileRelation;
	}
	
	private String cssImg;

	@Transient
	public String getCssImg() {
		return cssImg;
	}

	public void setCssImg(String cssImg) {
		this.cssImg = cssImg;
	}

	private String portalUrl;

	@Transient
	public String getPortalUrl() {
		return portalUrl;
	}
	public void setPortalUrl(String portalUrl) {
		this.portalUrl = portalUrl;
	}
	
	
	private Map<String,Map<String,FileManageCondition>> systemMap;
	@Transient
	public Map<String, Map<String, FileManageCondition>> getSystemMap() {
		return systemMap;
	}

	public void setSystemMap(Map<String, Map<String, FileManageCondition>> systemMap) {
		this.systemMap = systemMap;
	}

	
	private String fileSystem;

	
	@Column(name="FILE_SYSTEM")
	public String getFileSystem() {
		return fileSystem;
	}

	public void setFileSystem(String fileSystem) {
		this.fileSystem = fileSystem;
	}
	
}