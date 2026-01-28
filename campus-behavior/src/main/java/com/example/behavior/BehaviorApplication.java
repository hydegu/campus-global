package com.example.behavior;

import com.example.common.docs.annotation.EnableDoc;
import com.example.common.feign.annotation.EnableFeignClients;
import com.example.common.security.annotation.EnableResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
@EnableFeignClients(basePackages = "com.example")
@MapperScan("com.example.behavior.mapper")
@EnableDoc("behavior")
public class BehaviorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BehaviorApplication.class, args);
	}
}
