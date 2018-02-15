package com.pers.yefei.LayIM.service.userFriend;

import com.pers.yefei.LayIM.pojo.UserGroup;

import java.util.List;

public interface IUserFriendService {
    List<UserGroup> queryUserGroupAndFriends(int userID);

    boolean checkUserFriend(int userId, int friendUserID);
}
