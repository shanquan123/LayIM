package com.pers.yefei.LayIM.web.controller;

import com.pers.yefei.LayIM.component.SessionManager;
import com.pers.yefei.LayIM.component.UserGroupSerializer;
import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.pojo.UserGroup;
import com.pers.yefei.LayIM.service.userFriend.IUserFriendService;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/rest/mobile")
public class MobileController {

    final static Logger LOGGER = LoggerFactory.getLogger(MobileController.class);

    @Autowired
    private IUserFriendService userFriendService;

    @Autowired
    SessionManager sessionManager;

    @RequestMapping("/mobile.html")
    public ModelAndView mobile(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        ModelAndView mv = new ModelAndView("mobile/mobile");
        mv.addObject("hello", "freemarker hello");

        User user = sessionManager.getUserFromSession(request);
        List<UserGroup> userGroupList = userFriendService.queryUserGroupAndFriends(user.getUserID());
        JSONArray userGroupJSONArray = UserGroupSerializer.userGroupList2JSONArray(userGroupList);

        mv.addObject("user", user);
        mv.addObject("userFriends", userGroupJSONArray.toString());
        return mv;
    }


}
