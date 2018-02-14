package com.pers.yefei.LayIM.service.msg;

import com.pers.yefei.LayIM.pojo.UserMsg;

import java.util.HashMap;
import java.util.List;

public interface IUserMsgService {
    HashMap<Integer, List<UserMsg>> queryUnreadMsg(int userID);
}
