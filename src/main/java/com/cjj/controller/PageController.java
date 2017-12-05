package com.cjj.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjj.model.UserEntity;
import com.cjj.service.UserService;
import com.github.pagehelper.PageInfo;

@Controller
public class PageController {
	protected final Log log = LogFactory.getLog(this.getClass());
	@Autowired
	public UserService userService;
	

	@RequestMapping("/userList")
	public String dbTest(Model model){
//		List<UserEntity> list = userService.seacherAll();
//		model.addAttribute("users", list);
//		return "index";
		return "user";
	}

	@RequestMapping("/userList/{pageindex}")
	public String pageTest(@PathVariable("pageindex") int pageindex,Model model){
		List<UserEntity> list = userService.findAllByPage(pageindex, 1);
		PageInfo<UserEntity> pageInfo = new PageInfo<UserEntity>(list);
		model.addAttribute("pageInfo", pageInfo);
//		return "index";
		return "user";
	}
	@ResponseBody
	@RequestMapping("/testPage")
	public PageInfo<UserEntity> pageTest(@RequestParam("offset") int offset,@RequestParam("pageSize") int pageSize,@RequestParam("nicheng") String nicheng){
		int pageNum = offset/pageSize + 1;
		List<UserEntity> list = userService.findByName(pageNum, pageSize, nicheng);
		PageInfo<UserEntity> pageInfo = new PageInfo<UserEntity>(list);
		return pageInfo;
	}

	@ResponseBody
	@RequestMapping("/addUser")
	public UserEntity addUser(@RequestBody UserEntity user){
		Date now = new Date();
		user.setCreateTime(now);
		user.setUpdtime(now);
		userService.insertUser(user);
		return user;
	}
}
