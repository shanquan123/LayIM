package com.pers.yefei.LayIM.service.userMsg;

import com.pers.yefei.LayIM.dao.userMsg.IUserMsgDao;
import com.pers.yefei.LayIM.enumenate.UserMsgTypeEnum;
import com.pers.yefei.LayIM.pojo.UserMsg;
import com.pers.yefei.LayIM.service.user.IUserService;
import com.pers.yefei.LayIM.service.userFriend.IUserFriendService;
import com.pers.yefei.LayIM.utils.exception.IsNotFriendException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component("userMsgService")
public class UserMsgServiceImpl implements IUserMsgService{

    @Autowired
    private IUserMsgDao userMsgDao;


    @Autowired
    private IUserFriendService userFriendService;

//    @Override
//    public HashMap<Integer, List<UserMsg>> queryUnreadMsg(int userID){
//        List<UserMsg> userMsgList = userMsgDao.queryUnreadMsg(userID);
//        HashMap<Integer, List<UserMsg>> msgMap = new HashMap<>();
//        for (UserMsg userMsg : userMsgList){
//            int fromUserID = userMsg.getFromUserID();
//            List<UserMsg> msgList = null;
//            if(msgMap.containsKey(fromUserID)){
//                msgList = msgMap.get(fromUserID);
//            }else{
//                msgList = new ArrayList<>();
//            }
//            msgList.add(0, userMsg);
//            msgMap.put(fromUserID, msgList);
//        }
//
//        return msgMap;
//    }


    @Override
    public List<UserMsg> queryUnreadMsg(int userID){
        return userMsgDao.queryUnreadMsg(userID);
    }

    @Override
    public void setAllMsgRead(int userID){
        userMsgDao.setAllMsgRead(userID);
    }


    @Override
    public void setMsgRead(int userID, String userMsgIDs){
        String[] userMsgIDList = userMsgIDs.split(",");

        userMsgDao.setMsgRead(userID, userMsgIDList);
    }

    @Override
    public boolean sendUserMsg(int userID, int toUserID, String msgContent){
        if( ! userFriendService.checkUserFriend(userID, toUserID)){
            throw new IsNotFriendException();
        }

        UserMsg userMsg = new UserMsg();
        userMsg.setFromUserID(userID);
        userMsg.setToUserID(toUserID);
        userMsg.setMsgContent(msgContent);
        userMsg.setCreateTime(new Date());
        userMsg.setMsgType(UserMsgTypeEnum.TEXT.getMsgType());
        userMsg.setRead(false);


        userMsgDao.insertUserMsg(userMsg);

        return true;
    }
}
