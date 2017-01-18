package com.yeren.common.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yeren.common.bo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
public class TestUserService {

	@Autowired
	UserService userService;

	// @Test
	public void testSave() {
		User user = new User();
		user.setMobile("182175543888");
		userService.save(user);
	}

	// @Test
	public void TestDelete() {
		User user = new User();
		user.setId(2);
		userService.delete(user);
	}

	// @Test
	public void TestUpdate() {
		User user = new User();
		user.setId(3);
		user.setNickname("yeren108");
		userService.update(user);
	}

	// @Test
	public void TestGetUserById() {
		User user = userService.getUserById(1);
		System.out.println(user.getMobile());
	}

	// @Test
	public void TestFindUserByUsername() {
		User user = null;
		List<User> listUser = userService.findUserByUsername("yeren");
		user = listUser.get(0);
		System.out.println(user.getMobile());
	}

	@Test
	public void TestGetUserNum() {
		long userNum = userService.getUserNum();
		System.out.println(userNum);
	}

}
