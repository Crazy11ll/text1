import org.example.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start01 {
    public static void main(String[] args) {
        //获取上下文环境
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");

        UserService userService = (UserService) ac.getBean("userservice");
        userService.test();
    }
}

