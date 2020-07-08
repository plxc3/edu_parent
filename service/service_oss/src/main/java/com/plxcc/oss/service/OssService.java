package com.plxcc.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @PackgeName: com.plxcc.oss.service
 * @ClassName: OssService
 * @Author: plxc
 * Date: 2020/7/8 18:12
 * project name: edu_parent
 * @Version:
 * @Description:
 */
public interface OssService {
    String uploadAvatar(MultipartFile file) throws Exception;
}
