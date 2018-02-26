package com.pers.yefei.LayIM.component.bean;

import com.pers.yefei.LayIM.config.LayimSupport;
import com.pers.yefei.LayIM.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class UserAvatarManager {

    @Autowired
    private LayimSupport layimSupport;

    private static final String defaultAvatar = "/static/images/avatar_default.jpeg";

    public void rewriteAvatar(User user){

        if (user != null) {

            String avatar = user.getAvatar();

            if (avatar != null && avatar.trim().equals("")) {
                user.setAvatar(defaultAvatar);

            } else if (avatar.equals(defaultAvatar)) {
                //Do Nothing

            } else if (!avatar.startsWith("http")) {
                String host = layimSupport.getResourceHost();
                user.setAvatar(host + avatar);
            }
        }
    }

    public void rewriteAvatar(List<User> userList){
        if (userList != null && userList.size() > 0) {
            for (User user : userList) {
                rewriteAvatar(user);
            }
        }
    }
}
