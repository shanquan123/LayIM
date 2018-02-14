package com.pers.yefei.LayIM.service.msg;

import com.pers.yefei.LayIM.dao.msg.IUserMsgDao;
import com.pers.yefei.LayIM.pojo.UserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component("userMsgService")
public class UserMsgServiceImpl implements IUserMsgService{

    @Autowired
    private IUserMsgDao userMsgDao;


    @Override
    public HashMap<Integer, List<UserMsg>> queryUnreadMsg(int userID){
        List<UserMsg> userMsgList =  userMsgDao.queryUnreadMsg(userID);
        HashMap<Integer, List<UserMsg>> msgMap = new HashMap<>();
        for (UserMsg userMsg : userMsgList){
            int fromUserID = userMsg.getFromUserID();
            List<UserMsg> msgList = null;
            if(msgMap.containsKey(fromUserID)){
                msgList = msgMap.get(fromUserID);
            }else{
                msgList = new ArrayList<>();
            }
            msgList.add(0, userMsg);
            msgMap.put(fromUserID, msgList);
        }

        return msgMap;
    }

}
