package com.plxcc.vod.utils;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @PackgeName: com.plxcc.vod.utils
 * @ClassName: AliyunVodSDKUtils
 * @Author: plxc
 * Date: 2020/7/30 19:43
 * project name: edu_parent
 * @Version:
 * @Description:
 */
public class AliyunVodSDKUtils {
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        /**
         * 接入区域不能更改
         */
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
