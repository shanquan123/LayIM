package com.pers.yefei.LayIM.utils;

import java.util.HashMap;
import java.util.Map;

public class ResponseCodeUtil {
	public static final int ResponseCodeSuccess = 1;
	public static final int ResponseCodeFailure = 2;
	public static final int ResponseCodeNoData = 3;
	public static final int ResponseCodeAccessTokenTimeout = 4;
	public static final int ResponseCodeAccessTokenKicked = 5;
	
	public static final int ErrorCodeNoUserAuthInfo = 115;
	public static final int ErrorCodeInvalidAuthInfo = 116;
	public static final int ErrorCodeNoPrivilege = 117;
	public static final int ErrorCodeInvalidParameter = 120;
	
	public static final int ErrorCodeIllegalCharacter = 155;
	public static final int ErrorCodeCharacterLengthOfMoreThan = 156;
	public static final int ErrorCodeUserNotExists=158;
	public static final int ErrorCodeAccountType=159;
	
	public static final int ErrorCodeMD5ErrorRequestContent = 190;
	
	public static final int ErrorCodeNoBuyService = 323;
	//微信使用 400-500
	public static final int ErrorCodeMobileHasBeenUsed = 400;
	public static final int ErrorCodeScannerInfoExpiration = 401;
	public static final int ErrorCodeCheckCodeError = 402;
	public static final int THIS_USER_INFO_HAS_VALID = 403;
	public static final int ErrorCodeWxOpenIdHasBeenUse = 405;

	
	// 排班  技术  501-510
	public static final int ErrorCodeNoPeriod = 501;

	//pc  601-700
	public static final int NoLoginPerimissionCode = 601;
	public static final int ErrorCodeHasExistsDepartment = 602;
	
	private static final String ResponseReasonSuccess = "Success";
	private static final String ResponseReasonFailure = "Server failed to process client's request";
	private static final String ResponseReasonNoData = "Server failed to retrieve data for client's request";
	private static final String ErrorReasonCharacterLengthOfMoreThan="Character Length Of More Than";
	private static final String ErrorReasonIllegalCharacter="Illegal Character";
	private static final String ResponseReasonAccessTokenKicked = "user access token has been Kicked.";
	private static final String ResponseReasonAccessTokenTimeout = "invalid access token.";
	private static final String ErrorReasonInvalidAuthInfo = "user auth info is  invalid";
	private static final String ErrorReasonUserNotExists = "用户不存在";
	public static  final String ErrorReasonAccountType = "账号类型错误";
	private static final String ErrorReasonMD5Error="MD5加密失败";
	private static final String ErrorReasonInvalidParameter = "非法的参数";
	private static final String ErrorReasonNoPrivilege = "非法用户";
	private static final String  ErrorCodeNoBuyServiceInfo = "未购买服务";
	
	private static Map<Integer,String> codeReasonMap;
	
	static  {
		
		codeReasonMap = new HashMap<Integer,String>();
		//General
		codeReasonMap.put(ResponseCodeSuccess, ResponseReasonSuccess);
		codeReasonMap.put(ResponseCodeFailure, ResponseReasonFailure);
		codeReasonMap.put(ResponseCodeNoData, ResponseReasonNoData);
		codeReasonMap.put(ErrorCodeUserNotExists, ErrorReasonUserNotExists);
		codeReasonMap.put(ErrorCodeAccountType, ErrorReasonAccountType);
		codeReasonMap.put(ResponseCodeAccessTokenTimeout, ResponseReasonAccessTokenTimeout);
		codeReasonMap.put(ErrorCodeInvalidParameter, ErrorReasonInvalidParameter);
		codeReasonMap.put(ErrorCodeCharacterLengthOfMoreThan, ErrorReasonCharacterLengthOfMoreThan);
		codeReasonMap.put(ErrorCodeIllegalCharacter, ErrorReasonIllegalCharacter);
		codeReasonMap.put(ErrorCodeMD5ErrorRequestContent, ErrorReasonMD5Error);
		codeReasonMap.put(ErrorCodeNoPrivilege, ErrorReasonNoPrivilege);
		codeReasonMap.put(ResponseCodeAccessTokenKicked, ResponseReasonAccessTokenKicked);
		codeReasonMap.put(ErrorCodeInvalidAuthInfo, ErrorReasonInvalidAuthInfo);
		codeReasonMap.put(ErrorCodeNoBuyService, ErrorCodeNoBuyServiceInfo);
	}
	public static String getReason(int code){
		if (codeReasonMap.containsKey(code))
			return codeReasonMap.get(code);
		else
			return "Unknow code " + code;
	}
}
