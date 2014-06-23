package com.ai.mapp.sys.action;

import java.util.Collection;

import com.ai.mapp.base.action.BaseAction;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.service.UserService;
import com.ai.mapp.sys.util.SYSConstant;

public class SysManagerAction extends BaseAction {

	private UserService userService;
	
	private User user;
	
	private Collection<User> users;
	
	
	public String listUser() throws Exception
	{	
		users = userService.listUsers(user, getPage(), getLength());
		
		return SUCCESS;
		
	}
	
	public String apply() throws Exception
	{
		if(user == null || user.getUserId() == null)
			if(SYSConstant.LANGUAGE_CHINA.equals(getSessionValue("lang") + "")){
				throw new Exception("未找到userId,无法操作");
			 }else{
				 throw new Exception("Lost user ID,cannot operate");
			 }
		
		user = userService.loadUser(user.getUserId());
		user.setStatus(SYSConstant.STATE_VALID);
		
		userService.saveUser(user);
		
		return SUCCESS;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}
	
	
}
