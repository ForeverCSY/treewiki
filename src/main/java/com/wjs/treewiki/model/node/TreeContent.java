package com.wjs.treewiki.model.node;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TreeContent implements Serializable {
    /**
     * 标准ID -- tree_content.id
     * 
     */
    private Long id;

    /**
     * 节点ID -- tree_content.item_id
     * 
     */
    private Long itemId;

    /**
     * 是否被锁，0否：1是 -- tree_content.lockby
     * 
     */
    private String lockby;

    /**
     * 锁定人ID -- tree_content.lockby_id
     * 
     */
    private Long lockbyId;

    /**
     * 锁定人名称：冗余 -- tree_content.lockby_name
     * 
     */
    private String lockbyName;

    /**
     * 创建时间 -- tree_content.create_datetime
     * 
     */
    private Long createDatetime;

    /**
     * 文案内容 -- tree_content.content
     * 
     */
    private String content;

    /**
     * tree_content表的操作属性:serialVersionUID
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 数据字段 tree_content.id的get方法 
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 数据字段 tree_content.id的set方法
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 数据字段 tree_content.item_id的get方法 
     * 
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 数据字段 tree_content.item_id的set方法
     * 
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 数据字段 tree_content.lockby的get方法 
     * 
     */
    public String getLockby() {
        return lockby;
    }

    /**
     * 数据字段 tree_content.lockby的set方法
     * 
     */
    public void setLockby(String lockby) {
        this.lockby = lockby == null ? null : lockby.trim();
    }

    /**
     * 数据字段 tree_content.lockby_id的get方法 
     * 
     */
    public Long getLockbyId() {
        return lockbyId;
    }

    /**
     * 数据字段 tree_content.lockby_id的set方法
     * 
     */
    public void setLockbyId(Long lockbyId) {
        this.lockbyId = lockbyId;
    }

    /**
     * 数据字段 tree_content.lockby_name的get方法 
     * 
     */
    public String getLockbyName() {
        return lockbyName;
    }

    /**
     * 数据字段 tree_content.lockby_name的set方法
     * 
     */
    public void setLockbyName(String lockbyName) {
        this.lockbyName = lockbyName == null ? null : lockbyName.trim();
    }

    /**
     * 数据字段 tree_content.create_datetime的get方法 
     * 
     */
    public Long getCreateDatetime() {
        return createDatetime;
    }

    /**
     * 数据字段 tree_content.create_datetime的set方法
     * 
     */
    public void setCreateDatetime(Long createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * 数据字段 tree_content.content的get方法 
     * 
     */
    public String getContent() {
        return content;
    }

    /**
     * 数据字段 tree_content.content的set方法
     * 
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}