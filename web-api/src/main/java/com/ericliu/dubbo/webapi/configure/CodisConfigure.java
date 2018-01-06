package com.ericliu.dubbo.webapi.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author <a href=mailto:ericliu@fivewh.com>ericliu</a>,Date:2018/1/5
 */
@Configuration
public class CodisConfigure {

    @Value("${spring.codis.zookeeper.url}")
    private String zookeeperUrl;
    @Value("${spring.codis.zkproxy.dir}")
    private String zkproxyDir;


    private CoidsConnectionFactory coidsConnectionFactory;

    @Bean
    public CoidsConnectionFactory coidsConnectionFactory() {
        coidsConnectionFactory = new CoidsConnectionFactory();
        coidsConnectionFactory.setZkProxyDir(zkproxyDir);
        coidsConnectionFactory.setZkServer(zookeeperUrl);
        return coidsConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, String> getRedisTemplate() {
        return new StringRedisTemplate(coidsConnectionFactory);
    }

}
