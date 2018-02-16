package com.wjs.treewiki.model.auth;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class User implements Serializable {
    /**
     * 标准ID -- user.id
     * 
     */
    private Long id;

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

    /**
     * user表的操作属性:serialVersionUID
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 数据字段 user.id的get方法 
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 数据字段 user.id的set方法
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 数据字段 user.login_name的get方法 
     * 
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 数据字段 user.login_name的set方法
     * 
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 数据字段 user.password的get方法 
     * 
     */
    public String getPassword() {
        return password;
    }

    /**
     * 数据字段 user.password的set方法
     * 
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 数据字段 user.real_name的get方法 
     * 
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 数据字段 user.real_name的set方法
     * 
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 数据字段 user.user_type的get方法 
     * 
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 数据字段 user.user_type的set方法
     * 
     */
    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    /**
     * 数据字段 user.email的get方法 
     * 
     */
    public String getEmail() {
        return email;
    }

    /**
     * 数据字段 user.email的set方法
     * 
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 数据字段 user.mobile的get方法 
     * 
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 数据字段 user.mobile的set方法
     * 
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 数据字段 user.create_datetime的get方法 
     * 
     */
    public Long getCreateDatetime() {
        return createDatetime;
    }

    /**
     * 数据字段 user.create_datetime的set方法
     * 
     */
    public void setCreateDatetime(Long createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}