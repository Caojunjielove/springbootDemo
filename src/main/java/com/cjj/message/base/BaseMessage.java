package com.cjj.message.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class BaseMessage {

	private String funCode;

	private String rpid;
	
	private String reqDate;
	
	private String reqTime;
	@JSONField(serialize=false)
	private long startTime;
	
	@JSONField(serialize=false)
	private boolean signFlag;

	public String getFunCode() {
		return funCode;
	}

	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}

	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}

	public boolean isSignFlag() {
		return signFlag;
	}

	public void setSignFlag(boolean signFlag) {
		this.signFlag = signFlag;
	}

	public String getRpid() {
		return rpid;
	}

	public void setRpid(String rpid) {
		this.rpid = rpid;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}


	@Override
	public String toString() {
		return "BaseMesaage [funCode=" + funCode + ", reqDate=" + reqDate + ", reqTime=" + reqTime + ", signFlag="
				+ signFlag + "]";
	}
	
	public static void main(String[] args) {
		BaseMessage baseMsg = new BaseMessage();
		baseMsg.setFunCode("123");
		baseMsg.setReqDate("123");
		baseMsg.setReqTime("123");
		baseMsg.setSignFlag(false);
		String json = JSON.toJSONString(baseMsg);
		System.out.println("json:" + json);
		
	}
}
	