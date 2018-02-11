package com.wjs.treewiki.dao.node;

import com.wjs.common.dao.PageDataList;
import com.wjs.treewiki.model.node.TreeContent;
import com.wjs.treewiki.model.node.TreeContentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TreeContentMapper {
    /**
     * tree_content数据表的操作方法: countByExample  
     * 
     */
    int countByExample(TreeContentCriteria example);

    /**
     * tree_content数据表的操作方法: deleteByExample  
     * 
     */
    int deleteByExample(TreeContentCriteria example);

    /**
     * tree_content数据表的操作方法: deleteByPrimaryKey  
     * 
     */
    int deleteByPrimaryKey(Long id);

    /**
     * tree_content数据表的操作方法: insert  
     * 
     */
    int insert(TreeContent record);

    /**
     * tree_content数据表的操作方法: insertSelective  
     * 
     */
    int insertSelective(TreeContent record);

    /**
     * tree_content数据表的操作方法: selectByExampleWithBLOBs  
     * 
     */
    List<TreeContent> selectByExampleWithBLOBs(TreeContentCriteria example);

    /**
     * tree_content数据表的操作方法: selectByExample  
     * 
     */
    List<TreeContent> selectByExample(TreeContentCriteria example);

    /**
     * tree_content数据表的操作方法: selectByPrimaryKey  
     * 
     */
    TreeContent selectByPrimaryKey(Long id);

    /**
     * tree_content数据表的操作方法: lockByPrimaryKey  
     * 
     */
    TreeContent lockByPrimaryKey(Long id);

    /**
     * tree_content数据表的操作方法: lockByExample  
     * 
     */
    TreeContent lockByExample(TreeContentCriteria example);

    /**
     * tree_content数据表的操作方法: pageByExample  
     * 
     */
    PageDataList<TreeContent> pageByExample(TreeContentCriteria example);

    /**
     * tree_content数据表的操作方法: lastInsertId  
     * 线程安全的获得当前连接，最近一个自增长主键的值（针对insert操作）
     * 使用last_insert_id()时要注意，当一次插入多条记录时(批量插入)，只是获得第一次插入的id值，务必注意。
     * 
     */
    Long lastInsertId();

    /**
     * tree_content数据表的操作方法: updateByExampleSelective  
     * 
     */
    int updateByExampleSelective(@Param("record") TreeContent record, @Param("example") TreeContentCriteria example);

    /**
     * tree_content数据表的操作方法: updateByExampleWithBLOBs  
     * 
     */
    int updateByExampleWithBLOBs(@Param("record") TreeContent record, @Param("example") TreeContentCriteria example);

    /**
     * tree_content数据表的操作方法: updateByExample  
     * 
     */
    int updateByExample(@Param("record") TreeContent record, @Param("example") TreeContentCriteria example);

    /**
     * tree_content数据表的操作方法: updateByPrimaryKeySelective  
     * 
     */
    int updateByPrimaryKeySelective(TreeContent record);

    /**
     * tree_content数据表的操作方法: updateByPrimaryKeyWithBLOBs  
     * 
     */
    int updateByPrimaryKeyWithBLOBs(TreeContent record);

    /**
     * tree_content数据表的操作方法: updateByPrimaryKey  
     * 
     */
    int updateByPrimaryKey(TreeContent record);
}