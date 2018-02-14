package com.pers.yefei.LayIM.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("/rest/mobile")
public class DemonController {

    final static Logger LOGGER = LoggerFactory.getLogger(DemonController.class);

    @RequestMapping("/hello.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        ModelAndView mv = new ModelAndView("mobile/demo/pc");
        mv.addObject("hello", "freemarker hello");

        return mv;
    }

    @RequestMapping("/mobile_2.0.html")
    public ModelAndView mobile(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException {
        ModelAndView mv = new ModelAndView("mobile/demo/mobile_2.0");
        mv.addObject("hello", "freemarker hello");

        return mv;
    }


}
