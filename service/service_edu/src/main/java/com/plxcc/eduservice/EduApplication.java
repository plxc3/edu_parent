package com.plxcc.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @PackgeName: com.plxcc.eduservice
 * @ClassName: EduApplication
 * @Author: plxc
 * Date: 2020/7/4 23:00
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@SpringBootApplication
@MapperScan("com.plxcc.eduservice.mapper")
@ComponentScan(basePackages = {"com.plxcc"})
//开启服务调用
@EnableFeignClients
//开启nacos注册
@EnableDiscoveryClient
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
