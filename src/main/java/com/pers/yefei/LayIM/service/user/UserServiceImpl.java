package com.pers.yefei.LayIM.service.user;

import com.pers.yefei.LayIM.dao.user.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userService")
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao userDao;

}
