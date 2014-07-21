package com.ai.mapp.commission.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.mapp.base.Pager;
import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.commision.dal.ibatis.model.AiBusiConfig;
import com.ailk.butterfly.commision.service.IAiBusiConfigService;
import com.ailk.butterfly.sys.dal.ibatis.model.ViewCache;
import com.ailk.butterfly.sys.service.IViewCacheService;

/**
 * 指标管理类
 * 
 * @author xuzhou
 * 
 */
public class BusiCfgAction extends BaseAction {
	 
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(BusiCfgAction.class);

	@Autowired
	private IAiBusiConfigService aiBusiConfigService;
	@Autowired
	private IViewCacheService viewCacheService;

	private List<ViewCache> dataTypeList;

	private Map<String, String> map;

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public List<ViewCache> getDataTypeList() {
		return dataTypeList;
	}

	public void setDataTypeList(List<ViewCache> dataTypeList) {
		this.dataTypeList = dataTypeList;
	}

	private ViewCache vc;

	public ViewCache getVc() {
		return vc;
	}

	public void setVc(ViewCache vc) {
		this.vc = vc;
	}

	private AiBusiConfig busi;

	private List<AiBusiConfig> busiList;

	public AiBusiConfig getBusi() {
		return busi;
	}

	public void setBusi(AiBusiConfig busi) {
		this.busi = busi;
	}

	public String busicfgListMain() {
		log.info("----------QUERY SYS_USER-------------");
		try {
			if (busi == null) {
				busi = new AiBusiConfig();
			}
			busi.setSubSystemId("1");
			if (length == 0 || length == 16) {
				setLength(5);
			}
			if (page == 0) {
				page = 1;
			}
			page = (offset + 1) / length + 1;
			int start = length * (page - 1);// 由于hibernate和ibatis分页不一样hibernate传入的是页数和数量，ibatis传入的是开始的条数和数量
			busiList = (List<AiBusiConfig>) aiBusiConfigService.getBusiCFGs(
					busi, start, length);
			int count = aiBusiConfigService.countBusiCFG(busi);

			setTotal(count);
			setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),
					getLength(), "#busicfg_list"));

		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("查询异常，请重试", false);

		}
		return SUCCESS;
	}

	public List<AiBusiConfig> getBusiList() {
		return busiList;
	}

	public void setBusiList(List<AiBusiConfig> busiList) {
		this.busiList = busiList;
	}

	public String busicfgAdd() {
		try {
			dataTypeList = viewCacheService.findCacheByKey("DATA_TYPES");
			map = new TreeMap<String, String>();
			for (int i = 0; i < dataTypeList.size(); i++) {
				ViewCache vc = (ViewCache) dataTypeList.get(i);
				map.put(vc.getpValue(), vc.getpDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("保存异常，请重试", false);
		}
		return SUCCESS;
	}

	public String save() {
		if (busi == null || StringUtils.isEmpty(busi.getBusiCode())) {
			if (SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")) {
				return returnAjaxError("丢失指标信息", false);
			} else {
				return returnAjaxError("Lost Busi Code", false);
			}
		}
		try {
			busi.setCreator(this.getSessionValue(HTTP_SESSION_LOGINCODE) + "");
			busi.setState("1");
			aiBusiConfigService.insertBusiConfig(busi);
			return returnAjaxSuccess("保存成功", false);
		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("保存异常，请重试", false);
		}

	}

	/*
	 * 得到单个aiBusi
	 */
	public String busicfgEdit() {

		try {
			busi = aiBusiConfigService.getBusiConfig(busi.getBusiCode(),
					busi.getSubSystemId());
			dataTypeList = viewCacheService.findCacheByKey("DATA_TYPES");
			map = new TreeMap<String, String>();
			for (int i = 0; i < dataTypeList.size(); i++) {
				ViewCache vc = (ViewCache) dataTypeList.get(i);
				map.put(vc.getpValue(), vc.getpDesc());
			}
		} catch (Exception e) {
			return returnAjaxError("异常，请重试", false);
		}
		return SUCCESS;
	}

	/**
	 * 更新用户信息
	 * 
	 * @return
	 */
	public String update() {
		if (busi == null || busi.getBusiCode() == null) {
			if (SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")) {
				return returnAjaxError("丢失指标信息", false);
			} else {
				return returnAjaxError("Lost Busi Code", false);
			}
		}
		try {
			busi.setUpdator(this.getSessionValue(HTTP_SESSION_LOGINCODE) + "");
			aiBusiConfigService.updateBusiConfig(busi);
			return returnAjaxSuccess("更新成功", false);
		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("更新异常，请重试", false);
		}

	}

	/**
	 * 判断是否已经存在指标
	 * 
	 * @param loginName
	 * @param check_type
	 * @return
	 */
	public String checkOnlyUser() {
		Map<String, Object> result = new HashMap<String, Object>();
		boolean ret = false;
		try {
			ret = aiBusiConfigService.checkBusiCode(busi.getSubSystemId(),
					busi.getBusiCode());
			result.put("result", ret);
		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("异常，请重试", false);
		}
		return returnAjaxSuccess(result);
	}

}
