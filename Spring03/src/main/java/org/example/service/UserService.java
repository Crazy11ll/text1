package org.example.service;

import org.example.dao.UserDao;


import java.util.List;

public class UserService {
    //set 方法注入
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    //常用类型
    private String host;
    public void setHost(String host) {
        this.host = host;
    }
    //List集合类型
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }
    private void print(){
        list.forEach((v->System.out.println(v)));
    }
    public void test(){
        System.out.println("servicetext");
        userDao.test();
        System.out.println("常用类型"+host);
        print();

    }
}
