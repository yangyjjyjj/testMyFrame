package com.wtwd.ldl.common;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author ldaoliang
 * @Date 2019/10/18 0018 上午 11:08
 * @Description TODO
 **/
public class RespCodeEntity {
	private int code;
	private String msg;

	public RespCodeEntity(RespCode respCode){
		this.code = respCode.getCode();
		this.msg = respCode.getMsg();
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
}
