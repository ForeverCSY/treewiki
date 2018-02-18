package com.wjs.treewiki.service.user.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.wjs.common.dao.PageDataList;
import com.wjs.common.util.PasswordUtil;
import com.wjs.treewiki.constant.WebConstant;
import com.wjs.treewiki.dao.auth.UserMapper;
import com.wjs.treewiki.model.auth.User;
import com.wjs.treewiki.model.auth.UserCriteria;
import com.wjs.treewiki.service.user.UserService;
import com.wjs.treewiki.vo.auth.LogonInfo;
import com.wjs.treewiki.vo.user.UserItemVo;
import com.wjs.treewiki.vo.user.UserPageVo;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	

	ThreadLocal<LogonInfo> logonInfo = new ThreadLocal<>();
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public LogonInfo login(String userName, String password) {
		
		UserCriteria crt = new UserCriteria();
		crt.createCriteria().andLoginNameEqualTo(userName);
		List<User> users = userMapper.selectByExample(crt);
		if(CollectionUtils.isEmpty(users)){
			throw new RuntimeException("用户名不存在");
		}
		User user = users.get(0);
		if(!PasswordUtil.entry(password).equals(user.getPassword())){
			throw new RuntimeException("密码错误");
		}
		LogonInfo logonInfo = new LogonInfo();
		try {
			PropertyUtils.copyProperties(logonInfo, user);
		} catch (Exception e) {
			throw new RuntimeException("属性赋值异常:" + e.getMessage());
		}
		return logonInfo;
				
	}

	

	@Override
	public LogonInfo getLogonInfo() {
		return logonInfo.get() == null ? new LogonInfo() : logonInfo.get();
	}
	
	@Override
	public void setLogonInfo(LogonInfo cuckooLogonInfo) {
		
		logonInfo.set(cuckooLogonInfo);
	}
	
	@Override
	public void clearLogon() {

		logonInfo.remove();
	}



	@Override
	public List<User> listAll() {
		
		List<User> users = userMapper.selectByExample(new UserCriteria());
		
		
		
		return users;
	}



	@Override
	public User getUserById(Long userId) {

		return userMapper.selectByPrimaryKey(userId);
	}



	@Override
	public List<User> listUsersByType(String userType) {
		
		
		UserCriteria crt = new UserCriteria();
		if(!StringUtils.isEmpty(userType) && !WebConstant.ALL.equals(userType)){

			crt.createCriteria().andUserTypeEqualTo(userType);
		}
		crt.setOrderByClause(" real_name asc ");
		return userMapper.selectByExample(crt);
	}



	@Override
	public PageDataList<User> pageUsersByType(UserPageVo page) {


		UserCriteria crt = new UserCriteria();
		crt.setStart(page.getStart());
		crt.setLimit(page.getLimit());
		if(!StringUtils.isEmpty(page.getUserType()) && !WebConstant.ALL.equals(page.getUserType())){

			crt.createCriteria().andUserTypeEqualTo(page.getUserType());
		}
		crt.setOrderByClause(" real_name asc ");
		return userMapper.pageByExample(crt);
	}



	@Override
	public void addUser(User user) {
		
		UserCriteria crt = new UserCriteria();
		crt.createCriteria().andLoginNameEqualTo(user.getLoginName());
		if(CollectionUtils.isNotEmpty(userMapper.selectByExample(crt))){
			throw new RuntimeException("已经存在用户:" + user.getLoginName());
		}

		user.setPassword(PasswordUtil.entry(user.getPassword()));
		user.setCreateDatetime(System.currentTimeMillis());
		userMapper.insertSelective(user);
		
	}



	@Override
	public void modifyUserById(User user) {

		if(null == user.getId()){
			throw new RuntimeException("用户ID不能为空");
		}
		UserCriteria crt = new UserCriteria();
		crt.createCriteria().andIdNotEqualTo(user.getId()).andLoginNameEqualTo(user.getLoginName());
		if(CollectionUtils.isNotEmpty(userMapper.selectByExample(crt))){
			throw new RuntimeException("登录名称已占用:" + user.getLoginName());
		}
		
		user.setPassword(PasswordUtil.entry(user.getPassword()));
		userMapper.updateByPrimaryKeySelective(user);
	}

}
