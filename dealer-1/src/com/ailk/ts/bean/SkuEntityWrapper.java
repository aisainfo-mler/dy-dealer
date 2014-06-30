package com.ailk.ts.bean;

import com.ai.mapp.sys.entity.GoodsInfo;
import com.ailk.ts.dal.ibatis.model.ProductSku;
import com.ailk.ts.dal.ibatis.model.RepSellDetail;
import com.ailk.ts.dal.ibatis.model.SkuEntity;

public class SkuEntityWrapper {
	
	private SkuEntity entity;
	
	private String skuName;
	
	private RepSellDetail repSellDetail;
	
//	private ProductSku skuInfo;
	
	private GoodsInfo GoodInfo;

	public SkuEntity getEntity() {
		return entity;
	}

	public void setEntity(SkuEntity entity) {
		this.entity = entity;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public RepSellDetail getRepSellDetail() {
		return repSellDetail;
	}

	public void setRepSellDetail(RepSellDetail repSellDetail) {
		this.repSellDetail = repSellDetail;
	}

	public GoodsInfo getGoodInfo() {
		return GoodInfo;
	}

	public void setGoodInfo(GoodsInfo goodInfo) {
		GoodInfo = goodInfo;
	}

}
