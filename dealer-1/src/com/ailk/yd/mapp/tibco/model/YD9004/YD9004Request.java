package com.ailk.yd.mapp.tibco.model.YD9004;

import java.math.BigDecimal;
import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj 
 * @version 创建时间：2014-4-28 下午05:17:13
 * 类说明:套餐信息更新
 */

public class YD9004Request extends YDBody{
	/**
	 * 套餐列表
	 */
	private List<Contract> contractList;
	
	public List<Contract> getContractList() {
		return contractList;
	}

	public void setContractList(List<Contract> contractList) {
		this.contractList = contractList;
	}

	/**
	 * 合约对象
	 */
	public static class Contract extends YDBody {
		/**
		 * 最低消费
		 */
		private BigDecimal lowCost;
		/**
		 * 国内语音拨打分钟数
		 */
		private String voiceLen;
		/**
		 * 国内流量 单位 ：Mb
		 */
		private String data;
		/**
		 * 国内短信发送条数
		 */
		private String sms;
		/**
		 * 免接听（0 免，1 非免接听）
		 */
		private String freeCall;
		/**
		 * 国内语音拨打（卢比/分钟）
		 */
		private BigDecimal voiceFee;
		/**
		 * 可视电话费用
		 */
		private BigDecimal videoFee;
		/**
		 * 超额流量费用
		 */
		private BigDecimal flowFee;
		/**
		 * 其他费用
		 */
		private BigDecimal otherFee;
		/**
		 * 国内可视电话分钟数
		 */
		private String videoLen;
		/**
		 * 其他赠送
		 */
		private String addedService;
		/**
		 * 操作类型，0 新增，1修改，2删除
		 */
		private String optType;
		/**
		 * 套餐名称
		 */
		private String name;
		/**
		 * 彩信条数
		 */
		private String mms;
		
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMms() {
			return mms;
		}
		public void setMms(String mms) {
			this.mms = mms;
		}
		public String getOptType() {
			return optType;
		}
		public void setOptType(String optType) {
			this.optType = optType;
		}
		public BigDecimal getLowCost() {
			return lowCost;
		}
		public void setLowCost(BigDecimal lowCost) {
			this.lowCost = lowCost;
		}
		public String getVoiceLen() {
			return voiceLen;
		}
		public void setVoiceLen(String voiceLen) {
			this.voiceLen = voiceLen;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public String getSms() {
			return sms;
		}
		public void setSms(String sms) {
			this.sms = sms;
		}
		public String getFreeCall() {
			return freeCall;
		}
		public void setFreeCall(String freeCall) {
			this.freeCall = freeCall;
		}
		public BigDecimal getVoiceFee() {
			return voiceFee;
		}
		public void setVoiceFee(BigDecimal voiceFee) {
			this.voiceFee = voiceFee;
		}
		public BigDecimal getVideoFee() {
			return videoFee;
		}
		public void setVideoFee(BigDecimal videoFee) {
			this.videoFee = videoFee;
		}
		public BigDecimal getFlowFee() {
			return flowFee;
		}
		public void setFlowFee(BigDecimal flowFee) {
			this.flowFee = flowFee;
		}
		public BigDecimal getOtherFee() {
			return otherFee;
		}
		public void setOtherFee(BigDecimal otherFee) {
			this.otherFee = otherFee;
		}
		public String getVideoLen() {
			return videoLen;
		}
		public void setVideoLen(String videoLen) {
			this.videoLen = videoLen;
		}
		public String getAddedService() {
			return addedService;
		}
		public void setAddedService(String addedService) {
			this.addedService = addedService;
		}
		public Contract() {
			super();
		}
		public Contract(BigDecimal lowCost, String voiceLen, String data,
				String sms, String freeCall, BigDecimal voiceFee,
				BigDecimal videoFee, BigDecimal flowFee, BigDecimal otherFee,
				String videoLen, String addedService) {
			super();
			this.lowCost = lowCost;
			this.voiceLen = voiceLen;
			this.data = data;
			this.sms = sms;
			this.freeCall = freeCall;
			this.voiceFee = voiceFee;
			this.videoFee = videoFee;
			this.flowFee = flowFee;
			this.otherFee = otherFee;
			this.videoLen = videoLen;
			this.addedService = addedService;
		}
		
	}
	
	
}
