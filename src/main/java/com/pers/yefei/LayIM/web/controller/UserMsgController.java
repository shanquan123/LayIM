package com.pers.yefei.LayIM.web.controller;

import com.pers.yefei.LayIM.component.SessionManager;
import com.pers.yefei.LayIM.pojo.User;
import com.pers.yefei.LayIM.pojo.UserMsg;
import com.pers.yefei.LayIM.service.userMsg.IUserMsgService;
import com.pers.yefei.LayIM.utils.ResponseUtil;
import com.pers.yefei.LayIM.utils.exception.ServerBaseException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/rest/msg")
public class UserMsgController {

    final static Logger LOGGER = LoggerFactory.getLogger(UserMsgController.class);

    @Autowired
    private IUserMsgService userMsgService;

    @Autowired
    SessionManager sessionManager;


    @RequestMapping("/unread.ajax")
    public void ajaxGetUnreadMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {

        try{
            User user = sessionManager.getUserFromSession(request);
            List<UserMsg> msgList = userMsgService.queryUnreadMsg(user.getUserID());
            JSONObject msgData = new JSONObject();
            msgData.put("userMsgList", msgList);

            ResponseUtil.writeResponseSuccess(response, msgData);

        } catch (ServerBaseException e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        }
    }

    @RequestMapping("/read.ajax")
    public void ajaxReadMsg(HttpServletRequest request, HttpServletResponse response, String userMsgIDs) throws IOException, InterruptedException {

        try{
            User user = sessionManager.getUserFromSession(request);
            userMsgService.setMsgRead(user.getUserID(), userMsgIDs);

            ResponseUtil.writeResponseSuccess(response);

        } catch (ServerBaseException e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        } catch (Exception e) {
            LOGGER.error(e.toString(), e);
            ResponseUtil.writeResponseFailure(response, e);
        }
    }

    @RequestMapping("/send.ajax")
    public void ajaxSendMsg(HttpServletRequest request, HttpServletResponse response, int toUserID, String msgContent) throws IOException, InterruptedException {

        try{
            User user = sessionManager.getUserFromSession(request);
            userMsgService.sendUserMsg(user.getUserID(), toUserID, msgContent);

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
