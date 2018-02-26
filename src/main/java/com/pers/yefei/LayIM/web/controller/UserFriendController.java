package com.pers.yefei.LayIM.web.controller;

import com.pers.yefei.LayIM.component.bean.SessionManager;
import com.pers.yefei.LayIM.component.UserSerializer;
import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.service.user.IUserService;
import com.pers.yefei.LayIM.service.userFriend.IUserFriendService;
import com.pers.yefei.LayIM.service.userMsg.IUserMsgService;
import com.pers.yefei.LayIM.utils.ResponseUtil;
import com.pers.yefei.LayIM.utils.exception.ParameterException;
import com.pers.yefei.LayIM.utils.exception.ServerBaseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/rest/user")
public class UserFriendController {

    final static Logger LOGGER = LoggerFactory.getLogger(UserFriendController.class);

    @Autowired
    private IUserMsgService userMsgService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserFriendService userFriendService;

    @Autowired
    SessionManager sessionManager;


    @RequestMapping("/find.ajax")
    public void ajaxFindUser(HttpServletRequest request, HttpServletResponse response, String keywords){

        try{
            if(keywords.equals('%')){
                throw new ParameterException();
            }

            int pageNo = 1;
            int pageSize = 100;
            List<User> userList = userService.queryUserByKeywords(keywords, pageNo, pageSize);

            JSONObject msgData = new JSONObject();
            msgData.put("userList", userList);

            ResponseUtil.writeResponseSuccess(response, msgData);

        } catch (ServerBaseException e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        }
    }

    @RequestMapping("/apply.ajax")
    public void ajaxApply(HttpServletRequest request, HttpServletResponse response, int groupID, int toUserID, String remark){

        try{
            User user = sessionManager.getUserFromSession(request);
            userFriendService.applyFriend(user.getUserID(), toUserID, groupID, remark);

            ResponseUtil.writeResponseSuccess(response);

        } catch (ServerBaseException e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        }
    }

    @RequestMapping("/query_apply.ajax")
    public void ajaxGetAppling(HttpServletRequest request, HttpServletResponse response){

        try{
            User user = sessionManager.getUserFromSession(request);
            JSONArray list = userFriendService.queryUserFriendApply(user.getUserID());

            JSONObject msgData = new JSONObject();
            msgData.put("applyList", list);
            ResponseUtil.writeResponseSuccess(response, msgData);

        } catch (ServerBaseException e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        }
    }

    @RequestMapping("/agree.ajax")
    public void ajaxAgree(HttpServletRequest request, HttpServletResponse response, int applyItemID, int toGroupID){

        try{
            User user = sessionManager.getUserFromSession(request);
            User applyUser = userFriendService.agreeFriend(applyItemID, user.getUserID(), toGroupID);

            JSONObject msgData = new JSONObject();
            JSONObject userJson = UserSerializer.user2JSONObject(applyUser);
            msgData.put("user", userJson);
            ResponseUtil.writeResponseSuccess(response, msgData);

        } catch (ServerBaseException e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        }
    }

    @RequestMapping("/refuse.ajax")
    public void ajaxReject(HttpServletRequest request, HttpServletResponse response, int applyItemID) {

        try{
            User user = sessionManager.getUserFromSession(request);
            userFriendService.refuseFriend(applyItemID, user.getUserID());

            ResponseUtil.writeResponseSuccess(response);

        } catch (ServerBaseException e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        }
    }



}
