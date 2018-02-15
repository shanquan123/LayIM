package com.pers.yefei.LayIM.dao.userFriend;

import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.pojo.UserFriendApplyModel;
import com.pers.yefei.LayIM.pojo.UserGroup;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository("userFriendDao")
public class UserFriendDaoImpl implements IUserFriendDao {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public List<UserGroup> queryUserGroup(int userID){

        return sqlSession.selectList("com.pers.yefei.LayIM.dao.userFriend.queryUserGroup", userID);
    }

    @Override
    public UserGroup getUserGroupByGroupID(int groupID){

        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.userFriend.getUserGroupByGroupID", groupID);
    }

    @Override
    public int updateUserGroup(UserGroup userGroup){
        return sqlSession.update("com.pers.yefei.LayIM.dao.userFriend.updateUserGroup", userGroup);
    }

    @Override
    public int insertUserGroup(UserGroup userGroup){
        return sqlSession.insert("com.pers.yefei.LayIM.dao.userFriend.insertUserGroup", userGroup);
    }


    @Override
    public List<User> queryUserFriendByGroupID(int groupID){

        return sqlSession.selectList("com.pers.yefei.LayIM.dao.userFriend.queryUserFriendByGroupID", groupID);
    }

    @Override
    public int checkUserFriend(int userID, int friendUserID){
        HashMap params = new HashMap<>();
        params.put("userID", userID);
        params.put("friendUserID", friendUserID);

        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.userFriend.checkUserFriend", params);
    }


    @Override
    public int countUserGroup(int userID, int groupID){
        HashMap params = new HashMap<>();
        params.put("userID", userID);
        params.put("groupID", groupID);

        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.userFriend.countUserGroup", params);
    }

    @Override
    public int insertUserApplyFriend(UserFriendApplyModel userFriendApplyModel){
        return sqlSession.insert("com.pers.yefei.LayIM.dao.userFriend.insertUserApplyFriend", userFriendApplyModel);
    }

    @Override
    public int updateUserApplyFriend(UserFriendApplyModel userFriendApplyModel){
        return sqlSession.update("com.pers.yefei.LayIM.dao.userFriend.updateUserApplyFriend", userFriendApplyModel);
    }

    @Override
    public int countUserApplied(int fromUserID, int toUserID, Date limitTime){
        HashMap params = new HashMap<>();
        params.put("fromUserID", fromUserID);
        params.put("toUserID", toUserID);
        params.put("limitTime", limitTime);

        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.userFriend.countUserApplied", params);
    }

}
