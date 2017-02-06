package com.yeren.common.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.yeren.common.bo.User;
import com.yeren.common.dto.UserDto;
import com.yeren.common.exception.BaseException;

public interface UserService {
	void save(User user);
	void delete(User user);
	void update(User user);
	User find(int id);
	List<UserDto> findUserByUsername(String username) throws IllegalAccessException, InvocationTargetException;
	UserDto register(User user);
	UserDto login(String username,String password) throws BaseException ;
	long getUserNum();
}
