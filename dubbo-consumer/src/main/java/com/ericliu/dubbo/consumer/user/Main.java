package com.ericliu.dubbo.consumer.user;

import com.ericliu.dubbo.api.user.dto.UserDTO;
import com.ericliu.dubbo.api.user.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/11/28
 */
public class Main {

//    @Autowired
    private static UserService userService;

    public static void main(String[] args) {
//测试常规服务
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("dubbo/*-consumer.xml");
        context.start();
        System.out.println("consumer start");
        UserService userService = context.getBean(UserService.class);
        System.out.println("userService");
        UserDTO user=new UserDTO();
        user.setId(1l);
        user.setName("liuhao");
        System.out.println(userService.save(user));
    }

}
