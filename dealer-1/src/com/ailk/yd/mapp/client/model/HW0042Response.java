package com.ailk.yd.mapp.client.model;


import com.ailk.butterfly.mapp.core.model.IBody;

public class HW0042Response extends IBody {
	
	private String url;
		
	private String fileType;
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
}
