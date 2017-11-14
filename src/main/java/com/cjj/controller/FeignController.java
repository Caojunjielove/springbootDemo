package com.cjj.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.cjj.exception.MyException;
import com.cjj.httpservice.HttpService;
import com.cjj.message.InitReqMessage;
import com.cjj.message.InitResMessage;
import com.cjj.message.base.BaseMessage;
import com.cjj.service.UserService;

@RestController
public class FeignController {
	protected final Log log = LogFactory.getLog(this.getClass());
	@Autowired
	public UserService userService;
	@Autowired
    private Environment env;
	@Autowired
	private HttpService httpService;
	
	@RequestMapping(value="/feign",produces="text/plain") 
	public BaseMessage init(@Valid @RequestBody InitReqMessage reqMsg,BindingResult bindingResult){
		log.debug("接收信息：" + reqMsg);
		if(bindingResult.hasErrors()){
			throw new MyException(reqMsg,"9999",bindingResult.getFieldError().getDefaultMessage());
		}
		String resStr = httpService.query(JSON.toJSONString(reqMsg));
		log.info("响应信息：" + resStr);
		
		InitResMessage resMsg = new InitResMessage(reqMsg);
		String netspeed = env.getProperty("init.netspeed");
		String maxcardbalance = env.getProperty("init.maxcardbalance");
		resMsg.setNetworkSpeed(netspeed);
		resMsg.setCardMaxBalance(maxcardbalance);
		resMsg.setRetCode("0000");
		resMsg.setRetMsg("success");
		return resMsg;
	}
	

}
