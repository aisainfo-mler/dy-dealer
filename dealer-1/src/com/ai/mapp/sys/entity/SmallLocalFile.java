package com.ai.mapp.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "hw_small_local_file")
public class SmallLocalFile implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SLFID")
	private Long slfid;

	@Column(name = "FILE_EXT")
	private String fileExt;

	@Column(name = "CREATE_TIME")
	private Date createTime;

	@Column(name = "OPT_ID")
	private Long optId;

	@Column(name = "ORDER_ID")
	private Integer orderId;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "FILE_INDEX")
	private Integer fileIndex;

	@Column(name = "FILE_DOMAIN")
	private String fileDomain;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "CONTENT_BLOB", columnDefinition = "BLOB", nullable = true)
	private byte[] contentBlob;

	
	
	public String getFileDomain() {
		return fileDomain;
	}

	public void setFileDomain(String fileDomain) {
		this.fileDomain = fileDomain;
	}

	public Long getSlfid() {
		return slfid;
	}

	public void setSlfid(Long slfid) {
		this.slfid = slfid;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getOptId() {
		return optId;
	}

	public void setOptId(Long optId) {
		this.optId = optId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getFileIndex() {
		return fileIndex;
	}

	public void setFileIndex(Integer fileIndex) {
		this.fileIndex = fileIndex;
	}

	public byte[] getContentBlob() {
		return contentBlob;
	}

	public void setContentBlob(byte[] contentBlob) {
		this.contentBlob = contentBlob;
	}
}
