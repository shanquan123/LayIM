package com.pers.yefei.LayIM.web.controller;

import com.pers.yefei.LayIM.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/rest/mobile")
public class MobileController {

    final static Logger LOGGER = LoggerFactory.getLogger(MobileController.class);

    @Autowired
    private IndexService indexService;


    @RequestMapping("/mobile.html")
    public ModelAndView mobile(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        ModelAndView mv = new ModelAndView("mobile/mobile");
        mv.addObject("hello", "freemarker hello");

        return mv;
    }


}
