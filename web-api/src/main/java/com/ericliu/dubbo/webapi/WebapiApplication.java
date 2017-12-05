package com.ericliu.dubbo.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@Configuration
//@PropertySource("classpath:dubbo/dubbo.properties")
@ImportResource({ "classpath:dubbo/dubbo-*.xml" })
public class WebapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebapiApplication.class, args);
	}
}
