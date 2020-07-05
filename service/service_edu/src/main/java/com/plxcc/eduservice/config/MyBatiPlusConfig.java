package com.plxcc.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
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
     * 逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}
