package com.wjs.treewiki.service.user;

import java.util.List;

import com.wjs.treewiki.model.auth.User;
import com.wjs.treewiki.vo.auth.LogonInfo;

public interface UserService {

	LogonInfo login(String userName, String password);

	void setLogonInfo(LogonInfo logonInfo1);
	
	
	public LogonInfo getLogonInfo() ;
	
	
	public void clearLogon() ;

	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> listAll();

	/**
	 * 根据ID查询单个用户
	 * @param userId
	 * @return
	 */
	User getUserById(Long userId);

	/**
	 * 根据用户类型查询用户
	 * @param userType
	 * @return
	 */
	List<User> listUsersByType(String userType);

}
