package com.cjj.dao;

import java.util.List;

import com.cjj.model.UserEntity;

public interface UserDao {
	List<UserEntity> searchByName(String nicheng);
	List<UserEntity> searchAll();
	List<UserEntity> searchBySex(Integer sex);
}
