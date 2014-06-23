package com.ailk.yd.mapp.tibco.model.YD0007;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-4-28 下午06:03:38
 * 类说明:用户信息查询
 */

public class YD0007Request extends YDBody{
	/**
	 * 客户手机号
	 */
	private String mdn;
	
	private String emailid;
	
	private Integer page;
	private Integer size;
	
	/**
	 * 返回供tibco查询用的Map类型
	 * @return
	 */
	public Map returnGetParam(){
		 Map rm = new HashMap();
		 if(StringUtils.isNotBlank(mdn)){rm.put("mobileNumber", mdn);}
		 if(StringUtils.isNotBlank(emailid)){rm.put("emailId", emailid);}
		 if(page!=null){rm.put("offset", page);}
		 if(size!=null){rm.put("pageSize", size);}
		 return rm;
	}

	public String getMdn() {
		return mdn;
	}

	public void setMdn(String mdn) {
		this.mdn = mdn;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer offset) {
		this.page = offset;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer pageSize) {
		this.size = pageSize;
	}
	
	
	
}
