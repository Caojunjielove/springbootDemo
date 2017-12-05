package com.cjj.message.base;

import javax.servlet.http.HttpServletRequest;

import com.cjj.constant.RetCode;
import com.cjj.constant.RetMsg;

public class BaseResMessage extends BaseMessage {

	private String retCode;
	
	private String retMsg;

	public BaseResMessage(){}
	
	public BaseResMessage(BaseReqMessage baseReqMessage){
		setBaseReqMessage(baseReqMessage);
	}
	
	public void setBaseReqMessage(BaseReqMessage baseReqMessage){
		this.setFunCode(baseReqMessage.getFunCode());
		this.setReqDate(baseReqMessage.getReqDate());
		this.setReqTime(baseReqMessage.getReqTime());
		this.setSignFlag(baseReqMessage.isSignFlag());
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

	public void success(){
		setRetCode(RetCode.SUCCESS);
		setRetMsg(RetMsg.getMsg(RetCode.SUCCESS));
	}
	public static BaseResMessage error(){
		BaseResMessage baseMsg = new BaseResMessage();
    	baseMsg.setRetCode(RetCode.FAIL);
    	baseMsg.setRetMsg(RetMsg.getMsg(RetCode.FAIL));
    	return baseMsg;
	}

	public static BaseResMessage error(HttpServletRequest request){
		BaseResMessage baseMsg = error();
		request.setAttribute("resData", baseMsg);
		return baseMsg;
	}

	public static BaseResMessage error(HttpServletRequest request,String retCode,String retMsg){
		BaseResMessage baseMsg = error(request);
		baseMsg.setRetCode(retCode);
		baseMsg.setRetMsg(retMsg);
		return baseMsg;
	}
}
