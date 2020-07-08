package com.plxcc.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @PackgeName: com.plxcc.oss
 * @ClassName: OssApplication
 * @Author: plxc
 * Date: 2020/7/8 17:52
 * project name: edu_parent
 * @Version:
 * @Description:
 */
//启动时不去找数据源的配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = "com.plxcc")
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
