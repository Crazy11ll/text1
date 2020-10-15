package org.example.dao;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final String username="admin";
    private final String userpwd ="admin";
    /**
     * 通过用户名查询是否存在用户
     */

    public User CheckUserName(String uname){
        User user = null;
        if(!username.equals(uname.trim())) return null;
        user = new User();
        user.setUsername(username);
        user.setUserpwd(userpwd);
        return user;
    }
}
