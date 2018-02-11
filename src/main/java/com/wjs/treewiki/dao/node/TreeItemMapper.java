package com.wjs.treewiki.dao.node;

import com.wjs.common.dao.PageDataList;
import com.wjs.treewiki.model.node.TreeItem;
import com.wjs.treewiki.model.node.TreeItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeItemMapper {
    /**
     * tree_item数据表的操作方法: countByExample  
     * 
     */
    int countByExample(TreeItemCriteria example);

    /**
     * tree_item数据表的操作方法: deleteByExample  
     * 
     */
    int deleteByExample(TreeItemCriteria example);

    /**
     * tree_item数据表的操作方法: deleteByPrimaryKey  
     * 
     */
    int deleteByPrimaryKey(Long id);

    /**
     * tree_item数据表的操作方法: insert  
     * 
     */
    int insert(TreeItem record);

    /**
     * tree_item数据表的操作方法: insertSelective  
     * 
     */
    int insertSelective(TreeItem record);

    /**
     * tree_item数据表的操作方法: selectByExample  
     * 
     */
    List<TreeItem> selectByExample(TreeItemCriteria example);

    /**
     * tree_item数据表的操作方法: selectByPrimaryKey  
     * 
     */
    TreeItem selectByPrimaryKey(Long id);

    /**
     * tree_item数据表的操作方法: lockByPrimaryKey  
     * 
     */
    TreeItem lockByPrimaryKey(Long id);

    /**
     * tree_item数据表的操作方法: lockByExample  
     * 
     */
    TreeItem lockByExample(TreeItemCriteria example);

    /**
     * tree_item数据表的操作方法: pageByExample  
     * 
     */
    PageDataList<TreeItem> pageByExample(TreeItemCriteria example);

    /**
     * tree_item数据表的操作方法: lastInsertId  
     * 线程安全的获得当前连接，最近一个自增长主键的值（针对insert操作）
     * 使用last_insert_id()时要注意，当一次插入多条记录时(批量插入)，只是获得第一次插入的id值，务必注意。
     * 
     */
    Long lastInsertId();

    /**
     * tree_item数据表的操作方法: updateByExampleSelective  
     * 
     */
    int updateByExampleSelective(@Param("record") TreeItem record, @Param("example") TreeItemCriteria example);

    /**
     * tree_item数据表的操作方法: updateByExample  
     * 
     */
    int updateByExample(@Param("record") TreeItem record, @Param("example") TreeItemCriteria example);

    /**
     * tree_item数据表的操作方法: updateByPrimaryKeySelective  
     * 
     */
    int updateByPrimaryKeySelective(TreeItem record);

    /**
     * tree_item数据表的操作方法: updateByPrimaryKey  
     * 
     */
    int updateByPrimaryKey(TreeItem record);
}