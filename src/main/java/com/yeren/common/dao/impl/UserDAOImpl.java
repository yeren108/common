package com.yeren.common.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yeren.common.bo.User;
import com.yeren.common.dao.UserDAO;



@Repository("userDAO")
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {

	public boolean checkUserId(String userId) {
		List<User> users = findByCriteria("id", userId);
		if (users != null && users.size() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean checkUserName(String userName) {
		List<User> users = findByCriteria("username", userName);
		if (users != null && users.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean checkMobile(String mobile) {
		List<User> users = findByCriteria("mobile", mobile);
		if (users != null && users.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean checkEmail(String email) {
		List<User> users = findByCriteria("email", email);
		if (users != null && users.size() > 0) {
			return true;
		}
		return false;
	}
	
	public User getByUserId(String id) {
		User user = find(id);
		return user;
	}

}
