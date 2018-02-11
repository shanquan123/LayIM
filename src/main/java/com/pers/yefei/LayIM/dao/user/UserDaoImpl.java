package com.pers.yefei.LayIM.dao.user;

import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.pojo.UserFriendApplyModel;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public int countUserByUserName(String userName){

        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.user.countUserByUserName", userName);
    }

    @Override
    public int insertUser(User user){
        return sqlSession.insert("com.pers.yefei.LayIM.dao.user.insertUser", user);
    }

    @Override
    public int inserDefaulttUserGroup(int userID){
        return sqlSession.insert("com.pers.yefei.LayIM.dao.user.inserDefaulttUserGroup", userID);
    }

    @Override
    public User queryUserByLoginInfo(String userName, String password){
        HashMap params = new HashMap<>();
        params.put("userName", userName);
        params.put("password", password);

        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.user.queryUserByLoginInfo", params);
    }

    @Override
    public List<User> queryUserByKeywords(String keywords, int pageNo, int pageSize){
        int offset = 0;
        if(pageNo > 1){
            offset = (pageNo - 1) * pageSize;
        }
        HashMap params = new HashMap<>();
        params.put("keywords", keywords);
        params.put("pageSize", pageSize);
        params.put("offset", offset);

        return sqlSession.selectList("com.pers.yefei.LayIM.dao.user.queryUserByKeywords", params);
    }

    @Override
    public int countUserByKeywords(String keywords){
        HashMap params = new HashMap<>();
        params.put("keywords", keywords);

        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.user.countUserByKeywords", keywords);
    }

    @Override
    public int countUserGroupID(int userID, int groupID){
        HashMap params = new HashMap<>();
        params.put("userID", userID);
        params.put("groupID", groupID);

        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.user.countUserByKeywords", params);
    }

    @Override
    public int insertUserApplyFriend(UserFriendApplyModel userFriendApplyModel){
        return sqlSession.insert("com.pers.yefei.LayIM.dao.user.insertUserApplyFriend", userFriendApplyModel);
    }

    @Override
    public int updateUserApplyFriend(UserFriendApplyModel userFriendApplyModel){
        return sqlSession.update("com.pers.yefei.LayIM.dao.user.insertUserApplyFriend", userFriendApplyModel);
    }

    @Override
    public int countUserApplied(int fromUserID, int toUserID, Date limitTime){
        HashMap params = new HashMap<>();
        params.put("fromUserID", fromUserID);
        params.put("toUserID", toUserID);
        params.put("limitTime", limitTime);

        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.user.countUserApplied", params);
    }

    @Override
    public User getUserByUserID(int userID){
        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.user.getUserByUserID", userID);
    }

}
