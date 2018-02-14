package com.pers.yefei.LayIM.component;



import com.pers.yefei.LayIM.config.LayimSupport;
import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.service.login.ILoginService;
import com.pers.yefei.LayIM.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

public class SessionManager {

    private final String ACCESS_TOKEN_EXPIRED_CONFIG_KEY = "access_token_expired_date";

    private final String LAYIM_SESSION_ID = "layim_session_id";

    @Autowired
    private ILoginService loginService;

    @Autowired
    private LayimSupport layimSupport;


    public String genAccessToken(){

        String accessToken = UUID.randomUUID().toString();

        if( ! loginService.accessTokenExists(accessToken) ){
            return accessToken;
        }else{
            return genAccessToken();
        }

    }


    public Date genAccessTokenExpiredTime(){

        int expiredDay = Integer.valueOf(layimSupport.getValue(ACCESS_TOKEN_EXPIRED_CONFIG_KEY));

        return DateUtil.getDateAfterDays(expiredDay);

    }





    public User getUserFromSession(HttpServletRequest request) {
        String accessToken = getAccessTokenFromCookie(request);

        return getUserFromSession(accessToken);
    }

    private User getUserFromSession(String accessToken) {

		if(accessToken == null || accessToken.isEmpty()){
			return null;
		}

		return loginService.getUserByAccessToken(accessToken);
    }


    private String getAccessTokenFromAttr(HttpServletRequest request) {
        return  (String)request.getAttribute( LAYIM_SESSION_ID );
    }



    public void saveAccessToken2Cookie(String accessToken, HttpServletResponse response) {
        Cookie cookie = new Cookie(LAYIM_SESSION_ID, accessToken);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public String getAccessTokenFromCookie(HttpServletRequest request) {
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
