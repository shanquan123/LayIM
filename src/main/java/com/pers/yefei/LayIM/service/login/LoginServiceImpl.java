package com.pers.yefei.LayIM.service.login;

import com.pers.yefei.LayIM.component.SessionManager;
import com.pers.yefei.LayIM.config.LayimSupport;
import com.pers.yefei.LayIM.dao.user.IUserDao;
import com.pers.yefei.LayIM.dao.login.ILoginDao;
import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.pojo.UserLogin;
import com.pers.yefei.LayIM.utils.exception.AuthFialedException;
import com.pers.yefei.LayIM.utils.exception.UserNameUsedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;

@Component("loginService")
public class LoginServiceImpl implements ILoginService{

    @Autowired
    private ILoginDao loginDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private LayimSupport layimSupport;

    @Autowired
    private SessionManager sessionManager;


    @Override
    public boolean userNameExists(String userName){
        if(userDao.countUserByUserName(userName) == 0){
            return false;
        }else{
            return true;
        }
    }

    @Transactional
    @Override
    public boolean userRegister(User user){
        /**
         * 校验用户名
         */
        if(this.userNameExists(user.getUserName())){
            throw new UserNameUsedException();
        }

        /**
         * 添加用户
         */
        userDao.insertUser(user);

        /**
         * 添加默认分组
         */
        userDao.inserDefaulttUserGroup(user.getUserID());

        return true;
    }



    @Override
    public HashMap userLogin(String userName, String password){

        /**
         * 校验用户
         */
        User user = userDao.queryUserByLoginInfo(userName, password);

        if(user == null){
            throw new AuthFialedException();
        }

        HashMap result = new HashMap();
        result.put("code", 1);

        if ( "1".equals( layimSupport.getValue("only_allow_one_client_login") )){
            //只允许一台设备同时登录
            int num = loginDao.setLoginInvalidByUserID(user.getUserID());
            if ( num > 0 ){
                result.put("code", 2);
                result.put("userMsg", String.format("踢出之前登陆的用户%d个", num));
            }

        }


        /**
         * 添加登录信息
         */

        String accessToken = sessionManager.genAccessToken();
        Date expiredTime = sessionManager.genAccessTokenExpiredTime();

        UserLogin userLogin = new UserLogin();
        userLogin.setAccessToken(accessToken);
        userLogin.setCreateTime(new Date());
        userLogin.setExpiredTime(expiredTime);
        userLogin.setUpdateTime(new Date());
        userLogin.setUserID(user.getUserID());
        userLogin.setValid(true);

        loginDao.insertUserLogin(userLogin);

        result.put("accessToken", accessToken);

        return result;
    }


    @Override
    public boolean accessTokenExists(String accessToken){

        if (loginDao.countUserLoginByAccessToken(accessToken) == 0){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public User getUserByAccessToken(String accessToken){
        UserLogin userLogin  = loginDao.queryUserLoginByAccessToken(accessToken);
        if (userLogin == null){
            return null;
        }

        User user = userDao.getUserByUserID(userLogin.getUserID());

        if(user == null || user.isDeleted()){
            return null;
        }

        String avatar = user.getAvatar();
        if(avatar != null && !avatar.trim().equals("") && !avatar.startsWith("http")){
            String host = layimSupport.getResourceHost();
            user.setAvatar(host + avatar);
        }

        return user;
    }

    @Override
    public boolean userLogout(String accessToken){
        loginDao.setLoginInvalidByAccessToken(accessToken);
        return true;
    }

}
