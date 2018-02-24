package com.pers.yefei.LayIM.service.userFriend;

import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.pojo.UserGroup;
import org.json.JSONArray;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserFriendService {
    List<UserGroup> queryUserGroupAndFriends(int userID);

    boolean checkUserFriend(int userId, int friendUserID);

    void applyFriend(int userID, int toUserID, int userGroupID, String remark);

    JSONArray queryUserFriendApply(int userID);

    @Transactional
    User agreeFriend(int applyItemID, int toUserID, int toGroupID);

    @Transactional
    void refuseFriend(int applyItemID, int toUserID);
}
