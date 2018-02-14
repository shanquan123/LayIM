package com.pers.yefei.LayIM.dao.login;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.pers.yefei.LayIM.pojo.UserLogin;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


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
        System.out.println(sqlSession);
        List<Object> list = sqlSession.selectList("com.pers.yefei.LayIM.dao.login.countUserLoginByAccessToken", accessToken);
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
        userLogin.setUpdateTime(new Date());
        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.login.updateUserLoginByAccessToken", userLogin);
    }

    @Override
    public int setLoginInvalidByAccessToken(String accessToken){
        HashMap params = new HashMap();
        params.put("accessToken", accessToken);
        params.put("updateTime", new Date());
        return sqlSession.update("com.pers.yefei.LayIM.dao.login.setLoginInvalidByAccessToken", params);
    }

    @Override
    public int setLoginInvalidByUserID(int userID){
        HashMap params = new HashMap();
        params.put("userID", userID);
        params.put("updateTime", new Date());
        return sqlSession.update("com.pers.yefei.LayIM.dao.login.setLoginInvalidByUserID", params);
    }


}
