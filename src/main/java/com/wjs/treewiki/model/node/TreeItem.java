package com.wjs.treewiki.model.node;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class TreeItem implements Serializable {
    /**
     * 标准ID -- tree_item.id
     * 
     */
    private Long id;

    /**
     * 上级ID -- tree_item.p_id
     * 
     */
    private Long pId;

    /**
     * 节点名 -- tree_item.name
     * 
     */
    private String name;

    /**
     * 自定义节点样式 -- tree_item.icon_skin
     * 
     */
    private String iconSkin;

    /**
     * 排序字段 -- tree_item.sort
     * 
     */
    private Integer sort;

    /**
     * 创建时间 -- tree_item.create_datetime
     * 
     */
    private Long createDatetime;

    /**
     * 修改时间 -- tree_item.modify_datetime
     * 
     */
    private Long modifyDatetime;

    /**
     * tree_item表的操作属性:serialVersionUID
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 数据字段 tree_item.id的get方法 
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 数据字段 tree_item.id的set方法
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 数据字段 tree_item.p_id的get方法 
     * 
     */
    public Long getpId() {
        return pId;
    }

    /**
     * 数据字段 tree_item.p_id的set方法
     * 
     */
    public void setpId(Long pId) {
        this.pId = pId;
    }

    /**
     * 数据字段 tree_item.name的get方法 
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * 数据字段 tree_item.name的set方法
     * 
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 数据字段 tree_item.icon_skin的get方法 
     * 
     */
    public String getIconSkin() {
        return iconSkin;
    }

    /**
     * 数据字段 tree_item.icon_skin的set方法
     * 
     */
    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin == null ? null : iconSkin.trim();
    }

    /**
     * 数据字段 tree_item.sort的get方法 
     * 
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 数据字段 tree_item.sort的set方法
     * 
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 数据字段 tree_item.create_datetime的get方法 
     * 
     */
    public Long getCreateDatetime() {
        return createDatetime;
    }

    /**
     * 数据字段 tree_item.create_datetime的set方法
     * 
     */
    public void setCreateDatetime(Long createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * 数据字段 tree_item.modify_datetime的get方法 
     * 
     */
    public Long getModifyDatetime() {
        return modifyDatetime;
    }

    /**
     * 数据字段 tree_item.modify_datetime的set方法
     * 
     */
    public void setModifyDatetime(Long modifyDatetime) {
        this.modifyDatetime = modifyDatetime;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}