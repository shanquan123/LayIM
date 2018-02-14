package com.pers.yefei.LayIM.dao.msg;

import com.pers.yefei.LayIM.pojo.UserMsg;

import java.util.List;

public interface IUserMsgDao {


    /**
     *  添加消息 发送消息时使用
     * @param userMsg
     * @return
     */
    int insertUserMsg(UserMsg userMsg);

    /**
     * 根据msgID 查询消息
     * @param msgID
     * @return
     */
    UserMsg getUserMsg(int msgID);

    /**
     * 查询未读消息
     * @param userID
     * @return
     */
    List<UserMsg> queryUnreadMsg(int userID);

    /**
     * 更新所有未读消息状态为已读 登录时查询所有未读消息后使用
     * @param userID
     * @return
     */
    int setAllMsgRead(int userID);

    /**
     * 更新单条消息状态为已读 socket成功推送消息时使用
     * @param userMsg
     * @return
     */
    int setMsgRead(UserMsg userMsg);
}
