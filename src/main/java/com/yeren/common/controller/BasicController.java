/** 
 * Copyright (c) 2003-2015 Wonders Information Co.,Ltd. All Rights Reserved.
 * 5-6/F, 20 Bldg, 481 Guiping RD. Shanghai 200233,PRC
 *
 * This software is the confidential and proprietary information of Wonders Group.
 * (Research & Development Center). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gun.org
 */

package com.yeren.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yeren.common.aop.annotation.Parameter;
import com.yeren.common.constrant.ExceptionExcuteModel;
import com.yeren.common.exception.BaseException;
import com.yeren.common.parameter.BasicRequestParameter;


@Controller
@RequestMapping(value="/basic")
public class BasicController {
	
	@RequestMapping(value="/abc",method=RequestMethod.GET)
	@Parameter(require="username,password",model = ExceptionExcuteModel.RESTURN_JSON,parameter=BasicRequestParameter.class)
	@ResponseBody
	public Object basicAbc(HttpServletRequest request,HttpServletResponse response,BasicRequestParameter parameter) throws BaseException{
		System.out.println("fffffffffffffffffffff");
		if(parameter.getUsername().equals("12")){
			throw new BaseException("333","hhh");
		}else{
			return null;
		}
	}
	

}
