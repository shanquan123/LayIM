package com.pers.yefei.LayIM.web.controller;

import com.pers.yefei.LayIM.component.SessionManager;
import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.service.login.ILoginService;
import com.pers.yefei.LayIM.utils.ParameterUtil;
import com.pers.yefei.LayIM.utils.ResponseUtil;
import com.pers.yefei.LayIM.utils.exception.ParameterException;
import com.pers.yefei.LayIM.utils.exception.ServerBaseException;
import com.pers.yefei.LayIM.utils.exception.UserNameUsedException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;


@Controller
@RequestMapping("/rest/login")
public class LoginController {

    final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ILoginService loginService;

    @Autowired
    private SessionManager sessionManager;


    @RequestMapping("/login.html")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        ModelAndView mv = new ModelAndView("mobile/login");

        return mv;
    }


    @RequestMapping("/register.html")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        ModelAndView mv = new ModelAndView("mobile/register");

        return mv;
    }




    @RequestMapping("/register.ajax")
    public void ajaxUserRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        try {
            String userName = ParameterUtil.getRequiredString(request, "userName");
            String nickName = ParameterUtil.getRequiredString(request, "nickName");
            String password = ParameterUtil.getRequiredString(request, "password");

            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(nickName) || StringUtils.isEmpty(password)){
               throw new ParameterException();
            }


            User user = new User();
            user.setUserName(userName);
            user.setNickName(nickName);
            user.setPassword(password);

            user.setAvatar("");
            user.setSignature("");
            user.setRegisterTime(new Date());
            user.setDeleted(false);

            loginService.userRegister(user);

            HashMap result = loginService.userLogin(user.getUserName(), user.getPassword());

            String accessToken = (String)result.get("accessToken");

            sessionManager.saveAccessToken2Cookie(accessToken, response);

            ResponseUtil.writeResponseSuccess(response);

        } catch (ServerBaseException e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        }

    }


    @RequestMapping("/login.ajax")
    public void ajaxSignIn(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        try {

            String userName = ParameterUtil.getRequiredString(request, "userName");
            String password = ParameterUtil.getRequiredString(request, "password");

            if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
                throw new ParameterException();
            }

            HashMap result = loginService.userLogin(userName, password);

            String accessToken = (String)result.get("accessToken");
            sessionManager.saveAccessToken2Cookie(accessToken, response);


            if (!result.get("code").equals("1")){
                JSONObject msgData = new JSONObject();
                msgData.put("userMsg", result.get("userMsg"));
                ResponseUtil.writeResponseSuccess(response);

            }else {
                ResponseUtil.writeResponseSuccess(response);
            }

        } catch (ServerBaseException e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        }

    }

    @RequestMapping("/logout.ajax")
    public void ajaxSignOut(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {

        try{
            String accessToken = sessionManager.getAccessTokenFromCookie(request);
            loginService.userLogout(accessToken);
            ResponseUtil.writeResponseSuccess(response);

        } catch (ServerBaseException e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        }

    }

    @RequestMapping("/check_username.ajax")
    public void ajaxCheckUsername(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {

        try{
            String userName = ParameterUtil.getRequiredString(request, "userName");
            if ( loginService.userNameExists(userName) ) {
                throw  new UserNameUsedException();
            }

            ResponseUtil.writeResponseSuccess(response);


        } catch (ServerBaseException e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        }

    }



}
