package com.yeren.common.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeren.common.bo.User;
import com.yeren.common.dao.UserDao;
import com.yeren.common.dto.UserDto;
import com.yeren.common.exception.BaseException;
import com.yeren.common.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public User find(int id) {
		return userDao.find(id);
	}

	@Override
	public long getUserNum() {
		return userDao.getUserNum();
	}

	@Override
	public List<UserDto> findUserByUsername(String username) throws IllegalAccessException, InvocationTargetException {
		return userDao.findUserByUsername(username);
	}

	@Override
	public UserDto register(User user) {
		userDao.save(user);
		UserDto userDto=new UserDto();
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

	@Override
	public UserDto login(String username, String password) throws BaseException {
		return userDao.login(username, password);
	}

}
