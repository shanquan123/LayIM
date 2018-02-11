package com.pers.yefei.LayIM.utils.exception;


public enum ResponseCodeEnum {
	
	SuccessCode(1,"Success"),
	FailureCode(2,"系统繁忙，请稍后重试！"),
	ServerApiFailureCode(2,"ServerApiFailureCode"),


	UserNameHasExists(101, "用户名已存在！"),

	ParamsError(201, "请求参数错误！"),

	AuthFialed(230, "用户名或密码不正确！"),
	UserNameUsed(240, "用户名已经被占用！"),

	
	Network404(404, "找不到目标地址"),
	Network500(500, "服务器内部错误"), UnameOrPasswdError(600, "");
	
	 private final int code;
	 private final String reason;
	 
	 private ResponseCodeEnum(int code,String reason) {
	    this.code = code;
	    this.reason= reason;
	 }
	 
	 public int getCode(){
		 return this.code;
	 }
	 public String getReason(){
		 return this.reason;
	 }
}
