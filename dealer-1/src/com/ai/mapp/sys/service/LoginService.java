package com.ai.mapp.sys.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.mapp.base.util.MD5;
import com.ai.mapp.sys.entity.User;
import com.ai.mapp.sys.util.SYSConstant;

/**
 * @author Zhengwj 
 * @version 创建时间：2012-9-21 下午03:54:27
 * 类说明:
 */

@Service
@Transactional
public class LoginService {
	public final Log log = LogFactory.getLog(LoginService.class);
	
	@Autowired
	private UserService userService;
	
	public User checkPassword(String userCode,String password){
		password = MD5.toMD5(password);
		User user = new User();
		user.setUserCode(userCode);
		user.setPassword(password);
		user.setStatus(SYSConstant.STATE_VALID);
		List<User> users = (List<User>) userService.listAllUsers(user);
		if(users != null && users.size() != 0){
			user = users.get(0);
		}else{
			user = null;
		}
		return user;
	}
	
}
