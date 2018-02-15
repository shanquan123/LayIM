package com.pers.yefei.LayIM.component;

import com.pers.yefei.LayIM.pojo.UserGroup;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class UserGroupSerializer {

    public static JSONArray userGroupList2JSONArray(List<UserGroup> userGroupList){
        JSONArray userGroupJSONArray = new JSONArray();
        for (UserGroup userGroup : userGroupList){
            userGroupJSONArray.put(userGroup2JSONObject(userGroup));
        }
        return userGroupJSONArray;
    }

    public static JSONObject userGroup2JSONObject(UserGroup userGroup){
        JSONObject userGroupJson = new JSONObject();
        userGroupJson.put("id", userGroup.getGroupID());
        userGroupJson.put("groupname", userGroup.getGroupName());
        userGroupJson.put("list", UserSerializer.userList2JSONArray(userGroup.getFriends()));

        return userGroupJson;
    }
}
