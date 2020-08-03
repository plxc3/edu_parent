package com.plxcc.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @PackgeName: com.plxcc.cms
 * @ClassName: CmsApplication
 * @Author: plxc
 * Date: 2020/8/1 1:14
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.plxcc")
@MapperScan("com.plxcc.cms.mapper")
//开启nacos注册
@EnableDiscoveryClient
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
