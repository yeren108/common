package com.yeren.common.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import com.yeren.common.aop.annotation.Parameter;
import com.yeren.common.exception.BaseException;
import com.yeren.common.parameter.BasicRequestParameter;


@Aspect
@Component
public class ParameterAspect {
	static final Logger log = LoggerFactory.getLogger(ParameterAspect.class);
	public ParameterAspect() {
		log.info("初始化参数切面类......");
	}

	@Pointcut("@annotation(com.yeren.common.aop.annotation.Parameter)")
	public void controllerInteceptor() {
	}
	
	@Around("controllerInteceptor()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		log.info("");
		log.info("<<参数(拦截器):Start===");
		Object[] args = pjp.getArgs();
		MethodInvocationProceedingJoinPoint mjp = (MethodInvocationProceedingJoinPoint) pjp;
		MethodSignature signature = (MethodSignature) mjp.getSignature();
		Method method = signature.getMethod();
		Parameter parameter = method.getAnnotation(Parameter.class);
		Object obj = getParameter(args, parameter);
		BasicRequestParameter basicRequestParameter=(BasicRequestParameter)obj;
		Field[] declaredFields = basicRequestParameter.getClass().getDeclaredFields();
		for ( int i = 0 ; i < declaredFields. length ; i++){
	        Field field = declaredFields[i];
	        field.setAccessible( true ); // 设置些属性是可以访问的
	        Object val = field.get(basicRequestParameter); // 得到此属性的值    
	        log.info(field.getName()+ "：" +val);
	        /*if(val==null){
	        	throw new BaseException("555",field.getName()+"参数为空");
	        }*/
	    }
		log.info("=====参数(拦截器):End>>");
		log.info("");
		return pjp.proceed(args);
	}
	
	private Object getParameter(Object[] args, Parameter parameter) {
		Class<?> clz = parameter.parameter();
		for (Object obj : args) {
			if (obj.getClass().equals(clz)) {
				return obj;
			}
		}
		return null;
	}

}
