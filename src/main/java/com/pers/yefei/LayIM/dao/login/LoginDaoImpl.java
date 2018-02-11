package com.pers.yefei.LayIM.dao.login;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.pers.yefei.LayIM.pojo.UserLogin;
import org.springframework.stereotype.Repository;


@Repository("loginDao")
public class LoginDaoImpl implements ILoginDao{

    @Autowired
    private SqlSession sqlSession;



    @Override
    /**
     * 根据accessToken 统计登录信息 创建accessToken时使用
     * @param accessToken
     * @return
     */
    public int countUserLoginByAccessToken(String accessToken){

        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.login.countUserLoginByAccessToken", accessToken);
    }

    @Override
    public int insertUserLogin(UserLogin userLogin){

        return sqlSession.insert("com.pers.yefei.LayIM.dao.login.insertUserLogin", userLogin);
    }

    @Override
    public UserLogin queryUserLoginByAccessToken(String accessToken){
        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.login.queryUserLoginByAccessToken", accessToken);
    }

    @Override
    public int updateUserLoginByAccessToken(UserLogin userLogin){
        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.login.updateUserLoginByAccessToken", userLogin);
    }

    @Override
    public int setLoginInvalidByAccessToken(String accessToken){
        return sqlSession.update("setLoginInvalidByAccessToken", accessToken);
    }

    @Override
    public int setLoginInvalidByUserID(int userID){
        return sqlSession.update("setLoginInvalidByUserID", userID);
    }


}
