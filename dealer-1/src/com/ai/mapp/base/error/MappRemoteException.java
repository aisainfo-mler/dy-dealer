package com.ai.mapp.base.error;

public class MappRemoteException extends Exception {

	private String mappError;
	
	public MappRemoteException(Exception e) {
		super();
		this.mappError = e.getMessage();
	}
	
	public MappRemoteException(String message) {
		super(message);
		this.mappError = message;
	}

	public String getMappError() {
		return mappError;
	}

	public void setMappError(String mappError) {
		this.mappError = mappError;
	}
	
}
