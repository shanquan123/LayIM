package com.pers.yefei.LayIM.service.msg;

import com.pers.yefei.LayIM.dao.msg.IUserMsgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("userMsgService")
public class UserMsgServiceImpl implements IUserMsgService{

    @Autowired
    private IUserMsgDao userMsgDao;


}
