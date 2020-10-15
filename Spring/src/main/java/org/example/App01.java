package org.example;

import org.example.dao.TypeDao;
import org.example.service.AccountService;
import org.example.service.TypeService;
import org.example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App01
{
    public static void main( String[] args )
    {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring01.xml");
        TypeDao typeDao = (TypeDao) ac.getBean("typeDao");
        typeDao.test();
        TypeService typeService = (TypeService) ac.getBean("typeService");
        typeService.test();
    }
}
