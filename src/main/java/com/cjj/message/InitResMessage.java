package com.cjj.message;

import com.cjj.message.base.BaseResMessage;

public class InitResMessage extends BaseResMessage{
	
	private String networkSpeed;
	private String cardMaxBalance;
	public InitResMessage(InitReqMessage initReqMessage){
		super(initReqMessage);
	}
	public String getNetworkSpeed() {
		return networkSpeed;
	}
	public void setNetworkSpeed(String networkSpeed) {
		this.networkSpeed = networkSpeed;
	}
	public String getCardMaxBalance() {
		return cardMaxBalance;
	}
	public void setCardMaxBalance(String cardMaxBalance) {
		this.cardMaxBalance = cardMaxBalance;
	}
}
