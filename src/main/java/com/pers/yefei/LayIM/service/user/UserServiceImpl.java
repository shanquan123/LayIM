package com.pers.yefei.LayIM.service.user;

import com.pers.yefei.LayIM.component.bean.UserAvatarManager;
import com.pers.yefei.LayIM.dao.user.IUserDao;
import com.pers.yefei.LayIM.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userService")
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;

    @Autowired
    private UserAvatarManager userAvatarManager;


    @Override
    public List<User> queryUserByKeywords(String keywords, int pageNo, int pageSize){
        List<User> userList = userDao.queryUserByKeywords(keywords, pageNo, pageSize);
        userAvatarManager.rewriteAvatar(userList);
        return userList;
    }

    @Override
    public int countUserByKeywords(String keywords){
       return userDao.countUserByKeywords(keywords);
    }

    @Override
    public List<User> queryUserByUserIDs(List<Integer> userIDs){
        List<User> userList = userDao.queryUserByUserIDs(userIDs);
        userAvatarManager.rewriteAvatar(userList);
        return userList;
    }

    @Override
    public User getUserByUserID(int userID){
        User user = userDao.getUserByUserID(userID);
        userAvatarManager.rewriteAvatar(user);
        return user;
    }
}
