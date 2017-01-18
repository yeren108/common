package com.yeren.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeren.common.bo.User;
import com.yeren.common.dao.UserDao;
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
	public User getUserById(int id) {
		return userDao.getUserById(id);
	}

	@Override
	public long getUserNum() {
		return userDao.getUserNum();
	}

	@Override
	public List<User> findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}



}
