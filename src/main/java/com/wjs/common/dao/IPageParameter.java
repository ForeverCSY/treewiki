/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.wjs.common.dao;

public abstract interface IPageParameter {
	public abstract int getStart();

	public abstract int getLimit();

	public abstract boolean isRequireTotal();

	public abstract void setTotal(int paramInt);
}