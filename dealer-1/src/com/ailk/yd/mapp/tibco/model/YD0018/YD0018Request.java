package com.ailk.yd.mapp.tibco.model.YD0018;

import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author luyang
 * @version 创建时间：2014-4-28 下午12:34:22
 * 类说明:短信接口
 */

public class YD0018Request extends YDBody{
	
	private List<Sms> smsList;

	public List<Sms> getSmsList() {
		return smsList;
	}

	public void setSmsList(List<Sms> smsList) {
		this.smsList = smsList;
	}

	/**
	 * 合约对象
	 */
	public static class Sms extends YDBody {
		//手机号码
		private String mdn;
		//发送信息
		private String msg;
		public String getMdn() {
			return mdn;
		}
		public void setMdn(String mdn) {
			this.mdn = mdn;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
	}
}
