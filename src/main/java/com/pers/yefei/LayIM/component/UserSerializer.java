package com.pers.yefei.LayIM.component;

import com.pers.yefei.LayIM.pojo.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class UserSerializer {

    public static JSONArray userList2JSONArray(List<User> userList){
        JSONArray userJSONArray = new JSONArray();

        for (User user : userList){
            userJSONArray.put(user2JSONObject(user));
        }
        return userJSONArray;
    }

    public static JSONObject user2JSONObject(User user){
        JSONObject userJson = new JSONObject();

        userJson.put("id", user.getUserID());
        userJson.put("username", user.getNickName());
        userJson.put("avatar", user.getAvatar());
        userJson.put("sign", user.getSignature());

        return userJson;
    }

}
