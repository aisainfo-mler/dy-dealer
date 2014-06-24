package com.ailk.yd.mapp.client.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ailk.yd.mapp.model.YDBody;

public class HW0037Request extends YDBody {
	
	
	private List<TracType> tracTypes;


	public static class TracType{
		private String transactionType;
		private Integer size;
		public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
		public Integer getSize() {
			return size;
		}
		public void setSize(Integer size) {
			this.size = size;
		}
		
	}


	public List<TracType> getTracTypes() {
		return tracTypes;
	}


	public void setTracTypes(List<TracType> tracTypes) {
		this.tracTypes = tracTypes;
	}

}
