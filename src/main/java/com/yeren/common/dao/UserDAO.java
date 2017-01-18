package com.yeren.common.dao;

import com.yeren.common.bo.User;


public interface UserDAO extends BaseDAO<User>{
	boolean checkUserId(String userId);
	boolean checkUserName(String userName);
	boolean checkMobile(String mobile);
	public boolean checkEmail(String email);
	public User getByUserId(String id);
}
