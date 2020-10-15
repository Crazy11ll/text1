package org.example.service;

import org.example.dao.UserDao;
import org.example.dao.UserDao02;

import java.util.List;

public class UserService02 {

    private UserDao02 userDao02;

    public UserService02(UserDao02 userDao02) {
        this.userDao02 = userDao02;
    }

    public void test(){
        System.out.println("service02.text");
        userDao02.test();

    }
}
