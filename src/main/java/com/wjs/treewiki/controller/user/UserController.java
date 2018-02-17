package com.wjs.treewiki.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjs.common.web.BaseController;
import com.wjs.treewiki.constant.Dictionary;
import com.wjs.treewiki.model.auth.User;
import com.wjs.treewiki.service.user.UserService;
import com.wjs.treewiki.vo.user.UserItemVo;

@RequestMapping(value="/user")
@Controller
public class UserController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/listAll.json")
	@ResponseBody
	public Object listAll(HttpServletRequest request){
		
		List<User> users = userService.listAll();
		
		
		List<UserItemVo> userVos = new ArrayList<>();
		User u = new User();
		
		
		if(CollectionUtils.isNotEmpty(users)){
			for (User user : users) {
				UserItemVo vo = new UserItemVo();
				vo.setId(user.getId());
				vo.setpId(0L);
				vo.setName(user.getRealName() + "-" + user.getLoginName());
				userVos.add(vo);
				
			}
		}
		return success(userVos);
	}
	
	@RequestMapping("/listNormalUser.json")
	@ResponseBody
	public Object listNormalUser(HttpServletRequest request){
		
		List<User> users = userService.listUsersByType(Dictionary.UserType.NORMAL);
		
		
		List<UserItemVo> userVos = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(users)){
			for (User user : users) {
				UserItemVo vo = new UserItemVo();
				vo.setId(user.getId());
				vo.setpId(0L);
				vo.setName(user.getRealName() + "-" + user.getLoginName());
				userVos.add(vo);
				
			}
		}
		return success(userVos);
	}
	
	
	
}
