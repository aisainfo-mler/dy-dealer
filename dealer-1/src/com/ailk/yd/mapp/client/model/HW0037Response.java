package com.ailk.yd.mapp.client.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ailk.yd.mapp.model.YDBody;

public class HW0037Response extends YDBody {

	
	private List<TracRefNum> tracRefNums;
	
	
	public void addTracRef(TracRefNum trn){
		if(tracRefNums==null){
			tracRefNums = new ArrayList();
		}
		this.tracRefNums.add(trn);
	}
	
	public static class TracRefNum{
		private String tracType;
		private Set<String> tracNums;
		public String getTracType() {
			return tracType;
		}
		public void setTracType(String tracType) {
			this.tracType = tracType;
		}
		public Set<String> getTracNums() {
			return tracNums;
		}
		public void setTracNums(Set<String> tracNums) {
			this.tracNums = tracNums;
		}
		public TracRefNum(String tracType) {
			super();
			this.tracType = tracType;
		}
		public void addTracNum(String tracNum){
			if(tracNums==null){
				tracNums = new HashSet();
			}
			tracNums.add(tracNum);
		}
		
		
		
	}

	public List<TracRefNum> getTracRefNums() {
		return tracRefNums;
	}

	public void setTracRefNums(List<TracRefNum> tracRefNums) {
		this.tracRefNums = tracRefNums;
	}

	
}
