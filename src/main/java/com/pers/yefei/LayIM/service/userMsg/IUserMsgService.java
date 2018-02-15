package com.pers.yefei.LayIM.service.userMsg;

import com.pers.yefei.LayIM.pojo.UserMsg;

import java.util.List;

public interface IUserMsgService {

    List<UserMsg> queryUnreadMsg(int userID);

    void setAllMsgRead(int userID);

    void setMsgRead(int userID, String userMsgIDs);

    boolean sendUserMsg(int userID, int toUserID, String msgContent);
}
