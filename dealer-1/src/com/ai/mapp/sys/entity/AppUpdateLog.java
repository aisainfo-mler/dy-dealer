package com.ai.mapp.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-8-13 下午10:20:02
 * 类说明:
 */


@Entity
@Table(name="HW_APP_UPDATE_LOG")
public class AppUpdateLog implements java.io.Serializable {
	@Id
	@Column(name="LOG_ID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_APP_UPDATE_LOG_GEN")
	@SequenceGenerator(name="HW_APP_UPDATE_LOG_GEN",sequenceName="HW_APP_UPDATE_LOG_SEQ")
	private Long logId;
	
	@Column(name="ITEMKEY")
	private String itemKey;
	
	@Column(name="VERSION")
	private String version;
	
	@Column(name="UPDATECONTENT")
	private String updateContent;
	
	@Column(name="CREATETIME")
	private Date createDate;
	
	@Column(name="FILENAME")
	private String fileName;
	
	@Column(name="UPDATETITLE")
	private Date updateTitle;
	
	@Column(name="CREATOR")
	private String creator;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUpdateContent() {
		return updateContent;
	}

	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getUpdateTitle() {
		return updateTitle;
	}

	public void setUpdateTitle(Date updateTitle) {
		this.updateTitle = updateTitle;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
	
}
