package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloController {

    @RequestMapping(value = "hello")
    public ModelAndView text(){
        ModelAndView mv= new ModelAndView();
        mv.addObject("hello");
        mv.setViewName("hello");
        return mv;
    }
}
