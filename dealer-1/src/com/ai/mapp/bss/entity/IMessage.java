package com.ai.mapp.bss.entity;

import java.io.Serializable;


public interface IMessage extends Serializable   {

	public IMessage toEntity(String response) throws Exception;
	
	public String toStr() throws Exception;
	
	public boolean ifLast() throws Exception;
	
	public boolean ifSuccess() throws Exception;
	
	public String getErrorInfo() throws Exception;
	
	public String getBusiId();
	
}
