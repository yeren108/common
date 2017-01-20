package com.yeren.common.result;

import java.io.Serializable;

public class BaseResult<T> implements Serializable {

	private static final long serialVersionUID = -3301518437992187315L;
	private String msg = "操作成功";// 消息文本
	private String code = "200";// 接口应答码
	private T data;// 业务数据

	public BaseResult() {
	}

	public BaseResult(String msg) {
		this.msg = msg;
	}

	public BaseResult(T data) {
		this.data = data;
	}

	public BaseResult(String msg, T data) {
		this.msg = msg;
		this.data = data;
	}

	public BaseResult(String code, String msg,  T data) {
		super();
		this.msg = msg;
		this.code = code;
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
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

}
