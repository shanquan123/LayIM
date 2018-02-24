package com.pers.yefei.LayIM.component;

import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.pojo.UserFriendApplyModel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;

public class UserFriendApplySerializer {

    public static JSONArray format2JSONArray(List<UserFriendApplyModel> applyList, List<User> userList){
        HashMap<Integer, User> userMap = userList2Map(userList);
        JSONArray applyJSONArray = new JSONArray();

        for (UserFriendApplyModel apply : applyList){
            applyJSONArray.put(userApply2JSONObject(apply, userMap));
        }
        return applyJSONArray;
    }

    public static JSONObject userApply2JSONObject(UserFriendApplyModel apply, HashMap<Integer, User> userMap ){
        User user = userMap.get(apply.getApplyUserID());
        String remark = apply.getRemark();
        remark = StringUtils.isEmpty(remark) ? "请求添加好友" : remark;
        JSONObject applyJson = new JSONObject();

        applyJson.put("userID", user.getUserID());
        applyJson.put("nickName", user.getNickName());
        applyJson.put("avatar", user.getAvatar());
        applyJson.put("remark", remark);
        applyJson.put("applyItemID", apply.getItemID());

        return applyJson;
    }


    private static HashMap<Integer, User> userList2Map(List<User> userList){
        HashMap<Integer, User> userMap = new HashMap<>();
        for (User user: userList){
            userMap.put(user.getUserID(), user);
        }

        return userMap;
    }
}
