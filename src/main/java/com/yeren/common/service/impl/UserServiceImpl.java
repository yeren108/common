package com.yeren.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yeren.common.bo.User;
import com.yeren.common.dao.UserDAO;
import com.yeren.common.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO userDAO;
	
	@Override
	public void save(User user) {
		userDAO.save(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public List<User> find(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(Integer id) {
		return userDAO.find(id);
	}

	@Override
	public List<User> findByAttribute(String attribute) {
		// TODO Auto-generated method stub
		return null;
	}

}
