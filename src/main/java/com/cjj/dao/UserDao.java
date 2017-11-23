package com.cjj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import com.cjj.model.UserEntity;

public interface UserDao {
	List<UserEntity> searchByName(String nicheng);
	List<UserEntity> searchAll();
	
	@Insert("insert into user(u_id,nicheng,sex,phone,createtime,updtime) values(null,#{nicheng},#{sex},#{phone},#{createTime},#{updtime})")
	void insert(UserEntity user);
	
	List<UserEntity> searchBySex(Integer sex);
}
