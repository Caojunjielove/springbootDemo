package com.cjj.exception;

import com.cjj.message.base.BaseReqMessage;

public class MyException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseReqMessage baseReqMessage;
	private String code;
	private String msg;
	
	public MyException(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public MyException(BaseReqMessage baseReqMessage,String code, String msg) {
		this.baseReqMessage = baseReqMessage;
		this.code = code;
		this.msg = msg;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BaseReqMessage getBaseReqMessage() {
		return baseReqMessage;
	}

	public void setBaseReqMessage(BaseReqMessage baseReqMessage) {
		this.baseReqMessage = baseReqMessage;
	}
}
