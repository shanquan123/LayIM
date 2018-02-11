package com.pers.yefei.LayIM.component;



import com.pers.yefei.LayIM.config.LayimSupport;
import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.service.login.ILoginService;
import com.pers.yefei.LayIM.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

public class SessionManager {

    private static final String ACCESS_TOKEN_EXPIRED_CONFIG_KEY = "access_token_expired_date";

    private static final String LAYIM_SESSION_ID = "layim_session_id";

    @Autowired
    private static ILoginService loginService;

    @Autowired
    private static LayimSupport layimSupport;


    public static String genAccessToken(){

        String accessToken = UUID.randomUUID().toString();

        if( ! loginService.accessTokenExists(accessToken) ){
            return accessToken;
        }else{
            return genAccessToken();
        }

    }


    public static Date genAccessTokenExpiredTime(){

        int expiredDay = Integer.valueOf(layimSupport.getValue(ACCESS_TOKEN_EXPIRED_CONFIG_KEY));

        return DateUtil.getDateAfterDays(expiredDay);

    }





    public static User getUserFromSession(HttpServletRequest request) {
        String accessToken = getAccessTokenFromAttr(request);

        return getUserFromSession(accessToken);
    }

    private static User getUserFromSession(String accessToken) {

		if(accessToken == null || accessToken.isEmpty()){
			return null;
		}

		return loginService.getUserByAccessToken(accessToken);
    }


    private static String getAccessTokenFromAttr(HttpServletRequest request) {
        return  (String)request.getAttribute( LAYIM_SESSION_ID );
    }



    public static void saveAccessToken2Cookie(String accessToken, HttpServletResponse response) {
        Cookie cookie = new Cookie(LAYIM_SESSION_ID, accessToken);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static String getAccessTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals( LAYIM_SESSION_ID )) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


}
