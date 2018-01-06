package com.ericliu.dubbo.webapi.web.rest;

import com.ericliu.dubbo.api.auth.dto.AuthUserDTO;
import com.ericliu.dubbo.api.auth.service.AuthService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2017/12/12
 */
@Controller
@RequestMapping("/api/auth/codis")
public class TestRedisResouce {
    private static final Logger logger = LoggerFactory.getLogger(TestRedisResouce.class);


    private final StringRedisTemplate redisTemplate;


    public TestRedisResouce(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping
    public String get() {
        redisTemplate.opsForSet().add("test", "1");
        return "success";
    }


}
