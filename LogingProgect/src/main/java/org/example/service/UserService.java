package org.example.service;

import org.example.dao.UserDao;
import org.example.entity.User;
import org.example.entity.vo.MessageModel;
import org.example.tool.StringTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserDao userDao;

    public MessageModel CheckUserLogin(String uname, String upwd){
        MessageModel messageModel = new MessageModel();
        if(StringTool.isEmpty(uname)||StringTool.isEmpty(upwd)){
            messageModel.setResultcode(0);
            messageModel.setResultmessage("用户名或密码不能为空！");
            return messageModel;
        }
        User user = userDao.CheckUserName(uname);
        if(user == null){
            messageModel.setResultcode(0);
            messageModel.setResultmessage("用户名不存在！");
            return messageModel;
        }
        if(!upwd.equals(user.getUserpwd())){
            messageModel.setResultcode(0);
            messageModel.setResultmessage("密码错误！");
            return messageModel;
        }
        messageModel.setResultmessage("登陆成功");
        return messageModel;
    }
}
