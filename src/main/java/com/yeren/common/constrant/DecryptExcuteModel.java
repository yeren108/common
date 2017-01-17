package com.yeren.common.constrant;
/**
 * 此枚举类配合RequestRequire注解一起使用
 * 当配置该注解时，aop的解密拦截器会解析当前枚举
 * 如果是TRUE则进行解密操作，反之什么也不处理
 * @author liubiao
 *
 */
public enum DecryptExcuteModel {
	TRUE,FALSE
}
