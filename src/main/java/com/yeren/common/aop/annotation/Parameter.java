package com.yeren.common.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yeren.common.constrant.DecryptExcuteModel;
import com.yeren.common.constrant.ExceptionExcuteModel;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Parameter {
	/**
	 * 请求当前接口所需要的参数,多个以小写的逗号隔开
	 * @return
	 */
	public String require() default "";
	
	public Class<?> parameter() default Object.class;
	
	public String parameterIndex() default "-1";
	
	/**
	 *  示例：
	 *  Patameter(group="key|user&value,age|code")
	 *  以上情况是两组第一组是，如果key为空，那么user ,value必须非空，否则反之
	 *  第二组是，age和code必须有一个是非空的
	 */
	public String group() default "";
	
	public ExceptionExcuteModel model() default ExceptionExcuteModel.RESTURN_JSON;
	
	public DecryptExcuteModel decrypt() default DecryptExcuteModel.TRUE;
	
}
