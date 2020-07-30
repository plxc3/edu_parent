package com.plxcc.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @PackgeName: com.plxcc.vod.service
 * @ClassName: VodService
 * @Author: plxc
 * Date: 2020/7/28 19:51
 * project name: edu_parent
 * @Version:
 * @Description:
 */
public interface VodService {
    String uploadVideoAly(MultipartFile file);

    void removeVideo(String id);
}
