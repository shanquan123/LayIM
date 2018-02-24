package com.pers.yefei.LayIM.service.user;

import com.pers.yefei.LayIM.dao.user.IUserDao;
import com.pers.yefei.LayIM.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userService")
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;


    @Override
    public List<User> queryUserByKeywords(String keywords, int pageNo, int pageSize){
        return userDao.queryUserByKeywords(keywords, pageNo, pageSize);
    }

    @Override
    public int countUserByKeywords(String keywords){
       return userDao.countUserByKeywords(keywords);
    }

    @Override
    public List<User> queryUserByUserIDs(List<Integer> userIDs){
        return userDao.queryUserByUserIDs(userIDs);
    }

    @Override
    public User getUserByUserID(int userID){
        return userDao.getUserByUserID(userID);
    }
}
