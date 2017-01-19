
package com.yeren.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeren.common.aop.annotation.Parameter;
import com.yeren.common.bo.User;
import com.yeren.common.constrant.ExceptionExcuteModel;
import com.yeren.common.exception.BaseException;
import com.yeren.common.parameter.BasicRequestParameter;
import com.yeren.common.result.BaseResult;
import com.yeren.common.service.UserService;


@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@Parameter(require="username,password",model = ExceptionExcuteModel.RESTURN_JSON,parameter=BasicRequestParameter.class)
	@ResponseBody
	public BaseResult<Map<String,String>> login(HttpServletRequest request,HttpServletResponse response,BasicRequestParameter parameter) throws BaseException{
		List<User> listUser = userService.findUserByUsername(parameter.getUsername());
		Map<String, String> data=new HashMap<String, String>();
		data.put("sessionToken",listUser.get(0).getUsername());
		data.put("id",listUser.get(0).getId().toString());
		return new BaseResult<Map<String,String>>("登录成功",data);
	}
	

}
