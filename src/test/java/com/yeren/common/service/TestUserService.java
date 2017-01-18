package com.yeren.common.service;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeren.common.bo.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-context-test.xml")
public class TestUserService {
	@Autowired
	@Qualifier("sessionFactory")
	SessionFactory sf;

	@Autowired
	UserService  userService;

	@Test
	public void testSave() {
		User user = new User();
		user.setMobile("182175543888");
		userService.save(user);
	}

	 @Test
	public void testFindById() {
		 User user = userService.findById(1);
		 System.out.println("mobile:"+user.getMobile());
		
	}

}
