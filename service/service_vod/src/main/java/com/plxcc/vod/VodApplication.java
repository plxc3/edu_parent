package com.plxcc.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @PackgeName: com.plxcc.vod
 * @ClassName: VodApplication
 * @Author: plxc
 * Date: 2020/7/27 23:36
 * project name: edu_parent
 * @Version:
 * @Description:
 */
//不去加载数据库
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.plxcc")
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class,args);
    }
}
