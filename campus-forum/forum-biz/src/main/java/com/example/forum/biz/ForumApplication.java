package com.example.forum.biz;

import com.example.common.docs.annotation.EnableDoc;
import com.example.common.feign.annotation.EnableFeignClients;
import com.example.common.security.annotation.EnableResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableFeignClients(basePackages = "com.example")
@MapperScan("com.example.forum.biz.mapper")
@EnableDoc("forum")
public class ForumApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForumApplication.class, args);
    }
}
