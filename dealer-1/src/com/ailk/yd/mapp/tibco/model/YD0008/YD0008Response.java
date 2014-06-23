package com.ailk.yd.mapp.tibco.model.YD0008;

import java.util.ArrayList;
import java.util.List;

import com.ailk.yd.mapp.model.YDBody;

/**
 * @author Zhengwj
 * @version 创建时间：2014-4-28 下午06:15:09 类说明:号码搜索
 */

public class YD0008Response extends YDBody {
	private List<String> svnList;
	/**
	 * 靓号级别
	 */
	private String vanityName;

	public List<String> getSvnList() {
		return svnList;
	}

	public void setSvnList(List<String> svnList) {
		this.svnList = svnList;
	}

	public String getVanityName() {
		return vanityName;
	}

	public void setVanityName(String vanityName) {
		this.vanityName = vanityName;
	}

}
