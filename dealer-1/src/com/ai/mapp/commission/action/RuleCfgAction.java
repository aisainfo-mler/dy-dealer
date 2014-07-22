package com.ai.mapp.commission.action;

import java.util.ArrayList;
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
import com.ailk.butterfly.commision.dal.ibatis.model.AiRuleBusiRela;
import com.ailk.butterfly.commision.dal.ibatis.model.AiRuleConfig;
import com.ailk.butterfly.commision.dal.ibatis.model.AiRuleConfigWithBLOBs;
import com.ailk.butterfly.commision.service.IAiBusiConfigService;
import com.ailk.butterfly.commision.service.IAiRuleConfigService;
import com.ailk.butterfly.sys.dal.ibatis.model.ViewCache;
import com.ailk.butterfly.sys.service.IViewCacheService;

/**
 * 规则管理类
 * 
 * @author xuzhou
 * 
 */
public class RuleCfgAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(RuleCfgAction.class);
	@Autowired
	private IAiRuleConfigService aiRuleConfigService;
	private AiRuleConfig abc;
	private Map<String, String> map;
	@Autowired
	private IViewCacheService viewCacheService;
	@Autowired
	private IAiBusiConfigService aiBusiConfigService;

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	private AiRuleConfigWithBLOBs acwb;

	private List<AiRuleConfigWithBLOBs> ruleList;
	private List<AiRuleBusiRela> ralesList;
	private List<String> dataTypeList;
	private List<String> busiCodeList;
	private List<String> operationList;
	private List<String> ovalueList;

	private AiBusiConfig busi;

	private List<AiBusiConfig> busiList;

	public AiBusiConfig getBusi() {
		return busi;
	}

	public void setBusi(AiBusiConfig busi) {
		this.busi = busi;
	}

	public List<AiBusiConfig> getBusiList() {
		return busiList;
	}

	public void setBusiList(List<AiBusiConfig> busiList) {
		this.busiList = busiList;
	}

	List<AiRuleConfig> modsList;
	private Integer ruleId;

	public Integer getRuleId() {
		return ruleId;
	}

	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}

	public List<AiRuleConfig> getModsList() {
		return modsList;
	}

	public void setModsList(List<AiRuleConfig> modsList) {
		this.modsList = modsList;
	}

	public List<String> getDataTypeList() {
		return dataTypeList;
	}

	public void setDataTypeList(List<String> dataTypeList) {
		this.dataTypeList = dataTypeList;
	}

	public List<String> getBusiCodeList() {
		return busiCodeList;
	}

	public void setBusiCodeList(List<String> busiCodeList) {
		this.busiCodeList = busiCodeList;
	}

	public List<String> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<String> operationList) {
		this.operationList = operationList;
	}

	public List<String> getOvalueList() {
		return ovalueList;
	}

	public void setOvalueList(List<String> ovalueList) {
		this.ovalueList = ovalueList;
	}

	public AiRuleConfig getAbc() {
		return abc;
	}

	public void setAbc(AiRuleConfig abc) {
		this.abc = abc;
	}

	public AiRuleConfigWithBLOBs getAcwb() {
		return acwb;
	}

	public void setAcwb(AiRuleConfigWithBLOBs acwb) {
		this.acwb = acwb;
	}

	public List<AiRuleConfigWithBLOBs> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<AiRuleConfigWithBLOBs> ruleList) {
		this.ruleList = ruleList;
	}

	public List<AiRuleBusiRela> getRalesList() {
		return ralesList;
	}

	public void setRalesList(List<AiRuleBusiRela> ralesList) {
		this.ralesList = ralesList;
	}

	public String rulecfgListMain() {
		log.info("----------QUERY SYS_USER-------------");
		try {
			if (abc == null) {
				abc = new AiRuleConfig();
			}
			abc.setSubSystemId("1");
			if (length == 0 || length == 16) {// 每页长度
				setLength(5);
			}
			if (page == 0) {
				page = 1;
			}
			page = (offset + 1) / length + 1;
			int start = length * (page - 1);
			ruleList = aiRuleConfigService.getRuleConfigs(abc, start, length);
			int count = aiRuleConfigService.countRuleConfigs(abc);
			setTotal(count);
			setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),
					getLength(), "#rulecfg_list"));

		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("查询异常，请重试", false);

		}
		return SUCCESS;
	}

	public String getBusiCfgList() {
		try {
			if (busi == null) {
				busi = new AiBusiConfig();
			}
			busi.setSubSystemId("1");
			busiList = (List<AiBusiConfig>) aiBusiConfigService.getBusiCFGs(
					busi, null, null);

			setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),
					getLength(), "#rulecfgName_list"));
		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("查询异常，请重试", false);
		}
		return SUCCESS;
	}

	public String rulecfgAdd() {
		try {
			// 得到模块类型
			List<ViewCache> modTypeList = viewCacheService
					.findCacheByKey("MODTYPES");
			map = new TreeMap<String, String>();
			for (int i = 0; i < modTypeList.size(); i++) {
				ViewCache vc = (ViewCache) modTypeList.get(i);
				map.put(vc.getpValue(), vc.getpDesc());
			}

		} catch (Exception e) {
			return returnAjaxError("异常，请重试", false);
		}
		return SUCCESS;

	}

	public String save() {
		if (acwb == null || acwb.getRuleName() == null) {
			if (SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")) {
				return returnAjaxError("丢失规则信息", false);
			} else {
				return returnAjaxError("Lost RuleName Code", false);
			}
		}
		try {

			acwb.setCreator(this.getSessionValue(HTTP_SESSION_LOGINCODE) + "");
			acwb.setRuleState("1");
			acwb.setState("1");

			aiRuleConfigService.insertRuleConfig(acwb, dataTypeList,
					busiCodeList, operationList, ovalueList);
			
			return returnAjaxSuccess("保存成功", false);
		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("保存异常，请重试", false);
		}

	}

	/*
	 * 得到单个airule
	 */
	public String rulecfgEdit() {
		try {
			abc = aiRuleConfigService.getRuleConfig(ruleId);
			ralesList = aiRuleConfigService.getRuleRelas(ruleId);
			// 得到模块类型
			List<ViewCache> modTypeList = viewCacheService
					.findCacheByKey("MODTYPES");
			map = new TreeMap<String, String>();
			for (int i = 0; i < modTypeList.size(); i++) {
				ViewCache vc = (ViewCache) modTypeList.get(i);
				map.put(vc.getpValue(), vc.getpDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
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
		if (acwb == null || acwb.getRule() == null) {
			if (SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")) {
				return returnAjaxError("丢失指标信息", false);
			} else {
				return returnAjaxError("Lost rule Code", false);
			}
		}
		try {
			acwb.setUpdator(this.getSessionValue(HTTP_SESSION_LOGINCODE) + "");
			aiRuleConfigService.updateRuleConfig(acwb, dataTypeList,
					busiCodeList, operationList, ovalueList);
			return returnAjaxSuccess("更新成功", true);
		} catch (Exception e) {
			return returnAjaxError("更新异常，请重试", false);
		}

	}

	/**
	 * 得到模块信息
	 * 
	 * @param modType
	 * @param ruleName
	 * @param ruleIds
	 * @return
	 */
	public void getModConfigs(String modType, String ruleName, String ruleIds) {
		AiRuleConfig record = new AiRuleConfig();
		record.setModType(modType);
		if (ruleName != null && StringUtils.isNotEmpty(ruleName))
			record.setRuleName(ruleName);
		List<Integer> modeIds = new ArrayList<Integer>();
		if (ruleIds != null && StringUtils.isNotEmpty(ruleIds)) {
			String[] x = ruleIds.split(",");
			for (int i = 0; i < x.length; i++) {
				modeIds.add(new Integer(x[i]));
			}
		}
		try {
			modsList = aiRuleConfigService.getRuleConfigs(record, modeIds);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}
}
