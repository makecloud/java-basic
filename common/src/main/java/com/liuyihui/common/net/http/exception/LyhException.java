package com.liuyihui.common.net.http.exception;

/**
 * 云歌广告平台异常类
 * 
 * @author yinjy
 */
public class LyhException extends Exception {

	/** 异常编码 */
	private ExceptionCode code = ExceptionCode.UNKNOWN_ERROR;

	/**
	 * 构造函数
	 */
	public LyhException() {
		super();
	}

	/**
	 * 构造函数
	 * 
	 * @param code 异常编码
	 */
	public LyhException(ExceptionCode code) {
		super();
		this.code = code;
	}

	/**
	 * 构造函数
	 * 
	 * @param message 异常消息
	 */
	public LyhException(String message) {
		super(message);
	}

	/**
	 * 构造函数
	 * 
	 * @param code 异常编码
	 * @param message 异常消息
	 */
	public LyhException(ExceptionCode code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * 构造函数
	 * 
	 * @param cause 异常原因
	 */
	public LyhException(Throwable cause) {
		super(cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param code 异常编码
	 * @param cause 异常原因
	 */
	public LyhException(ExceptionCode code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	/**
	 * 构造函数
	 * 
	 * @param message 异常消息
	 * @param cause 异常原因
	 */
	public LyhException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 构造函数
	 * 
	 * @param code 异常编码
	 * @param message 异常消息
	 * @param cause 异常原因
	 */
	public LyhException(ExceptionCode code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	/**
	 * 获取异常编码
	 * 
	 * @return 异常编码
	 */
	public ExceptionCode getCode() {
		return code;
	}

}
