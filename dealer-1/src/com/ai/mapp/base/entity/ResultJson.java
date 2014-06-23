package com.ai.mapp.base.entity;

import net.sf.json.JSONObject;

import com.ai.mapp.base.StringUtil;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-12-7 下午05:26:41
 * 类说明:
 */

public class ResultJson {
	private boolean flag;
	private String msg;
	/**
	 * @return the flag
	 */
	public boolean getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		return "{'flag':" + flag + ",'msg':'" + msg + "'}";
	}
	
	public static ResultJson convertObj(String str){
		if(StringUtil.isEmpty(str))
			return null;
		ResultJson result = new ResultJson();
		JSONObject jsonO = JSONObject.fromObject(str);
		result.setFlag(new Boolean((jsonO.get("flag") != null?jsonO.get("flag").toString():"false")));
		result.setMsg((String)jsonO.get("msg"));
		return result;
	}
	
	public static void main(String[] args) {
		ResultJson result = new ResultJson();
		result.setFlag(false);
		result.setMsg("kjlkl");
		System.out.println(convertObj(result.toString()).getFlag());
	}
	
}
