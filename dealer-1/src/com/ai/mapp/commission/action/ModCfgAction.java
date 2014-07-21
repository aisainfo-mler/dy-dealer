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
import com.ailk.butterfly.commision.dal.ibatis.model.AiModConfig;
import com.ailk.butterfly.commision.dal.ibatis.model.AiRuleConfig;
import com.ailk.butterfly.commision.dal.ibatis.model.AiRuleConfigWithBLOBs;
import com.ailk.butterfly.commision.dal.ibatis.model.AiRuleModRelation;
import com.ailk.butterfly.commision.dal.ibatis.model.CimAgentModRelation;
import com.ailk.butterfly.commision.service.IAiModConfigService;
import com.ailk.butterfly.commision.service.IAiRuleConfigService;
import com.ailk.butterfly.core.exception.BusinessException;
import com.ailk.butterfly.core.exception.SystemException;
import com.ailk.butterfly.sys.dal.ibatis.model.ViewCache;
import com.ailk.butterfly.sys.service.IViewCacheService;

/**
 * 模块管理类
 * 
 * @author xuzhou
 * 
 */
public class ModCfgAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ModCfgAction.class);
	private Map<String, String> map;
	private Map<String, String> maps;
	private AiRuleConfig abc;

	public AiRuleConfig getAbc() {
		return abc;
	}

	public void setAbc(AiRuleConfig abc) {
		this.abc = abc;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Map<String, String> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, String> maps) {
		this.maps = maps;
	}

	@Autowired
	private IViewCacheService viewCacheService;
	@Autowired
	private IAiModConfigService aiModConfigService;

	private AiModConfig mod;

	private List<AiModConfig> modlist;
	private List<AiRuleModRelation> anrList;
	private List<AiRuleConfigWithBLOBs> ruleList;
	@Autowired
	private IAiRuleConfigService aiRuleConfigService;

	public AiModConfig getMod() {
		return mod;
	}

	public void setMod(AiModConfig mod) {
		this.mod = mod;
	}

	public List<AiRuleModRelation> getAnrList() {
		return anrList;
	}

	public void setAnrList(List<AiRuleModRelation> anrList) {
		this.anrList = anrList;
	}

	public List<AiModConfig> getModlist() {
		return modlist;
	}

	public void setModlist(List<AiModConfig> modlist) {
		this.modlist = modlist;
	}

	private Integer modId;

	public Integer getModId() {
		return modId;
	}

	public void setModId(Integer modId) {
		this.modId = modId;
	}

	public List<AiRuleConfigWithBLOBs> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<AiRuleConfigWithBLOBs> ruleList) {
		this.ruleList = ruleList;
	}

	public String modcfgListMain() {
		log.info("----------QUERY SYS_USER-------------");
		try {
			if (mod == null) {
				mod = new AiModConfig();
			}
			mod.setSubSystemId("1");
			if (length == 0 || length == 16) {
				setLength(5);
			}
			if (page == 0) {
				page = 1;
			}
			page = (offset + 1) / length + 1;
			int start = length * (page - 1);
			modlist = aiModConfigService.getModConfigs(mod, start, length);
			int count = aiModConfigService.countModConfigs(mod);
			setTotal(count);
			setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),
					getLength(), "#modcfg_list"));

		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("查询异常，请重试", false);

		}
		return SUCCESS;
	}

	public String getRuleCfgList() {
		try {
			if (abc == null) {
				abc = new AiRuleConfig();
			}
			abc.setSubSystemId("1");

			ruleList = aiRuleConfigService.getRuleConfigs(abc, null, null);

			setPagerHeader(Pager.generate(getOffset(), (int) getTotal(),
					getLength(), "#modcfgName_list"));
		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("查询异常，请重试", false);
		}
		return SUCCESS;
	}

	public String save() {
		if (mod == null || StringUtils.isEmpty(mod.getModType())) {
			if (SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")) {
				return returnAjaxError("丢失模块信息", false);
			} else {
				return returnAjaxError("Lost modType Code", false);
			}
		}
		try {
			String ruleIds = request.getParameter("ruleIds");
			mod.setCreator(this.getSessionValue(HTTP_SESSION_LOGINCODE) + "");
			aiModConfigService.insertModConfig(mod, ruleIds);
		} catch (BusinessException e) {
			e.printStackTrace();
			return returnAjaxError("有异常" + e.getErrorMsg(), false);
		} catch (SystemException e) {
			e.printStackTrace();
			return returnAjaxError("有异常" + e.getErrorMsg(), false);
		}
		return returnAjaxSuccess("保存成功", true);
	}

	public String modcfgAdd() {
		try {
			// 得到模块类型
			List<ViewCache> modTypeList = viewCacheService
					.findCacheByKey("MODTYPES");
			map = new TreeMap<String, String>();
			for (int i = 0; i < modTypeList.size(); i++) {
				ViewCache vc = (ViewCache) modTypeList.get(i);
				map.put(vc.getpValue(), vc.getpDesc());
			}
			// 得到返佣类型
			List<ViewCache> comitems = viewCacheService
					.findCacheByKey("COMM_ITEM_TYPE");
			maps = new TreeMap<String, String>();
			for (int i = 0; i < comitems.size(); i++) {
				ViewCache vc = (ViewCache) comitems.get(i);
				maps.put(vc.getpValue(), vc.getpDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("异常，请重试", false);
		}
		return SUCCESS;
	}

	/*
	 * 得到单个aimod
	 */
	public String modcfgEdit() {
		try {
			anrList = aiModConfigService.getRuleMods(modId);
			mod = aiModConfigService.getModConfig(modId);
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
		String ruleIds = null;
		if (ruleIds == null || StringUtils.isEmpty(ruleIds)) {
			if (SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")) {
				return returnAjaxError("丢失指标信息", false);
			} else {
				return returnAjaxError("Lost mod Code", false);
			}
		}
		try {
			mod.setUpdator(this.getSessionValue(HTTP_SESSION_LOGINCODE) + "");
			aiModConfigService.updateModConfig(mod, ruleIds);
			return returnAjaxSuccess("更新成功", true);
		} catch (Exception e) {
			return returnAjaxError("更新异常，请重试", false);
		}

	}

	/**
	 * 
	 * @param agentId
	 * @param modType
	 * @return
	 */
	public String getAgentMods() {
		String modType = null;
		Integer agentId = null;
		try {
			List<CimAgentModRelation> cimAMs = aiModConfigService.getAgentMods(
					agentId, modType);
			List<AiModConfig> mods = new ArrayList<AiModConfig>();
			for (CimAgentModRelation camr : cimAMs) {
				AiModConfig mod = aiModConfigService.getModConfig(camr
						.getModid());
				if (mod != null)
					mods.add(mod);
			}
			return returnAjaxSuccess(mods + "", true);
		} catch (Exception e) {
			e.printStackTrace();
			return returnAjaxError("异常，请重试" + e.getMessage(), false);

		}

	}
}
