package com.pers.yefei.LayIM.dao.msg;

import com.pers.yefei.LayIM.pojo.UserMsg;

public interface IUserMsgDao {


    /**
     *  添加消息 发送消息时使用
     * @param userMsg
     * @return
     */
    int insertUserMsg(UserMsg userMsg);

    /**
     * 更新消息状态 成功推送消息时使用
     * @param userMsg
     * @return
     */
    int updateUserMsg(UserMsg userMsg);

    /**
     * 根据msgID 查询消息
     * @param msgID
     * @return
     */
    UserMsg getUserMsg(int msgID);
}
