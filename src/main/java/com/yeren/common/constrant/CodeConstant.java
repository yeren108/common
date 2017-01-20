package com.yeren.common.constrant;

public class CodeConstant {

	/* password:50XX */
	public static final String PASSWORD_LENGTH_EXCEPTION = "5001";
	public static final String PASSWORD_LENGTH_EXCEPTION_MSG = "密码长度不合格";

	public static final String PASSWORD_DECRYPT_EXCEPTION = "5002";
	public static final String PASSWORD_DECRYPT_EXCEPTION_MSG = "密码解密失败";

	public static final String PASSWORD_ENCRYPT_EXCEPTION = "5003";
	public static final String PASSWORD_ENCRYPT_EXCEPTION_MSG = "密码加密失败";

	/* username:51XX */
	public static final String USERNAME_FORMAT_ERROR_EXCEPTION = "5101";
	public static final String USERNAME_FORMAT_ERROR_EXCEPTION_MSG = "用户名格式错误";

	public static final String USERNAME_EXISTED_EXCEPTION = "5102";
	public static final String USERNAME_EXISTED_EXCEPTION_MSG = "用户名已存在";

	public static final String USERNAME_NOT_EXISTED_EXCEPTION = "5103";
	public static final String USERNAME_NOT_EXISTED_EXCEPTION_MSG = "用户不存在";

	/* username&password:52XX */
	public static final String USERNAME_OR_PASSWORD_ERROR_EXCEPTION = "5201";
	public static final String USERNAME_OR_PASSWORD_ERROR_EXCEPTION_MSG = "用户名或者密码错误";

	/* mobile:53XX */
	public static final String MOBILE_NOT_EXISTED_EXCEPTION = "5301";
	public static final String MOBILE_NOT_EXISTED_EXCEPTION_MSG = "手机号不存在";

	public static final String MOBILE_EXISTED_EXCEPTION = "5302";
	public static final String MOBILE_EXISTED_EXCEPTION_MSG = "手机号已存在";

	public static final String MOBILE_FORMAT_ERROR_EXCEPTION = "5303";
	public static final String MOBILE_FORMAT_ERROR_EXCEPTION_MSG = "手机号格式错误";

	/* mail:54XX */
	public static final String MAIL_EXISTED_EXCEPTION = "5401";
	public static final String MAIL_EXISTED_EXCEPTION_MSG = "邮箱已存在";

	public static final String MAIL_FORMAT_ERROR_EXCEPTION = "5402";
	public static final String MAIL_FORMAT_ERROR_EXCEPTION_MSG = "用户名格式错误";

	/* idcard:55XX */
	public static final String IDCARD_FORMAT_ERROR = "5501";
	public static final String IDCARD_FORMAT_ERROR_MSG = "身份证号格式不正确";

	public static final String IDCARD_EMPTY = "5502";
	public static final String IDCARD_EMPTY_MSG = "身份证号为空";

	/* format:56XX */
	public static final String FORMAT_ERROR_EXCEPTION = "5601";
	public static final String FORMAT_ERROR_EXCEPTION_MSG = "格式异常";

	/* decrypt:57XX */
	public static final String PARAMETER_DECRYPT_FAIL = "5701";
	public static final String PARAMETER_DECRYPT_FAIL_MSG = "参数解密失败";

}