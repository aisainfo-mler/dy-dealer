package com.ai.mapp.sys.entity;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * @author luyang 
 * @version 创建时间：2014-5-14
 * 类说明:
 */
@Entity
@Table(name="HW_CALL_INTERFACE_LOG")
public class CallLog implements java.io.Serializable {
	
	
//	1	logId	bigint	20	0	0			1	0	0
//	0	call_url	varchar	400	0	1		请求地址			0
//	0	call_req	varchar	2000	0	1		请求报文			0
//	0	call_rsp	varchar	2000	0	1		响应报文			0
//	0	call_req_encrypt	varchar	2000	0	1		请求密文			0
//	0	call_rsp_encrypt	varchar	2000	0	1		响应密文			0
//	0	call_success	varchar	1	0	1		成功失败			0
//	0	call_msg	varchar	400	0	1		描述信息			0
//	0	userid	bigint	20	0	1		请求用户id	0	0	0
//	0	call_time	datetime	0	0	1	
	
	@Id
	@Column(name="LOGID")
	@GeneratedValue(strategy=GenerationType.AUTO,generator="HW_CALL_INTERFACE_LOG_GEN")
	@SequenceGenerator(name="HW_CALL_INTERFACE_LOG_GEN",sequenceName="HW_CALL_INTERFACE_LOG_SEQ")
	private Long logId;
	
	@Column(name="USERID")
	private Long userId;

	@Column(name="CALL_URL")
	private String url;

	@Column(name="CALL_TIME")
	private Date createTime;

	@Column(name="CALL_REQ")
	private String req;

	@Column(name="CALL_RSP")
	private String rsp;

	@Column(name="CALL_REQ_ENCRYPT")
	private String reqEncrypt;

	@Column(name="CALL_RSP_ENCRYPT")
	private User rspEncrypt;

	@Column(name="CALL_SUCCESS")
	private String success;

	@Column(name="CALL_MSG")
	private String msg;
	
	@Column(name="CALL_BIZCODE")
	private String bizCode;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReq() {
		return req;
	}

	public void setReq(String req) {
		this.req = req;
	}

	public String getRsp() {
		return rsp;
	}

	public void setRsp(String rsp) {
		this.rsp = rsp;
	}

	public String getReqEncrypt() {
		return reqEncrypt;
	}

	public void setReqEncrypt(String reqEncrypt) {
		this.reqEncrypt = reqEncrypt;
	}

	public User getRspEncrypt() {
		return rspEncrypt;
	}

	public void setRspEncrypt(User rspEncrypt) {
		this.rspEncrypt = rspEncrypt;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getBizCode() {
		return bizCode;
	}

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	
}
