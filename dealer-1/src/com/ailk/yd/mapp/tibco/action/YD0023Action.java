package com.ailk.yd.mapp.tibco.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ailk.yd.mapp.tibco.model.YD0023.YD0023Request;
import com.ailk.yd.mapp.tibco.model.YD0023.YD0023Response;

@Service("yd0023")
public class YD0023Action extends AbstractTibcoService<YD0023Request, YD0023Response> {
	
	@Value("${yd0023_url}")
	private String url;
	
	@Override
	public String getTibcoUrl() {
		return url;
	}

	@Override
	protected YD0023Response convertResponse(String json) throws Exception {
		YD0023Response rm = new YD0023Response();
		//如果是正确的格式的话应该是这样：<?xml version=\"1.0\"?><Document>  <response></response>  <url>*******</url></Document>
		if(StringUtils.isBlank(json)){
			throw new Exception("response from  tibco is null");
		}
		int start = json.indexOf("<url>");
		int end = json.indexOf("</url>");
		if(start<0 && end <0){
			throw new Exception("response from  tibco format error");
		}
		
		String realUrl = json.substring(start+5,end);
		realUrl = realUrl.replaceAll("&#38;", "&");
		rm.setUrl(realUrl);
		return rm;
	}
	
	@Override
	protected String convertRequest(YD0023Request request) throws Exception
	{
//		setUrl(request.getOrn());
		return YD0023Request.pre+request.getFileContents()+YD0023Request.aft;
	}
	
}
