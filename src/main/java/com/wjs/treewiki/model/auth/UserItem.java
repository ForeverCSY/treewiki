package com.wjs.treewiki.model.auth;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class UserItem implements Serializable {
    /**
     * 标准ID -- user_item.id
     * 
     */
    private Long id;

    /**
     * 用户ID -- user_item.user_id
     * 
     */
    private Long userId;

    /**
     * 节点ID -- user_item.item_id
     * 
     */
    private Long itemId;

    /**
     * 创建时间 -- user_item.create_datetime
     * 
     */
    private Long createDatetime;

    /**
     * user_item表的操作属性:serialVersionUID
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 数据字段 user_item.id的get方法 
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 数据字段 user_item.id的set方法
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 数据字段 user_item.user_id的get方法 
     * 
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 数据字段 user_item.user_id的set方法
     * 
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 数据字段 user_item.item_id的get方法 
     * 
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 数据字段 user_item.item_id的set方法
     * 
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 数据字段 user_item.create_datetime的get方法 
     * 
     */
    public Long getCreateDatetime() {
        return createDatetime;
    }

    /**
     * 数据字段 user_item.create_datetime的set方法
     * 
     */
    public void setCreateDatetime(Long createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}