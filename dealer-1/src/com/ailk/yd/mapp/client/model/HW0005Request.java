package com.ailk.yd.mapp.client.model;


import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.ailk.yd.mapp.model.YDBody;

public class HW0005Request extends YDBody {
	
	private String userName;

    private String passWd;

    private String imsi;

    private String imei;

    private String clientVersion;

    private String hardwareBrand;

    private String hardwareModel;

    private String os;

    private String from;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(String clientVersion) {
		this.clientVersion = clientVersion;
	}

	public String getHardwareBrand() {
		return hardwareBrand;
	}

	public void setHardwareBrand(String hardwareBrand) {
		this.hardwareBrand = hardwareBrand;
	}

	public String getHardwareModel() {
		return hardwareModel;
	}

	public void setHardwareModel(String hardwareModel) {
		this.hardwareModel = hardwareModel;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException
	{
		HW0005Request req = new HW0005Request();
		
		System.out.println(new ObjectMapper().writeValueAsString(req));
		
	}
	
}
