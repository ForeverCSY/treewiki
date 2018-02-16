package com.wjs.treewiki.dao.auth;

import com.wjs.common.dao.PageDataList;
import com.wjs.treewiki.model.auth.User;
import com.wjs.treewiki.model.auth.UserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * user数据表的操作方法: countByExample  
     * 
     */
    int countByExample(UserCriteria example);

    /**
     * user数据表的操作方法: deleteByExample  
     * 
     */
    int deleteByExample(UserCriteria example);

    /**
     * user数据表的操作方法: deleteByPrimaryKey  
     * 
     */
    int deleteByPrimaryKey(Long id);

    /**
     * user数据表的操作方法: insert  
     * 
     */
    int insert(User record);

    /**
     * user数据表的操作方法: insertSelective  
     * 
     */
    int insertSelective(User record);

    /**
     * user数据表的操作方法: selectByExample  
     * 
     */
    List<User> selectByExample(UserCriteria example);

    /**
     * user数据表的操作方法: selectByPrimaryKey  
     * 
     */
    User selectByPrimaryKey(Long id);

    /**
     * user数据表的操作方法: lockByPrimaryKey  
     * 
     */
    User lockByPrimaryKey(Long id);

    /**
     * user数据表的操作方法: lockByExample  
     * 
     */
    User lockByExample(UserCriteria example);

    /**
     * user数据表的操作方法: pageByExample  
     * 
     */
    PageDataList<User> pageByExample(UserCriteria example);

    /**
     * user数据表的操作方法: lastInsertId  
     * 线程安全的获得当前连接，最近一个自增长主键的值（针对insert操作）
     * 使用last_insert_id()时要注意，当一次插入多条记录时(批量插入)，只是获得第一次插入的id值，务必注意。
     * 
     */
    Long lastInsertId();

    /**
     * user数据表的操作方法: updateByExampleSelective  
     * 
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserCriteria example);

    /**
     * user数据表的操作方法: updateByExample  
     * 
     */
    int updateByExample(@Param("record") User record, @Param("example") UserCriteria example);

    /**
     * user数据表的操作方法: updateByPrimaryKeySelective  
     * 
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * user数据表的操作方法: updateByPrimaryKey  
     * 
     */
    int updateByPrimaryKey(User record);
}