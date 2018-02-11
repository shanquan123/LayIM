package com.pers.yefei.LayIM.dao.login;

import com.pers.yefei.LayIM.pojo.UserLogin;

public interface ILoginDao {

    /**
     * 根据accessToken 统计登录信息 创建accessToken时使用
     * @param accessToken
     * @return
     */
    int countUserLoginByAccessToken(String accessToken);

    /**
     * 添加登录信息
     * @param userLogin
     * @return
     */
    int insertUserLogin(UserLogin userLogin);

    /**
     * 根据accessToken 查询登录信息 自动登录时使用
     * @param accessToken
     * @return
     */
    UserLogin queryUserLoginByAccessToken(String accessToken);

    /**
     * 根据accessToken 修改登录信息 自动登录时使用
     * @param userLogin
     * @return
     */
    int updateUserLoginByAccessToken(UserLogin userLogin);

    /**
     *  根据accessToken 把用户登录信息设置为无效  退出登陆时使用
     * @param accessToken
     * @return
     */
    int setLoginInvalidByAccessToken(String accessToken);

    /**
     * 根据userID 把用户登录信息设置为无效  只允许一个用户同时登录时使用
     * @param userID
     * @return
     */
    int setLoginInvalidByUserID(int userID);
}
