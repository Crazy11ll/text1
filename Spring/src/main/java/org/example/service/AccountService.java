package org.example.service;

import org.example.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AccountService {
    @Autowired
    @Qualifier(value = "accountDao")
    private AccountDao accountDao;

    public void test(){
       System.out.println("AccountService.test");
       accountDao.test();
    }
}
