package com.wjs.common.web;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * 服务返回给客户端的json对象包装
 */
public class JsonResult<T> {
	/**
	 * 状态
	 */
	public static enum Status {
		// 正确
		SUCCESS("succ","成功"),
		// 错误
		ERROR("fail","失败"),
		
		WARN("warn","警告"),
		
		REQUESTBINDERROR("binderror","参数错误");


		private String code = "succ";

		private Status(String code,String msg) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

	}

	private String code;

	private String msg;
	
	/**
	 * 兼容BUI清算后台数据格式
	 */
	private String exception;

	private T data = null;

	public JsonResult(Status status, String msg) {
		this.code = status.code;
		if(JsonResult.Status.ERROR.equals(status)){
			this.exception = msg;
		}
		this.msg = msg;
	}

	public JsonResult(Status status, String msg, T data) {
		this.code = status.code;
		if(JsonResult.Status.ERROR.equals(status)){
			this.exception = msg;
		}
		this.msg = msg;
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setStatus(Status status) {
		if (status != null) {
			this.code = status.code;
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	public String getException() {
	
		return exception;
	}
	
	public void setException(String exception) {
	
		this.exception = exception;
	}

	
	public void setCode(String code) {
	
		this.code = code;
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
