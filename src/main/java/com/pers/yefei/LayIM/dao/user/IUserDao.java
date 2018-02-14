package com.pers.yefei.LayIM.dao.user;

import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.pojo.UserFriendApplyModel;

import java.util.Date;
import java.util.List;

public interface IUserDao {

    /**
     * 根据用户名统计用户数量  注册校验时使用
     * @param userName
     * @return
     */
    int countUserByUserName(String userName);

    /**
     * 添加用户 注册使用
     * @param user
     * @return
     */

    int insertUser(User user);

    /**
     * 添加默认用户组 注册时使用
     * @param userID
     * @return
     */
    int inserDefaulttUserGroup(int userID);


    /**
     * 查询用户信息  登录时使用
     * @param userName
     * @param password
     * @return
     */
    User queryUserByLoginInfo(String userName, String password);


    /**
     * 查找用户  添加好友时使用
     * @param keywords
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<User> queryUserByKeywords(String keywords, int pageNo, int pageSize);

    /**
     * 统计用户  添加好友时使用
     * @param keywords
     * @return
     */
    int countUserByKeywords(String keywords);

    User getUserByUserID(int userID);
}
