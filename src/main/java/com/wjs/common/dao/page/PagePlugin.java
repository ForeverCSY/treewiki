package com.wjs.common.dao.page;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.springframework.jdbc.support.JdbcUtils;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.util.JdbcConstants;
import com.wjs.common.dao.PageDataList;

@Intercepts(@Signature(method = "handleResultSets", type = ResultSetHandler.class, args = { Statement.class }))
public class PagePlugin implements Interceptor {

	private Properties properties;

	private String pageMethod;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		DefaultResultSetHandler resultSetHandler = (DefaultResultSetHandler) invocation.getTarget();
		MetaObject metaResultSetHandler = MetaObject.forObject(resultSetHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory());

		MappedStatement stmt = (MappedStatement) metaResultSetHandler.getValue("mappedStatement");
		String id = stmt.getId();
		if (id.substring(id.lastIndexOf(".") + 1, id.length()).equals(pageMethod)) {
			
			Object obj = invocation.proceed();
			List list = (List) obj;
			Integer count = list.size();

			Connection connection = (Connection) metaResultSetHandler.getValue("executor.delegate.transaction.connection"); 
			ParameterHandler parameterHandler = (ParameterHandler) metaResultSetHandler.getValue("parameterHandler");  
			BoundSql boundSql = (BoundSql) metaResultSetHandler.getValue("parameterHandler.boundSql");
			
			int start = 0;
			int limit = 10;
			// 获取起始行和结束行
			Object parameterObject = parameterHandler.getParameterObject();
			Class parameterClaz = (Class) parameterObject.getClass();

			start = getField(parameterObject, parameterClaz, "start");
			
			limit = getField(parameterObject, parameterClaz, "limit");
			  
			String countSql = getCountSql(boundSql.getSql());
			count = getTotalRecord(connection, countSql, parameterHandler);
			
			
			PageDataList page = new PageDataList();
			page.setPageSize(limit);
			int curPage = start / limit + 1;
			page.setPage(curPage);
			page.setTotal(count);
			page.setRows(list);

			List<PageDataList> result = new ArrayList<PageDataList>();
			result.add(page);
			return result;
		}

		return invocation.proceed();
	}
	
	@SuppressWarnings("rawtypes")
	private int getField(Object parameterObject, Class clazz, String fieldName)
			throws NoSuchFieldException, IllegalAccessException {
		Field startField = null;
		for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
	            try {  
	            	startField = clazz.getDeclaredField(fieldName) ;  
	            	break;
	            } catch (Exception e) {  
	                // ingnore              
	            }   
	        }  
		
		if(null != startField){
			startField.setAccessible(true);
			return (Integer)startField.get(parameterObject);
		}else{
			throw new RuntimeException("please set field-start in a pageQry");
		}
	}

	private Integer getTotalRecord(Connection connection, String countSql, ParameterHandler parameterHandler) {

		PreparedStatement preparedStatement = null;  
        ResultSet resultSet = null;  
        try {  
  
            preparedStatement = connection.prepareStatement(countSql);  
            parameterHandler.setParameters(preparedStatement);  
            resultSet = preparedStatement.executeQuery();  
            resultSet.next();  
  
            return (Integer) JdbcUtils.getResultSetValue(resultSet, 1, Integer.class);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally {  
            JdbcUtils.closeResultSet(resultSet);  
            JdbcUtils.closeStatement(preparedStatement);  
        }  
        return 0;  
	}

	private String getCountSql(String sql) {
		
		String dbType = JdbcConstants.MYSQL;

		//格式化输出
		String result = SQLUtils.format(sql, dbType);
//		System.out.println(result);
		StringBuffer countSql = new StringBuffer(64);
		countSql.append("select count(1) from ");
		countSql.append(result.substring(result.indexOf("\nFROM ")+5 , result.lastIndexOf("\nLIMIT ")));
//		System.out.println(countSql.toString());
		return countSql.toString();
	}
	
	public static void main(String[] args) {

		String sql = "select a.company_base_id,a.open_FROM from (select company_base_id , open_from , open_to  from company_basic_info JOIN customs_credit_info) a limit 1,2";
		String dbType = JdbcConstants.MYSQL;

		//格式化输出
		String result = SQLUtils.format(sql, dbType);
		System.out.println(result);
		StringBuffer countSql = new StringBuffer(64);
		countSql.append("select count(1) from ");
		countSql.append(result.substring(result.indexOf("\nFROM ")+5 , result.lastIndexOf("\nLIMIT ")));
		System.out.println(countSql.toString()); 
		
		

	}
	@Override
	public Object plugin(Object target) {

		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

		this.properties = properties;

	}

	public String getPageMethod() {

		return pageMethod;
	}

	public void setPageMethod(String pageMethod) {

		this.pageMethod = pageMethod;
	}

	public Properties getProperties() {

		return properties;
	}

}
