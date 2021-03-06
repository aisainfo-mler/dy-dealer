package com.ai.mapp.sys.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ai.mapp.base.StringUtil;
import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.sys.entity.Menu;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.LoginService;
import com.ai.mapp.sys.service.MenuService;
import com.ai.mapp.sys.util.SYSConstant;
import com.ailk.butterfly.core.security.IUserinfo;
import com.ailk.butterfly.sys.dal.ibatis.model.IUserInfo;
import com.ailk.butterfly.sys.dal.ibatis.model.SysUser;
import com.ailk.butterfly.sys.dal.ibatis.model.SysUserInfo;
import com.ailk.web.SessionUtil;
import com.ailk.yd.mapp.model.UserInfo;

public class LoginAction extends BaseAction {

	private final Log log = LogFactory.getLog(LoginAction.class);

	@Autowired
	private LoginService loginService;

	@Autowired
	private MenuService mappMenuService;

	private User user;

	private String checkCode;

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	private List<Menu> subMenus;

	public String register() {
		log.debug("注册");
		return SUCCESS;
	}

	public String registerSubmit() throws Exception {
		return SUCCESS;
	}

	/**
	 * <p>
	 * 登陆
	 * </p>
	 * 
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String
	 * @author Zhengwj
	 * @Date 2012-9-21 下午04:09:38
	 */
	public String login() throws Exception {

		if (user == null || StringUtil.isEmpty(user.getUserCode())
				|| StringUtil.isEmpty(user.getPassword())) {
			if (SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")) {
				return returnAjaxError("丢失用户名或密码", false);
			} else {
				return returnAjaxError("Lost user's name or password", false);
			}
		}
		if (checkCode == null || StringUtil.isEmpty(checkCode)) {
			if (SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")) {
				return returnAjaxError("丢失验证码！", false);
			} else {
				return returnAjaxError("Lost check Code", false);
			}
		}
		String kaptchaExpected = (String) request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		System.out.println(kaptchaExpected + "\t122123eee  " + checkCode);
		if (!kaptchaExpected.equalsIgnoreCase(checkCode)) {
			if (SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")) {
				return returnAjaxError("验证码错误！", false);
			} else {
				return returnAjaxError("check Code is error", false);
			}
		}

		user = loginService.checkPassword(user.getUserCode(),
				user.getPassword());

		if (user != null) {
			putSessionValue(HTTP_SESSION_LOGINCODE, user.getUserCode());
			putSessionValue(HTTP_SESSION_USER, user);

			// 获得菜单
			Menu menu = new Menu();
			Menu parent = new Menu();
			parent.setId(Long.parseLong("0"));
			menu.setParent(parent);
			List<Menu> mainMenu = (List<Menu>) mappMenuService.listMenus(menu,
					-1, 0);
			Map<Long, List<Menu>> subMenuMap = new HashMap<Long, List<Menu>>();
			subMenuMap = mappMenuService.getSubMenu(mainMenu);

			putSessionValue(HTTP_SESSION_MAINMENU, mainMenu);
			putSessionValue(HTTP_SESSION_SUBMENU, subMenuMap);
			/**
			 * 构建butterfly session
			 */
			IUserInfo userInfo = new SysUserInfo();
			userInfo.setUserId(user.getUserId());
			userInfo.setUserName(user.getUserCode());
			SysUser sysUser = new SysUser();
			sysUser.setUserLoginName(user.getUserCode());
			sysUser.setUserName(user.getName());
			sysUser.setDeptId(user.getNetId().intValue());
			sysUser.setMobilePhone(user.getMobileNo());
			sysUser.setEmail(user.getEmail());
			sysUser.setUserStatus(user.getStatus());
			userInfo.setUser(sysUser);
			request.getSession().setAttribute(
					SessionUtil.SESSION_USER_INFO_KEY, userInfo);

			if (SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")) {
				return returnAjaxSuccess("登陆成功", false);
			} else {
				return returnAjaxSuccess("Login success", false);
			}
		} else {
			if (SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")) {
				return returnAjaxError("用户名或密码错误!", false);
			} else {
				return returnAjaxError(
						"User's name or password is not correct!", false);
			}
		}

	}

	/**
	 * <p>
	 * 退出该系统
	 * </p>
	 * 
	 * @param: @return
	 * @param: @throws Exception
	 * @return: String
	 * @author Zhengwj
	 * @Date 2012-9-21 下午04:10:57
	 */
	public String logout() throws Exception {

		getHttpSession().removeAttribute(HTTP_SESSION_LOGINCODE);
		getHttpSession().removeAttribute(HTTP_SESSION_USER);
		getHttpSession().removeAttribute(HTTP_SESSION_MAINMENU);

		getHttpSession().invalidate();
		return SUCCESS;
	}

	public String left() throws Exception {
		if (menuId != null) {
			Map<Long, List<Menu>> subMenuMap = (Map<Long, List<Menu>>) getSessionValue(HTTP_SESSION_SUBMENU);
			subMenus = subMenuMap.get(menuId);
		}
		return SUCCESS;
	}

	public List<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

}
