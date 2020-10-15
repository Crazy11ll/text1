package org.example.controller;

import org.example.entity.vo.MessageModel;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserControl {
    @Resource
    private UserService userService;
    public MessageModel UserLogin(String name, String pwd){
        MessageModel messageModel = new MessageModel();
        messageModel = userService.CheckUserLogin(name,pwd);
        return messageModel;
    }

}
