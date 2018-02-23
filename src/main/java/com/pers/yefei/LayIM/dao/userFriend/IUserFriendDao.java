package com.pers.yefei.LayIM.dao.userFriend;

import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.pojo.UserFriendApplyModel;
import com.pers.yefei.LayIM.pojo.UserGroup;

import java.util.Date;
import java.util.List;

public interface IUserFriendDao {

    /**
     * 查询用户的所有分组
     * @param userID
     * @return
     */
    List<UserGroup> queryUserGroup(int userID);

    /**
     * 根据groupID获取group信息
     * @param groupID
     * @return
     */
    UserGroup getUserGroupByGroupID(int groupID);

    /**
     * 更新分组
     * @param userGroup
     * @return
     */
    int updateUserGroup(UserGroup userGroup);

    /**
     * 新建分组
     * @param userGroup
     * @return
     */
    int insertUserGroup(UserGroup userGroup);

    /**
     * 查询用户分组下的所有好友
     * @param groupID
     * @return
     */
    List<User> queryUserFriendByGroupID(int groupID);

    /**
     * 查询用户之间是否是好友
     * @param userId
     * @param friendUserID
     * @return
     */
    int checkUserFriend(int userID, int friendUserID);


    /**
     * 查询用户分组是否存在 添加好友时使用
     * @param userID
     * @param groupID
     * @return
     */
    int countUserGroup(int userID, int groupID);


    /**
     * 添加好友申请
     * @param userFriendApplyModel
     * @return
     */
    int insertUserApplyFriend(UserFriendApplyModel userFriendApplyModel);

    /**
     * 处理好友申请
     * @param userFriendApplyModel
     * @return
     */
    int updateUserApplyFriend(UserFriendApplyModel userFriendApplyModel);


    /**
     * 统计最近一段时间内 对某已用户发起的添加好友申请次数
     * @param applyUserID
     * @param toUserID
     * @param limitTime
     * @return
     */
    int countUserApplied(int applyUserID, int toUserID, Date limitTime);

    UserFriendApplyModel getFriendApplyByItemID(int itemID);

    int insertUserFriendItem(int groupID, int userID);
}
