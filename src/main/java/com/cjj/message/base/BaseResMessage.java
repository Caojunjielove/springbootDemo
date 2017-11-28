package com.cjj.message.base;

public class BaseResMessage extends BaseMessage {

	private String retCode;
	
	private String retMsg;

	public BaseResMessage(){}
	
	public BaseResMessage(BaseReqMessage baseReqMessage){
		this.setFunCode(baseReqMessage.getFunCode());
		this.setReqDate(baseReqMessage.getReqDate());
		this.setReqTime(baseReqMessage.getReqTime());
		this.setSignFlag(baseReqMessage.isSignFlag());
		this.setStartTime(baseReqMessage.getStartTime());
		this.setRpid(baseReqMessage.getRpid());
	}
	
	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}


	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	
	@Override
	public String toString() {
		return "BaseResMessage [retCode=" + retCode + ", retMsg=" + retMsg + ", toString()=" + super.toString() + "]";
	}
}
