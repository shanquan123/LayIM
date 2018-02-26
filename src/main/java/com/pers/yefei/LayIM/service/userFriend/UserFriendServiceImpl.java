package com.pers.yefei.LayIM.service.userFriend;

import com.pers.yefei.LayIM.component.UserFriendApplySerializer;
import com.pers.yefei.LayIM.component.bean.UserAvatarManager;
import com.pers.yefei.LayIM.dao.userFriend.IUserFriendDao;
import com.pers.yefei.LayIM.enumenate.UserFriendStatusEnum;
import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.pojo.UserFriendApplyModel;
import com.pers.yefei.LayIM.pojo.UserGroup;
import com.pers.yefei.LayIM.service.user.IUserService;
import com.pers.yefei.LayIM.utils.DateUtil;
import com.pers.yefei.LayIM.utils.DuplicateUtil;
import com.pers.yefei.LayIM.utils.exception.OutOfUserApplyCountException;
import com.pers.yefei.LayIM.utils.exception.ParameterException;
import com.pers.yefei.LayIM.utils.exception.UserWasFriendException;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Component("userFriendService")
public class UserFriendServiceImpl implements IUserFriendService {

    @Autowired
    private IUserFriendDao userFriendDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserAvatarManager userAvatarManager;

    @Override
    public List<UserGroup> queryUserGroupAndFriends(int userID){
        List<UserGroup> userGroupList = userFriendDao.queryUserGroup(userID);
        if (userGroupList != null && userGroupList.size() > 0){
            for (UserGroup userGroup: userGroupList){
                List<User> friends = queryFriendsByGroupID(userGroup.getGroupID());
                userGroup.setFriends(friends);
            }
        }
        return userGroupList;
    }

    private List<User> queryFriendsByGroupID(int groupID){
        List<User> userList = userFriendDao.queryUserFriendByGroupID(groupID);
        userAvatarManager.rewriteAvatar(userList);
        return userList;
    }

    @Override
    public boolean checkUserFriend(int userId, int friendUserID){
        if(userFriendDao.checkUserFriend(userId, friendUserID) > 0){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public void applyFriend(int userID, int toUserID, int userGroupID, String remark){

        int countUserApplied = userFriendDao.countUserApplied(userID, toUserID, DateUtil.getDateAfterDays(-30));
        if(countUserApplied > 0){
            throw new OutOfUserApplyCountException();
        }

        if (this.checkUserFriend(userID, toUserID) && this.checkUserFriend(toUserID, userID)){
            throw new UserWasFriendException();
        }

        UserFriendApplyModel userFriendApplyModel = new UserFriendApplyModel();
        userFriendApplyModel.setApplyUserID(userID);
        userFriendApplyModel.setToUserID(toUserID);
        userFriendApplyModel.setUserGroupID(userGroupID);
        userFriendApplyModel.setStatus(UserFriendStatusEnum.APPLIED.getStatus());
        userFriendApplyModel.setRemark(remark);

        userFriendDao.insertUserApplyFriend(userFriendApplyModel);

    }

    @Override
    public JSONArray queryUserFriendApply(int userID){
        List<UserFriendApplyModel> applyList = userFriendDao.queryUserFriendApply(userID, UserFriendStatusEnum.APPLIED.getStatus());
        DuplicateUtil.unique(applyList);

        if(applyList.size() == 0){
            return new JSONArray();
        }

        List<User> userList = queryUserList(applyList);

        return UserFriendApplySerializer.format2JSONArray(applyList, userList);
    }

    private List<User> queryUserList(List<UserFriendApplyModel> applyList){
        List<Integer> userIDList = new ArrayList<>();
        for (UserFriendApplyModel um : applyList){
            userIDList.add(um.getApplyUserID());
        }
        return userService.queryUserByUserIDs(userIDList);
    }



    @Transactional
    @Override
    public User agreeFriend(int applyItemID, int toUserID, int toGroupID){

        //校验申请是否有效
        UserFriendApplyModel item = userFriendDao.getFriendApplyByItemID(applyItemID);
        checkApply(item, toUserID);

        //校验要添加的groupID是否有效
        if (userFriendDao.countUserGroup(toUserID, toGroupID) == 0 ){
            throw new ParameterException();
        }


        int fromUserID = item.getApplyUserID();


        //添加好友操作
        if( !this.checkUserFriend(fromUserID, toUserID) ){
            userFriendDao.insertUserFriendItem(item.getUserGroupID(), toUserID);
        }

        //反向添加好友操作
        if( !this.checkUserFriend(toUserID, fromUserID) ){
            userFriendDao.insertUserFriendItem(toGroupID, fromUserID);
        }

        //更新申请状态
        item.setStatus(UserFriendStatusEnum.AGREED.getStatus());
        userFriendDao.updateUserApplyFriend(item);

        return userService.getUserByUserID(item.getApplyUserID());

    }

    @Transactional
    @Override
    public void refuseFriend(int applyItemID, int toUserID){

        //校验申请是否有效
        UserFriendApplyModel item = userFriendDao.getFriendApplyByItemID(applyItemID);
        checkApply(item, toUserID);

        //更新申请状态
        item.setStatus(UserFriendStatusEnum.REFUSED.getStatus());
        userFriendDao.updateUserApplyFriend(item);

    }

    private boolean checkApply(UserFriendApplyModel item, int toUserID){
        if(item == null || toUserID != item.getToUserID() || item.getStatus() != UserFriendStatusEnum.APPLIED.getStatus()){
            throw new ParameterException();
        }
        return true;
    }

}
