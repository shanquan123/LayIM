package com.pers.yefei.LayIM.dao.msg;

import com.pers.yefei.LayIM.pojo.UserMsg;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository("userMsgDao")
public class UserMsgDaoImpl implements IUserMsgDao {

    @Autowired
    private SqlSession sqlSession;


    @Override
    public int insertUserMsg(UserMsg userMsg){
        return sqlSession.insert("com.pers.yefei.LayIM.dao.msg.insertUserMsg", userMsg);
    }

    @Override
    public int updateUserMsg(UserMsg userMsg){
        return sqlSession.update("com.pers.yefei.LayIM.dao.msg.updateUserMsg", userMsg);
    }

    @Override
    public UserMsg getUserMsg(int msgID){
        HashMap params = new HashMap();
        params.put("msgID", msgID);
        return sqlSession.selectOne("com.pers.yefei.LayIM.dao.msg.getUserMsg", params);
    }
}
