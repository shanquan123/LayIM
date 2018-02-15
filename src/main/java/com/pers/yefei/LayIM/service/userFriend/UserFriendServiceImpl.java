package com.pers.yefei.LayIM.service.userFriend;

import com.pers.yefei.LayIM.dao.userFriend.IUserFriendDao;
import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.pojo.UserGroup;
import com.pers.yefei.LayIM.pojo.UserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;


@Component("userFriendService")
public class UserFriendServiceImpl implements IUserFriendService {

    @Autowired
    private IUserFriendDao userFriendDao;

    @Override
    public List<UserGroup> queryUserGroupAndFriends(int userID){
        List<UserGroup> userGroupList = userFriendDao.queryUserGroup(userID);
        if (userGroupList != null && userGroupList.size() > 0){
            for (UserGroup userGroup: userGroupList){
                List<User> friends = userFriendDao.queryUserFriendByGroupID(userGroup.getGroupID());
                userGroup.setFriends(friends);
            }
        }
        return userGroupList;
    }


    @Override
    public boolean checkUserFriend(int userId, int friendUserID){
        if(userFriendDao.checkUserFriend(userId, friendUserID) > 0){
            return true;
        }else{
            return false;
        }
    }

}
