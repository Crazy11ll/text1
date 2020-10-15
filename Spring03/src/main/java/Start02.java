import org.example.dao.TypeDao;
import org.example.service.TypeService;
import org.example.service.UserService;
import org.example.service.UserService02;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start02 {
    public static void main(String[] args) {
        //获取上下文环境
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring02.xml");

        UserService02 userService02 = (UserService02) ac.getBean("userService02");
        userService02.test();
        TypeService typeService = (TypeService) ac.getBean("typeservice");
        typeService.test();
    }

}

