package org.example;

import org.example.service.AccountService;
import org.example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) ac.getBean("userservice");
        userService.test();
        System.out.println( "Hello World!" );
        AccountService accountService = (AccountService) ac.getBean("accountService");
        accountService.test();
        String s="   1 37a a  a a   ";
        System.out.println(s.trim());
    }
}
