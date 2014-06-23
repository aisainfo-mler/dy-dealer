package com.ailk.yd.mapp.tibco.model.YD9015;

import java.util.Date;
import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author luyang
 * @version 创建时间：2014-4-28 下午12:34:22
 * 类说明:TIBCO将发货的商品串号导入dealer数据库接口
 */

public class YD9015Request extends YDBody{

	private String orderId;
	
	private String sn;
	
	private String shipSn;
	
	private String company;
	
	private List<Good> goods;
	
	private Date sendTime;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public List<Good> getGoods() {
		return goods;
	}
	
	public String getShipSn() {
		return shipSn;
	}

	public void setShipSn(String shipSn) {
		this.shipSn = shipSn;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}


	public static class Good extends YDBody {
		
		private String goodId;
		
		private String keyValue;


		public String getGoodId() {
			return goodId;
		}

		public void setGoodId(String goodId) {
			this.goodId = goodId;
		}

		public String getKeyValue() {
			return keyValue;
		}

		public void setKeyValue(String keyValue) {
			this.keyValue = keyValue;
		}
	}
	
}
