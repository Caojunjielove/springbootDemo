package com.cjj.constant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cjj.config.RetMsgProperties;
@Component
public class RetMsg {
	
	@Autowired
	private RetMsgProperties retMsgProperties;
	
	private static RetMsgProperties RET_MSG_PROPERTIES;
	
	
	@PostConstruct
    public void init() {
		RET_MSG_PROPERTIES = retMsgProperties;
    }
	
	public static String getMsg(String code){
		return RET_MSG_PROPERTIES.getMsg().get(code) == null ? "交易失败" : RET_MSG_PROPERTIES.getMsg().get(code);
	}
}
