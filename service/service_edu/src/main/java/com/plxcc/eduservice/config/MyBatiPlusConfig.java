package com.plxcc.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @PackgeName: com.plxcc.eduservice.config
 * @ClassName: MyBatiPlusConfig
 * @Author: plxc
 * Date: 2020/7/5 0:23
 * project name: edu_parent
 * @Version:
 * @Description:
 */
@Configuration
public class MyBatiPlusConfig {

    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

}
