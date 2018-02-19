package com.pers.yefei.LayIM.utils.exception;


public class OutOfUserApplyCountException extends ServerBaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param parameter:该值是内部输出的值
	 */
	public OutOfUserApplyCountException(String parameter) {
		super(ResponseCodeEnum.UserNameUsed, ":"+parameter);
	}

	/**
	 */
	public OutOfUserApplyCountException() {
		super(ResponseCodeEnum.OutOfUserApplyCount);
	}




	public OutOfUserApplyCountException(String parameter, Exception e) {
		super(ResponseCodeEnum.OutOfUserApplyCount, ":"+parameter, e);
	}

	@Override
	public String getMessage() {
		return this.reason;
	}
}
