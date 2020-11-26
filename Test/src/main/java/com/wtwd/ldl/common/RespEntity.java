package com.wtwd.ldl.common;

import javafx.beans.binding.ObjectExpression;

/**
 * @Author ldaoliang
 * @Date 2019/10/18 0018 上午 11:06
 * @Description TODO
 **/
public class RespEntity {

	private int code;
	private String msg;
	private Object data;

	public RespEntity(RespCode respCode){
		this.code = respCode.getCode();
		this.msg = respCode.getMsg();
	}

	public RespEntity(RespCode respCode, Object data){
		this(respCode);
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
