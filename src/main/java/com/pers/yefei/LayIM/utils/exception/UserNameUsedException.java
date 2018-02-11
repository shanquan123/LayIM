package com.pers.yefei.LayIM.utils.exception;


public class UserNameUsedException extends ServerBaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param parameter:该值是内部输出的值
	 */
	public UserNameUsedException(String parameter) {
		super(ResponseCodeEnum.UserNameUsed, ":"+parameter);
	}

	/**
	 */
	public UserNameUsedException() {
		super(ResponseCodeEnum.UserNameUsed);
	}




	public UserNameUsedException(String parameter, Exception e) {
		super(ResponseCodeEnum.UserNameUsed, ":"+parameter, e);
	}

	@Override
	public String getMessage() {
		return this.reason;
	}
}
