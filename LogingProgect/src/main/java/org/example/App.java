package org.example;

import org.example.controller.UserControl;
import org.example.entity.vo.MessageModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        UserControl userControl = (UserControl) ac.getBean("userControl");
        System.out.print("请输入登录账号：");
        String num = cin.next();
        System.out.print("请输入登录密码：");
        String psw = cin.next();
        MessageModel messageModel = userControl.UserLogin(num,psw);
        System.out.println("状态码："+messageModel.getResultcode()+"\n"+"提示信息："+messageModel.getResultmessage());
    }
}
