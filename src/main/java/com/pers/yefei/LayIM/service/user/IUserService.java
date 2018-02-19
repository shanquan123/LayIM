package com.pers.yefei.LayIM.service.user;

import com.pers.yefei.LayIM.pojo.User;

import java.util.List;

public interface IUserService {

    List<User> queryUserByKeywords(String keywords, int pageNo, int pageSize);

    int countUserByKeywords(String keywords);
}
