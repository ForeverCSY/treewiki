package com.wjs.treewiki.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wjs.treewiki.constant.WebConstant;

public class CommonInteceptor extends HandlerInterceptorAdapter {

	private Gson gson = new GsonBuilder().create();
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		
		request.setAttribute("basePath", basePath);
		String loginInfo = gson.toJson(request.getSession().getAttribute(WebConstant.SESSION_USER_KEY));
		request.setAttribute("logonInfo", loginInfo);	
		
		return super.preHandle(request, response, handler);
	}
	
	

}
