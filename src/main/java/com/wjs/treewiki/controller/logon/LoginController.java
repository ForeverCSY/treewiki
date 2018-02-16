package com.wjs.treewiki.controller.logon;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.wjs.common.web.BaseController;
import com.wjs.treewiki.constant.WebConstant;
import com.wjs.treewiki.constant.WebPages;
import com.wjs.treewiki.service.user.UserService;
import com.wjs.treewiki.vo.auth.LogonInfo;

@Controller
@RequestMapping("/logon")
public class LoginController extends BaseController{

	
	@Autowired
	UserService userService;
	
	@RequestMapping("/in.json")
	@ResponseBody
	public Object add(HttpServletRequest request, String userName, String password){
		
		if(StringUtils.isEmpty(userName)){
			throw new RuntimeException("登录名称不能为空");
		}
		if(StringUtils.isEmpty(password)){
			throw new RuntimeException("密码不能为空");
		}
		
		LogonInfo logonInfo = userService.login(userName, password);
		
		request.getSession().setAttribute(WebConstant.SESSION_USER_KEY, logonInfo);
		
		return success();
	}
	
	
	@RequestMapping("/out")
	@ResponseBody
	public Object logout(HttpServletRequest request,String redirectUrl){
		
		
		request.getSession().invalidate();
		

		request.setAttribute("redirectUrl", redirectUrl);
		if(null != redirectUrl){
		
			return success(redirectUrl);
		}
		return success(WebPages.INDEX);
	}
}
