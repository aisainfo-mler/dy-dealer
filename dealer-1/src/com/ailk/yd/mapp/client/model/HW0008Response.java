package com.ailk.yd.mapp.client.model;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.ailk.yd.mapp.model.YDBody;

public class HW0008Response extends YDBody {
	private List<SvnNumber> svnList;
	
	public static class SvnNumber{
		/**
		 * 靓号级别
		 */
		private String vanityName;
		private String svnNumber;
		private String numFee;
		
		
		public String getVanityName() {
			return vanityName;
		}
		public void setVanityName(String vanityName) {
			this.vanityName = vanityName;
		}
		public String getSvnNumber() {
			return svnNumber;
		}
		public void setSvnNumber(String svnNumber) {
			this.svnNumber = svnNumber;
		}
		public SvnNumber(String vanityName, String svnNumber,String numFee) {
			super();
			this.vanityName = vanityName;
			this.svnNumber = svnNumber;
			this.numFee = numFee;
		}
		public SvnNumber() {
			super();
		}
		public String getNumFee() {
			return numFee;
		}
		public void setNumFee(String numFee) {
			this.numFee = numFee;
		}
		
	}
	


	public void setSvnList(List<SvnNumber> svnList) {
		this.svnList = svnList;
	}



	public List<SvnNumber> getSvnList() {
		return svnList;
	}



}
