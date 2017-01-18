package com.yeren.common.service;

import java.util.List;

import com.yeren.common.bo.User;

public interface UserService {
	void save(User user);
	void update(User user);
	void delete(User user);
	User getUserById(int id);
	long getUserNum();
	List<User> findUserByUsername(String username);
}
