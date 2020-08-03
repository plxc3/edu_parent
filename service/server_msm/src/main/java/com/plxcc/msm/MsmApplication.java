package com.plxcc.msm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @PackgeName: com.plxcc.msm
 * @ClassName: MsmApplication
 * @Author: plxc
 * Date: 2020/8/2 19:35
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.plxcc")
@MapperScan("com.plxcc.msm.mapper")
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class,args);
    }
}
