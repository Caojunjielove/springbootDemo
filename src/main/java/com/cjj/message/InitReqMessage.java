package com.cjj.message;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.cjj.message.base.BaseReqMessage;

public class InitReqMessage extends BaseReqMessage{
	
	@NotEmpty(message="{init.calling}")
	@Pattern(regexp="^(13[0-9]|15[7-9]|153|156|18[7-9])[0-9]{8}$",message="{init.calling.pattern}")
	private String calling;
	
	private String merId;
	
	private String clientVer;

	public String getCalling() {
		return calling;
	}

	public void setCalling(String calling) {
		this.calling = calling;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getClientVer() {
		return clientVer;
	}

	public void setClientVer(String clientVer) {
		this.clientVer = clientVer;
	}

	@Override
	public String toString() {
		return "InitReqMessage [calling=" + calling + ", merId=" + merId + ", clientVer=" + clientVer + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
