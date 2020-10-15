package org.example.service;

import org.example.dao.IUserDao;
import org.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

    public class UserService {

    @Resource
    private UserDao userDao;

    @Resource(name = "userDao01")
    private IUserDao iUserDao;
    public void test(){
        System.out.println("Service.test");
        userDao.test();
        iUserDao.test();
    }

    public void setUserdao(UserDao userdao) {
        this.userDao = userdao;
    }

}
