package com.pers.yefei.LayIM.dao.userMsg;

import com.pers.yefei.LayIM.pojo.UserMsg;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository("userMsgDao")
public class UserMsgDaoImpl implements IUserMsgDao {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public int insertUserMsg(UserMsg userMsg){
        return sqlSession.insert("com.pers.yefei.LayIM.dao.userMsg.insertUserMsg", userMsg);
    }

    @Override
    public UserMsg getUserMsg(int msgID){
        HashMap params = new HashMap();
        params.put("msgID", msgID);
        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.userMsg.getUserMsg", params);
    }

    @Override
    public List<UserMsg> queryUnreadMsg(int userID){
        return sqlSession.selectList("com.pers.yefei.LayIM.dao.userMsg.queryUnreadMsg", userID);
    }

    @Override
    public int setAllMsgRead(int userID){
        HashMap params = new HashMap();
        params.put("userID", userID);
        params.put("readTime", new Date());
        return sqlSession.update("com.pers.yefei.LayIM.dao.userMsg.setAllMsgRead", params);
    }

    @Override
    public int setMsgRead(UserMsg userMsg){
        return sqlSession.update("com.pers.yefei.LayIM.dao.userMsg.setMsgRead", userMsg);
    }

    @Override
    public int setMsgRead(int userID, String[] userMsgIDList){
        HashMap params = new HashMap();
        params.put("userID", userID);
        params.put("userMsgIDList", userMsgIDList);
        return sqlSession.update("com.pers.yefei.LayIM.dao.userMsg.setMsgReadByMsgIDs", params);
    }
}
