package com.wjs.treewiki.vo.user;

public class UserItemVo {
	
    private Long id;
    private Long pId;

    private String name;
    
    
    /**
     * 登录名 -- user.login_name
     * 
     */
    private String loginName;

    /**
     * 密码 -- user.password
     * 
     */
    private String password;

    /**
     * 真实名称 -- user.real_name
     * 
     */
    private String realName;

    /**
     * 用户类型：管理员/普通/游客 -- user.user_type
     * 
     */
    private String userType;

    /**
     * 邮箱 -- user.email
     * 
     */
    private String email;

    /**
     * 手机号 -- user.mobile
     * 
     */
    private String mobile;

    /**
     * 创建时间 -- user.create_datetime
     * 
     */
    private Long createDatetime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Long createDatetime) {
		this.createDatetime = createDatetime;
	}

	
	
    
}
