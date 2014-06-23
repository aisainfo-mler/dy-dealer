package com.ai.mapp.sys.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-8-16 下午03:02:54
 * 类说明:
 */

@Entity
@Table(name="HW_FILE_RELATION")
public class FileRelation implements java.io.Serializable {
	
	private Long id;
	private String tableDomain;
	private Long objectId;
	private String status;
	private Date createtime;
	private String creator;
	private Integer fileIndex;
	private FileUpload fileUpload;
	
	
	
	public FileRelation() {
		super();
	}
	
	
	public FileRelation(Long id) {
		super();
		this.id = id;
	}


	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_FILE_RELATION_GEN")
	@SequenceGenerator(name="HW_FILE_RELATION_GEN",sequenceName="HW_FILE_RELATION_SEQ")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="TABLE_DOMAIN")
	public String getTableDomain() {
		return tableDomain;
	}
	public void setTableDomain(String tableDomain) {
		this.tableDomain = tableDomain;
	}
	
	@Column(name="OBJECT_ID")
	public Long getObjectId() {
		return objectId;
	}
	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name="CREATETIME")
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Column(name="CREATOR")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@Column(name="FILEINDEX")
	public Integer getFileIndex() {
		return fileIndex;
	}
	public void setFileIndex(Integer fileIndex) {
		this.fileIndex = fileIndex;
	}

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=FileUpload.class)
	@JoinColumn(name="FILE_ID",referencedColumnName="FILE_ID")
	@NotFound(action=NotFoundAction.IGNORE)
	public FileUpload getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(FileUpload fileUpload) {
		this.fileUpload = fileUpload;
	}
	
	public Collection<Long> objectIds;

	@Transient
	public Collection<Long> getObjectIds() {
		return objectIds;
	}
	public void setObjectIds(Collection<Long> objectIds) {
		this.objectIds = objectIds;
	}
	
	
	
}
