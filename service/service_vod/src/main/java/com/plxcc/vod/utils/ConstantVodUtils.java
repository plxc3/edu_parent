package com.plxcc.vod.utils;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @PackgeName: com.plxcc.vod.utils
 * @ClassName: ConstantVodUtils
 * @Author: plxc
 * Date: 2020/7/28 20:31
 * project name: edu_parent
 * @Version:
 * @Description:
 */

/**
 * aliyun:
 *   vod:
 *     file:
 *       keyid: LTAI4GKbm3sbKrQutNLfpD6g
 *       keysercert: otsw0JVsvBW5hsBbmXn3lMN3ox1FiL
 */
@Component
public class ConstantVodUtils implements InitializingBean {

    @Value("${aliyun.vod.file.keyid}")
    private String keyid;

    @Value("${aliyun.vod.file.keysercert}")
    private String keysercert;

    public static String KEY_ID;
    public static String KEY_SERCERT;

    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID=keyid;
        KEY_SERCERT=keysercert;
    }
}
