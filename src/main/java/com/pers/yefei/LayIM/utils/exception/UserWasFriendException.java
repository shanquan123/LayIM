package com.pers.yefei.LayIM.utils.exception;


public class UserWasFriendException extends ServerBaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param parameter:该值是内部输出的值
	 */
	public UserWasFriendException(String parameter) {
		super(ResponseCodeEnum.UserNameUsed, ":"+parameter);
	}

	/**
	 */
	public UserWasFriendException() {
		super(ResponseCodeEnum.UserWasFriend);
	}




	public UserWasFriendException(String parameter, Exception e) {
		super(ResponseCodeEnum.UserWasFriend, ":"+parameter, e);
	}

	@Override
	public String getMessage() {
		return this.reason;
	}
}
