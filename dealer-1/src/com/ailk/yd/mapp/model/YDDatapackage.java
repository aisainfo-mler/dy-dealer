package com.ailk.yd.mapp.model;

import com.ailk.butterfly.mapp.core.model.IBody;
import com.ailk.butterfly.mapp.core.model.IHeader;
import com.ailk.butterfly.mapp.core.model.IMappDatapackage;

public class YDDatapackage implements IMappDatapackage {

private IHeader header;
	
	private IBody body;
	
	public YDDatapackage() {
		super();
	}

	public YDDatapackage(YDHeader header) {
		super();
		this.header = header;
	}

	public YDDatapackage(IHeader header, IBody body) {
		super();
		this.header = header;
		this.body = body;
	}
	
	public IHeader getHeader() {
		return header;
	}

	public void setHeader(IHeader header) {
		this.header = header;
	}

	public IBody getBody() {
		return body;
	}

	public void setBody(IBody body) {
		this.body = body;
	}
}
