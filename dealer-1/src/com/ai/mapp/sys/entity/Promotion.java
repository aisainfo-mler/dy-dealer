package com.ai.mapp.sys.entity;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-5-8 下午02:12:48
 * 类说明:促销 广告对象 
 */

@Entity
@Table(name="HW_PROMOTION")
public class Promotion implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO,generator="hw_promotion_gen")
    @SequenceGenerator(name="hw_promotion_gen",sequenceName="HW_PROMOTION$SEQ")
	@Column(name="PROMOTION_ID")
	private Long id;
	
	@Column(name="PROMOTION_TYPE")
	private String type;
	
//	@OneToOne(fetch=FetchType.LAZY,targetEntity=SmallLocalFile.class)
//	@JoinColumn(name="PROMOTION_FILE_ID",referencedColumnName="slfid")
//	@NotFound(action=NotFoundAction.IGNORE)
//	private SmallLocalFile file;
	
	@Column(name="PROMOTION_FILE_ID")
	private Long fileId;
	
	@Column(name="EFF_DATE")
	private Date effDate;
	
	@Column(name="EXP_DATE")
	private Date expDate;
	
	@Column(name="REMARK")
	private String remark;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="CREATOR")
	private Long creator;
	
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	@Column(name="FILE_INDEX")
	private String fileIndex;
	
	@Column(name="FILE4")
	private Long file4Id;
	
	@Column(name="FILE5")
	private Long file5Id;
	
	@Transient
	private String tmpStartTime;
	
	@Transient
	private String tmpEndTime;
	@Transient
	private SmallLocalFile imgFile;
	@Transient
	private SmallLocalFile img4File;
	@Transient
	private SmallLocalFile img5File;
	
	
	public String getTmpStartTime() {
		return tmpStartTime;
	}
	public void setTmpStartTime(String tmpStartTime) {
		this.tmpStartTime = tmpStartTime;
	}
	public String getTmpEndTime() {
		return tmpEndTime;
	}
	public void setTmpEndTime(String tmpEndTime) {
		this.tmpEndTime = tmpEndTime;
	}
	public String getFileIndex() {
		return fileIndex;
	}
	public void setFileIndex(String fileIndex) {
		this.fileIndex = fileIndex;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
//	public SmallLocalFile getFile() {
//		return file;
//	}
//	public void setFile(SmallLocalFile file) {
//		this.file = file;
//	}
	

	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
	public Date getEffDate() {
		return effDate;
	}
	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getFile4Id() {
		return file4Id;
	}
	public void setFile4Id(Long file4Id) {
		this.file4Id = file4Id;
	}
	public Long getFile5Id() {
		return file5Id;
	}
	public void setFile5Id(Long file5Id) {
		this.file5Id = file5Id;
	}
	public SmallLocalFile getImgFile() {
		return imgFile;
	}
	public void setImgFile(SmallLocalFile imgFile) {
		this.imgFile = imgFile;
	}
	public SmallLocalFile getImg4File() {
		return img4File;
	}
	public void setImg4File(SmallLocalFile img4File) {
		this.img4File = img4File;
	}
	public SmallLocalFile getImg5File() {
		return img5File;
	}
	public void setImg5File(SmallLocalFile img5File) {
		this.img5File = img5File;
	}
	

}
