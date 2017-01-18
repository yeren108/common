package com.yeren.common.service;

import java.util.List;

import com.yeren.common.bo.User;

public interface UserService {
	void save(User user);
	void delete(User user);
	void update(User user);
	List<User> find(User user);
	User findById(Integer id);//通过站点ID找站点
	List<User> findByAttribute(String attribute);//模糊查询
}
