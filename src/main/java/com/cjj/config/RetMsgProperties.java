package com.cjj.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:ValidationMessages.properties")
@ConfigurationProperties(prefix = "retMsg")
public class RetMsgProperties {

	private Map<String, String> msg = new HashMap<String, String>();
	
	public Map<String, String> getMsg() {
		return msg;
	}

	public void setMsg(Map<String, String> msg) {
		this.msg = msg;
	}

}
