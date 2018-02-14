package com.pers.yefei.LayIM.utils.exception;


import com.pers.yefei.LayIM.utils.ResponseCodeUtil;

public abstract class ServerBaseException extends RuntimeException {

	protected int code;
	//just for internal trace
	protected String reason;

	public ServerBaseException(){
	}

	public ServerBaseException(String reason){
		this.reason = reason;
	}
	
	public ServerBaseException (String reason, Exception ex){
		super(ex);
		this.reason = reason;
	}

	public ServerBaseException(ResponseCodeEnum resCode){
		this.code = resCode.getCode();
		this.reason = resCode.getReason();
	}
	
	public ServerBaseException(ResponseCodeEnum resCode,String verboseMsg){
		this.code = resCode.getCode();
		this.reason = resCode.getReason() + verboseMsg;
	}
	
	public ServerBaseException (ResponseCodeEnum resCode,String verboseMsg, Exception ex){
		super(ex);
		this.code = resCode.getCode();
		this.reason = resCode.getReason() + verboseMsg;
	}
	
	public int getCode() {
		return code;
	}

	public String getMessage(){
		return ResponseCodeUtil.getReason(code);
	}

	public String getReason(){
		return this.reason;
	}
	
	@Override
	public String toString(){
		return String.format("ServerBaseException - %s [code = %s, message = %s, reason = %s]", this.getClass().getName(),  this.code, this.getMessage(), this.getReason());

	}
}
