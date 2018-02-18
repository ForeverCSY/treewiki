package com.wjs.treewiki.vo.auth;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class LogonInfo {
	
    private Long id;

	private String loginName;


    private String userType;
    
    private String mobile;

    private String email;
    
	
	private String realName;
	private Long createDatetime;

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getLoginName() {
		return loginName;
	}



	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}



	
	
	public String getUserType() {
		return userType;
	}



	public void setUserType(String userType) {
		this.userType = userType;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getRealName() {
		return realName;
	}



	public void setRealName(String realName) {
		this.realName = realName;
	}



	public Long getCreateDatetime() {
		return createDatetime;
	}



	public void setCreateDatetime(Long createDatetime) {
		this.createDatetime = createDatetime;
	}



	@Override
	public String toString() {
		
		return ReflectionToStringBuilder.toString(this);
	}
}
