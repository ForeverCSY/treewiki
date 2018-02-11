/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.wjs.common.dao;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class PageParameter implements IPageParameter, Serializable {
	private static final long serialVersionUID = 6245373576773306619L;
	public static final int PAGE_LIMIT_DEFAULT = 20;
	
	/**
	 * 起始行
	 */
	private int start;
	/**
	 * 每页行数
	 */
	private int limit;
	
	
	private boolean requireTotal;
	private int total;
	
	/**
	 * 当前第几页，从第一页开始
	 */
	private int page = 1;
	
	/**
	 * 每页个数
	 */
	private int rows = 10;
	
	/**
	 * 排序字段
	 */
	private String sort;
	

	/**
	 * 排序
	 */
	private String order;// asc/desc

	public PageParameter() {
		this.limit = 20;
		this.requireTotal = true;
	}
	
	
	
	
//	public PageParameter(int page, int limit) {
//		super();
//		setPage(page);
//		setLimit(limit);
//	}

	public int getStart() {
		return this.start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return this.limit;
	}

	public void setLimit(int limit) {
		
		this.limit = limit;
		this.rows = limit;
	}
	/**
	 * 兼容历史空间不建议使用
	 * @param total
	 * @author Silver 
	 */
	@Deprecated
	public int getTotal() {
		return this.total;
	}

	/**
	 * 兼容历史空间不建议使用
	 * @param total
	 * @author Silver 
	 */
	@Deprecated
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * 兼容历史空间不建议使用
	 * @param total
	 * @author Silver 
	 */
	@Deprecated
	public boolean isRequireTotal() {
		return this.requireTotal;
	}
	/**
	 * 兼容历史空间不建议使用
	 * @param total
	 * @author Silver 
	 */
	@Deprecated
	public void setRequireTotal(boolean requireTotal) {
		this.requireTotal = requireTotal;
	}

	
	public int getPage() {
		if(page < 1){
			page = 1;
		}
		return page;
	}

	
	public void setPage(int page) {
	
		this.page = page;
		this.start = (this.page -1) * this.limit;
		
	}
	/**
	 * 兼容历史空间不建议使用
	 * @param total
	 * @author Silver 
	 */
	@Deprecated
	public int getRows() {
		if(rows <= 0){
			rows = 1;
		}
		return rows;
	}
	/**
	 * 兼容历史空间不建议使用
	 * @param total
	 * @author Silver 
	 */
	@Deprecated
	public void setRows(int rows) {
	
		this.rows = rows;
		this.limit = rows;
	}

	
	public String getSort() {
	
		return sort;
	}

	
	public void setSort(String sort) {
	
		this.sort = sort;
	}
	/**
	 * 兼容历史空间不建议使用
	 * @param total
	 * @author Silver 
	 */
	@Deprecated
	public String getOrder() {
	
		return order;
	}
	/**
	 * 兼容历史空间不建议使用
	 * @param total
	 * @author Silver 
	 */
	@Deprecated
	public void setOrder(String order) {
	
		this.order = order;
	}
	
	@Override
	public String toString() {
	
		return ReflectionToStringBuilder.toString(this);
	}
	
	
}