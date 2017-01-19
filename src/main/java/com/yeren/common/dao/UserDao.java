package com.yeren.common.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.yeren.common.bo.User;
import com.yeren.common.dto.UserDto;

@Repository("userDao")
public class UserDao extends BaseDao<User> {

	public long getUserNum() {
		Object obj = getHibernateTemplate().iterate(
				"select count(u.id) from User u").next();
		return (Long) obj;
	}

	@SuppressWarnings("unchecked")
	public List<UserDto> findUserByUsername(String username) throws IllegalAccessException, InvocationTargetException {
		List<UserDto> listDto=new ArrayList<UserDto>();
		UserDto userDto=new UserDto();
		List<User> listBo = (List<User>) getHibernateTemplate().find("from User u where u.username like ?", username + "%");
		if(listBo!=null&&listBo.size()>0){
			BeanUtils.copyProperties(listBo.get(0),userDto);
			listDto.add(userDto);
		}
		return null;
	}
	
	
	public UserDto login(String username,String password){
		return null;
	}
	
	
}
