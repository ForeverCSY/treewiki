package com.wjs.treewiki.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjs.common.dao.PageDataList;
import com.wjs.common.dao.PageParameter;
import com.wjs.common.util.PropertyUtils;
import com.wjs.common.web.BaseController;
import com.wjs.treewiki.constant.Dictionary;
import com.wjs.treewiki.constant.WebConstant;
import com.wjs.treewiki.model.auth.User;
import com.wjs.treewiki.service.user.UserService;
import com.wjs.treewiki.vo.auth.LogonInfo;
import com.wjs.treewiki.vo.user.UserItemVo;
import com.wjs.treewiki.vo.user.UserPageVo;

@RequestMapping(value="/user")
@Controller
public class UserController extends BaseController{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping("")
	public Object user(HttpServletRequest request){

		LogonInfo logonInfo = userService.getLogonInfo();
		if(Dictionary.UserType.ADMIN.equals(logonInfo.getUserType())){

			return "user";	
		}

		return "redirect:user/detail?id=" + logonInfo.getId()+"&from=mine";
	}
	
	
	
	@RequestMapping("/detail")
	public Object detail(HttpServletRequest request,Long id){

		if(null == id){
			id = userService.getLogonInfo().getId();
		}
		
		User user = userService.getUserById(id);
		if(user == null){
			throw new RuntimeException("找不到用户:" + id);
		}
		// 非管理员，只能查看自己的信息
		if(!Dictionary.UserType.ADMIN.equals(user.getUserType()) && !userService.getLogonInfo().getId().equals(id)){
			throw new RuntimeException("非管理员,只能查看自己的信息");
		}

		request.setAttribute("userInfo", user);
		return "user/detail";
	}
	
	@RequestMapping("/listAll.json")
	@ResponseBody
	public Object listAll(HttpServletRequest request){
		
		List<User> users = userService.listAll();
		
		
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
	
	@RequestMapping("/getUsersByType.json")
	@ResponseBody
	public Object getUsersByType(HttpServletRequest request,String type){
		
		List<User> users = userService.listUsersByType(type);
		
		
		List<UserItemVo> userVos = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(users)){
			for (User user : users) {
				UserItemVo vo = new UserItemVo();
				PropertyUtils.copyProperties(vo, user);
				vo.setpId(0L);
				vo.setName(user.getRealName() + "-" + user.getLoginName());
				userVos.add(vo);
				
			}
		}
		return success(userVos);
	}
	
	@RequestMapping("/pageUsersByType.json")
	@ResponseBody
	public Object pageUsersByType(HttpServletRequest request,UserPageVo page){
		
		PageDataList<User> users = userService.pageUsersByType(page);
		
		return dataTable(users);
	}
	
	@RequestMapping("/regist.json")
	@ResponseBody
	public Object regist(HttpServletRequest request,UserItemVo page){
		
		User user = new User();
		PropertyUtils.copyProperties(user, page);
		userService.addUser(user);
		return success();
	}
	
	@RequestMapping("/modify.json")
	@ResponseBody
	public Object modify(HttpServletRequest request,UserItemVo page){
		
		User user = new User();
		PropertyUtils.copyProperties(user, page);
		userService.modifyUserById(user);
		return success();
	}
	
	
	
	
	
}
