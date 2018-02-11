package com.pers.yefei.LayIM.utils.exception;


public class AuthFialedException extends ServerBaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param parameter:该值是内部输出的值
	 */
	public AuthFialedException(String parameter) {
		super(ResponseCodeEnum.AuthFialed, ":"+parameter);
	}

	/**
	 */
	public AuthFialedException() {
		super(ResponseCodeEnum.AuthFialed);
	}




	public AuthFialedException(String parameter, Exception e) {
		super(ResponseCodeEnum.AuthFialed, ":"+parameter, e);
	}

	@Override
	public String getMessage() {
		return this.reason;
	}
}
