package com.example.service.biz;

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
@MapperScan("com.example.service.biz.mapper")
@EnableDoc("service")
public class ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceApplication.class, args);
	}
}
