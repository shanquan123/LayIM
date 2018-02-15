package com.pers.yefei.LayIM.utils.exception;


public class IsNotFriendException extends ServerBaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param parameter:该值是内部输出的值
	 */
	public IsNotFriendException(String parameter) {
		super(ResponseCodeEnum.IsNotFriend, ":"+parameter);
	}

	/**
	 */
	public IsNotFriendException() {
		super(ResponseCodeEnum.IsNotFriend);
	}




	public IsNotFriendException(String parameter, Exception e) {
		super(ResponseCodeEnum.IsNotFriend, ":"+parameter, e);
	}

	@Override
	public String getMessage() {
		return this.reason;
	}
}
