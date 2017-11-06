package com.cjj.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.cjj.exception.MyException;
import com.cjj.message.InitReqMessage;
import com.cjj.message.InitResMessage;
import com.cjj.message.base.BaseMessage;
import com.cjj.model.UserEntity;
import com.cjj.service.UserService;
import com.github.pagehelper.PageInfo;

@RestController
public class TestController {
	protected final Log log = LogFactory.getLog(this.getClass());
	@Autowired
	public UserService userService;
	@Autowired
    private Environment env;
	
	@RequestMapping(value="/YYCSHValid",produces="text/plain") 
	public BaseMessage init(@Valid @RequestBody InitReqMessage reqMsg,BindingResult bindingResult){
		log.debug("接收信息：" + reqMsg);
		if(bindingResult.hasErrors()){
			throw new MyException(reqMsg,"9999",bindingResult.getFieldError().getDefaultMessage());
		}
		InitResMessage resMsg = new InitResMessage(reqMsg);
		String netspeed = env.getProperty("init.netspeed");
		String maxcardbalance = env.getProperty("init.maxcardbalance");
		resMsg.setNetworkSpeed(netspeed);
		resMsg.setCardMaxBalance(maxcardbalance);
		resMsg.setRetCode("0000");
		resMsg.setRetMsg("success");
		return resMsg;
	}
	
	@RequestMapping(value="/YYCSH") 
	public BaseMessage init(@RequestBody InitReqMessage reqMsg){
		log.debug("接收信息：" + reqMsg);
		InitResMessage resMsg = new InitResMessage(reqMsg);
		String netspeed = env.getProperty("init.netspeed");
		String maxcardbalance = env.getProperty("init.maxcardbalance");
		resMsg.setNetworkSpeed(netspeed);
		resMsg.setCardMaxBalance(maxcardbalance);
		resMsg.setRetCode("0000");
		resMsg.setRetMsg("success");
		return resMsg;
	}
	@RequestMapping(value="/YYCSHACCEPT",produces="text/plain") 
	public BaseMessage initA(@RequestBody InitReqMessage reqMsg){
		log.debug("接收信息：" + reqMsg);
		InitResMessage resMsg = new InitResMessage(reqMsg);
		String netspeed = env.getProperty("init.netspeed");
		String maxcardbalance = env.getProperty("init.maxcardbalance");
		resMsg.setNetworkSpeed(netspeed);
		resMsg.setCardMaxBalance(maxcardbalance);
		resMsg.setRetCode("0000");
		resMsg.setRetMsg("success");
		return resMsg;
	}
	
	@RequestMapping(value="/YYCSHDB",produces="text/plain") 
	public InitResMessage initDB(@RequestBody InitReqMessage reqMsg){
		log.debug("接收信息：" + reqMsg);
		InitResMessage resMsg = new InitResMessage(reqMsg);
		List<UserEntity> list = userService.seacherAll();
		resMsg.setRetCode("0000");
		resMsg.setRetMsg("success");
		return resMsg;
	}
	@RequestMapping(value="/YYCSHDBValid",produces="text/plain") 
	public InitResMessage initDB(@Valid @RequestBody InitReqMessage reqMsg,BindingResult bindingResult){
		log.debug("接收信息：" + reqMsg);
		if(bindingResult.hasErrors()){
			throw new MyException(reqMsg,"9999",bindingResult.getFieldError().getDefaultMessage());
		}
		InitResMessage resMsg = new InitResMessage(reqMsg);
		List<UserEntity> list = userService.seacherAll();
		resMsg.setRetCode("0000");
		resMsg.setRetMsg("success");
		return resMsg;
	}

	@RequestMapping("/initTestError")
	public UserEntity init(@RequestBody UserEntity reqMsg){
		log.debug("接收信息：" + reqMsg);
		return reqMsg;
	}

	@RequestMapping("/user")
	public String dbTest(){
		List<UserEntity> list = userService.seacherAll();
		String userJson = JSON.toJSONString(list);
		return userJson;
	}

	@RequestMapping("/page/{pageindex}")
	public String pageTest(@PathVariable("pageindex") int pageindex){
		List<UserEntity> list = userService.findAllByPage(pageindex, 1);
		PageInfo<UserEntity> pageInfo = new PageInfo<UserEntity>(list);
		String pageInfoJson = JSON.toJSONString(pageInfo);
		return pageInfoJson;
	}
	
	@RequestMapping("/sex")
	public String pageSexTest(){
		List<UserEntity> list = userService.searchBySex(1, 2, 0);
		PageInfo<UserEntity> pageInfo = new PageInfo<UserEntity>(list);
		String pageInfoJson = JSON.toJSONString(pageInfo);
		return pageInfoJson;
	}
}
