package org.example;

import org.example.service.UserDao;
import org.example.service.UserService;
import org.example.spring.MyFactory;
import org.example.spring.MyclassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MyFactory factory = new MyclassPathXmlApplicationContext("spring.xml");
        UserDao userDao = (UserDao) factory.getBean("userdo");
        userDao.test();
        UserService userservice = (UserService) factory.getBean("userservice");
        userservice.test();

    }
}
