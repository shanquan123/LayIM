package com.pers.yefei.LayIM.service.login;

import com.pers.yefei.LayIM.pojo.User;

import java.util.HashMap;

public interface ILoginService {

    /**
     * 查询用户名是否已经存在
     * @param userName
     * @return
     */

    boolean userNameExists(String userName);

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean userRegister(User user);


    /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    HashMap userLogin(String userName, String password);


    /**
     * 查询access是否存在
     * @param accessToken
     * @return
     */
    boolean accessTokenExists(String accessToken);


    /**
     * 根据accessToken查询用户信息
     * @param accessToken
     * @return
     */
    User getUserByAccessToken(String accessToken);

    /**
     * 用户退出登陆
     * @param accessToken
     * @return
     */

    boolean userLogout(String accessToken);
}
