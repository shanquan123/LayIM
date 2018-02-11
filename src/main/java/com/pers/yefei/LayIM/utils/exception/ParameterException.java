package com.pers.yefei.LayIM.utils.exception;


public class ParameterException extends ServerBaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param parameter:该值是内部输出的值
	 */
	public ParameterException(String parameter) {
		super(ResponseCodeEnum.ParamsError, ":"+parameter);
	}

	/**
	 */
	public ParameterException() {
		super(ResponseCodeEnum.ParamsError);
	}


	public ParameterException(String parameter, Exception e) {
		super(ResponseCodeEnum.ParamsError, ":"+parameter, e);
	}

	@Override
	public String getMessage() {
		return this.reason;
	}
}
