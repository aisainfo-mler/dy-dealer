package com.ailk.yd.mapp.tibco.model.YD0023;

import com.ailk.yd.mapp.tibco.model.TibcoRequest;

public class YD0023Request implements TibcoRequest {
	private String fileContents;

	
	public YD0023Request(String fc){
		fileContents = (fc);
	}
	
	private String wrap(String in){
		return pre+in+aft;
	}
	
	public static String pre = "<Document><url>";
	public static String aft = "</url></Document>";


	public String getFileContents() {
		return fileContents;
	}

	public void setFileContents(String fileContents) {
		this.fileContents = fileContents;
	}
}
