package com.yeren.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yeren.common.bo.User;

@Repository("userDao")
public class UserDao extends BaseDao{
	public void save(User user){
		getHibernateTemplate().save(user);
	}
	
	public void update(User user){
		getHibernateTemplate().update(user);
	}
	
	public void delete(User user){
		getHibernateTemplate().delete(user);
	}
	
	public User getUserById(int id) {
		return getHibernateTemplate().get(User.class, id);
	}

	public long getUserNum() {
		Object obj = getHibernateTemplate().iterate(
				"select count(u.id) from User u").next();
		return (Long) obj;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findUserByUsername(String username) {
		return (List<User>) getHibernateTemplate().find(
				"from User u where u.username like ?", username + "%");
	}
}
