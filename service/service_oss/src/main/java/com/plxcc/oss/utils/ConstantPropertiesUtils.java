package com.plxcc.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @PackgeName: com.plxcc.oss.utils
 * @ClassName: ConstantPropertiesUtils
 * @Author: plxc
 * Date: 2020/7/8 18:02
 * project name: edu_parent
 * @Version:
 * @Description:阿里云配置属性读取常量类
 */


@Component
//当项目启动，执行一个spring接口方法,继承InitializingBean
public class ConstantPropertiesUtils  implements InitializingBean {

    //读取配置文件
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.file.keyId}")
    private String keyId;
    @Value("${aliyun.oss.file.keySercert}")
    private String keySercert;
    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;


    //定义公开的静态常量
    public static String ENDPOINT;
    public static String KEY_ID;
    public static String KEY_SERCERT;
    public static String BUCKET_NAME;
    @Override
    public void afterPropertiesSet() throws Exception {

        ENDPOINT=this.endpoint;
        KEY_ID=this.keyId;
        KEY_SERCERT=this.keySercert;
        BUCKET_NAME=this.bucketName;

    }
}
