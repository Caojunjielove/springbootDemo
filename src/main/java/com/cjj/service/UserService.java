package com.cjj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjj.dao.UserDao;
import com.cjj.model.UserEntity;
import com.github.pagehelper.PageHelper;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public List<UserEntity> seacherAll(){
		return userDao.searchAll();
	}
	
	public List<UserEntity> findAllByPage(int pageNo, int pageSize){
		PageHelper.startPage(pageNo, pageSize);
		return userDao.searchAll();
	}

	public List<UserEntity> findByName(int pageNo, int pageSize,String nicheng){
		PageHelper.startPage(pageNo, pageSize);
		return userDao.searchByName(nicheng);
	}

	public List<UserEntity> searchBySex(int pageNo, int pageSize,int sex){
		PageHelper.startPage(pageNo, pageSize);
		return userDao.searchBySex(sex);
	}
}
