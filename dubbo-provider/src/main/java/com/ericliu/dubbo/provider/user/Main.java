package com.ericliu.dubbo.provider.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/11/28
 */
@Configuration
@EnableJpaRepositories(basePackages="com.ericliu.dubbo.provider.*.repo",entityManagerFactoryRef="emf")
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/*-provider.xml","application.xml");
        System.out.println(context.getDisplayName() + ": here");
        context.start();
        System.out.println("服务已经启动...");

        synchronized (Main.class){
            while (true) {
                try {
                    Main.class.wait();
                } catch (InterruptedException e) {
                }
            }
        }

    }
}
