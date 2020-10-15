package org.example.service;

import org.example.dao.TypeDao;

public class TypeService {
    private TypeDao typeDao;

    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }
    public void test(){
        System.out.println("Service.text");
        typeDao.test();
    }
}
