package com.yeren.common.exception;

import com.yeren.common.constrant.ExceptionExcuteModel;

public class BaseException extends Exception {
	private static final long serialVersionUID = 8532651197560816647L;
	private ExceptionExcuteModel model = ExceptionExcuteModel.NOTHING;
	private String errorMessage;
	private String code;

	public BaseException() {
		super();
	}

	public BaseException(Exception e) {
		super(e);
	}

	public BaseException(String code, String message) {
		super(message);
		this.errorMessage = message;
		this.code = code;
	}

	public BaseException(String message) {
		super(message);
		this.errorMessage = message;
	}

	public BaseException(String message, ExceptionExcuteModel mode) {
		super(message);
		this.model = mode;
		this.errorMessage = message;
	}

	public BaseException(String code, String message, ExceptionExcuteModel mode) {
		super(message);
		this.model = mode;
		this.errorMessage = message;
		this.code = code;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public ExceptionExcuteModel getModel() {
		return model;
	}

	public void setModel(ExceptionExcuteModel model) {
		this.model = model;
	}

}
