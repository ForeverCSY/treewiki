package com.wjs.treewiki.dao.auth;

import com.wjs.common.dao.PageDataList;
import com.wjs.treewiki.model.auth.UserItem;
import com.wjs.treewiki.model.auth.UserItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserItemMapper {
    /**
     * user_item数据表的操作方法: countByExample  
     * 
     */
    int countByExample(UserItemCriteria example);

    /**
     * user_item数据表的操作方法: deleteByExample  
     * 
     */
    int deleteByExample(UserItemCriteria example);

    /**
     * user_item数据表的操作方法: deleteByPrimaryKey  
     * 
     */
    int deleteByPrimaryKey(Long id);

    /**
     * user_item数据表的操作方法: insert  
     * 
     */
    int insert(UserItem record);

    /**
     * user_item数据表的操作方法: insertSelective  
     * 
     */
    int insertSelective(UserItem record);

    /**
     * user_item数据表的操作方法: selectByExample  
     * 
     */
    List<UserItem> selectByExample(UserItemCriteria example);

    /**
     * user_item数据表的操作方法: selectByPrimaryKey  
     * 
     */
    UserItem selectByPrimaryKey(Long id);

    /**
     * user_item数据表的操作方法: lockByPrimaryKey  
     * 
     */
    UserItem lockByPrimaryKey(Long id);

    /**
     * user_item数据表的操作方法: lockByExample  
     * 
     */
    UserItem lockByExample(UserItemCriteria example);

    /**
     * user_item数据表的操作方法: pageByExample  
     * 
     */
    PageDataList<UserItem> pageByExample(UserItemCriteria example);

    /**
     * user_item数据表的操作方法: lastInsertId  
     * 线程安全的获得当前连接，最近一个自增长主键的值（针对insert操作）
     * 使用last_insert_id()时要注意，当一次插入多条记录时(批量插入)，只是获得第一次插入的id值，务必注意。
     * 
     */
    Long lastInsertId();

    /**
     * user_item数据表的操作方法: updateByExampleSelective  
     * 
     */
    int updateByExampleSelective(@Param("record") UserItem record, @Param("example") UserItemCriteria example);

    /**
     * user_item数据表的操作方法: updateByExample  
     * 
     */
    int updateByExample(@Param("record") UserItem record, @Param("example") UserItemCriteria example);

    /**
     * user_item数据表的操作方法: updateByPrimaryKeySelective  
     * 
     */
    int updateByPrimaryKeySelective(UserItem record);

    /**
     * user_item数据表的操作方法: updateByPrimaryKey  
     * 
     */
    int updateByPrimaryKey(UserItem record);
}