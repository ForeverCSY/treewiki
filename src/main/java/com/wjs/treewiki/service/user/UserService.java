package com.wjs.treewiki.service.user;

import java.util.List;

import com.wjs.common.dao.PageDataList;
import com.wjs.treewiki.model.auth.User;
import com.wjs.treewiki.vo.auth.LogonInfo;
import com.wjs.treewiki.vo.user.UserPageVo;

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

	/**
	 * 根据用户类型，分页查询用户
	 * @param page
	 * @return
	 */
	PageDataList<User> pageUsersByType(UserPageVo page);

	void addUser(User user);

	void modifyUserById(User user);

}
